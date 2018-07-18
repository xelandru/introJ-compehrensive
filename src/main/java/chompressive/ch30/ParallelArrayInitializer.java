package chompressive.ch30;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;


public class ParallelArrayInitializer {

    public static void main(String[] args) {

        final int SIZE = 10_000_000;
        double[] list = new double[SIZE];

        long startTime = System.currentTimeMillis();
        parallelAssignValues(list);
        long endTime = System.currentTimeMillis();

        System.out.println("Time parallel: " + (endTime - startTime) + " [ms]");
        System.out.println("Time parallel: " + (endTime - startTime) / 1000.0 + " [s]");

        double[] list2 = new double[SIZE];
        startTime = System.currentTimeMillis();
        for(int i=0;i<list2.length;i++)
            list2[i] = ThreadLocalRandom.current().nextDouble();
        endTime = System.currentTimeMillis();

        System.out.println("Time sequential: " + (endTime - startTime) + " [ms]");
        System.out.println("Time sequential: " + (endTime - startTime) / 1000.0 + " [s]");


    }

    public static void parallelAssignValues(double[] list) {

        RecursiveAction mainTask = new initializerTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);

    }

    private static class initializerTask extends RecursiveAction {

        private double[] list;
        private int start;
        private int end;
        private final int THRESHOLD = 10_000;


        public initializerTask(double[] list, int start, int end) {

            this.list = list;
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {
            if (end - start < THRESHOLD) {
                for (int i = start; i < end; i++)
                    list[i] = ThreadLocalRandom.current().nextDouble();

            } else {
                int middle = (start+end)/2;
                invokeAll(new initializerTask(list, start, middle), new initializerTask(list, middle, end));
            }
        }
    }
}
