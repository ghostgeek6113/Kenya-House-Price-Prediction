package com.example.housepriceprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Predict extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_CITY = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_AREA = "com.example.application.example.EXTRA_NUMBER";
    public static final String EXTRA_BEDROOMS = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_BATHROOMS = "com.example.application.example.EXTRA_NUMBER";
    public static final String EXTRA_SIZE = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_PARKING = "com.example.application.example.EXTRA_NUMBER";
    public static final String EXTRA_PREDICTION = "com.example.application.example.EXTRA_NUMBER";
    private AutoCompleteTextView city;
    private AutoCompleteTextView area;
    private TextView bedrooms;
    private TextView bathrooms;
    private TextView size;
    private TextView parking;
    Bundle bundle;
    private ArrayList<Map> arrayList = new ArrayList<>();
    private Map<String, Object> hashMap = new HashMap<>();
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private SharedPreferences mPreferences;
    private ImageView banner;
    String [] cities = {"Nairobi", "Mombasa"};
    String [] areas = {"Malindi", "Nyali", "Karen", "Kileleshwa", "Kilimani", " Kitisuru","Lavington","Muthaiga","Nyari","Riverside","Runda"};
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);
        city = findViewById(R.id.predictCity);
        city.setAdapter(new ArrayAdapter<>(Predict.this, android.R.layout.simple_list_item_1,cities));
        area = findViewById(R.id.predictArea);
        area.setAdapter(new ArrayAdapter<>(Predict.this, android.R.layout.simple_list_item_1, areas));
        bedrooms = findViewById(R.id.predictBedrooms);
        bathrooms = findViewById(R.id.predictBathrooms);
        size = findViewById(R.id.predictSize);
        parking = findViewById(R.id.predictParking);
        Button buttonPredict = findViewById(R.id.predictButton);
        buttonPredict.setOnClickListener(this);
        bundle = new Bundle();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        banner = findViewById(R.id.imageBack);
        banner.setOnClickListener(this);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.predictButton) {
            new Connection().execute();
            //saveDataFirestore();
        }
        else if (view.getId() == R.id.imageBack){
            startActivity(new Intent(this, Home.class));
        }
    }




    /*private void Prediction() {
        String pCity = city.getText().toString();
        String pArea = area.getText().toString();
        float pBedrooms = Float.parseFloat(bedrooms.getText().toString());
        float pBathrooms = Float.parseFloat(bathrooms.getText().toString());
        float pSize = Float.parseFloat(size.getText().toString());
        float pParking = Float.parseFloat(parking.getText().toString());
        try {
            URL url = new URL("https://midtermprojectapi.herokuapp.com/predict");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoOutput(true);
            @SuppressLint("DefaultLocale") String post_data = String.format("[{\"city\": \"%s\",\"area\": \"%s\", \"bedrooms\":%f, \"bathrooms\": %f, \"size\": %f, \"parking\": %f}]", pCity,pArea,pBedrooms,pBathrooms,pSize,pParking);
            byte[] input = post_data.getBytes(StandardCharsets.UTF_8);
            System.out.println(post_data);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(input, 0, input.length);
            os.close();
            System.out.println(post_data);
            System.out.println("result after Reading JSON Response");

            InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder response=new StringBuilder();
            String line;
            while ((line=bufferedReader.readLine())!=null){
                response.append(line);
            }
            bufferedReader.close();
            System.out.println("Response : "+response.toString());
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error making POST request");
        }
    }*/

    @SuppressLint("StaticFieldLeak")
    public class Connection extends AsyncTask<Void, Void, Void> {

        public String predictionResponse;

        @Override
        protected Void doInBackground(Void... voids) {
            String pCity = city.getText().toString();
            String pArea = area.getText().toString();
            float pBedrooms = Float.parseFloat(bedrooms.getText().toString());
            float pBathrooms = Float.parseFloat(bathrooms.getText().toString());
            float pSize = Float.parseFloat(size.getText().toString());
            float pParking = Float.parseFloat(parking.getText().toString());
            try {
                URL url = new URL("https://midtermprojectapi.herokuapp.com/predict");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);
                @SuppressLint("DefaultLocale") String post_data = String.format("[{\"city\": \"%s\",\"area\": \"%s\", \"bedrooms\":%f, \"bathrooms\": %f, \"size\": %f, \"parking\": %f}]", pCity,pArea,pBedrooms,pBathrooms,pSize,pParking);
                byte[] input = post_data.getBytes(StandardCharsets.UTF_8);
                System.out.println(post_data);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(input, 0, input.length);
                os.close();
                System.out.println(post_data);
                System.out.println("result after Reading JSON Response");

                InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                StringBuilder response=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    response.append(line);
                }
                bufferedReader.close();
                System.out.println("Response : "+response.toString());
                predictionResponse = response.toString();
                System.out.println("Response : "+predictionResponse);
                predictionResponse = predictionResponse.replace("prediction","");
                predictionResponse = predictionResponse.replace(":","");
                predictionResponse = predictionResponse.replace("[","");
                predictionResponse = predictionResponse.replace("]","");
                predictionResponse = predictionResponse.replaceAll("\"","");
                predictionResponse = predictionResponse.replaceAll("\\}","");
                predictionResponse = predictionResponse.replaceAll("\\{","");
                Locale currentLocale = new Locale("EN","KE");
                NumberFormat formatter = NumberFormat.getCurrencyInstance(currentLocale);
                predictionResponse = formatter.format(Double.parseDouble(predictionResponse));
                System.out.println(predictionResponse);
                System.out.println(formatter.format(Double.parseDouble(predictionResponse)));
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error making POST request");
            }
            Intent intent = new Intent(getApplicationContext(), PredictionResults.class);
            intent.putExtra(EXTRA_PREDICTION, predictionResponse);
            startActivity(intent);
            //loadDataGson();
            saveDataFirestore();
            //saveDataGson();
            return null;
        }
        public Map<String, Object> saveEntry() {
            String sCtiy = city.getText().toString().trim();
            String sArea = area.getText().toString().trim();
            float sBedrooms= Float.parseFloat(bedrooms.getText().toString().trim());
            float sBathroms = Float.parseFloat(bathrooms.getText().toString().trim());
            float sSize = Float.parseFloat(size.getText().toString().trim());
            float sParking = Float.parseFloat(parking.getText().toString().trim());
            int c = hashMap.size();
            System.out.println("prediction results" + predictionResponse);
            System.out.println(EXTRA_PREDICTION);
            for(int i = 0; i<= hashMap.size();i++){
                hashMap.put("City", sCtiy);
                hashMap.put("Area", sArea);
                hashMap.put("Bedrooms", sBedrooms);
                hashMap.put("Bathrooms", sBathroms);
                hashMap.put("Size",sSize);
                hashMap.put("Parking",sParking);
                hashMap.put("Price", predictionResponse);
                hashMap.put("Date", new Timestamp(new Date()).toDate());
                c++;
            }
            return hashMap;
        }
        private void saveDataFirestore(){
            FirebaseUser rUser = mAuth.getCurrentUser();
            assert rUser != null;
            String userId = rUser.getUid();
            Map <String, Object> hashMap = saveEntry();
            arrayList.add(hashMap);
            House house = new House(arrayList);
            System.out.println("HashMap: "+hashMap);
            System.out.println("ArrayList: "+arrayList);
            System.out.println("Items: "+house);
            fStore.collection("UserData").document(userId).set(house).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("TAG","Registered successfully"+userId);
                }
            });
        }
        private void saveDataGson() {
            FirebaseUser rUser = mAuth.getCurrentUser();
            String userId = rUser.getUid();
            SharedPreferences sharedPreferences = getSharedPreferences(userId, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(arrayList);
            editor.putString("task list", json);
            editor.apply();
            System.out.println(json);
        }
        private void loadDataGson() {
            FirebaseUser rUser = mAuth.getCurrentUser();
            String userId = rUser.getUid();
            SharedPreferences sharedPreferences = getSharedPreferences(userId, MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("task list", null);
            Type type = new TypeToken<ArrayList<Map>>() {}.getType();
            arrayList = gson.fromJson(json, type);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            System.out.println(arrayList);
        }

    }

}