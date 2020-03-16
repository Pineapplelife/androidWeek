package com.tchoupi.money;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private TextView resultTextView;
    private TextView errorMessageTextView;
    private TextView labelTextView;
    final double euroToDollar = 1.113;
    final double dollarToEuro = 0.898;
    String conversionType = "euroToDollar";
    private Button changeConversionButton;
    private ImageView flagImageView;
    final private int[] flags = {R.drawable.flag_usa, R.drawable.flag_euro};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convertButton = findViewById(R.id.convertButton);
        amountEditText = findViewById(R.id.amountEditText);
        resultTextView = findViewById(R.id.resultTextView);
        errorMessageTextView = findViewById(R.id.errorMessageTextView);
        labelTextView = findViewById(R.id.labelTextView);
        changeConversionButton = findViewById(R.id.changeConversionButton);
        flagImageView = findViewById(R.id.flagImageView);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(amountEditText.getText().toString().length() < 1) {
                    errorMessageTextView.setText("Please enter a number ton convert.");
                }
                else {
                    errorMessageTextView.setText("");
                    double inputUser = Double.parseDouble(amountEditText.getText().toString());
                    if(conversionType.equals("euroToDollar")){
                        double result = inputUser * euroToDollar;
                        resultTextView.setText(Double.toString(result));
                    }
                    else if(conversionType.equals("dollarToEuro")){
                        double result = inputUser * dollarToEuro;
                        resultTextView.setText(Double.toString(result));
                    }
                }
            }
        });

        changeConversionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(conversionType.equals("euroToDollar")){
                    conversionType = "dollarToEuro";
                    flagImageView.setImageResource(flags[1]);
                    changeConversionButton.setText("Dollar to Euro");
                    labelTextView.setText("$");
                }
                else if(conversionType.equals("dollarToEuro")){
                    conversionType = "euroToDollar";
                    flagImageView.setImageResource(flags[0]);
                    changeConversionButton.setText("Euro To Dollar");
                    labelTextView.setText("€");
                }
            }
        });
    }
}
