package com.example.hotelreservationclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GuestsRequest implements Serializable {

    // a list of hotel which contains hotel model
    @SerializedName("guest_list")
    @Expose
    public List<GuestModel> guests_list = null;

    @SerializedName("hotel_name")
    @Expose
    public String hotel_name = null;
    @SerializedName("checkin")
    @Expose
    public String checkin = null;
    @SerializedName("checkout")
    @Expose
    public String checkout = null;

    public List<GuestModel> getGuests_list() {
        return guests_list;
    }

    public void setGuests_list(List<GuestModel> guests_list) {
        this.guests_list = guests_list;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


}
