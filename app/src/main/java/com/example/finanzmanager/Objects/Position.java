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

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public boolean getRecurring() {
        return recurring;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() { return description; }
}
