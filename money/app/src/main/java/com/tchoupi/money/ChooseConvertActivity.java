package com.tchoupi.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseConvertActivity extends AppCompatActivity {

    Currency dollar = new Currency(R.drawable.usa_flag, 1.116, 0.895, "$");
    Currency pound = new Currency(R.drawable.british_flag, 0.911, 1.097, "£");
    Currency yen = new Currency(R.drawable.japan_flag, 118.730, 0.008, "¥");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_convert_activity);

        final Button dollarConvertButton = findViewById(R.id.dollarConvertButton);
        final Button poundConvertButton = findViewById(R.id.poundConvertButton);
        final Button yenConvertButton = findViewById(R.id.yenConvertButton);

        dollarConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseConvertActivity.this, CurrentConvertActivity.class);
                intent.putExtra("flag", dollar.getFlag());
                intent.putExtra("currencyLabel", dollar.getSymbol());
                intent.putExtra("currencyRate", dollar.getRate());
                intent.putExtra("euroRate", dollar.getEuroRate());
                startActivity(intent);
            }
        });

        poundConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseConvertActivity.this, CurrentConvertActivity.class);
                intent.putExtra("flag", pound.getFlag());
                intent.putExtra("currencyLabel", pound.getSymbol());
                intent.putExtra("currencyRate", pound.getRate());
                intent.putExtra("euroRate", pound.getEuroRate());
                startActivity(intent);
            }
        });

        yenConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseConvertActivity.this, CurrentConvertActivity.class);
                intent.putExtra("flag", yen.getFlag());
                intent.putExtra("currencyLabel", yen.getSymbol());
                intent.putExtra("currencyRate", yen.getRate());
                intent.putExtra("euroRate", yen.getEuroRate());
                startActivity(intent);
            }
        });
    }
}
