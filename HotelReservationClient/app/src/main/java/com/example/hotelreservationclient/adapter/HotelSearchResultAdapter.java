package com.example.hotelreservationclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreservationclient.R;
import com.example.hotelreservationclient.model.HotelModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HotelSearchResultAdapter extends RecyclerView.Adapter<HotelSearchResultAdapter.HotelSearchResultHolder>  {

    // hotel search result that from viewmodel
    private List<HotelModel> hotelSearchResults = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public HotelSearchResultAdapter.HotelSearchResultHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View hotelListItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_list_layout, parent, false);

        return new HotelSearchResultHolder(hotelListItemView);
    }

    /**
     * using viewholder bind textview with search result
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull HotelSearchResultAdapter.HotelSearchResultHolder holder, int position) {
        HotelModel hotelModel = hotelSearchResults.get(position);
        holder.hotelName.setText(hotelModel.getHotel_name());
        holder.hotelPrice.setText(hotelModel.getPrice());
        holder.hotelAvailability.setText(hotelModel.getAvailability()?"Available":"Unavailable");

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


    class HotelSearchResultHolder extends RecyclerView.ViewHolder {
        TextView hotelName, hotelPrice, hotelAvailability;


        public HotelSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            hotelName = itemView.findViewById(R.id.hotel_name_text_view);
            hotelPrice = itemView.findViewById(R.id.price_text_view);
            hotelAvailability = itemView.findViewById(R.id.availability_text_view);

        }
    }
}
