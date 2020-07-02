package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.ygba.youthgobudget.database.DatabaseHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import static com.youthgobudget.Goapphelper.IP_ADDRESS;

public class FrontActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper databaseHelper;
    ArrayList<Goapphelper> nacHelpers;
    private ImageView ic_cos,ic_lgs,ic_ba,life_cycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        Toolbar toolbar =   findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ic_cos = findViewById(R.id.ic_cos);
        ic_lgs = findViewById(R.id.ic_lgs);
        ic_ba = findViewById(R.id.ic_ba);
        life_cycle = findViewById(R.id.life_cycle);

        databaseHelper = new DatabaseHelper(FrontActivity.this);

        DrawerLayout drawer =   findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =   findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        life_cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontActivity.this,BudgetLifeCycleActivity.class));
            }
        });

        ic_ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontActivity.this,BudgetMoniteringActivity.class));
            }
        });

        ic_lgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontActivity.this,LocalGovernmentActivity.class));
            }
        });

        ic_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontActivity.this,BudgetConsutanceActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.location){

            PostResponseAsyncTask regionsPost = new PostResponseAsyncTask(FrontActivity.this, false, new AsyncResponse() {
                @Override
                public void processFinish(String s) {

                    databaseHelper.deleteTable("regions");

                    try {

                        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

                        for (final Goapphelper result : nacHelpers) {
                            databaseHelper.saveRgion(result.name,result.id);
                        }

                    }catch(Exception e){}
                }
            });

            regionsPost.execute(IP_ADDRESS+"areas/regions");
            regionsPost.setEachExceptionsHandler(new EachExceptionsHandler() {
                @Override
                public void handleIOException(IOException e) {
                    Toast.makeText(getApplicationContext(), "Internet connectivity is weak, or the server " +
                            "delayed to respond. Please try again", Toast.LENGTH_LONG).show();
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

            PostResponseAsyncTask districtPost = new PostResponseAsyncTask(FrontActivity.this, false, new AsyncResponse() {
                @Override
                public void processFinish(String s) {

                    databaseHelper.deleteTable("districts");

                    try{
                        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

                        for (final Goapphelper result : nacHelpers) {
                            databaseHelper.saveDistrict(result.name,result.region_id,result.id);
                        }
                    }catch (Exception e){}
                }
            });

            districtPost.execute(IP_ADDRESS+"areas/districts");
            districtPost.setEachExceptionsHandler(new EachExceptionsHandler() {
                @Override
                public void handleIOException(IOException e) {
                    Toast.makeText(getApplicationContext(), "Internet connectivity is weak, or the server " +
                            "delayed to respond. Please try again", Toast.LENGTH_LONG).show();
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

            PostResponseAsyncTask subcountyPost = new PostResponseAsyncTask(FrontActivity.this, new AsyncResponse() {
                @Override
                public void processFinish(String s) {

                    databaseHelper.deleteTable("sub_counties");

                    try{

                        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

                        for (final Goapphelper result : nacHelpers) {
                            databaseHelper.saveSubcounty(result.name,result.district_id,result.id);
                        }

                        Toast.makeText(FrontActivity.this, "Synchronised successfully", Toast.LENGTH_SHORT).show();

                    }catch(Exception e){}


                }
            });

            subcountyPost.execute(IP_ADDRESS+"areas/sub-counties");
            subcountyPost.setEachExceptionsHandler(new EachExceptionsHandler() {
                @Override
                public void handleIOException(IOException e) {
                    Toast.makeText(getApplicationContext(), "Internet connectivity is weak, or the server " +
                            "delayed to respond. Please try again", Toast.LENGTH_LONG).show();
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

            PostResponseAsyncTask sectorPost = new PostResponseAsyncTask(FrontActivity.this, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    databaseHelper.deleteTable("sectors");
                    try{

                        nacHelpers = new JsonConverter<Goapphelper>().toArrayList(s, Goapphelper.class);

                        for (final Goapphelper result : nacHelpers) {
                            databaseHelper.saveSector(result.name,result.id);
                        }

                        Toast.makeText(FrontActivity.this, "Synchronised successfully", Toast.LENGTH_SHORT).show();

                    }catch(Exception e){}
                }
            });
            sectorPost.execute(IP_ADDRESS+"sectors");
            sectorPost.setEachExceptionsHandler(new EachExceptionsHandler() {
                @Override
                public void handleIOException(IOException e) {
                    Toast.makeText(getApplicationContext(), "Internet connectivity is weak, or the server " +
                            "delayed to respond. Please try again", Toast.LENGTH_LONG).show();
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

        }

        if (id == R.id.budget_consultation) {

            startActivity(new Intent(FrontActivity.this,BudgetConsutanceActivity.class));

        } else if(id == R.id.budget_monitering){

            startActivity(new Intent(FrontActivity.this,BudgetMoniteringActivity.class));

        } else if(id == R.id.polls){

            startActivity(new Intent(FrontActivity.this,PollsActivity.class));

        } else if(id == R.id.local_government_structure){

            startActivity(new Intent(FrontActivity.this,LocalGovernmentActivity.class));

        }

        else if(id == R.id.life_cycle){

            startActivity(new Intent(FrontActivity.this,BudgetLifeCycleActivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}