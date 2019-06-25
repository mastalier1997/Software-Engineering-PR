package Settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finanzmanager.ActivityClasses.MainActivity;

import com.example.finanzmanager.R;

public class InfoRepeatingIncome extends AppCompatActivity {

    EditText description;
    EditText value;
    String old_desc;
    double old_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_info_repeating_income);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='"+getResources().getColor(R.color.colorAccent)+"'>Änderung von Einnahmen</font>"));

        //Back Button aktivieren
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        description = (EditText) findViewById(R.id.editText_info_description_income);
        value = (EditText) findViewById(R.id.editText_info_value_income);

        Intent intent = getIntent();
        double valueText = intent.getDoubleExtra("value", 0);
        String descriptionText = intent.getStringExtra("description");

        old_desc = descriptionText;
        old_value = valueText;

        description.setText(descriptionText);
        value.setText(String.valueOf(valueText));

        Button save = (Button) findViewById(R.id.button_save_info_income);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_desc = description.getText().toString();
                double new_value = Double.parseDouble(value.getText().toString());

                MainActivity.account.changeRepeatingIncomeList(old_desc, old_value, new_desc, new_value);
                MainActivity.saveToAccount();

                Intent intent= new Intent(InfoRepeatingIncome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button delete = (Button) findViewById(R.id.button_delete_info_income);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.account.deleteRepeatingIncome(old_desc, old_value);
                MainActivity.saveToAccount();

                Intent intent= new Intent(InfoRepeatingIncome.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    /**
     * back button to Main Activity
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
