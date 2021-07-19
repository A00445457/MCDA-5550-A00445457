package com.example.hotelreservationclient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.adapter.HotelSearchResultAdapter;
import com.example.hotelreservationclient.model.HotelsResponse;
import com.example.hotelreservationclient.viewmodel.HotelViewModel;

public class HotelListFragment extends Fragment {

    // claim viewmodel and adapter
    private HotelViewModel hotelViewModel;
    private HotelSearchResultAdapter hotelSearchResultAdapter;

    View view;
    TextView headingTextView;
    ProgressBar progressBar;

    //executed before onCreateView
    // assign variables
    // can't do anything involves in the View hierarchy
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get instance of HotelSearchResultAdapter
        hotelSearchResultAdapter = new HotelSearchResultAdapter();

        hotelViewModel = ViewModelProviders.of(this).get(HotelViewModel.class);
        hotelViewModel.init();
        hotelViewModel.getHotelsResponseLiveData().observe(this, new Observer<HotelsResponse>() {
            @Override
            public void onChanged(HotelsResponse hotelsResponse) {
                if (hotelsResponse != null) {
                    hotelSearchResultAdapter.setResults(hotelsResponse.getHotels_list());
                }
            }
        });
    }

    // assign View variables and do graphical initialisations.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // get view of hotel_list_fragment
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText("Welcome user, displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);


        // set recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(hotelSearchResultAdapter);

        // execute search
        performSearch();
        return view;
    }


    // use viewmodel to execute search hotel
    // viewmodel will call repository to search hotels
    // repository will call hotelserachservice to execute retrofit call
    public void performSearch() {
        hotelViewModel.searchHotels();
    }
}
