
package edu.psu.se411.lab12_threads;

import java.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.CryptoAccount;
import edu.psu.se411.lab12_threads.model.Transaction;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args)
            throws InterruptedException {

        logger.info("Application is starting...");

        runTest(1000, false);
        runTest(10, false);

        logger.info("Application is closing...");
    }

    public static void runTest(
            int nbAccounts, boolean cached)
            throws InterruptedException {

        int nbTransactions = 1000000;
        double initialBalance = 1000.0;

        CryptoAccount[] accounts =
                new CryptoAccount[nbAccounts];

        for (int i = 0; i < nbAccounts; i++) {
            accounts[i] = new CryptoAccount(
                    "acc_" + i, initialBalance);
        }

        ExecutorService executor;

        if (cached) {
            executor = Executors.newCachedThreadPool();
        } else {
            executor = Executors.newFixedThreadPool(16);
        }

        // Limit pending tasks to prevent overload
        Semaphore permits = new Semaphore(200);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < nbTransactions; i++) {

            permits.acquire();

            final int id = i;

            executor.execute(() -> {
                try {
                    ThreadLocalRandom random =
                            ThreadLocalRandom.current();

                    int senderIndex =
                            random.nextInt(nbAccounts);

                    int receiverIndex =
                            random.nextInt(nbAccounts - 1);

                    if (receiverIndex >= senderIndex) {
                        receiverIndex++;
                    }

                    double amount =
                            random.nextDouble(1, 101);

                    Transaction t = new Transaction(
                            "id_" + id,
                            accounts[senderIndex],
                            accounts[receiverIndex],
                            amount);

                    t.run();

                } finally {
                    permits.release();
                }
            });
        }

        executor.shutdown();

        if (!executor.awaitTermination(
                30, TimeUnit.MINUTES)) {
            executor.shutdownNow();
            throw new IllegalStateException(
                    "Transactions did not finish");
        }

        long endTime = System.currentTimeMillis();

        double total = 0;

        for (CryptoAccount account : accounts) {
            total += account.getBalance();
        }

        System.out.println("------------------------");
        System.out.println(cached
                ? "CACHED THREAD POOL"
                : "FIXED THREAD POOL");

        System.out.println("Accounts: " + nbAccounts);
        System.out.println("Transactions: " + nbTransactions);
        System.out.println("Initial total: "
                + (nbAccounts * initialBalance));
        System.out.println("Final total: " + total);
        System.out.println("Time: "
                + (endTime - startTime) + " ms");
    }
}
