package com.Travel.pk;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.Travel.pk.R;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HotelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new FetchHotelDataTask().execute();
    }

    private class FetchHotelDataTask extends AsyncTask<Void, Void, List<Hotel>> {
        @Override
        protected List<Hotel> doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://raw.githubusercontent.com/griffin-k/travel/main/hotel.json")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Hotel>>() {}.getType();
                    return gson.fromJson(json, type);
                } else {
                    Log.e("MainActivity2", "Failed to fetch JSON: " + response.code());
                    return null;
                }
            } catch (IOException e) {
                Log.e("MainActivity2", "Error fetching JSON", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Hotel> hotelList) {
            if (hotelList != null) {
                adapter = new HotelAdapter(hotelList, MainActivity2.this);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(MainActivity2.this, "Failed to load hotel data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
