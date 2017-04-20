package com.example.robin.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Menu_Design extends Activity {

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
    private String name,username;
    public ArrayList<String> getCompletedOrder() {
        return completedOrder;
    }
    public void setCompletedOrder(ArrayList<String> completedOrder) {
        this.completedOrder = completedOrder;
    }

    EditText etUsername,etName;
    TextView tvPay;
    ImageButton ibMenu,ibDesign;
    MenuOrder menuOrder;
    ArrayList<String> designOrder;
    ArrayList<String> completedOrder;

    public Boolean getDesignFinalized() {
        return designFinalized;
    }

    public void setDesignFinalized(Boolean designFinalized) {
        this.designFinalized = designFinalized;
    }

    public Boolean getMenuFinalized() {
        return menuFinalized;
    }

    public void setMenuFinalized(Boolean menuFinalized) {
        this.menuFinalized = menuFinalized;
    }

    Boolean designFinalized;
    Boolean menuFinalized;

    private final static String TAG = "MenuDesignActivity";
    final int REQ_STARTA = 101;
    final int REQ_STARTB = 102;

    /////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(int i = 0;i<10;i++) {   //Used to identify when each activity lifecycle method is started
            Log.i(TAG, "On Create .....");
        }

        setContentView(R.layout.activity_menu__design);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etName = (EditText) findViewById(R.id.etName);
        tvPay = (TextView) findViewById(R.id.tvpay);
        ibMenu = (ImageButton) findViewById(R.id.ibmenu);
        ibDesign = (ImageButton) findViewById(R.id.ibdesign);

        Intent intent = getIntent();

        designFinalized = intent.getBooleanExtra("designFinalized",false);
        menuFinalized = intent.getBooleanExtra("menuFinalized",false);

        setName(intent.getStringExtra("name"));
        setUsername(intent.getStringExtra("username"));
        etName.setText(getName());
        etUsername.setText(getUsername());

        completedOrder = new ArrayList<String>();


        View.OnClickListener onClickListenerMenu = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menuIntent = new Intent(Menu_Design.this,Menu.class);
                menuIntent.putExtra("name", getName());
                menuIntent.putExtra("username", getUsername());
                menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(menuIntent,REQ_STARTA);
            }};

        View.OnClickListener onClickListenerDesign = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent designIntent = new Intent(Menu_Design.this,Design.class);
                designIntent.putExtra("name", getName());
                designIntent.putExtra("username", getUsername());
                designIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(designIntent,REQ_STARTB);
            }};

        View.OnClickListener onClickListenerPay = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent completedOrderIntent = new Intent(Menu_Design.this,CompletedOrder.class);
                completedOrderIntent.putStringArrayListExtra("completedOrder",getCompletedOrder());
                Menu_Design.this.startActivity(completedOrderIntent);
            }};

        if (menuFinalized == false ){
            ibMenu.setOnClickListener(onClickListenerMenu);
        }else{
            ibMenu.setVisibility(View.GONE);
        }
        if (designFinalized == false ){
            ibDesign.setOnClickListener(onClickListenerDesign);
        }else{
            ibDesign.setVisibility(View.GONE);
        }

        tvPay.setOnClickListener(onClickListenerPay);

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
            System.out.println("The value of menuFinalized is: "+ getMenuFinalized().toString());
            System.out.println("The value of designFinalized is: "+ getDesignFinalized().toString());
        }

        View.OnClickListener onClickListenerMenu = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent menuIntent = new Intent(Menu_Design.this,Menu.class);
                menuIntent.putExtra("name", getName());
                menuIntent.putExtra("username", getUsername());
                menuIntent.putExtra("designFinalized",getDesignFinalized());
                menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(menuIntent,REQ_STARTA);
            }};

        View.OnClickListener onClickListenerDesign = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent designIntent = new Intent(Menu_Design.this,Design.class);
                designIntent.putExtra("name", getName());
                designIntent.putExtra("username", getUsername());
                designIntent.putExtra("menuFinalized", getMenuFinalized());
                designIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(designIntent,REQ_STARTB);
            }};

        ibMenu.setOnClickListener(onClickListenerMenu);
        ibDesign.setOnClickListener(onClickListenerDesign);

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
    @Override
    public void onActivityResult(int req, int res, Intent data) {
        for(int i = 0;i<10;i++) {
            Log.i(TAG, "On Result .....");
        }

        if(req == REQ_STARTA) {
            if(res == RESULT_OK) {
                ibMenu.setVisibility(View.GONE);
                ibMenu.setClickable(false);
                setMenuFinalized(true);
                menuOrder = (MenuOrder) data.getSerializableExtra("menuOrder");
                List<String[]> itemsFromMenu = menuOrder.getItems();
                for (String[] item : itemsFromMenu) {
                    for (String subitem : item) {
                        completedOrder.add(subitem);
                    }
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(completedOrder.toString());
                }
            }
        }

        if(req == REQ_STARTB) {
            if(res == RESULT_OK){
                ibDesign.setVisibility(View.GONE);
                ibDesign.setClickable(false);
                setDesignFinalized(true);
                designOrder = data.getStringArrayListExtra("designOrder");
                for (String item : designOrder) {
                    completedOrder.add(item);
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(completedOrder.toString());
            }
        }
    }
}
