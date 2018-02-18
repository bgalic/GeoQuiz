package com.example.geoquiz;

public class Continent {
    public String Name;

    public Continent(String name) {
        Name = name;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "Name='" + Name + '\'' +
                '}';
    }
}

