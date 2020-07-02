package org.ygba.youthgobudget.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "youthgobudget_db";

    private static final String TABLE = "agriculture";
    private static final String HEALTH_TABLE = "health";
    private static final String EDUCATION_TABLE = "education";

    private static final String KEY_ID = "record_id";
    private static final String REGION = "region";
    private static final String DISTRICT = "district";
    private static final String SUBCOUNTY = "subcounty";
    private static final String PHONE_ID = "phone_id";
    private static final String GENDER = "gender";
    private static final String AGE_CATEGORY = "ageCategory";

    //================================================== AGRICULTURE =======================================================

    private static final String STAFF_NAME = "staffName";
    private static final String DATE_RECORDED = "date_recorded";
    private static final String PARISH = "parish";
    private static final String AGRIC_SERVICE_BEST = "Agric_service_best";
    private static final String AGRIC_SERVICE_SPEC = "agric_service_spec";
    private static final String AGRIC_SERVICE_REASON = "agric_service_reason";
    private static final String AGRIC_SERVICE_WORST = "agric_service_worst";
    private static final String AGRIC_SERVICE_WORST_SPEC = "agric_service_worst_spec";
    private static final String AGRIC_SERVICE_REASON_WORST = "agric_service_reason_worst";
    private static final String AGRIC_SERVICE_PROBLEM = "Agric_service_problem";
    private static final String AGRIC_SERVICE_PROBLEM_SPEC = "Agric_service_problem_spec";
    private static final String AGRIC_SERVICE_REASON_PROBLEM = "Agric_service_reason_problem";

    private static final String AGRIC_SERVICE_NEED = "Agric_service_need";
    private static final String AGRIC_SERVICE_NEED_SPEC = "Agric_service_need_spec";
    private static final String AGRIC_SERVICE_REASON_NEED = "Agric_service_reason_need";

    private static final String AGRIC_PROPOSED_PRIORITY = "Agric_proposed_priority";
    private static final String AGRIC_PROPOSED_PRIORITY_SPEC = "Agric_proposed_priority_spec";
    private static final String AGRIC_PROPOSED_PRIORITY_REASON = "Agric_proposed_priority_reason";

