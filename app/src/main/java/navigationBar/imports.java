package navigationBar;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finanzmanager.DataClasses.Date;
import com.example.finanzmanager.DataClasses.Expense;
import com.example.finanzmanager.DataClasses.Income;
import com.example.finanzmanager.DataClasses.Month;
import com.example.finanzmanager.DataClasses.PositionSample;
import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.MainActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class imports extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private TextView path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_import);

        // Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Import</font>"));

        // File name
        textView = findViewById(R.id.editText);
        textView.setText("FiMa_data.csv");

        // Path of file
        path = findViewById(R.id.text_info_pfad);
        path.setText("Datei von: Eigene Dateien --> Interner Speicher");

        // import the csv file
        button = findViewById(R.id.button_import);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if permission READ_EXTERNAL_STORAGE is already granted
                if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    askForPermission();
                } else {
                    Log.d("imports", "permission already granted");

                    importData();
                }
            }
        }); // End setOnClickListener()

    }

    private void importData() {
        List<PositionSample> samples = readCSVData();

        // If file doesn't exist
        if (samples == null) {
            //Toast Message
            Context context = getApplicationContext();
            CharSequence text = textView.getText() + " existiert nicht!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        // Load Data to app
        if (!samples.isEmpty()) {
            for (PositionSample s : samples) {

                if (MainActivity.months.yearExists(s.getDate().getYear()) == false) {
                    MainActivity.months.newYear(s.getDate().getYear());
                    MainActivity.account.updateRepeating(s.getDate().getYear());
                }

                if (s.getReocurring()) {
                    if (s.getPositionType() == 0) {
                        MainActivity.account.addExpenseFromCurrentMonth(s.getDate(), s.getValue(),
                                s.getCategory(), s.getDescription());
                        MainActivity.account.addRepeatingExpense(s.getDate(), s.getValue(),
                                s.getReocurring(), s.getCategory(), s.getDescription());
                    }
                    if (s.getPositionType() == 1) {
                        MainActivity.account.addIncomeFromCurrentMonth(s.getDate(), s.getValue(),
                                s.getCategory(), s.getDescription());
                        MainActivity.account.addRepeatingIncome(s.getDate(), s.getValue(),
                                s.getReocurring(), s.getCategory(), s.getDescription());
                    }
                } else if (s.getPositionType() == 0) {
                    MainActivity.account.addExpense(s.getDate(), s.getValue(),
                            s.getReocurring(), s.getCategory(), s.getDescription());
                    MainActivity.months.updateMonthExpense(s.getDate().getYear(),
                            s.getDate().getMonth(), s.getValue());
                } else if (s.getPositionType() == 1) {
                    MainActivity.account.addIncome(s.getDate(), s.getValue(),
                            s.getReocurring(), s.getCategory(), s.getDescription());
                    MainActivity.months.updateMonthIncome(s.getDate().getYear(),
                            s.getDate().getMonth(), s.getValue());
                } else {
                    Log.d("imports", "Sample from CSV was not added. Unkown why");
                }

                if (!MainActivity.years.contains(s.getDate().getYear())) {
                    MainActivity.years.add(s.getDate().getYear());
                }
            }
            Gson gson = new Gson();

            String account_json = gson.toJson(MainActivity.account);
            String months_json = gson.toJson(MainActivity.months);
            String years_json = gson.toJson(MainActivity.years);
            MainActivity.saveEditor.putString("months", months_json);
            MainActivity.saveEditor.putString("years", years_json);
            MainActivity.saveEditor.putString("account", account_json);
            MainActivity.saveEditor.commit();

            //Toast Message
            Context context = getApplicationContext();
            CharSequence text = "Import der CSV-Datei erfolgreich.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {
            Log.d("imports", "CSV file is empty");

            //Toast Message
            Context context = getApplicationContext();
            CharSequence text = "Import der CSV-Datei fehlgeschlagen. Die Datei ist" +
                    "leer.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private List<PositionSample> readCSVData() {
        List<PositionSample> dataList = new ArrayList<>();
        BufferedReader reader;
        textView = findViewById(R.id.editText);

        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + textView.getText();
        try {
            reader = Files.newBufferedReader(Paths.get(csv));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String line = "";

        try {
            // Spaltenüberschrift in csv-datei überspringen
            reader.readLine();

            while (((line = reader.readLine()) != null)) {

                // split by ';'
                String[] tokens = line.split(";");

                // read the data
                PositionSample sample = new PositionSample();
                if (tokens.length >= 8 &&
                        tokens[0].length() > 0 &&
                        tokens[1].length() > 0 &&
                        tokens[2].length() > 0 &&
                        tokens[3].length() > 0 &&
                        tokens[4].length() > 0 &&
                        tokens[5].length() > 0 &&
                        tokens[6].length() > 0 &&
                        tokens[7].length() > 0) {
                    sample.setPositionType(Integer.parseInt(tokens[0]));
                    sample.setDate(new Date(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
                    sample.setValue(Double.parseDouble(tokens[4]));
                    sample.setReocurring(Boolean.parseBoolean(tokens[5]));
                    sample.setCategory(tokens[6]);
                    sample.setDescription(tokens[7]);

                    if (sample.getPositionType() == 0 || sample.getPositionType() == 1) {
                        dataList.add(sample);
                        Log.d("imports", "Just created: " + sample);
                    } else {
                        Log.d("imports", "Invalid PositionType: " + sample);
                    }
                } else {
                    Log.d("imports", "Invalid data: " + sample);
                }
            }
        } catch (IOException e) {
            Log.wtf("imports", "Error reading sample.csv on line " + line, e);
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void askForPermission() {
        int REQUEST_READ_EXTERNAL_STORAGE = 99902;
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Zugriff auf den Speicher benötigt, um Daten zu importieren",
                    Toast.LENGTH_LONG).show();
        }
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
    }

}
