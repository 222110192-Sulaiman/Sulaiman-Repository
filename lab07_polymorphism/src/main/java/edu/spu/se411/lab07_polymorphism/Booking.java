package edu.spu.se411.lab07_polymorphism;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public abstract class Booking {

    private String bookingId;
    private String customerFullName;
    private String travelDate;
    private String destinationCity;

    public Booking(String bookingId,
                   String customerFullName,
                   String travelDate,
                   String destinationCity) {

        this.bookingId = bookingId;
        this.customerFullName = customerFullName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public abstract double calculateTotalPrice()
            throws MissingInformationException, InvalidArgumentException;

    @Override
    public String toString() {
        return "Booking ID: " + bookingId
                + "\nCustomer: " + customerFullName
                + "\nTravel Date: " + travelDate
                + "\nDestination: " + destinationCity;
    }
}