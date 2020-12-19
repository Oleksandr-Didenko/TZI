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
        Intent i = new Intent(this, EncriptMessageActivity.class);
        startActivity(i);
    }

    public void decriptMessage(View v){
        Intent i = new Intent(this, DecriptMessageActivity.class);
        startActivity(i);
    }

}