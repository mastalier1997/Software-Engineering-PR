package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.DataClasses.Date;
import com.example.finanzmanager.DataClasses.Month;
import com.example.finanzmanager.DataClasses.Month_overview;
import com.example.finanzmanager.DataClasses.PositionList;
import com.example.finanzmanager.R;
import com.example.finanzmanager.DataClasses.Income;
import com.example.finanzmanager.DataClasses.Expense;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.lang.Double;
import java.util.List;

import addNew.income_menu;
import navigationBar.expense_overview;
import navigationBar.exports;
import navigationBar.graph;
import navigationBar.imports;
import navigationBar.income_overview;
import settings.settings;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //zwei objekte für die Speicherung am Smartphone
    private static SharedPreferences savePreference;
    public static SharedPreferences.Editor saveEditor;

    //alle Variablen, welche am Smartphone gespeicher werden
    public static PositionList account;
    public static Month_overview months;
    static public List<Integer> years;
    public static Integer currentYear;
    public static Integer currentMonth;

    //sonstige Variablen
    int value = 0;
    String description = "";
    String category = "";
    int day, month, year = 0;
    Boolean repeat = false;

    TextView sumIncome;
    TextView sumExpense;
    TextView balance;

    private ListView listview_income, listView_expense;
    private ArrayAdapter<String> stringArrayAdapter_income, stringArrayAdapter_expense;
    private ArrayList<String> stringArrayList_income, stringArrayList_expense;

    public String Income, Expense;
    private int backwards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Finanzmanger </font>"));

        /*AdView adView = findViewById(R.id.adView);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");*/

        // sumIncome und sumExpense für die Ausgabe
        sumIncome = (TextView) findViewById(R.id.textView_sumIncome);
        sumExpense = (TextView) findViewById(R.id.textView_sumExpense);
        balance = (TextView) findViewById(R.id.textView_balance);
        listview_income = (ListView) findViewById(R.id.listView_income);
        listView_expense = (ListView) findViewById(R.id.listView_expense);

        //Datenspeicherung

        savePreference = PreferenceManager.getDefaultSharedPreferences(this);
        saveEditor = savePreference.edit();

        //Einlese der Daten bei jedem Start der App bzw. Neuaufruf der Startseite
        checkSharedPreferences();

        Log.e("year", Integer.toString(currentMonth) + "." + Integer.toString(currentYear));
        updateListView();

        //DropDown für Jahre
        Spinner dropdown_year = findViewById(R.id.dropDown_year);
        if (years.isEmpty()) years.add(Calendar.getInstance().get(Calendar.YEAR));
        Collections.sort(years);
        Collections.reverse(years);
        ArrayAdapter<Integer> adapter_year = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, years);
        adapter_year.setDropDownViewResource(R.layout.spinner);
        dropdown_year.setAdapter(adapter_year);
        dropdown_year.setSelection(numOfYear(years, currentYear));


        //Aktualisierung wenn neues Jahr ausgewählt wird
        dropdown_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                ((TextView) adapterView.getChildAt(0)).setTypeface(type);
                if (!(currentYear == (Integer) adapterView.getSelectedItem())) {
                    currentYear = (Integer) adapterView.getSelectedItem();
                    saveEditor.putInt("currentYear", currentYear);
                    saveEditor.commit();
                    updateListView();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //DropDown für Monate
        Spinner dropdown_month = findViewById(R.id.dropDown_month);
        try{
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(dropdown_month);

            // Set popupWindow height to 600px
            popupWindow.setHeight(600);
        }catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e){
            //no action
        }
        String[] items = new String[]{"Jannuar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
        ArrayAdapter<String> adapter_month = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_month.setDropDownViewResource(R.layout.spinner);
        dropdown_month.setAdapter(adapter_month);
        dropdown_month.setSelection(currentMonth-1);


        //Aktualisierung wenn neues Monat ausgewählt wird
        dropdown_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                ((TextView) adapterView.getChildAt(0)).setTypeface(type);
                if (((adapterView.getSelectedItem().toString().equals("")))) {
                    //do nothing
                } else {
                    String index = (String) adapterView.getSelectedItem();
                    switch (index) {
                        case "Jannuar":
                            currentMonth = 1;
                            break;
                        case "Februar":
                            currentMonth = 2;
                            break;
                        case "März":
                            currentMonth = 3;
                            break;
                        case "April":
                            currentMonth = 4;
                            break;
                        case "Mai":
                            currentMonth = 5;
                            break;
                        case "Juni":
                            currentMonth = 6;
                            break;
                        case "Juli":
                            currentMonth = 7;
                            break;
                        case "August":
                            currentMonth = 8;
                            break;
                        case "September":
                            currentMonth = 9;
                            break;
                        case "Oktober":
                            currentMonth = 10;
                            break;
                        case "November":
                            currentMonth = 11;
                            break;
                        case "Dezember":
                            currentMonth = 12;
                            break;
                        default:
                            currentMonth = 0;
                    }
                    updateListView();
                    saveEditor.putInt("currentMonth", currentMonth);
                    saveEditor.commit();
                }
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // je nachdem von welcher klasse man kommt wird unterschiedliches erstellt
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            if(bundle.getString("type").equals("income")) { // neue Einnahme

                value = bundle.getInt("value");
                description = bundle.getString("description");
                category = bundle.getString("category");
                repeat = bundle.getBoolean("repeats");
                day = bundle.getInt("day");
                month = bundle.getInt("month");
                year = bundle.getInt("year");
                Date date = new Date(day, month, year);

                if(months.yearExists(year) == false) {
                    months.newYear(year);
                    updateRepeating(year); //falls ein neues Jahr eingefügt wird, werden alle sich wiederholenden Position eingefügt
                }

                if(repeat == false) {
                    account.addIncome(date, value, repeat, category, description);
                    months.updateMonthIncome(year, month, value);
                } else {
                    addIncomeFromCurrentMonth(date, value, category, description);
                    account.addRepeatingIncome(date, value, true, category, description);
                }

                if (!years.contains(year)) { years.add(year); }

            } else if (bundle.getString("type").equals("expense")){ //neue Ausgabe
                value = bundle.getInt("value");
                description = bundle.getString("description");
                category = bundle.getString("category");
                repeat = bundle.getBoolean("repeats");
                day = bundle.getInt("day");
                month = bundle.getInt("month");
                year = bundle.getInt("year");
                Date date = new Date(day, month, year);


                if(months.yearExists(year) == false) {
                    months.newYear(year);
                    updateRepeating(year); //falls ein neues Jahr eingefügt wird, werden alle sich wiederholenden Position eingefügt
                }

                if(repeat == false) {
                    account.addExpense(date, value, repeat, category, description);
                    months.updateMonthExpense(year, month, value);
                } else {
                    addExpenseFromCurrentMonth(date, value, category, description);
                    account.addRepeatingExpense(date, value, true, category, description);

                }

                if (!years.contains(year)) { years.add(year); }
            }
            Gson gson = new Gson();

            String account_json = gson.toJson(account);
            String months_json = gson.toJson(months);
            String years_json = gson.toJson(years);
            saveEditor.putString("months", months_json);
            saveEditor.putString("years", years_json);
            saveEditor.putString("account", account_json);
            saveEditor.commit();
        }
        Collections.sort(years, Collections.<Integer>reverseOrder());


        //Plus Button Weiterleitung
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, income_menu.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static void setCurrentMonth(Integer currentMonth) {
        MainActivity.currentMonth = currentMonth;
        saveEditor.putInt("currentMonth", currentMonth);
        saveEditor.commit();
    }

    public static void setCurrentYear(Integer currentYear) {
        MainActivity.currentYear = currentYear;
        saveEditor.putInt("currentYear", currentYear);
        saveEditor.commit();
    }

    /**
     * safes the current state of the account and months variable to the shared preferences
     */
    public static void saveToAccount() {
        Gson gson = new Gson();
        String account_json = gson.toJson(account);
        String months_json = gson.toJson(months);
        saveEditor.putString("account", account_json);
        saveEditor.putString("months", months_json);
        saveEditor.commit();
    }

    /**
     * when a new year is added, all repeating positions are updated
     * @param year
     */
    private void updateRepeating(int year) {
        ArrayList<Income> incomes = account.updateRepeatingIncome(year);
        ArrayList<Expense> expenses = account.updateRepeatingExpense(year);
        Date date = new Date(1, 1, year);
        int counter = 1;
        for (Income i : incomes) {
            addIncomeFromCurrentMonth(date, i.getValue(), i.getCategory(), i.getDescription());
            counter++;
        }
        for (Expense e : expenses) {
            addExpenseFromCurrentMonth(date, e.getValue(), e.getCategory(), e.getDescription());
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
        ArrayList<Month> from = months.fromMonth(year, month);
        for (Month i : from) {
            Date currentDate = new Date(15, i.getMonth(), i.getYear());
            account.addIncome(currentDate, value, true, category, description);
            months.updateMonthIncome(i.getYear(), i.getMonth(), value);
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
        ArrayList<Month> from = months.fromMonth(year, month);
        for (Month i : from) {
            Date currentDate = new Date(15, i.getMonth(), i.getYear());
            account.addExpense(currentDate, value, true, category, description);
            months.updateMonthExpense(i.getYear(), i.getMonth(), value);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.e("Test", "der Settings Listener wurde aufgerufen");
            Intent intent = new Intent(MainActivity.this, settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_income) {
            Intent intent = new Intent(MainActivity.this, income_overview.class);
            startActivity(intent);
        } else if (id == R.id.nav_expense) {
            Intent intent = new Intent(MainActivity.this, expense_overview.class);
            startActivity(intent);
        } else if (id == R.id.nav_graph) {
            Intent intent= new Intent(MainActivity.this, graph.class);
            startActivity(intent);
        } else if (id == R.id.nav_export) {
            Intent intent = new Intent(MainActivity.this, exports.class);
            startActivity(intent);
        } else if (id == R.id.nav_import) {
            Intent intent = new Intent(MainActivity.this, imports.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * update the list views for incomes and expenses
     */
    public void updateListView() {
        //ListView
        //Einkommen
        //Befüllung aus account
        stringArrayList_income = account.getIncomeFromDate(currentMonth, currentYear);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, R.layout.listview, R.id.textView_list_white, stringArrayList_income){

            //Farbe der Elemente in der Liste ändern
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                return view;
            }
        };
        /*SET THE ADAPTER TO LISTVIEW*/
        listview_income.setAdapter(adapter);

        //Ausgaben
        //Befüllung aus account
        stringArrayList_expense = account.getExpenseFromDate(currentMonth, currentYear);


        //Ausgabe in ListView
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(
                getApplicationContext(), R.layout.listview, R.id.textView_list_white, stringArrayList_expense){

            //Farbe der Elemente in der Liste ändern
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                return view;
            }
        };
        listView_expense.setAdapter(adapter1);

        //Aktualisierung der Zahlen und Positionsliste
        if (months.size() != 0) {
            Month output = months.getMonth(currentYear, currentMonth);
            if (output != null) {
                String Income = Double.toString(output.getSumIncome());
                String Expense = Double.toString(output.getSumExpense());
                String Balance = Double.toString(output.getBalance());

                sumIncome.setText(Income);
                sumExpense.setText(Expense);
                balance.setText(Balance);
            } else {
                sumIncome.setText("0");
                sumExpense.setText("0");
                balance.setText("0");
            }
        }
    }

    /**
     * clears the shared preference file
     */
    public static void deleteData() {
        saveEditor.clear();
        saveEditor.commit();
    }

    /**
     * reads the shared preference file and declares the variables
     */
    private void checkSharedPreferences() {
        Gson gson = new Gson();

        String account_json = savePreference.getString("account", "");
        if (!account_json.equals("")) {
            PositionList account_saved = gson.fromJson(account_json, PositionList.class);
            account = account_saved;
        } else {
            account = new PositionList();
        }


        String months_json = savePreference.getString("months", "");
        if (!months_json.equals("")) {
            Month_overview months_saved = gson.fromJson(months_json, Month_overview.class);
            months = months_saved;
        } else {
            months = new Month_overview();
        }


        //ArrayList benötigt einen eigenen Json, da
        String years_json = savePreference.getString("years", "");
        if (!years_json.equals("")) {
            List<Double> years_saved_double = gson.fromJson(years_json, ArrayList.class);
            List<Integer> years_saved_int = new ArrayList<>();
            for (Double i : years_saved_double) {
                Double output = i;
                Integer input = output.intValue();
                years_saved_int.add(input);
            }
            years = years_saved_int;
        } else {
            years = new ArrayList<Integer>();
        }

        int year = savePreference.getInt("currentYear", -1);

        if (year != -1) {
            currentYear = year;
        } else {
            currentYear = 2019;
        }


        int month = savePreference.getInt("currentMonth", -1);
        if (month != -1) {
            currentMonth = month;
        } else {
            currentMonth = 1;
        }

    }

    public int numOfYear(List<Integer> years, Integer search) {
        int counter = 0;
        for (Integer y : years) {
            Log.e("year", Integer.toString(y.intValue()) + " -- " + Integer.toString(search));
            if(y.intValue() == search) return counter;
            counter++;
        }
        return counter;
    }

}
