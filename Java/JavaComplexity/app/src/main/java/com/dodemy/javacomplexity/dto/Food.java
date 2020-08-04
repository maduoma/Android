package com.dodemy.javacomplexity.dto;

public class Food {

    private String name;
    private int calories;
    private String cost;
    private String prepTime;
    private String type = "Undefined";

    public Food() {

    }

    public Food(String name) {
        this.name = name;
    }

    public Food(String name, int calories, String cost, String prepTime) {
        this.name = name;
        this.calories = calories;
        this.cost = cost;
        this.prepTime = prepTime;
    }

    public Food(String name, int calories, String cost) {
        this.name = name;
        this.calories = calories;
        this.cost = cost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return name + " " + type;
    }

}
