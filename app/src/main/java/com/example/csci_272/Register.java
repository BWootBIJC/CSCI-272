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
    Button buttonRegister, buttonsignUpLink;
    TextView textViewRegister;
    ProgressBar progressBar;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Connecting UI elements to Java file
        editTextname = findViewById(R.id.name);
        editTextemail = findViewById(R.id.email);
        editTextusername = findViewById(R.id.username);
        editTextpassword = findViewById(R.id.password);
        buttonRegister = findViewById(R.id.register);
        buttonsignUpLink = findViewById(R.id.signUpLink);
        textViewRegister = findViewById(R.id.register);
        progressBar = findViewById(R.id.progress);

        buttonsignUpLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final String name, email, username, password;
                name = editTextname.getText().toString();
                email = (editTextemail.getText().toString());
                username = (editTextusername.getText().toString());
                password = (editTextpassword.getText().toString());
                //checking to make sure the fields aren't empty before proceeding to collect user input
                if (!name.equals("") && !email.equals("") && !username.equals("") && !password.equals(""))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            //Collecting user input
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "email";
                            field[2] = "username";
                            field[3] = "password";
                            //Posting user input to database
                            String[] data = new String[4];
                            data[0] = name;
                            data[1] = email;
                            data[2] = username;
                            data[3] = password;
                            PutData putData = new PutData("http://10.109.30.16/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut())
                            {
                                //Sending back corresponding signup success or signup failed message
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    progressBar.setVisibility(View.GONE);
                                    if(result.equals("Sign Up Success"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
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