
package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static Integer nbAccounts = 10;
    public static Double initialBalance = 1000.0;
    public static Integer nbTransactions = 10000;

    public static void main(String[] args)
            throws InterruptedException {

        logger.info("Application is starting...");

        // 1. Create 10 accounts
        CryptoAccount[] accounts =
                new CryptoAccount[nbAccounts];

        for (int i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount(
                    "acc_" + i, initialBalance);
        }

        System.out.println("Initial total: "
                + calculateTotal(accounts));

        // 2. Create 10,000 threads in batches
        int batchSize = 250;

        for (int start = 0;
                start < nbTransactions;
                start += batchSize) {

            int end = Math.min(
                    start + batchSize, nbTransactions);

            Thread[] threads = new Thread[end - start];

            for (int i = start; i < end; i++) {

                final int id = i;

                threads[i - start] = new Thread(() -> {

                    CryptoAccount randomA = accounts[
                            (int)(Math.random() * nbAccounts)];

                    CryptoAccount randomB = accounts[
                            (int)(Math.random() * nbAccounts)];

                    Double randomAmount =
                            Math.random() * 10 + 1;

                    Transaction t = new Transaction(
                            "T" + id,
                            randomA,
                            randomB,
                            randomAmount);

                    t.execute();
                });

                threads[i - start].start();
            }

            // Wait for all threads in this batch
            for (Thread thread : threads) {
                thread.join();
            }
        }

        // 3. Calculate final total
        System.out.println("Final total: "
                + calculateTotal(accounts));

        logger.info("Application is closing...");
    }

    public static double calculateTotal(
            CryptoAccount[] accounts) {

        double total = 0;

        for (CryptoAccount account : accounts) {
            total += account.getBalance();
        }

        return total;
    }
}