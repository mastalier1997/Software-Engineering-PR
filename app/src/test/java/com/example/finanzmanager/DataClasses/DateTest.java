package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Random;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DateTest {
    private Date date;
    private int day;
    private int month;
    private int year;
    private Random random;

    @Before
    public void setUp() {
        random = new Random();
        day = 1+random.nextInt(31);
        month = 1+random.nextInt(12);
        year = 1900+random.nextInt(201);
        date = new Date(day, month, year);
    }

    @After
    public void tearDown() {
        date = null;
    }

    @Test
    public void constructorTest(){
        date = new Date(day, month, year);
        // Vergleich der Datums-Summen
        assertEquals((day+month+year), (date.getDay()+date.getMonth()+date.getYear()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_day(){
        date = new Date(32, month, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_day2(){
        date = new Date(-10, month, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_month(){
        date = new Date(day, 13, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_month2(){

        date = new Date(day, -10, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_year(){
        date = new Date(day, month, 2101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_year2(){
        date = new Date(day, month, -2010);
    }

    @Test
    public void getString(){
        StringBuffer sb = new StringBuffer();
        sb.append(day);
        sb.append(".");
        sb.append(month);
        sb.append(".");
        sb.append(year);
        String expected = sb.toString();

        String actual = date.getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getDay() {
        int actual = date.getDay();
        assertEquals(day, actual);
    }

    @Test
    public void getMonth() {
        int actual = date.getMonth();
        assertEquals(month, actual);
    }

    @Test
    public void getYear() {
        int actual = date.getYear();
        assertEquals(year, actual);
    }
}