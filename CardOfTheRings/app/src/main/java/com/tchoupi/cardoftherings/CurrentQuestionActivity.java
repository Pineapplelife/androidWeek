package com.tchoupi.cardoftherings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class CurrentQuestionActivity extends AppCompatActivity {

    private int questionId;
    private ArrayList<Question> questionArrayList;
    /**
     * Function onCreate, called first, which initialize the activity
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_question);

        Intent currentIntent = getIntent();
        final ArrayList<Question> questionArrayList = currentIntent.getParcelableArrayListExtra("questions");
        questionId = currentIntent.getIntExtra("questionId", 0);

        final Question currentQuestion = questionArrayList.get(questionId);

        // Recuperation of the elements from the layout
        ImageView questionImageView = findViewById(R.id.questionImageView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        final TextView validResponseTextView = findViewById(R.id.validResponseTextView);
        final TextView correctResponseTextView = findViewById(R.id.correctResponseTextView);
        final TextView errorTextView = findViewById(R.id.errorTextView);
        final RadioGroup responseRadioGroup = findViewById(R.id.responseRadioGroup);
        final Button validButton = findViewById(R.id.validButton);

        // Set the Current Object Question in every fields of the layout
        questionImageView.setImageResource(currentQuestion.getImage());
        questionTextView.setText(currentQuestion.getQuestion());
        for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
            String currentAnswer = currentQuestion.getAnswers().get(i);
            RadioButton sentenceRadioButton = new RadioButton(this);
            sentenceRadioButton.setText(currentAnswer);
            responseRadioGroup.addView(sentenceRadioButton);
        }


        validButton.setText("Valider la réponse");
        final Question finalQuestionTest = currentQuestion;
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will get the radioButton in the radioGroup that is checked
                RadioButton checkedRadioButton = responseRadioGroup.findViewById(responseRadioGroup.getCheckedRadioButtonId());
                if (checkedRadioButton.getText() != null) {
                    errorTextView.setText("");
                    if (checkedRadioButton.getText().equals(finalQuestionTest.getResponse())) {
                        validResponseTextView.setText("Bonne réponse !");
                        validResponseTextView.setTextColor(Color.GREEN);
                        correctResponseTextView.setText("");
                    } else {
                        validResponseTextView.setText("Mauvaise réponse !");
                        validResponseTextView.setTextColor(Color.RED);
                        correctResponseTextView.setText("La réponse était : " + finalQuestionTest.getResponse());
                    }

                    if(questionId + 1 == questionArrayList.size()){
                        validButton.setText("Résultats");
                        validButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(CurrentQuestionActivity.this, CurrentQuestionActivity.class);
                                intent.putExtra("questions", questionArrayList);
                                intent.putExtra("questionId", questionId + 1);
                                startActivity(intent);
                            }
                        });
                    }
                    else {
                        validButton.setText("Question suivante");
                        validButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(CurrentQuestionActivity.this, CurrentQuestionActivity.class);
                                intent.putExtra("questions", questionArrayList);
                                intent.putExtra("questionId", questionId + 1);
                                startActivity(intent);
                            }
                        });
                    }
                } else {
                    errorTextView.setText("Veuillez sélectionner une réponse.");
                }
            }
        });
    }
}
