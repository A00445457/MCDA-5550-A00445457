package com.example.hotelreservationclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.adapter.GuestListConfirmAdapter;
import com.example.hotelreservationclient.model.GuestModel;
import com.example.hotelreservationclient.model.GuestsRequest;
import com.example.hotelreservationclient.viewmodel.GuestViewModel;

import java.util.ArrayList;
import java.util.List;

public class HotelGuestListDetailsFragment extends Fragment {

    private View view;
    private Button confirmButton;
    private GuestViewModel guestViewModel;
    private GuestListConfirmAdapter guestListConfirmAdapter;


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String numberOfGuests = getArguments().getString("number of guests");
        numberOfGuests = numberOfGuests.isEmpty() ? "1" : numberOfGuests;
        int number = Integer.parseInt(numberOfGuests);
        guestListConfirmAdapter = new GuestListConfirmAdapter(number);
        guestViewModel = ViewModelProviders.of(this).get(GuestViewModel.class);
        guestViewModel.init();

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

        confirmButton = view.findViewById(R.id.confirm_reservation_button);
        RecyclerView recyclerView = view.findViewById(R.id.guests_list_recyclerView);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuestsRequest guestsRequest = new GuestsRequest();
                List<GuestModel> guestModels = new ArrayList<>();
                for (int i = 0; i < recyclerView.getChildCount(); i++) {

                    View view = recyclerView.getChildAt(i);
                    EditText firstNameEditText = (EditText) view.findViewById(R.id.first_name_edittext);
                    String firstName = firstNameEditText.getText().toString();
                    EditText lastNameEditText = (EditText) view.findViewById(R.id.last_name_edittext);
                    String lastName = lastNameEditText.getText().toString();
                    if (firstNameEditText.getText().toString().isEmpty() || lastNameEditText.getText().toString().isEmpty()) {
                        Context context = view.getContext();
                        CharSequence text = "Please input guest name on #" + (i + 1);
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;

                    }
                    RadioGroup genderRadio = (RadioGroup) view.findViewById(R.id.gender_radio_group);
                    int selectedId = genderRadio.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    RadioButton radioButton = (RadioButton) recyclerView.findViewById(selectedId);
                    String gender = (String) radioButton.getText();
                    GuestModel guestModel = new GuestModel(firstName, lastName, gender);
                    guestModels.add(guestModel);
                }
                guestsRequest.guests_list = guestModels;
                guestsRequest.checkin = checkInDate;
                guestsRequest.checkout = checkOutDate;
                guestsRequest.hotel_name = hotelName;

                Bundle bundle = new Bundle();
                bundle.putSerializable("guestsRequest", guestsRequest);

                // set Fragment class Arguments
                ConfirmReservationFragment confirmReservationFragment = new ConfirmReservationFragment();
                confirmReservationFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.remove(HotelGuestListDetailsFragment.this);
                fragmentTransaction.replace(R.id.main_layout, confirmReservationFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });


    }


}
