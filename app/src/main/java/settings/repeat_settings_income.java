package settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.finanzmanager.DataClasses.Income;
import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class repeat_settings_income extends AppCompatActivity {

    private ListView listview_income_repeat;
    private ArrayAdapter<String> stringArrayAdapter_income_repeat;
    private ArrayList<String> stringArrayList_income_repeat = new ArrayList<>();
    private ArrayList<Income> repeating_incomes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.repeat_settings_income);


        //ListView
        listview_income_repeat = (ListView) findViewById(R.id.listView_repeating_income);
        //Befüllung aus account
        repeating_incomes = MainActivity.account.get_repeatingIncomeList();

        for(Income i : repeating_incomes) {
            stringArrayList_income_repeat.add(i.getInfo());
        }

        //Ausgabe in ListView
        stringArrayAdapter_income_repeat = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, R.id.textView_list_black, stringArrayList_income_repeat);
        listview_income_repeat.setAdapter(stringArrayAdapter_income_repeat);


        Spinner dropdown = findViewById(R.id.dropDown_InOut_repeat);
        List<String> items2= new ArrayList<>();
        items2.add(0,"Einnahmen");
        items2.add("Ausgaben");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (adapterView.getItemAtPosition(position).equals("Einnahmen")){
                    //do nothing
                }else {
                    String item= adapterView.getItemAtPosition(position).toString();

                    if (adapterView.getItemAtPosition(position).equals("Ausgaben")){
                        Intent intent = new Intent(repeat_settings_income.this, repeat_settings_expense.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ImageButton cancelButton = (ImageButton) findViewById(R.id.button_cancel_income_setting);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(repeat_settings_income.this, settings.class);
                startActivity(intent);
            }
        });

        listview_income_repeat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Income info = repeating_incomes.get(i);
                Intent intent = new Intent(repeat_settings_income.this, info_repeating_income.class);
                intent.putExtra("description", info.getDescription());
                intent.putExtra("value", info.getValue());
                startActivity(intent);
            }
        });

    }

}
