package com.tchoupi.cardoftherings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CurrentQuestionActivity extends AppCompatActivity {

    /**
     * Function onCreate, called first, which initialize the activity
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_question);

        // Creation of the Current Question
        ArrayList<String> sentencesTest = new ArrayList<>();
        sentencesTest.add("Albus Dumbledore");
        sentencesTest.add("Gandalf");
        sentencesTest.add("Gondulf");
        sentencesTest.add("La réponse D");
        final Question questionTest = new Question("Comment s'appelle ce personnage ? la réponse vous étonnera certainement eheh", R.drawable.gandalf, sentencesTest, "Gandalf", "facile");

        // Recuperation of the elements from the layout
        ImageView questionImageView = findViewById(R.id.questionImageView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        final TextView validResponseTextView = findViewById(R.id.validResponseTextView);
        final TextView correctResponseTextView = findViewById(R.id.correctResponseTextView);
        final TextView errorTextView = findViewById(R.id.errorTextView);
        final RadioGroup responseRadioGroup = findViewById(R.id.responseRadioGroup);
        Button validButton = findViewById(R.id.validButton);

        // Set the Current Object Question in every fields of the layout
        questionImageView.setImageResource(questionTest.getImage());
        questionTextView.setText(questionTest.getQuestion());
        for (int i = 0; i < questionTest.getSentences().size(); i++) {
            String currentSentence = questionTest.getSentences().get(i);
            RadioButton sentenceRadioButton = new RadioButton(this);
            sentenceRadioButton.setText(currentSentence);
            responseRadioGroup.addView(sentenceRadioButton);
        }


        validButton.setText("Valider la réponse");
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will get the radioButton in the radioGroup that is checked
                RadioButton checkedRadioButton = responseRadioGroup.findViewById(responseRadioGroup.getCheckedRadioButtonId());
                if (checkedRadioButton.getText() != null) {
                    errorTextView.setText("");
                    if(checkedRadioButton.getText().equals(questionTest.getResponse())){
                        validResponseTextView.setText("Bonne réponse !");
                        validResponseTextView.setTextColor(Color.GREEN);
                        correctResponseTextView.setText("");
                    } else {
                        validResponseTextView.setText("Mauvaise réponse !");
                        validResponseTextView.setTextColor(Color.RED);
                        correctResponseTextView.setText("La réponse était : " + questionTest.getResponse());
                    }
                }
                else {
                    errorTextView.setText("Veuillez sélectionner une réponse.");
                }
            }
        });
    }
}
