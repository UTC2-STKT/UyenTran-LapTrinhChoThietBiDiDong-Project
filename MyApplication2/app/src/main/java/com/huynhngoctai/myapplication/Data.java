package com.huynhngoctai.myapplication;

public class Data {
    private int id;
    private String dateTime;
    private Long voltage;
    private Long current;
    private Long power;
    private Long energy;
    private Long frequency;
    private Long powerFactor;

    public Data(int id, String dateTime, Long voltage, Long current, Long power, Long energy, Long frequency, Long powerFactor) {
        this.id = id;
        this.dateTime = dateTime;
        this.voltage = voltage;
        this.current = current;
        this.power = power;
        this.energy = energy;
        this.frequency = frequency;
        this.powerFactor = powerFactor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getVoltage() {
        return voltage;
    }

    public void setVoltage(Long voltage) {
        this.voltage = voltage;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getEnergy() {
        return energy;
    }

    public void setEnergy(Long energy) {
        this.energy = energy;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public Long getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(Long powerFactor) {
        this.powerFactor = powerFactor;
    }
}

