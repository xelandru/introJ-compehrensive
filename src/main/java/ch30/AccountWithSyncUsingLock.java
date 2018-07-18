package ch30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithSyncUsingLock {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.execute(new AddPennyTask());
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            System.out.println("Balance: " + account.getBalance());
        }
    }


    private static class Account {

        private int balance = 0;

        private static Lock lock = new ReentrantLock();



        public int getBalance() {
            return balance;
        }
        public void deposit(int amount) {

            lock.lock();

            try {
                int newBalance = balance + amount;
                Thread.sleep(10);
                balance = newBalance;
            } catch (InterruptedException e) {

            }
            finally {
                lock.unlock();
            }
        }
    }

    private static class AddPennyTask implements Runnable {
        @Override
        public void run() {

            account.deposit(1);
        }
    }
}
