package com.example.geoquiz;

public class Score {
    public int id;
    public int numberOfCorrect;
    public String place;
    public String continent;



    public Score(int id, int numberOfCorrect, String place, String continent) {

        this.id = id;
        this.numberOfCorrect = numberOfCorrect;
        this.place = place;
        this.continent = continent;
    }



    public Score(int id, int numberOfCorrect, String place) {
        this.id = id;
        this.numberOfCorrect = numberOfCorrect;
        this.place = place;
    }

    public Score(int numberOfCorrect, String place, String continent) {
        this.numberOfCorrect = numberOfCorrect;
        this.place = place;
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }



    public String getPlace() {
        return place;
    }



    public String getContinent() {
        return continent;
    }



    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", numberOfCorrect=" + numberOfCorrect +
                ", place='" + place + '\'' +
                '}';
    }
}
