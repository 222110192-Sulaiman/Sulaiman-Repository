package edu.spu.se411.lab08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class ObserverPatternTest {

    /** Simple observer that remembers how many times it was notified. */
    private static class CountingObserver implements Observer {
        int count = 0;
        Subject lastSubject = null;

        @Override
        public void update(Subject subject) {
            count++;
            lastSubject = subject;
        }
    }

    /** Runs the observer's update and returns what it printed. */
    private String printedBy(Observer observer, Subject subject) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            observer.update(subject);
        } finally {
            System.setOut(originalOut);
        }
        return buffer.toString();
    }

    // ---- Registration and unregistration ----

    @Test
    public void registeredObserverIsNotifiedWithTheSubject() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver observer = new CountingObserver();
        temp.register(observer);

        temp.setReading(25);

        assertEquals(1, observer.count);
        assertSame(temp, observer.lastSubject);
    }

    @Test
    public void allRegisteredObserversAreNotified() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver first = new CountingObserver();
        CountingObserver second = new CountingObserver();
        temp.register(first);
        temp.register(second);

        temp.setReading(25);

        assertEquals(1, first.count);
        assertEquals(1, second.count);
    }

    @Test
    public void unregisteredObserverIsNoLongerNotified() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver removed = new CountingObserver();
        CountingObserver kept = new CountingObserver();
        temp.register(removed);
        temp.register(kept);

        temp.unregister(removed);
        temp.setReading(25);

        assertEquals(0, removed.count);
        assertEquals(1, kept.count);
    }

    @Test
    public void registeringTwiceNotifiesOnlyOnce() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver observer = new CountingObserver();
        temp.register(observer);
        temp.register(observer);

        temp.setReading(25);

        assertEquals(1, observer.count);
    }

    // ---- Sensor updates ----

    @Test
    public void readingIsAlreadyUpdatedWhenObserverIsNotified() {
        HumiditySensor humidity = new HumiditySensor("Humidity");
        double[] seen = new double[1];
        humidity.register(subject -> seen[0] = ((Sensor) subject).getReading());

        humidity.setReading(45.5);

        assertEquals(45.5, seen[0]);
    }

    @Test
    public void sameReadingDoesNotNotifyAgain() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver observer = new CountingObserver();
        temp.register(observer);

        temp.setReading(25);
        temp.setReading(25);

        assertEquals(1, observer.count);
    }

    @Test
    public void oneObserverCanWatchBothSensors() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        HumiditySensor humidity = new HumiditySensor("Humidity");
        CountingObserver observer = new CountingObserver();
        temp.register(observer);
        humidity.register(observer);

        temp.setReading(25);
        assertSame(temp, observer.lastSubject);

        humidity.setReading(50);
        assertSame(humidity, observer.lastSubject);

        assertEquals(2, observer.count);
    }

    @Test
    public void eachSensorHasItsOwnUnit() {
        Sensor temp = new TemperatureSensor("Temp");
        Sensor humidity = new HumiditySensor("Humidity");

        assertEquals("C", temp.getUnit());
        assertEquals("%", humidity.getUnit());
    }

    // ---- Dashboard and logger observers ----

    @Test
    public void dashboardPrintsTheSensorChange() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        temp.setReading(25);

        String output = printedBy(new DashboardObserver(), temp);

        assertTrue(output.contains("[Dashboard]"));
        assertTrue(output.contains("Temp"));
    }

    @Test
    public void loggerPrintsAndWritesToTheLogFile() throws IOException {
        // A unique name so we only match the line written by this test
        String name = "LogFileSensor" + System.nanoTime();
        HumiditySensor humidity = new HumiditySensor(name);
        humidity.setReading(50);

        String output = printedBy(new LoggerObserver(), humidity);
        assertTrue(output.contains("[Logger]"));

        String logFile = new String(Files.readAllBytes(Paths.get("target/test-logs/log.out")));
        assertTrue(logFile.contains(name + " changed"));
    }

    // ---- Cloning ----

    @Test
    public void cloneCopiesTheDataIntoANewObjectOfTheSameType() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        temp.setReading(25);

        TemperatureSensor copy = temp.clone();

        assertNotSame(temp, copy);
        assertEquals(TemperatureSensor.class, copy.getClass());
        assertEquals("Temp", copy.getName());
        assertEquals(25, copy.getReading());

        HumiditySensor humidity = new HumiditySensor("Humidity");
        assertEquals(HumiditySensor.class, humidity.clone().getClass());
    }

    @Test
    public void cloneDoesNotKeepTheObserversOfTheOriginal() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver observer = new CountingObserver();
        temp.register(observer);

        TemperatureSensor copy = temp.clone();
        copy.setReading(99);

        assertEquals(0, copy.getObserverCount());
        assertEquals(0, observer.count);
        assertEquals(1, temp.getObserverCount());
    }

    @Test
    public void cloneAndOriginalHaveIndependentObservers() {
        TemperatureSensor temp = new TemperatureSensor("Temp");
        CountingObserver originalObserver = new CountingObserver();
        temp.register(originalObserver);

        TemperatureSensor copy = temp.clone();
        CountingObserver copyObserver = new CountingObserver();
        copy.register(copyObserver);

        temp.setReading(25);
        assertEquals(1, originalObserver.count);
        assertEquals(0, copyObserver.count);

        copy.setReading(30);
        assertEquals(1, originalObserver.count);
        assertEquals(1, copyObserver.count);

        // Changing the clone does not change the original's reading
        assertEquals(25, temp.getReading());
        assertFalse(temp.getReading() == copy.getReading());
    }
}
