package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ygba.youthgobudget.database.DatabaseHelper;

import java.util.Calendar;
import java.util.List;

public class BudgetMoniteringActivity extends AppCompatActivity {

    private Spinner sector,district,subcounty,quoter,region;

    private EditText amount_sent,amount_spent,date_received,date_of_monitoring;

    private String budget_sector,budget_region,budget_district,budget_subcounty,budget_quoter,budget_amount_sent, budget_amount_spent,budget_date_received,budget_date_of_monitoring;

    private Button save_budget_monitoring;

    DatabaseHelper databaseHelper;

    private List<String> lables;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_monitering);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetMoniteringActivity.this,BudgetMonitoringRecordsActivity.class);
                startActivity(intent);
            }
        });

        sector = findViewById(R.id.sector);
        district = findViewById(R.id.district);
        subcounty = findViewById(R.id.sub_county);
        quoter = findViewById(R.id.quoter);
        region = findViewById(R.id.region);

        amount_sent = findViewById(R.id.amount_sent);
        amount_spent = findViewById(R.id.amount_spent);
        date_received = findViewById(R.id.date_received);
        date_of_monitoring = findViewById(R.id.date_of_monitoring);
        save_budget_monitoring = findViewById(R.id.save_budget_monitoring);

        databaseHelper = new DatabaseHelper(this);

        lables = databaseHelper.reaDataTable("regions");
        loadSpinnerData(region,lables);

        lables = databaseHelper.reaDataTable("sectors");
        loadSpinnerData(sector,lables);

        loadDatePicker(date_received);
        loadDatePicker(date_of_monitoring);

        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();

                List<String> lables = databaseHelper.readDistricts(label);

                loadSpinnerData(district,lables);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();

                List<String> lables = databaseHelper.readSubCounty(label);

                loadSpinnerData(subcounty,lables);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save_budget_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                budget_region = String.valueOf(region.getSelectedItem());

                budget_district = String.valueOf(district.getSelectedItem());

                budget_subcounty = String.valueOf(subcounty.getSelectedItem());

                budget_sector = String.valueOf(sector.getSelectedItem());

                budget_quoter = String.valueOf(quoter.getSelectedItem());

                budget_amount_sent = amount_sent.getText().toString();

                budget_amount_spent = amount_spent.getText().toString();

                budget_date_received = date_received.getText().toString();

                budget_date_of_monitoring = date_of_monitoring.getText().toString();

                try {

                    databaseHelper.saveBudgetMonitoring(budget_region,budget_district,budget_subcounty,budget_sector,budget_quoter,budget_amount_sent,budget_amount_spent,budget_date_received,budget_date_of_monitoring);
                    Toast.makeText(BudgetMoniteringActivity.this, "Saved locally", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());

                }catch (Exception e){
                    Toast.makeText(BudgetMoniteringActivity.this, "Failed to save: "+e.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadSpinnerData(Spinner spinner, List<String> lables) {
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void loadDatePicker(final EditText editText){
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(BudgetMoniteringActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                editText.setText(year+"-" + (monthOfYear + 1) + "-"+dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

}