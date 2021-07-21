package com.example.hotelreservationclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuestsRequest {

    // a list of hotel which contains hotel model
    @SerializedName("guest_list")
    @Expose
    public List<GuestModel> guests_list = null;

    @SerializedName("hotel_name")
    @Expose
    public String hotel_name=null;
    @SerializedName("checkout")
    @Expose
    public String checkin=null;
    @SerializedName("checkout")
    @Expose
    public String checkout=null;

}
