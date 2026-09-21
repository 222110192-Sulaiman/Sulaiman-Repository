package edu.spu.se411.lab07_polymorphism;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class TrainBooking extends Booking {

    private SeatClass seatClass;
    private Double distance;

    public TrainBooking(String bookingId,
                        String customerFullName,
                        String travelDate,
                        String destinationCity,
                        SeatClass seatClass) {

        super(bookingId, customerFullName, travelDate, destinationCity);

        this.seatClass = seatClass;
        this.distance = null;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public double calculateTotalPrice()
            throws MissingInformationException, InvalidArgumentException {

        if (distance == null) {
            throw new MissingInformationException(
                    "Train distance has not been provided.");
        }

        if (distance < 1 ||
            distance > GlobalConfig.MAX_TRAIN_DISTANCE) {

            throw new InvalidArgumentException(
                    "Distance must be between 1 and "
                    + GlobalConfig.MAX_TRAIN_DISTANCE + " km.");
        }

        if (seatClass == SeatClass.STANDARD) {
            return distance * GlobalConfig.TRAIN_STANDARD_RATE;
        } else {
            return distance * GlobalConfig.TRAIN_FIRST_CLASS_RATE;
        }
    }
}