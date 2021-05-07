package com.deniz.gymmanagement;


public class Lesson {
    private String Title;
    private String PTName;
    private Double Price;

    public Lesson(String title, String ptName, Double price) {
        Title = title;
        PTName = ptName;
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public String getPTName() {
        return PTName;
    }

    public Double getPrice() {
        return Price;
    }
}
