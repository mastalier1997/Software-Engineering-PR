package com.example.finanzmanager.Objects;

import java.util.ArrayList;

public class Month_overview {

    private ArrayList<Month> overview;

    public Month_overview() {
        overview = new ArrayList<>();
    }

    public void newMonth(int year, int month) {
        overview.add(new Month(year, month));
    }

    public int size() {
        return overview.size();
    }

    public Month getMonth(int year, int month) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                return overview.get(i);
            }
        }
        return null;
    }

    public void updateMonthIncome(int year, int month, int value) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                Month cache = overview.get(i);
                cache.setIncome(value);
                overview.set(i, cache);
            }
        }
    }

    public void updateMonthExpense(int year, int month, int value) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                Month cache = overview.get(i);
                cache.setExpense(value);
                overview.set(i, cache);
            }
        }
    }

}
