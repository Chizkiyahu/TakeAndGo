package com.example.chizkiyahuandchaskyh.takeandgo.model.entities;

public class Address {

    protected String country;

    public Address(String country, String city, String street, int houseNum) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
    }

    protected String city;
    protected String street;
    protected int houseNum;
    protected double Latitude;
    protected double longitude;




    @Override
    public String toString() {
        return houseNum + street + ", "  + city + ", " + country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
