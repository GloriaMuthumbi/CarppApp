package com.gmuthumbi.carppapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmuthumbi.carppapp.Modals.Car;
import com.gmuthumbi.carppapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    private List<Car> CarList;

    public CarListAdapter(List<Car>carlist){
    CarList = carlist;
    }

    @NonNull
    @Override
    public CarListAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item_card,parent,false);


        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarListAdapter.CarViewHolder holder, int position) {
            Car car = CarList.get(position);
            holder.carName.setText(car.getCarName());
            holder.carPlate.setText(car.getCarPlate());
            holder.carPrice.setText(car.getCarPrice());
            Picasso.get().load(car.getCarImg()).into(holder.carImg);

    }

    public void setCarList(List<Car> carList) {
        CarList = carList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return CarList.size();
    }

    public class CarViewHolder extends  RecyclerView.ViewHolder{
        private ImageView carImg;
        private TextView carName,carPrice,carPlate;


        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carImg = itemView.findViewById(R.id.carImg);
            carName = itemView.findViewById(R.id.carName);
            carPrice = itemView.findViewById(R.id.carPrice);
            carPlate = itemView.findViewById(R.id.carPlate);
        }



    }
}
