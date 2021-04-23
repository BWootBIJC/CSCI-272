package com.example.csci_272;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

        EditText editTextUsername, editTextPassword;
        Button buttonLogin;
        Button buttonSignUp;
        ProgressBar progressBar;
        int clickCount = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            //Connecting UI elements to Java file
            editTextUsername = findViewById(R.id.username);
            editTextPassword = findViewById(R.id.password);
            buttonLogin = findViewById(R.id.login);
            buttonSignUp = findViewById(R.id.signUpLink);
            progressBar = findViewById(R.id.loading);

            buttonSignUp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Register.class);
                    startActivity(intent);
                    finish();
                }
            });

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                //Method that holds all functionality when the user clicks the log in button
                public void onClick(View v) {
                    final String username, password;
                    username = editTextUsername.getText().toString();
                    password = editTextPassword.getText().toString();
                    clickCount =+ 1;
                    final String numberOfTimesClicked = "You only have " + String.valueOf(clickCount) + " login attempts left before you are sent to a waiting room." ;
                    //Making sure fields are not empty, then proceeding with checks.
                    if(!username.equals("") && !password.equals("")) {
                        progressBar.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Collecting data and posting to database
                                String[] field = new String[2];
                                field[0] = "username";
                                field[1] = "password";
                                String[] data = new String[2];
                                data[0] = username;
                                data[1] = password;
                                PutData putData = new PutData("http://10.109.30.16/LoginRegister/login.php", "POST", field, data);
                                while (clickCount <= 3) {
                                if (putData.startPut()) {
                                    //Returning Login success, or login failed message
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        if (result.equals("Login Success")) {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity((intent));
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                                    Toast.makeText(getApplicationContext(), numberOfTimesClicked, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

}




