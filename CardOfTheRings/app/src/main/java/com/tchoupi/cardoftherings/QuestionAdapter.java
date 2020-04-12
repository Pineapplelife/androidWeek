package com.tchoupi.cardoftherings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Question> questionArrayList;

    QuestionAdapter(ArrayList<Question> questionArrayList) { this.questionArrayList = questionArrayList; }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rootItem:
                Context context = v.getContext();
                Question question = (Question) v.getTag();
                ArrayList<Question> questionList = new ArrayList<>();
                questionList.add(question);
                Intent intent = new Intent(context, CurrentQuestionActivity.class);
                intent.putExtra("questions", questionList);
                intent.putExtra("questionId", 0);
                intent.putExtra("singleQuestion", true);
                context.startActivity(intent);
                break;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView image;
        final TextView question;
        final TextView answers;
        final TextView difficulty;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.questionImageView);
            question = itemView.findViewById(R.id.questionTextView);
            answers = itemView.findViewById(R.id.answersTextView);
            difficulty = itemView.findViewById(R.id.difficultyTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        final Question question = questionArrayList.get(position);
        holder.image.setImageResource(question.getImage());
        holder.question.setText(question.getQuestion());

        ArrayList<String> answers = question.getAnswers();
        // ROBIN: Ici ta méthode est un peu bourrine, tu affectes à chaque fois
        // au TextView alors que tu vas le modifier au prochaine tour de boucle.
        // De plus, les String sont immutables en Java => Chaque fois que tu fais
        // une str = str + "toto", tu génères une nouvelle String.

        //DONE

        StringBuilder sb = new StringBuilder();
        String separator = "";
        for (String answer : answers) {
            sb.append(separator);
            sb.append(answer);
            separator = ", ";
        }
        holder.answers.setText(sb.toString());


        switch (question.getDifficulty()){
            case "facile":
                holder.difficulty.setTextColor(Color.rgb(0,150,0));
                holder.difficulty.setText(question.getDifficulty().toUpperCase());
                break;
            case "moyen":
                holder.difficulty.setTextColor(Color.rgb(255,128,0));
                holder.difficulty.setText(question.getDifficulty().toUpperCase());
                break;
            case "difficile":
                holder.difficulty.setTextColor(Color.rgb(179,0,0));
                holder.difficulty.setText(question.getDifficulty().toUpperCase());
                break;
        }

        holder.itemView.setTag(question);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }


}
