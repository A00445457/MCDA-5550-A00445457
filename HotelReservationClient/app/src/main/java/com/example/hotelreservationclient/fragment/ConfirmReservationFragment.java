package com.example.hotelreservationclient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.adapter.GuestListConfirmAdapter;
import com.example.hotelreservationclient.model.ConfirmResponse;
import com.example.hotelreservationclient.model.GuestsRequest;
import com.example.hotelreservationclient.model.HotelsResponse;
import com.example.hotelreservationclient.viewmodel.GuestViewModel;

import org.jetbrains.annotations.NotNull;

public class ConfirmReservationFragment extends Fragment {


    private View view;
    private GuestListConfirmAdapter guestListConfirmAdapter;
    private GuestViewModel guestViewModel;
    private LiveData<ConfirmResponse> getConfirmResponseLiveData;
    private TextView confirmReservationTextView;
    private String confirmNumber="";

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guestViewModel = ViewModelProviders.of(this).get(GuestViewModel.class);
        guestViewModel.init();

        GuestsRequest guestsRequest = (GuestsRequest) getArguments().getSerializable("guestsRequest");

        guestViewModel.sendConfirmRequest(guestsRequest);
        getConfirmResponseLiveData = guestViewModel.getConfirmResponseLiveData();
        getConfirmResponseLiveData.observe(this, new Observer<ConfirmResponse>() {
            @Override
            public void onChanged(ConfirmResponse confirmResponse) {
                if (confirmResponse != null&&confirmReservationTextView!=null) {
//                    hotelSearchResultAdapter.setResults(hotelsResponse.getHotels_list());
                    confirmReservationTextView.setText("Thank you for your reservation, your reservation number: "+confirmResponse.getConfirmation_number()+" is response from api.");

                }
            }
        });

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.reservation_confirm_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmReservationTextView = view.findViewById(R.id.confirm_reservation_text_view);
        confirmReservationTextView.setText("Thank you for your reservation, your reservation number is response from api.");

    }
}
