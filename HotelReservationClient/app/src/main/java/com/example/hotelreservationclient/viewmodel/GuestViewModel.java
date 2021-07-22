package com.example.hotelreservationclient.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hotelreservationclient.model.ConfirmResponse;
import com.example.hotelreservationclient.model.GuestsRequest;
import com.example.hotelreservationclient.model.HotelsResponse;
import com.example.hotelreservationclient.repository.HotelRepository;

import org.jetbrains.annotations.NotNull;

/**
 * didn't use right now, leave for future improvement of two way databinding
 * solving the recyclerView contains edittext need to be iterated to assignment the guestConfirmRequest
 */
public class GuestViewModel extends AndroidViewModel {


    // live data which like a bridge between app backend and view
    private LiveData<ConfirmResponse> getConfirmResponseLiveData;
    // hotel repository, where we get hotel list data
    private HotelRepository hotelRepository;


    public GuestViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public void sendConfirmRequest(GuestsRequest guestsRequest){
        hotelRepository.requestReservation(guestsRequest);
    }

    // initial hotel repository and livedata
    public void init() {
        hotelRepository = new HotelRepository();
        getConfirmResponseLiveData = hotelRepository.getConfirmResponseLiveData();
    }

    public LiveData<ConfirmResponse> getConfirmResponseLiveData() {
        return getConfirmResponseLiveData;
    }
}
