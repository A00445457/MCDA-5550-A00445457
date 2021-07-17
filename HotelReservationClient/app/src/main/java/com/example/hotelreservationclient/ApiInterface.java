package com.example.hotelreservationclient;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ApiInterface {

    // API's endpoints
    @GET("/hotellist")
    public void getHotelsLists(Callback<Map<String, List<HotelListData>>> callback);
    // API's endpoints
    @GET("/availablehotel")
    public void getAvailableHotelsLists(Callback<Map<String, List<HotelListData>>> callback);
}
