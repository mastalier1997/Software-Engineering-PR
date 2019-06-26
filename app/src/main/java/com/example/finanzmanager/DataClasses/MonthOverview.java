package com.example.finanzmanager.DataClasses;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthOverview {

    private ArrayList<Month> overview;
    //private int sumIncome; //never used

    public MonthOverview() {
        overview = new ArrayList<>();
        for (int year = 2010; year <= 2025; year++){
            for (int i = 1; i <= 12; i++) {
                Month insert = new Month(year, i);
                overview.add(insert);
            }
        }
    }

    /**
     * adds a new month to the list of months
     * @param year value of the new year
     * @param month value of the new month
     */
    public void newMonth(int year, int month) {
        overview.add(new Month(year, month));
    }

    /**
     * returns the number of saved months
     * @return size
     */
    public int size() {
        return overview.size();
    }


    /**
     * returns a list of all the months starting from an initial Month
     * @param year value of the needed year
     * @param month value of the needed month
     * @return list of months
     */
    public ArrayList<Month> fromMonth(int year, int month) {
        ArrayList<Month> from = new ArrayList<>();
        for (Month i : overview) {
            if (i.getYear() >= year) {
                if(i.getYear() == year) {
                    if(i.getMonth()>= month) {
                        from.add(i);
                    }
                } else {
                    from.add(i);
                }
            }
        }
        return from;
    }


    /**
     * checks if a year is already included in the list
     * @param year
     * @return if the year exists
     */
    public boolean yearExists(int year) {
        for (Month i : overview) {
            if (i.getYear() == year) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns the Month according the the parameters
     * @param year
     * @param month
     * @return month
     */
    public Month getMonth(int year, int month) {
        for (Month i : overview) {
            if ((i.getMonth() == month) && (i.getYear() == year)) {
                return i;
            }
        }
        return null;
    }


    /**
     * updates the sum of all incomes from a month and also its balance
     * @param year
     * @param month
     * @param value
     */
    public void updateMonthIncome(int year, int month, double value) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                Month cache = overview.get(i);
                cache.setIncome(value);
                overview.set(i, cache);
            }
        }
    }

    /**
     * updates the sum of all expenses from a month and also its balance
     * @param year
     * @param month
     * @param value
     */
    public void updateMonthExpense(int year, int month, double value) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                Month cache = overview.get(i);
                cache.setExpense(value);
                overview.set(i, cache);
            }
        }
    }


    /**
     * adds a new year with all its 12 months to the list
     * @param year
     */
    public void newYear(int year) {
        for (int i = 1; i <= 12; i++) {
           Month insert = new Month (year, i);
           overview.add(insert);
        }
    }

    /**
     * this method is only used internally, it just makes sure, that older versions of shared preference files still work by filling all months from the MonthOverview
     */
    public void addAllMonths() {
        for (int year = 2010; year <= 2025; year++){
            for (int i = 1; i <= 12; i++) {
                Month insert = new Month(year, i);
                if(!this.yearExists(year)) {
                    overview.add(insert);
                }
            }
        }
    }
}
