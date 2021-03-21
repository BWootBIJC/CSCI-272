package com.example.csci_272;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.csci_272.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}