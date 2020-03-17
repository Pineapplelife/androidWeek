package com.tchoupi.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CurrencyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_list_activity);

        List<Currency> currencies = new ArrayList<>();

        for(int i = 0; i < 30; i++){
            currencies.add(new Currency(R.drawable.usa_flag, 1.116, 0.895, "$"));
            currencies.add(new Currency(R.drawable.british_flag, 0.911, 1.097, "£"));
            currencies.add(new Currency(R.drawable.japan_flag, 118.730, 0.008, "¥"));
        }

        CurrencyAdapter adapter = new CurrencyAdapter(currencies);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
