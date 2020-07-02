package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.os.Bundle;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.ygba.youthgobudget.database.DatabaseHelper;
import org.ygba.youthgobudget.utils.Goapphelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.ygba.youthgobudget.utils.Goapphelper.IP_ADDRESS;

public class WaterSectorRecordsActivity extends AppCompatActivity {

    private ListView lv;
    private DatabaseHelper databaseHelper;
    private ArrayList<Goapphelper> nacHelpers;
    private String s;
    private Goapphelper data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_sector_records);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:

                                databaseHelper=new DatabaseHelper(WaterSectorRecordsActivity.this);
                                s=String.valueOf(databaseHelper.cur2Json(databaseHelper.readWaterSaniationData()));

                                Log.d("SQL-JSON",s);

                                Toast.makeText(WaterSectorRecordsActivity.this, "Starting to upload data ...", Toast.LENGTH_SHORT).show();

                                HashMap hashMap = new HashMap();
                                hashMap.put("DATA",s);
                                PostResponseAsyncTask postResponseAsyncTask = new PostResponseAsyncTask(WaterSectorRecordsActivity.this, hashMap, new AsyncResponse() {
                                    @Override
                                    public void processFinish(String s) {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WaterSectorRecordsActivity.this);
                                        alertDialogBuilder.setTitle("Server Response");
                                        alertDialogBuilder.setMessage(s);
                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();
                                        return;
                                    }
                                });

                                postResponseAsyncTask.execute(IP_ADDRESS+"water_sector");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(WaterSectorRecordsActivity.this);
                builder.setTitle("Upload Record(s)");
                builder.setMessage("All your new records will be uploaded to the remote server, your data will stay on the phone for backup purposes.").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();







            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper=new DatabaseHelper(WaterSectorRecordsActivity.this);

        lv =  findViewById(R.id.water_records);

        databaseHelper=new DatabaseHelper(WaterSectorRecordsActivity.this);

        s=String.valueOf(databaseHelper.cur2Json(databaseHelper.readWaterSaniationData()));

        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

        BindDictionary<Goapphelper> dict = new BindDictionary<Goapphelper>();

        dict.addStringField(R.id.record_id, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Record: "+item.id;
            }
        });

        dict.addStringField(R.id.name, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Name: "+item.name_of_monitor;
            }
        });

        dict.addStringField(R.id.date_recorded, new StringExtractor<Goapphelper>() {
            @Override
            public String getStringValue(Goapphelper item, int position) {
                return "Date: "+item.date;
            }
        });

        FunDapter<Goapphelper> fundupter = new FunDapter<>(WaterSectorRecordsActivity.this, nacHelpers, R.layout.watersaniation_layout, dict);
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

                                DatabaseHelper databaseHelper = new DatabaseHelper(WaterSectorRecordsActivity.this);
                                databaseHelper.deleteSingleWaterSaniation(data.id);

                                finish();
                                startActivity(getIntent());

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(WaterSectorRecordsActivity.this);
                builder.setTitle("Delete Record");
                builder.setMessage("Are you sure you want to delete this record?").setPositiveButton("Yes",
                        dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });




    }

}
