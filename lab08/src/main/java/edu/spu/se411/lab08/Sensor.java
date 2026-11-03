package edu.spu.se411.lab08;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class for all sensors (the Subject).
 * Holds the shared logic (observer list, register/unregister/notify, reading, clone)
 * so that every concrete sensor reuses it.
 */
public abstract class Sensor implements Subject, Cloneable {

    static Logger logger = LoggerFactory.getLogger(Sensor.class);

    private String name;
    private double reading;
    private List<Observer> observers = new ArrayList<>();

    public Sensor(String name) {
        this.name = name;
    }

    /** Unit of the reading, each sensor type defines its own (polymorphism). */
    public abstract String getUnit();

    @Override
    public void register(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
            logger.info("Observer {} registered to {}", o.getClass().getSimpleName(), name);
        }
    }

    @Override
    public void unregister(Observer o) {
        if (observers.remove(o)) {
            logger.info("Observer {} unregistered from {}", o.getClass().getSimpleName(), name);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    /** Sets a new reading; observers are notified only if the value changed. */
    public void setReading(double reading) {
        if (Double.compare(this.reading, reading) != 0) {
            this.reading = reading;
            notifyObservers();
        }
    }

    public double getReading() {
        return reading;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getObserverCount() {
        return observers.size();
    }

    /**
     * The clone keeps the same name/reading but gets its OWN empty observer list,
     * so it is not observed by the observers of the original sensor.
     */
    @Override
    public Sensor clone() {
        try {
            Sensor copy = (Sensor) super.clone();
            copy.observers = new ArrayList<>();
            logger.info("{} cloned", name);
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Sensor must be cloneable", e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f %s", name, reading, getUnit());
    }
}
