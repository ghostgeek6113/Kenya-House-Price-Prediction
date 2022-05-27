package com.example.housepriceprediction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private EditText firstname, lastname, localisation, email, number, password;
    private ImageView banner;
    private Button registerUser;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        firstname = findViewById(R.id.FirstName);
        lastname = findViewById(R.id.LastName);
        localisation = findViewById(R.id.Location);
        email = findViewById(R.id.emailAddress);
        number = findViewById(R.id.PhoneNumber);
        password = findViewById(R.id.Password);

        banner = findViewById(R.id.imageBack);
        banner.setOnClickListener(this);

        registerUser = findViewById(R.id.RegistrationButton);
        registerUser.setOnClickListener(this);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
            case R.id.login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.RegistrationButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String mail = email.getText().toString().trim();
        String firstName = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();
        String loc = localisation.getText().toString().trim();
        String phone = number.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (mail.isEmpty()) {
            email.setError("Email required");
            email.requestFocus();
            return;
        }
        if (firstName.isEmpty()) {
            firstname.setError("first name required");
            firstname.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            lastname.setError("last name required");
            lastname.requestFocus();
            return;
        }
        if (loc.isEmpty()) {
            localisation.setError("location required");
            localisation.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            number.setError("Phone number required");
            number.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("the password cannot be empty");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }
        if (password.length() < 6) {
            password.setError("Min password length is 8");
            password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser rUser = mAuth.getCurrentUser();
                        assert rUser != null;
                        String userId = rUser.getUid();
                        DocumentReference documentReference = fStore.collection("Users").document(userId);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userId", userId);
                        hashMap.put("email", mail);
                        hashMap.put("first name", firstName);
                        hashMap.put("shop", lastName);
                        hashMap.put("location", loc);
                        hashMap.put("phone", phone);
                        documentReference.set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("TAG", "Registered successfully" + userId);
                            }
                        });
                        startActivity(new Intent(Registration.this, MainActivity.class));
                    } else {
                        Toast.makeText(Registration.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}