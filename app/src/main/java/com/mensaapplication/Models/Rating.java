package com.mensaapplication.Models;

public class Rating {

    int id;
    String comment;
    int ratingGiven;

    public Rating(){}

    public Rating(String comment, int ratingGiven) {
        this.comment = comment;
        this.ratingGiven = ratingGiven;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRatingGiven() {
        return ratingGiven;
    }

    public void setRatingGiven(int ratingGiven) {
        this.ratingGiven = ratingGiven;
    }
}
