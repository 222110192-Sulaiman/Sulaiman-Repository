package edu.spu.se411.lab08;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");

        // Two sensors (subjects)
        TemperatureSensor temp = new TemperatureSensor("Temperature Sensor");
        HumiditySensor humidity = new HumiditySensor("Humidity Sensor");

        // Two observers
        Observer dashboard = new DashboardObserver();
        Observer loggerObserver = new LoggerObserver();

        // Register the observers to the sensors
        temp.register(dashboard);
        temp.register(loggerObserver);
        humidity.register(dashboard);
        humidity.register(loggerObserver);

        Random random = new Random();

        // Simulate sensor readings changes
        for (int i = 0; i < 10; i++) {
            temp.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // ---- Cloning test ----
        System.out.println("\n===== Cloning the temperature sensor =====");
        TemperatureSensor tempClone = temp.clone();
        tempClone.setName("Temperature Sensor (Clone)");
        System.out.println("Original: " + temp + " | observers = " + temp.getObserverCount());
        System.out.println("Clone:    " + tempClone + " | observers = " + tempClone.getObserverCount());

        System.out.println("\nChanging the clone's reading (no observers -> nothing printed):");
        tempClone.setReading(50);

        System.out.println("\nRegistering only the dashboard to the clone and changing it:");
        tempClone.register(dashboard);
        tempClone.setReading(55);

        System.out.println("\nChanging the original (still notifies its own 2 observers):");
        temp.setReading(25);

        // Unregister test
        System.out.println("\nUnregistering the logger from the original and changing it:");
        temp.unregister(loggerObserver);
        temp.setReading(26);

        logger.info("Application is ending...");
    }
}
