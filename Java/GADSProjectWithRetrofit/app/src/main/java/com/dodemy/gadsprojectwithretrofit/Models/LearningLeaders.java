package com.dodemy.gadsprojectwithretrofit.Models;

public class LearningLeaders {
    private String name;
    private int hours;
    private String country;
    private String badgeUrl;

//    public LearningLeaders() {
//    }

    public LearningLeaders(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}
