package com.example.finanzmanager.DataClasses;

public class   PositionSample {
    int positionType;
    Date date;
    int value;
    boolean reocurring;
    String category;
    String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 0 = Expense
     * 1 = Income
     */
    public int getPositionType() {
        return positionType;
    }

    /**
     * 0 = Expense
     * 1 = Income
     * @param positionType
     */
    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean getReocurring() {
        return reocurring;
    }

    public void setReocurring(boolean reocurring) {
            this.reocurring = reocurring;
    }

    @Override
    public String toString() {
        return "PositionSample{" +
                "positionType=" + positionType +
                ", date=" + date +
                ", value=" + value +
                ", reocurring=" + reocurring +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
