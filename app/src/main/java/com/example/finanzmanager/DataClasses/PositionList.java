package com.example.finanzmanager.DataClasses;

import android.util.Log;

import com.example.finanzmanager.activity_classes.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class PositionList {

    private List<Income> incomeList;
    public List<Expense> expenseList;
    private ArrayList<Income> repeatingIncomeList;
    private ArrayList<Expense> repeatingExpenseList;
    public ArrayList<String> categoriesIncome;
    public ArrayList<String> categoriesExpense;

    public PositionList() {
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
        repeatingExpenseList = new ArrayList<>();
        repeatingIncomeList = new ArrayList<>();
        categoriesIncome = new ArrayList<>();
        categoriesIncome.add("Prämie");
        categoriesIncome.add("Investition");
        categoriesIncome.add("Gehalt");
        categoriesIncome.add("Dividenden");
        categoriesIncome.add("Glücksspiel");
        categoriesIncome.add("Rückerstattung");

        categoriesExpense = new ArrayList<>();
        categoriesExpense.add("Bar");
        categoriesExpense.add("Abhebung");
        categoriesExpense.add("Kino");
        categoriesExpense.add("Sprit");
        categoriesExpense.add("FastFood");
        categoriesExpense.add("Lebensmittel");
        categoriesExpense.add("Fitnessstudio");
        categoriesExpense.add("Hotel");
        categoriesExpense.add("Reisen");
    }

    public ArrayList<String> getIncomeFromQuarter(int quarter, int year) {
        int [] months = new int[3];
        switch(quarter) {
            case (1):
                months[0] = 1; months[1] = 2; months[2] = 3;
                break;
            case (2):
                months[0] = 4; months[1] = 5; months[2] = 6;
                break;
            case (3):
                months[0] = 7; months[1] = 8; months[2] = 9;
                break;
            case (4):
                months[0] = 10; months[1] = 11; months[2] = 12;
                break;
        }
        ArrayList<String> found = new ArrayList<>();
        for(int i = 0; i <=2; i++) {
            found.addAll(this.getIncomeFromDate(months[i], year));
        }
        return found;
    }

    public ArrayList<String> getExpenseFromQuarter(int quarter, int year) {
        int [] months = new int[3];
        switch(quarter) {
            case (1):
                months[0] = 1; months[1] = 2; months[2] = 3;
                break;
            case (2):
                months[0] = 4; months[1] = 5; months[2] = 6;
                break;
            case (3):
                months[0] = 7; months[1] = 8; months[2] = 9;
                break;
            case (4):
                months[0] = 10; months[1] = 11; months[2] = 12;
                break;
        }
        ArrayList<String> found = new ArrayList<>();
        for(int i = 0; i <=2; i++) {
            found.addAll(this.getExpenseFromDate(months[i], year));
        }
        return found;
    }

    public ArrayList<String> getIncomeFromYear(int year) {
        ArrayList<String> found = new ArrayList<>();
        for(int i = 0; i <=12; i++) {
            found.addAll(this.getIncomeFromDate(i, year));
        }
        return found;
    }

    public ArrayList<String> getExpenseFromYear(int year) {
        ArrayList<String> found = new ArrayList<>();
        for(int i = 0; i <=12; i++) {
            found.addAll(this.getIncomeFromDate(i, year));
        }
        return found;
    }

    public ArrayList<String> getIncomeFromCategory(String category) {
        ArrayList<String> found = new ArrayList<>();
        for (int i = 0; i<incomeList.size(); i++) {
            if (incomeList.get(i).getCategory().equals(category)) {
                found.add(this.getIncomeDate(i));
            }
        }
        Collections.reverse(found);
        return found;
    }

    public ArrayList<String> getExpenseFromCategory(String category) {
        ArrayList<String> found = new ArrayList<>();
        for (int i = 0; i<expenseList.size(); i++) {
            if (expenseList.get(i).getCategory().equals(category)) {
                found.add(this.getExpenseDate(i));
            }
        }
        Collections.reverse(found);
        return found;
    }

    public ArrayList<String> getIncomeCategories() {
        return categoriesIncome;
    }

    public ArrayList<String> getExpenseCategories() {
        return categoriesExpense;
    }

    public void deleteRepeatingIncome(String description, double value) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        //Annahme: ein Paar aus Beschreibung und Betrag kommt nur einmal vor
        for(int i = 0; i < repeatingIncomeList.size(); i++) {
            Income test = repeatingIncomeList.get(i);
            if (test.getDescription().equals(description) && test.getValue() == value) {
                repeatingIncomeList.remove(i);
                break;
            }
        }

        for(int i = 0; i<incomeList.size(); i++) {
            Income test = incomeList.get(i);
            if (test.getDescription().equals(description) && test.getValue() == value && test.getDate().getYear() >= year) {
                Log.e("Schleifentest:", "Vor der IF");
                if ((test.getDate().getYear() == year && test.getDate().getMonth() >= month) || test.getDate().getYear() > year) {
                    Log.e("Schleifentest:", "Nach der IF");
                    incomeList.remove(i);
                    i--; //notwendig da das i+1 element auf die Stelle i rückt und somit im nächsten Schleifendurchgang übersprungen wird
                    MainActivity.months.updateMonthIncome(test.getDate().getYear(), test.getDate().getMonth(), -value);
                }
            }
        }
    }

    public void deleteRepeatingExpense(String description, double value) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        //Annahme: ein Paar aus Beschreibung und Betrag kommt nur einmal vor
        for(int i = 0; i < repeatingExpenseList.size(); i++) {
            Expense test = repeatingExpenseList.get(i);
            if (test.getDescription().equals(description) && test.getValue() == value) {
                repeatingExpenseList.remove(i);
                break;
            }
        }

        for(int i = 0; i<incomeList.size(); i++) {
            Expense test = expenseList.get(i);
            if (test.getDescription().equals(description) && test.getValue() == value && test.getDate().getYear() >= year) {
                if ((test.getDate().getYear() == year && test.getDate().getMonth() >= month) || test.getDate().getYear() > year) {
                    expenseList.remove(i);
                    i--; //notwendig da das i+1 element auf die Stelle i rückt und somit im nächsten Schleifendurchgang übersprungen wird
                    MainActivity.months.updateMonthExpense(test.getDate().getYear(), test.getDate().getMonth(), -value);
                }
            }
        }
    }


    /**
     * changes the value and the description from a repeating Income
     * -> in the list where all repeating incomes are saved
     * -> in the list where all incomes are saved and the targeted repeating income occurs multiple times
     * @param old_desc
     * @param old_value
     * @param new_desc
     * @param new_value
     */
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

    /**
     * changes the value and the description from a repeating Expense
     * -> in the list where all repeating expenses are saved
     * -> in the list where all expenses are saved and the targeted repeating expense occurs multiple times
     * @param old_desc
     * @param old_value
     * @param new_desc
     * @param new_value
     */
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

    /**
     * @return repeatingIncomeList
     */
    public ArrayList<Income> get_repeatingIncomeList() {
        return repeatingIncomeList;
    }

    /**
     * @return repeatingExpenseList
     */
    public ArrayList<Expense> get_repeatingExpenseList() {
        return repeatingExpenseList;
    }

    /**
     * returns all Incomes from a month
     * @param month
     * @param year
     * @return income list
     */
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

    /**
     * returns all Expenses from a month
     * @param month
     * @param year
     * @return expense list
     */
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

    public ArrayList<Double> getExpenseValueFromDate(int month, int year) {
        ArrayList<Double> found = new ArrayList<>();
        for (int i = 0; i<expenseList.size(); i++) {
            if (expenseList.get(i).getDate().getMonth() == month && expenseList.get(i).getDate().getYear() == year) {
                found.add(this.getValueExpense(i));
            }
        }
        Collections.reverse(found);
        return found;
    }

    /**
     * adds a new Income to the list
     * @param date
     * @param value
     * @param recurring
     * @param category
     * @param description
     */
    public void addIncome(Date date, double value, boolean recurring, String category, String description) {
        Income i = new Income(date, value, recurring, category, description);
        incomeList.add(i);
    }

    /**
     * adds a new Expense to the list
     * @param date
     * @param value
     * @param recurring
     * @param category
     * @param description
     */
    public void addExpense(Date date, double value, boolean recurring, String category, String description) {
        Expense e = new Expense(date, value, recurring, category, description);
        expenseList.add(e);
    }

    /**
     * adds a new repeating Income to the list
     * @param date
     * @param value
     * @param recurring
     * @param category
     * @param description
     */
    public void addRepeatingIncome(Date date, double value, boolean recurring, String category, String description) {
        Income i = new Income(date, value, recurring, category, description);
        this.repeatingIncomeList.add(i);
    }

    /**
     * adds a new repeating Expense to the list
     * @param date
     * @param value
     * @param recurring
     * @param category
     * @param description
     */
    public void addRepeatingExpense(Date date, double value, boolean recurring, String category, String description) {
        Expense e = new Expense(date, value, recurring, category, description);
        this.repeatingExpenseList.add(e);
    }

    /**
     * returns the income at an index
     * @param i
     * @return income
     */
    public String getIncome(int i) {
        return incomeList.get(i).getInfo();
    }

    /**
     * returns the expense at an index
     * @param i
     * @return expense
     */
    public String getExpense(int i) { return expenseList.get(i).getInfo(); }

    /**
     * returns the income at an index
     * @param i
     * @return income
     */
    public String getIncomeDate(int i) {
        return incomeList.get(i).getInfoDate();
    }

    /**
     * returns the expense at an index
     * @param i
     * @return expense
     */
    public String getExpenseDate(int i) { return expenseList.get(i).getInfoDate(); }

    public double getValueExpense(int i){ return  expenseList.get(i).getValue();}

    /**
     * returns the number of incomes in total
     */
    public int incomeLength() {
        return incomeList.size();
    }



    /**
     * returns the number of incomes in a month
     * @param month
     * @param year
     * @return number
     */
    public int incomeLengthInMonth (int month, int year) {
        int size = 0;
        for(Income i : incomeList) {
            if (i.getDate().getMonth() == month && i.getDate().getYear() == year) size++;
        }
        return size;
    }

    /**
     * returns the number of expenses in total
     */
    public int expenseLength() {
        return expenseList.size();
    }

    /**
     * returns the number of expense in a month
     * @param month
     * @param year
     */
    public int expenseLengthInMonth (int month, int year) {
        int size = 0;
        for(Expense i : expenseList) {
            if (i.getDate().getMonth() == month && i.getDate().getYear() == year) size++;
        }
        return size;
    }

    /**
     * returns all repeating incomes where the year of the starting date is smaller then the
     * @param year
     * @return income list
     */
    public ArrayList<Income> updateRepeatingIncome(int year) {
        ArrayList<Income> affectedIncomes = new ArrayList<>();
        for (Income i : repeatingIncomeList) {
            if (i.getDate().getYear()<year) affectedIncomes.add(i);
        }
        return affectedIncomes;
    }

    /**
     * returns all repeating expenses where the year of the starting date is smaller then the
     * @param year
     * @return expense list
     */
    public ArrayList<Expense> updateRepeatingExpense(int year) {
        ArrayList<Expense> affectedExpenses = new ArrayList<>();
        for (Expense e : repeatingExpenseList) {
            if (e.getDate().getYear()<year) affectedExpenses.add(e);
        }
        return affectedExpenses;
    }
}