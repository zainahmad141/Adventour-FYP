package com.Travel.pk;

// Hotel.java
public class Hotel {
    private String name;
    private String address;
    private String timing;

    public Hotel(String name, String address, String timing) {
        this.name = name;
        this.address = address;
        this.timing = timing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
