package edu.spu.se411.lab07_polymorphism;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class CarRentalBooking extends Booking {

    private double dailyRentalRate;
    private Integer numberOfDays;

    public CarRentalBooking(String bookingId,
                            String customerFullName,
                            String travelDate,
                            String destinationCity,
                            double dailyRentalRate) {

        super(bookingId, customerFullName, travelDate, destinationCity);

        this.dailyRentalRate = dailyRentalRate;
        this.numberOfDays = null;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    @Override
    public double calculateTotalPrice()
            throws MissingInformationException, InvalidArgumentException {

        if (numberOfDays == null) {
            throw new MissingInformationException(
                    "Number of rental days has not been provided.");
        }

        if (numberOfDays < 1 ||
            numberOfDays > GlobalConfig.MAX_RENTAL_DAYS) {

            throw new InvalidArgumentException(
                    "Rental days must be between 1 and "
                    + GlobalConfig.MAX_RENTAL_DAYS + ".");
        }

        return dailyRentalRate * numberOfDays;
    }
}