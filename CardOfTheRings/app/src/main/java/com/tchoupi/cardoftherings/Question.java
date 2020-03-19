package com.tchoupi.cardoftherings;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {

    private final String question;
    private final int image;
    private final ArrayList<String> answers;
    private final String response;
    private final String difficulty;

    public Question(String question, int image, ArrayList<String> answers, String response, String difficulty) {
        this.question = question;
        this.image = image;
        this.answers = answers;
        this.response = response;
        this.difficulty = difficulty;
    }

    protected Question(Parcel in) {
        question = in.readString();
        image = in.readInt();
        answers = in.createStringArrayList();
        response = in.readString();
        difficulty = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public int getImage() {
        return image;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getResponse() {
        return response;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeInt(image);
        dest.writeStringList(answers);
        dest.writeString(response);
        dest.writeString(difficulty);
    }
}
