package com.example.hotelreservationclient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.adapter.GuestListConfirmAdapter;

import java.util.Arrays;
import java.util.List;

public class HotelGuestListDetailsFragment extends Fragment {

    View view;
    GuestListConfirmAdapter guestListConfirmAdapter;



    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String numberOfGuests = getArguments().getString("number of guests");
        numberOfGuests = numberOfGuests.isEmpty()?"1":numberOfGuests;
        int number = Integer.parseInt(numberOfGuests);
        guestListConfirmAdapter = new GuestListConfirmAdapter(number);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        // set recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.guests_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(guestListConfirmAdapter);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");
        String hotelName = getArguments().getString("hotelName");
        String price = getArguments().getString("price");

        hotelRecapTextView.setText("You have selected " + hotelName
                + ".\nThe cost per night will be $" + price
                + ".\nYou can checkin on " + checkInDate
                + " and checkout on " + checkOutDate + ".");

        /*
        split line************************************************************************************
         */
//        rvPrueba = view.findViewById(R.id.guests_list_recyclerView);
//        btnCalcular = view.findViewById(R.id.etCantidad);
//        rvPrueba.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        adapter = new PruebaAdapter(lista);
//        rvPrueba.setAdapter(adapter);


    }


}
