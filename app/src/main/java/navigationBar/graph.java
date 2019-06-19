package navigationBar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.DataClasses.Month;
import com.example.finanzmanager.DataClasses.Month_overview;
import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.MainActivity;
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
    private int selectedMonth;
    private  int selectedYear;
    private static SharedPreferences.Editor saveEditor;
    private MainActivity mainActivity;
    public static Month_overview months;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_graph);
        Log.e("Jahr und Monat", Integer.toString(MainActivity.currentYear) + Integer.toString(MainActivity.currentMonth) );
        Log.e("test", "test");
        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Schrift in der Status Bar angeben
        getSupportActionBar().setTitle(Html.fromHtml("<font color='"+getResources().getColor(R.color.colorAccent)+"'>Graph </font>"));



        if (output != null) {
            income = output.getSumIncome();
            expense= output.getSumExpense();
            drawPie(income, expense);
        }

        //Auswahl des Jahres und Monats
        Log.e("test", "test");
        Spinner spinnerMonth = findViewById(R.id.spinnerMonthGraph);
        Spinner spinnerYear = findViewById(R.id.spinnerYearGraph);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinnerMonth);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(spinnerYear);

            popupWindow.setHeight(800);
            popupWindow1.setHeight(800);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {

        }

        Log.e("test", "test");
        ArrayList<String> month = new ArrayList<>();
        month.add("Jannuar"); month.add("Februar"); month.add("März");month.add("April"); month.add("Mai");
        month.add("Juni");month.add("Juli"); month.add("August"); month.add("September"); month.add("Oktober");
        month.add("November"); month.add("Dezember");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, month);
        adapter.setDropDownViewResource(R.layout.spinner);
        spinnerMonth.setAdapter(adapter);
        spinnerMonth.setSelection(MainActivity.currentMonth-1);

        List<Integer> year = MainActivity.years;

        Log.e("test", "test");
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, year);
        adapter1.setDropDownViewResource(R.layout.spinner);
        spinnerYear.setAdapter(adapter1);
        spinnerYear.setSelection(this.numOfYear(year, MainActivity.currentYear));

        Log.e("test", "test");
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                ((TextView) adapterView.getChildAt(0)).setTypeface(type);
                if (!(selectedYear == (Integer) adapterView.getSelectedItem())) {
                    selectedYear = (Integer) adapterView.getSelectedItem();
                    MainActivity.setCurrentYear(selectedYear);
                    output = MainActivity.months.getMonth(MainActivity.currentYear, MainActivity.currentMonth);
                    income = output.getSumIncome();
                    expense= output.getSumExpense();
                    drawPie(income, expense);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Log.e("test", "test");
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
                            selectedMonth = 1;
                            break;
                        case "Februar":
                            selectedMonth = 2;
                            break;
                        case "März":
                            selectedMonth = 3;
                            break;
                        case "April":
                            selectedMonth = 4;
                            break;
                        case "Mai":
                            selectedMonth = 5;
                            break;
                        case "Juni":
                            selectedMonth = 6;
                            break;
                        case "Juli":
                            selectedMonth = 7;
                            break;
                        case "August":
                            selectedMonth = 8;
                            break;
                        case "September":
                            selectedMonth = 9;
                            break;
                        case "Oktober":
                            selectedMonth = 10;
                            break;
                        case "November":
                            selectedMonth = 11;
                            break;
                        case "Dezember":
                            selectedMonth = 12;
                            break;
                        default:
                            selectedMonth =0;
                    }
                    mainActivity.setCurrentMonth(selectedMonth);
                    output = MainActivity.months.getMonth(MainActivity.currentYear, MainActivity.currentMonth);
                    income = output.getSumIncome();
                    expense= output.getSumExpense();
                    drawPie(income, expense);
                }
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        Log.e("test", "test");
       /* Button detailViewButton = (Button) findViewById(R.id.detailedView);
        detailViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(graph.this, graph_detailed.class);
                startActivity(intent);
            }
        });*/
    }

    /**
     * zeichnet Graph
     */
    public void drawPie(double income, double expense){

        Log.e("test", "drawing");
        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView_graph);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();


        if (income==0&&expense==0){
            config.startAngle(90)
                    .addData(new SimplePieInfo(50, Color.parseColor("#4CAF50"), "Einnahmen"))
                    .addData(new SimplePieInfo(50, Color.parseColor("#DC3939"), "Ausgaben"))
                    //.drawText(true).strokeMode(false)
                    .autoSize(true)
                    .duration(1000).textSize(40);// draw pie animation duration
        }else {
            config.startAngle(-90)//.addDatas(listElement())// Starting angle offset
                    .addData(new SimplePieInfo(income, Color.parseColor("#4CAF50"), "Einnahmen"))
                    .addData(new SimplePieInfo(expense, Color.parseColor("#DC3939"), "Ausgaben"))
                    //.drawText(true).strokeMode(false)
                    .autoSize(true)
                    .duration(1000).textSize(40);// draw pie animation duration
        }

        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

    }


    /**
     * Gibt Jahr zurück
     * @param years
     * @param search
     * @return
     */
    public int numOfYear(List<Integer> years, Integer search) {
        int counter = 0;
        for (Integer y : years) {
            if(y == search) return counter;
            counter++;
        }
        return counter;
    }


    /**
     * Zurück Button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }

        return super.onOptionsItemSelected(item);
    }
}
