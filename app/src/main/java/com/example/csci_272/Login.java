package com.example.csci_272;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
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
                public void onClick(View v) {
                    final String username, password;
                    username = editTextUsername.getText().toString();
                    password = editTextPassword.getText().toString();

                    if(!username.equals("") && !password.equals("")) {
                        progressBar.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[2];
                                field[0] = "username";
                                field[1] = "password";
                                String[] data = new String[2];
                                data[0] = username;
                                data[1] = password;
                                PutData putData = new PutData("http://10.109.30.16/LoginRegister/login.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        if(result.equals("Login Success")){
                                            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity((intent));
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
                                        }
                                    }
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




