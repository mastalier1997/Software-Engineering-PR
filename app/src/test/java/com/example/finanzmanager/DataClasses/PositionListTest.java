package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

// TODO: In PositionList neu hinzugefügte Methoden testen unnd
//  Testklasse auf 100% Code coverage bringen!
public class PositionListTest {
    private PositionList pl;
    private int year = 2010;
    private int month = 10;
    private double d = 10.10;
    private boolean recurring = false;
    private String category = "cat";
    private String description = "this is a cat";
    private Date date = new Date(10, month, year);

    @Before
    public void setUp() {
        pl = new PositionList();

    }

    @After
    public void tearDown() {
        pl = null;
    }

    @Test
    public void getIncomeFromDate() {
        assertEquals(0, pl.getIncomeFromDate(month, year).size());

        pl.addIncome(date, d, recurring, category, description);
        assertEquals(1, pl.getIncomeFromDate(month, year).size());
        // Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        assertEquals(0, pl.getIncomeFromDate(month2, year).size());
    }

    @Test
    public void getExpenseFromDate() {
        assertEquals(0, pl.getExpenseFromDate(month, year).size());

        pl.addExpense(date, d, recurring, category, description);
        assertEquals(1, pl.getExpenseFromDate(month, year).size());
        // Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        assertEquals(0, pl.getExpenseFromDate(month2, year).size());
    }

    @Test
    public void addIncome() {
        pl.addIncome(date, d, recurring, category, description);
        pl.addIncome(date, d+1, recurring, category, description);
        pl.addIncome(date, d+2, recurring, category, description);
        assertEquals(3, pl.getIncomeFromDate(month, year).size());
    }

    @Test
    public void addExpense() {
        pl.addExpense(date, d, recurring, category, description);
        pl.addExpense(date, d+1, recurring, category, description);
        pl.addExpense(date, d+2, recurring, category, description);
        assertEquals(3, pl.getExpenseFromDate(month, year).size());
    }

    @Test
    public void getIncome() {
        pl.addIncome(date, d, recurring, category, description);
        pl.addIncome(date, d+1, recurring, category, description);
        pl.addIncome(date, d+2, recurring, category, description);
        assertNotNull(pl.getIncome(2));
       // assertNull(pl.getIncome(4));
    }

    @Test
    public void getExpense() {
        pl.addExpense(date, d, recurring, category, description);
        pl.addExpense(date, d+1, recurring, category, description);
        pl.addExpense(date, d+2, recurring, category, description);
        assertNotNull(pl.getExpense(2));
        // assertNull(pl.getExpense(4));
    }

    @Test
    public void addRepeatingIncome(){
        ArrayList<Income> list = new ArrayList<>();
        int size = list.size();
        pl.addRepeatingIncome(date, d, true, category, description);
        assertEquals(size+1, pl.get_repeatingIncomeList().size());
    }

    @Test
    public void addRepeatingExpense(){
        ArrayList<Expense> list = new ArrayList<>();
        int size = list.size();
        pl.addRepeatingExpense(date, d, true, category, description);
        assertEquals(size+1, pl.get_repeatingExpenseList().size());
    }

    @Test
    public void updateRepeatingIncome(){
        pl.addRepeatingIncome(date, d, recurring, category, description);
        assertEquals(0, pl.updateRepeatingIncome(year).size());
    }

    @Test
    public void updateRepeatingExpense(){
        pl.addRepeatingExpense(date, d, recurring, category, description);
        assertEquals(0, pl.updateRepeatingExpense(year).size());
    }

    @Test
    public void changeRepeatingIncomeList(){
        pl.addRepeatingIncome(date, d, recurring, category, description);
        pl.changeRepeatingIncomeList(description, d, "newString", 9.10);
        assertEquals("newString", pl.get_repeatingIncomeList().get(0).getDescription());
    }

    @Test
    public void changeRepeatingExpenseList(){
        pl.changeRepeatingExpenseList(description, d, "newString", 9.10);
        assertEquals(0, pl.get_repeatingExpenseList().size());
    }

    @Test
    public void getIncomeFromQuarter(){
        ArrayList<String> al = pl.getIncomeFromQuarter(1, 2010);
        assertNotNull(al.toArray());
        al = pl.getIncomeFromQuarter(2, 2010);
        assertNotNull(al.toArray());
        al = pl.getIncomeFromQuarter(3, 2010);
        assertNotNull(al.toArray());
        al = pl.getIncomeFromQuarter(4, 2010);
        assertNotNull(al.toArray());

    }

    @Test
    public void getExpenseFromQuarter(){
        ArrayList<String> al = pl.getExpenseFromQuarter(1, 2010);
        assertNotNull(al.toArray());
        al = pl.getIncomeFromQuarter(2, 2010);
        assertNotNull(al.toArray());
        al = pl.getIncomeFromQuarter(3, 2010);
        assertNotNull(al.toArray());
        al = pl.getIncomeFromQuarter(4, 2010);
        assertNotNull(al.toArray());

    }

    @Test
    public void getIncomeFromYear(){
        ArrayList<String> l = pl.getIncomeFromYear(2010);
        assertNotNull(l);
    }

    @Test
    public void getExpenseFromYear(){
        ArrayList<String> l = pl.getExpenseFromYear(2010);
        assertNotNull(l);
    }

    @Test
    public void getExpenseFromCategory(){
        ArrayList<String> l = pl.getExpenseFromCategory(category);
        assertNotNull(l);
    }

    @Test
    public void getIncomeCategories(){
        assertNotNull(pl.getIncomeCategories());
    }

    @Test
    public void getExpenseCategories(){
        assertNotNull(pl.getExpenseCategories());
    }

    @Test
    public void deleteRepeatingIncome(){
        pl.addRepeatingIncome(date, d, recurring, category, description);
        assertEquals(d, pl.get_repeatingIncomeList().get(0).getValue(), 0.01);
        pl.deleteRepeatingIncome(description, d);
        assertTrue(pl.get_repeatingIncomeList().isEmpty());
    }

    @Test
    public void deleteRepeatingExpense(){
        pl.addRepeatingExpense(date, d, recurring, category, description);
        assertEquals(d, pl.get_repeatingExpenseList().get(0).getValue(), 0.01);
        pl.deleteRepeatingExpense(description, d);
        assertTrue(pl.get_repeatingExpenseList().isEmpty());
    }

    @Test
    public void getIncomeDate(){
        pl.addIncome(date, d, recurring, category, description);
        String s = pl.getIncomeDate(0);
        assertEquals(s, pl.getIncomeDate(0));
    }

    @Test
    public void getExpenseDate(){
        pl.addExpense(date, d, recurring, category, description);
        String s = pl.getExpenseDate(0);
        assertEquals(s, pl.getExpenseDate(0));
    }

    @Test
    public void getValueExpense(){
        pl.addExpense(date, d, recurring, category, description);
        assertEquals(d, pl.getValueExpense(0), 0.01);
    }



}