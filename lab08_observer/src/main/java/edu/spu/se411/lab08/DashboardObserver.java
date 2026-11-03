package edu.spu.se411.lab08;

/** First observer: displays the new reading on the dashboard. */
public class DashboardObserver implements Observer {

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor) {
            Sensor sensor = (Sensor) subject;
            System.out.printf("[Dashboard] %s reading is now %.2f %s%n",
                    sensor.getName(), sensor.getReading(), sensor.getUnit());
        }
    }
}
