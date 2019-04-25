package com.example.finanzmanager.Objects;

import android.util.Log;

import com.example.finanzmanager.activity_classes.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Month_overview {

    private ArrayList<Month> overview;

    public Month_overview() {
        overview = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1; i<=12; i++) {
            Month insert = new Month (year, i);
            overview.add(insert);
        }
    }

    public void newMonth(int year, int month) {
        overview.add(new Month(year, month));
    }

    public int size() {
        return overview.size();
    }

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

    public boolean yearExists(int year) {
        for (Month i : overview) {
            if (i.getYear() == year) {
                return true;
            }
        }
        return false;
    }

    public Month getMonth(int year, int month) {
        for (Month i : overview) {
            if ((i.getMonth() == month) && (i.getYear() == year)) {
                return i;
            }
        }
        return null;
    }

    public void updateMonthIncome(int year, int month, double value) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                Month cache = overview.get(i);
                cache.setIncome(value);
                overview.set(i, cache);
            }
        }
    }

    public void updateMonthExpense(int year, int month, double value) {
        for (int i = 0; i<overview.size();i++) {
            if ((overview.get(i).getMonth() == month) && (overview.get(i).getYear() == year)) {
                Month cache = overview.get(i);
                cache.setExpense(value);
                overview.set(i, cache);
            }
        }
    }

    public void newYear(int year) {
        for (int i = 1; i <= 12; i++) {
           Month insert = new Month (year, i);
           overview.add(insert);
        }
    }
}
