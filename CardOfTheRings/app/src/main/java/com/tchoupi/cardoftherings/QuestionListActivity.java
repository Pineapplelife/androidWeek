package com.tchoupi.cardoftherings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class QuestionListActivity extends AppCompatActivity {

    private ArrayList<Question> questionArrayList = new ArrayList<>();

    QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list);

        Intent currentIntent = getIntent();
        questionArrayList = currentIntent.getParcelableArrayListExtra("questions");
        Log.i("QuestionListActivity", String.valueOf(questionArrayList));

        adapter = new QuestionAdapter(questionArrayList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
