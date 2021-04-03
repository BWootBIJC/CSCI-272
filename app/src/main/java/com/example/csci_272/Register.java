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

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String name, email, username, password;
                name = editTextname.getText().toString();
                email = (editTextemail.getText().toString());
                username = (editTextusername.getText().toString());
                password = (editTextpassword.getText().toString());
                System.out.println(name);
                System.out.println(email);
                System.out.println(username);
                System.out.println(password);
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
                            field[0] = "Name";
                            field[1] = "Email";
                            field[2] = "Username";
                            field[3] = "Password";

                            String[] data = new String[4];
                            data[0] = "Name";
                            data[1] = "Email";
                            data[2] = "Username";
                            data[3] = "Password";
                            PutData putData = new PutData("http://192.168.1.90/Login-Database/signup.php", "POST", field, data);
                            if (putData.startPut())
                            {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    progressBar.setVisibility(View.GONE);
                                    if(result.equals("Sign Up Success"))
                                    {
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}