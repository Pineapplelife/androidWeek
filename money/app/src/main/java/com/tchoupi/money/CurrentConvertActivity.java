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
    String euroLabel = "€";
    int[] flags = {R.drawable.france_flag , 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_convert_activity);

        Intent currentIntent = getIntent();
        Currency currentCurrency = currentIntent.getParcelableExtra("currency");
        flags[1] = currentCurrency.getFlag();
        currencyRate = currentCurrency.getRate();
        currencyLabel = currentCurrency.getSymbol();
        euroRate = currentCurrency.getEuroRate();

        final Button convertButton = findViewById(R.id.convertButton);
        final TextView amountEditText = findViewById(R.id.amountEditText);
        final TextView resultTextView = findViewById(R.id.resultTextView);
        final TextView errorMessageTextView = findViewById(R.id.errorMessageTextView);
        final TextView currencyLabelTextView = findViewById(R.id.currencyLabelTextView);
        final Button changeConversionButton = findViewById(R.id.changeConversionButton);
        final ImageView flagImageView1 = findViewById(R.id.flagImageView1);
        final ImageView flagImageView2 = findViewById(R.id.flagImageView2);

        currencyLabelTextView.setText(euroLabel);
        flagImageView1.setImageResource(flags[0]);
        flagImageView2.setImageResource(flags[1]);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(amountEditText.getText().toString().length() < 1){
                    errorMessageTextView.setText("Enter a number to convert.");
                }
                else {
                    double inputUser = Double.parseDouble(amountEditText.getText().toString());
                    if(currencyLabelTextView.getText().toString().equals(euroLabel)){
                        resultTextView.setText(inputUser * currencyRate + " " + currencyLabel);
                    }
                    else {
                        resultTextView.setText(inputUser * euroRate + " " + euroLabel);
                    }
                }
            }
        });

        changeConversionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                if(currencyLabelTextView.getText().toString().equals(euroLabel)){
                    currencyLabelTextView.setText(currencyLabel);
                    flagImageView1.setImageResource(flags[1]);
                    flagImageView2.setImageResource(flags[0]);
                }
                else {
                    currencyLabelTextView.setText(euroLabel);
                    flagImageView1.setImageResource(flags[0]);
                    flagImageView2.setImageResource(flags[1]);
                }
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentConvertActivity.this, CurrencyListActivity.class);
                startActivity(intent);
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
