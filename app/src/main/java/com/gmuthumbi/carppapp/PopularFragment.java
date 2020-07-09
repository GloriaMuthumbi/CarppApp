package com.gmuthumbi.carppapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    private RecyclerView popularRecyclerView;
    private ArrayList<Vehicle> popularVehicleData;
    private VehicleAdapter popularAdapter;

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_popular, container, false);

        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);

        popularRecyclerView = rootView.findViewById(R.id.recycler_popular);
        //4. Set the layout for the recycler view.
        popularRecyclerView.setLayoutManager(new

                LinearLayoutManager(getContext()));
        // initialize the array list that will contain data.
        popularVehicleData = new ArrayList<>();
        // Initialize the vehicle adapter
        popularAdapter = new VehicleAdapter(popularVehicleData, getContext());
        //set the adapter
        popularRecyclerView.setAdapter(popularAdapter);
        // Get data

        InitializeData();

        // Implement swiping and moving of card items
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(popularVehicleData, from, to);
                popularAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                popularVehicleData.remove(viewHolder.getAdapterPosition());
                popularAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });
        helper.attachToRecyclerView(popularRecyclerView);

        return rootView;
    }

    private void InitializeData() {
        // get the data you created in the research files: strings.xml
        String[] popularTitles = getResources().getStringArray(R.array.vehicle_title);
        String[] popularDescription = getResources().getStringArray(R.array.vehicle_description);
        TypedArray popularImages = getResources().obtainTypedArray(R.array.vehicle_image);
        //Clear existing data to avoid duplication
        popularVehicleData.clear();
        // Create an array list
        for (int i = 0; i < popularTitles.length; i++) {
            popularVehicleData.add(new Vehicle(popularImages.getResourceId(i, 0), popularTitles[i], popularDescription[i]));
        }
        // clean up data in typed array
        popularImages.recycle();
        //Notify the adapter of the change in the data set
        popularAdapter.notifyDataSetChanged();
    }


}
