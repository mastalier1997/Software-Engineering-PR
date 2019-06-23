package com.example.finanzmanager.DataClasses;

public class Income extends Position {

    //TODO Wird nie verwendet, zu etwas gut?
    private int id;
    static private int idCounter = 0;

    public Income(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);
        this.id = idCounter;
        idCounter++;
    }

    public int getId() {
        return id;
    }

}
