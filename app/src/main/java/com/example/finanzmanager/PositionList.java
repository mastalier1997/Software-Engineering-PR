package com.example.finanzmanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PositionList {

    List<Einnahme> incomeList;
    List<Ausgabe> expenseList;

    public PositionList() {
        incomeList = new ArrayList<Einnahme>();
        expenseList = new ArrayList<Ausgabe>();
    }

    public void addIncome(Date date, double d, boolean recurring, String category, String description) {
        Einnahme e = new Einnahme(date, d, recurring, category, description);
        incomeList.add(e);
    }

    public void addExpense(Date date, double d, boolean recurring, String category, String description) {
        Ausgabe e = new Ausgabe(date, d, recurring, category, description);
        expenseList.add(e);
    }


    public void printIncome() {
        System.out.println("Einnahmen: ");
        Iterator<Einnahme> itr = incomeList.iterator();
        Einnahme print;
        int i = 0;
        while(itr.hasNext()) {
            print = incomeList.get(i);
            print.printIncome();
            itr.next();
            i++;
        }
    }

    public void printExpense() {
        System.out.println("Ausgaben: ");
        Iterator<Ausgabe> itr = expenseList.iterator();
        Ausgabe print;
        int i = 0;
        while(itr.hasNext()) {
            print = expenseList.get(i);
            print.printExpense();
            itr.next();
            i++;
        }
    }

    public void printIncomeCondition(String name) {
        System.out.println("Einnahmen: ");
        Iterator<Einnahme> itr = incomeList.iterator();
        Einnahme print;
        int i = 0;
        while(itr.hasNext()) {
            if (incomeList.get(i).getcategory().equals(name)) {
                print = incomeList.get(i);
                print.printIncome();
            }
            itr.next();
            i++;
        }
    }

    public void printExpenseCondition(String name) {
        System.out.println("Ausgaben: ");
        Iterator<Ausgabe> itr = expenseList.iterator();
        Ausgabe print;
        int i = 0;
        while(itr.hasNext()) {
            if (expenseList.get(i).getcategory().equals(name)) {
                print = expenseList.get(i);
                print.printExpense();
            }
            itr.next();
            i++;
        }
    }



}