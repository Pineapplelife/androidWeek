package com.tchoupi.cardoftherings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class CurrentQuestionActivity extends AppCompatActivity {

    private int questionId;
    private ArrayList<Question> questionArrayList;
    private int userScore;
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
        userScore = currentIntent.getIntExtra("userScore", 0);

        final Question currentQuestion = questionArrayList.get(questionId);

        // Recuperation of the elements from the layout
        ImageView questionImageView = findViewById(R.id.questionImageView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        final TextView validResponseTextView = findViewById(R.id.validResponseTextView);
        final TextView correctResponseTextView = findViewById(R.id.correctResponseTextView);
        final TextView errorTextView = findViewById(R.id.errorTextView);
        final TextView currentIdTextView = findViewById(R.id.currentIdTextView);
        final RadioGroup responseRadioGroup = findViewById(R.id.responseRadioGroup);
        final Button validButton = findViewById(R.id.validButton);

        currentIdTextView.setText((questionId + 1) + " / " + questionArrayList.size());

        // Set the Current Object Question in every fields of the layout
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
        final Question finalCurrentQuestion = currentQuestion;
        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will get the radioButton in the radioGroup that is checked
                RadioButton checkedRadioButton = responseRadioGroup.findViewById(responseRadioGroup.getCheckedRadioButtonId());
                // ROBIN: Gros bug qui attend ici ! Si jamais l'utilisateur
                 // n'a rien sélectionné, radioId renvoie -1.
                // Par conséquent, le findviewById renvoie null /!\
                 // Il faut absolument vérifier le résultat de getCheckedRadioButtonId
                if (checkedRadioButton.getText() != null) {
                    errorTextView.setText("");
                    if (checkedRadioButton.getText().equals(finalCurrentQuestion.getResponse())) {
                        validResponseTextView.setText("Bonne réponse !");
                        validResponseTextView.setTextColor(Color.GREEN);
                        correctResponseTextView.setText("");
                        userScore += 1;
                    } else {
                        validResponseTextView.setText("Mauvaise réponse !");
                        validResponseTextView.setTextColor(Color.RED);
                        correctResponseTextView.setText("La réponse était : " + finalCurrentQuestion.getResponse());
                    }

                    if(questionId + 1 == questionArrayList.size()){
                        validButton.setText("Résultats");
                        validButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(CurrentQuestionActivity.this, ResultActivity.class);
                                intent.putExtra("questionTotal", questionArrayList.size());
                                intent.putExtra("userScore", userScore);
                                intent.putExtra("difficulty", finalCurrentQuestion.getDifficulty());
                                startActivity(intent);
                            }
                        });
                    }
                    else {
                        validButton.setText("Question suivante");
                        // ROBIN: Malin de t'en sortir comme ça, mais ce n'est pas très "scalable"
                        // comme solution. A chaque fois tu réaffect un nouveau ClickListener
                        // a ton bouton.
                        // Le mieux serait de définir les états de tes écrans en créant
                        // ce que l'on appelle une machine à état.
                        // Par exemple une variable globale int state, quand
                        // elle vaut 1, ça veut dire que l'utilisateur n'as pas encore répondu,
                        // le if devient donc if (state == 1) { ... }
                        // ensuite tu passes, a state = 2; Et tu testes par rapport à cette valeur
                        validButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(CurrentQuestionActivity.this, CurrentQuestionActivity.class);
                                intent.putExtra("questions", questionArrayList);
                                intent.putExtra("questionId", questionId + 1);
                                intent.putExtra("userScore", userScore);
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
