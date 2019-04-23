package com.example.finanzmanager.Objects;

public class Position {
    private Date date;
    private double value;
    private boolean recurring;
    private String category;
    private String description;

    Position(Date date, double value, boolean recurring, String category, String description) {
        this.date = date;
        this.value = value;
        this.recurring = recurring;
        this.category = category;
        this.description = description;
    }

    void printPosition() {
        date.printDate();
        System.out.println(", Betrag: " + value + ", Kategorie: " + category);
    }

    Date getDate() {
        return date;
    }

    double getValue() {
        return value;
    }

    public boolean getRecurring() {
        return recurring;
    }

    String getCategory() {
        return category;
    }

    String getDescription() { return description; }
}
