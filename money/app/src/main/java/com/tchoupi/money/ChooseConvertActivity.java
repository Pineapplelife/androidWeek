package com.tchoupi.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChooseConvertActivity extends AppCompatActivity {

    int[] flags = {R.drawable.usa_flag, R.drawable.british_flag, R.drawable.japan_flag};
    String[] currencyLabel = {"$", "£", "¥"};
    double[] rate = {1.116, 0.911, 118.730};
    double[] euroRate = {0.895, 1.097, 0.008};

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
                intent.putExtra("flag", flags[0]);
                intent.putExtra("currencyLabel", currencyLabel[0]);
                intent.putExtra("currencyRate", rate[0]);
                intent.putExtra("euroRate", euroRate[0]);
                startActivity(intent);
            }
        });

        poundConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseConvertActivity.this, CurrentConvertActivity.class);
                intent.putExtra("flag", flags[1]);
                intent.putExtra("currencyLabel", currencyLabel[1]);
                intent.putExtra("currencyRate", rate[1]);
                intent.putExtra("euroRate", euroRate[1]);
                startActivity(intent);
            }
        });

        yenConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseConvertActivity.this, CurrentConvertActivity.class);
                intent.putExtra("flag", flags[2]);
                intent.putExtra("currencyLabel", currencyLabel[2]);
                intent.putExtra("currencyRate", rate[2]);
                intent.putExtra("euroRate", euroRate[2]);
                startActivity(intent);
            }
        });
    }
}
