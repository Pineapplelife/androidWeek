package com.tchoupi.cardoftherings;

import java.util.ArrayList;

public class Question {

    private final String question;
    private final int image;
    private final ArrayList<String> sentences;
    private final String response;
    private final String difficulty;

    public Question(String question, int image, ArrayList<String> sentences, String response, String difficulty) {
        this.question = question;
        this.image = image;
        this.sentences = sentences;
        this.response = response;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public int getImage() {
        return image;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public String getResponse() {
        return response;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
