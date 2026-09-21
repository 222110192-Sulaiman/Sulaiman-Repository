package edu.spu.se411.lab07_polymorphism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class App {

    private static final Logger logger =
            LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Application is starting...");

        try {

            FlightBooking flight = new FlightBooking(
                    "F001",
                    "Sulaiman AlHoshan",
                    "2026-09-25",
                    "Dubai",
                    500.0
            );

            flight.setLuggageWeight(20);


            TrainBooking train = new TrainBooking(
                    "T001",
                    "Sulaiman AlHoshan",
                    "2026-09-26",
                    "Jeddah",
                    SeatClass.FIRST_CLASS
            );

            train.setDistance(950);


            CarRentalBooking car = new CarRentalBooking(
                    "C001",
                    "Sulaiman AlHoshan",
                    "2026-09-27",
                    "Riyadh",
                    150.0
            );

            car.setNumberOfDays(5);


            System.out.println("===== FLIGHT =====");
            computeTotalPrice(flight);

            System.out.println();

            System.out.println("===== TRAIN =====");
            computeTotalPrice(train);

            System.out.println();

            System.out.println("===== CAR RENTAL =====");
            computeTotalPrice(car);

        } catch (MissingInformationException
                 | InvalidArgumentException e) {

            logger.error("Exception occurred: " + e.getMessage());

            System.out.println("Error: " + e.getMessage());

        } finally {

            logger.info("Application is stopping...");
        }
    }

    public static void computeTotalPrice(Booking booking)
            throws MissingInformationException, InvalidArgumentException {

        System.out.println(booking);

        System.out.printf(
                "Total Price: %.2f SAR%n",
                booking.calculateTotalPrice()
        );
    }
}