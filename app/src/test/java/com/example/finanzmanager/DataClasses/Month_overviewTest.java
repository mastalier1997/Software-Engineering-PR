package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class Month_overviewTest {
    private Random random;
    private MonthOverview m_o;
    private int year;
    private int month;
    private int value;
    // Delta fuer Double-Werte
    private double delta = 0.01;

    @Before
    public void setUp() {
        random = new Random();
        year = 1900+random.nextInt(201);
        month = 1+random.nextInt(12);
        value = random.nextInt(999999);
        m_o = new MonthOverview();
    }

    @After
    public void tearDown() {
        m_o = null;
    }

    @Test
    public void newMonth() {
        m_o.newMonth(year, month);
        Month actual = m_o.getMonth(year, month);
        assertNotNull(actual);
    }

    @Test
    public void size() {
        // Immer 192(15 Jahre x 12 Monate) Monate bei Instanzierung
        assertEquals(192, m_o.size());
        m_o.newMonth(year, month);
        assertEquals(193, m_o.size());
    }

    @Test
    public void getMonth() {
        m_o.newMonth(year, month);
        m_o.newMonth(2000, 12);
        m_o.newMonth(1900, 1);
        // gesuchter Monat enthalten
        assertNotNull(m_o.getMonth(year, month));
        // gesuchter Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        assertNull(m_o.getMonth(2050, month2));
    }

    @Test
    public void updateMonthIncome() {
        m_o.newMonth(year, month);
        // gesuchter Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        m_o.updateMonthIncome(year, month2, value);
        assertEquals( 0, m_o.getMonth(year, month).getSumIncome(), delta);
        // gesuchter Monat enthalten
        m_o.updateMonthIncome(year, month, value);
        assertEquals(value, m_o.getMonth(year, month).getSumIncome(), delta);
    }

    @Test
    public void updateMonthExpense() {
        m_o.newMonth(year, month);
        // gesuchter Monat nicht enthalten
        int month2;
        if(month>=12) month2 = month-1;
        else month2 = month+1;
        m_o.updateMonthExpense(year, month2, value);
        assertEquals( 0, m_o.getMonth(year, month).getSumExpense(), delta);
        // gesuchter Monat enthalten
        m_o.updateMonthExpense(year, month, value);
        assertEquals(value, m_o.getMonth(year, month).getSumExpense(), delta);
    }

    @Test
    public void newYear(){
        int size = m_o.size();
        m_o.newYear(year);
        assertEquals(size+12, m_o.size());
    }

    @Test
    public void fromMonth(){
        // 84 = 7 Jahre x 12 Monate
        assertEquals(84, m_o.fromMonth(2019, 1).size());
        m_o.newMonth(1900, 1);
        // 193 = (15 Jahre x 12 Monate) + 1 added
        assertEquals(193, m_o.fromMonth(1900, 1).size());
        m_o.newMonth(1900 + 1, 12);
        // noch einer geadded
        assertEquals(194, m_o.fromMonth(1900, 1).size());
        m_o.newMonth(2100, 1);
        assertEquals(1, m_o.fromMonth(2100, 1).size());
        m_o.newMonth(2100, 12);
        assertEquals(2, m_o.fromMonth(2100, 1).size());
    }

    @Test
    public void yearExists(){
        assertFalse(m_o.yearExists(1950));
        m_o.newYear(1950);
        assertTrue(m_o.yearExists(1950));
    }
}