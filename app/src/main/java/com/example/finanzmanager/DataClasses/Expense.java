package com.example.finanzmanager.DataClasses;

public class Expense extends Position{

    private int id;
    static private int idCounter = 0;

    Expense(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);

        this.id = idCounter;
        idCounter++;
    }
}