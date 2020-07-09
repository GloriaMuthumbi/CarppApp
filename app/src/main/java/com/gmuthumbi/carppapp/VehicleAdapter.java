package com.gmuthumbi.carppapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private ArrayList<Vehicle> vehicleData;
    private Context myContext;

    VehicleAdapter(ArrayList<Vehicle> vehicleData, Context context) {
        this.myContext = context;
        this.vehicleData = vehicleData;
    }

    @NonNull
    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.car_list_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.ViewHolder holder, int position) {
        Vehicle currentReceipt = vehicleData.get(position);

        holder.bindTo(currentReceipt);
    }

    @Override
    public int getItemCount() {
        return vehicleData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*
         create constructor viewHolder used in the onCreateViewHolder() method
        @param itemView rootView of the vehicle_0list_item layout
         */
        // Declare the private member variable that the viewHolder will hold
        private ImageView myVehicleImage;
        private TextView myVehicleTitle;
        private TextView myVehicleDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myVehicleImage = itemView.findViewById(R.id.vehicle_image);
            myVehicleTitle = itemView.findViewById(R.id.vehicle_title);
            myVehicleDescription = itemView.findViewById(R.id.vehicle_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int popularPosition = getAdapterPosition();
                    Vehicle currentPopular = vehicleData.get(popularPosition);
                    if (popularPosition == 0) {
                        Intent carIntent = new Intent(myContext, CarActivity.class);
                        carIntent.putExtra("cTitle", currentPopular.getVehicleTitle());
                        carIntent.putExtra("cImage", currentPopular.getVehicleImage());
                        carIntent.putExtra("cDescription", currentPopular.getVehicleDescription());
                        myContext.startActivity(carIntent);
                    } else {
                        Toast.makeText(myContext, "create an activity for the car", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }

        public void bindTo(Vehicle currentReceipt) {
            Glide.with(myContext).load(currentReceipt.getVehicleImage()).into(myVehicleImage);
            myVehicleTitle.setText(currentReceipt.getVehicleTitle());
            myVehicleDescription.setText(currentReceipt.getVehicleDescription());
        }
    }

}
