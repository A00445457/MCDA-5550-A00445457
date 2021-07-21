package com.example.hotelreservationclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hotelreservationclient.model.GuestsRequest;
import com.example.hotelreservationclient.model.HotelsResponse;
import com.example.hotelreservationclient.repository.HotelRepository;

import org.jetbrains.annotations.NotNull;

public class GuestViewModel extends AndroidViewModel {


    // live data which like a bridge between app backend and view
    private LiveData<GuestsRequest> guestsRequestLiveData;

    public GuestViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public String sendConfirmRequest(){
        return null;
    }

    // initial hotel repository and livedata
    public void init() {
//        hotelRepository = new HotelRepository();
//        guestsRequestLiveData = hotelRepository.getGuestsRequestLiveData();
    }

    public LiveData<GuestsRequest> getGuestsRequestLiveData() {
        return guestsRequestLiveData;
    }
}
