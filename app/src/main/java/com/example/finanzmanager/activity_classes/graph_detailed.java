package com.example.finanzmanager.activity_classes;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.Objects.Income;
import com.example.finanzmanager.Objects.PositionList;
import com.example.finanzmanager.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class graph_detailed extends AppCompatActivity {

    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    private int selctedMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_detailed);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Graph</font>"));

        Spinner spinnerMonth = findViewById(R.id.spinnerMonthGraph2);
        Spinner spinnerYear = findViewById(R.id.spinnerYearGraph2);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinnerMonth);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(spinnerYear);

            // Set popupWindow height to 800px
            popupWindow.setHeight(800);
            popupWindow1.setHeight(800);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        ArrayList<String> month = new ArrayList<>();
        month.add("Monat");month.add("Jänner"); month.add("Februar"); month.add("März");month.add("April"); month.add("Mai");
        month.add("Juni");month.add("Juli"); month.add("August"); month.add("September"); month.add("October");
        month.add("November"); month.add("Dezember");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, month);
        adapter.setDropDownViewResource(R.layout.spinner);

        spinnerMonth.setAdapter(adapter);

        ArrayList<String> year = new ArrayList<>();
        year.add("Jahr");
        for (int i = 1980; i<2050;i++){
            year.add(Integer.toString(i));
        }
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, year);
        spinnerYear.setAdapter(adapter1);
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            selctedMonth = 1;
                            MainActivity.setCurrentMonth(1);
                            break;
                        case "Februar":
                            selctedMonth = 2;
                            MainActivity.setCurrentMonth(2);
                            break;
                        case "März":
                            selctedMonth = 3;
                            MainActivity.setCurrentMonth(3);
                            break;
                        case "April":
                            selctedMonth = 4;
                            MainActivity.setCurrentMonth(4);
                            break;
                        case "Mai":
                            selctedMonth = 5;
                            break;
                        case "Juni":
                            selctedMonth = 6;
                            break;
                        case "Juli":
                            selctedMonth = 7;
                            break;
                        case "August":
                            selctedMonth = 8;
                            break;
                        case "September":
                            selctedMonth = 9;
                            break;
                        case "Oktober":
                            selctedMonth = 10;
                            break;
                        case "November":
                            selctedMonth = 11;
                            break;
                        case "Dezember":
                            selctedMonth = 12;
                            break;
                        default:
                            selctedMonth =0;
                    }
                    /*updateListView();
                    saveEditor.putInt("currentMonth", selctedMonth);
                    saveEditor.commit();*/
                    pieChart.animateY(3000);

                }
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);


    }

    public static PositionList positionList;

    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(2f, 0));
        /*entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(7f, 4));
        entries.add(new BarEntry(3f, 5));*/
        /*ArrayList a = positionList.getExpenseValueFromDate(4,2019);
        for (int i=0; i<a.size();i++){
            entries.add(new BarEntry(2f,i));
        }*/

    }

    public void AddValuesToPieEntryLabels(){
       // ArrayList a = positionList.getExpenseValueFromDate(4,2019);
        //PieEntryLabels.add(""+a.get(0)+"");
        PieEntryLabels.add("February");
        PieEntryLabels.add("March");
        PieEntryLabels.add("April");
        PieEntryLabels.add("May");
        PieEntryLabels.add("June");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
