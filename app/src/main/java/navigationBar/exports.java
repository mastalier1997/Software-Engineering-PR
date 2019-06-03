package navigationBar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finanzmanager.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class exports extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        // Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#F66213'>Export </font>"));

        // Writing to CSV File
        button = findViewById(R.id.button_export);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkForPermission();

                String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
                String fileName = "AnalysisData.csv";
                String filePath = baseDir + File.separator + fileName;
                File f = new File(filePath);
                CSVWriter writer = null;

                // File exist
                if(f.exists()&&!f.isDirectory())
                {
                    FileWriter mFileWriter = null;
                    try {
                        mFileWriter = new FileWriter(filePath, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    writer = new CSVWriter(mFileWriter);
                }
                else
                {
                    try {
                        writer = new CSVWriter(new FileWriter(filePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                String[] data = {"Aus-/Eingabe","Tag","Monat","Jahr","Betrag","Wiederk.","Kategorie","Beschreibung"};

                if(writer != null)
                    writer.writeNext(data);

                try {
                    if(writer != null)
                        writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*
                String[] entries = { "book", "coin", "pencil", "cup" };
                String fileName = "src/main/res/raw/export.csv";

                try (FileOutputStream fos = new FileOutputStream(fileName);
                     OutputStreamWriter osw = new OutputStreamWriter(fos,
                             StandardCharsets.UTF_8);
                     CSVWriter writer = new CSVWriter(osw)) {

                    writer.writeNext(entries);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */

            }
        }); // End setOnClickListener
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkForPermission(){
        int REQUEST_WRITE_EXTERNAL_STORAGE = 99919;
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Log.d("export", "permission already granted");
        } else{
            if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "Zugriff auf den Speicher benötigt, um Daten zu exportieren",
                        Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        }

    }
}
