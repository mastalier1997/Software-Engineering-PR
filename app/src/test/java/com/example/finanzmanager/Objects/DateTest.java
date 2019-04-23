package com.example.finanzmanager.Objects;

import java.io.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DateTest {
    private Date date;
    private int day = 10;
    private int month = 10;
    private int year = 2010;

    @Before
    public void setUp() {
        date = new Date(day, month, year);

    }

    @After
    public void tearDown() {
        date = null;
    }

    @Test
    public void constructorTest(){
        date = new Date(day, month , year);
        // compares sum of day, month and year
        assertEquals((day+month+year), (date.getDay()+date.getMonth()+date.getYear()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_day(){
        date = new Date(-10, 10, 2010);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_month(){
        date = new Date(10, -10, 2010);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest_year(){
        date = new Date(10, 10, -2010);
    }

    @Test
    public void printDate() {
        String expected = day + "." + month + "." + year;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        date.printDate();
        assertEquals(expected, outContent.toString());
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