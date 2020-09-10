package com.gmuthumbi.carppapp.Fragments;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.gmuthumbi.carppapp.Adapters.CarListAdapter;
import com.gmuthumbi.carppapp.Adapters.myCarAdapter;
import com.gmuthumbi.carppapp.Modals.API_Credentials;
import com.gmuthumbi.carppapp.Modals.Car;
import com.gmuthumbi.carppapp.Modals.myCar;
import com.gmuthumbi.carppapp.Networking.CarRequests;
import com.gmuthumbi.carppapp.R;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.gmuthumbi.carppapp.Activities.loginActivity";
    private RecyclerView recyclerView;
    private myCarAdapter mcarListAdapter;
    private List<myCar> carList;
    private View view;
    public account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account.
     */
    // TODO: Rename and change types and number of parameters
    public static account newInstance(String param1, String param2) {
        account fragment = new account();
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

        view = inflater.inflate(R.layout.fragment_account, container, false);
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        carList = new ArrayList<>();
        final String token = mPreferences.getString("JWT","");
        String name = mPreferences.getString("userName","");

        TextView username = view.findViewById(R.id.userName);

        username.setText(name);
        JWT jwt = new JWT(token);
        Claim img = jwt.getClaim("userImg");
        Claim em = jwt.getClaim("email");
        Claim loc = jwt.getClaim("location");

        API_Credentials api_credentials = new API_Credentials();


        //Log.d("JWTRES",claim.asString());
        final String userImg = img.asString();
        final String email = em.asString();
        final String location = loc.asString();

        Uri uri = Uri.parse(api_credentials.getAPIngrok()+"userProfile/"+userImg);

        CircleImageView avatar = view.findViewById(R.id.userProf);
        TextView mail = view.findViewById(R.id.mail);
        TextView gps = view.findViewById(R.id.gps);
        mail.setText(email);
        gps.setText(location);
        Picasso.get().load(uri).noFade().into(avatar);


        VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
            @Override
            public void onSuccess(JSONObject jsonObject) throws JSONException {
                API_Credentials api_credentials = new API_Credentials();
                JSONArray carsArr2 = new JSONArray(jsonObject.getString("cars"));

                for (int i=0; i<carsArr2.length();i++) {
                    String name = carsArr2.getJSONObject(i).get("carName").toString();
                    String plate = carsArr2.getJSONObject(i).get("noPlate").toString();

                    Uri uri = Uri.parse(carsArr2.getJSONObject(i).getJSONObject("request").get("url").toString());

                    String[] segments = uri.getPath().split("/");
                    String imgStr = api_credentials.getAPIngrok() + "carImg/" + segments[segments.length - 1];

                    Uri imguri = Uri.parse(imgStr);
                    myCar car = new myCar(imguri, name, plate);
                    carList.add(car);
                }
                initcarRecyclerview();
            }

            @Override
            public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {


            }

            @Override
            public void onSuccess(JSONObject jsonObject, String name, String plate, Uri carImg,String carId,String userId,String mileage, String rating, String description) throws JSONException {


            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());

        CarRequests carRequests = new CarRequests();

        StringRequest stringRequest = carRequests.myCarDetails(volleyCallbacks,token,"/myCars");

        queue.add(stringRequest);

        return view;

    }

    private void initcarRecyclerview(){

        recyclerView = view.findViewById(R.id.userCarsList);
        mcarListAdapter = new myCarAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mcarListAdapter);
        Log.d("volley3",Integer.toString(mcarListAdapter.getItemCount()));

    }
}
