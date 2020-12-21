package com.example.tzi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tzi.email.EmailHandler;
import com.example.tzi.encryption.GammaMethod;

public class DecriptMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decript_message);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void decriptMessage(View v){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        EditText editEmail = findViewById(R.id.textEmailAddressReceiverDecode);
        String reciever = editEmail.getText().toString();

        EditText editSubject = findViewById(R.id.textSubjectDecode);
        String subject = editSubject.getText().toString();

        EditText editGamma = findViewById(R.id.textGammaDecode);
        String gamma = editGamma.getText().toString();

        EmailHandler email = new EmailHandler(username,password);

        if (reciever.isEmpty()){
            Toast.makeText(this, "Please enter Receivers Email!", Toast.LENGTH_LONG).show();
        }
        else if(subject.isEmpty()) {
            Toast.makeText(this, "Please enter Subject!", Toast.LENGTH_LONG).show();
        }
        else if (gamma.isEmpty()){
            Toast.makeText(this, "Please enter Gamma!", Toast.LENGTH_LONG).show();
        }
        else {
            email.setReceiver(reciever);


            String encodedMessage = email.receiveMessage(subject, reciever);

            String message = GammaMethod.Decript(encodedMessage, gamma);

            TextView messageTittle = findViewById(R.id.textMessageTittleDecode);
            messageTittle.setVisibility(TextView.VISIBLE);

            TextView messageView = findViewById(R.id.TextMessageDecode);
            messageView.setText(message);
        }
    }
}