package com.example.finanzmanager.DataClasses;

import android.util.Log;

import com.example.finanzmanager.activity_classes.MainActivity;

import org.apache.tools.ant.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PositionListTest {
    private Random random;
    private PositionList pl;
    private int year;
    private int month;
    private int day;
    private double d;
    private String category;
    private String description;
    private Date date, q1, q2, q3, q4;

    @Before
    public void setUp() {
        random = new Random();
        year = 1900+random.nextInt(201);
        month = 1+random.nextInt(12);
        day = 1+random.nextInt(31);
        d = (random.nextInt(999999)+0.99);
        category = "cat";
        description = "this is a cat";
        date = mock(Date.class);
        pl = new PositionList();

        // Mocking Attributes
        when(date.getYear()).thenReturn(year);
        when(date.getMonth()).thenReturn(month);
        when(date.getDay()).thenReturn(day);

        q1 = mock(Date.class);
        when(q1.getYear()).thenReturn(2010);
        when(q1.getMonth()).thenReturn(2);
        when(q1.getDay()).thenReturn(10);

        q2 = mock(Date.class);
        when(q2.getYear()).thenReturn(2010);
        when(q2.getMonth()).thenReturn(5);
        when(q2.getDay()).thenReturn(10);

        q3 = mock(Date.class);
        when(q3.getYear()).thenReturn(2010);
        when(q3.getMonth()).thenReturn(8);
        when(q3.getDay()).thenReturn(10);

        q4 = mock(Date.class);
        when(q4.getYear()).thenReturn(2010);
        when(q4.getMonth()).thenReturn(11);
        when(q4.getDay()).thenReturn(10);
    }

    @After
    public void tearDown() {
        pl = null;
    }

    @Test
    public void getIncomeFromDate() {
        assertEquals(0, pl.getIncomeFromDate(month, year).size());

        pl.addIncome(date, d, false, category, description);
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

        pl.addExpense(date, d, false, category, description);
        assertEquals(1, pl.getExpenseFromDate(month, year).size());
        // Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        assertEquals(0, pl.getExpenseFromDate(month2, year).size());
    }

    @Test
    public void addIncome() {
        pl.addIncome(date, d+1, false, category, description);
        pl.addIncome(date, d+2, false, category, description);
        assertTrue(pl.getIncome(1).contains(Double.toString(d+2)));
    }

    @Test
    public void addExpense() {
        pl.addExpense(q2, d+1, false, category, description);
        pl.addExpense(q3, d+2, false, category, description);
        assertTrue(pl.getExpense(1).contains(Double.toString(d+2)));
    }

    @Test
    public void getIncome() {
        pl.addIncome(date, d, false, category, description);
        pl.addIncome(date, d+1, false, category, description);
        pl.addIncome(date, d+2, false, category, description);
        assertTrue(pl.getIncome(2).contains(Double.toString(d+2)));
        try{
            pl.getIncome(3);
        } catch (IndexOutOfBoundsException e){
            assertEquals("Index: 3, Size: 3", e.getMessage());
        }
    }

    @Test
    public void getExpense() {
        pl.addExpense(date, d, false, category, description);
        pl.addExpense(date, d+1, false, category, description);
        pl.addExpense(date, d+2, false, category, description);
        assertTrue(pl.getExpense(2).contains(Double.toString(d+2)));
        try{
            pl.getExpense(3);
        } catch (IndexOutOfBoundsException e){
            assertEquals("Index: 3, Size: 3", e.getMessage());
        }
    }

    @Test
    public void addRepeatingIncome(){
        try{
            pl.addRepeatingIncome(date, d, false, category, description);
        } catch(IllegalArgumentException e){
            assertEquals("must not be false", e.getMessage());
        }
        ArrayList<Income> list = new ArrayList<>();
        int size = list.size();
        pl.addRepeatingIncome(date, d, true, category, description);
        assertEquals(size+1, pl.get_repeatingIncomeList().size());
    }

    @Test
    public void addRepeatingExpense(){
        try{
            pl.addRepeatingExpense(date, d, false, category, description);
        } catch(IllegalArgumentException e){
            assertEquals("must not be false", e.getMessage());
        }
        ArrayList<Expense> list = new ArrayList<>();
        int size = list.size();
        pl.addRepeatingExpense(date, d, true, category, description);
        assertEquals(size+1, pl.get_repeatingExpenseList().size());
    }

    @Test
    public void updateRepeatingIncome(){
        assertEquals(0, pl.updateRepeatingIncome(year+1).size());
        pl.addRepeatingIncome(date, d, true, category, description);
        assertEquals(1, pl.updateRepeatingIncome(year+1).size());
    }

    @Test
    public void updateRepeatingExpense(){
        assertEquals(0, pl.updateRepeatingExpense(year+1).size());
        pl.addRepeatingExpense(date, d, true, category, description);
        assertEquals(1, pl.updateRepeatingExpense(year+1).size());
    }

    @Test
    public void changeRepeatingIncomeList(){
        pl.addRepeatingIncome(date, d, true, category, description);
        pl.changeRepeatingIncomeList(description, d, "newString", d+1);
        assertEquals("newString", pl.get_repeatingIncomeList().get(0).getDescription());
        assertEquals(d+1, pl.get_repeatingIncomeList().get(0).getValue(), 0.001);
    }

    @Test
    public void changeRepeatingExpenseList(){
        pl.addRepeatingExpense(date, d, true, category, description);
        pl.changeRepeatingExpenseList(description, d, "newString", d+1);
        assertEquals("newString", pl.get_repeatingExpenseList().get(0).getDescription());
        assertEquals(d+1, pl.get_repeatingExpenseList().get(0).getValue(), 0.01);
    }

    @Test
    public void getIncomeFromQuarter(){

        pl.addIncome(q1, d, false, "q12345", description);
        pl.addIncome(q2, d, false, "q23451", description);
        pl.addIncome(q3, d, false, "q34512", description);
        pl.addIncome(q4, d, false, "q45123", description);

        assertTrue(pl.getIncomeFromQuarter(1, 2010).get(0).contains("q12345"));
        assertTrue(pl.getIncomeFromQuarter(2, 2010).get(0).contains("q23451"));
        assertTrue(pl.getIncomeFromQuarter(3, 2010).get(0).contains("q34512"));
        assertTrue(pl.getIncomeFromQuarter(4, 2010).get(0).contains("q45123"));

    }

    @Test
    public void getExpenseFromQuarter(){

        pl.addExpense(q1, d, false, "q12345", description);
        pl.addExpense(q2, d, false, "q23451", description);
        pl.addExpense(q3, d, false, "q34512", description);
        pl.addExpense(q4, d, false, "q45123", description);

        assertTrue(pl.getExpenseFromQuarter(1, 2010).get(0).contains("q12345"));
        assertTrue(pl.getExpenseFromQuarter(2, 2010).get(0).contains("q23451"));
        assertTrue(pl.getExpenseFromQuarter(3, 2010).get(0).contains("q34512"));
        assertTrue(pl.getExpenseFromQuarter(4, 2010).get(0).contains("q45123"));
    }

    @Test
    public void getIncomeFromYear(){
        assertNotNull(pl.getIncomeFromYear(year));

    }

    @Test
    public void getExpenseFromYear(){
        assertNotNull(pl.getExpenseFromYear(year));
    }

    @Test
    public void getExpenseFromCategory(){

        pl.addExpense(q1, d, false, "q12345", description);
        pl.addExpense(q2, d+1, false, "q23451", description);
        pl.addExpense(q3, d+2, false, "q34512", description);
        pl.addExpense(q4, d+3, false, "q45123", description);

        assertTrue(pl.getExpenseFromCategory("q12345").get(0).contains(Double.toString(d)));
        assertTrue(pl.getExpenseFromCategory("q23451").get(0).contains(Double.toString(d+1)));
        assertTrue(pl.getExpenseFromCategory("q34512").get(0).contains(Double.toString(d+2)));
        assertTrue(pl.getExpenseFromCategory("q45123").get(0).contains(Double.toString(d+3)));
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

        pl.addRepeatingIncome(q1, d, true, category, description);
        pl.addRepeatingIncome(q2, d+1, true, category, description);
        pl.addRepeatingIncome(q3, d+2, true, category, description);
        pl.addRepeatingIncome(q4, d+3, true, category, description);
        pl.addRepeatingIncome(date, d, true, category, description);

        int before = pl.get_repeatingIncomeList().size();
        pl.deleteRepeatingIncome(description, d);
        int after = pl.get_repeatingIncomeList().size();

        assertEquals(before-1, after);
    }

    @Test
    public void deleteRepeatingExpense(){

        pl.addRepeatingExpense(q1, d, true, category, description);
        pl.addRepeatingExpense(q2, d+1, true, category, description);
        pl.addRepeatingExpense(q3, d+2, true, category, description);
        pl.addRepeatingExpense(q4, d+3, true, category, description);
        pl.addRepeatingExpense(date, d, true, category, description);

        int before = pl.get_repeatingExpenseList().size();
        pl.deleteRepeatingExpense(description, d);
        int after = pl.get_repeatingExpenseList().size();

        assertEquals(before-1, after);
    }

    @Test
    public void getIncomeDate(){
        pl.addIncome(date, d, false, category, description);
        assertTrue(pl.getIncomeDate(0).contains(Double.toString(d)));
    }

    @Test
    public void getExpenseDate(){
        pl.addExpense(date, d, false, category, description);
        assertTrue(pl.getExpenseDate(0).contains(Double.toString(d)));
    }

    @Test
    public void getValueExpense(){
        pl.addExpense(date, d, false, category, description);
        assertEquals(d, pl.getValueExpense(0), 0.001);
    }

    @Test
    public void getIncomeFromCategory(){
        pl.addIncome(q1, d, false, "q12345", description);
        pl.addIncome(q2, d+1, false, "q23451", description);
        pl.addIncome(q3, d+2, false, "q34512", description);
        pl.addIncome(q4, d+3, false, "q45123", description);

        assertTrue(pl.getIncomeFromCategory("q12345").get(0).contains(Double.toString(d)));
        assertTrue(pl.getIncomeFromCategory("q23451").get(0).contains(Double.toString(d+1)));
        assertTrue(pl.getIncomeFromCategory("q34512").get(0).contains(Double.toString(d+2)));
        assertTrue(pl.getIncomeFromCategory("q45123").get(0).contains(Double.toString(d+3)));
    }

    @Test
    public void numOfYear(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1900; i<=2100; i++){
            list.add(i);
        }
        assertEquals(0, pl.numOfYear(list, -1));
        assertEquals(0, pl.numOfYear(list, 1900));
        assertEquals(1, pl.numOfYear(list, 1901));
        assertEquals(200, pl.numOfYear(list, 2100));
        assertEquals(200, pl.numOfYear(list, 2101));
        assertEquals(200, pl.numOfYear(list, 2200));
    }

}