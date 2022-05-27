package com.example.housepriceprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private TextView predictor, historyckg;
    private FirebaseAuth mAuth;
    private View home;
    private View history;
    private View account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        predictor = findViewById(R.id.HomePredictButton);
        predictor.setOnClickListener(this);

        historyckg = findViewById(R.id.HomeHistoryButton);
        historyckg.setOnClickListener(this);

        home = findViewById(R.id.menuHome);
        home.setOnClickListener(this);

        history = findViewById(R.id.menuHistory);
        history.setOnClickListener(this);

        account = findViewById(R.id.menuAccount);
        account.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.HomePredictButton:
                startActivity(new Intent(this, Predict.class));
                break;
            case R.id.HomeHistoryButton:
            case R.id.menuHistory:
                startActivity(new Intent(this, History.class));
                break;
            case R.id.menuHome:
                startActivity(new Intent(this, Home.class));
                break;
            case R.id.menuAccount:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuHome) {
            startActivity(new Intent(this, Home.class));
        } else if (item.getItemId() == R.id.menuHistory) {
            startActivity(new Intent(this, History.class));
        }
        return super.onOptionsItemSelected(item);
    }
}