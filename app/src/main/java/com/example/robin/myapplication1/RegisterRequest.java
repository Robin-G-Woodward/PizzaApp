package com.example.robin.myapplication1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robin on 12/21/2016.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUESR_URL = "https://myfirstdomain.000webhostapp.com/Register.php"; //Domain of my website where Register.php is found
    private Map<String,String> params;

    public RegisterRequest(String name, String username,String password, Response.Listener<String> listener){ // This is the constructor method, so when an object of
                                                                                                                // RegisteRequeast is made the following method is ran.
        super(Method.POST, REGISTER_REQUESR_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name); //Strings need to be added due to them being used in Register.php file.
        params.put("username",username);
        params.put("password",password);
        /* Fills the hash map (just a data structure with strings of the data found in the edit text views)
        */
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


