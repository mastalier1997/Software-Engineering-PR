package com.example.finanzmanager.Objects;

import android.util.Log;

import com.example.finanzmanager.activity_classes.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PositionList {

    private List<Income> incomeList;
    private List<Expense> expenseList;
    public ArrayList<Income> repeatingIncomeList;
    private ArrayList<Expense> repeatingExpenseList;

    public PositionList() {
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
        repeatingExpenseList = new ArrayList<>();
        repeatingIncomeList = new ArrayList<>();
    }

    public void changeRepeatingIncomeList(String old_desc, double old_value, String new_desc, double new_value) {
        double difference = new_value-old_value;
        //Annahme: ein Paar aus Beschreibung und Betrag kommt nur einmal vor
        for(int i = 0; i < repeatingIncomeList.size(); i++) {
            Income test = repeatingIncomeList.get(i);
            if (test.getDescription().equals(old_desc) && test.getValue() == old_value) {
                Income replace = new Income(test.getDate(), new_value, test.getRecurring(), test.getCategory(), new_desc);
                repeatingIncomeList.set(i, replace);
                break;
            }
        }

        for(int i = 0; i<incomeList.size(); i++) {
            Income test = incomeList.get(i);
            if (test.getDescription().equals(old_desc) && test.getValue() == old_value) {
                Income replace = new Income(test.getDate(), new_value, test.getRecurring(), test.getCategory(), new_desc);
                incomeList.set(i, replace);
                MainActivity.months.updateMonthIncome(test.getDate().getYear(), test.getDate().getMonth(), difference);
            }
        }
    }

    public void changeRepeatingExpenseList(String old_desc, double old_value, String new_desc, double new_value) {
        double difference = new_value-old_value;
        //Annahme: ein Paar aus Beschreibung und Betrag kommt nur einmal vor
        for(int i = 0; i < repeatingExpenseList.size(); i++) {
            Expense test = repeatingExpenseList.get(i);
            if (test.getDescription().equals(old_desc) && test.getValue() == old_value) {
                Expense replace = new Expense(test.getDate(), new_value, test.getRecurring(), test.getCategory(), new_desc);
                repeatingExpenseList.set(i, replace);
                break;
            }
        }

        for(int i = 0; i<expenseList.size(); i++) {
            Expense test = expenseList.get(i);
            if (test.getDescription().equals(old_desc) && test.getValue() == old_value) {
                Expense replace = new Expense(test.getDate(), new_value, test.getRecurring(), test.getCategory(), new_desc);
                expenseList.set(i, replace);
                MainActivity.months.updateMonthExpense(test.getDate().getYear(), test.getDate().getMonth(), difference);
            }
        }
    }

    public ArrayList<Income> get_repeatingIncomeList() {
        return repeatingIncomeList;
    }

    public ArrayList<Expense> get_repeatingExpenseList() {
        return repeatingExpenseList;
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