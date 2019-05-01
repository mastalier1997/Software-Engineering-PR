package com.example.finanzmanager.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class Month_overviewTest {
    private Month_overview m_o;
    private int year = 2010;
    private int month = 10;
    // Wert fuer updateMonthExpense & updateMonthIncome
    private int value = 100;
    // Delta fuer Double-Werte
    private double delta = 0.01;

    @Before
    public void setUp() {
        m_o = new Month_overview();
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
        // Immer 12 Monate bei Instanzierung
        assertEquals(12, m_o.size());
        m_o.newMonth(year, month);
        assertEquals(13, m_o.size());
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
        assertNull(m_o.getMonth(year, month2));
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
        ArrayList<Month> arrayList =  m_o.fromMonth(year, month);
        assertEquals(m_o.size(), arrayList.size());
        m_o.newYear(year);
        arrayList =  m_o.fromMonth(year, month);
        assertEquals(m_o.size()-(month-1), arrayList.size());
    }

    @Test
    public void yearExists(){
        assertFalse(m_o.yearExists(year));
        m_o.newYear(year);
        assertTrue(m_o.yearExists(year));
    }
}