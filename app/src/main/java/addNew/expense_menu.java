package addNew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class expense_menu extends AppCompatActivity {
    SharedPreferences expense_pref;
    SharedPreferences.Editor expense_editor;
    public static String extraName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_expense);
        Spinner spinner = (Spinner) findViewById(R.id.dropDown_In_Out);

        // Speicherung der eigenen Kategorie & Kontrolle bei Neustart
        expense_pref =  PreferenceManager.getDefaultSharedPreferences(this);
        expense_editor = expense_pref.edit();
        checkSharedPreferences();

        List<String> categories= new ArrayList<>();
        categories.add(0,"Ausgaben");
        categories.add("Einahmen");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(R.layout.spinner);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#F66213"));
                Typeface type = ResourcesCompat.getFont(getApplicationContext(), R.font.droid);
                ((TextView) adapterView.getChildAt(0)).setTypeface(type);
                if (adapterView.getItemAtPosition(position).equals("Ausgaben")){
                    //do nothing
                }else {
                    String item= adapterView.getItemAtPosition(position).toString();

                    if (adapterView.getItemAtPosition(position).equals("Einahmen")){
                        Intent intent = new Intent(expense_menu.this, income_menu.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button createCategorie = (Button) findViewById(R.id.Button_createCat_expense);
        createCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(expense_menu.this, create_category_expense.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Ausgaben </font>"));

        // Es wird ein integer mitgegeben, mit dem das richtige Bild und Text ausgewählt wird
        ImageButton imageButton = (ImageButton) findViewById(R.id.Button_cash);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                // der name ist der Schlüssel, mit dem dieser Wert in der Zielklasse wieder gefunden wird
                intent.putExtra("kategorie", 1);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.Button_atm);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 2);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.Button_cinema);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 3);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.Button_gas);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 4);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.Button_fastFood);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 5);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.Button_grocery);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 6);
                startActivity(intent);
            }
        });

        ImageButton imageButton7 = (ImageButton) findViewById(R.id.Button_gym);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 7);
                startActivity(intent);
            }
        });

        ImageButton imageButton8 = (ImageButton) findViewById(R.id.Button_hotel);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 8);
                startActivity(intent);
            }
        });

        ImageButton imageButton9 = (ImageButton) findViewById(R.id.Button_travel);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 9);
                startActivity(intent);
            }
        });

        Intent extraIntent = getIntent();
        if(extraIntent.getStringExtra("eins") != null){
            extraName = extraIntent.getStringExtra("eins");
            expense_editor.putString("extraName", extraName);
            expense_editor.commit();
        }


        TextView imageText = findViewById(R.id.textView_extraCat_expense);
        imageText.setText(extraName);

        ImageButton imageButton10 = (ImageButton) findViewById(R.id.Button_extraCatExp);

        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(expense_menu.this, new_expense.class);
                intent.putExtra("kategorie", 10);
                intent.putExtra("zwei", extraName);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent intent = new Intent(expense_menu.this, MainActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    private void checkSharedPreferences() {

        String newSavedName = expense_pref.getString("extraName", "");
        if (!newSavedName.equals("")) {
            extraName = newSavedName;
        } else {
            extraName = null;
        }

    }
}
