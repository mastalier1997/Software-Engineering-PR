package navigationBar;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finanzmanager.DataClasses.Expense;
import com.example.finanzmanager.DataClasses.Income;
import com.example.finanzmanager.DataClasses.PositionSample;
import com.example.finanzmanager.R;
import com.example.finanzmanager.activity_classes.MainActivity;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class exports extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_export);

        // Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Export </font>"));

        // TextView Message
        textView = findViewById(R.id.text_info_Export);
        textView.setText("Durch Klicken auf \"Exportieren\" werden alle Daten in eine CSV Datei exportiert (Endung .csv)" +
                "\n\nSpeicherort: Eigene Dateien --> Interner Speicher");

        // Writing to CSV File
        button = findViewById(R.id.button_export);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if permission WRITE_EXTERNAL_STORAGE is already granted
                if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                    askForPermission();
                } else {
                    Log.d("exportData", "permission already granted");

                    try {
                        exportData();
                    } catch (IOException e) {
                        e.printStackTrace();

                        //Toast Message
                        Context context = getApplicationContext();
                        CharSequence text = "Export in CSV-Datei fehlgeschlagen.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    //Toast Message
                    Context context = getApplicationContext();
                    CharSequence text = "Export in CSV-Datei erfolgreich. \n Dateiname: FM_Data... .csv";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        }); // End setOnClickListener
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
        int REQUEST_WRITE_EXTERNAL_STORAGE = 99901;
        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Zugriff auf den Speicher benötigt, um Daten zu exportieren",
                    Toast.LENGTH_LONG).show();
        }
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
    }

    private void exportData() throws IOException {
        //TODO: Android Version abfragen ober >= 8.0 Oreo, sonst exportData nicht möglich
        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/FM_Data_" + System.currentTimeMillis() + ".csv";
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(csv));

                CSVWriter csvWriter = new CSVWriter(writer,
                        ';', //seperation character
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {

            //List of all Positions
            List<PositionSample> allPos = new ArrayList<>();

            //add repeating Incomes to list
            List<Income> r_income_list = MainActivity.account.repeatingIncomeList;
            for (Income i : r_income_list) {
                PositionSample ps = new PositionSample();
                ps.setCategory(i.getCategory());
                ps.setDate(i.getDate());
                ps.setDescription(i.getDescription());
                ps.setPositionType(1); // 1=income
                ps.setReocurring(i.getRecurring());
                ps.setValue(i.getValue());
                allPos.add(ps);
            }

            //add repeating Expenses to list
            List<Expense> r_expense_list = MainActivity.account.repeatingExpenseList;
            for (Expense i : r_expense_list) {
                PositionSample ps = new PositionSample();
                ps.setCategory(i.getCategory());
                ps.setDate(i.getDate());
                ps.setDescription(i.getDescription());
                ps.setPositionType(0); // 0=income
                ps.setReocurring(i.getRecurring());
                ps.setValue(i.getValue());
                allPos.add(ps);
            }

            //add non repeating Incomes to list
            List<Income> income_list = MainActivity.account.incomeList;
            for (Income i : income_list) {
                if (!i.getRecurring()) {
                    PositionSample ps = new PositionSample();
                    ps.setCategory(i.getCategory());
                    ps.setDate(i.getDate());
                    ps.setDescription(i.getDescription());
                    ps.setPositionType(1); // 1=income
                    ps.setReocurring(i.getRecurring());
                    ps.setValue(i.getValue());
                    allPos.add(ps);
                }
            }

            //add non repeating Expenses to list
            List<Expense> expense_list = MainActivity.account.expenseList;
            for (Expense i : expense_list) {
                if (!i.getRecurring()) {
                    PositionSample ps = new PositionSample();
                    ps.setCategory(i.getCategory());
                    ps.setDate(i.getDate());
                    ps.setDescription(i.getDescription());
                    ps.setPositionType(0); // 0=expense
                    ps.setReocurring(i.getRecurring());
                    ps.setValue(i.getValue());
                    allPos.add(ps);
                }
            }

            //sort list by Date
            Collections.sort(allPos, PositionSample::compareTo);
            Collections.sort(allPos, Collections.reverseOrder());
            //*Write to file*

            //Header of CSV-File
            String[] headerRecord = {"Aus-/Eingabe", "Tag", "Monat", "Jahr", "Betrag", "Wiederk.", "Kategorie", "Beschreibung"};
            csvWriter.writeNext(headerRecord);

            //Write every Position to file
            for (PositionSample ps : allPos) {
                csvWriter.writeNext(ps.readyToExport());
            }
        }
    }

}
