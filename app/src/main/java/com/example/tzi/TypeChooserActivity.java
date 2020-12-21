package com.example.tzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TypeChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_chooser);
    }

    public void encriptMessage(View v){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");


        Intent i = new Intent(this, EncriptMessageActivity.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        startActivity(i);
    }

    public void decriptMessage(View v){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        Intent i = new Intent(this, DecriptMessageActivity.class);
        i.putExtra("username", username);
        i.putExtra("password", password);
        startActivity(i);
    }

}