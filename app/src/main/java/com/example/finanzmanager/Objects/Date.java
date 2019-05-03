package com.example.finanzmanager.Objects;

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
