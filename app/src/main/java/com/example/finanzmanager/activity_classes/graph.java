package com.example.finanzmanager.activity_classes;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.finanzmanager.Objects.Month;
import com.example.finanzmanager.R;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class graph extends AppCompatActivity {
    private Month output = MainActivity.months.getMonth(MainActivity.currentYear, MainActivity.currentMonth);
    private double income = 0;
    private double expense = 0;
    private int selctedMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        if (output != null) {
            income = output.getSumIncome();
            expense= output.getSumExpense();
            drawPie();
        }

        Spinner spinnerMonth = findViewById(R.id.spinnerMonthGraph);
        Spinner spinnerYear = findViewById(R.id.spinnerYearGraph);
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
        spinnerMonth.setAdapter(adapter);

        ArrayList<String> year = new ArrayList<>();
        year.add("Jahr");
        for (int i = 1980; i<2050;i++){
            year.add(Integer.toString(i));
        }
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, year);
        spinnerYear.setAdapter(adapter1);

        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
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
                    drawPie();

                }
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    /**
     * draw the Pie chart
     */
    public void drawPie(){


        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView_graph);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();

        //config.addDatas(addElement());

        if (income==0&&expense==0){
            config.startAngle(90)
                    .addData(new SimplePieInfo(50, Color.parseColor("#FF800000"), "Einnahmen"))
                    .addData(new SimplePieInfo(50, Color.parseColor("#FFFF00FF"), "Ausgaben"))
                    .drawText(true).strokeMode(false)
                    .duration(1000).textSize(40);// draw pie animation duration
        }else {
            config.startAngle(-90)//.addDatas(listElement())// Starting angle offset
                    .addData(new SimplePieInfo(income, Color.parseColor("#FF8000"), "Einnahmen"))
                    .addData(new SimplePieInfo(expense, Color.parseColor("#F781F3"), "Ausgaben"))
                    .drawText(true).strokeMode(false)
                    .duration(1000).textSize(40);// draw pie animation duration
        }

        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

    }


    public List listElement(){
        List<Integer> list=new ArrayList<>();
        list.add(12);
        list.add(33);
        list.add(456);
        list.add(7896);
        return list;
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
