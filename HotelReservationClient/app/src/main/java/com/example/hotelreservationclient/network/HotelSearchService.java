package com.example.hotelreservationclient.network;

import com.example.hotelreservationclient.model.HotelsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit interface for searching hotels
 */
public interface HotelSearchService {

    // get all hotels
    @GET("/hotellist")
    Call<HotelsResponse> searchHotels();

    // get all available hotels
    @GET("/availablehotel")
    Call<HotelsResponse> searchAvaibaleHotels();

}
