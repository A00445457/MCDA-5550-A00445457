package com.example.hotelreservationclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelModel {

    // key of hotel
    @SerializedName("hid")
    @Expose
    private String hid;

    // name of hotel
    @SerializedName("hotel_name")
    @Expose
    private String hotel_name;

    //cost of hotel in single night
    @SerializedName("price")
    @Expose
    private String price;

    // availability of hotel
    @SerializedName("availability")
    @Expose
    private boolean availability;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
