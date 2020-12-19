package com.example.tzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tzi.R;
import com.example.tzi.TypeChooserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void confirmLogin(View v){

        EditText t1 = findViewById(R.id.TextEmailAddress);
        EditText t2 = findViewById(R.id.TextPassword);
        if( t1.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter E-mail!", Toast.LENGTH_LONG).show();
        }
        else if (t2.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter Password!", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(this, TypeChooserActivity.class);
            startActivity(i);
        }
    }
}