//====================================================== HEALTH AND EDUCATION ===============================================

    private static final String LEVEL_OF_EDUCATION = "level_of_education";
    private static final String ESTABLISH_HEALTH_EDUCATION = "establish_health_education";
    private static final String YES_ESTABLISH_HEALTH_EDUCATION = "yes_establish_health_education";
    private static final String REASON_ESTABLISH_HEALTH_EDUCATION = "reason_establish_health_education";
    private static final String RATE_ESTABLISH_HEALTH_EDUCATION = "rate_establish_health_education";
    private static final String RATE_REASON_ESTABLISH_HEALTH_EDUCATION = "rate_reason_establish_health_education";
    private static final String BEST_HEALTH_EDUCATION = "best_health_education";
    private static final String BEST_HEALTH_EDUCATION_SPEC = "best_health_education_spec";
    private static final String BEST_HEALTH_EDUCATION_REASON = "best_health_education_reason";
    private static final String WORST_EDUCATION = "worst_health_education";
    private static final String WORST_EDUCATION_REASON_SPEC = "worst_health_education_spec";
    private static final String WORST_EDUCATION_REASON = "worst_health_education_reason";
    private static final String PRIORITY_HEALTH_EDUCATION = "priority_health_education";
    private static final String PRIORITY_HEALTH_EDUCATION_SPEC = "priority_health_education_spec";
    private static final String PRIORITY_HEALTH_EDUCATION_REASON = "priority_health_education_reason";

    //====================================================== EDUCATION ===============================================

    private static final String USE_EDUCATION = "use_education";
    private static final String BEST_USE_EDUCATION_SERVICE = "best_use_education_service";
    private static final String BEST_USE_EDUCATION_SPEC = "best_use_education_spec";
    private static final String BEST_USE_EDUCATION_REASON = "best_use_education_reason";
    private static final String WORST_USE_EDUCATION_SERVICE = "worst_use_education_service";
    private static final String WORST_USE_EDUCATION_SPEC = "worst_use_education_spec";
    private static final String WORST_USE_EDUCATION_REASON = "worst_use_education_reason";
    private static final String PRIORITY_USE_EDUCATION_SERVICE = "priority_use_education_service";
    private static final String PRIORITY_USE_EDUCATION_SPEC = "priority_use_education_spec";
    private static final String PRIORITY_USE_EDUCATION_REASON = "priority_use_education_reason";

    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    Cursor cu;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY, " +
                REGION + " VARCHAR(200) NULL , " +
                "" + DISTRICT + " VARCHAR(100) NULL, " +
                "" + SUBCOUNTY + " VARCHAR(200)  NULL, " +
                "" + PARISH + " VARCHAR(100) NULL, " +
                "" + PHONE_ID + " VARCHAR(160) NULL, " +
                "" + STAFF_NAME + " VARCHAR(120) NULL, " +
                "" + DATE_RECORDED + " VARCHAR(150) NULL, " +
                "" + AGE_CATEGORY + " VARCHAR(150) NULL, " +
                "" + GENDER + " VARCHAR(10) NULL, " +
                "" + AGRIC_PROPOSED_PRIORITY_REASON + " TEXT NULL, financial_year VARCHAR(50) NULL, quarter VARCHAR(20) NULL," +
                "phone_number VARCHAR(30) NULL, is_there_substantive_agricultural_extension_worker VARCHAR(5) NULL," +
                "if_no_why VARCHAR(200) NULL, extension_services_expected_or_approved VARCHAR(200) NULL, extension_services_amount_received VARCHAR(20) NULL," +
                "extension_services_date_received VARCHAR(20) NULL, extension_services_date_withdrawn VARCHAR(20) NULL, development_expected_or_approved VARCHAR(20) NULL," +
                "development_amount_received VARCHAR(20) NULL, development_date_received VARCHAR(20) NULL, development_date_withdrawn VARCHAR(20) NULL, " +
                "number_of_field_visits_for_farmer_support VARCHAR(10) NULL, advisory_demonstration_community VARCHAR(5) NULL, " +
                "if_yes_how_many_during_the_quarter VARCHAR(10) NULL, areas_where_the_meetings_or_workshops_were_held VARCHAR(200) NULL," +
                "reasons_for_not_conducting_the_community_meeting VARCHAR(200) NULL, are_there_any_advisory_services VARCHAR(5) NULL," +
                "if_yes_how_many_were_done_during_the_quarter VARCHAR(10) NULL, mention_the_areas_where_the_visits_were_made VARCHAR(200) NULL, " +
                "male_number_of_farmers_visited_for_advisory_services VARCHAR(20) NULL, female_number_of_farmers_visited_for_advisory_services VARCHAR(20) NULL," +
                "reasons_for_not_conducting_the_farmer_advisory_services_visits VARCHAR(200) NULL, any_owc_inputs VARCHAR(5) NULL," +
                "inputs_or_planting_materials_one VARCHAR(200) NULL, date_one VARCHAR(20) NULL, male_that_received_input_one VARCHAR(20) NULL," +
                "female_that_received_input_one VARCHAR(20) NULL, sub_county_or_village_one VARCHAR(100) NULL, inputs_or_planting_materials_two VARCHAR(200) NULL," +
                "date_two VARCHAR(20) NULL, male_that_received_input_two VARCHAR(10) NULL, female_that_received_input_two VARCHAR(10) NULL, " +
                "sub_county_or_village_two VARCHAR(100) NULL, inputs_or_planting_materials_three VARCHAR(200) NULL, date_three VARCHAR(20) NULL, " +
                "male_that_received_input_three VARCHAR(10) NULL, female_that_received_input_three VARCHAR(10) NULL, sub_county_or_village_three VARCHAR(200) NULL," +
                "inputs_or_planting_materials_four VARCHAR(200) NULL, date_four VARCHAR(20) NULL, male_that_received_input_four VARCHAR(10) NULL, " +
                "female_that_received_input_four VARCHAR(10) NULL, sub_county_or_village_four VARCHAR(100) NULL, inputs_or_planting_materials_five VARCHAR(200) NULL," +
                "date_five VARCHAR(20) NULL, male_that_received_input_five VARCHAR(10) NULL, female_that_received_input_five VARCHAR(10) NULL, sub_county_or_village_five VARCHAR(100) NULL," +
                "inputs_or_planting_materials_six VARCHAR(100) NULL, date_six VARCHAR(20) NULL, male_that_received_input_six VARCHAR(10) NULL, " +
                "female_that_received_input_six VARCHAR(10) NULL, sub_county_or_village_six VARCHAR(10) NULL, if_non_what_are_the_reasons_for_not_providing_farmers_with_agriculture_inputs_and_livestock_during_the_quarter " +
                "VARCHAR(200) NULL,village VARCHAR(100) null)";

        String HEALTH = "CREATE TABLE " + HEALTH_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + REGION + " VARCHAR(100) NOT NULL," +
                "" + DISTRICT + " VARCHAR(10) NOT NULL, " + SUBCOUNTY + " VARCHAR(100) NOT NULL, " + PHONE_ID + " VARCHAR(100) NOT NULL, " +
                "" + GENDER + " VARCHAR(10) NOT NULL, " + AGE_CATEGORY + " VARCHAR(20) NOT NULL, " + LEVEL_OF_EDUCATION + " VARCHAR(50) NOT NULL, " +
                "" + ESTABLISH_HEALTH_EDUCATION + " TEXT NULL, " + YES_ESTABLISH_HEALTH_EDUCATION + " TEXT NULL, " + REASON_ESTABLISH_HEALTH_EDUCATION + " TEXT NULL, " +
                "" + RATE_ESTABLISH_HEALTH_EDUCATION + " TEXT NULL, " + RATE_REASON_ESTABLISH_HEALTH_EDUCATION + " TEXT NULL, " + BEST_HEALTH_EDUCATION + " TEXT NULL," +
                "" + BEST_HEALTH_EDUCATION_SPEC + " TEXT NULL, " + BEST_HEALTH_EDUCATION_REASON + " TEXT NULL, " + WORST_EDUCATION_REASON + " TEXT NULL, " + WORST_EDUCATION_REASON_SPEC + " TEXT NULL, " +
                "" + WORST_EDUCATION + " TEXT NULL, " + PRIORITY_HEALTH_EDUCATION + " TEXT NULL, " + PRIORITY_HEALTH_EDUCATION_SPEC + " TEXT NULL, " +
                "" + PRIORITY_HEALTH_EDUCATION_REASON + " TEXT NULL, financial_year VARCHAR(20) NULL, quarter VARCHAR(5) NULL, date VARCHAR(20) NULL," +
                "parish VARCHAR(100) NULL, village VARCHAR(100) NULL, name_of_monitor VARCHAR(200) NULL, phone_number VARCHAR(30) NULL, " +
                "name_and_grade_of_health_center VARCHAR(200) NULL, number_of_outpatient_opd_attendance_during_the_quarter VARCHAR(20) NULL," +
                "number_of_inpatient_attendances_during_the_quarter VARCHAR(20) NULL, recurrent_approved_budgets VARCHAR(20) NULL, " +
                "recurrent_budget_released VARCHAR(20) NULL, recurrent_date_received VARCHAR(20) NULL, recurrent_date_withdrawn VARCHAR(20) NULL, " +
                "development_approved_budgets VARCHAR(20) NULL, development_budget_released VARCHAR(20) NULL, development_date_received VARCHAR(20) NULL," +
                "development_date_withdrawn VARCHAR(20) NULL, health_facility_display_budget_information VARCHAR(20) NULL, maternity_ward_yes VARCHAR(200) NULL," +
                "maternity_ward_no VARCHAR(200) NULL, general_ward_yes VARCHAR(200) NULL, general_ward_no VARCHAR(200) NULL, delivery_beds_yes VARCHAR(200) NULL," +
                "delivery_beds_no VARCHAR(200) NULL, family_planning_services_yes VARCHAR(200) NULL, family_planning_services_no VARCHAR(200) NULL, " +
                "hiv_counselling_testing_and_treatment_yes VARCHAR(200) NULL, hiv_counselling_testing_and_treatment_no VARCHAR(200) NULL, pmtct_yes VARCHAR(100) NULL, " +
                "pmtct_no VARCHAR(100) NULL,immunization_yes VARCHAR(100) NULL, immunization_no VARCHAR(100) NULL,youth_friendly_corners_yes VARCHAR(100) NULL, " +
                "youth_friendly_corners_no VARCHAR(100) NULL, vaccination_for_hep_b_yes VARCHAR(100) NULL, vaccination_for_hep_b_no VARCHAR(100) NULL, " +
                "live_no_of_deliveries VARCHAR(20) NULL, still_no_of_deliveries VARCHAR(20) NULL, children_immunized_with_the_pentavalent_vaccine VARCHAR(20) NULL, " +
                "facility_open_during_hours_that_are_convenient_for_adolescents VARCHAR(20) NULL, are_srh_services_offered_for_free VARCHAR(5) NULL, " +
                "adolescents_privacy VARCHAR(5) NULL, counseling_and_treatment_rooms_allow_for_privacy VARCHAR(5) NULL,  code_of_conduct_in_place VARCHAR(5) NULL," +
                "transparent_confidential_mechanism_for_adolescents_to_submit_complaints VARCHAR(5) NULL, have_providers_been_trained_to_provide_adolescent_friendly_services VARCHAR(5) NULL," +
                "have_all_staff_been_oriented_to_providing_confidential_adolescent_friendly_services VARCHAR(5) NULL, staff_demonstrate_respect_when_interacting_with_adolescents VARCHAR(5) NULL," +
                "providers_ensure_the_clients_privacy_and_confidentiality VARCHAR(5) NULL, providers_set_aside_sufficient_time_for_client_provider_interaction VARCHAR(5) NULL," +
                "peer_educators_or_peer_counselors_available VARCHAR(5) NULL, health_providers_assessed_using_quality_standard_checklists VARCHAR(5) NULL, " +
                "adolescents_female_and_male_play_a_role_in_the_operation_of_the_health_facility VARCHAR(5) NULL, adolescents_involved_in_monitoring_the_quality_of_srh_service_provision VARCHAR(5) NULL," +
                "can_adolescents_be_seen_in_the_facility_without_the_consent_of_their_parents_or_spouses VARCHAR(5) NULL, wide_range_of_srh_services_available VARCHAR(200) NULL," +
                "written_guidelines_for_providing_adolescent_services VARCHAR(5) NULL, condoms_available_to_both_young_men_and_young_women VARCHAR(5) NULL, educational_materials VARCHAR(5) NULL," +
                "are_referral_mechanisms_in_place_for_medical_emergencies VARCHAR(5) NULL, toilet_number_of_blocks VARCHAR(10) NULL, toilet_number_of_stances VARCHAR(10) NULL," +
                "toilet_male_patient_stances VARCHAR(10) NULL, toilet_female_patient_stances VARCHAR(10) NULL, toilet_health_facility_male_staff VARCHAR(10) NULL, " +
                "toilet_health_facility_female_staff VARCHAR(10) NULL, toilet_health_facility_mixed_staff VARCHAR(10) NULL, toilet_functional VARCHAR(10) NULL, " +
                "toilet_not_functional VARCHAR(10) NULL, latrine_number_of_blocks VARCHAR(10) NULL, latrine_number_of_stances VARCHAR(10) NULL, latrine_male_patient_stances VARCHAR(10) NULL," +
                "latrine_female_patient_stances VARCHAR(10) NULL, latrine_health_facility_male_staff VARCHAR(10) NULL, latrine_health_facility_female_staff VARCHAR(10) NULL, " +
                " latrine_health_facility_mixed_staff VARCHAR(10) NULL, latrine_functional VARCHAR(10) NULL, latrine_not_functional VARCHAR(10) NULL, " +
                "female_change_rooms_number_of_blocks VARCHAR(10) NULL, female_change_rooms_number_of_stances VARCHAR(10) NULL, female_change_rooms_male_patient_stances VARCHAR(10) NULL," +
                "female_change_rooms_patient_stances VARCHAR(10) NULL, female_change_rooms_health_facility_staff VARCHAR(10) NULL, female_change_rooms_health_facility_female_staff VARCHAR(10) NULL," +
                "female_change_rooms_health_facility_mixed_staff VARCHAR(10) NULL, female_change_rooms_functional VARCHAR(10) NULL, female_change_rooms_not_functional VARCHAR(10) NULL," +
                "health_facility_toilet_latrine_accessible_to_patients_with_disabilities VARCHAR(5) NULL, pwd_friendly_facilities_does_the_health_center VARCHAR(100) NULL," +
                "pwd_friendly_facilities_does_the_health_center_specify VARCHAR(100) NULL, number_borehole VARCHAR(10) NULL, borehole_functional VARCHAR(10) NULL, " +
                "borehole_non_functional VARCHAR(10) NULL, tap_borehole VARCHAR(10) NULL, tap_functional VARCHAR(10) NULL, tap_non_functional VARCHAR(10) NULL, " +
                "water_tank_borehole VARCHAR(10) NULL, water_tank_functional VARCHAR(10) NULL, water_tank_non_functional VARCHAR(10) NULL, water_point_accessible_to_pwds VARCHAR(10) NULL," +
                "facility_have_a_functional_water_within_nearby VARCHAR(10) NULL, facility_have_a_functional_water_within_nearby_estimate VARCHAR(10) NULL, " +
                "handwashing_facility_installed_at_the_health_facility VARCHAR(5) NULL, health_unit_management_committee_exist VARCHAR(5) NULL, " +
                "if_yes_how_often_do_they_meet VARCHAR(10) NULL, last_visit_of_suport_supervisor VARCHAR(20) NULL, medical_staff_ceiling VARCHAR(10) NULL, " +
                "total_no_of_medical_staff VARCHAR(10) NULL, no_of_medical_staff_present VARCHAR(10) NULL, non_medical_staff_ceiling VARCHAR(10) NULL, " +
                "total_no_of_non_medical_staff VARCHAR(10) NULL, no_of_non_medical_staff_present VARCHAR(10) NULL, reasons_for_the_absent_staff VARCHAR(10) NULL, " +
                "last_date_of_medical_staff_performance_appraisal VARCHAR(10) NULL, number_of_staff_appraised VARCHAR(10) NULL, " +
                "did_the_hc_receive_a_medical_supplies_consignment_from_national_medical_stores_in_this_quarter VARCHAR(5) NULL, if_yes_please_provide_date_as_per_delivery_note VARCHAR(20) NULL, " +
                "one_stock_required VARCHAR(100) NULL, two_stock_required VARCHAR(100) NULL, three_stock_required VARCHAR(100) NULL, four_stock_required VARCHAR(100) NULL," +
                "five_stock_required VARCHAR(100) NULL, six_stock_required VARCHAR(100) NULL, when_did_the_health_center_last_drugs_consignment_get_finished VARCHAR(20) NULL," +
                "number_of_medical_equipment_bought VARCHAR(10) NULL, does_have_an_ambulance VARCHAR(5) NULL, if_no_how_are_referrals_handled VARCHAR(200) NULL, " +
                "list_any_other_observations_or_challenges_hc VARCHAR(200) NULL )";

        String EDUCATION = "CREATE TABLE " + EDUCATION_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + REGION + " VARCHAR(10) NOT NULL," +
                "" + DISTRICT + " VARCHAR(10) NOT NULL, " + SUBCOUNTY + " VARCHAR(10) NOT NULL, " + PHONE_ID + " VARCHAR(100) NOT NULL, " +
                "" + GENDER + " VARCHAR(10) NOT NULL, " + AGE_CATEGORY + " VARCHAR(20) NOT NULL, " + LEVEL_OF_EDUCATION + " VARCHAR(50) NOT NULL, " +
                "" + USE_EDUCATION + " TEXT NULL, " + BEST_USE_EDUCATION_SERVICE + " TEXT NULL, " +
                "" + BEST_USE_EDUCATION_SPEC + " TEXT NULL, " + BEST_USE_EDUCATION_REASON + " TEXT NULL, " + WORST_USE_EDUCATION_SERVICE + " TEXT NULL, " + WORST_USE_EDUCATION_SPEC + " TEXT NULL," +
                "" + WORST_USE_EDUCATION_REASON + " TEXT NULL, " + PRIORITY_USE_EDUCATION_SERVICE + " TEXT NULL, " + PRIORITY_USE_EDUCATION_SPEC + " TEXT NULL, " +
                "" + PRIORITY_USE_EDUCATION_REASON + " TEXT NULL)";


        String REGIONTABLESTRING = "CREATE TABLE  regions(record_id INTEGER PRIMARY KEY, name VARCHAR(100) NOT NULL, region_id VARCHAR(10) NOT NULL)";

        String DISTRICTTABLESTRING = "CREATE TABLE  districts(record_id INTEGER PRIMARY KEY, name VARCHAR(100) NOT NULL, region_id VARCHAR(10) NOT NULL,district_id VARCHAR(10) NOT NULL)";

        String SUBCOUNTYTABLESTRING = "CREATE TABLE  sub_counties(record_id INTEGER PRIMARY KEY, name VARCHAR(100) NOT NULL, district_id VARCHAR(10) NOT NULL,subcategories_id VARCHAR(10) NOT NULL)";

        String SECTOR = "CREATE TABLE  sectors(record_id INTEGER PRIMARY KEY, name VARCHAR(100) NOT NULL, sector_id VARCHAR(10) NOT NULL)";

        String BUDGETMONITERING = "CREATE TABLE  budget_mornitoring(record_id INTEGER PRIMARY KEY, region_name VARCHAR(15) NOT NULL, " +
                "sector_name VARCHAR(50) NOT NULL,quater VARCHAR(20) NOT NULL,amount_sent VARCHAR(20) NOT NULL, date_received VARCHAR(20) NOT NULL, " +
                "amount_spent VARCHAR(20) NOT NULL, sub_county VARCHAR(50) NOT NULL, district VARCHAR(50) NOT NULL, date_of_monitoring VARCHAR(20) NOT NULL," +
                " region_id VARCHAR(20) NOT NULL, sector_id VARCHAR(20) NOT NULL)";

        String WATERANDSANITATION = "CREATE TABLE  water_and_sanitation(record_id INTEGER PRIMARY KEY, financial_year VARCHAR(10) NULL, " +
                "quarter VARCHAR(10) NULL, date VARCHAR(20) NULL, region VARCHAR(50) NULL, district VARCHAR(50) NULL, subcounty VARCHAR(50) NULL, parish " +
                "VARCHAR(50) NULL, village VARCHAR(50) NULL, name_of_monitor VARCHAR(50) NULL, phone_number VARCHAR(20) NULL, division_have_water_sanitation VARCHAR(10) NULL," +
                "division_have_water_sanitation_if_no VARCHAR(50) NULL, what_are_the_water_sanitationt_protection_activities_taking_place_in_the_sub_county VARCHAR(50) NULL," +
                " are_there_rural_safe_water_point_sources_constructed VARCHAR(50) NULL, area_in_the_sub_county_one VARCHAR(100) NULL, water_source_one VARCHAR(10) NULL," +
                "functional_one VARCHAR(10) NULL, non_functional_one VARCHAR(10) NULL, no_water_source_available_one VARCHAR(10) NULL, area_in_the_sub_county_two VARCHAR(100) NULL," +
                "water_source_two VARCHAR(10) NULL, functional_two VARCHAR(10) NULL, non_functional_two VARCHAR(10) NULL, no_water_source_available_two VARCHAR(10) NULL, " +
                "area_in_the_sub_county_three VARCHAR(10) NULL, water_source_three VARCHAR(10) NULL, functional_three VARCHAR(10) NULL, non_functional_three VARCHAR(10) NULL, " +
                "no_water_source_available_three VARCHAR(10) NULL, area_in_the_sub_county_four VARCHAR(50) NULL, water_source_four VARCHAR(10) NULL, functional_four VARCHAR(10) NULL," +
                "non_functional_four VARCHAR(10) NULL, no_water_source_available_four VARCHAR(10) NULL, area_in_the_sub_county_five VARCHAR(50) NULL, water_source_five VARCHAR(10) NULL, " +
                "functional_five VARCHAR(10) NULL, non_functional_five VARCHAR(10) NULL, no_water_source_available_five VARCHAR(10) NULL, area_in_the_sub_county_six VARCHAR(10) NULL, " +
                "water_source_six VARCHAR(10) NULL, functional_six VARCHAR(10) NULL, non_functional_six VARCHAR(10) NULL, no_water_source_available_six VARCHAR(10) NULL, " +
                "do_the_rural_water_point_sources_have_user_committees VARCHAR(5) NULL, if_yes_do_the_rural_water_point_sources_have_user_committees VARCHAR(10) NULL, " +
                "are_there_any_wetlands_demarcated_or_protected VARCHAR(100) NULL, name_of_the_sub_county_village_two VARCHAR(50) NULL, wetland_under_destruction_two VARCHAR(10) NULL, " +
                "name_of_the_sub_county_village_three VARCHAR(50) NULL, wetland_under_destruction_three VARCHAR(10) NULL, name_of_the_sub_county_village_four VARCHAR(50) NULL, " +
                "wetland_under_destruction_four VARCHAR(10) NULL, name_of_the_sub_county_village_five VARCHAR(50) NULL, wetland_under_destruction_five VARCHAR(10) NULL, " +
                "name_of_the_sub_county_village_six VARCHAR(50) NULL, wetland_under_destruction_six VARCHAR(10) NULL, what_are_the_tree_planting_programs_known_in_the_area VARCHAR(100) NULL, " +
                "phone_id VARCHAR(200) NULL)";

        String SOCIALdEVELOPMENT = "CREATE TABLE social_development(record_is INTEGER PRIMARY KEY, financial_year varchar(20) , quarter varchar(5) null," +
                "date varchar(20) null, region varchar(20) null, district varchar(50) null, subcounty varchar(50) null, parish varchar(50) null, village varchar(50) null, " +
                "name_of_monitor varchar(100) null, phone_number varchar(20) null, budget_receipt_date varchar(20) null, community_mobilization_expected_or_approved varchar(100) null, " +
                "community_mobilization_amount_received varchar(10) null, community_mobilization_date_received varchar(20) null, community_mobilization_date_withdrawn varchar(20) null," +
                " fal_ylp_uwep_expected_or_approved varchar(10) null, fal_ylp_uwep_amount_received varchar(10) null, fal_ylp_uwep_date_received varchar(20) null, " +
                " fal_ylp_uwep_date_withdrawn varchar(20) null, are_the_women_empowerment_programs_functional varchar(5) null, if_yes_how_many_women_have_been_supported  " +
                "varchar(10) null, name_of_the_women_group_village_or_sub_county_one varchar(100) null, number_of_males_one varchar(10) null, number_of_females_one varchar(10) null," +
                "amount_received_one varchar(10) null, name_of_the_women_group_village_or_sub_county_two varchar(100) null, number_of_males_two varchar(10) null, " +
                "number_of_females_two varchar(10) null, amount_received_two varchar(10) null, name_of_the_women_group_village_or_sub_county_three varchar(100) null, " +
                "number_of_males_three varchar(10) null, number_of_females_three varchar(10) null, amount_received_three varchar(10) null, " +
                "name_of_the_women_group_village_or_sub_county_four  varchar(100) null, number_of_males_four varchar(10) null, number_of_females_four varchar(10) null, " +
                "amount_received_four varchar(10) null, name_of_the_women_group_village_or_sub_county_five varchar(100) null, number_of_males_five varchar(10) null, " +
                "number_of_females_five varchar(10) null, amount_received_five varchar(10) null, name_of_the_women_group_village_or_sub_county_six varchar(100) null, " +
                "number_of_males_six varchar(10) null, number_of_females_six varchar(10) null, amount_received_six varchar(10) null, " +
                "name_of_the_women_group_village_or_sub_county_seven varchar(100) null, number_of_males_seven varchar(10) null, number_of_females_seven varchar(10) null, " +
                "amount_received_seven varchar(10) null, is_the_youth_livelihood_program_running varchar(5) null, if_yes_how_many_youths_have_been_supported varchar(10) null, " +
                " name_of_the_youth_group_one varchar(100) null, village_or_sub_county_one varchar(100) null, number_of_group_members_males_one varchar(10) null, " +
                "number_of_female_group_members_one varchar(10) null, livelihood_amount_received_one varchar(10) null, name_of_the_youth_group_two varchar(100) null, " +
                "village_or_sub_county_two varchar(100) null, number_of_group_members_males_two varchar(10) null, number_of_female_group_members_two varchar(10) null, " +
                " livelihood_amount_received_two varchar(10) null, name_of_the_youth_group_three varchar(100) null, village_or_sub_county_three varchar(100) null, " +
                "number_of_group_members_males_three varchar(10) null, number_of_female_group_members_three varchar(10) null, livelihood_amount_received_three varchar(10) null, " +
                "name_of_the_youth_group_four varchar(100) null, village_or_sub_county_four varchar(100) null, number_of_group_members_males_four varchar(10) null, " +
                "number_of_female_group_members_four varchar(10) null, livelihood_amount_received_four varchar(10) null,  name_of_the_youth_group_five varchar(100) not null, " +
                "village_or_sub_county_five varchar(100) null, number_of_group_members_males_five varchar(10) null, number_of_female_group_members_five varchar(10) null, " +
                "livelihood_amount_received_five varchar(10) null, name_of_the_youth_group_six varchar(100) null, village_or_sub_county_six varchar(100) null, " +
                "number_of_group_members_males_six varchar(10) null, number_of_female_group_members_six varchar(10) null, livelihood_amount_received_six varchar(10) null, " +
                "name_of_the_youth_group_seven varchar(100) null, village_or_sub_county_seven varchar(100) null, number_of_group_members_males_seven varchar(10) null, " +
                "number_of_female_group_members_seven varchar(10) null, livelihood_amount_received_seven varchar(10) null, " +
                "male_adult_literacy_training_sessions_have_been_conducted_in_this_quarter varchar(50) null, female_adult_literacy_training_sessions_have_been_conducted_in_this_quarter varchar(50) null, " +
                "mention_any_other_challenges_or_observations_affecting_social_development_sector varchar(200) null, district_budget_financial_year varchar(50) null, " +
                "administration_approved_budget varchar(10) null, administration_percentage_share varchar(20) null, finance_approved_budget varchar(20) null, " +
                "finance_percentage_share varchar(20) null, statutory_bodies_approved_budget varchar(20) null, statutory_bodies_percentage_share varchar(20) null, " +
                "production_and_marketing_approved_budget varchar(20) null, production_and_marketing_percentage_share varchar(20) null, health_approved_budget varchar(20) null, " +
                "health_percentage_share varchar(20) null, education_approved_budget varchar(20) null, education_percentage_share varchar(20) null, " +
                "roads_and_engineering_approved_budget varchar(20) null, roads_and_engineering_percentage_share varchar(20) null, water_approved_budget varchar(20) null, " +
                "water_percentage_share varchar(20) null, natural_resources_approved_budget varchar(20) null, natural_resources_percentage_share varchar(20) null, " +
                "community_based_services_approved_budget varchar(20) null, community_based_services_percentage_share varchar(20) null, planning_approved_budget varchar(10) null, " +
                "planning_percentage_share varchar(20) null, internal_audit_approved_budget varchar(20) null, internal_audit_percentage_share varchar(20) null, " +
                "trade_industry_and_local_development_approved_budget varchar(20) null, trade_industry_and_local_development_percentage_share varchar(20) null, " +
                "total_approved_budget varchar(20) null, total_percentage_share varchar(20) null, wage_approved_budget varchar(20) null, " +
                "wage_percentage_share varchar(20) null, non_wage_recurrent_approved_budget varchar(20) null, non_wage_recurrent_percentage_share varchar(20) null, " +
                "domestic_development_approved_budget varchar(20) null, domestic_development_percentage_share varchar(20) null, ext_financing_approved_budget varchar(20) null, " +
                "ext_financing_percentage_share varchar(20) null, district_or_sector_one varchar(100) null, sub_county_one varchar(100) null, financial_year_one varchar(10) null," +
                "service_centre_one varchar(100) null, community_needs_one varchar(100) null, district_or_sector_two varchar(100) null, sub_county_two varchar(100) null, " +
                "financial_year_two varchar(100) null, service_centre_two varchar(100) null, community_needs_two varchar(100) null, district_or_sector_three varchar(100) null, " +
                "sub_county_three varchar(100) null, financial_year_three varchar(20) null, service_centre_three varchar(100) null, community_needs_three varchar(100) null, " +
                "district_or_sector_four varchar(100) null, sub_county_four varchar(100) null, financial_year_four varchar(20) null, service_centre_four varchar(100) null, " +
                "community_needs_four varchar(100) null, district_or_sector_five varchar(100) null, sub_county_five varchar(100) null, financial_year_five varchar(10) null, " +
                "service_centre_five varchar(100) null, community_needs_five varchar(100) null, district_or_sector_six varchar(100) null, sub_county_six varchar(100) null, " +
                "financial_year_six varchar(10) null, service_centre_six varchar(100) null, community_needs_six varchar(100) null, district_or_sector_seven varchar(100) null, " +
                "sub_county_seven varchar(100) null, financial_year_seven varchar(20) null, service_centre_seven varchar(100) null, community_needs_seven varchar(100) null )";


        db.execSQL(CREATE_TABLE);
        db.execSQL(HEALTH);
        db.execSQL(EDUCATION);
        db.execSQL(REGIONTABLESTRING);
        db.execSQL(DISTRICTTABLESTRING);
        db.execSQL(SUBCOUNTYTABLESTRING);
        db.execSQL(SECTOR);
        db.execSQL(BUDGETMONITERING);
        db.execSQL(WATERANDSANITATION);
        db.execSQL(SOCIALdEVELOPMENT);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HEALTH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EDUCATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS regions");
        db.execSQL("DROP TABLE IF EXISTS districts");
        db.execSQL("DROP TABLE IF EXISTS sub_counties");
        db.execSQL("DROP TABLE IF EXISTS sectors");
        db.execSQL("DROP TABLE IF EXISTS budget_mornitoring");
        db.execSQL("DROP TABLE IF EXISTS water_and_sanitation");
        onCreate(db);
    }

    public Cursor reaData() {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE + " ORDER BY " + KEY_ID + " DESC", null);
    }


    public List<String> reaDataTable(String tableNname) {

        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableNname + " ORDER BY name ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    private String itemDataSelection(String table_name, String item_selected, String targetColum_name) {

        String id = "";

        String selectQuery = "SELECT " + targetColum_name + " FROM " + table_name + " WHERE name = '" + item_selected + "' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(0);
            } while (cursor.moveToNext());
        }


        return id;


    }

    public List<String> readSubCounty(String selected) {

        Log.e("District", selected);

        List<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT sub_counties.name AS name, districts.name AS district_name FROM sub_counties JOIN districts ON " +
                " sub_counties.district_id = districts.district_id WHERE district_name = '" + selected + "' ORDER BY name ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int iName = cursor.getColumnIndex("name");

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            labels.add(cursor.getString(iName));
        }
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;

    }


    public List<String> readDistricts(String selected) {

        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT districts.name FROM districts JOIN regions ON districts.region_id = regions.region_id  WHERE " +
                "regions.name = '" + selected + "' ORDER BY districts.name ASC";

        //db = this.getReadableDatabase();
        db = this.getWritableDatabase();
        Cursor cursor = this.getWritableDatabase().rawQuery(selectQuery, null);

        int iName = cursor.getColumnIndex("name");

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            labels.add(cursor.getString(iName));
        }

        cursor.close();
        db.close();

        // returning lables
        return labels;
    }


    public void saveRgion(String name, String id) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("region_id", id);
        try {
            db.insert("regions", null, values);
        } catch (Exception e) {
        }

    }

    public void saveDistrict(String name, String region_id, String district_id) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("region_id", region_id);
        values.put("district_id", district_id);
        try {
            db.insert("districts", null, values);
        } catch (Exception e) {
        }

    }

    public void saveSubcounty(String name, String district_id, String subcategories_id) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("district_id", district_id);
        values.put("subcategories_id", subcategories_id);
        try {
            db.insert("sub_counties", null, values);
        } catch (Exception e) {
        }

    }

    public void saveSector(String name, String sector_id) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("sector_id", sector_id);
        try {
            db.insert("sectors", null, values);
        } catch (Exception e) {
        }

    }

    public Cursor reaDataSubcounty() {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM sub_counties", null);
    }



    public void deleteTable(String table_name) {
        db = this.getWritableDatabase();
        db.delete(table_name, null, null);
    }

    public void deleteSingleRow(String rowId) {
        db = this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + "=" + rowId, null);
    }

    public void deleteSinglemedical(String rowId) {
        db = this.getWritableDatabase();
        db.delete(HEALTH_TABLE, KEY_ID + "=" + rowId, null);
    }



    public void deleteSingleBudget(String rowId) {
        db = this.getWritableDatabase();
        db.delete("budget_mornitoring", KEY_ID + "=" + rowId, null);
    }

    public void delete_single_education(String rowId) {
        db = this.getWritableDatabase();
        db.delete(EDUCATION_TABLE, KEY_ID + "=" + rowId, null);
    }

    public Cursor reaData(String water_id) {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE + " WHERE " + KEY_ID + " = " + water_id + "" +
                " ORDER BY " + KEY_ID + " DESC", null);
    }

    public JSONArray cur2Json(Cursor cursor) {
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        rowObject.put(cursor.getColumnName(i),
                                cursor.getString(i));
                    } catch (Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        return resultSet;
    }


    public Cursor reaMedicalData() {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM " + HEALTH_TABLE + " ORDER BY " + KEY_ID + " DESC", null);
    }


    public void save_health(String phone_id, String reasonestablish_health_education, String ratereason_establish_health_education,
                            String besthealth_education_spec, String besthealth_education_reason, String worsthealth_education_spec,
                            String worsthealth_education_reason, String priorityhealth_education, String priorityhealth_education_spec,
                            String priorityhealth_education_reason, String gender, String ageCategory, String level_of_education, String region,
                            String district, String subcounty, String establish_health_education, String yes_establish_health_education,
                            String rate_establish_health_education, String best_health_education, String worst_health_education,
                            String financialYear, String quarter, String date, String parish, String village, String nameOfMonitor, String phoneNumber,
                            String nameAndGradeOfHealthCenter, String numberOfOutpatientOpdAttendanceDuringTheQuarter,
                            String numberOfInpatientAttendancesDuringTheQuarter, String recurrentApprovedBudgets, String recurrentBudgetReleased,
                            String recurrentDateReceived, String recurrentDateWithdrawn, String developmentApprovedBudgets, String developmentBudgetReleased,
                            String developmentDateReceived, String developmentDateWithdrawn, String healthFacilityDisplayBudgetInformation, String maternityWardYes,
                            String maternityWardNo, String generalWardYes, String generalWardNo, String deliveryBedsYes, String deliveryBedsNo,
                            String familyPlanningServicesYes, String familyPlanningServicesNo, String hivCounsellingTestingAndTreatmentYes,
                            String hivCounsellingTestingAndTreatmentNo, String pmtctYes, String pmtctNo, String immunizationYes, String immunizationNo,
                            String youthFriendlyCornersYes, String youthFriendlyCornersNo, String vaccinationForHepBYes, String vaccinationForHepBNo,
                            String livenoOfDeliveries, String stillNoOfDeliveries, String childrenImmunizedWithThePentavalentVaccine,
                            String facilityOpenDuringHoursThatAreConvenientForAdolescents, String areSrhServicesOfferedForFree, String adolescentsPrivacy,
                            String counselingAndTreatmentRoomsAllowForPrivacy, String codeofConductInPlace,
                            String transparentConfidentialMechanismForAdolescentsToSubmitComplaints, String haveProvidersBeenTrainedToProvideAdolescentFriendlyServices,
                            String haveAllStaffBeenOrientedToProvidingConfidentialAdolescentFriendlyServices,
                            String staffDemonstrateRespectWhenInteractingWithAdolescents, String providersEnsureTheClientsPrivacyAndConfidentiality,
                            String providersSetAsideSufficientTimeForClientProviderInteraction, String peerEducatorsOrPeerCounselorsAvailable,
                            String healthProvidersAssessedUsingQualityStandardChecklists, String adolescentsFemaleAndMalePlayARoleInTheOperationOfTheHealthFacility,
                            String adolescentsInvolvedInMonitoringTheQualityOfSrhServiceProvision,
                            String canAdolescentsBeSeenInTheFacilityWithoutTheConsentOfTheirParentsOrSpouses, String wideRangeOfSrhServicesAvailable,
                            String writtenGuidelinesForProvidingAdolescentServices, String condomsAvailableToBothYoungMenAndYoungWomen,
                            String educationalMaterials, String areReferralMechanismsInPlaceForMedicalEmergencies, String toiletNumberOfBlocks,
                            String toiletNumberOfStances, String toiletMalePatientStances, String toiletFemalePatientStances, String toiletHealthFacilityMaleStaff,
                            String toiletHealthFacilityFemaleStaff, String toiletHealthFacilityMixedStaff, String toiletFunctional, String toiletNotFunctional,
                            String latrineNumberOfBlocks, String latrineNumberOfStances, String latrineMalePatientStances, String latrineFemalePatientStances,
                            String latrineHealthFacilityMaleStaff, String latrineHealthFacilityFemaleStaff, String latrineHealthFacilityMixedStaff,
                            String latrineFunctional, String latrineNotFunctional, String femaleChangeRoomsNumberOfBlocks, String femaleChangeRoomsNumberOfStances,
                            String femaleChangeRoomsMalePatientStances, String femaleChangeRoomsPatientStances, String femaleChangeRoomsHealthFacilityStaff,
                            String femaleChangeRoomsHealthFacilityFemaleStaff, String femaleChangeRoomsHealthFacilityMixedStaff, String femaleChangeRoomsFunctional,
                            String femaleChangeRoomsNotFunctional, String healthFacilityToiletLatrineAccessibleToPatientsWithDisabilities,
                            String pwdFriendlyFacilitiesDoesTheHealthCenter, String pwdFriendlyFacilitiesDoesTheHealthCenterSpecify, String numberBorehole,
                            String boreholeFunctional, String boreholeNonFunctional, String tapBorehole, String tapFunctional, String tapNonFunctional,
                            String waterTankBorehole, String waterTankFunctional, String waterTankNonFunctional, String waterPointAccessibleToPwds,
                            String facilityHaveAFunctionalWaterWithinNearby, String facilityHaveAFunctionalWaterWithinNearbyEstimate,
                            String handwashingFacilityInstalledAtTheHealthFacility, String healthUnitManagementCommitteeExist, String ifyesHowOftenDoTheyMeet,
                            String lastVisitOfSuportSupervisor, String medicalStaffCeiling, String totalNoOfMedicalStaff, String noOfMedicalStaffPresent,
                            String nonMedicalStaffCeiling, String totalNoOfNonMedicalStaff, String noOfNonMedicalStaffPresent, String reasonsForTheAbsentStaff,
                            String lastDateOfMedicalStaffPerformanceAppraisal, String numberOfStaffAppraised,
                            String didTheHcReceiveAMedicalSuppliesConsignmentFromNationalMedicalStoresInThisQuarter, String ifYesPleaseProvideDateAsPerDeliveryNote,
                            String oneStockRequired, String twoStockRequired, String threeStockRequired, String fourStockRequired, String fiveStockRequired,
                            String sixStockRequired, String whenDidTheHealthCenterLastDrugsConsignmentGetFinished, String numberOfMedicalEquipmentBought,
                            String doesHaveAnAmbulance, String ifNoHowAreReferralsHandled, String listAnyOtherObservationsOrChallengesHc) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REGION, region);
        values.put(DISTRICT, district);
        values.put(SUBCOUNTY, subcounty);
        values.put(GENDER, gender);
        values.put(AGE_CATEGORY, ageCategory);
        values.put(LEVEL_OF_EDUCATION, level_of_education);
        values.put(ESTABLISH_HEALTH_EDUCATION, establish_health_education);
        values.put(YES_ESTABLISH_HEALTH_EDUCATION, yes_establish_health_education);
        values.put(REASON_ESTABLISH_HEALTH_EDUCATION, reasonestablish_health_education);
        values.put(RATE_ESTABLISH_HEALTH_EDUCATION, rate_establish_health_education);
        values.put(RATE_REASON_ESTABLISH_HEALTH_EDUCATION, ratereason_establish_health_education);
        values.put(BEST_HEALTH_EDUCATION, best_health_education);
        values.put(BEST_HEALTH_EDUCATION_SPEC, besthealth_education_spec);
        values.put(BEST_HEALTH_EDUCATION_REASON, besthealth_education_reason);
        values.put(WORST_EDUCATION, worst_health_education);
        values.put(WORST_EDUCATION_REASON_SPEC, worsthealth_education_spec);
        values.put(WORST_EDUCATION_REASON, worsthealth_education_reason);
        values.put(PRIORITY_HEALTH_EDUCATION, priorityhealth_education);
        values.put(PRIORITY_HEALTH_EDUCATION_SPEC, priorityhealth_education_spec);
        values.put(PRIORITY_HEALTH_EDUCATION_REASON, priorityhealth_education_reason);
        values.put(PHONE_ID, phone_id);
        values.put("financial_year",financialYear);
        values.put("quarter",quarter);
        values.put("date",date);
        values.put("parish",parish);
        values.put("village",village);
        values.put("name_of_monitor",nameOfMonitor);
        values.put("phone_number",phoneNumber);
        values.put("name_and_grade_of_health_center",nameAndGradeOfHealthCenter);
        values.put("number_of_outpatient_opd_attendance_during_the_quarter",numberOfOutpatientOpdAttendanceDuringTheQuarter);
        values.put("number_of_inpatient_attendances_during_the_quarter",numberOfInpatientAttendancesDuringTheQuarter);
        values.put("recurrent_approved_budgets",recurrentApprovedBudgets);
        values.put("recurrent_budget_released",recurrentBudgetReleased);
        values.put("recurrent_date_received",recurrentDateReceived);
        values.put("recurrent_date_withdrawn", recurrentDateWithdrawn);
        values.put("development_approved_budgets",developmentApprovedBudgets);
        values.put("development_budget_released",developmentBudgetReleased);
        values.put("development_date_received",developmentDateReceived);
        values.put("development_date_withdrawn",developmentDateWithdrawn);
        values.put("health_facility_display_budget_information",healthFacilityDisplayBudgetInformation);
        values.put("maternity_ward_yes",maternityWardYes);
        values.put("maternity_ward_no",maternityWardNo);
        values.put("general_ward_yes",generalWardYes);
        values.put("general_ward_no",generalWardNo);
        values.put("delivery_beds_yes",deliveryBedsYes);
        values.put("delivery_beds_no",deliveryBedsNo);
        values.put("family_planning_services_yes",familyPlanningServicesYes);
        values.put("family_planning_services_no",familyPlanningServicesNo);
        values.put("hiv_counselling_testing_and_treatment_yes",hivCounsellingTestingAndTreatmentYes);
        values.put("hiv_counselling_testing_and_treatment_no",hivCounsellingTestingAndTreatmentNo);
        values.put("pmtct_yes",pmtctYes);
        values.put("pmtct_no",pmtctNo);
        values.put("immunization_yes",immunizationYes);
        values.put("immunization_no",immunizationNo);
        values.put("youth_friendly_corners_yes",youthFriendlyCornersYes);
        values.put("youth_friendly_corners_no",youthFriendlyCornersNo);
        values.put("vaccination_for_hep_b_yes",vaccinationForHepBYes);
        values.put("vaccination_for_hep_b_no",vaccinationForHepBNo);
        values.put("live_no_of_deliveries",livenoOfDeliveries);
        values.put("still_no_of_deliveries",stillNoOfDeliveries);
        values.put("children_immunized_with_the_pentavalent_vaccine",childrenImmunizedWithThePentavalentVaccine);
        values.put("facility_open_during_hours_that_are_convenient_for_adolescents",facilityOpenDuringHoursThatAreConvenientForAdolescents);
        values.put("are_srh_services_offered_for_free",areSrhServicesOfferedForFree);
        values.put("adolescents_privacy",adolescentsPrivacy);
        values.put("counseling_and_treatment_rooms_allow_for_privacy",counselingAndTreatmentRoomsAllowForPrivacy);
        values.put("code_of_conduct_in_place",codeofConductInPlace);
        values.put("transparent_confidential_mechanism_for_adolescents_to_submit_complaints",transparentConfidentialMechanismForAdolescentsToSubmitComplaints);
        values.put("have_providers_been_trained_to_provide_adolescent_friendly_services",haveProvidersBeenTrainedToProvideAdolescentFriendlyServices);
        values.put("have_all_staff_been_oriented_to_providing_confidential_adolescent_friendly_services",haveAllStaffBeenOrientedToProvidingConfidentialAdolescentFriendlyServices);
        values.put("staff_demonstrate_respect_when_interacting_with_adolescents",staffDemonstrateRespectWhenInteractingWithAdolescents);
        values.put("providers_ensure_the_clients_privacy_and_confidentiality",providersEnsureTheClientsPrivacyAndConfidentiality);
        values.put("providers_set_aside_sufficient_time_for_client_provider_interaction",providersSetAsideSufficientTimeForClientProviderInteraction);
        values.put("peer_educators_or_peer_counselors_available",peerEducatorsOrPeerCounselorsAvailable);
        values.put("health_providers_assessed_using_quality_standard_checklists",healthProvidersAssessedUsingQualityStandardChecklists);
        values.put("adolescents_female_and_male_play_a_role_in_the_operation_of_the_health_facility",adolescentsFemaleAndMalePlayARoleInTheOperationOfTheHealthFacility);
        values.put("adolescents_involved_in_monitoring_the_quality_of_srh_service_provision",adolescentsInvolvedInMonitoringTheQualityOfSrhServiceProvision);
        values.put("can_adolescents_be_seen_in_the_facility_without_the_consent_of_their_parents_or_spouses",canAdolescentsBeSeenInTheFacilityWithoutTheConsentOfTheirParentsOrSpouses);
        values.put("wide_range_of_srh_services_available",wideRangeOfSrhServicesAvailable);
        values.put("written_guidelines_for_providing_adolescent_services",writtenGuidelinesForProvidingAdolescentServices);
        values.put("condoms_available_to_both_young_men_and_young_women",condomsAvailableToBothYoungMenAndYoungWomen);
        values.put("educational_materials",educationalMaterials);
        values.put("are_referral_mechanisms_in_place_for_medical_emergencies",areReferralMechanismsInPlaceForMedicalEmergencies);
        values.put("toilet_number_of_blocks",toiletNumberOfBlocks);
        values.put("toilet_number_of_stances",toiletNumberOfStances);
        values.put("toilet_male_patient_stances",toiletMalePatientStances);
        values.put("toilet_female_patient_stances",toiletFemalePatientStances);
        values.put("toilet_health_facility_male_staff",toiletHealthFacilityMaleStaff);
        values.put("toilet_health_facility_female_staff",toiletHealthFacilityFemaleStaff);
        values.put("toilet_health_facility_mixed_staff",toiletHealthFacilityMixedStaff);
        values.put("toilet_functional",toiletFunctional);
        values.put("toilet_not_functional",toiletNotFunctional);
        values.put("latrine_number_of_blocks",latrineNumberOfBlocks);
        values.put("latrine_number_of_stances",latrineNumberOfStances);
        values.put("latrine_male_patient_stances",latrineMalePatientStances);
        values.put("latrine_female_patient_stances",latrineFemalePatientStances);
        values.put("latrine_health_facility_male_staff",latrineHealthFacilityMaleStaff);
        values.put("latrine_health_facility_female_staff",latrineHealthFacilityFemaleStaff);
        values.put("latrine_health_facility_mixed_staff",latrineHealthFacilityMixedStaff);
        values.put("latrine_functional",latrineFunctional);
        values.put("latrine_not_functional",latrineNotFunctional);
        values.put("female_change_rooms_number_of_blocks",femaleChangeRoomsNumberOfBlocks);
        values.put("female_change_rooms_number_of_stances",femaleChangeRoomsNumberOfStances);
        values.put("female_change_rooms_male_patient_stances",femaleChangeRoomsMalePatientStances);
        values.put("female_change_rooms_patient_stances",femaleChangeRoomsPatientStances);
        values.put("female_change_rooms_health_facility_staff",femaleChangeRoomsHealthFacilityStaff);
        values.put("female_change_rooms_health_facility_female_staff",femaleChangeRoomsHealthFacilityFemaleStaff);
        values.put("female_change_rooms_health_facility_mixed_staff",femaleChangeRoomsHealthFacilityMixedStaff);
        values.put("female_change_rooms_functional",femaleChangeRoomsFunctional);
        values.put("female_change_rooms_not_functional",femaleChangeRoomsNotFunctional);
        values.put("health_facility_toilet_latrine_accessible_to_patients_with_disabilities",healthFacilityToiletLatrineAccessibleToPatientsWithDisabilities);
        values.put("pwd_friendly_facilities_does_the_health_center",pwdFriendlyFacilitiesDoesTheHealthCenter);
        values.put("pwd_friendly_facilities_does_the_health_center_specify",pwdFriendlyFacilitiesDoesTheHealthCenterSpecify);
        values.put("number_borehole",numberBorehole);
        values.put("borehole_functional",boreholeFunctional);
        values.put("borehole_non_functional",boreholeNonFunctional);
        values.put("tap_borehole",tapBorehole);
        values.put("tap_functional",tapFunctional);
        values.put("tap_non_functional",tapNonFunctional);
        values.put("water_tank_borehole",waterTankBorehole);
        values.put("water_tank_functional",waterTankFunctional);
        values.put("water_tank_non_functional",waterTankNonFunctional);
        values.put("water_point_accessible_to_pwds",waterPointAccessibleToPwds);
        values.put("facility_have_a_functional_water_within_nearby",facilityHaveAFunctionalWaterWithinNearby);
        values.put("facility_have_a_functional_water_within_nearby_estimate",facilityHaveAFunctionalWaterWithinNearbyEstimate);
        values.put("handwashing_facility_installed_at_the_health_facility",handwashingFacilityInstalledAtTheHealthFacility);
        values.put("health_unit_management_committee_exist",healthUnitManagementCommitteeExist);
        values.put("if_yes_how_often_do_they_meet",ifyesHowOftenDoTheyMeet);
        values.put("last_visit_of_suport_supervisor",lastVisitOfSuportSupervisor);
        values.put("medical_staff_ceiling",medicalStaffCeiling);
        values.put("total_no_of_medical_staff",totalNoOfMedicalStaff);
        values.put("no_of_medical_staff_present",noOfMedicalStaffPresent);
        values.put("non_medical_staff_ceiling",nonMedicalStaffCeiling);
        values.put("total_no_of_non_medical_staff",totalNoOfNonMedicalStaff);
        values.put("no_of_non_medical_staff_present",noOfNonMedicalStaffPresent);
        values.put("reasons_for_the_absent_staff",reasonsForTheAbsentStaff);
        values.put("last_date_of_medical_staff_performance_appraisal",lastDateOfMedicalStaffPerformanceAppraisal);
        values.put("number_of_staff_appraised",numberOfStaffAppraised);
        values.put("did_the_hc_receive_a_medical_supplies_consignment_from_national_medical_stores_in_this_quarter",didTheHcReceiveAMedicalSuppliesConsignmentFromNationalMedicalStoresInThisQuarter);
        values.put("if_yes_please_provide_date_as_per_delivery_note",ifYesPleaseProvideDateAsPerDeliveryNote);
        values.put("one_stock_required",oneStockRequired);
        values.put("two_stock_required",twoStockRequired);
        values.put("three_stock_required",threeStockRequired);
        values.put("four_stock_required",fourStockRequired);
        values.put("five_stock_required",fiveStockRequired);
        values.put("six_stock_required",sixStockRequired);
        values.put("when_did_the_health_center_last_drugs_consignment_get_finished",whenDidTheHealthCenterLastDrugsConsignmentGetFinished);
        values.put("number_of_medical_equipment_bought",numberOfMedicalEquipmentBought);
        values.put("does_have_an_ambulance",doesHaveAnAmbulance);
        values.put("if_no_how_are_referrals_handled",ifNoHowAreReferralsHandled);
        values.put("list_any_other_observations_or_challenges_hc",listAnyOtherObservationsOrChallengesHc);

        try {
            db.insert(HEALTH_TABLE, null, values);
            Log.d("SQL-RECORD: ", "HEALTH SAVED");
        } catch (Exception e) {
            Log.d("SQL-RECORD: ", "Health=>" + e.toString());
        }

    }

    public Cursor reaEducationData() {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM " + EDUCATION_TABLE + " ORDER BY " + KEY_ID + " DESC", null);
    }

    public Cursor reaeducationdata() {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM " + EDUCATION_TABLE + " ORDER BY " + KEY_ID + " DESC", null);
    }

    public void save_education(String phone_id, String gender, String ageCategory, String level_of_education,
                               String region, String district, String subcounty, String use_education,
                               String bestuse_education_service, String bestuse_education_spec, String bestuse_education_reason,
                               String worstuse_education_service, String worstuse_education_spec, String worstuse_education_reason,
                               String priorityuse_education_service, String priorityuse_education_spec,
                               String priorityuse_education_reason
    ) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REGION, region);
        values.put(DISTRICT, district);
        values.put(SUBCOUNTY, subcounty);
        values.put(GENDER, gender);
        values.put(AGE_CATEGORY, ageCategory);
        values.put(LEVEL_OF_EDUCATION, level_of_education);
        values.put(USE_EDUCATION, use_education);
        values.put(BEST_USE_EDUCATION_SERVICE, bestuse_education_service);
        values.put(BEST_USE_EDUCATION_SPEC, bestuse_education_spec);
        values.put(BEST_USE_EDUCATION_REASON, bestuse_education_reason);
        values.put(WORST_USE_EDUCATION_SERVICE, worstuse_education_service);
        values.put(WORST_USE_EDUCATION_SPEC, worstuse_education_spec);
        values.put(WORST_USE_EDUCATION_REASON, worstuse_education_reason);
        values.put(PRIORITY_USE_EDUCATION_SERVICE, priorityuse_education_service);
        values.put(PRIORITY_USE_EDUCATION_SPEC, priorityuse_education_spec);
        values.put(PRIORITY_USE_EDUCATION_REASON, priorityuse_education_reason);
        values.put(PHONE_ID, phone_id);

        try {
            db.insert(EDUCATION_TABLE, null, values);
            Log.d("SQL-RECORD: ", "EDUCATION SAVED");
        } catch (Exception e) {
            Log.d("SQL-RECORD: ", "Education=>" + e.toString());
        }
    }

    public void saveBudgetMonitoring(String budget_region, String budget_district, String budget_subcounty, String budget_sector,
                                     String budget_quoter, String budget_amount_sent, String budget_amount_spent,
                                     String budget_date_received, String budget_date_of_monitoring) {

        String region_id = itemDataSelection("regions", "" + budget_region, "region_id");

        String sector_id = itemDataSelection("sectors", "" + budget_sector, "sector_id");

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("region_name", budget_region);
        values.put("sector_name", budget_sector);
        values.put("region_id", region_id);
        values.put("sector_id", sector_id);
        values.put("quater", budget_quoter);
        values.put("amount_sent", budget_amount_sent);
        values.put("amount_spent", budget_amount_spent);
        values.put("sub_county", budget_subcounty);
        values.put("district", budget_district);
        values.put("date_received", budget_date_received);
        values.put("date_of_monitoring", budget_date_of_monitoring);
        try {
            db.insert("budget_mornitoring", null, values);
        } catch (Exception e) {
        }

    }

    public Cursor readMonitoringRecords() {

        String records = "SELECT budget_mornitoring.id, region_id,sector_name, region_name,sector_id,quater,amount_sent,amount_spent as amount,sub_county," +
                "district,date_received as date,date_of_monitoring FROM budget_mornitoring";

        db = this.getWritableDatabase();

        return this.getWritableDatabase().rawQuery(records, null);

    }




    public void save_agriculture(String staffName, String dateRecorded, String ageCategory, String region, String district, String subcounty, String parish,
                                 String agric_service_best, String agric_service_spec, String agric_service_reason, String agric_service_worst,
                                 String agric_service_worst_spec, String agric_service_reason_worst, String agric_service_problem, String agric_service_problem_spec,
                                 String agric_service_reason_problem, String phone_id, String gender, String agric_service_need, String agric_service_need_spec,
                                 String agric_service_reason_need, String agric_proposed_priority, String agric_proposed_priority_spec,
                                 String agric_proposed_priority_reason, String financialYear, String phoneNumber, String ifNoWhy,
                                 String extensionServicesExpectedorApproved, String extensionServicesAmountReceived, String extensionServicesDateReceived,
                                 String extensionServicesDateWithdrawn, String developmentExpectedorApproved, String developmentAmountReceived,
                                 String developmentDateReceived, String developmentDateWithdrawn, String numberofFieldVisitsforFarmerSupportConductedDuringtheQuarter,
                                 String ifyeshowmanyDuringtheQuarter, String mentiontheAreaswheretheMeetingsorWorkshopswereHeld,
                                 String ifnonewhataretheReasonsfornotConductingtheCommunityMeetingsonFarming,
                                 String ifyeshowmanyweredoneDuringtheQuarter, String mentiontheAreaswheretheVisitswereMade,
                                 String malenumberofFarmersVisitedforAdvisoryServices, String femaleNumberofFarmersVisitedforAdvisoryServices,
                                 String whataretheReasonsfornotConductingtheFarmerAdvisoryServicesVisits, String inputsorPlantingMaterialsOne, String dateOne,
                                 String malethatReceivedInputOne, String femalethatReceivedInputOne, String subcountyorVillageOne,
                                 String inputsorPlantingMaterialsTwo, String dateTwo, String malethatReceivedInputTwo, String femalethatReceivedInputTwo,
                                 String subCountyorVillageTwo, String inputsorplantingMaterialsThree, String dateThree, String malethatReceivedInputThree,
                                 String femalethatReceivedInputThree, String subCountyorVillageThree, String inputsorPlantingMaterialsFour, String dateFour,
                                 String malethatReceivedInputFour, String femalethatReceivedInputFour, String subcountyorVillageFour,
                                 String inputsorPlantingMaterialsFive, String dateFive, String malethatReceivedInputFive, String femaleThatReceivedInputFive,
                                 String subcountyorvillageFive, String inputsorPlantingMaterialsSix, String dateSix, String malethatReceivedInputSix,
                                 String femalethatReceivedInputSix, String subCountyorVillageSix,
                                 String ifNonWhataretheReasonsForNotProvidingFarmerswithAgricultureInputsandLivestockDuringtheQuarter, String quoter,
                                 String advisoryDemonstrationCommunity, String areThereanyAdvisoryServices, String haveYouGivenAnyAgricultureInputsandLivestock,
                                 String doestheSubCountyDivisionHaveASubstantiveAgriculturalExtensionWorker) {


        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_NAME, staffName);
        values.put(DATE_RECORDED, dateRecorded);
        values.put(AGE_CATEGORY, ageCategory);
        values.put(REGION, region);
        values.put(DISTRICT, district);
        values.put(SUBCOUNTY, subcounty);
        values.put(PARISH, parish);

