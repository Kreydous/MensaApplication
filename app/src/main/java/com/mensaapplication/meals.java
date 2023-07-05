package com.mensaapplication;

public class meals {
    String type;
    String mealName;
    String priceStudent;
    String priceBedienstete;
    int rating;

    public meals(String type, String mealName, String priceStudent, String priceBedienstete, int rating, int filter) {
        this.type = type;
        this.mealName = mealName;
        this.priceStudent = priceStudent;
        this.priceBedienstete = priceBedienstete;
        this.rating = rating;
        this.filter = filter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getPriceStudent() {
        return priceStudent;
    }

    public void setPriceStudent(String priceStudent) {
        this.priceStudent = priceStudent;
    }

    public String getPriceBedienstete() {
        return priceBedienstete;
    }

    public void setPriceBedienstete(String priceBedienstete) {
        this.priceBedienstete = priceBedienstete;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    int filter;
}
