package com.example.housepriceprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class History extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SummaryActivity";
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String userId;
    Handler handler = new Handler();
    ArrayList<Map> uHouse;
    ArrayList<String> uCity;
    ArrayList<String> uArea;
    ArrayList<Double> uBedroom;
    ArrayList<Double> uBathroom;
    ArrayList<Double> uSize;
    ArrayList<Double> uParking;
    ArrayList<String> uPrice;
    ArrayList<Date> uDate;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter pAdapter;
    private ImageView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = (RecyclerView) findViewById(R.id.summaryview);

        uHouse = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        banner = findViewById(R.id.imageBack);
        banner.setOnClickListener(this);
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        uCity = new ArrayList<>();
        uArea = new ArrayList<>();
        uBedroom = new ArrayList<>();
        uBathroom = new ArrayList<>();
        uSize = new ArrayList<>();
        uParking = new ArrayList<>();
        uPrice = new ArrayList<>();
        uDate = new ArrayList<java.util.Date>();
        DocumentReference documentReference = fStore.collection("UserData").document(userId);
        assert uHouse != null;
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc = task.getResult();
                assert doc != null;
                uHouse = (ArrayList<Map>) doc.get("houses");
                // assert uHouse != null;
                for(Map i:uHouse){
                    uCity.add((String) i.get("City"));
                    uArea.add((String) (i.get("Area")));
                    uBedroom.add(Double.valueOf(String.valueOf(i.get("Bedrooms"))));
                    uBathroom.add(Double.valueOf(String.valueOf(i.get("Bathrooms"))));
                    uSize.add(Double.valueOf(String.valueOf(i.get("Size"))));
                    uParking.add(Double.valueOf(String.valueOf(i.get("Parking"))));
                    uPrice.add((String) i.get("Price"));
                    uDate.add(((Timestamp) i.get("Date")).toDate());
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(History.this);
                    pAdapter = new SecondaryAdapter(uCity,uArea,uBedroom,uBathroom,uSize,uParking,uPrice,uDate);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(pAdapter);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageBack) {
            startActivity(new Intent(this, Home.class));
        }
    }
}