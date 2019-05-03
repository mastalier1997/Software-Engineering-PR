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
