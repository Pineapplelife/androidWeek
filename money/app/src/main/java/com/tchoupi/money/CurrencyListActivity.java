package com.tchoupi.money;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyListActivity extends AppCompatActivity {

    List<Currency> currencies;

    CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_list_activity);

        currencies = new ArrayList<>();

        loadRatesFromApi();

         adapter = new CurrencyAdapter(currencies);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadRatesFromApi() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.exchangeratesapi.io/latest")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("CurrencyListActivity", "onFailure: ", e);
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = Objects.requireNonNull(response.body()).string();

                try {
                    JSONObject jsonObject = new JSONObject(body);
                    JSONObject rates = jsonObject.getJSONObject("rates");
                    double usd = rates.getDouble("USD");
                    double gbp = rates.getDouble("GBP");
                    double jpy = rates.getDouble("JPY");

                    currencies.add(new Currency(R.drawable.usa_flag, usd, 0.895, "$"));
                    currencies.add(new Currency(R.drawable.british_flag, gbp, 1.097, "£"));
                    currencies.add(new Currency(R.drawable.japan_flag, jpy, 0.008, "¥"));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
