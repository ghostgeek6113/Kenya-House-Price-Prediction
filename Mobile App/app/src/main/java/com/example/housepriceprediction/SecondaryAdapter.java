package com.example.housepriceprediction;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class SecondaryAdapter extends RecyclerView.Adapter<SecondaryAdapter.ViewHolder> {
    ArrayList<String> uCity;
    ArrayList<String> uArea;
    ArrayList<Double> uBedroom;
    ArrayList<Double> uBathroom;
    ArrayList<Double> uSize;
    ArrayList<Double> uParking;
    ArrayList<String> uPrice;
    ArrayList<Date> uDate;

    public SecondaryAdapter(ArrayList<String> uCity, ArrayList<String> uArea, ArrayList<Double> uBedroom, ArrayList<Double> uBathroom, ArrayList<Double> uSize, ArrayList<Double> uParking, ArrayList<String> uPrice, ArrayList<Date> uDate) {
        this.uCity = uCity;
        this.uArea = uArea;
        this.uBedroom = uBedroom;
        this.uBathroom = uBathroom;
        this.uSize = uSize;
        this.uParking = uParking;
        this.uPrice = uPrice;
        this.uDate = uDate;
    }

    @NonNull
    @Override
    public SecondaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_template, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SecondaryAdapter.ViewHolder holder, int position) {
        holder.city.setText("City: " + uCity.get(position));
        holder.area.setText("Area: " + uArea.get(position));
        holder.bedroom.setText("Bedrooms: " + String.valueOf(uBedroom.get(position)));
        holder.bathroom.setText("Bathrooms: " + String.valueOf(uBathroom.get(position)));
        holder.size.setText("Size in acre: " + String.valueOf(uSize.get(position)));
        holder.parking.setText("Parking: " + String.valueOf(uParking.get(position)));
        holder.price.setText("Price: " + uPrice.get(position));
        holder.date.setText("Date: " + String.valueOf(uDate.get(position)));
    }

    @Override
    public int getItemCount() {
        return uCity.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView city;
        public TextView area;
        public TextView bedroom;
        public TextView bathroom;
        public TextView size;
        public TextView parking;
        public TextView price;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.rCity);
            area = itemView.findViewById(R.id.rArea);
            bedroom = itemView.findViewById(R.id.rBedrooms);
            bathroom = itemView.findViewById(R.id.rBathrooms);
            size = itemView.findViewById(R.id.rSize);
            parking = itemView.findViewById(R.id.rParking);
            price = itemView.findViewById(R.id.rPrice);
            date = itemView.findViewById(R.id.rDate);
        }
    }
}
