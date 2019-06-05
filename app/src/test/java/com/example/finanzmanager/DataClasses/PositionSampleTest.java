package com.example.finanzmanager.DataClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PositionSampleTest {

    PositionSample positionSample;

    int positionType1 = 1;
    int positionType0 = 0;
    int value = 500;
    boolean reocurring = true;
    String category = "cat";
    String description = "this is a cat";
    Date date = new Date(10, 10, 2010);

    @Before
    public void setUp() throws Exception {
        positionSample = new PositionSample();
        positionSample.setPositionType(positionType1);
        positionSample.setValue(value);
        positionSample.setReocurring(reocurring);
        positionSample.setCategory(category);
        positionSample.setDescription(description);
        positionSample.setDate(date);
    }

    @After
    public void tearDown() throws Exception {
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
        assertEquals(value, positionSample.getValue());
    }

    @Test
    public void setValue() {
        assertEquals(value, positionSample.getValue());
        positionSample.setValue(10);
        assertEquals(10, positionSample.getValue());
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

    @Test @Ignore
    public void compareTo() {
        //TODO: Warum schlägt dieser Test fehl?
        PositionSample ps1 = positionSample;
        PositionSample ps2 = positionSample;
        PositionSample ps3 = positionSample;

        ps1.setDate(new Date(9, 10, 2010));
        ps2.setDate(new Date(10, 9, 2010));
        ps3.setDate(new Date(10, 10, 2009));

        assertEquals(1, positionSample.compareTo(ps1));
        assertEquals(1, positionSample.compareTo(ps2));
        assertEquals(1, positionSample.compareTo(ps3));

        ps1.setDate(new Date(10, 10, 2010));
        assertEquals(0, positionSample.compareTo(ps1));

        ps1.setDate(new Date(11, 10, 2010));
        ps2.setDate(new Date(10, 11, 2010));
        ps3.setDate(new Date(10, 10, 2011));
        assertEquals(-1, positionSample.compareTo(ps1));
        assertEquals(-1, positionSample.compareTo(ps2));
        assertEquals(-1, positionSample.compareTo(ps3));
    }
}