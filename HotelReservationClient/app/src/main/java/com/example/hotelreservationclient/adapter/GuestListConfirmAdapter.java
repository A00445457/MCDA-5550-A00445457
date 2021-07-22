package com.example.hotelreservationclient.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.databinding.GuestListLayoutBinding;
import com.example.hotelreservationclient.model.GuestModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuestListConfirmAdapter extends RecyclerView.Adapter<GuestListConfirmAdapter.GuestListConfirmHolder> {

    private List<GuestModel> guestModelList = new ArrayList<>();

    public GuestListConfirmAdapter(int guestNumber){
        for (int i = 0; i < guestNumber; i++) {
            guestModelList.add(new GuestModel(i+1));
        }
    }

    @NonNull
    @NotNull
    @Override
    public GuestListConfirmHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GuestListLayoutBinding guestListLayoutBinding = DataBindingUtil.inflate(inflater,
                R.layout.guest_list_layout, parent, false);

        return new GuestListConfirmHolder(guestListLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GuestListConfirmHolder holder, int position) {
        GuestModel guestModel = guestModelList.get(position);
        holder.guestListLayoutBinding.setGuest(guestModel);
    }

    @Override
    public int getItemCount() {
        return guestModelList.size();
    }

//    public void initTextView(int number) {
//        this.guestModelList = new ArrayList<>();
//        for (int i = 0; i < number; i++) {
//            guestModelList.add(new GuestModel());
//        }
//    }
    public List<GuestModel> getGuestModelList(){
        return guestModelList;
    }

    class GuestListConfirmHolder extends RecyclerView.ViewHolder {
        private GuestListLayoutBinding guestListLayoutBinding;

        // use dataBinding/viewBinding instead of findById
        public GuestListConfirmHolder(@NonNull GuestListLayoutBinding guestListLayoutBinding) {
            super(guestListLayoutBinding.getRoot());
            this.guestListLayoutBinding = guestListLayoutBinding;


        }
    }
}
