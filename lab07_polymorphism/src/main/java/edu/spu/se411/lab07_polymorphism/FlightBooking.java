package edu.spu.se411.lab07_polymorphism;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class FlightBooking extends Booking {

    private double baseTicketPrice;
    private Double luggageWeight;

    public FlightBooking(String bookingId,
                         String customerFullName,
                         String travelDate,
                         String destinationCity,
                         double baseTicketPrice) {

        super(bookingId, customerFullName, travelDate, destinationCity);

        this.baseTicketPrice = baseTicketPrice;
        this.luggageWeight = null;
    }

    public void setLuggageWeight(double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public double getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public Double getLuggageWeight() {
        return luggageWeight;
    }

    @Override
    public double calculateTotalPrice()
            throws MissingInformationException, InvalidArgumentException {

        if (luggageWeight == null) {
            throw new MissingInformationException(
                    "Luggage weight has not been provided.");
        }

        if (luggageWeight < 0 ||
            luggageWeight > GlobalConfig.MAX_LUGGAGE_WEIGHT) {

            throw new InvalidArgumentException(
                    "Luggage weight must be between 0 and "
                    + GlobalConfig.MAX_LUGGAGE_WEIGHT + " kg.");
        }

        return baseTicketPrice
                + (luggageWeight * GlobalConfig.EXTRA_LUGGAGE_RATE);
    }
}