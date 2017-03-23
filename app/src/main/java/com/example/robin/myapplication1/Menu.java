package com.example.robin.myapplication1;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Menu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setDropbox();
    }

    void setDropbox() {//Set adapter and listener
        Integer[] numberDropbox = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numberDropbox);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int j = 1; j <= 3; j++) {
            switch (j) {
                case 1:
                    for (int i = 1; i <= 27; i++) { // Small Pizza
                        String ViewIncrement = ("spA" + i);
                        int resID = getResources().getIdentifier(ViewIncrement, "id", getPackageName());
                        Spinner spinner = (Spinner) findViewById(resID);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new SpinnerActivity(this,ViewIncrement));
                    }
                    break;
                case 2:
                    for (int i = 1; i <= 27; i++) {// Medium Pizza
                        String ViewIncrement = ("spB" + i);
                        int resID = getResources().getIdentifier(ViewIncrement, "id", getPackageName());
                        Spinner spinner = (Spinner) findViewById(resID);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new SpinnerActivity(this,ViewIncrement));
                    }
                    break;
                case 3:
                    for (int i = 1; i <= 27; i++) { // Large Pizza
                        String ViewIncrement = ("spC" + i);
                        int resID = getResources().getIdentifier(ViewIncrement, "id", getPackageName());
                        Spinner spinner = (Spinner) findViewById(resID);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new SpinnerActivity(this,ViewIncrement));
                    }
                    break;
            }
        }
    }













}

