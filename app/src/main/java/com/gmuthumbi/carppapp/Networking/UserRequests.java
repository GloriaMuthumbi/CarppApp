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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRequests {

API_Credentials api_credentials;

    public JsonObjectRequest login(final String email, final String password){

api_credentials = new API_Credentials();

        return new JsonObjectRequest(
                Request.Method.POST,
                api_credentials.getAPIurl(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Volley", "Error: "
                                + error.getMessage());
                    }
                }) {

            @Override
            protected Map getParams()
            {
                Map params = new HashMap();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };
    }

}
