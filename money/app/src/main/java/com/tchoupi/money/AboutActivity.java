package com.tchoupi.money;

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
        setContentView(R.layout.about_activity);

        Intent srcIntent = getIntent();
        User user = srcIntent.getParcelableExtra("author");
        String versionLabel = srcIntent.getStringExtra("versionLabel");

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView versionTextView = findViewById(R.id.versionTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);

        nameTextView.setText(user.name);
        versionTextView.setText(versionLabel);
        emailTextView.setText(user.email);

        final Intent intent = new Intent(this, CurrentConvertActivity.class);
        Button backButtonHome = findViewById(R.id.backButton);

        backButtonHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
