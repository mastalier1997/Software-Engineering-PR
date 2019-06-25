package Settings;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.finanzmanager.R;
import com.example.finanzmanager.ActivityClasses.MainActivity;

public class InfoRepeatingExpense extends AppCompatActivity {

    EditText description;
    EditText value;
    String old_desc;
    double old_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_info_repeating_expense);

        description = (EditText) findViewById(R.id.editText_info_description_expense);
        value = (EditText) findViewById(R.id.editText_info_value_expense);

        Intent intent = getIntent();
        double valueText = intent.getDoubleExtra("value", 0);
        String descriptionText = intent.getStringExtra("description");

        old_desc = descriptionText;
        old_value = valueText;

        description.setText(descriptionText);
        value.setText(String.valueOf(valueText));

        Button save = (Button) findViewById(R.id.button_save_info_expense);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_desc = description.getText().toString();
                double new_value = Double.parseDouble(value.getText().toString());

                MainActivity.account.changeRepeatingExpenseList(old_desc, old_value, new_desc, new_value);
                MainActivity.saveToAccount();

                Intent intent= new Intent(InfoRepeatingExpense.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button delete = (Button) findViewById(R.id.button_delete_info_expense);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.account.deleteRepeatingExpense(old_desc, old_value);
                MainActivity.saveToAccount();

                Intent intent= new Intent(InfoRepeatingExpense.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
