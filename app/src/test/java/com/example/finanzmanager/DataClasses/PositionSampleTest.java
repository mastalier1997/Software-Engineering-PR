package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class PositionSampleTest {

    PositionSample positionSample;
    int positionType1 = 1;
    int positionType0 = 0;
    double value = 500.0;
    boolean reocurring = true;
    String category = "cat";
    String description = "this is a cat";
    Date date;

    @Before
    public void setUp(){
        date = mock(Date.class);
        when(date.getDay()).thenReturn(10);
        when(date.getMonth()).thenReturn(10);
        when(date.getYear()).thenReturn(2010);

        positionSample = new PositionSample();
        positionSample.setPositionType(positionType1);
        positionSample.setValue(value);
        positionSample.setReocurring(reocurring);
        positionSample.setCategory(category);
        positionSample.setDescription(description);
        positionSample.setDate(date);
    }

    @After
    public void tearDown() {
        positionSample = null;
    }

    @Test
    public void getCategory() {
        assertEquals(category, positionSample.getCategory());
    }

    @Test
    public void setCategory() {
        positionSample.setCategory("new cat");
        assertEquals("new cat", positionSample.getCategory());
    }

    @Test
    public void getDescription() {
        assertEquals(description, positionSample.getDescription());
    }

    @Test
    public void setDescription() {
        positionSample.setDescription("new description");
        assertEquals("new description", positionSample.getDescription());
    }

    @Test
    public void getPositionType() {
        assertEquals(positionType1, positionSample.getPositionType());
        positionSample.setPositionType(positionType0);
        assertEquals(positionType0, positionSample.getPositionType());
    }

    @Test
    public void setPositionType() {
        assertEquals(positionType1, positionSample.getPositionType());
        positionSample.setPositionType(positionType0);
        assertEquals(positionType0, positionSample.getPositionType());
    }

    @Test
    public void getDate() {
        assertEquals(date, positionSample.getDate());
    }

    @Test
    public void setDate() {
        positionSample.setDate(new Date(10, 10, 2010));
        assertEquals(10, positionSample.getDate().getDay());
        assertEquals(10, positionSample.getDate().getMonth());
        assertEquals(2010, positionSample.getDate().getYear());
    }

    @Test
    public void getValue() {
        assertEquals(value, positionSample.getValue(), 0.001);
    }

    @Test
    public void setValue() {
        assertEquals(value, positionSample.getValue(), 0.001);
        positionSample.setValue(10);
        assertEquals(10, positionSample.getValue(), 0.001);
    }

    @Test
    public void getReocurring() {
        assertEquals(reocurring, positionSample.getReocurring());
    }

    @Test
    public void setReocurring() {
        assertEquals(reocurring, positionSample.getReocurring());
        positionSample.setReocurring(!reocurring);
        assertEquals(!reocurring, positionSample.getReocurring());

    }

    @Test
    public void toStringTest() {
        String expected = "PositionSample{" +
                "positionType=" + positionType1 +
                ", date=" + date +
                ", value=" + value +
                ", reocurring=" + reocurring +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
        assertEquals(expected, positionSample.toString());
    }

    @Test
    public void readyToExport() {
        String[] s = new String[8];
        s[0] = String.valueOf(positionType1);
        s[1] = String.valueOf(date.getDay());
        s[2] = String.valueOf(date.getMonth());
        s[3] = String.valueOf(date.getYear());
        s[4] = String.valueOf(value);
        s[5] = ((Boolean)reocurring).toString();
        s[6] = category;
        s[7] = description;

        assertArrayEquals(s, positionSample.readyToExport());
    }

    @Test
    public void compareTo() {

        // Nicht Instanz von PositionSample
        assertEquals(-1, positionSample.compareTo(date));

        // Mocking fuer Date
        Date date2 = mock(Date.class);
        when(date2.getDay()).thenReturn(9);
        when(date2.getMonth()).thenReturn(10);
        when(date2.getYear()).thenReturn(2010);

        Date date3 = mock(Date.class);
        when(date3.getDay()).thenReturn(10);
        when(date3.getMonth()).thenReturn(9);
        when(date3.getYear()).thenReturn(2010);

        Date date4 = mock(Date.class);
        when(date4.getDay()).thenReturn(10);
        when(date4.getMonth()).thenReturn(10);
        when(date4.getYear()).thenReturn(2009);

        // PositionSample-Instanzen zum Vergleichen
        PositionSample ps1 = new PositionSample();
        ps1.setDescription(description);
        ps1.setCategory(category);
        ps1.setDate(date2);
        ps1.setPositionType(positionType0);
        ps1.setReocurring(reocurring);
        ps1.setValue(value);

        PositionSample ps2 = new PositionSample();
        ps2.setDescription(description);
        ps2.setCategory(category);
        ps2.setDate(date3);
        ps2.setPositionType(positionType0);
        ps2.setReocurring(reocurring);
        ps2.setValue(value);

        PositionSample ps3 = new PositionSample();
        ps3.setDescription(description);
        ps3.setCategory(category);
        ps3.setDate(date4);
        ps3.setPositionType(positionType0);
        ps3.setReocurring(reocurring);
        ps3.setValue(value);

        // Vergleiche >
        assertEquals(1, positionSample.compareTo(ps1));
        assertEquals(1, positionSample.compareTo(ps2));
        assertEquals(1, positionSample.compareTo(ps3));

        // Vergleiche =
        ps1.setDate(date);
        assertEquals(0, positionSample.compareTo(ps1));
        ps1.setDate(date2);

        // Vergleiche <
        assertEquals(-1, ps1.compareTo(positionSample));
        assertEquals(-1, ps2.compareTo(positionSample));
        assertEquals(-1, ps3.compareTo(positionSample));
    }
}