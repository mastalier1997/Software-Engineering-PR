package com.example.finanzmanager.Objects;

class Income extends Position {

    // der Counter ist static sodass jedes mal wenn ein Objekt der Klasse erstellt wird der counter nicht wieder bei null beginnt
    private int id;
    static private int idCounter = 0;

    Income(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);
        this.id = idCounter;
        idCounter++;
    }

    void printIncome() {
        System.out.print("Id: " + id + ", Datum: ");
        super.printPosition();
    }

    String getInfo() {
        String info = Double.toString(super.getValue());
        info = info + "\t\t\t\t\t" + super.getDescription();
        return info;
    }

}
