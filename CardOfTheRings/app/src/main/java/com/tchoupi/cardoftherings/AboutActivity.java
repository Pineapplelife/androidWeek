package com.tchoupi.cardoftherings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Intent currentIntent = getIntent();

        TextView appNameTextView = findViewById(R.id.appNameTextView);
        TextView versionTextView = findViewById(R.id.versionTextView);
        TextView authorTextView = findViewById(R.id.authorTextView);

        appNameTextView.setText(currentIntent.getStringExtra("appName"));
        versionTextView.setText(currentIntent.getStringExtra("version"));
        authorTextView.setText(currentIntent.getStringExtra("author"));

        Button homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
