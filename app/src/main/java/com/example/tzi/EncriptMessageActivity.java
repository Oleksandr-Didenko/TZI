package com.example.tzi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tzi.email.EmailHandler;
import com.example.tzi.encryption.GammaMethod;

public class EncriptMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encript_message);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void encriptMessage(View v){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        EditText editEmail = findViewById(R.id.textEmailAddressReceiverEncode);
        String reciever = editEmail.getText().toString();

        EditText editSubject = findViewById(R.id.textSubjectEncode);
        String subject = editSubject.getText().toString();

        EditText editText = findViewById(R.id.textMessageEncode);
        String messageText = editText.getText().toString();

        EditText editGamma = findViewById(R.id.textGammaEncode);
        String gamma = editGamma.getText().toString();

        EmailHandler email = new EmailHandler(username, password);

        if (reciever.isEmpty()){
            Toast.makeText(this, "Please enter Receivers Email!", Toast.LENGTH_LONG).show();
        }
        else if (gamma.isEmpty()){
            Toast.makeText(this, "Please enter Gamma!", Toast.LENGTH_LONG).show();
        }

        else if(messageText.isEmpty()) {
            Toast.makeText(this, "Please enter Message!", Toast.LENGTH_LONG).show();
        }
        else {
            email.setReceiver(reciever);

            String decodedMessage = GammaMethod.Encript(messageText, gamma);
            email.sendMessage(subject, decodedMessage);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        }
    }
}