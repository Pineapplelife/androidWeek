package com.tchoupi.cardoftherings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    /**
     * This Activity is the first screen of the App
     * You can choose to play, view the question list or go to the about screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Recuperation of all elements from the layout Home
        Button playButton = findViewById(R.id.playButton);
        Button questionListButton = findViewById(R.id.questionListButton);
        Button aboutButton = findViewById(R.id.aboutButton);

        questionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuestionsFromApi("all");
            }
        });

        //TODO
        // ROBIN: Tout ce qui est dans le ClickListener aurait mérité
        // d'être dans une fonction à part pour alléger un peu onCreate
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(HomeActivity.this);
                builderSingle.setTitle("Sélectionne une difficulté :");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("J'ai vu les films 1 fois je crois");
                arrayAdapter.add("Aventurier aguerri");
                arrayAdapter.add("Sueurs et Larmes");

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(HomeActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Tu as sélectionné la difficulté :");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String difficulty;
                                switch (strName){
                                    case "J'ai vu les films 1 fois je crois":
                                        difficulty = "facile";
                                        break;
                                    case "Aventurier aguerri":
                                        difficulty = "moyen";
                                        break;
                                    // ROBIN: Les questions sur la géographie ici...
                                    // espèce de p'tit b*tard :-D !!
                                    // Je me suis loupé sur le lac, sinon j'étais bon, ouf!!
                                    case "Sueurs et Larmes":
                                        difficulty = "difficile";
                                        break;
                                    default:
                                        difficulty = "";
                                }
                                loadQuestionsFromApi(difficulty);
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                intent.putExtra("appName", "The Lord of the Quizz");
                intent.putExtra("version", "V " + BuildConfig.VERSION_NAME);
                intent.putExtra("author", "Made with ♥ by TCHOUPI");
                startActivity(intent);
            }
        });
    }


    private void loadQuestionsFromApi(final String difficulty) {
        OkHttpClient client = new OkHttpClient();

        String url = quizUrlFromDifficulty(difficulty);

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("HomeActivity", "onFailure: " + e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                final ArrayList<Question> questions = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(body);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ArrayList<String> answers = new ArrayList<>();
                        for (int j = 0; j < jsonObject.getJSONArray("answers").length(); j++) {
                            answers.add(jsonObject.getJSONArray("answers").getString(j));
                        }
                        Context context = HomeActivity.this;
                        questions.add(new Question(
                                jsonObject.getString("question"),
                                context.getResources().getIdentifier(jsonObject.getString("image"), "drawable", context.getPackageName()),
                                answers,
                                jsonObject.getString("response"),
                                jsonObject.getString("difficulty")
                        ));
                    }
                    Collections.shuffle(questions);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (difficulty == "all") {
                                Intent intent = new Intent(HomeActivity.this, QuestionListActivity.class);
                                intent.putExtra("questions", questions);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(HomeActivity.this, CurrentQuestionActivity.class);
                                intent.putExtra("questions", questions);
                                intent.putExtra("questionId", 0);
                                intent.putExtra("singleQuestion", false);
                                startActivity(intent);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String quizUrlFromDifficulty(String difficulty) {
        String diffParam = "";
        switch (difficulty) {
            case "facile":
                diffParam = "?difficulty=facile";
                break;
            case "moyen":
                diffParam = "?difficulty=moyen";
                break;
            case "difficile":
                diffParam = "?difficulty=difficile";
                break;
        }

        return "http://gryt.tech:8080/lotr/" + diffParam;
    }
}
