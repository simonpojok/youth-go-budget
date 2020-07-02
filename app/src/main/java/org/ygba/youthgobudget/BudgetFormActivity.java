package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
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

import org.ygba.youthgobudget.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Queue;

public class BudgetFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button savebtn;
    EditText staffname,daterecorded,agric_service_spec,
            agric_service_reason,agric_service_worst_spec,agric_service_reason_worst,
            agric_service_problem_spec,agric_service_reason_problem,agric_service_need_spec,
            agric_service_reason_need,gric_service_reason_need,Agric_proposed_priority_spec,
            Agric_proposed_priority_reason,agric_service_need;

    Spinner agecategory,region,agric_service_best,agric_service_worst,agric_service_problem,gender,subcounty,district,Agric_proposed_priority;

    String staffName,dateRecorded,ageCategory,Region,District,Subcounty,
            Agric_service_best,Agric_service_spec,Agric_service_reason,
            Agric_service_worst,Agric_service_worst_spec,Agric_service_reason_worst,
            Agric_service_problem,Agric_service_problem_spec,Agric_service_reason_problem,
            PHONE_ID,Gender,Agric_service_need,Agric_service_need_spec,Agric_service_reason_need,
            agric_proposed_priority_spec,agric_proposed_priority_reason,agric_proposed_priority,financialYear;

    String phoneNumber,Parish,ifNoWhy,extensionServicesExpectedorApproved,extensionServicesAmountReceived,extensionServicesDateReceived,
            extensionServicesDateWithdrawn,developmentExpectedorApproved,developmentAmountReceived,developmentDateReceived,
            developmentDateWithdrawn,numberofFieldVisitsforFarmerSupportConductedDuringtheQuarter,
            ifyeshowmanyDuringtheQuarter,mentiontheAreaswheretheMeetingsorWorkshopswereHeld,
            ifnonewhataretheReasonsfornotConductingtheCommunityMeetingsonFarming,
            ifyeshowmanyweredoneDuringtheQuarter,mentiontheAreaswheretheVisitswereMade,malenumberofFarmersVisitedforAdvisoryServices,
            femaleNumberofFarmersVisitedforAdvisoryServices,whataretheReasonsfornotConductingtheFarmerAdvisoryServicesVisits,
            inputsorPlantingMaterialsOne,dateOne,malethatReceivedInputOne,femalethatReceivedInputOne,subcountyorVillageOne,
            inputsorPlantingMaterialsTwo,dateTwo,malethatReceivedInputTwo,femalethatReceivedInputTwo,subCountyorVillageTwo,
            inputsorplantingMaterialsThree,dateThree,malethatReceivedInputThree,femalethatReceivedInputThree,subCountyorVillageThree,
            inputsorPlantingMaterialsFour,dateFour,malethatReceivedInputFour,femalethatReceivedInputFour,subcountyorVillageFour,
            inputsorPlantingMaterialsFive,dateFive,malethatReceivedInputFive,femaleThatReceivedInputFive,subcountyorvillageFive,
            inputsorPlantingMaterialsSix,dateSix,malethatReceivedInputSix,femalethatReceivedInputSix,subCountyorVillageSix,
            ifNonWhataretheReasonsForNotProvidingFarmerswithAgricultureInputsandLivestockDuringtheQuarter,Quoter,advisoryDemonstrationCommunity,
            areThereanyAdvisoryServices,haveYouGivenAnyAgricultureInputsandLivestock,doestheSubCountyDivisionHaveASubstantiveAgriculturalExtensionWorker;




