package com.gmuthumbi.carppapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
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
        if (password.length()>=8 && !TextUtils.isEmpty(password.getText().toString())){
            PasswordValidator = true;
        }else {
            password.setError("password has to be 8 characters or more");
        }

        if (EmailValidator && PasswordValidator){
            Toast.makeText(loginActivity.this,"I have been clicked",Toast.LENGTH_SHORT).show();
            //API request
            VolleyCallbacks volleyCallbacks = new VolleyCallbacks() {
                @Override
                public void onSuccess(JSONObject jsonObject) throws JSONException {
                    Log.d("APItest",jsonObject.toString());

                }
            };

            UserRequests userRequests = new UserRequests();
            JsonObjectRequest jsonObjectRequest =
                    userRequests.login(volleyCallbacks,email.getText().toString(),password.getText().toString());

            queue.add(jsonObjectRequest);
        }

    }
}
