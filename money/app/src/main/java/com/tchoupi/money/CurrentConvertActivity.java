package com.tchoupi.money;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CurrentConvertActivity extends AppCompatActivity {

    double currencyRate;
    double euroRate;
    String currencyLabel;
    int[] flags = {R.drawable.france_flag};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_convert_activity);

        Intent currentCurrency = getIntent();
        currencyRate = currentCurrency.getDoubleExtra("currencyRate", 0);
        euroRate = currentCurrency.getDoubleExtra("euroRate", 0);
        currencyLabel = currentCurrency.getStringExtra("currencyLabel");
        flags[1] = currentCurrency.getIntExtra("flag", R.drawable.france_flag);

        final Button convertButton = findViewById(R.id.convertButton);
        final TextView amountEditText = findViewById(R.id.amountEditText);
        final TextView resultTextView = findViewById(R.id.resultTextView);
        final TextView errorMessageTextView = findViewById(R.id.errorMessageTextView);
        final TextView deviseTextView = findViewById(R.id.deviseTextView);
        final Button changeConversionButton = findViewById(R.id.changeConversionButton);
        final ImageView flagImageView1 = findViewById(R.id.flagImageView1);
        final ImageView flagImageView2 = findViewById(R.id.flagImageView2);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //TODO
            }
        });



        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User user = new User("Tchoupi", "mgueribout@gmail.com");

                Intent intent = new Intent(CurrentConvertActivity.this, AboutActivity.class);
                intent.putExtra("author", user);
                intent.putExtra("versionLabel", "v1.0.0");
                startActivity(intent);
            }
        });
    }
}
