package edu.psu.se411.Lab05;

import edu.psu.se411.exceptions.InvalidAgeException;

public class App {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Invalid age: You must be 18 or older.");
        }

        System.out.println("Age valid.");
    }

    public static void main(String[] args) {
        try {
            validateAge(15);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}