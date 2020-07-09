package com.gmuthumbi.carppapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class CarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        TextView carTitle = findViewById(R.id.car_title);
        carTitle.setText(getIntent().getStringExtra("cTitle"));
        TextView carDescription = findViewById(R.id.car_description);
        carDescription.setText(getIntent().getStringExtra("cDescription"));
        ImageView carImage = findViewById(R.id.car_image);
        Glide.with(this).load(getIntent().getIntExtra("cImage", 0)).into(carImage);
    }
}