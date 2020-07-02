package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ygba.youthgobudget.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EducationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Best_use_education_service,Best_use_education_spec,
            Best_use_education_reason,Worst_use_education_service,Worst_use_education_spec,Worst_use_education_reason,
            Priority_use_education_spec,Priority_use_education_reason;

    private Spinner Use_education, Priority_use_education_service,Gender,AgeCategory,Level_of_education,Region,District,Subcounty;

    private String Bestuse_education_service,Bestuse_education_spec,Bestuse_education_reason,Worstuse_education_service,
            Worstuse_education_spec,Worstuse_education_reason,Priorityuse_education_service,Priorityuse_education_spec,
            Priorityuse_education_reason, use_education,gender,ageCategory,level_of_education,region,district,subcounty,PHONE_ID;

    Button save_education;

    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EducationActivity.this,EducationRecordActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(this);

        initialise();


    }

    private void initialise() {
        save_education = findViewById(R.id.save_education);
        Gender = findViewById(R.id.gender);
        AgeCategory = findViewById(R.id.age_category);
        Level_of_education = findViewById(R.id.level_of_education);

        Region = findViewById(R.id.region);
        District = findViewById(R.id.district);
        Subcounty = findViewById(R.id.subcounty);

        List<String> lables = databaseHelper.reaDataTable("regions");

        loadSpinnerData(Region,lables);

        Region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String label = parent.getItemAtPosition(position).toString();

                List<String> lables = databaseHelper.readDistricts(label);

                loadSpinnerData(District,lables);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        District.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();

                List<String> lables = databaseHelper.readSubCounty(label);

                loadSpinnerData(Subcounty,lables);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Best_use_education_service = findViewById(R.id.best_use_education_service);
        Best_use_education_spec = findViewById(R.id.best_use_education_spec);
        Best_use_education_reason = findViewById(R.id.best_use_education_reason);
        Worst_use_education_service = findViewById(R.id.worst_use_education_service);
        Worst_use_education_spec = findViewById(R.id.worst_use_education_spec);
        Worst_use_education_reason = findViewById(R.id.worst_use_education_reason);
        Priority_use_education_service = findViewById(R.id.priority_use_education_service);
        Priority_use_education_spec = findViewById(R.id.priority_use_education_spec);
        Priority_use_education_reason = findViewById(R.id.priority_use_education_reason);
        Best_use_education_service.setOnClickListener(EducationActivity.this);
        Worst_use_education_service.setOnClickListener(EducationActivity.this);
        Use_education = findViewById(R.id.use_education);

        save_education.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_education:
                gender = String.valueOf(Gender.getSelectedItem());
                ageCategory = String.valueOf(AgeCategory.getSelectedItem());
                level_of_education = String.valueOf(Level_of_education.getSelectedItem());
                region = String.valueOf(Region.getSelectedItem());
                district = String.valueOf(District.getSelectedItem());
//                District.getText().toString();
                subcounty = String.valueOf(Subcounty.getSelectedItem());
//                Subcounty.getText().toString();
                Bestuse_education_service = Best_use_education_service.getText().toString();
                Bestuse_education_spec = Best_use_education_spec.getText().toString();
                Bestuse_education_reason = Best_use_education_reason.getText().toString();
                Worstuse_education_service = Worst_use_education_service.getText().toString();
                Worstuse_education_spec = Worst_use_education_spec.getText().toString();
                Worstuse_education_reason = Worst_use_education_reason.getText().toString();
                Priorityuse_education_service = String.valueOf(Priority_use_education_service.getSelectedItem());
                Priorityuse_education_spec = Priority_use_education_spec.getText().toString();
                Priorityuse_education_reason = Priority_use_education_reason.getText().toString();
                use_education = String.valueOf(Use_education.getSelectedItem());

                if (gender.equals("") || ageCategory.equals("") || level_of_education.equals("")
                        || region.equals("") || district.equals("") || subcounty.equals("")){
                    Toast.makeText(this, "You have Mandatory missing fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                PHONE_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+""+date;

                try {
                    databaseHelper.save_education(PHONE_ID,gender,ageCategory,level_of_education,region,district,subcounty,use_education,
                            Bestuse_education_service,Bestuse_education_spec,Bestuse_education_reason,Worstuse_education_service,
                            Worstuse_education_spec,Worstuse_education_reason,Priorityuse_education_service,Priorityuse_education_spec,
                            Priorityuse_education_reason);
                    Toast.makeText(this, "Saved to local machine", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());

                }catch (Exception e){}
                break;

            case R.id.best_use_education_service:
                listItems = getResources().getStringArray(R.array.education_service_array);
                multiplexes(Best_use_education_service, Best_use_education_service,listItems);
                break;

            case R.id.worst_use_education_service:
                listItems = getResources().getStringArray(R.array.education_service_array);
                multiplexes(Worst_use_education_service, Worst_use_education_service,listItems);
                break;
        }

    }

    private  void multiplexes(EditText clickable_field, final EditText selected, final String[] listItems){
        checkedItems = new boolean[listItems.length];
        clickable_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(EducationActivity.this);
                mBuilder.setTitle(R.string.listofservices);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if (!mUserItems.contains(position)){
                                mUserItems.add(position);
                            }
                        }
                        else if(!isChecked){
                            if(mUserItems.contains(position)){
                                try {
                                    mUserItems.remove(position);
                                }catch (Exception e){}

                            }
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String items = "";
                        for (int i = 0; i < mUserItems.size(); i++){
                            items = items + listItems[mUserItems.get(i)];
                            if (i != (mUserItems.size()-1)){
                                items = items + ", ";
                            }
                        }
                        mUserItems.clear();
                        selected.setText(items);
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clearall, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i=0; i < checkedItems.length; i++){
                            checkedItems[i] = false;
                            mUserItems.clear();
                            selected.setText(R.string.emptystring);
                        }
                    }
                });

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void loadSpinnerData(Spinner spinner, List<String> lables) {


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
