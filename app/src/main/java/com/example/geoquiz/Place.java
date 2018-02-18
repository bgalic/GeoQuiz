package com.example.geoquiz;

public class Place {
    public int id;
    public String latitude;
    public String longitude;
    public String temp;

    public Place(int id, String latitude, String longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temp = temp;
    }

    public Place(int id, String latitude, String longitude, String temp) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temp = temp;
    }

    public Place() {
    }

    public String getTemp() {
        return temp;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }



    public String getLongitude() {
        return longitude;
    }



    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", temp='" + temp + '\'' +
                '}';
    }
}
