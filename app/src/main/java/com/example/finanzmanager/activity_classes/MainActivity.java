package com.example.finanzmanager.activity_classes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.Objects.Date;
import com.example.finanzmanager.Objects.Month;
import com.example.finanzmanager.Objects.Month_overview;
import com.example.finanzmanager.Objects.PositionList;
import com.example.finanzmanager.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Double;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //zwei objekte für die Speicherung am Smartphone
    private SharedPreferences savePreference;
    private SharedPreferences.Editor saveEditor;

    //alle Variablen, welche am Smartphone gespeicher werden
    static PositionList account;
    static Month_overview months;
    static private List<Integer> years;
    static Integer currentYear;
    static Integer currentMonth;

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

        // sumIncome und sumExpense für die Ausgabe
        sumIncome = (TextView) findViewById(R.id.textView_sumIncome);
        sumExpense = (TextView) findViewById(R.id.textView_sumExpense);
        balance = (TextView) findViewById(R.id.textView_balance);
        listview_income = (ListView) findViewById(R.id.listView_income);
        listView_expense = (ListView) findViewById(R.id.listView_expense);

        //Datenspeicherung

        //savePreference = PreferenceManager.getDefaultSharedPreferences(this);
        //saveEditor = savePreference.edit();

        // WICHTIG !!!!!!!!!!!
        // Wenn ihr die Daten löschen wollts und die App auf null setzten wollts einfach die zwei Zeilen
        // Code darunter entkommentieren - die App ausführen - und die Zeilen wieder als Kommentar markieren

        saveEditor.clear();
        saveEditor.commit();

        //Einlese der Daten bei jedem Start der App bzw. Neuaufruf der Startseite
        checkSharedPreferences();

        //DropDown für Jahre
        Spinner dropdown_year = findViewById(R.id.dropDown_year);
        if (years.isEmpty()) years.add(2019);
        final ArrayAdapter<Integer> adapter_year = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, years);
        dropdown_year.setAdapter(adapter_year);


        //Aktualisierung wenn neues Jahr ausgewählt wird
        dropdown_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.e("Jahr Current", Integer.toString(currentYear));
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

            // Set popupWindow height to 800px
            popupWindow.setHeight(600);
        }catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e){
            //no action
        }
        String[] items = new String[]{"Jannuar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
        ArrayAdapter<String> adapter_month = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_month.setAdapter(adapter_month);
        dropdown_month.setSelection(currentMonth-1);


        //Aktualisierung wenn neues Monat ausgewählt wird
        dropdown_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
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
                account.addIncome(date, value, repeat, category, description);

                if(months.getMonth(year, month) == null) {
                    months.newMonth(year, month);
                    months.updateMonthIncome(year, month, value);
                } else {
                    months.updateMonthIncome(year, month, value);
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
                account.addExpense(date, value, repeat, category, description);

                if(months.getMonth(year, month) == null) {
                    months.newMonth(year, month);
                    months.updateMonthExpense(year, month, value);
                } else {
                    months.updateMonthExpense(year, month, value);
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
            return true;
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
            Intent intent= new Intent(MainActivity.this, Graph.class);
            startActivity(intent);
        } else if (id == R.id.nav_export) {
            Intent intent = new Intent(MainActivity.this, Export.class);
            startActivity(intent);
        } else if (id == R.id.nav_import) {
            Intent intent = new Intent(MainActivity.this, Import.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateListView() {
        //ListView
        //Einkommen
        //Befüllung aus account
        stringArrayList_income = account.getIncomeFromDate(currentMonth, currentYear);

        //Ausgabe in ListView
        stringArrayAdapter_income = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,stringArrayList_income);
        listview_income.setAdapter(stringArrayAdapter_income);

        //Ausgaben
        //Befüllung aus account
        stringArrayList_expense = account.getExpenseFromDate(currentMonth, currentYear);


        //Ausgabe in ListView
        stringArrayAdapter_expense = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,stringArrayList_expense);
        listView_expense.setAdapter(stringArrayAdapter_expense);

        //Aktualisierung der Zahlen und Positionsliste
        if (months.size() != 0) {
            Month output = months.getMonth(currentYear, currentMonth);
            if (output != null) {
                String Income = Integer.toString(output.getSumIncome());
                String Expense = Integer.toString(output.getSumExpense());
                String Balance = Integer.toString(output.getBalance());

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
        Log.e("years_json:", years_json);
        if (!years_json.equals("")) {
            List<Double> years_saved_double = gson.fromJson(years_json, ArrayList.class);
            List<Integer> years_saved_int = new ArrayList<>();
            for (int i = 0; i<years_saved_double.size();i++) {
                Double output = years_saved_double.get(i);
                Integer input = output.intValue();
                years_saved_int.add(input);
            }
            years = years_saved_int;
        } else {
            years = new ArrayList<Integer>();
        }

        int year = savePreference.getInt("currentYear", -1);
        Log.e("Jahr:", Integer.toString(year));
        if (year != -1) {
            currentYear = year;
        } else {
            currentYear = 2019;
        }


        int month = savePreference.getInt("currentMonth", -1);
        if (month != -1) {
            currentMonth = month;
        } else {
            currentMonth = 0;
        }
    }
}
