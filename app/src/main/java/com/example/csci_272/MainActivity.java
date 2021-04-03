package com.example.csci_272;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public Button signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signUpLink = findViewById(R.id.signUpLink);
        signUpLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendToCreateAccount();
            }
        });
    }
    public void sendToCreateAccount(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    }
