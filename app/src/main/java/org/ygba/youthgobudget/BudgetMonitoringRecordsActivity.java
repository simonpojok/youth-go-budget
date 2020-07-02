package org.ygba.youthgobudget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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

public class BudgetMonitoringRecordsActivity extends AppCompatActivity {

    private ListView lv;

    DatabaseHelper databaseHelper;
    ArrayList<Goapphelper> nacHelpers;

    private String s;
    Goapphelper data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_monitoring_records);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronising data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                synchData();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        databaseHelper=new DatabaseHelper(BudgetMonitoringRecordsActivity.this);

        s=String.valueOf(databaseHelper.cur2Json(databaseHelper.readMonitoringRecords()));

        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

        BindDictionary<Goapphelper> dict = new BindDictionary<Goapphelper>();

        dict.addStringField(R.id.region, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Region: "+item.region_name;
            }
        });

        dict.addStringField(R.id.district, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "District: "+item.district;
            }
        });

        dict.addStringField(R.id.sub_county, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Sub county: "+item.sub_county;
            }
        });

        dict.addStringField(R.id.sector, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Sector: "+item.sector_name;
            }
        });


        dict.addStringField(R.id.quoter, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Quoter: "+item.quater;
            }
        });

        dict.addStringField(R.id.amount_sent, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Amount sent: "+item.amount_sent;
            }
        });

        dict.addStringField(R.id.amount_spent, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Amount spent: "+item.amount;
            }
        });

        dict.addStringField(R.id.date_received, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Date received: "+item.date;
            }
        });

        dict.addStringField(R.id.date_of_monitoring, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Date of monitoring: "+item.date_of_monitoring;
            }
        });


        FunDapter<Goapphelper> fundupter = new FunDapter<>(BudgetMonitoringRecordsActivity.this,
                nacHelpers, R.layout.budget_monitoring_layout, dict);

        lv = findViewById(R.id.budget_monitoring_records);

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

                                DatabaseHelper databaseHelper = new DatabaseHelper(BudgetMonitoringRecordsActivity.this);
                                databaseHelper.deleteSingleBudget(data.id);

                                finish();
                                startActivity(getIntent());

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(BudgetMonitoringRecordsActivity.this);
                builder.setTitle("Delete Record");
                builder.setMessage("Are you sure you want to delete this record?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });

    }


    private void synchData(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:
                        databaseHelper=new DatabaseHelper(BudgetMonitoringRecordsActivity.this);
                        s=String.valueOf(databaseHelper.cur2Json(databaseHelper.readMonitoringRecords()));

                        Toast.makeText(BudgetMonitoringRecordsActivity.this, "Starting to upload data ...",
                                Toast.LENGTH_SHORT).show();

                        HashMap hashMap = new HashMap();
                        hashMap.put("data",s);
                        PostResponseAsyncTask postResponseAsyncTask = new PostResponseAsyncTask(BudgetMonitoringRecordsActivity.this,
                                hashMap, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BudgetMonitoringRecordsActivity.this);
                                alertDialogBuilder.setTitle("Server Response");
                                alertDialogBuilder.setMessage(s);
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                                return;
                            }
                        });

                        postResponseAsyncTask.execute(IP_ADDRESS+"budget");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(BudgetMonitoringRecordsActivity.this);
        builder.setTitle("Upload Record(s)");
        builder.setMessage("All your new records will be uploaded to the remote server, your data will stay on the phone for backup purposes.").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }

}
