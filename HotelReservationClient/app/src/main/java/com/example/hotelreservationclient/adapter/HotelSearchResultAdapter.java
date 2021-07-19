package com.example.hotelreservationclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.clicklistener.HotelItemClickListener;
import com.example.hotelreservationclient.databinding.HotelListLayoutBinding;
import com.example.hotelreservationclient.model.HotelModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HotelSearchResultAdapter extends RecyclerView.Adapter<HotelSearchResultAdapter.HotelSearchResultHolder> {

    // hotel search result that from viewmodel
    private List<HotelModel> hotelSearchResults = new ArrayList<>();

    private HotelItemClickListener clickListener;

    @NonNull
    @NotNull
    @Override
    public HotelSearchResultAdapter.HotelSearchResultHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HotelListLayoutBinding hotelListLayoutBinding = DataBindingUtil.inflate(inflater,
                R.layout.hotel_list_layout, parent, false);

        return new HotelSearchResultHolder(hotelListLayoutBinding);

    }

    /**
     * using viewholder bind textview with search result
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull HotelSearchResultAdapter.HotelSearchResultHolder holder, int position) {
        HotelModel hotelModel = hotelSearchResults.get(position);
        holder.hotelListLayoutBinding.setHotel(hotelModel);

    }

    // get count of search result
    @Override
    public int getItemCount() {
        return hotelSearchResults.size();
    }

    public void setResults(List<HotelModel> results) {
        this.hotelSearchResults = results;
        notifyDataSetChanged();
    }

    public void setClickListener(HotelItemClickListener hotelItemClickListener) {
        this.clickListener = hotelItemClickListener;
    }


    class HotelSearchResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private HotelListLayoutBinding hotelListLayoutBinding;

        // use dataBinding/viewBinding instead of findById
        public HotelSearchResultHolder(@NonNull HotelListLayoutBinding hotelListLayoutBinding) {
            super(hotelListLayoutBinding.getRoot());
            this.hotelListLayoutBinding = hotelListLayoutBinding;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAbsoluteAdapterPosition());
            }
        }
    }
}
