package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.ygba.youthgobudget.utils.Goapphelper;

import java.util.ArrayList;

import static org.ygba.youthgobudget.utils.Goapphelper.IP_ADDRESS;

public class PollsActivity extends AppCompatActivity {
    private ArrayList<Goapphelper> ygbHelpers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PostResponseAsyncTask postResponseAsyncTask = new PostResponseAsyncTask(PollsActivity.this,
                new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        try{
                            ygbHelpers = new JsonConverter<Goapphelper>().toArrayList(s,Goapphelper.class);

                            BindDictionary<Goapphelper> dict = new BindDictionary<Goapphelper>();

                            dict.addStringField(R.id.title, new StringExtractor<Goapphelper>() {
                                @Override
                                public String getStringValue(Goapphelper item, int position) {
                                    return "" + item.title;
                                }
                            });

                            dict.addStringField(R.id.date, new StringExtractor<Goapphelper>() {
                                @Override
                                public String getStringValue(Goapphelper item, int position) {
                                    return "" + item.created_at;
                                }
                            });

                            dict.addStringField(R.id.description, new StringExtractor<Goapphelper>() {
                                @Override
                                public String getStringValue(Goapphelper item, int position) {
                                    return "" + item.description;
                                }
                            });
                            ListView listView = findViewById(R.id.polls);

                            FunDapter<Goapphelper> fundupter = new FunDapter<>(PollsActivity.this,
                                    ygbHelpers, R.layout.polls_layout, dict);

                            listView.setAdapter(fundupter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Goapphelper service = ygbHelpers.get(position);

                                    Intent intent = new Intent(PollsActivity.this, PollAnswerActivity.class);
                                    intent.putExtra("poll_id", service.id);
                                    intent.putExtra("poll_title", service.title);
                                    intent.putExtra("poll_link", service.link);
                                    startActivity(intent);

                                }
                            });

                        }catch(Exception e){}
                    }
                });

        postResponseAsyncTask.execute(IP_ADDRESS+"polls");
    }

}