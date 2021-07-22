package com.example.hotelreservationclient.network;

import com.example.hotelreservationclient.model.ConfirmResponse;
import com.example.hotelreservationclient.model.GuestsRequest;
import com.example.hotelreservationclient.model.HotelsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

//    @Headers("Content-type: application/json")
    @POST("/reservation")
    public Call<ConfirmResponse> requestReservation(@Body GuestsRequest guestsRequest);

}
