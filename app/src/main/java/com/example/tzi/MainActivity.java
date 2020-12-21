package com.example.tzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    public void confirmLogin(View v){

        EditText t1 = findViewById(R.id.TextEmailAddressSender);
        EditText t2 = findViewById(R.id.TextPassword);
        if( t1.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter E-mail!", Toast.LENGTH_LONG).show();
        }
        else if (t2.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter Password!", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(this, TypeChooserActivity.class);
            EditText editText = findViewById(R.id.TextEmailAddressSender);
            String username = editText.getText().toString();
            i.putExtra("username", username);

            editText = findViewById(R.id.TextPassword);
            String password = editText.getText().toString();
            i.putExtra("password", password);

            startActivity(i);
        }
    }
}