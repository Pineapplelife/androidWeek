package com.tchoupi.cardoftherings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent currentIntent = getIntent();
        int questionTotal = currentIntent.getIntExtra("questionTotal", 0);
        int userScore = currentIntent.getIntExtra("userScore", 0);
        int scoreRate = (userScore * 100) / questionTotal;

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        TextView difficultyTextView = findViewById(R.id.difficultyTextView);
        TextView scoreRateTextView = findViewById(R.id.scoreRateTextView);

        scoreTextView.setText(userScore + " / " + questionTotal);
        difficultyTextView.setText("Difficulté : " + currentIntent.getStringExtra("difficulty"));
        scoreRateTextView.setText("Taux de réussite : " + scoreRate + " %");

        Button homeButton = findViewById(R.id.homeButton);
        Button aboutButton = findViewById(R.id.aboutButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, AboutActivity.class);
                intent.putExtra("appName", "The Lord of the Quizz");
                intent.putExtra("version", "V " + BuildConfig.VERSION_NAME);
                intent.putExtra("author", "Made with ♥ by TCHOUPI");
                startActivity(intent);
            }
        });
    }
}
