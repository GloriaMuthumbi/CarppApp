package com.gmuthumbi.carppapp.utils;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallbacks {
    void onSuccess(JSONObject jsonObject) throws JSONException;
}