//        values.put(AGRIC_SERVICE_BEST, agric_service_best);
//        values.put(AGRIC_SERVICE_SPEC, agric_service_spec);
//        values.put(AGRIC_SERVICE_REASON, agric_service_reason);

//        values.put(AGRIC_SERVICE_WORST, agric_service_worst);
//        values.put(AGRIC_SERVICE_WORST_SPEC, agric_service_worst_spec);
//        values.put(AGRIC_SERVICE_REASON_WORST, agric_service_reason_worst);

//        values.put(AGRIC_SERVICE_PROBLEM, agric_service_problem);
//        values.put(AGRIC_SERVICE_PROBLEM_SPEC, agric_service_problem_spec);
//        values.put(AGRIC_SERVICE_REASON_PROBLEM, agric_service_reason_problem);

//        values.put(AGRIC_SERVICE_NEED, agric_service_need);
//        values.put(AGRIC_SERVICE_NEED_SPEC, agric_service_need_spec);
//        values.put(AGRIC_SERVICE_REASON_NEED, agric_service_reason_need);

//        values.put(AGRIC_PROPOSED_PRIORITY, agric_proposed_priority);
//        values.put(AGRIC_PROPOSED_PRIORITY_SPEC, agric_proposed_priority_spec);
//        values.put(AGRIC_PROPOSED_PRIORITY_REASON, agric_proposed_priority_reason);

        values.put(DatabaseHelper.PHONE_ID, phone_id);
        values.put(GENDER, gender);

        values.put("financial_year", financialYear);
        values.put("quarter", quoter);
        values.put("phone_number", phoneNumber);
        values.put("parish", parish);
        values.put("is_there_substantive_agricultural_extension_worker", doestheSubCountyDivisionHaveASubstantiveAgriculturalExtensionWorker);
        values.put("if_no_why",ifNoWhy);
        values.put("extension_services_expected_or_approved",extensionServicesExpectedorApproved);
        values.put("extension_services_amount_received",extensionServicesAmountReceived);
        values.put("extension_services_date_received",extensionServicesDateReceived);
        values.put("extension_services_date_withdrawn",extensionServicesDateWithdrawn);
        values.put("development_expected_or_approved",developmentExpectedorApproved);
        values.put("development_amount_received",developmentAmountReceived);
        values.put("development_date_received",developmentDateReceived);
        values.put("development_date_withdrawn",developmentDateWithdrawn);
        values.put("number_of_field_visits_for_farmer_support",numberofFieldVisitsforFarmerSupportConductedDuringtheQuarter);
        values.put("advisory_demonstration_community",advisoryDemonstrationCommunity);
        values.put("if_yes_how_many_during_the_quarter",ifyeshowmanyDuringtheQuarter);
        values.put("areas_where_the_meetings_or_workshops_were_held",mentiontheAreaswheretheMeetingsorWorkshopswereHeld);
        values.put("reasons_for_not_conducting_the_community_meeting",ifnonewhataretheReasonsfornotConductingtheCommunityMeetingsonFarming);
        values.put("are_there_any_advisory_services",areThereanyAdvisoryServices);
        values.put("if_yes_how_many_were_done_during_the_quarter",ifyeshowmanyweredoneDuringtheQuarter);
        values.put("mention_the_areas_where_the_visits_were_made",mentiontheAreaswheretheVisitswereMade);
        values.put("male_number_of_farmers_visited_for_advisory_services",malenumberofFarmersVisitedforAdvisoryServices);
        values.put("female_number_of_farmers_visited_for_advisory_services",femaleNumberofFarmersVisitedforAdvisoryServices);
        values.put("reasons_for_not_conducting_the_farmer_advisory_services_visits",whataretheReasonsfornotConductingtheFarmerAdvisoryServicesVisits);
        values.put("any_owc_inputs",haveYouGivenAnyAgricultureInputsandLivestock);
        values.put("inputs_or_planting_materials_one",inputsorPlantingMaterialsOne);
        values.put("date_one",dateOne);
        values.put("male_that_received_input_one",malethatReceivedInputOne);
        values.put("female_that_received_input_one",femalethatReceivedInputOne);
        values.put("sub_county_or_village_one",subcountyorVillageOne);
        values.put("inputs_or_planting_materials_two",inputsorPlantingMaterialsTwo);
        values.put("date_two",dateTwo);
        values.put("male_that_received_input_two",malethatReceivedInputTwo);
        values.put("female_that_received_input_two",femalethatReceivedInputTwo);
        values.put("sub_county_or_village_two",subCountyorVillageTwo);
        values.put("inputs_or_planting_materials_three",inputsorplantingMaterialsThree);
        values.put("date_three",dateThree);
        values.put("male_that_received_input_three",malethatReceivedInputThree);
        values.put("female_that_received_input_three",femalethatReceivedInputThree);
        values.put("sub_county_or_village_three",subCountyorVillageThree);
        values.put("inputs_or_planting_materials_four",inputsorPlantingMaterialsFour);
        values.put("date_four",dateFour);
        values.put("male_that_received_input_four",malethatReceivedInputFour);
        values.put("female_that_received_input_four",femalethatReceivedInputFour);
        values.put("sub_county_or_village_four",subcountyorVillageFour);
        values.put("inputs_or_planting_materials_five",inputsorPlantingMaterialsFive);
        values.put("date_five",dateFive);
        values.put("male_that_received_input_five",malethatReceivedInputFive);
        values.put("female_that_received_input_five",femaleThatReceivedInputFive);
        values.put("sub_county_or_village_five",subcountyorvillageFive);
        values.put("inputs_or_planting_materials_six",inputsorPlantingMaterialsSix);
        values.put("date_six",dateSix);
        values.put("male_that_received_input_six",malethatReceivedInputSix);
        values.put("female_that_received_input_six",femalethatReceivedInputSix);
        values.put("sub_county_or_village_six",subCountyorVillageSix);
        values.put("if_non_what_are_the_reasons_for_not_providing_farmers_with_agriculture_inputs_and_livestock_during_the_quarter",
                ifNonWhataretheReasonsForNotProvidingFarmerswithAgricultureInputsandLivestockDuringtheQuarter);
        try {
            db.insert(TABLE, null, values);
            Log.d("SQL-RECORD: ", "SAVED");
        } catch (Exception e) {
            Log.d("SQL-RECORD: ", e.toString());
        }

    }

    public void saveWaterSanitation(String financialYear, String date, String parish, String village, String nameOfMonitor, String phoneNumber,
                                    String divisionHaveWaterSanitationIfNo, String whatAreTheWaterSanitationtProtectionActivitiesTakingPlaceInTheSubCounty,
                                    String areaInTheSubCountyOne, String waterSourceOne, String functionalOne, String nonFunctionalOne, String noWaterSourceAvailableOne,
                                    String areaInTheSubCountyTwo, String waterSourceTwo, String functionalTwo, String nonFunctionalTwo, String noWaterSourceAvailableTwo,
                                    String areaInTheSubCountyThree, String waterSourceThree, String functionalThree, String nonFunctionalThree,
                                    String noWaterSourceAvailableThree, String areaInTheSubCountyFour, String waterSourceFour, String functionalFour,
                                    String nonFunctionalFour, String noWaterSourceAvailableFour, String areaInTheSubCountyFive, String waterSourceFive,
                                    String functionalFive, String nonFunctionalFive, String noWaterSourceAvailableFive, String areaInTheSubCountySix,
                                    String waterSourceSix, String functionalSix, String nonFunctionalSix, String noWaterSourceAvailableSix,
                                    String ifYesDoTheRuralWaterPointSourcesHaveUserCommittees, String areThereAnyWetlandsDemarcatedOrProtected,
                                    String nameOfTheSubCountyVillageTwo, String wetlandUnderDestructionTwo, String nameOfTheSubCountyVillageThree,
                                    String wetlandUnderDestructionThree, String nameOfTheSubCountyVillageFour, String wetlandUnderDestructionFour,
                                    String nameOfTheSubCountyVillageFive, String wetlandUnderDestruction_five, String nameOfTheSubCountyVillageSix,
                                    String wetlandUnderDestructionSix, String whatAreTheTreePlantingProgramsKnownInTheArea, String quarter, String region,
                                    String district, String subcounty, String divisionHaveWaterSanitation, String areThereRuralSafeWaterPointSourcesConstructed,
                                    String doTheRuralWaterPointSourcesHaveUserCommittees, String phone_id) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("financial_year", financialYear);
        values.put("quarter", quarter);
        values.put("date", date);
        values.put("region", region);
        values.put("district",district);
        values.put("subcounty",subcounty);
        values.put("parish",parish);
        values.put("village",village);
        values.put("name_of_monitor",nameOfMonitor);
        values.put("phone_number",phoneNumber);
        values.put("division_have_water_sanitation",divisionHaveWaterSanitation);
        values.put("division_have_water_sanitation_if_no",divisionHaveWaterSanitationIfNo);
        values.put("what_are_the_water_sanitationt_protection_activities_taking_place_in_the_sub_county", whatAreTheWaterSanitationtProtectionActivitiesTakingPlaceInTheSubCounty);
        values.put("are_there_rural_safe_water_point_sources_constructed",areThereRuralSafeWaterPointSourcesConstructed);
        values.put("area_in_the_sub_county_one", areaInTheSubCountyOne);
        values.put("water_source_one",waterSourceOne);
        values.put("functional_one",functionalOne);
        values.put("non_functional_one",nonFunctionalOne);
        values.put("no_water_source_available_one",noWaterSourceAvailableOne);
        values.put("area_in_the_sub_county_two", areaInTheSubCountyTwo);
        values.put("water_source_two",waterSourceTwo);
        values.put("functional_two", functionalTwo);
        values.put("non_functional_two", nonFunctionalTwo);
        values.put("no_water_source_available_two", noWaterSourceAvailableTwo);
        values.put("area_in_the_sub_county_three", areaInTheSubCountyThree);
        values.put("water_source_three", waterSourceThree);
        values.put("functional_three", functionalThree);
        values.put("non_functional_three", nonFunctionalThree);
        values.put("no_water_source_available_three", noWaterSourceAvailableThree);
        values.put("area_in_the_sub_county_four", areaInTheSubCountyFour);
        values.put("water_source_four", waterSourceFour);
        values.put("functional_four", functionalFour);
        values.put("non_functional_four", nonFunctionalFour);
        values.put("no_water_source_available_four", noWaterSourceAvailableFour);
        values.put("area_in_the_sub_county_five", areaInTheSubCountyFive);
        values.put("water_source_five",waterSourceFive);
        values.put("functional_five", functionalFive);
        values.put("non_functional_five",nonFunctionalFive);
        values.put("no_water_source_available_five",noWaterSourceAvailableFive);
        values.put("area_in_the_sub_county_six", areaInTheSubCountySix);
        values.put("water_source_six", waterSourceSix);
        values.put("functional_six", functionalSix);
        values.put("non_functional_six", nonFunctionalSix);
        values.put("no_water_source_available_six", noWaterSourceAvailableSix);
        values.put("do_the_rural_water_point_sources_have_user_committees", doTheRuralWaterPointSourcesHaveUserCommittees);
        values.put("if_yes_do_the_rural_water_point_sources_have_user_committees", ifYesDoTheRuralWaterPointSourcesHaveUserCommittees);
        values.put("are_there_any_wetlands_demarcated_or_protected", areThereAnyWetlandsDemarcatedOrProtected);
        values.put("name_of_the_sub_county_village_two", nameOfTheSubCountyVillageTwo);
        values.put("wetland_under_destruction_two",wetlandUnderDestructionTwo);
        values.put("name_of_the_sub_county_village_three", nameOfTheSubCountyVillageThree);
        values.put("wetland_under_destruction_three", wetlandUnderDestructionThree);
        values.put("name_of_the_sub_county_village_four", nameOfTheSubCountyVillageFour);
        values.put("wetland_under_destruction_four", wetlandUnderDestructionFour);
        values.put("name_of_the_sub_county_village_five", nameOfTheSubCountyVillageFive);
        values.put("wetland_under_destruction_five", wetlandUnderDestruction_five);
        values.put("name_of_the_sub_county_village_six", nameOfTheSubCountyVillageSix);
        values.put("wetland_under_destruction_six",wetlandUnderDestructionSix);
        values.put("what_are_the_tree_planting_programs_known_in_the_area",whatAreTheTreePlantingProgramsKnownInTheArea);
        values.put("phone_id",phone_id);

        try {
            db.insert("water_and_sanitation", null, values);
            Log.d("SQL-RECORD: ", "SAVED");
        } catch (Exception e) {
            Log.d("SQL-RECORD: ", e.toString());
        }
    }

    public Cursor readWaterSaniationData() {
        db = this.getWritableDatabase();
        return this.getWritableDatabase().rawQuery("SELECT * FROM water_and_sanitation ORDER BY record_id DESC", null);
    }


    public void deleteSingleWaterSaniation(String rowId) {
        db = this.getWritableDatabase();
        db.delete("water_and_sanitation", KEY_ID + "=" + rowId, null);
    }
}

