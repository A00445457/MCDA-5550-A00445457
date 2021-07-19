package com.example.hotelreservationclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Dealing with response of request hotel list
 */
public class HotelsResponse {

    // a list of hotel which contains hotel model
    @SerializedName("hotels_list")
    @Expose
    List<HotelModel> hotels_list = null;

    public List<HotelModel> getHotels_list() {
        return hotels_list;
    }

}
