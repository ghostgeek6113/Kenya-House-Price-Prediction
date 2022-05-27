package com.example.housepriceprediction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PredictionResults extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_results);
        Intent intent = getIntent();
        String text = intent.getStringExtra(Predict.EXTRA_PREDICTION);
        TextView textView1 = (TextView) findViewById(R.id.message);
        textView1.setText(text);
        View home = findViewById(R.id.menuHome);
        home.setOnClickListener(this);

        View history = findViewById(R.id.menuHistory);
        history.setOnClickListener(this);

        View account = findViewById(R.id.menuAccount);
        account.setOnClickListener(this);

        ImageView banner = findViewById(R.id.imageBack);
        banner.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageBack) {
            startActivity(new Intent(this, Home.class));
        } else if (view.getId() == R.id.menuHome) {
            startActivity(new Intent(this, Home.class));
        } else if (view.getId() == R.id.menuHistory) {
            startActivity(new Intent(this, History.class));
        } else if (view.getId() == R.id.menuAccount) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}