package com.example.csci_272;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csci_272.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.ObjectOutputStream;

public class Register extends AppCompatActivity {

    EditText editTextname, editTextemail, editTextusername, editTextpassword;
    Button buttonRegister;
    TextView textViewRegister;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextname = findViewById(R.id.name);
        editTextemail = findViewById(R.id.email);
        editTextusername = findViewById(R.id.username);
        editTextpassword = findViewById(R.id.password);
        buttonRegister = findViewById(R.id.register);
        textViewRegister = findViewById(R.id.register);
        progressBar = findViewById(R.id.progress);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String name, email, username, password;
                name = editTextname.getText().toString();
                email = editTextemail.getText().toString();
                username = editTextusername.getText().toString();
                password = editTextpassword.getText().toString();


                if (!name.equals("") && !email.equals("") && !username.equals("") && !password.equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            String[] field = new String[4];
                            field[0] = "name";
                            field[1] = "email";
                            field[2] = "username";
                            field[3] = "password";

                            String[] data = new String[4];
                            data[0] = "name";
                            data[1] = "email";
                            data[2] = "username";
                            data[3] = "password";
                            PutData putData = new PutData("http://10.109.30.18/Login-Database/signup.php", "POST", field, data);
                            if (putData.onComplete()){
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                            }
                            if (putData.startPut())
                            {
                                String result = putData.getResult();
                                if(result.equals("Sign Up Success"))
                                {
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}