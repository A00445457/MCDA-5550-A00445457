package com.example.hotelreservationclient.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hotelreservationclient.model.HotelsResponse;
import com.example.hotelreservationclient.network.HotelSearchService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelRepository {

    // base url that provide hotel search and book service
    private static final String HOTEL_SEARCH_SERVICE_BASE_URL = "http://a00445457hotelreservation-env.eba-ymyy8p6r.us-east-1.elasticbeanstalk.com/";

    private HotelSearchService hotelSearchService;
    private MutableLiveData<HotelsResponse> hotelsResponseLiveData;



    /**
     * in the constructor, prepare the instance of hotelsearchService and MutableLiveData for hotel
     */
    public HotelRepository() {
        hotelsResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // get instacne of hotelsearchservice
        hotelSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(HOTEL_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HotelSearchService.class);

    }

    /**
     * call hotel search service and get Hotel response data and bind it with hotelsResponseLiveData
     * for viewmodel to binding with view
     */
    public void searchHotels() {
        hotelSearchService.searchHotels()
                .enqueue(new Callback<HotelsResponse>() {
                    @Override
                    public void onResponse(Call<HotelsResponse> call, Response<HotelsResponse> response) {
                        if (response.body() != null) {
                            //get response successfully and bind response data with live data
                            hotelsResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<HotelsResponse> call, Throwable t) {
                        hotelsResponseLiveData.postValue(null);
                    }
                });
    }

    /**
     * provide hotel list get from api
     * @return live data which could binding to view
     */
    public LiveData<HotelsResponse> getHotelsResponseLiveData() {
        return hotelsResponseLiveData;
    }
}


