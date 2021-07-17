package com.example.hotelreservationclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HotelSearchFragment extends Fragment {

    View view;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText guestCountEditText;
    Button confirmSearchButton, searchButton;
    DatePicker checkInDatePickerView, checkOutDatePickerView;
    String checkInDate, checkOutDate, numberOfGuests;


    /**
     * inflate screen when run this app
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable  ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout,container,false);
        return view;
    }


    /**
     * after view created, take all inputs
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.title_text_view);
        guestCountEditText = view.findViewById(R.id.guests_count_edit_text);
        confirmSearchButton = view.findViewById(R.id.confirm_my_search_button);
        searchTextConfirmationTextView = view.findViewById(R.id.search_confirm_text_view);
        checkInDatePickerView = view.findViewById(R.id.checkin_date_picker_view);
        checkOutDatePickerView = view.findViewById(R.id.checkout_date_picker_view);
        searchButton = view.findViewById(R.id.search_button);


        //set text
        titleTextView.setText(R.string.welcome_text);

        //set button
        confirmSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInDate = getDateFromCalendar(checkInDatePickerView);
                checkOutDate = getDateFromCalendar(checkOutDatePickerView);
                numberOfGuests = guestCountEditText.getText().toString();
                searchTextConfirmationTextView.setText("The checkin is " + checkInDate +
                        " and checkout is " +  checkOutDate +
                        ". Number of guests is "+numberOfGuests);

            }
        });

        //On click Listener for search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInDate = getDateFromCalendar(checkInDatePickerView);
                checkOutDate = getDateFromCalendar(checkOutDatePickerView);
                numberOfGuests = guestCountEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);

                HotelsListFragment hotelListFragment = new HotelsListFragment();
                hotelListFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout,hotelListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }


    /**
     * get date from datePickerView
     * @return
     */
    private String getDateFromCalendar(DatePicker datePickerView){
        int day = datePickerView.getDayOfMonth();
        int month = datePickerView.getMonth();
        int year = datePickerView.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;

    }
}
