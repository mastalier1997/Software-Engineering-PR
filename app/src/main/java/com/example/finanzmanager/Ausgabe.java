package com.example.finanzmanager;

public class Ausgabe extends Position{

    private int id;
    static private int idCounter = 0;

    public Ausgabe(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);

        this.id = idCounter;
        idCounter++;
    }

    public void printExpense() {
        System.out.print("Id: " + id + ", Datum: ");
        super.printPosition();
    }
}