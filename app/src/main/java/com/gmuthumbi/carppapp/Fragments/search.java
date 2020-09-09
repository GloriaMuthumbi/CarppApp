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
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmuthumbi.carppapp.Adapters.CarListAdapter;
import com.gmuthumbi.carppapp.Modals.API_Credentials;
import com.gmuthumbi.carppapp.Modals.Car;
import com.gmuthumbi.carppapp.Networking.CarRequests;
import com.gmuthumbi.carppapp.Networking.LeaseRequests;
import com.gmuthumbi.carppapp.R;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.gmuthumbi.carppapp.Activities.loginActivity";
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CarListAdapter mcarListAdapter;
    private List<Car> carList;
    private View view;
    public search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search.
     */
    // TODO: Rename and change types and number of parameters
    public static search newInstance(String param1, String param2) {
        search fragment = new search();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        carList = new ArrayList<>();

        TextView textView = view.findViewById(R.id.userTest);
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        String name = mPreferences.getString("userName","");
        String greeting = getString(R.string.greeting)+" "+name.replaceAll(" .*", "");
        final String token = mPreferences.getString("JWT","");

        textView.setText(greeting);

        final VolleyCallbacks volleyCallbacks1 = new VolleyCallbacks() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {

            }

            @Override
            public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {

            }

            @Override
            public void onSuccess(JSONObject jsonObject, String name, String plate, Uri carImg) throws JSONException {
                JSONArray carsArr = new JSONArray(jsonObject.getString("lease"));
                String rate = carsArr.getJSONObject(0).get("rate").toString();

                //Log.d("volley4",plate);

                Car car = new Car(carImg,name,rate+"/hr",plate);
                carList.add(car);
                initcarRecyclerview();
            }
        };

        final VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {
                API_Credentials api_credentials = new API_Credentials();
                JSONArray carsArr2 = new JSONArray(jsonObject.getString("cars"));
                RequestQueue queue = Volley.newRequestQueue(getContext());

                for (int i=0;i<carsArr2.length();i++){
                    String name = carsArr2.getJSONObject(i).get("carName").toString();
                    String id = carsArr2.getJSONObject(i).get("id").toString();
                    String plate = carsArr2.getJSONObject(i).get("noPlate").toString();

                    Uri uri = Uri.parse(carsArr2.getJSONObject(i).getJSONObject("request").get("url").toString());

                    String[] segments = uri.getPath().split("/");
                    String imgStr = api_credentials.getAPIngrok()+"carImg/"+segments[segments.length-1];

                    Uri imguri = Uri.parse(imgStr);

                    LeaseRequests leaseRequests = new LeaseRequests();

                    StringRequest stringRequest = leaseRequests.leaseSearchDetails(volleyCallbacks1,token,"/"+id,name,plate,imguri);
                    queue.add(stringRequest);

                }
            }

            @Override
            public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {

            }

            @Override
            public void onSuccess(JSONObject jsonObject, String name, String price, Uri carImg) throws JSONException {

            }
        };
        searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                ImageView imageView = view.findViewById(R.id.carAdventure);
                imageView.setVisibility(View.GONE);
                recyclerView = view.findViewById(R.id.searchResults);
                recyclerView.setVisibility(View.VISIBLE);
                CarRequests carRequests = new CarRequests();
                StringRequest stringRequest = carRequests.carSearch(volleyCallbacks,token,"/search/"+s);
                queue.add(stringRequest);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return view;
    }

    private void initcarRecyclerview(){
        recyclerView = view.findViewById(R.id.searchResults);
        mcarListAdapter = new CarListAdapter(carList);
        //LinearLayoutManager mlayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mcarListAdapter);
        Log.d("volley3",Integer.toString(mcarListAdapter.getItemCount()));
    }
}
