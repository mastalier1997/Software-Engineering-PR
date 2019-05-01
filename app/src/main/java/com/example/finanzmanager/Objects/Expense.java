package com.example.finanzmanager.Objects;

public class Expense extends Position{

    private int id;
    static private int idCounter = 0;

    Expense(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);

        this.id = idCounter;
        idCounter++;
    }


    /**
     * creates a String out of the value and the description from a Position
     * @return String info
     */
    public String getInfo() {
        String info = Double.toString(super.getValue());
        info = info + "\t\t\t\t\t" + super.getDescription();
        return info;
    }

}