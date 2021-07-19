package com.example.hotelreservationclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hotelreservationclient.model.HotelsResponse;
import com.example.hotelreservationclient.repository.HotelRepository;

import org.jetbrains.annotations.NotNull;

/**
 * Hotel viewmodel which used for bingding between view and model
 */
public class HotelViewModel extends AndroidViewModel {

    // hotel repository, where we get hotel list data
    private HotelRepository hotelRepository;
    // live data which like a bridge between app backend and view
    private LiveData<HotelsResponse> hotelsResponseLiveData;

    public HotelViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    // initial hotel repository and livedata
    public void init() {
        hotelRepository = new HotelRepository();
        hotelsResponseLiveData = hotelRepository.getHotelsResponseLiveData();
    }

    // call search hotel function in hotel repository
    public void searchHotels() {
        hotelRepository.searchHotels();
    }

    // return livedata<hotelresponse>
    public LiveData<HotelsResponse> getHotelsResponseLiveData() {
        return hotelsResponseLiveData;
    }

}
