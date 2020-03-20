package com.tchoupi.cardoftherings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SingleQuestionActivity extends AppCompatActivity {

    private int userScore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_question);

        Intent currentIntent = getIntent();
        final Question currentQuestion = currentIntent.getParcelableExtra("question");

        ImageView questionImageView = findViewById(R.id.questionImageView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        final RadioGroup responseRadioGroup = findViewById(R.id.responseRadioGroup);
        final TextView validResponseTextView = findViewById(R.id.validResponseTextView);
        final TextView correctResponseTextView = findViewById(R.id.correctResponseTextView);
        final TextView errorTextView = findViewById(R.id.errorTextView);
        final Button validButton = findViewById(R.id.validButton);

        questionImageView.setImageResource(currentQuestion.getImage());
        questionTextView.setText(currentQuestion.getQuestion());

        ArrayList<String> answerList = currentQuestion.getAnswers();
        Collections.shuffle(answerList);
        for (int i = 0; i < answerList.size(); i++) {
            String currentAnswer = answerList.get(i);
            RadioButton sentenceRadioButton = new RadioButton(this);
            sentenceRadioButton.setText(currentAnswer);
            responseRadioGroup.addView(sentenceRadioButton);
        }

        validButton.setText("Valider la réponse");
        validButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // This will get the radioButton in the radioGroup that is checked
                RadioButton checkedRadioButton = responseRadioGroup.findViewById(responseRadioGroup.getCheckedRadioButtonId());
                if (checkedRadioButton.getText() != null) {
                    errorTextView.setText("");
                    if (checkedRadioButton.getText().equals(currentQuestion.getResponse())) {
                        validResponseTextView.setText("Bonne réponse !");
                        validResponseTextView.setTextColor(Color.GREEN);
                        correctResponseTextView.setText("");
                        userScore += 1;
                    } else {
                        validResponseTextView.setText("Mauvaise réponse !");
                        validResponseTextView.setTextColor(Color.RED);
                        correctResponseTextView.setText("La réponse était : " + currentQuestion.getResponse());
                    }

                    validButton.setText("Résultats");
                    validButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SingleQuestionActivity.this, ResultActivity.class);
                            intent.putExtra("questionTotal", 1);
                            intent.putExtra("userScore", userScore);
                            intent.putExtra("difficulty", currentQuestion.getDifficulty());
                            startActivity(intent);
                        }
                    });
                } else {
                    errorTextView.setText("Veuillez sélectionner une réponse.");
                }
            }
        });
    }
}
