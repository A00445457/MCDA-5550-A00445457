package com.example.hotelreservationclient.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotelreservationclient.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    View view;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText guestCountEditText, nameEditText;
    Button confirmSearchButton, searchButton, retrieveButton, clearButton;
    DatePicker checkInDatePickerView, checkOutDatePickerView;
    String checkInDate, checkOutDate, numberOfGuests, guestName;

    // Declaration of shared preferences keys
    SharedPreferences sharedPreferences;
    public static final String myPreference = "myPref";
    public static final String name = "nameKey";
    public static final String guestsCount = "guestsCount";


    /**
     * inflate screen when run this app
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }


    /**
     * after view created, take all inputs
     *
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

        retrieveButton = view.findViewById(R.id.retrieve_button);
        clearButton = view.findViewById(R.id.clear_button);
        nameEditText = view.findViewById(R.id.name_edit_text);


        //set text
        titleTextView.setText(R.string.welcome_text);

        //set button
        confirmSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInDate = getDateFromCalendar(checkInDatePickerView);
                checkOutDate = getDateFromCalendar(checkOutDatePickerView);
                numberOfGuests = guestCountEditText.getText().toString();
                guestName = nameEditText.getText().toString();
                if (!checkGuestInfo()) {
                    return;
                }

                searchTextConfirmationTextView.setText("The checkin is " + checkInDate +
                        " and checkout is " + checkOutDate +
                        ". Number of guests is " + numberOfGuests);


                // Saving into shared preferences
                sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(name, guestName);
                editor.putString(guestsCount, numberOfGuests);
                editor.commit();

            }
        });

        //On click Listener for search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInDate = getDateFromCalendar(checkInDatePickerView);
                checkOutDate = getDateFromCalendar(checkOutDatePickerView);
                numberOfGuests = guestCountEditText.getText().toString();
                guestName = nameEditText.getText().toString();
                if (!checkGuestInfo()) {
                    return;
                }


                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);

                HotelListFragment hotelListFragment = new HotelListFragment();
                hotelListFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        // Retrieve Button Click Listener

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);

                if (sharedPreferences.contains(name)) {
                    nameEditText.setText(sharedPreferences.getString(name, ""));
                }
                if (sharedPreferences.contains(guestsCount)) {
                    guestCountEditText.setText(sharedPreferences.getString(guestsCount, ""));

                }
            }
        });

        //Clear Button Click Listener
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestCountEditText.setText("");
                nameEditText.setText("");
            }
        });

    }

    /**
     * check if numberof guests is valid
     *
     * @return
     */
    private boolean checkGuestInfo() {
        boolean result = true;
        //check if numberOfGUests is empty
        if (numberOfGuests.isEmpty()) {
            Context context = view.getContext();
            CharSequence text = "Please input number of check in guest.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            result = false;
        }
        //check if numberOfGuests is valid
        if (!android.text.TextUtils.isDigitsOnly(numberOfGuests)) {
            Context context = view.getContext();
            CharSequence text = "Please input valid number of check in guest.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            result = false;
        } else if (Integer.parseInt(numberOfGuests) < 1) {
            Context context = view.getContext();
            CharSequence text = "Please input valid number of check in guest.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            result = false;
        }
        return result;
    }


    /**
     * get date from datePickerView
     *
     * @return
     */
    private String getDateFromCalendar(DatePicker datePickerView) {
        int day = datePickerView.getDayOfMonth();
        int month = datePickerView.getMonth();
        int year = datePickerView.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;

    }
}
