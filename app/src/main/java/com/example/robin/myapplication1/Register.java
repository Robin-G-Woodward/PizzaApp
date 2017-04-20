package com.example.robin.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final TextView tvBack = (TextView) findViewById(R.id.tvBack);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(Register.this, Login.class);
                Register.this.startActivity(LoginIntent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // When the register button is clicked everything in the edit text views gets stored as string variables
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() { // Making an object to listen for a response
                    @Override                                                                  // is a response is heard then the onresponse
                                                                                               // method is ran
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);//This can fail is response is not in the form of a JASON
                                                                               // string hence wrap with try/catch clause
                            String success = jsonResponse.getString("success");// This will either return true due to the response success
                            String taken = jsonResponse.getString("taken");

                            if(taken == "taken"){
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Username taken please try again.")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();  }

                            if (success == "success"){ //If response is true then make an intent to return to login activity
                                Intent intent = new Intent(Register.this,Login.class);
                                Register.this.startActivity(intent); //Intent is passed to startActivity method and taken to login activity
                            }
                            else{ //If the response is anything else but success i.e false then error message sent
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) { // This is the catch is response is not in the jason format
                            e.printStackTrace(); // Just prints StackTrace
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name,username,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this); //Queue to give a middle man before requeasts sent to website/server
                queue.add(registerRequest); //Adds the requeast to the request queue

            }
        });


    }
}
