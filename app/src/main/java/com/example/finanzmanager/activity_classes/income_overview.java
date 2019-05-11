package com.example.finanzmanager.activity_classes;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class income_overview extends AppCompatActivity {

    private int currentYear = 2019;
    private int currentMonth = 4;
    private String currentCategory = "";
    private String currentSort = "";
    private int currentQuarter = 1;
    private ListView listView_income_month, listView_income_category, listView_income_quarter, listView_income_year;
    private TextView textView_cat, textView_date, textView_repeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_overview);

        Spinner spinner_month = findViewById(R.id.spinner_Month_income);
        Spinner spinner_year = findViewById(R.id.spinner_year_income);
        Spinner spinner_category = findViewById(R.id.spinner_category_income);
        Spinner spinner_sort_by = findViewById(R.id.spinner_sortBy_income);
        Spinner spinner_quarter = findViewById(R.id.spinner_quarter_income);
        listView_income_month = findViewById(R.id.listView_all_incomes_month);
        listView_income_category = findViewById(R.id.listView_all_incomes_category);
        listView_income_quarter = findViewById(R.id.listView_all_incomes_quarters);
        listView_income_year = findViewById(R.id.listView_all_incomes_years);
        textView_cat = findViewById(R.id.textView_sort_income_cat);
        textView_date = findViewById(R.id.textView_sort_income_date);
        textView_repeat = findViewById(R.id.textView_sort_income_repeat);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Übersicht Einnahmen </font>"));
        //getSupportActionBar().



        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner_month);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(spinner_year);

            // Set popupWindow height to 800px
            popupWindow.setHeight(800);
            popupWindow1.setHeight(800);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        ArrayList<String> month = new ArrayList<>();
        month.add("Jannuar"); month.add("Februar"); month.add("März");month.add("April"); month.add("Mai");
        month.add("Juni");month.add("Juli"); month.add("August"); month.add("September"); month.add("October");
        month.add("November"); month.add("Dezember");

        // month.add("Letzen 10 Tage"); month.add("Letzer Monat"); month.add("Letzes Halbes Jahr");

        ArrayAdapter<String> adapter_month = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, month);
        adapter_month.setDropDownViewResource(R.layout.spinner);
        spinner_month.setAdapter(adapter_month);

        ArrayAdapter<Integer> adapter_year = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.years);
        adapter_year.setDropDownViewResource(R.layout.spinner);
        spinner_year.setAdapter(adapter_year);

        // Kategorien
        ArrayAdapter<String> adapter_category = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.account.getIncomeCategories());
        adapter_category.setDropDownViewResource(R.layout.spinner);
        spinner_category.setAdapter(adapter_category);

        //Quarter
        ArrayList<Integer> quarters = new ArrayList<>();
        quarters.add(1); quarters.add(2); quarters.add(3); quarters.add(4);
        ArrayAdapter<Integer> adapter_quarter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, quarters);
        adapter_quarter.setDropDownViewResource(R.layout.spinner);
        spinner_quarter.setAdapter(adapter_quarter);

        //Sort By
        ArrayList<String> sort = new ArrayList<>();
        sort.add("Jahr und Monat"); sort.add("Jahr und Quartal"); sort.add("Jahr"); sort.add("Kategorie");

        ArrayAdapter<String> adapter_sort_by = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sort);
        adapter_sort_by.setDropDownViewResource(R.layout.spinner);
        spinner_sort_by.setAdapter(adapter_sort_by);

        spinner_quarter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                if (!(currentQuarter == (Integer) parent.getSelectedItem())) {
                    currentQuarter = (Integer) parent.getSelectedItem();
                    updateQuarterListView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_sort_by.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                if (!(currentSort.equals((String) parent.getSelectedItem()))) {
                    currentSort = (String) parent.getSelectedItem();
                    switch (currentSort) {
                        case ("Jahr und Monat"):
                            listView_income_month.setVisibility(View.VISIBLE);
                            spinner_month.setVisibility(View.VISIBLE);
                            spinner_year.setVisibility(View.VISIBLE);
                            textView_repeat.setVisibility(View.VISIBLE);
                            textView_cat.setVisibility(View.VISIBLE);
                            listView_income_category.setVisibility(View.INVISIBLE);
                            spinner_category.setVisibility(View.INVISIBLE);
                            textView_date.setVisibility(View.INVISIBLE);
                            spinner_quarter.setVisibility(View.INVISIBLE);
                            listView_income_quarter.setVisibility(View.INVISIBLE);
                            listView_income_year.setVisibility(View.INVISIBLE);
                            updateMonthListView();
                            break;
                        case ("Kategorie"):
                            listView_income_category.setVisibility(View.VISIBLE);
                            spinner_category.setVisibility(View.VISIBLE);
                            textView_date.setVisibility(View.VISIBLE);
                            listView_income_month.setVisibility(View.INVISIBLE);
                            spinner_month.setVisibility(View.INVISIBLE);
                            spinner_year.setVisibility(View.INVISIBLE);
                            textView_cat.setVisibility(View.INVISIBLE);
                            textView_repeat.setVisibility(View.INVISIBLE);
                            spinner_quarter.setVisibility(View.INVISIBLE);
                            listView_income_quarter.setVisibility(View.INVISIBLE);
                            listView_income_year.setVisibility(View.INVISIBLE);
                            updateCategoryListView();
                            break;
                        case ("Jahr und Quartal"):
                            listView_income_category.setVisibility(View.INVISIBLE);
                            spinner_category.setVisibility(View.INVISIBLE);
                            textView_date.setVisibility(View.INVISIBLE);
                            listView_income_month.setVisibility(View.VISIBLE);
                            spinner_month.setVisibility(View.INVISIBLE);
                            spinner_year.setVisibility(View.VISIBLE);
                            textView_cat.setVisibility(View.VISIBLE);
                            textView_repeat.setVisibility(View.VISIBLE);
                            spinner_quarter.setVisibility(View.VISIBLE);
                            listView_income_quarter.setVisibility(View.VISIBLE);
                            listView_income_year.setVisibility(View.INVISIBLE);
                            updateQuarterListView();
                            break;
                        case ("Jahr"):
                            listView_income_category.setVisibility(View.INVISIBLE);
                            spinner_category.setVisibility(View.INVISIBLE);
                            textView_date.setVisibility(View.INVISIBLE);
                            listView_income_month.setVisibility(View.INVISIBLE);
                            spinner_month.setVisibility(View.INVISIBLE);
                            spinner_year.setVisibility(View.VISIBLE);
                            textView_cat.setVisibility(View.VISIBLE);
                            textView_repeat.setVisibility(View.VISIBLE);
                            spinner_quarter.setVisibility(View.INVISIBLE);
                            listView_income_quarter.setVisibility(View.INVISIBLE);
                            listView_income_year.setVisibility(View.VISIBLE);
                            updateYearListView();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                if (!(currentCategory.equals((String) parent.getSelectedItem()))) {
                    currentCategory = (String) parent.getSelectedItem();
                    updateCategoryListView();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Aktualisierung wenn neues Jahr ausgewählt wird
        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                if (!(currentYear == (Integer) adapterView.getSelectedItem())) {
                    currentYear = (Integer) adapterView.getSelectedItem();
                    updateMonthListView();
                    updateQuarterListView();
                    updateYearListView();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Aktualisierung wenn neues Monat ausgewählt wird
        spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    updateMonthListView();
                }
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateYearListView() {
        ArrayList<String> stringArrayList_income = MainActivity.account.getIncomeFromYear(currentYear);

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
        listView_income_year.setAdapter(adapter);
    }

    private void updateQuarterListView() {
        ArrayList<String> stringArrayList_income = MainActivity.account.getIncomeFromQuarter(currentQuarter, currentYear);

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
        listView_income_quarter.setAdapter(adapter);
    }

    private void updateCategoryListView() {
        ArrayList<String> stringArrayList_income = MainActivity.account.getIncomeFromCategory(currentCategory);

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
        listView_income_category.setAdapter(adapter);
    }


    private void updateMonthListView() {
        //ListView
        //Einkommen
        //Befüllung aus account
        ArrayList<String> stringArrayList_income = MainActivity.account.getIncomeFromDate(currentMonth, currentYear);

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
        listView_income_month.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
