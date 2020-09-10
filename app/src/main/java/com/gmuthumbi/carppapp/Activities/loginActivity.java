package com.gmuthumbi.carppapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmuthumbi.carppapp.Networking.UserRequests;
import com.gmuthumbi.carppapp.R;
import com.gmuthumbi.carppapp.utils.VolleyCallbacks;

import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

        EditText email,password;
        private SharedPreferences mPreferences;
        private String sharedPrefFile = "com.gmuthumbi.carppapp.Activities.loginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

    }

    public void login(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        Boolean EmailValidator = false;
        Boolean PasswordValidator = false;
        if(!TextUtils.isEmpty(email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            EmailValidator=true;
        }else{
            email.setError("please enter a valid email");
        }
        if (!TextUtils.isEmpty(password.getText().toString())){
            PasswordValidator = true;
        }else {
            password.setError("invalid password");
        }

        if (EmailValidator && PasswordValidator){
            Toast.makeText(loginActivity.this,"I have been clicked",Toast.LENGTH_SHORT).show();
            //API request
            VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
                @Override
                public void onSuccess(JSONObject jsonObject) throws JSONException {
                    Log.d("APItest",jsonObject.get("userName").toString());
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                    preferencesEditor.putString("JWT",jsonObject.get("token").toString());
                    preferencesEditor.putString("userName",jsonObject.get("userName").toString());
                    preferencesEditor.apply();

                   // String tk = mPreferences.getString("JWT","");


                    Intent explore = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(explore);
                }

                @Override
                public void onSuccess(JSONObject jsonObject, String rate) throws JSONException {

                }

                @Override
                public void onSuccess(JSONObject jsonObject, String name, String plate, Uri carImg, String carId,String userId,String mileage, String rating, String description) throws JSONException {

                }


            };

            UserRequests userRequests = new UserRequests();
            JsonObjectRequest jsonObjectRequest =
                    userRequests.login(volleyCallbacks,email.getText().toString(),password.getText().toString());

            queue.add(jsonObjectRequest);

        }

    }
}
