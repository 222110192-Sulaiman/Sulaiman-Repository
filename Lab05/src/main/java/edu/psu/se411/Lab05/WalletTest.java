package edu.psu.se411.Lab05;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class WalletTest {

    public static void main(String[] args) {

        Wallet wallet = new Wallet(500);

        try {
            wallet.withdraw(600);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}