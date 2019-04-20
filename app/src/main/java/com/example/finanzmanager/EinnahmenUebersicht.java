package com.example.finanzmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EinnahmenUebersicht extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einnahmen_uebersicht);

        Spinner spinner = findViewById(R.id.spinnerMonth2);
        Spinner spinner1 = findViewById(R.id.spinnerYear2);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);
            android.widget.ListPopupWindow popupWindow1 = (android.widget.ListPopupWindow) popup.get(spinner1);

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
        // month.add("Letzen 10 Tage"); month.add("Letzer Monat"); month.add("Letzes Halbes Jahr");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, month);
        spinner.setAdapter(adapter);

        ArrayList<String> year = new ArrayList<>();
        year.add("Jahr");
        for (int i = 1980; i<2050;i++){
            year.add(Integer.toString(i));
        }
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, year);
        spinner1.setAdapter(adapter1);

        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
