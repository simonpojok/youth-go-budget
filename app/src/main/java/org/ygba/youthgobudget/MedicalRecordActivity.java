package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.ygba.youthgobudget.database.DatabaseHelper;
import org.ygba.youthgobudget.utils.Goapphelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import static org.ygba.youthgobudget.utils.Goapphelper.IP_ADDRESS;

public class MedicalRecordActivity extends AppCompatActivity {
    ListView lv;
    DatabaseHelper databaseHelper;
    ArrayList<Goapphelper> nacHelpers;
    private String s;
    Goapphelper data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab =   findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Uploading data ...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:

                                databaseHelper=new DatabaseHelper(MedicalRecordActivity.this);

                                s = String.valueOf(databaseHelper.cur2Json(databaseHelper.reaMedicalData()));

                                Log.d("SQL-JSON",s);

                                Toast.makeText(MedicalRecordActivity.this, "Starting to upload data ...", Toast.LENGTH_SHORT).show();

                                HashMap hashMap = new HashMap();

                                hashMap.put("DATA",s);

                                PostResponseAsyncTask postResponseAsyncTask = new PostResponseAsyncTask(MedicalRecordActivity.this, hashMap, new AsyncResponse() {
                                    @Override
                                    public void processFinish(String s) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MedicalRecordActivity.this);
                                        alertDialogBuilder.setTitle("Server Response");
                                        alertDialogBuilder.setMessage(s);
                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();
                                        return;
                                    }
                                });

                                postResponseAsyncTask.execute(IP_ADDRESS+"health");
                                postResponseAsyncTask.setEachExceptionsHandler(new EachExceptionsHandler() {
                                    @Override
                                    public void handleIOException(IOException e) {
                                        Toast.makeText(getApplicationContext(), "Request Could not reach the dedicated server, operation failed.", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void handleMalformedURLException(MalformedURLException e) {
                                        Toast.makeText(getApplicationContext(), "The URL is not well specified.", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void handleProtocolException(ProtocolException e) {
                                        Toast.makeText(getApplicationContext(), "Issue with protocol.", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                                        Toast.makeText(getApplicationContext(), "Text encoding is not proper.", Toast.LENGTH_LONG).show();
                                    }
                                });

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MedicalRecordActivity.this);
                builder.setTitle("Upload Record(s)");
                builder.setMessage("All your new records will be uploaded to the remote server, your data will stay on the phone for backup purposes.").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv = (ListView)findViewById(R.id.medical_education_records);

        databaseHelper=new DatabaseHelper(MedicalRecordActivity.this);

        s=String.valueOf(databaseHelper.cur2Json(databaseHelper.reaMedicalData()));

        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

        BindDictionary<Goapphelper> dict = new BindDictionary<Goapphelper>();

        dict.addStringField(R.id.region, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Region: "+item.region;
            }
        });

        dict.addStringField(R.id.district, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "District: "+item.district;
            }
        });

        dict.addStringField(R.id.subcounty, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Subcounty: "+item.subcounty;
            }
        });

        dict.addStringField(R.id.gender, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Gender: "+item.gender;
            }
        });

        dict.addStringField(R.id.ageCategory, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Age Category: "+item.ageCategory;
            }
        });

        dict.addStringField(R.id.level_of_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Level of education: "+item.level_of_education;
            }
        });

        dict.addStringField(R.id.establish_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Establish health education: "+item.establish_health_education;
            }
        });

        dict.addStringField(R.id.yes_establish_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Yes establish health education: "+item.yes_establish_health_education;
            }
        });

        dict.addStringField(R.id.yes_establish_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Reason establish health education: "+item.reason_establish_health_education;
            }
        });

        dict.addStringField(R.id.rate_establish_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Rate establish health education: "+item.rate_establish_health_education;
            }
        });

        dict.addStringField(R.id.rate_reason_establish_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Rate reason establish health education: "+item.rate_reason_establish_health_education;
            }
        });

        dict.addStringField(R.id.best_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Best health education: "+item.best_health_education;
            }
        });

        dict.addStringField(R.id.best_health_education_spec, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Best health education spec: "+item.best_health_education_spec;
            }
        });

        dict.addStringField(R.id.best_health_education_reason, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Best health education reason: "+item.best_health_education_reason;
            }
        });

        dict.addStringField(R.id.worst_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Worst health education: "+item.worst_health_education;
            }
        });

        dict.addStringField(R.id.worst_health_education_spec, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Worst health education Spec: "+item.worst_health_education_spec;
            }
        });

        dict.addStringField(R.id.worst_health_education_reason, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Worst health education reason: "+item.worst_health_education_reason;
            }
        });

        dict.addStringField(R.id.priority_health_education, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Priority health education: "+item.priority_health_education;
            }
        });

        dict.addStringField(R.id.priority_health_education_spec, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Priority health education spec: "+item.priority_health_education_spec;
            }
        });

        dict.addStringField(R.id.priority_health_education_reason, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Priority health education reason: "+item.priority_health_education_reason;
            }
        });


        FunDapter<Goapphelper> fundupter = new FunDapter<>(MedicalRecordActivity.this, nacHelpers, R.layout.health_layout, dict);
        lv.setAdapter(fundupter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data = nacHelpers.get(position);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:

                                DatabaseHelper databaseHelper = new DatabaseHelper(MedicalRecordActivity.this);
                                databaseHelper.deleteSinglemedical(data.id);

                                finish();
                                startActivity(getIntent());

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MedicalRecordActivity.this);
                builder.setTitle("Delete Record");
                builder.setMessage("Are you sure you want to delete this record?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });



    }

}