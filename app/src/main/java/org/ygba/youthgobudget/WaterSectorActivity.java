package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.ygba.youthgobudget.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WaterSectorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button save_water_sanitation;

    private DatabaseHelper databaseHelper;

    private Spinner quarter, region, district, subcounty, division_have_water_sanitation, are_there_rural_safe_water_point_sources_constructed,
            do_the_rural_water_point_sources_have_user_committees;

    private EditText financial_year, date, parish, village, name_of_monitor, phone_number, division_have_water_sanitation_if_no,
            what_are_the_water_sanitationt_protection_activities_taking_place_in_the_sub_county, area_in_the_sub_county_one, water_source_one,
            functional_one, non_functional_one, no_water_source_available_one, area_in_the_sub_county_two, water_source_two, functional_two, non_functional_two,
            no_water_source_available_two, area_in_the_sub_county_three, water_source_three, functional_three,non_functional_three, no_water_source_available_three,
            area_in_the_sub_county_four, water_source_four, functional_four, non_functional_four, no_water_source_available_four, area_in_the_sub_county_five,
            water_source_five, functional_five, non_functional_five, no_water_source_available_five, area_in_the_sub_county_six, water_source_six,
            functional_six, non_functional_six, no_water_source_available_six, if_yes_do_the_rural_water_point_sources_have_user_committees,
            are_there_any_wetlands_demarcated_or_protected, name_of_the_sub_county_village_two, wetland_under_destruction_two, name_of_the_sub_county_village_three,
            wetland_under_destruction_three, name_of_the_sub_county_village_four, wetland_under_destruction_four,name_of_the_sub_county_village_five,
            wetland_under_destruction_five, name_of_the_sub_county_village_six, wetland_under_destruction_six,what_are_the_tree_planting_programs_known_in_the_area;

    private String financialYear, Date, Parish, Village, nameOfMonitor, phoneNumber, divisionHaveWaterSanitationIfNo,
            whatAreTheWaterSanitationtProtectionActivitiesTakingPlaceInTheSubCounty, areaInTheSubCountyOne, waterSourceOne,
            functionalOne, nonFunctionalOne, noWaterSourceAvailableOne, areaInTheSubCountyTwo, waterSourceTwo, functionalTwo, nonFunctionalTwo,
            noWaterSourceAvailableTwo, areaInTheSubCountyThree, waterSourceThree, functionalThree,nonFunctionalThree, noWaterSourceAvailableThree,
            areaInTheSubCountyFour, waterSourceFour, functionalFour, nonFunctionalFour, noWaterSourceAvailableFour, areaInTheSubCountyFive,
            waterSourceFive, functionalFive, nonFunctionalFive, noWaterSourceAvailableFive, areaInTheSubCountySix, waterSourceSix,
            functionalSix, nonFunctionalSix, noWaterSourceAvailableSix, ifYesDoTheRuralWaterPointSourcesHaveUserCommittees,
            areThereAnyWetlandsDemarcatedOrProtected, nameOfTheSubCountyVillageTwo, wetlandUnderDestructionTwo, nameOfTheSubCountyVillageThree,
            wetlandUnderDestructionThree, nameOfTheSubCountyVillageFour, wetlandUnderDestructionFour,nameOfTheSubCountyVillageFive,
            wetlandUnderDestruction_five, nameOfTheSubCountyVillageSix, wetlandUnderDestructionSix,whatAreTheTreePlantingProgramsKnownInTheArea,
            Quarter, Region, District, Subcounty, divisionHaveWaterSanitation, areThereRuralSafeWaterPointSourcesConstructed,
            doTheRuralWaterPointSourcesHaveUserCommittees,PHONE_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_sector);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WaterSectorActivity.this, WaterSectorRecordsActivity.class));
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        save_water_sanitation = findViewById(R.id.save_water_sanitation);

        databaseHelper = new DatabaseHelper(WaterSectorActivity.this);

        financial_year = findViewById(R.id.financial_year);
        date = findViewById(R.id.date);
        parish = findViewById(R.id.parish);
        village = findViewById(R.id.village);
        name_of_monitor = findViewById(R.id.name_of_monitor);
        phone_number = findViewById(R.id.phone_number);
        division_have_water_sanitation_if_no = findViewById(R.id.division_have_water_sanitation_if_no);
        what_are_the_water_sanitationt_protection_activities_taking_place_in_the_sub_county = findViewById(R.id.what_are_the_water_sanitationt_protection_activities_taking_place_in_the_sub_county);
        area_in_the_sub_county_one = findViewById(R.id.area_in_the_sub_county_one);
        water_source_one = findViewById(R.id.water_source_one);
        functional_one = findViewById(R.id.functional_one);
        non_functional_one = findViewById(R.id.non_functional_one);
        no_water_source_available_one = findViewById(R.id.no_water_source_available_one);
        area_in_the_sub_county_two = findViewById(R.id.area_in_the_sub_county_two);
        water_source_two = findViewById(R.id.water_source_two);
        functional_two = findViewById(R.id.functional_two);
        non_functional_two = findViewById(R.id.non_functional_two);
        no_water_source_available_two = findViewById(R.id.no_water_source_available_two);
        area_in_the_sub_county_three = findViewById(R.id.area_in_the_sub_county_three);
        water_source_three = findViewById(R.id.water_source_three);
        functional_three = findViewById(R.id.functional_three);
        non_functional_three = findViewById(R.id.non_functional_three);
        no_water_source_available_three = findViewById(R.id.no_water_source_available_three);
        area_in_the_sub_county_four = findViewById(R.id.area_in_the_sub_county_four);
        water_source_four = findViewById(R.id.water_source_four);
        functional_four = findViewById(R.id.functional_four);
        non_functional_four = findViewById(R.id.non_functional_four);
        no_water_source_available_four = findViewById(R.id.no_water_source_available_four);
        area_in_the_sub_county_five = findViewById(R.id.area_in_the_sub_county_five);
        water_source_five = findViewById(R.id.water_source_five);
        functional_five = findViewById(R.id.functional_five);
        non_functional_five = findViewById(R.id.non_functional_five);
        no_water_source_available_five = findViewById(R.id.no_water_source_available_five);
        area_in_the_sub_county_six = findViewById(R.id.area_in_the_sub_county_six);
        water_source_six = findViewById(R.id.water_source_six);
        functional_six = findViewById(R.id.functional_six);
        non_functional_six = findViewById(R.id.non_functional_six);
        no_water_source_available_six = findViewById(R.id.no_water_source_available_six);
        if_yes_do_the_rural_water_point_sources_have_user_committees = findViewById(R.id.if_yes_do_the_rural_water_point_sources_have_user_committees);
        are_there_any_wetlands_demarcated_or_protected = findViewById(R.id.are_there_any_wetlands_demarcated_or_protected);
        name_of_the_sub_county_village_two = findViewById(R.id.name_of_the_sub_county_village_two);
        wetland_under_destruction_two = findViewById(R.id.wetland_under_destruction_two);
        name_of_the_sub_county_village_three = findViewById(R.id.name_of_the_sub_county_village_three);
        wetland_under_destruction_three = findViewById(R.id.wetland_under_destruction_three);
        name_of_the_sub_county_village_four = findViewById(R.id.name_of_the_sub_county_village_four);
        wetland_under_destruction_four = findViewById(R.id.wetland_under_destruction_four);
        name_of_the_sub_county_village_five = findViewById(R.id.name_of_the_sub_county_village_five);
        wetland_under_destruction_five = findViewById(R.id.wetland_under_destruction_five);
        name_of_the_sub_county_village_six = findViewById(R.id.name_of_the_sub_county_village_six);
        wetland_under_destruction_six = findViewById(R.id.wetland_under_destruction_six);
        what_are_the_tree_planting_programs_known_in_the_area = findViewById(R.id.what_are_the_tree_planting_programs_known_in_the_area);

        quarter = findViewById(R.id.quarter);
        region = findViewById(R.id.region);
        district = findViewById(R.id.district);
        subcounty = findViewById(R.id.subcounty);
        division_have_water_sanitation = findViewById(R.id.division_have_water_sanitation);
        are_there_rural_safe_water_point_sources_constructed = findViewById(R.id.are_there_rural_safe_water_point_sources_constructed);
        do_the_rural_water_point_sources_have_user_committees = findViewById(R.id.do_the_rural_water_point_sources_have_user_committees);


        List<String> lables = databaseHelper.reaDataTable("regions");

        loadSpinnerData(region,lables);

        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();

                List<String> lables = databaseHelper.readDistricts(label);

                Log.d("District",lables.toString());

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


        save_water_sanitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                financialYear = financial_year.getText().toString();
                Date = date.getText().toString();
                Parish = parish.getText().toString();
                Village = village.getText().toString();
                nameOfMonitor = name_of_monitor.getText().toString();
                phoneNumber = phone_number.getText().toString();
                divisionHaveWaterSanitationIfNo = division_have_water_sanitation_if_no.getText().toString();
                whatAreTheWaterSanitationtProtectionActivitiesTakingPlaceInTheSubCounty = what_are_the_water_sanitationt_protection_activities_taking_place_in_the_sub_county.getText().toString();
                areaInTheSubCountyOne = area_in_the_sub_county_one.getText().toString();
                waterSourceOne = water_source_one.getText().toString();
                functionalOne = functional_one.getText().toString();
                nonFunctionalOne = non_functional_one.getText().toString();
                noWaterSourceAvailableOne = no_water_source_available_one.getText().toString();
                areaInTheSubCountyTwo = area_in_the_sub_county_two.getText().toString();
                waterSourceTwo = water_source_two.getText().toString();
                functionalTwo = functional_two.getText().toString();
                nonFunctionalTwo = non_functional_two.getText().toString();
                noWaterSourceAvailableTwo = no_water_source_available_two.getText().toString();
                areaInTheSubCountyThree = area_in_the_sub_county_three.getText().toString();
                waterSourceThree = water_source_three.getText().toString();
                functionalThree = functional_three.getText().toString();
                nonFunctionalThree = non_functional_three.getText().toString();
                noWaterSourceAvailableThree = no_water_source_available_three.getText().toString();
                areaInTheSubCountyFour = area_in_the_sub_county_four.getText().toString();
                waterSourceFour = water_source_four.getText().toString();
                functionalFour = functional_four.getText().toString();
                nonFunctionalFour = non_functional_four.getText().toString();
                noWaterSourceAvailableFour = no_water_source_available_four.getText().toString();
                areaInTheSubCountyFive = area_in_the_sub_county_five.getText().toString();
                waterSourceFive = water_source_five.getText().toString();
                functionalFive = functional_five.getText().toString();
                nonFunctionalFive = non_functional_five.getText().toString();
                noWaterSourceAvailableFive = no_water_source_available_five.getText().toString();
                areaInTheSubCountySix = area_in_the_sub_county_six.getText().toString();
                waterSourceSix = water_source_six.getText().toString();
                functionalSix = functional_six.getText().toString();
                nonFunctionalSix = non_functional_six.getText().toString();
                noWaterSourceAvailableSix = no_water_source_available_six.getText().toString();
                ifYesDoTheRuralWaterPointSourcesHaveUserCommittees = if_yes_do_the_rural_water_point_sources_have_user_committees.getText().toString();
                areThereAnyWetlandsDemarcatedOrProtected = are_there_any_wetlands_demarcated_or_protected.getText().toString();
                nameOfTheSubCountyVillageTwo = name_of_the_sub_county_village_two.getText().toString();
                wetlandUnderDestructionTwo = wetland_under_destruction_two.getText().toString();
                nameOfTheSubCountyVillageThree = name_of_the_sub_county_village_three.getText().toString();
                wetlandUnderDestructionThree = wetland_under_destruction_three.getText().toString();
                nameOfTheSubCountyVillageFour = name_of_the_sub_county_village_four.getText().toString();
                wetlandUnderDestructionFour = wetland_under_destruction_four.getText().toString();
                nameOfTheSubCountyVillageFive = name_of_the_sub_county_village_five.getText().toString();
                wetlandUnderDestruction_five = wetland_under_destruction_five.getText().toString();
                nameOfTheSubCountyVillageSix = name_of_the_sub_county_village_six.getText().toString();
                wetlandUnderDestructionSix = wetland_under_destruction_six.getText().toString();
                whatAreTheTreePlantingProgramsKnownInTheArea = what_are_the_tree_planting_programs_known_in_the_area.getText().toString();
                Quarter = String.valueOf(quarter.getSelectedItem());
                Region = String.valueOf(region.getSelectedItem());
                District = String.valueOf(district.getSelectedItem());
                Subcounty = String.valueOf(subcounty.getSelectedItem());
                divisionHaveWaterSanitation = String.valueOf(division_have_water_sanitation.getSelectedItem());
                areThereRuralSafeWaterPointSourcesConstructed = String.valueOf(are_there_rural_safe_water_point_sources_constructed.getSelectedItem());
                doTheRuralWaterPointSourcesHaveUserCommittees = String.valueOf(do_the_rural_water_point_sources_have_user_committees.getSelectedItem());

                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                PHONE_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+""+date;

                databaseHelper.saveWaterSanitation(financialYear, Date, Parish, Village, nameOfMonitor, phoneNumber, divisionHaveWaterSanitationIfNo,
                        whatAreTheWaterSanitationtProtectionActivitiesTakingPlaceInTheSubCounty, areaInTheSubCountyOne, waterSourceOne,
                        functionalOne, nonFunctionalOne, noWaterSourceAvailableOne, areaInTheSubCountyTwo, waterSourceTwo, functionalTwo, nonFunctionalTwo,
                        noWaterSourceAvailableTwo, areaInTheSubCountyThree, waterSourceThree, functionalThree,nonFunctionalThree, noWaterSourceAvailableThree,
                        areaInTheSubCountyFour, waterSourceFour, functionalFour, nonFunctionalFour, noWaterSourceAvailableFour, areaInTheSubCountyFive,
                        waterSourceFive, functionalFive, nonFunctionalFive, noWaterSourceAvailableFive, areaInTheSubCountySix, waterSourceSix,
                        functionalSix, nonFunctionalSix, noWaterSourceAvailableSix, ifYesDoTheRuralWaterPointSourcesHaveUserCommittees,
                        areThereAnyWetlandsDemarcatedOrProtected, nameOfTheSubCountyVillageTwo, wetlandUnderDestructionTwo, nameOfTheSubCountyVillageThree,
                        wetlandUnderDestructionThree, nameOfTheSubCountyVillageFour, wetlandUnderDestructionFour,nameOfTheSubCountyVillageFive,
                        wetlandUnderDestruction_five, nameOfTheSubCountyVillageSix, wetlandUnderDestructionSix,whatAreTheTreePlantingProgramsKnownInTheArea,
                        Quarter, Region, District, Subcounty, divisionHaveWaterSanitation, areThereRuralSafeWaterPointSourcesConstructed,
                        doTheRuralWaterPointSourcesHaveUserCommittees,PHONE_ID);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WaterSectorActivity.this);
                alertDialogBuilder.setTitle("Data record response");
                alertDialogBuilder.setMessage("Your data has been saved locally on you device");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return;

            }
        });
    }


    private void loadSpinnerData(Spinner spinner,List<String> lables) {

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
