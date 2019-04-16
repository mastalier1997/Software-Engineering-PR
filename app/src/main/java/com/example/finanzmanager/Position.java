package com.example.finanzmanager;

public class Position {
    private Date date;
    private double value;
    private boolean recurring;
    private String category;
    private String description;

    public Position(Date date, double value, boolean recurring, String category, String description) {
        this.date = date;
        this.value = value;
        this.recurring = recurring;
        this.category = category;
        this.description = description;
    }

    public void printPosition() {
        date.printDate();
        System.out.println(", Betrag: " + value + ", Kategorie: " + category);
    }

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public boolean getRecurring() {
        return recurring;
    }

    public String getcategory() {
        return category;
    }
}
