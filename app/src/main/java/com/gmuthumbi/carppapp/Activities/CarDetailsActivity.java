package com.gmuthumbi.carppapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmuthumbi.carppapp.Modals.API_Credentials;
import com.gmuthumbi.carppapp.Networking.UserRequests;
import com.gmuthumbi.carppapp.R;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.core.content.ContextCompat;

public class CarDetailsActivity extends Activity {
    TextView description,username,carName,price;
    ImageView carImage,userImage;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.gmuthumbi.carppapp.Activities.loginActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardetails);

        RequestQueue queue = Volley.newRequestQueue(this);
        carImage = findViewById(R.id.car_image);
        userImage = findViewById(R.id.user_img);
        description = findViewById(R.id.car_description);
        carName = findViewById(R.id.car_name);
        price = findViewById(R.id.car_price);
        Intent intent = getIntent();
        RatingBar rating = findViewById(R.id.rating_bar);

        rating.setRating(Float.valueOf(intent.getStringExtra("rating")));
        mPreferences = this.getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        final String token = mPreferences.getString("JWT","");

        description.setText(intent.getStringExtra("description"));
        carName.setText(intent.getStringExtra("carName"));
        price.setText(intent.getStringExtra("price"));
        Uri uri = Uri.parse(intent.getStringExtra("image"));
        Picasso.get().load(uri).into(carImage);

        UserRequests userRequests = new UserRequests();

        StringRequest stringRequest = userRequests.userDetails(volleyCallbacks,token,intent.getStringExtra("userId"));

        queue.add(stringRequest);
    }

    VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
        @Override
        public void onSuccess(JSONObject jsonObject) throws JSONException {
            API_Credentials api_credentials = new API_Credentials();
            Uri uri = Uri.parse(jsonObject.getJSONObject("request").get("url").toString());

            String[] segments = uri.getPath().split("/");
            String imgStr = api_credentials.getAPIngrok()+"userProfile/"+segments[segments.length-1];
            Uri img = Uri.parse(imgStr);
            Picasso.get().load(img).into(userImage);
        }

        @Override
        public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {

        }


        @Override
        public void onSuccess(JSONObject jsonObject, String name, String plate, Uri carImg, String carId, String userId, String mileage, String rating, String description) throws JSONException {

        }
    };



}
