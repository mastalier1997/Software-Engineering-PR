package com.example.finanzmanager.activity_classes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finanzmanager.R;

import java.util.Calendar;

public class new_expense extends AppCompatActivity {

    DatePickerDialog datePickerDialog;

    ImageView imgView;
    TextView txtView;
    CheckBox repeat;
    TextView textView;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    // Speicherung der eigenen Kategorie
    static String extraName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);


        textView=(TextView) findViewById(R.id.textView_date_expense);
        ImageButton imageButton=(ImageButton) findViewById(R.id.Button_date_expense);

        Intent intent = getIntent();
        int category = intent.getIntExtra("kategorie", 0);
        extraName3 = intent.getStringExtra("zwei");

        repeat = (CheckBox) findViewById(R.id.checkBox_repeat_expense);



        //Anzeige des richtigen Bildes und Textes
        switch (category) {
            case 1:
                imgView = (ImageView) findViewById(R.id.imageView_cash);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_cash);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 2:
                imgView = (ImageView) findViewById(R.id.imageView_atm);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_atm);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 3:
                imgView = (ImageView) findViewById(R.id.imageView_cinema);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_cinema);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 4:
                imgView = (ImageView) findViewById(R.id.imageView_gas);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_gas);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 5:
                imgView = (ImageView) findViewById(R.id.imageView_fastFood);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_fastFood);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 6:
                imgView = (ImageView) findViewById(R.id.imageView_groceries);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_groceries);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 7:
                imgView = (ImageView) findViewById(R.id.imageView_fitnessStudio);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_fitnessStudio);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 8:
                imgView = (ImageView) findViewById(R.id.imageView_hotel);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_hotel);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 9:
                imgView = (ImageView) findViewById(R.id.imageView_travel);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_travel);
                txtView.setVisibility(View.VISIBLE);
                break;
            case 10:
                imgView = (ImageView) findViewById(R.id.imageView_extraCat_expense);
                imgView.setVisibility(View.VISIBLE);
                txtView = (TextView) findViewById(R.id.textView_extraCat_expense);
                txtView.setText(extraName3);
                txtView.setVisibility(View.VISIBLE);
                break;
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(new_expense.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        textView.setText(mDay +"."+(mMonth+1)+"."+mYear);
                        day = mDay;
                        month = mMonth+1;
                        year = mYear;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ImageButton checkButton = (ImageButton) findViewById(R.id.Button_check_expense);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Betrag abspeichern
                EditText add = (EditText) findViewById(R.id.editText_value_expense);
                int addNumber = Integer.parseInt(add.getText().toString());

                //kategorie speichern
                String category = txtView.getText().toString();

                //Beschreibung speichern
                EditText descriptionText = (EditText) findViewById(R.id.editText_description_expense);
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

                Intent intent= new Intent(new_expense.this, MainActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.Button_cancel_expense);
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
