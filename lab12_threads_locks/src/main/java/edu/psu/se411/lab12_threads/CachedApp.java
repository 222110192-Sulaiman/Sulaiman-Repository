
package edu.psu.se411.lab12_threads;

public class CachedApp {

    public static void main(String[] args)
            throws InterruptedException {

        App.runTest(1000, true);
        App.runTest(10, true);
    }
}

