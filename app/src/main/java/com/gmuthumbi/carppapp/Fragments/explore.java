package com.gmuthumbi.carppapp.Fragments;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmuthumbi.carppapp.Adapters.CarListAdapter;
import com.gmuthumbi.carppapp.Modals.API_Credentials;
import com.gmuthumbi.carppapp.Modals.Car;
import com.gmuthumbi.carppapp.Networking.CarRequests;
import com.gmuthumbi.carppapp.Networking.LeaseRequests;
import com.gmuthumbi.carppapp.Networking.UserRequests;
import com.gmuthumbi.carppapp.R;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link explore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class explore extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.car,R.drawable.car2,R.drawable.car3};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.gmuthumbi.carppapp.Activities.loginActivity";
    private RecyclerView recyclerView;
    private CarListAdapter mcarListAdapter;
    private List<Car> carList;
    private View view;
    public explore() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment explore.
     */
    // TODO: Rename and change types and number of parameters
    public static explore newInstance(String param1, String param2) {
        explore fragment = new explore();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        carouselView = view.findViewById(R.id.Carousel);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
                
            }
        };


        carList = new ArrayList<>();


        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());

        mPreferences = getActivity().getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        final String token = mPreferences.getString("JWT","");

//getting the car name
        final VolleyCallbacks volleyCallbacks1 = new VolleyCallbacks() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

            }

            @Override
            public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {
                API_Credentials api_credentials = new API_Credentials();
                JSONArray carsArr2 = new JSONArray(jsonObject.getString("cars"));
                String name = carsArr2.getJSONObject(0).get("carName").toString();
                String plate = carsArr2.getJSONObject(0).get("noPlate").toString();

                Uri uri = Uri.parse(carsArr2.getJSONObject(0).getJSONObject("request").get("url").toString());

                String[] segments = uri.getPath().split("/");
                String imgStr = api_credentials.getAPIngrok()+"carImg/"+segments[segments.length-1];

                Uri imguri = Uri.parse(imgStr);
                Car car = new Car(imguri,name,rate+"/hr",plate);
                carList.add(car);
                initcarRecyclerview();
            }

        };


        VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {
                //Log.d("volley3",jsonObject.getString("cars").toString());
                JSONArray carsArr = new JSONArray(jsonObject.getString("lease"));

                RequestQueue queue = Volley.newRequestQueue(getContext());
                for (int i=0;i<carsArr.length();i++){

                    String id = carsArr.getJSONObject(i).get("carId").toString();
                    String rate = carsArr.getJSONObject(i).get("rate").toString();
                    CarRequests carRequests = new CarRequests();
                    Log.d("volley4",id);

                    StringRequest stringRequest = carRequests.carDetails(volleyCallbacks1,token,"/specific/"+id,rate);

                    queue.add(stringRequest);


                }
            }

            @Override
            public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {

            }

        };

        LeaseRequests leaseRequests = new LeaseRequests();
        StringRequest stringRequest = leaseRequests.leaseDetails(volleyCallbacks,token,"/");
        queue.add(stringRequest);

        return view;
    }


    private void initcarRecyclerview(){
        recyclerView = view.findViewById(R.id.availableCars);
        mcarListAdapter = new CarListAdapter(carList);
        //LinearLayoutManager mlayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mcarListAdapter);
        Log.d("volley3",Integer.toString(mcarListAdapter.getItemCount()));
    }
}
