package com.mensaapplication.Models;

import java.util.List;

//mensa model
public class Mensa {

    int id;
    String name;
    String location;
    float rating;
    List<Rating> ratings;

    public Mensa(){}

    public Mensa(String name, String location, float rating, List<Rating> ratings) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
