package com.example.finanzmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class neu_ausgabe extends AppCompatActivity {

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    ImageView imgView;
    TextView txtView;
    CheckBox repeat;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neu_ausgabe);

        textView=(TextView) findViewById(R.id.date_ausgabe);
        ImageButton imageButton=(ImageButton) findViewById(R.id.dateButton_ausgabe);

        Intent intent = getIntent();
        int category = intent.getIntExtra("kategorie", 0);
        String extraName2 = intent.getStringExtra("Test");

        repeat = (CheckBox) findViewById(R.id.checkBox_wieder_ausgabe);
        calendar= Calendar.getInstance();

        //Anzeige des richtigen Bildes und Textes
        switch (category) {
            case 1:
                imgView = (ImageView) findViewById(R.id.image_bar);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_bar);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgView = (ImageView) findViewById(R.id.image_abhebung);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_abhebung);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgView = (ImageView) findViewById(R.id.image_kino);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_kino);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgView = (ImageView) findViewById(R.id.image_sprit);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_sprit);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgView = (ImageView) findViewById(R.id.image_fastfood);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_fastfood);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 6:
                imgView = (ImageView) findViewById(R.id.image_lebensmittel);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_lebensmittel);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 7:
                imgView = (ImageView) findViewById(R.id.image_fitnessstudio);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_fitnessstudio);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 8:
                imgView = (ImageView) findViewById(R.id.image_hotel);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_hotel);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 9:
                imgView = (ImageView) findViewById(R.id.image_reisen);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_reisen);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 10:
                imgView = (ImageView) findViewById(R.id.image_extraCatAus);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.text_extraCatAus);
                txtView.setText(extraName2);
                txtView.setVisibility(View.VISIBLE);
                break;
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(neu_ausgabe.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        textView.setText(mDay +"."+(mMonth+1)+"."+mYear);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ImageButton checkButton = (ImageButton) findViewById(R.id.checkButton_ausgabe);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Betrag abspeichern
                EditText add = (EditText) findViewById(R.id.editText_number_ausgabe);
                int addNumber = Integer.parseInt(add.getText().toString());

                //Datum abspeichern
                Integer day = 0;
                Integer month = 0;
                Integer year = 0;
                if (calendar != null) {
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    month = calendar.get(Calendar.MONTH);
                    year = calendar.get(Calendar.YEAR);
                }

                //kategorie speichern
                String category = txtView.getText().toString();

                //Beschreibung speichern
                EditText descriptionText = (EditText) findViewById(R.id.editText_beschreibung_ausgabe);
                String description = descriptionText.getText().toString();

                //wiederkehrend
                Boolean repeats = false;
                if(repeat.isChecked()) repeats = true;

                //alles in einem Bundle gespeichert
                Bundle extras = new Bundle();
                extras.putInt("value", addNumber);
                extras.putInt("day", day);
                extras.putInt("month", month);
                extras.putInt("year", year);
                extras.putString("category", category);
                extras.putString("description", description);
                extras.putBoolean("repeats", repeats);
                extras.putString("type", "expense");

                Intent intent= new Intent(neu_ausgabe.this, MainActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.cancelButton_ausgabe);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Statt neuem Intent wird der alte Intent beendet
                finish();
            }
        });
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