//    =============================================================New Data  =================================

    private EditText financial_year,phone_number,parish,if_no_why,
            extension_services_expected_or_approved,extension_services_amount_received,extension_services_date_received,extension_services_date_withdrawn,
            development_expected_or_approved,development_amount_received,development_date_received,development_date_withdrawn,
            number_of_field_visits_for_farmer_support_conducted_during_the_quarter,if_yes_how_many_during_the_quarter,
            mention_the_areas_where_the_meetings_or_workshops_were_held,if_none_what_are_the_reasons_for_not_conducting_the_community_meetings_on_farming,
            if_yes_how_many_were_done_during_the_quarter,mention_the_areas_where_the_visits_were_made,male_number_of_farmers_visited_for_advisory_services,
            female_number_of_farmers_visited_for_advisory_services,what_are_the_reasons_for_not_conducting_the_farmer_advisory_services_visits,
            inputs_or_planting_materials_one,date_one,male_that_received_input_one,female_that_received_input_one,sub_county_or_village_one,
            inputs_or_planting_materials_two,date_two,male_that_received_input_two,female_that_received_input_two,sub_county_or_village_two,
            inputs_or_planting_materials_three,date_three,male_that_received_input_three,female_that_received_input_three,sub_county_or_village_three,
            inputs_or_planting_materials_four,date_four,male_that_received_input_four,female_that_received_input_four,sub_county_or_village_four,
            inputs_or_planting_materials_five,date_five,male_that_received_input_five,female_that_received_input_five,sub_county_or_village_five,
            inputs_or_planting_materials_six,date_six,male_that_received_input_six,female_that_received_input_six,sub_county_or_village_six,
            if_non_what_are_the_reasons_for_not_providing_farmers_with_agriculture_inputs_and_livestock_during_the_quarter;

    private Spinner quoter,advisory_demonstration_community,are_there_any_advisory_services,have_you_given_any_agriculture_inputs_and_livestock,
            does_the_sub_county_division_have_a_substantive_agricultural_extension_worker;


