package com.example.robin.myapplication1;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    private static final String LOGIN_REQUESR_URL = "https://myfirstdomain.000webhostapp.com/Login.php"; //Domain of my website where Register.php is found
    private Map<String,String> params;

    public LoginRequest(String username,String password, Response.Listener<String> listener){ // This is the constructor method, so when an object of RegisteRequeast is made the following method is ran.
        super(Request.Method.POST, LOGIN_REQUESR_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


