package com.gmuthumbi.carppapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmuthumbi.carppapp.Modals.Car;
import com.gmuthumbi.carppapp.Modals.myCar;
import com.gmuthumbi.carppapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myCarAdapter extends RecyclerView.Adapter<myCarAdapter.myCarViewHolder> {
    private List<myCar> CarList;

    public myCarAdapter(List<myCar>carlist){
        CarList = carlist;
    }

    @NonNull
    @Override
    public myCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycar_item_card,parent,false);


        return new myCarAdapter.myCarViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull myCarViewHolder holder, int position) {
        myCar car = CarList.get(position);
        holder.carName.setText(car.getCarName());
        holder.carPlate.setText(car.getCarPlate());
        Picasso.get().load(car.getCarImg()).into(holder.carImg);
    }

    @Override
    public int getItemCount() {
        return CarList.size();
    }

    public class myCarViewHolder extends RecyclerView.ViewHolder {
        private ImageView carImg;
        private TextView carName,carPlate;

        public myCarViewHolder(@NonNull View itemView) {
            super(itemView);
            carImg = itemView.findViewById(R.id.Img);
            carName = itemView.findViewById(R.id.name);
            carPlate = itemView.findViewById(R.id.plate);
        }
    }
}
