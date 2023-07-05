package com.mensaapplication.Models;

import java.util.List;

public class Food {
    int id;
    String name;
    String priceForStudents;
    String priceForNonStudents;
    List<Category> categories;
    float rating;
    String offerCategory;
    String mensaName;

    List<Rating> ratings;

    public Food(){}

    public Food(String name, String priceForStudents, String priceForNonStudents, List<Category> categories, float rating, List<Rating> ratings, String offerCategory,String mensaName) {
        this.name = name;
        this.priceForStudents = priceForStudents;
        this.priceForNonStudents = priceForNonStudents;
        this.categories = categories;
        this.rating = rating;
        this.ratings = ratings;
        this.offerCategory = offerCategory;
        this.mensaName = mensaName;
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

    public String getPriceForStudents() {
        return priceForStudents;
    }

    public void setPriceForStudents(String priceForStudents) {
        this.priceForStudents = priceForStudents;
    }

    public String getPriceForNonStudents() {
        return priceForNonStudents;
    }

    public void setPriceForNonStudents(String priceForNonStudents) {
        this.priceForNonStudents = priceForNonStudents;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOfferCategory() {
        return offerCategory;
    }

    public void setOfferCategory(String offerCategory) {
        this.offerCategory = offerCategory;
    }

    public String getMensaName() {
        return mensaName;
    }

    public void setMensaName(String mensaName) {
        this.mensaName = mensaName;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
