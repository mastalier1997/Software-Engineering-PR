package AddNew;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finanzmanager.R;
import com.example.finanzmanager.ActivityClasses.MainActivity;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewExpense extends AppCompatActivity {

    DatePickerDialog datePickerDialog;

    ImageView imgView;
    TextView txtView;
    CheckBox repeat;
    TextView textView;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH)+1;
    int year = calendar.get(Calendar.YEAR);

    // Speicherung der eigenen Kategorie
    static String extraName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                month=month-1;
                datePickerDialog = new DatePickerDialog(NewExpense.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        textView.setText(mDay +"."+(mMonth+1)+"."+mYear);
                        day = mDay;
                        month = mMonth+1;
                        year = mYear;
                    }
                }, year, month, day);
                //Limit des Kalenders setzen 2010 - 2025
                Calendar minYear = Calendar.getInstance();
                minYear.set(Calendar.DAY_OF_MONTH, 1);
                minYear.set(Calendar.MONTH, 0);
                minYear.set(Calendar.YEAR, 2010);

                Calendar maxYear = Calendar.getInstance();
                maxYear.set(Calendar.DAY_OF_MONTH, 31);
                maxYear.set(Calendar.MONTH, 11);
                maxYear.set(Calendar.YEAR, 2025);


                datePickerDialog.getDatePicker().setMinDate(minYear.getTimeInMillis());   //min Date = 01.01.2010
                datePickerDialog.getDatePicker().setMaxDate(maxYear.getTimeInMillis());   //max Date = 31.12.2025
                datePickerDialog.show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Ausgabe </font>"));


        ImageButton checkButton = (ImageButton) findViewById(R.id.Button_check_expense);
        EditText add = (EditText) findViewById(R.id.editText_value_expense);
        add.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6,2)});
        checkButton.setEnabled(false);

        //checkt ob Input im Feld geschehen ist
        add.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    checkButton.setEnabled(false);

                }else {
                    checkButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView textView = findViewById(R.id.hinweisBetrag);
                textView.setVisibility(View.INVISIBLE);
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (add.getText().toString()){
                        case "":break;
                        default://Betrag abspeichern
                            double addNumber = Double.parseDouble(add.getText().toString());


                            //kategorie speichern
                            String category = txtView.getText().toString();

                            //Beschreibung speichern
                            EditText descriptionText = (EditText) findViewById(R.id.editText_description_expense);
                            String description = descriptionText.getText().toString();

                            //wiederkehrend
                            Boolean repeats = false;
                            if(repeat.isChecked()) repeats = true;

                            boolean allowed = true;

                            if (repeats && MainActivity.account.repeatingExpenseContains(description, addNumber)) {
                                //Toast Message
                                Context context = getApplicationContext();
                                CharSequence text = "Ungültige Eingabe: Die Beschreibung und der Wert sind bereits in einer anderen Position gespeichert";
                                int duration = Toast.LENGTH_LONG;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                allowed = false;
                            }

                            if (allowed) {

                                //alles in einem Bundle gespeichert
                                Bundle extras = new Bundle();
                                extras.putDouble("value", addNumber);
                                extras.putInt("day", day);
                                extras.putInt("month", month);
                                extras.putInt("year", year);
                                extras.putString("category", category);
                                extras.putString("description", description);
                                extras.putBoolean("repeats", repeats);
                                extras.putString("type", "expense");

                                Intent intent = new Intent(NewExpense.this, MainActivity.class);
                                intent.putExtras(extras);
                                startActivity(intent);
                            }
                    }

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

    private class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
            mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }

    }


}
