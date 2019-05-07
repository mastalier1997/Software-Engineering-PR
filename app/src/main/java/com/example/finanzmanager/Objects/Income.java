package com.example.finanzmanager.Objects;

public class Income extends Position {

    private int id;
    static private int idCounter = 0;

    Income(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);
        this.id = idCounter;
        idCounter++;
    }

}
