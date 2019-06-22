package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.view.MenuItem;
import com.example.finanzmanager.DataClasses.Date;
import com.example.finanzmanager.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.*;
import addNew.income_menu;

import static android.os.Looper.getMainLooper;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @After
    public void tearDown(){
        activity = null;
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(activity);
    }

    @Test
    public void clickOnPlus_shouldStartActivity_income_menu(){
        activity.findViewById(R.id.plus).performClick();
        Intent expectedIntent = new Intent(activity, income_menu.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void clickOnSettings_shouldStartActivity_settings(){
        //TODO Test für Settings Button?
        activity.findViewById(R.id.action_settings);
       /* Intent expectedIntent = new Intent(activity, settings.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
        */
    }

    @Test
    public void setCurrentMonth(){
        activity.setCurrentMonth(0);
        assertNotEquals(-20, MainActivity.currentMonth.intValue());
        activity.setCurrentMonth(13);
        assertNotEquals(1000, MainActivity.currentMonth.intValue());
        activity.setCurrentMonth(1);
        assertEquals(1, MainActivity.currentMonth.intValue());
        activity.setCurrentMonth(12);
        assertEquals(12, MainActivity.currentMonth.intValue());
    }

    @Test
    public void setCurrentYear(){
        activity.setCurrentYear(1899);
        assertNotEquals(1899, MainActivity.currentYear.intValue());
        activity.setCurrentMonth(2101);
        assertNotEquals(2101, MainActivity.currentYear.intValue());
        activity.setCurrentYear(1900);
        assertEquals(1900, MainActivity.currentYear.intValue());
        activity.setCurrentYear(2100);
        assertEquals(2100, MainActivity.currentYear.intValue());
    }

    @Test
    public void checkRepeatingPositionsGetUpdatedInNewYears(){
        Date d1 = mock(Date.class);
        when(d1.getDay()).thenReturn(10);
        when(d1.getMonth()).thenReturn(10);
        when(d1.getYear()).thenReturn(2010);

        Date d2 = mock(Date.class);
        when(d2.getDay()).thenReturn(10);
        when(d2.getMonth()).thenReturn(10);
        when(d2.getYear()).thenReturn(2011);

        Date d3 = mock(Date.class);
        when(d3.getDay()).thenReturn(15);
        when(d3.getMonth()).thenReturn(11);
        when(d3.getYear()).thenReturn(2018);

        activity.addExpenseFromCurrentMonth(d1, 20, "Bar", "neue Bar");
        MainActivity.account.addRepeatingExpense(d1, 20, true, "Bar", "neue Bar");
        assertEquals(3, MainActivity.account.getExpenseFromYear(2010).size());
        shadowOf(getMainLooper()).idle();

        /*
        activity.addIncomeFromCurrentMonth(d2, 20, "Bar", "andere Bar");
        MainActivity.account.addRepeatingIncome(d2, 20, true, "Bar", "andere Bar");

        activity.addExpenseFromCurrentMonth(d3, 500, "Sprit", "für neuen BMW");
        MainActivity.account.addRepeatingExpense(d3, 500, true, "Sprit", "für neuen BMW");

        assertNotEquals(MainActivity.account.getExpenseFromYear());
        activity.addIncomeFromCurrentMonth(d3, 5000, "Sprit", "altes Auto");
        MainActivity.account.addRepeatingIncome(d3, 5000, true, "Sprit", "altes Auto");
*/
    }

    @Test
    public void addIncomeFromCurrentMonth() {
        Date d = new Date(10, 10, 2010);
        double val = 10.10;
        String cat = "cat";
        String desc = "this is a cat";
        activity.addIncomeFromCurrentMonth(d, val, cat, desc);
        // TODO: Besseres assert überlegen
        assertEquals(12, MainActivity.months.fromMonth(2010, 10).size());

    }

    @Test
    public void addExpenseFromCurrentMonth() {
        Date d = new Date(10, 10, 2010);
        double val = 10.10;
        String cat = "cat";
        String desc = "this is a cat";
        MainActivity.months.size();
        activity.addExpenseFromCurrentMonth(d, val, cat, desc);
        // TODO: Besseres assert überlegen
        assertEquals(12, MainActivity.months.size(), 0.01);
    }

    @Test
    public void onBackPressed() {
        activity.onBackPressed();
        // TODO: neuen Test schreiben
    }

    @Test
    public void onCreateOptionsMenu() {
        boolean t = Mockito.anyBoolean();
        //Menu m = mock(Menu.class);
        // TODO: Test ändern
        assertNotNull(t);
        assertNotNull(activity);
    }

    @Test
    public void onOptionsItemSelected() {
        boolean t = Mockito.anyBoolean();
        //Menu m = mock(Menu.class);
        // TODO: Test ändern
        assertNotNull(t);
        assertNotNull(activity);
    }

    @Test
    public void onNavigationItemSelected() {
        MenuItem m = mock(MenuItem.class);
        //activity.onNavigationItemSelected(m);
        // TODO: Test schreiben
    }

    @Test
    public void deleteData() {
        activity.deleteData();
        assertEquals(0, activity.value);
        // TODO: Umschreiben
    }
}