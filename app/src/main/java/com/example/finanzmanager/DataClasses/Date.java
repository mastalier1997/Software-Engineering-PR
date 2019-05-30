package com.example.finanzmanager.DataClasses;

public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (day <= 0 || month <= 0 || year <= 0) {
            throw new IllegalArgumentException("Ungueltiges Datum");
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(day);
        buffer.append(".");
        buffer.append(month);
        buffer.append(".");
        buffer.append(year);
        return buffer.toString();
    }

    /**
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return year
     */
    public int getYear() {
        return year;
    }
}
