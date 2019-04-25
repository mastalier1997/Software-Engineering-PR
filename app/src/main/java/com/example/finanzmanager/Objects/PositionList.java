package com.example.finanzmanager.Objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PositionList {

    private List<Income> incomeList;
    private List<Expense> expenseList;
    public List<Income> repeatingIncomeList;
    private List<Expense> repeatingExpenseList;

    public PositionList() {
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
        repeatingExpenseList = new ArrayList<>();
        repeatingIncomeList = new ArrayList<>();
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
        Income i = new Income(date, d, recurring, category, description);
        incomeList.add(i);
    }

    public void addExpense(Date date, double d, boolean recurring, String category, String description) {
        Expense e = new Expense(date, d, recurring, category, description);
        expenseList.add(e);
    }

    public void addRepeatingIncome(Date date, double d, boolean recurring, String category, String description) {
        Income i = new Income(date, d, recurring, category, description);
        this.repeatingIncomeList.add(i);
    }

    public void addRepeatingExpense(Date date, double d, boolean recurring, String category, String description) {
        Expense e = new Expense(date, d, recurring, category, description);
        this.repeatingExpenseList.add(e);
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
        for(Income i : incomeList) {
            if (i.getDate().getMonth() == month && i.getDate().getYear() == year) size++;
        }
        return size;
    }

    public int expenseLength() {
        return expenseList.size();
    }
    public int expenseLengthInMonth (int month, int year) {
        int size = 0;
        for(Expense i : expenseList) {
            if (i.getDate().getMonth() == month && i.getDate().getYear() == year) size++;
        }
        return size;
    }


    public void printIncome() {
        System.out.println("Incomen: ");
        Iterator<Income> itr = incomeList.iterator();
        Income print;
        int i = 0;
        while (itr.hasNext()) {
            print = incomeList.get(i);
            print.printIncome();
            itr.next();
            i++;
        }
    }

    public ArrayList<Income> updateRepeatingIncome(int year) {
        ArrayList<Income> affectedIncomes = new ArrayList<>();
        Log.e("New Year:", Integer.toString(repeatingIncomeList.size()));

        Log.e("New Year2:", Integer.toString(year));
        for (Income i : repeatingIncomeList) {
            if (i.getDate().getYear()<year) affectedIncomes.add(i);
        }
        return affectedIncomes;
    }

    public ArrayList<Expense> updateRepeatingExpense(int year) {
        ArrayList<Expense> affectedExpenses = new ArrayList<>();
        for (Expense e : repeatingExpenseList) {
            if (e.getDate().getYear()<year) affectedExpenses.add(e);
        }
        return affectedExpenses;
    }
}