package com.example.robin.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.io.Serializable;


public class Menu extends Activity implements Serializable{

    Button ButtonCompleteMenu;
    Button ButtonBack;
    private final static String TAG = "MenuActivity";
    private static final long serialVersionUID = 1974743341607699233L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public void setStateOfOrder(boolean stateOfOrder) {
        this.stateOfOrder = stateOfOrder;
    }

    private boolean stateOfOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Used to identify when each activity lifecycle method is started
        for(int i = 0;i<10;i++) {
            Log.i(TAG, "On Create .....");
        }
        setContentView(R.layout.activity_menu);
        setDropbox();

        Intent intent = getIntent();
        setName(intent.getStringExtra("name"));
        setUsername(intent.getStringExtra("username"));
        setStateOfOrder(intent.getBooleanExtra("designFinalized",false));

        View.OnClickListener onClickListenerCompleteMenu = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                MenuOrder menuorder = exit();

                Intent menuDesignIntent = new Intent(Menu.this,Menu_Design.class);
                menuDesignIntent.putExtra("name", name);
                menuDesignIntent.putExtra("username", username);
                menuDesignIntent.putExtra("menuOrder", menuorder);

                setResult(RESULT_OK,menuDesignIntent);

                finish();

            }
        };
        View.OnClickListener onClickListenerBack = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent menuDesignIntent = new Intent(Menu.this,Menu_Design.class);
                menuDesignIntent.putExtra("name", name);
                menuDesignIntent.putExtra("username", username);
                menuDesignIntent.putExtra("designFinalized",stateOfOrder);

                startActivity(menuDesignIntent);

            }
        };


        ButtonCompleteMenu = (Button)findViewById(R.id.buttonCompleteMenu);
        ButtonCompleteMenu.setOnClickListener(onClickListenerCompleteMenu);

        ButtonBack = (Button)findViewById(R.id.buttonBack);
        ButtonBack.setOnClickListener(onClickListenerBack);
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Used to identify when each activity lifecycle method is started
        for(int i = 0;i<10;i++){
            Log.i(TAG, "On Destroy .....");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onPause() {
        super.onPause();
        //Used to identify when each activity lifecycle method is started
        for(int i = 0;i<10;i++) {
            Log.i(TAG, "On Pause .....");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onRestart() {
        super.onRestart();
        for(int i = 0;i<10;i++) {
            Log.i(TAG, "On Restart .....");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();
        //Used to identify when each activity lifecycle method is started
        for(int i = 0;i<10;i++) {
            Log.i(TAG, "On Resume .....");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onStart() {
        super.onStart();
        //Used to identify when each activity lifecycle method is started
        for(int i = 0;i<10;i++){
            Log.i(TAG, "On Start .....");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onStop() {
        super.onStop();
        //Used to identify when each activity lifecycle method is started
        for(int i = 0;i<10;i++) {
            Log.i(TAG, "On Stop .....");
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////



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
    public MenuOrder exit(){

        MenuOrder order = new MenuOrder();
        EditText etTotal = (EditText)findViewById(R.id.etTotal);
        String total =(etTotal.getText()).toString();
        order.addItem("Menu_Total:",total);

        for (int j = 1; j <= 3; j++) {
            switch (j) {
                case 1:
                    for (int i = 1; i <= 27; i++) { // Small Pizza

                        String ViewIncrement = ("spA" + i);
                        int resID = getResources().getIdentifier(ViewIncrement, "id", getPackageName());
                        Spinner spinner = (Spinner) findViewById(resID);
                        String valueOfSpinnerString = spinner.getSelectedItem().toString();
                        int valueOfSpinnerInt = Integer.parseInt(valueOfSpinnerString);
                        if (valueOfSpinnerInt != 0){
                            String item =(String)spinner.getTag();
                            order.addItem(valueOfSpinnerString,item);
                        }
                    }
                    break;
                case 2:
                    for (int i = 1; i <= 27; i++) {// Medium Pizza
                        String ViewIncrement = ("spB" + i);
                        int resID = getResources().getIdentifier(ViewIncrement, "id", getPackageName());
                        Spinner spinner = (Spinner) findViewById(resID);
                        String valueOfSpinnerString = spinner.getSelectedItem().toString();
                        int valueOfSpinnerInt = Integer.parseInt(valueOfSpinnerString);
                        if (valueOfSpinnerInt != 0){
                            String item =(String)spinner.getTag();
                            order.addItem(valueOfSpinnerString,item);
                        }
                    }
                    break;
                case 3:
                    for (int i = 1; i <= 27; i++) { // Large Pizza
                        String ViewIncrement = ("spC" + i);
                        int resID = getResources().getIdentifier(ViewIncrement, "id", getPackageName());
                        Spinner spinner = (Spinner) findViewById(resID);
                        String valueOfSpinnerString = spinner.getSelectedItem().toString();
                        int valueOfSpinnerInt = Integer.parseInt(valueOfSpinnerString);
                        if (valueOfSpinnerInt != 0){
                            String item =(String)spinner.getTag();
                            order.addItem(valueOfSpinnerString,item);
                        }
                    }
                    break;
            }
        }
        return order;
    }
}