//  ============================================  End here =================================================



    private DatabaseHelper databaseHelper;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_form);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myCalendar = Calendar.getInstance();

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BudgetFormActivity.this,AgricListActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Agriculture");
        databaseHelper = new DatabaseHelper(BudgetFormActivity.this);
        initionaliser();


        listItems = getResources().getStringArray(R.array.agric_service_array);
        checkedItems = new boolean[listItems.length];
        agric_service_need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(BudgetFormActivity.this);
                mBuilder.setTitle("List of services");
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
                                }catch(Exception e){}

                            }
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String items = "";
                        for (int i = 0; i < mUserItems.size(); i++){
                            items = items + listItems[mUserItems.get(i)];
                            if (i != (mUserItems.size()-1)){
                                items = items + ", ";
                            }
                        }
                        agric_service_need.setText(items);
                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i=0; i < checkedItems.length; i++){
                            checkedItems[i] = false;
                            mUserItems.clear();
                            agric_service_need.setText("");
                        }
                    }
                });

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }

    private void initionaliser() {

        savebtn = findViewById(R.id.save_form);
        staffname = findViewById(R.id.staff_name);
        daterecorded = findViewById(R.id.date_recorded);
        agecategory = findViewById(R.id.age_category);

        region = findViewById(R.id.region);
        district = findViewById(R.id.district);
        subcounty = findViewById(R.id.subcounty);
//=================================   New Data =============================
        financial_year = findViewById(R.id.financial_year);
        phone_number = findViewById(R.id.phone_number);
        parish = findViewById(R.id.parish);
        if_no_why = findViewById(R.id.if_no_why);
        extension_services_expected_or_approved = findViewById(R.id.extension_services_expected_or_approved);
        extension_services_amount_received = findViewById(R.id.extension_services_amount_received);
        extension_services_date_received = findViewById(R.id.extension_services_date_received);
        extension_services_date_withdrawn = findViewById(R.id.extension_services_date_withdrawn);
        development_expected_or_approved = findViewById(R.id.development_expected_or_approved);
        development_amount_received = findViewById(R.id.development_amount_received);
        development_date_received = findViewById(R.id.development_date_received);
        development_date_withdrawn = findViewById(R.id.development_date_withdrawn);
        number_of_field_visits_for_farmer_support_conducted_during_the_quarter = findViewById(R.id.number_of_field_visits_for_farmer_support_conducted_during_the_quarter);
        if_yes_how_many_during_the_quarter = findViewById(R.id.if_yes_how_many_during_the_quarter);
        mention_the_areas_where_the_meetings_or_workshops_were_held = findViewById(R.id.mention_the_areas_where_the_meetings_or_workshops_were_held);
        if_none_what_are_the_reasons_for_not_conducting_the_community_meetings_on_farming = findViewById(R.id.if_none_what_are_the_reasons_for_not_conducting_the_community_meetings_on_farming);
        if_yes_how_many_were_done_during_the_quarter = findViewById(R.id.if_yes_how_many_were_done_during_the_quarter);
        mention_the_areas_where_the_visits_were_made = findViewById(R.id.mention_the_areas_where_the_visits_were_made);
        male_number_of_farmers_visited_for_advisory_services = findViewById(R.id.male_number_of_farmers_visited_for_advisory_services);
        male_number_of_farmers_visited_for_advisory_services = findViewById(R.id.male_number_of_farmers_visited_for_advisory_services);
        female_number_of_farmers_visited_for_advisory_services = findViewById(R.id.female_number_of_farmers_visited_for_advisory_services);
        what_are_the_reasons_for_not_conducting_the_farmer_advisory_services_visits = findViewById(R.id.what_are_the_reasons_for_not_conducting_the_farmer_advisory_services_visits);
        inputs_or_planting_materials_one = findViewById(R.id.inputs_or_planting_materials_one);
        date_one = findViewById(R.id.date_one);
        male_that_received_input_one = findViewById(R.id.male_that_received_input_one);
        female_that_received_input_one = findViewById(R.id.female_that_received_input_one);
        sub_county_or_village_one = findViewById(R.id.sub_county_or_village_one);
        inputs_or_planting_materials_two = findViewById(R.id.inputs_or_planting_materials_two);
        date_two = findViewById(R.id.date_two);
        male_that_received_input_two = findViewById(R.id.male_that_received_input_two);
        female_that_received_input_two = findViewById(R.id.female_that_received_input_two);
        sub_county_or_village_two = findViewById(R.id.sub_county_or_village_two);
        inputs_or_planting_materials_three = findViewById(R.id.inputs_or_planting_materials_three);
        date_three = findViewById(R.id.date_three);
        male_that_received_input_three = findViewById(R.id.male_that_received_input_three);
        female_that_received_input_three = findViewById(R.id.female_that_received_input_three);
        sub_county_or_village_three = findViewById(R.id.sub_county_or_village_three);
        inputs_or_planting_materials_four = findViewById(R.id.inputs_or_planting_materials_four);
        date_four = findViewById(R.id.date_four);
        male_that_received_input_four = findViewById(R.id.male_that_received_input_four);
        female_that_received_input_four = findViewById(R.id.female_that_received_input_four);
        sub_county_or_village_four = findViewById(R.id.sub_county_or_village_four);
        inputs_or_planting_materials_five = findViewById(R.id.inputs_or_planting_materials_five);
        date_five = findViewById(R.id.date_five);
        male_that_received_input_five = findViewById(R.id.male_that_received_input_five);
        female_that_received_input_five = findViewById(R.id.female_that_received_input_five);
        sub_county_or_village_five = findViewById(R.id.sub_county_or_village_five);
        inputs_or_planting_materials_six = findViewById(R.id.inputs_or_planting_materials_six);
        date_six = findViewById(R.id.date_six);
        male_that_received_input_six = findViewById(R.id.male_that_received_input_six);
        male_that_received_input_six = findViewById(R.id.male_that_received_input_six);
        female_that_received_input_six = findViewById(R.id.female_that_received_input_six);
        sub_county_or_village_six = findViewById(R.id.sub_county_or_village_six);
        if_non_what_are_the_reasons_for_not_providing_farmers_with_agriculture_inputs_and_livestock_during_the_quarter = findViewById(R.id.if_non_what_are_the_reasons_for_not_providing_farmers_with_agriculture_inputs_and_livestock_during_the_quarter);

//        Spinners
        quoter = findViewById(R.id.quoter);
        advisory_demonstration_community = findViewById(R.id.advisory_demonstration_community);
        are_there_any_advisory_services = findViewById(R.id.are_there_any_advisory_services);
        have_you_given_any_agriculture_inputs_and_livestock = findViewById(R.id.have_you_given_any_agriculture_inputs_and_livestock);
        does_the_sub_county_division_have_a_substantive_agricultural_extension_worker = findViewById(R.id.does_the_sub_county_division_have_a_substantive_agricultural_extension_worker);


//        ============================= End new data =====================================


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

                Log.d("Sucounty",lables.toString());

                Log.d("Subcounty",databaseHelper.cur2Json(databaseHelper.reaDataSubcounty()).toString());

                loadSpinnerData(subcounty,lables);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gender =  findViewById(R.id.gender);

        agric_service_need =   findViewById(R.id.agric_service_need);
        agric_service_reason_need =  findViewById(R.id.agric_service_reason_need);
        agric_service_need_spec =  findViewById(R.id.agric_service_need_spec);

        agric_service_best =  findViewById(R.id.agric_service_best);
        agric_service_spec =  findViewById(R.id.agric_service_spec);
        agric_service_reason = findViewById(R.id.agric_service_reason);

        agric_service_worst =  findViewById(R.id.agric_service_worst);
        agric_service_worst_spec = findViewById(R.id.agric_service_worst_spec);
        agric_service_reason_worst = findViewById(R.id.agric_service_reason_worst);

        agric_service_problem = findViewById(R.id.agric_service_problem);
        agric_service_problem_spec = findViewById(R.id.agric_service_problem_spec);
        agric_service_reason_problem = findViewById(R.id.Agric_service_reason_problem);

        Agric_proposed_priority = findViewById(R.id.Agric_proposed_priority);
        Agric_proposed_priority_spec = findViewById(R.id.Agric_proposed_priority_spec);
        Agric_proposed_priority_reason = findViewById(R.id.Agric_proposed_priority_reason);


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        daterecorded.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(BudgetFormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                staffName = staffname.getText().toString();
                dateRecorded = daterecorded.getText().toString();
                ageCategory = String.valueOf(agecategory.getSelectedItem());
                Gender = String.valueOf(gender.getSelectedItem());
                Region = String.valueOf(region.getSelectedItem());
                Agric_service_need = agric_service_need.getText().toString();
                District = String.valueOf(district.getSelectedItem());
                Subcounty = String.valueOf(subcounty.getSelectedItem());
                Parish = parish.getText().toString();

                financialYear = financial_year.getText().toString();

                Agric_service_need_spec = agric_service_need_spec.getText().toString();
                Agric_service_reason_need = agric_service_reason_need.getText().toString();

                agric_proposed_priority = String.valueOf(Agric_proposed_priority.getSelectedItem());
                agric_proposed_priority_spec = Agric_proposed_priority_spec.getText().toString();
                agric_proposed_priority_reason = Agric_proposed_priority_reason.getText().toString();

                Agric_service_best = String.valueOf(agric_service_best.getSelectedItem());
                Agric_service_spec = agric_service_spec.getText().toString();
                Agric_service_reason = agric_service_reason.getText().toString();

                Agric_service_worst = String.valueOf(agric_service_worst.getSelectedItem());
                Agric_service_worst_spec = agric_service_worst_spec.getText().toString();
                Agric_service_reason_worst = agric_service_reason_worst.getText().toString();

                Agric_service_problem = String.valueOf(agric_service_problem.getSelectedItem());
                Agric_service_problem_spec = agric_service_problem_spec.getText().toString();
                Agric_service_reason_problem = agric_service_reason_problem.getText().toString();

                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                PHONE_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+""+date;


                phoneNumber = phone_number.getText().toString();
                Parish = parish.getText().toString();
                ifNoWhy = if_no_why.getText().toString();
                extensionServicesExpectedorApproved =  extension_services_expected_or_approved.getText().toString();
                extensionServicesAmountReceived = extension_services_amount_received.getText().toString();
                extensionServicesDateReceived = extension_services_date_received.getText().toString();
                extensionServicesDateWithdrawn = extension_services_date_withdrawn.getText().toString();
                developmentExpectedorApproved = development_expected_or_approved.getText().toString();
                developmentAmountReceived = development_amount_received.getText().toString();
                developmentDateReceived = development_date_received.getText().toString();
                developmentDateWithdrawn = development_date_withdrawn.getText().toString();
                numberofFieldVisitsforFarmerSupportConductedDuringtheQuarter = number_of_field_visits_for_farmer_support_conducted_during_the_quarter.getText().toString();
                ifyeshowmanyDuringtheQuarter = if_yes_how_many_during_the_quarter.getText().toString();
                mentiontheAreaswheretheMeetingsorWorkshopswereHeld = mention_the_areas_where_the_meetings_or_workshops_were_held.getText().toString();
                ifnonewhataretheReasonsfornotConductingtheCommunityMeetingsonFarming = if_none_what_are_the_reasons_for_not_conducting_the_community_meetings_on_farming.getText().toString();
                ifyeshowmanyweredoneDuringtheQuarter = if_yes_how_many_were_done_during_the_quarter.getText().toString();
                mentiontheAreaswheretheVisitswereMade = mention_the_areas_where_the_visits_were_made.getText().toString();
                malenumberofFarmersVisitedforAdvisoryServices = male_number_of_farmers_visited_for_advisory_services.getText().toString();
                femaleNumberofFarmersVisitedforAdvisoryServices = female_number_of_farmers_visited_for_advisory_services.getText().toString();
                whataretheReasonsfornotConductingtheFarmerAdvisoryServicesVisits = what_are_the_reasons_for_not_conducting_the_farmer_advisory_services_visits.getText().toString();
                inputsorPlantingMaterialsOne = inputs_or_planting_materials_one.getText().toString();
                dateOne = date_one.getText().toString();
                malethatReceivedInputOne = male_that_received_input_one.getText().toString();
                femalethatReceivedInputOne = female_that_received_input_one.getText().toString();
                subcountyorVillageOne = sub_county_or_village_one.getText().toString();
                inputsorPlantingMaterialsTwo = inputs_or_planting_materials_two.getText().toString();
                dateTwo = date_two.getText().toString();
                malethatReceivedInputTwo = male_that_received_input_two.getText().toString();
                femalethatReceivedInputTwo = female_that_received_input_two.getText().toString();
                subCountyorVillageTwo = sub_county_or_village_two.getText().toString();
                inputsorplantingMaterialsThree = inputs_or_planting_materials_three.getText().toString();
                dateThree = date_three.getText().toString();
                malethatReceivedInputThree = male_that_received_input_three.getText().toString();
                femalethatReceivedInputThree = female_that_received_input_three.getText().toString();
                subCountyorVillageThree = sub_county_or_village_three.getText().toString();
                inputsorPlantingMaterialsFour = inputs_or_planting_materials_four.getText().toString();
                dateFour = date_four.getText().toString();
                malethatReceivedInputFour = male_that_received_input_four.getText().toString();
                femalethatReceivedInputFour = female_that_received_input_four.getText().toString();
                subcountyorVillageFour = sub_county_or_village_four.getText().toString();
                inputsorPlantingMaterialsFive = inputs_or_planting_materials_five.getText().toString();
                dateFive = date_five.getText().toString();
                malethatReceivedInputFive = male_that_received_input_five.getText().toString();
                femaleThatReceivedInputFive = female_that_received_input_five.getText().toString();
                subcountyorvillageFive = sub_county_or_village_five.getText().toString();
                inputsorPlantingMaterialsSix = inputs_or_planting_materials_six.getText().toString();
                dateSix = date_six.getText().toString();
                malethatReceivedInputSix = male_that_received_input_six.getText().toString();
                femalethatReceivedInputSix = female_that_received_input_six.getText().toString();
                subCountyorVillageSix = sub_county_or_village_six.getText().toString();
                ifNonWhataretheReasonsForNotProvidingFarmerswithAgricultureInputsandLivestockDuringtheQuarter = if_non_what_are_the_reasons_for_not_providing_farmers_with_agriculture_inputs_and_livestock_during_the_quarter.getText().toString();
                Quoter = String.valueOf(quoter.getSelectedItem());
                advisoryDemonstrationCommunity = String.valueOf(advisory_demonstration_community.getSelectedItem());
                areThereanyAdvisoryServices = String.valueOf(are_there_any_advisory_services.getSelectedItem());
                haveYouGivenAnyAgricultureInputsandLivestock = String.valueOf(have_you_given_any_agriculture_inputs_and_livestock.getSelectedItem());
                doestheSubCountyDivisionHaveASubstantiveAgriculturalExtensionWorker =  String.valueOf(does_the_sub_county_division_have_a_substantive_agricultural_extension_worker.getSelectedItem());



                databaseHelper.save_agriculture(staffName,dateRecorded,ageCategory,Region,District,
                        Subcounty,Parish,Agric_service_best,Agric_service_spec,Agric_service_reason,
                        Agric_service_worst,Agric_service_worst_spec,Agric_service_reason_worst,
                        Agric_service_problem,Agric_service_problem_spec,Agric_service_reason_problem,
                        PHONE_ID,Gender,Agric_service_need,Agric_service_need_spec,Agric_service_reason_need,
                        agric_proposed_priority,agric_proposed_priority_spec,agric_proposed_priority_reason,financialYear,phoneNumber,ifNoWhy,
                        extensionServicesExpectedorApproved,extensionServicesAmountReceived,extensionServicesDateReceived,
                        extensionServicesDateWithdrawn,developmentExpectedorApproved,developmentAmountReceived,developmentDateReceived,
                        developmentDateWithdrawn,numberofFieldVisitsforFarmerSupportConductedDuringtheQuarter,ifyeshowmanyDuringtheQuarter,
                        mentiontheAreaswheretheMeetingsorWorkshopswereHeld, ifnonewhataretheReasonsfornotConductingtheCommunityMeetingsonFarming,
                        ifyeshowmanyweredoneDuringtheQuarter,mentiontheAreaswheretheVisitswereMade,malenumberofFarmersVisitedforAdvisoryServices,
                        femaleNumberofFarmersVisitedforAdvisoryServices,whataretheReasonsfornotConductingtheFarmerAdvisoryServicesVisits,
                        inputsorPlantingMaterialsOne,dateOne,malethatReceivedInputOne,femalethatReceivedInputOne,subcountyorVillageOne,
                        inputsorPlantingMaterialsTwo,dateTwo,malethatReceivedInputTwo,
                        femalethatReceivedInputTwo,subCountyorVillageTwo,inputsorplantingMaterialsThree,dateThree,malethatReceivedInputThree,
                        femalethatReceivedInputThree,subCountyorVillageThree,inputsorPlantingMaterialsFour,dateFour,malethatReceivedInputFour,
                        femalethatReceivedInputFour,subcountyorVillageFour,inputsorPlantingMaterialsFive,dateFive,malethatReceivedInputFive,
                        femaleThatReceivedInputFive,subcountyorvillageFive,inputsorPlantingMaterialsSix,dateSix,malethatReceivedInputSix,
                        femalethatReceivedInputSix,subCountyorVillageSix,ifNonWhataretheReasonsForNotProvidingFarmerswithAgricultureInputsandLivestockDuringtheQuarter,
                        Quoter,advisoryDemonstrationCommunity,areThereanyAdvisoryServices,haveYouGivenAnyAgricultureInputsandLivestock,
                        doestheSubCountyDivisionHaveASubstantiveAgriculturalExtensionWorker);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BudgetFormActivity.this);
                alertDialogBuilder.setTitle("Data record response");
                alertDialogBuilder.setMessage("Your data has been saved locally on you device");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        staffname.setText(""); daterecorded.setText("");
                        parish.setText(""); agric_service_spec.setText(""); agric_service_reason.setText("");
                        agric_service_worst_spec.setText(""); agric_service_reason_worst.setText("");
                        agric_service_problem_spec.setText(""); agric_service_reason_problem.setText("");
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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        daterecorded.setText(sdf.format(myCalendar.getTime()));
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
