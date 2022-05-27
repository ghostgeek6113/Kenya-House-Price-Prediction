package com.example.housepriceprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView register, newPassword;
    private EditText email, password;
    private Button signIn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        newPassword = (TextView) findViewById(R.id.forgotpassword);
        newPassword.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.LoginButton);
        signIn.setOnClickListener(this);

        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                startActivity(new Intent(this, Registration.class));
                break;

            case R.id.LoginButton:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (mail.isEmpty()) {
            email.setError("Set a valid email");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("the password cannot be empty");
            password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            password.setError("Min password length is 8");
            password.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}