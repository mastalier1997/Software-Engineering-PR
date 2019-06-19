package com.example.finanzmanager.DataClasses;

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

    /**
     * creates a String out of the value and the description from a Position
     * @return String info
     */
    public String getInfo() {
        String desc = description;
        String cat = category;
        StringBuffer info = new StringBuffer();

        if(Double.toString(value).length() <= 6)
            info.append(" ");

        info.append(String.format(String.format("%6s", Double.toString(value))));
        if(desc.length() > 17) {
            desc = desc.substring(0, 14) + "...";
        }
        info.append(" ");
        info.append(String.format(String.format("%-17s", desc)));
        if(cat.length() > 6) {
            cat = cat.substring(0, 5) + ".";
        }
        info.append(" ");
        info.append(String.format(String.format("%-6s", cat)));
        info.append(" ");
        String rec;
        if(recurring)
            rec = "ja";
        else rec = "nein";
        info.append(String.format(String.format("%-5s", rec)));
        return info.toString();

        //TODO kann eigentlich entfernt werden
        /*
        StringBuffer info = new StringBuffer();
        int value_size = 9;
        int description_size = 17;
        int category_size = 15;
        info.append(" ");
        info.append(String.format("%1$-" + value_size + "s", Double.toString(value)).replace(' ', '\t'));
        info.append(String.format("%1$-" + description_size + "s", description).replace(' ', '\t'));
        info.append(String.format("%1$-" + category_size + "s", category).replace(' ', '\t'));
        String repeat = "";
        if (recurring) {
            repeat = "ja";
        } else {
            repeat = "nein";
        }
        info.append(String.format("%1$" + 4 + "s", repeat).replace(' ', '\t'));
        return info.toString();
        */
    }

    /**
     *
     * @return
     */

    public String getInfoDate() {
        String desc = description;
        String cat = category;

        StringBuffer info = new StringBuffer();
        info.append(" ");
        info.append(String.format(String.format("%6s", Double.toString(value))));
        if(desc.length() > 17) {
            desc = desc.substring(0, 14) + "...";
        }
        info.append(" ");
        info.append(String.format(String.format("%-17s", desc)));
        if(cat.length() > 6) {
            cat = cat.substring(0, 5) + ".";
        }
        info.append(" ");
        info.append(String.format("%6s", date.getString()));
        return info.toString();

        //TODO kann eigentlich entfernt werden
        /*
        StringBuffer info = new StringBuffer();
        int value_size = 10;
        int description_size = 17;
        int date_size = 19;
        info.append(" ");
        info.append(String.format("%1$-" + value_size + "s", Double.toString(value)).replace(' ', '\t'));
        info.append(String.format("%1$-" + description_size + "s", description).replace(' ', '\t'));
        info.append(String.format("%1$" + date_size + "s", date.getString()).replace(' ', '\t'));
        return info.toString();
        */
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * @return recurring
     */
    public boolean getRecurring() {
        return recurring;
    }

    /**
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return description
     */
    public String getDescription() { return description; }
}
