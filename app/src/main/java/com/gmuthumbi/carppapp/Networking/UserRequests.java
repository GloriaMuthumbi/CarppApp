package com.gmuthumbi.carppapp.Networking;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.gmuthumbi.carppapp.Modals.API_Credentials;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRequests {

API_Credentials api_credentials;

    public JsonObjectRequest login(final VolleyCallbacks volleyCallbacks , final String email, final String password){

api_credentials = new API_Credentials();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);


        return new JsonObjectRequest(
                Request.Method.POST,
                api_credentials.getAPIurl()+"users/login",
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            volleyCallbacks.onSuccess(response);
                            Log.d("volley2",response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley1", "Error: "
                                + error.getMessage());
                    }
                }) {


        };

    }

}
