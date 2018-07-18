package chompressive.ch30;


public class TaskThreadDemo {
    public static void main(String[] args) {


        Runnable printA = new PrintChar('a',1000);
        Runnable printB = new PrintChar('b',1000);
        Runnable print100 = new PrintNum(1000);

        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);
        thread3.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class PrintChar implements Runnable {

    private char charToPrint;
    private int times;

    public PrintChar(char charToPrint, int times) {
        this.charToPrint = charToPrint;
        this.times = times;
    }


    @Override
    public void run() {

        int cnt = 0;
        while (cnt++ < times)
            System.out.print(" " + charToPrint);

    }
}

class PrintNum implements Runnable {

    private int lastNum;
    /** Construct a task for printing 1, 2, ..., n */
    public PrintNum(int n) {
        lastNum = n;
    }
    @Override /** Tell the thread how to run */
    public void run() {
            for (int i = 1; i <= lastNum; i++) {
                System.out.print(" " + i);
            }

    }
}
