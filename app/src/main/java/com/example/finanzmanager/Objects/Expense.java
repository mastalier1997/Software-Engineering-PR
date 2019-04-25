package com.example.finanzmanager.Objects;

public class Expense extends Position{

    private int id;
    static private int idCounter = 0;

    Expense(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);

        this.id = idCounter;
        idCounter++;
    }

    void printExpense() {
        System.out.print("Id: " + id + ", Datum: ");
        super.printPosition();
    }

    String getInfo() {
        String info = Double.toString(super.getValue());
        info = info + "\t\t\t\t\t" + super.getDescription();
        return info;
    }

}