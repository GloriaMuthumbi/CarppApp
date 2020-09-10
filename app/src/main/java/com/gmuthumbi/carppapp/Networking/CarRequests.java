package com.gmuthumbi.carppapp.Networking;

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

public class CarRequests {
    API_Credentials api_credentials;


    public StringRequest carDetails(final VolleyCallbacks volleyCallbacks , final String token, final String apUrl,final String rate){

        api_credentials = new API_Credentials();

        return new StringRequest(
                Request.Method.GET,
                api_credentials.getAPIurl()+"car"+apUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject result = new JSONObject(response);
                            volleyCallbacks.onSuccess(result,rate);

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

    public StringRequest carSearch(final VolleyCallbacks volleyCallbacks , final String token, final String apUrl){

        api_credentials = new API_Credentials();

        return new StringRequest(
                Request.Method.GET,
                api_credentials.getAPIurl()+"car"+apUrl,
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

    public StringRequest myCarDetails(final VolleyCallbacks volleyCallbacks , final String token, final String apUrl){

        api_credentials = new API_Credentials();

        return new StringRequest(
                Request.Method.GET,
                api_credentials.getAPIurl()+"car"+apUrl,
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

}
