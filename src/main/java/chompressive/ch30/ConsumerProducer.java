package chompressive.ch30;


import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {

    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();

    }


    private static class Buffer {

        private static final int CAPACITY = 1;
        private static LinkedList<Integer> queue = new LinkedList<>();
        private static Lock lock = new ReentrantLock();
        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        public void write(int value) {
            lock.lock();

            try {
                while (CAPACITY == queue.size()) {
                    notFull.await();
                }
                queue.offer(value);
                notEmpty.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int read() {
            int value = 0;
            lock.lock();

            try {
                while (queue.isEmpty()) {
                    notEmpty.await();
                }
                value = queue.remove(value);
                notFull.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }

    private static class ProducerTask implements Runnable {
        @Override
        public void run() {

            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes: " + i);
                    buffer.write(i++);
                    Thread.sleep((int) (Math.random() * 10_000));
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Consumer reads value:" + buffer.read());
                    Thread.sleep((int) (Math.random() * 10_000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}