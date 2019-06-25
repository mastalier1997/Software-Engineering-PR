package com.example.finanzmanager.DataClasses;

import com.example.finanzmanager.ActivityClasses.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class PositionList {

    public List<Income> incomeList;
    public List<Expense> expenseList;
    public ArrayList<Income> repeatingIncomeList;
    public ArrayList<Expense> repeatingExpenseList;
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

    public boolean repeatingIncomeContains(String description, double value) {
        for (Income i : repeatingIncomeList) {
            if (i.getDescription().equals(description) && (int) i.getValue() == (int) value) return true;
        }
        return false;
    }

    public boolean repeatingExpenseContains(String description, double value) {
        for (Expense e : repeatingExpenseList) {
            if (e.getDescription().equals(description) && (int) e.getValue() == (int) value) return true;
        }
        return false;
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

    public ArrayList<String> getIncomeCategories() { return categoriesIncome;
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
                //Log.e("Schleifentest:", "Vor der IF");
                if ((test.getDate().getYear() == year && test.getDate().getMonth() >= month) || test.getDate().getYear() > year) {
                    //Log.e("Schleifentest:", "Nach der IF");
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
        addNewIncomeCategory(category);
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
        addNewExpenseCategory(category);
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
        if(!recurring)
            throw new IllegalArgumentException("must not be false");
        Income i = new Income(date, value, recurring, category, description);
        this.repeatingIncomeList.add(i);
        addNewIncomeCategory(category);
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
        if(!recurring)
            throw new IllegalArgumentException("must not be false");
        Expense e = new Expense(date, value, recurring, category, description);
        this.repeatingExpenseList.add(e);
        addNewExpenseCategory(category);
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
    public String getExpense(int i) {
        return expenseList.get(i).getInfo(); }

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

    /**
     * checks if the expense-category is new or already in use
     * --> adds it if true
     * @param cat the checked category
     * @return true if new, false otherwise
     */
    public boolean addNewExpenseCategory(String cat){
        if(!(categoriesExpense.contains(cat))){
            categoriesExpense.add(cat);
            categoriesExpense.sort(String::compareTo);
            return true;
        }
        return false;
    }


    /**
     * checks if the income-category is new or already in use
     * --> adds it if true
     * @param cat the checked category
     * @return true if new, false otherwise
     */
    public boolean addNewIncomeCategory(String cat){
        if(!(categoriesIncome.contains(cat))){
            categoriesIncome.add(cat);
            categoriesIncome.sort(String::compareTo);
            return true;
        }
        return false;
    }


    /********************************
    *********************************
    *** METHODEN AUS MainActivity ***
    *********************************
    *********************************/

    /**
     * when a new year is added, all repeating positions are updated
     * @param year
     *
     */
    public void updateRepeating(int year) {
        ArrayList<Income> incomes = updateRepeatingIncome(year);
        ArrayList<Expense> expenses = updateRepeatingExpense(year);
        Date date = new Date(1, 1, year);
        int counter = 1;
        for (Income i : incomes) {
            MainActivity.account.addIncomeFromCurrentMonth(date, i.getValue(), i.getCategory(), i.getDescription());
            counter++;
        }
        for (Expense e : expenses) {
            MainActivity.account.addExpenseFromCurrentMonth(date, e.getValue(), e.getCategory(), e.getDescription());
        }
    }

    /**
     * A new repeating Income gets added to all following months
     * @param date
     * @param value
     * @param category
     * @param description
     */
    //bei wiederkehrendem Einkommen wird die erfasste Einnahme in alle bisher erstellten zukünftigen Monate eingefügt
    public void addIncomeFromCurrentMonth(Date date, double value, String category, String description) {
        int month = date.getMonth();
        int year = date.getYear();
        ArrayList<Month> from = MainActivity.months.fromMonth(year, month);
        for (Month i : from) {
            Date currentDate = new Date(15, i.getMonth(), i.getYear());
            addIncome(currentDate, value, true, category, description);
            MainActivity.months.updateMonthIncome(i.getYear(), i.getMonth(), value);
        }
    }

    /**
     * A new repeating Expense gets added to all following months
     * @param date
     * @param value
     * @param category
     * @param description
     */
    //bei wiederkehrendem Ausgaben wird die erfasste Ausgabe in alle bisher erstellten zukünftigen Monate eingefügt
    public void addExpenseFromCurrentMonth(Date date, double value, String category, String description) {
        int month = date.getMonth();
        int year = date.getYear();
        ArrayList<Month> from = MainActivity.months.fromMonth(year, month);
        for (Month i : from) {
            Date currentDate = new Date(15, i.getMonth(), i.getYear());
            addExpense(currentDate, value, true, category, description);
            MainActivity.months.updateMonthExpense(i.getYear(), i.getMonth(), value);
        }
    }

    public int numOfYear(List<Integer> years, Integer search) {
        if(search < 1900)
            return 0;

        int counter = 0;
        for (Integer y : years) {
          //Log.e("year", Integer.toString(y.intValue()) + " -- " + Integer.toString(search));
            if(y.intValue() == search) return counter;
            counter++;
        }
        return counter-1;
    }

    /**************************************
     **************************************
     *** METHODEN AUS MainActivity ENDE ***
     **************************************
     **************************************/

}