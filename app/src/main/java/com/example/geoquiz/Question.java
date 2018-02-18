package com.example.geoquiz;

import java.io.Serializable;

public class Question implements Serializable {

    public int id;
    public int place_id;
    public String question;
    public String answer;
    public  String place;

    public Question(int id, int place_id, String question, String answer) {
        this.id = id;
        this.place_id = place_id;
        this.question = question;
        this.answer = answer;
    }

    public Question(int id, int place_id, String question, String answer, String place) {
        this.id = id;
        this.place_id = place_id;
        this.question = question;
        this.answer = answer;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getQuestion() {
        return question;
    }



    public String getAnswer() {
        return answer;
    }



    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", place_id=" + place_id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", place='" + place + '\'' +
                '}';
    }


}
