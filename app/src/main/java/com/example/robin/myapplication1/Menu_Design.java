package com.example.robin.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Menu_Design extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__design);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final ImageButton ibMenu = (ImageButton) findViewById(R.id.ibmenu);
        final ImageButton ibDesign = (ImageButton) findViewById(R.id.ibdesign);

        ibDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent designIntent = new Intent(Menu_Design.this,Design.class);
                Menu_Design.this.startActivity(designIntent);
            }
        });

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(Menu_Design.this,Menu.class);
                Menu_Design.this.startActivity(menuIntent);
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        etUsername.setText(username);
        etName.setText(name);



    }
}
