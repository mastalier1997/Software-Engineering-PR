package com.example.finanzmanager.Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PositionList {

    private List<Income> incomeList;
    private List<Expense> expenseList;

    public PositionList() {
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
    }

    public ArrayList<String> getIncomeFromDate(int month, int year) {
        ArrayList<String> found = new ArrayList<>();
        for (int i = 0; i<incomeList.size(); i++) {
            if (incomeList.get(i).getDate().getMonth() == month && incomeList.get(i).getDate().getYear() == year) {
                found.add(this.getIncome(i));
            }
        }
        Collections.reverse(found);
        return found;
    }

    public ArrayList<String> getExpenseFromDate(int month, int year) {
        ArrayList<String> found = new ArrayList<>();
        for (int i = 0; i<expenseList.size(); i++) {
            if (expenseList.get(i).getDate().getMonth() == month && expenseList.get(i).getDate().getYear() == year) {
                found.add(this.getExpense(i));
            }
        }
        Collections.reverse(found);
        return found;
    }

    public void addIncome(Date date, double d, boolean recurring, String category, String description) {
        Income e = new Income(date, d, recurring, category, description);
        incomeList.add(e);
    }

    public void addExpense(Date date, double d, boolean recurring, String category, String description) {
        Expense e = new Expense(date, d, recurring, category, description);
        expenseList.add(e);
    }

    public String getIncome(int i) {
        return incomeList.get(i).getInfo();
    }

    public String getExpense(int i) { return expenseList.get(i).getInfo(); }

    public int incomeLength() {
        return incomeList.size();
    }

    public int incomeLengthInMonth (int month, int year) {
        int size = 0;
        for(int i = 0; i<incomeList.size()-1;i++) {
            if (incomeList.get(i).getDate().getMonth() == month && incomeList.get(i).getDate().getYear() == year) size++;
        }
        return size;
    }

    public int expenseLength() {
        return expenseList.size();
    }
    public int expenseLengthInMonth (int month, int year) {
        int size = 0;
        for(int i = 0; i<expenseList.size()-1;i++) {
            if (expenseList.get(i).getDate().getMonth() == month && expenseList.get(i).getDate().getYear() == year) size++;
        }
        return size;
    }


    public void printIncome() {
        System.out.println("Incomen: ");
        Iterator<Income> itr = incomeList.iterator();
        Income print;
        int i = 0;
        while(itr.hasNext()) {
            print = incomeList.get(i);
            print.printIncome();
            itr.next();
            i++;
        }
    }

    public void printExpense() {
        System.out.println("Expensen: ");
        Iterator<Expense> itr = expenseList.iterator();
        Expense print;
        int i = 0;
        while(itr.hasNext()) {
            print = expenseList.get(i);
            print.printExpense();
            itr.next();
            i++;
        }
    }

    public void printIncomeCondition(String name) {
        System.out.println("Incomen: ");
        Iterator<Income> itr = incomeList.iterator();
        Income print;
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
        System.out.println("Expensen: ");
        Iterator<Expense> itr = expenseList.iterator();
        Expense print;
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