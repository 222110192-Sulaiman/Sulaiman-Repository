package edu.spu.se411.lab08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Second observer: logs the new reading (console + log file). */
public class LoggerObserver implements Observer {

    static Logger logger = LoggerFactory.getLogger(LoggerObserver.class);

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor) {
            Sensor sensor = (Sensor) subject;
            System.out.printf("[Logger]    %s changed -> %.2f %s%n",
                    sensor.getName(), sensor.getReading(), sensor.getUnit());
            logger.info("{} changed -> {}", sensor.getName(), sensor);
        }
    }
}
