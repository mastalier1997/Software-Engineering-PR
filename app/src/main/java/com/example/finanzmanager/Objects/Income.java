package com.example.finanzmanager.Objects;

public class Income extends Position {

    private int id;
    static private int idCounter = 0;

    Income(Date date, double value, boolean recurring, String category, String description) {
        super(date, value, recurring, category, description);
        this.id = idCounter;
        idCounter++;
    }


    /**
     * creates a String out of the value and the description from a Position
     * @return String info
     */
    public String getInfo() {
        StringBuffer info = new StringBuffer();
        int value_size = 9;
        int description_size = 13;
        int category_size = 13;
        info.append(String.format("%1$-" + value_size + "s", Double.toString(super.getValue())).replace(' ', '\t'));
        info.append(String.format("%1$-" + description_size + "s", super.getDescription()).replace(' ', '\t'));
        info.append(String.format("%1$-" + category_size + "s", super.getCategory()).replace(' ', '\t'));
        String repeat = "";
        if (super.getRecurring()) {
            repeat = "ja";
        } else {
            repeat = "nein";
        }
        info.append(repeat);
        return info.toString();
    }

}
