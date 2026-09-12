package edu.psu.se411.Lab05;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class Wallet {

    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }

        balance = balance - amount;

        System.out.println("Withdrawal successful.");
        System.out.println("Remaining balance: " + balance);
    }
}