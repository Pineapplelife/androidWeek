package com.tchoupi.cardoftherings;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_question);

        ArrayList<String> sentencesTest = new ArrayList<>();
        sentencesTest.add("Albus Dumbledore");
        sentencesTest.add("Gandalf");
        sentencesTest.add("Gondulf");
        sentencesTest.add("La réponse D");
        sentencesTest.add("Le Magicien d'Oz");
        final Question questionTest = new Question("Comment s'appelle ce personnage ? la réponse vous étonnera certainement eheh", R.drawable.gandalf, sentencesTest, "Gandalf", "facile");

        ImageView questionImageView = findViewById(R.id.questionImageView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        final RadioGroup responseRadioGroup = findViewById(R.id.responseRadioGroup);
        Button validButton = findViewById(R.id.validButton);

        questionImageView.setImageResource(questionTest.getImage());
        questionTextView.setText(questionTest.getQuestion());
        for(int i = 0; i < questionTest.getSentences().size(); i++){
            String currentSentence = questionTest.getSentences().get(i);
            RadioButton sentenceRadioButton = new RadioButton(this);
            sentenceRadioButton.setText(currentSentence);
            responseRadioGroup.addView(sentenceRadioButton);
        }

        // This will get the radiobutton in the radiogroup that is checked
        final RadioButton checkedRadioButton = responseRadioGroup.findViewById(responseRadioGroup.getCheckedRadioButtonId());

        validButton.setText("Valider la réponse");
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.i("CurrentQuestionActivity","coucou");
            }
        });
    }
}
