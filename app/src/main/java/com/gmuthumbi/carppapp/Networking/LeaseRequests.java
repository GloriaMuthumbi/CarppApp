package com.gmuthumbi.carppapp.Networking;

import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gmuthumbi.carppapp.Modals.API_Credentials;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LeaseRequests {

    API_Credentials api_credentials;


    public StringRequest leaseDetails(final VolleyCallbacks volleyCallbacks , final String token, final String apUrl){

        api_credentials = new API_Credentials();

        return new StringRequest(
                Request.Method.GET,
                api_credentials.getAPIurl()+"lease"+apUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject result = new JSONObject(response);
                            volleyCallbacks.onSuccess(result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley3", "Error: "
                                + error.getMessage());
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };

    }
    public StringRequest leaseSearchDetails(final VolleyCallbacks volleyCallbacks , final String token, final String apUrl,final String Name, final String plate, final Uri carImg,final String carId,final String userId,final String mileage, final String rating, final String description){

        api_credentials = new API_Credentials();

        return new StringRequest(
                Request.Method.GET,
                api_credentials.getAPIurl()+"lease"+apUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject result = new JSONObject(response);
                            volleyCallbacks.onSuccess(result,Name,plate,carImg,carId,userId,mileage,rating,description);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley3", "Error: "
                                + error.getMessage());
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer "+token);
                return params;
            }
        };

    }
}
