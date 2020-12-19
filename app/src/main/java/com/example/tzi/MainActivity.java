package com.example.tzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tzi.R;
import com.example.tzi.TypeChooserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void confirmLogin(View v){
        Intent i = new Intent(this, TypeChooserActivity.class);
        startActivity(i);
    }
}