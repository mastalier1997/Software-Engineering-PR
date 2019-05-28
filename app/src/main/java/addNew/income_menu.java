package addNew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finanzmanager.R;

import java.util.ArrayList;
import java.util.List;

public class income_menu extends AppCompatActivity {
    SharedPreferences income_pref;
    SharedPreferences.Editor income_editor;
    public static String extraName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        // Speicherung der eigenen Kategorie & Kontrolle bei Neustart
        income_pref =  PreferenceManager.getDefaultSharedPreferences(this);
        income_editor = income_pref.edit();
        income_checkSharedPreferences();

        Spinner dropdown2 = findViewById(R.id.dropDown_InOut);
        List<String> items2= new ArrayList<>();
        items2.add(0,"Einnahmen");
        items2.add("Ausgaben");

        final ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown2.setAdapter(adapter);

        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getItemAtPosition(position).equals("Einnahmen")){
                    //do nothing
                }else {
                    String item= adapterView.getItemAtPosition(position).toString();

                    if (adapterView.getItemAtPosition(position).equals("Ausgaben")){
                        Intent intent = new Intent(income_menu.this, expense_menu.class);
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
                Intent intent = new Intent(income_menu.this, create_category_income.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Es wird ein integer mitgegeben, mit dem das richtige Bild und Text ausgewählt wird
        ImageButton imageButton = (ImageButton) findViewById(R.id.Button_award);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie",1);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.Button_investment);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie", 2);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = (ImageButton) findViewById(R.id.Button_salary);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie", 3);
                startActivity(intent);
            }
        });

        ImageButton imageButton4 = (ImageButton) findViewById(R.id.Button_dividend);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie", 4);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.Button_gambling);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie", 5);
                startActivity(intent);
            }
        });

        ImageButton imageButton5 = (ImageButton) findViewById(R.id.Button_refund);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie", 6);
                startActivity(intent);
            }
        });


        Intent extraIntent = getIntent();

        if(extraIntent.getStringExtra("income_text") != null){
            extraName2 = extraIntent.getStringExtra("income_text");
            income_editor.putString("extraName2", extraName2);
            income_editor.commit();
        }
        //extraName2 = extraIntent.getStringExtra("Test2");

        TextView imageText2 = findViewById(R.id.textView_extraCat_income);
        imageText2.setText(extraName2);
        ImageButton imageButton7 = (ImageButton) findViewById(R.id.Button_extraCatInc);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(income_menu.this, new_income.class);
                intent.putExtra("kategorie", 7);
                intent.putExtra("income_text2", extraName2);
                startActivity(intent);
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
    private void income_checkSharedPreferences() {

        String newSavedName = income_pref.getString("extraName2", "");
        if (!newSavedName.equals("")) {
            extraName2 = newSavedName;
        } else {
            extraName2 = null;
        }

    }
}
