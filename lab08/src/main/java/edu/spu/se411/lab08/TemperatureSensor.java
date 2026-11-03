package edu.spu.se411.lab08;

public class TemperatureSensor extends Sensor {

    public TemperatureSensor(String name) {
        super(name);
    }

    @Override
    public String getUnit() {
        return "C";
    }

    @Override
    public TemperatureSensor clone() {
        return (TemperatureSensor) super.clone();
    }
}
