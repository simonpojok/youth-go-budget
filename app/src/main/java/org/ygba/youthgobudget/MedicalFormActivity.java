package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.ygba.youthgobudget.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MedicalFormActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner Gender,AgeCategory,Level_of_education,Region,Establish_health_education,
            Yes_establish_health_education,Rate_establish_health_education,Best_health_education,Worst_health_education,District,Subcounty;

    private EditText Reason_establish_health_education,Rate_reason_establish_health_education,Best_health_education_spec,
            Best_health_education_reason,Worst_health_education_spec,Worst_health_education_reason,Priority_health_education,
            Priority_health_education_spec,Priority_health_education_reason;

    private String Reasonestablish_health_education,Ratereason_establish_health_education,Besthealth_education_spec,Besthealth_education_reason,
            Worsthealth_education_spec,Worsthealth_education_reason,Priorityhealth_education,Priorityhealth_education_spec,
            Priorityhealth_education_reason,gender,ageCategory,level_of_education,region,district,subcounty,establish_health_education,yes_establish_health_education,
            rate_establish_health_education,best_health_education,worst_health_education,PHONE_ID;

    private Button Save_education_heath;

    private Calendar myCalendar;

    private DatePickerDialog.OnDateSetListener datePicker;

    private Spinner quarter,health_facility_display_budget_information,facility_open_during_hours_that_are_convenient_for_adolescents,
            are_srh_services_offered_for_free,adolescents_privacy,counseling_and_treatment_rooms_allow_for_privacy,code_of_conduct_in_place,
            transparent_confidential_mechanism_for_adolescents_to_submit_complaints,have_providers_been_trained_to_provide_adolescent_friendly_services,
            have_all_staff_been_oriented_to_providing_confidential_adolescent_friendly_services,staff_demonstrate_respect_when_interacting_with_adolescents,
            providers_ensure_the_clients_privacy_and_confidentiality,providers_set_aside_sufficient_time_for_client_provider_interaction,
            peer_educators_or_peer_counselors_available,health_providers_assessed_using_quality_standard_checklists,
            adolescents_female_and_male_play_a_role_in_the_operation_of_the_health_facility,adolescents_involved_in_monitoring_the_quality_of_srh_service_provision,
            can_adolescents_be_seen_in_the_facility_without_the_consent_of_their_parents_or_spouses,written_guidelines_for_providing_adolescent_services,
            condoms_available_to_both_young_men_and_young_women,educational_materials,are_referral_mechanisms_in_place_for_medical_emergencies,
            health_facility_toilet_latrine_accessible_to_patients_with_disabilities,pwd_friendly_facilities_does_the_health_center,water_point_accessible_to_pwds,
            facility_have_a_functional_water_within_nearby,handwashing_facility_installed_at_the_health_facility,health_unit_management_committee_exist,
            if_yes_how_often_do_they_meet,did_the_hc_receive_a_medical_supplies_consignment_from_national_medical_stores_in_this_quarter,does_have_an_ambulance;


    private EditText financial_year, date, parish, village, name_and_grade_of_health_center, name_of_monitor, phone_number, number_of_outpatient_opd_attendance_during_the_quarter,
            number_of_inpatient_attendances_during_the_quarter,recurrent_approved_budgets,recurrent_budget_released, recurrent_date_received, recurrent_date_withdrawn,
            development_approved_budgets,development_budget_released,development_date_received, development_date_withdrawn,maternity_ward_yes,maternity_ward_no,
            general_ward_yes,general_ward_no,delivery_beds_yes,delivery_beds_no,family_planning_services_yes,family_planning_services_no,hiv_counselling_testing_and_treatment_yes,
            hiv_counselling_testing_and_treatment_no,pmtct_yes,pmtct_no,immunization_yes,immunization_no,youth_friendly_corners_yes,youth_friendly_corners_no,
            vaccination_for_hep_b_yes,vaccination_for_hep_b_no,live_no_of_deliveries,still_no_of_deliveries,children_immunized_with_the_pentavalent_vaccine,
            wide_range_of_srh_services_available,toilet_number_of_blocks,toilet_number_of_stances,toilet_male_patient_stances,toilet_female_patient_stances,
            toilet_health_facility_male_staff,toilet_health_facility_female_staff,toilet_health_facility_mixed_staff,toilet_functional,toilet_not_functional,
            latrine_number_of_blocks,latrine_number_of_stances,latrine_male_patient_stances,latrine_female_patient_stances,latrine_health_facility_male_staff,
            latrine_health_facility_female_staff,latrine_health_facility_mixed_staff,latrine_functional,latrine_not_functional,female_change_rooms_number_of_blocks,
            female_change_rooms_number_of_stances,female_change_rooms_male_patient_stances,female_change_rooms_patient_stances,female_change_rooms_health_facility_staff,
            female_change_rooms_health_facility_female_staff,female_change_rooms_health_facility_mixed_staff,female_change_rooms_functional,female_change_rooms_not_functional,
            pwd_friendly_facilities_does_the_health_center_specify,number_borehole,borehole_functional,borehole_non_functional,tap_borehole,tap_functional,tap_non_functional,
            water_tank_borehole,water_tank_functional,water_tank_non_functional,facility_have_a_functional_water_within_nearby_estimate,
            last_visit_of_suport_supervisor,medical_staff_ceiling,total_no_of_medical_staff,no_of_medical_staff_present,non_medical_staff_ceiling,
            total_no_of_non_medical_staff,no_of_non_medical_staff_present,reasons_for_the_absent_staff,last_date_of_medical_staff_performance_appraisal,
            number_of_staff_appraised,if_yes_please_provide_date_as_per_delivery_note,one_stock_required,two_stock_required,three_stock_required,
            four_stock_required,five_stock_required,six_stock_required,when_did_the_health_center_last_drugs_consignment_get_finished,number_of_medical_equipment_bought,
            if_no_how_are_referrals_handled,list_any_other_observations_or_challenges_hc;

    private String financialYear, Quarter, Date, Parish, Village, nameOfMonitor,phoneNumber,nameAndGradeOfHealthCenter, numberOfOutpatientOpdAttendanceDuringTheQuarter,
            numberOfInpatientAttendancesDuringTheQuarter, recurrentApprovedBudgets, recurrentBudgetReleased, recurrentDateReceived, recurrentDateWithdrawn,
            developmentApprovedBudgets,developmentBudgetReleased, developmentDateReceived, developmentDateWithdrawn, healthFacilityDisplayBudgetInformation,maternityWardYes,
            maternityWardNo,generalWardYes,generalWardNo,deliveryBedsYes,deliveryBedsNo,familyPlanningServicesYes,familyPlanningServicesNo,hivCounsellingTestingAndTreatmentYes,
            hivCounsellingTestingAndTreatmentNo,pmtctYes,pmtctNo,immunizationYes,immunizationNo,youthFriendlyCornersYes,youthFriendlyCornersNo,vaccinationForHepBYes,
            vaccinationForHepBNo,livenoOfDeliveries,stillNoOfDeliveries,childrenImmunizedWithThePentavalentVaccine,facilityOpenDuringHoursThatAreConvenientForAdolescents,
            areSrhServicesOfferedForFree,adolescentsPrivacy,counselingAndTreatmentRoomsAllowForPrivacy,codeofConductInPlace,
            transparentConfidentialMechanismForAdolescentsToSubmitComplaints,haveProvidersBeenTrainedToProvideAdolescentFriendlyServices,
            haveAllStaffBeenOrientedToProvidingConfidentialAdolescentFriendlyServices,staffDemonstrateRespectWhenInteractingWithAdolescents,
            providersEnsureTheClientsPrivacyAndConfidentiality,providersSetAsideSufficientTimeForClientProviderInteraction,peerEducatorsOrPeerCounselorsAvailable,
            healthProvidersAssessedUsingQualityStandardChecklists,adolescentsFemaleAndMalePlayARoleInTheOperationOfTheHealthFacility,
            adolescentsInvolvedInMonitoringTheQualityOfSrhServiceProvision,canAdolescentsBeSeenInTheFacilityWithoutTheConsentOfTheirParentsOrSpouses,
            wideRangeOfSrhServicesAvailable,writtenGuidelinesForProvidingAdolescentServices,condomsAvailableToBothYoungMenAndYoungWomen,educationalMaterials,
            areReferralMechanismsInPlaceForMedicalEmergencies,toiletNumberOfBlocks,toiletNumberOfStances,toiletMalePatientStances,toiletFemalePatientStances,
            toiletHealthFacilityMaleStaff,toiletHealthFacilityFemaleStaff,toiletHealthFacilityMixedStaff,toiletFunctional,toiletNotFunctional,latrineNumberOfBlocks,
            latrineNumberOfStances,latrineMalePatientStances,latrineFemalePatientStances,latrineHealthFacilityMaleStaff,latrineHealthFacilityFemaleStaff,
            latrineHealthFacilityMixedStaff,latrineFunctional,latrineNotFunctional,femaleChangeRoomsNumberOfBlocks,femaleChangeRoomsNumberOfStances,
            femaleChangeRoomsMalePatientStances,femaleChangeRoomsPatientStances,femaleChangeRoomsHealthFacilityStaff,femaleChangeRoomsHealthFacilityFemaleStaff,
            femaleChangeRoomsHealthFacilityMixedStaff,femaleChangeRoomsFunctional,femaleChangeRoomsNotFunctional,
            healthFacilityToiletLatrineAccessibleToPatientsWithDisabilities,pwdFriendlyFacilitiesDoesTheHealthCenter,pwdFriendlyFacilitiesDoesTheHealthCenterSpecify,
            numberBorehole,boreholeFunctional,boreholeNonFunctional,tapBorehole,tapFunctional,tapNonFunctional,waterTankBorehole,waterTankFunctional,waterTankNonFunctional,
            waterPointAccessibleToPwds,facilityHaveAFunctionalWaterWithinNearby,facilityHaveAFunctionalWaterWithinNearbyEstimate,
            handwashingFacilityInstalledAtTheHealthFacility,healthUnitManagementCommitteeExist,ifyesHowOftenDoTheyMeet,lastVisitOfSuportSupervisor,medicalStaffCeiling,
            totalNoOfMedicalStaff,noOfMedicalStaffPresent,nonMedicalStaffCeiling,totalNoOfNonMedicalStaff,noOfNonMedicalStaffPresent,reasonsForTheAbsentStaff,
            lastDateOfMedicalStaffPerformanceAppraisal,numberOfStaffAppraised,didTheHcReceiveAMedicalSuppliesConsignmentFromNationalMedicalStoresInThisQuarter,
            ifYesPleaseProvideDateAsPerDeliveryNote,oneStockRequired,twoStockRequired,threeStockRequired,fourStockRequired,fiveStockRequired,sixStockRequired,
            whenDidTheHealthCenterLastDrugsConsignmentGetFinished,numberOfMedicalEquipmentBought,doesHaveAnAmbulance,ifNoHowAreReferralsHandled,
            listAnyOtherObservationsOrChallengesHc;



    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myCalendar = Calendar.getInstance();

        databaseHelper = new DatabaseHelper(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalFormActivity.this,MedicalRecordActivity.class);
                startActivity(intent);
            }});

        initialise_spinner();
        initialize_edited();

    }

    private void initialize_edited() {
        Reason_establish_health_education = findViewById(R.id.reason_establish_health_education);
        Rate_reason_establish_health_education = findViewById(R.id.rate_reason_establish_health_education);
        Best_health_education_spec = findViewById(R.id.best_health_education_spec);
        Best_health_education_reason = findViewById(R.id.best_health_education_reason);
        Worst_health_education_spec = findViewById(R.id.worst_health_education_spec);
        Worst_health_education_reason = findViewById(R.id.worst_health_education_reason);
        Priority_health_education = findViewById(R.id.priority_health_education);
        Priority_health_education_spec = findViewById(R.id.priority_health_education_spec);
        Priority_health_education_reason = findViewById(R.id.priority_health_education_reason);
        Save_education_heath = findViewById(R.id.save_education_heath);
        Save_education_heath.setOnClickListener(MedicalFormActivity.this);
        Priority_health_education.setOnClickListener(MedicalFormActivity.this);


        financial_year = findViewById(R.id.financial_year);
        date = findViewById(R.id.date);
        parish = findViewById(R.id.parish);
        village = findViewById(R.id.village);
        name_and_grade_of_health_center = findViewById(R.id.name_and_grade_of_health_center);
        name_of_monitor  = findViewById(R.id.name_of_monitor);
        phone_number = findViewById(R.id.phone_number);
        number_of_outpatient_opd_attendance_during_the_quarter = findViewById(R.id.number_of_outpatient_opd_attendance_during_the_quarter);
        number_of_inpatient_attendances_during_the_quarter = findViewById(R.id.number_of_inpatient_attendances_during_the_quarter);
        recurrent_approved_budgets = findViewById(R.id.recurrent_approved_budgets);
        recurrent_budget_released = findViewById(R.id.recurrent_budget_released);
        recurrent_date_received = findViewById(R.id.recurrent_date_received);
        recurrent_date_withdrawn = findViewById(R.id.recurrent_date_withdrawn);
        development_approved_budgets = findViewById(R.id.development_approved_budgets);
        development_budget_released = findViewById(R.id.development_budget_released);
        development_date_received = findViewById(R.id.development_date_received);
        development_date_withdrawn = findViewById(R.id.development_date_withdrawn);
        maternity_ward_yes = findViewById(R.id.maternity_ward_yes);
        maternity_ward_no = findViewById(R.id.maternity_ward_no);
        general_ward_yes = findViewById(R.id.general_ward_yes);
        general_ward_no = findViewById(R.id.general_ward_no);
        delivery_beds_yes = findViewById(R.id.delivery_beds_yes);
        delivery_beds_no = findViewById(R.id.delivery_beds_no);
        family_planning_services_yes = findViewById(R.id.family_planning_services_yes);
        family_planning_services_no = findViewById(R.id.family_planning_services_no);
        hiv_counselling_testing_and_treatment_yes = findViewById(R.id.hiv_counselling_testing_and_treatment_yes);
        hiv_counselling_testing_and_treatment_no = findViewById(R.id.hiv_counselling_testing_and_treatment_no);
        pmtct_yes = findViewById(R.id.pmtct_yes);
        pmtct_no = findViewById(R.id.pmtct_no);
        immunization_yes = findViewById(R.id.immunization_yes);
        immunization_no = findViewById(R.id.immunization_no);
        youth_friendly_corners_yes = findViewById(R.id.youth_friendly_corners_yes);
        youth_friendly_corners_no = findViewById(R.id.youth_friendly_corners_no);
        vaccination_for_hep_b_yes = findViewById(R.id.vaccination_for_hep_b_yes);
        vaccination_for_hep_b_no = findViewById(R.id.vaccination_for_hep_b_no);
        live_no_of_deliveries = findViewById(R.id.live_no_of_deliveries);
        still_no_of_deliveries = findViewById(R.id.still_no_of_deliveries);
        children_immunized_with_the_pentavalent_vaccine = findViewById(R.id.children_immunized_with_the_pentavalent_vaccine);
        wide_range_of_srh_services_available = findViewById(R.id.wide_range_of_srh_services_available);
        toilet_number_of_blocks = findViewById(R.id.toilet_number_of_blocks);
        toilet_number_of_stances = findViewById(R.id.toilet_number_of_stances);
        toilet_male_patient_stances = findViewById(R.id.toilet_male_patient_stances);
        toilet_female_patient_stances = findViewById(R.id.toilet_female_patient_stances);
        toilet_health_facility_male_staff = findViewById(R.id.toilet_health_facility_male_staff);
        toilet_health_facility_female_staff = findViewById(R.id.toilet_health_facility_female_staff);
        toilet_health_facility_mixed_staff = findViewById(R.id.toilet_health_facility_mixed_staff);
        toilet_functional = findViewById(R.id.toilet_functional);
        toilet_not_functional = findViewById(R.id.toilet_not_functional);
        latrine_number_of_blocks = findViewById(R.id.latrine_number_of_blocks);
        latrine_number_of_stances = findViewById(R.id.latrine_number_of_stances);
        latrine_male_patient_stances = findViewById(R.id.latrine_male_patient_stances);
        latrine_female_patient_stances = findViewById(R.id.latrine_female_patient_stances);
        latrine_health_facility_male_staff = findViewById(R.id.latrine_health_facility_male_staff);
        latrine_health_facility_female_staff = findViewById(R.id.latrine_health_facility_female_staff);
        latrine_health_facility_mixed_staff = findViewById(R.id.latrine_health_facility_mixed_staff);
        latrine_functional = findViewById(R.id.latrine_functional);
        latrine_not_functional = findViewById(R.id.latrine_not_functional);
        female_change_rooms_number_of_blocks = findViewById(R.id.female_change_rooms_number_of_blocks);
        female_change_rooms_number_of_stances = findViewById(R.id.female_change_rooms_number_of_stances);
        female_change_rooms_male_patient_stances = findViewById(R.id.female_change_rooms_male_patient_stances);
        female_change_rooms_patient_stances = findViewById(R.id.female_change_rooms_patient_stances);
        female_change_rooms_health_facility_staff = findViewById(R.id.female_change_rooms_health_facility_staff);
        female_change_rooms_health_facility_female_staff = findViewById(R.id.female_change_rooms_health_facility_female_staff);
        female_change_rooms_health_facility_mixed_staff = findViewById(R.id.female_change_rooms_health_facility_mixed_staff);
        female_change_rooms_functional = findViewById(R.id.female_change_rooms_functional);
        female_change_rooms_not_functional = findViewById(R.id.female_change_rooms_not_functional);
        pwd_friendly_facilities_does_the_health_center_specify = findViewById(R.id.pwd_friendly_facilities_does_the_health_center_specify);
        number_borehole = findViewById(R.id.number_borehole);
        borehole_functional = findViewById(R.id.borehole_functional);
        borehole_non_functional = findViewById(R.id.borehole_non_functional);
        tap_borehole = findViewById(R.id.tap_borehole);
        tap_functional = findViewById(R.id.tap_functional);
        tap_non_functional = findViewById(R.id.tap_non_functional);
        water_tank_borehole = findViewById(R.id.water_tank_borehole);
        water_tank_functional = findViewById(R.id.water_tank_functional);
        water_tank_non_functional = findViewById(R.id.water_tank_non_functional);
        facility_have_a_functional_water_within_nearby_estimate = findViewById(R.id.facility_have_a_functional_water_within_nearby_estimate);
        last_visit_of_suport_supervisor = findViewById(R.id.last_visit_of_suport_supervisor);
        medical_staff_ceiling = findViewById(R.id.medical_staff_ceiling);
        total_no_of_medical_staff = findViewById(R.id.total_no_of_medical_staff);
        no_of_medical_staff_present = findViewById(R.id.no_of_medical_staff_present);
        non_medical_staff_ceiling  = findViewById(R.id.non_medical_staff_ceiling);
        total_no_of_non_medical_staff = findViewById(R.id.total_no_of_non_medical_staff);
        no_of_non_medical_staff_present = findViewById(R.id.no_of_non_medical_staff_present);
        reasons_for_the_absent_staff = findViewById(R.id.reasons_for_the_absent_staff);
        last_date_of_medical_staff_performance_appraisal = findViewById(R.id.last_date_of_medical_staff_performance_appraisal);
        number_of_staff_appraised = findViewById(R.id.number_of_staff_appraised);
        if_yes_please_provide_date_as_per_delivery_note = findViewById(R.id.if_yes_please_provide_date_as_per_delivery_note);
        one_stock_required = findViewById(R.id.one_stock_required);
        two_stock_required = findViewById(R.id.two_stock_required);
        three_stock_required = findViewById(R.id.three_stock_required);
        four_stock_required = findViewById(R.id.four_stock_required);
        five_stock_required = findViewById(R.id.five_stock_required);
        six_stock_required = findViewById(R.id.six_stock_required);
        when_did_the_health_center_last_drugs_consignment_get_finished = findViewById(R.id.when_did_the_health_center_last_drugs_consignment_get_finished);
        number_of_medical_equipment_bought = findViewById(R.id.number_of_medical_equipment_bought);
        if_no_how_are_referrals_handled = findViewById(R.id.if_no_how_are_referrals_handled);
        list_any_other_observations_or_challenges_hc = findViewById(R.id.list_any_other_observations_or_challenges_hc);



//        Spinner
        quarter = findViewById(R.id.quarter);

        health_facility_display_budget_information = findViewById(R.id.health_facility_display_budget_information);
        facility_open_during_hours_that_are_convenient_for_adolescents = findViewById(R.id.facility_open_during_hours_that_are_convenient_for_adolescents);
        are_srh_services_offered_for_free = findViewById(R.id.are_srh_services_offered_for_free);
        adolescents_privacy = findViewById(R.id.adolescents_privacy);
        counseling_and_treatment_rooms_allow_for_privacy = findViewById(R.id.counseling_and_treatment_rooms_allow_for_privacy);
        code_of_conduct_in_place = findViewById(R.id.code_of_conduct_in_place);
        transparent_confidential_mechanism_for_adolescents_to_submit_complaints = findViewById(R.id.transparent_confidential_mechanism_for_adolescents_to_submit_complaints);
        have_providers_been_trained_to_provide_adolescent_friendly_services = findViewById(R.id.have_providers_been_trained_to_provide_adolescent_friendly_services);
        have_all_staff_been_oriented_to_providing_confidential_adolescent_friendly_services = findViewById(R.id.have_all_staff_been_oriented_to_providing_confidential_adolescent_friendly_services);
        staff_demonstrate_respect_when_interacting_with_adolescents = findViewById(R.id.staff_demonstrate_respect_when_interacting_with_adolescents);
        providers_ensure_the_clients_privacy_and_confidentiality = findViewById(R.id.providers_ensure_the_clients_privacy_and_confidentiality);
        providers_set_aside_sufficient_time_for_client_provider_interaction = findViewById(R.id.providers_set_aside_sufficient_time_for_client_provider_interaction);
        peer_educators_or_peer_counselors_available = findViewById(R.id.peer_educators_or_peer_counselors_available);
        health_providers_assessed_using_quality_standard_checklists = findViewById(R.id.health_providers_assessed_using_quality_standard_checklists);
        adolescents_female_and_male_play_a_role_in_the_operation_of_the_health_facility = findViewById(R.id.adolescents_female_and_male_play_a_role_in_the_operation_of_the_health_facility);
        adolescents_involved_in_monitoring_the_quality_of_srh_service_provision = findViewById(R.id.adolescents_involved_in_monitoring_the_quality_of_srh_service_provision);
        can_adolescents_be_seen_in_the_facility_without_the_consent_of_their_parents_or_spouses = findViewById(R.id.can_adolescents_be_seen_in_the_facility_without_the_consent_of_their_parents_or_spouses);
        written_guidelines_for_providing_adolescent_services = findViewById(R.id.written_guidelines_for_providing_adolescent_services);
        condoms_available_to_both_young_men_and_young_women = findViewById(R.id.condoms_available_to_both_young_men_and_young_women);
        educational_materials = findViewById(R.id.educational_materials);
        are_referral_mechanisms_in_place_for_medical_emergencies = findViewById(R.id.are_referral_mechanisms_in_place_for_medical_emergencies);
        health_facility_toilet_latrine_accessible_to_patients_with_disabilities = findViewById(R.id.health_facility_toilet_latrine_accessible_to_patients_with_disabilities);
        pwd_friendly_facilities_does_the_health_center = findViewById(R.id.pwd_friendly_facilities_does_the_health_center);
        water_point_accessible_to_pwds = findViewById(R.id.water_point_accessible_to_pwds);
        facility_have_a_functional_water_within_nearby = findViewById(R.id.facility_have_a_functional_water_within_nearby);
        handwashing_facility_installed_at_the_health_facility = findViewById(R.id.handwashing_facility_installed_at_the_health_facility);
        health_unit_management_committee_exist = findViewById(R.id.health_unit_management_committee_exist);
        if_yes_how_often_do_they_meet = findViewById(R.id.if_yes_how_often_do_they_meet);
        did_the_hc_receive_a_medical_supplies_consignment_from_national_medical_stores_in_this_quarter = findViewById(R.id.did_the_hc_receive_a_medical_supplies_consignment_from_national_medical_stores_in_this_quarter);
        does_have_an_ambulance = findViewById(R.id.does_have_an_ambulance);
        latrineMalePatientStances = latrine_male_patient_stances.getText().toString();

    }

    private void initialise_spinner() {

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


        Establish_health_education = findViewById(R.id.establish_health_education);
        Yes_establish_health_education = findViewById(R.id.yes_establish_health_education);
        Rate_establish_health_education = findViewById(R.id.rate_establish_health_education);
        Best_health_education = findViewById(R.id.best_health_education);
        Worst_health_education = findViewById(R.id.worst_health_education);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_education_heath:

                Reasonestablish_health_education = Reason_establish_health_education.getText().toString();
                Ratereason_establish_health_education = Rate_reason_establish_health_education.getText().toString();
                Besthealth_education_spec = Best_health_education_spec.getText().toString();
                Besthealth_education_reason = Best_health_education_reason.getText().toString();
                Worsthealth_education_spec = Worst_health_education_spec.getText().toString();
                Worsthealth_education_reason = Worst_health_education_reason.getText().toString();
                Priorityhealth_education = Priority_health_education.getText().toString();
                Priorityhealth_education_spec = Priority_health_education_spec.getText().toString();
                Priorityhealth_education_reason = Priority_health_education_reason.getText().toString();

                gender = String.valueOf(Gender.getSelectedItem());
                ageCategory = String.valueOf(AgeCategory.getSelectedItem());
                level_of_education = String.valueOf(Level_of_education.getSelectedItem());
                region = String.valueOf(Region.getSelectedItem());
                district = String.valueOf(District.getSelectedItem());
                subcounty = String.valueOf(Subcounty.getSelectedItem());
                establish_health_education = String.valueOf(Establish_health_education.getSelectedItem());
                yes_establish_health_education = String.valueOf(Yes_establish_health_education.getSelectedItem());
                rate_establish_health_education = String.valueOf(Rate_establish_health_education.getSelectedItem());
                best_health_education = String.valueOf(Best_health_education.getSelectedItem());
                worst_health_education = String.valueOf(Worst_health_education.getSelectedItem());

                String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                PHONE_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+""+today;

                financialYear = financial_year.getText().toString();
                Quarter = String.valueOf(quarter.getSelectedItem());
                Date = date.getText().toString();
                Parish = parish.getText().toString();

                loadDatePicker(date);
                loadDatePicker(recurrent_date_received);
                loadDatePicker(recurrent_date_withdrawn);
                loadDatePicker(development_date_received);
                loadDatePicker(development_date_withdrawn);

                Village = village.getText().toString();
                nameOfMonitor = name_of_monitor.getText().toString();
                phoneNumber = phone_number.getText().toString();
                nameAndGradeOfHealthCenter = name_and_grade_of_health_center.getText().toString();
                numberOfOutpatientOpdAttendanceDuringTheQuarter = number_of_outpatient_opd_attendance_during_the_quarter.getText().toString();
                numberOfInpatientAttendancesDuringTheQuarter = number_of_inpatient_attendances_during_the_quarter.getText().toString();
                recurrentApprovedBudgets = recurrent_approved_budgets.getText().toString();
                recurrentBudgetReleased = recurrent_budget_released.getText().toString();
                recurrentDateReceived = recurrent_date_received.getText().toString();
                recurrentDateWithdrawn = recurrent_date_withdrawn.getText().toString();
                developmentApprovedBudgets = development_approved_budgets.getText().toString();
                developmentBudgetReleased = development_budget_released.getText().toString();
                developmentDateReceived = development_date_received.getText().toString();
                developmentDateWithdrawn = development_date_withdrawn.getText().toString();
                healthFacilityDisplayBudgetInformation = String.valueOf(health_facility_display_budget_information.getSelectedItem());
                maternityWardYes = maternity_ward_yes.getText().toString();
                maternityWardNo = maternity_ward_no.getText().toString();
                generalWardYes = general_ward_yes.getText().toString();
                generalWardNo = general_ward_no.getText().toString();
                deliveryBedsYes = delivery_beds_yes.getText().toString();
                deliveryBedsNo = delivery_beds_no.getText().toString();
                familyPlanningServicesYes = family_planning_services_yes.getText().toString();
                familyPlanningServicesNo = family_planning_services_no.getText().toString();
                hivCounsellingTestingAndTreatmentYes = hiv_counselling_testing_and_treatment_yes.getText().toString();
                hivCounsellingTestingAndTreatmentNo = hiv_counselling_testing_and_treatment_no.getText().toString();
                pmtctYes = pmtct_yes.getText().toString();
                pmtctNo = pmtct_no.getText().toString();
                immunizationYes = immunization_yes.getText().toString();
                immunizationNo = immunization_no.getText().toString();
                youthFriendlyCornersYes = youth_friendly_corners_yes.getText().toString();
                youthFriendlyCornersNo = youth_friendly_corners_no.getText().toString();
                vaccinationForHepBYes = vaccination_for_hep_b_yes.getText().toString();
                vaccinationForHepBNo = vaccination_for_hep_b_no.getText().toString();
                livenoOfDeliveries = live_no_of_deliveries.getText().toString();
                stillNoOfDeliveries = still_no_of_deliveries.getText().toString();
                childrenImmunizedWithThePentavalentVaccine = children_immunized_with_the_pentavalent_vaccine.getText().toString();
                facilityOpenDuringHoursThatAreConvenientForAdolescents = String.valueOf(facility_open_during_hours_that_are_convenient_for_adolescents.getSelectedItem());
                areSrhServicesOfferedForFree = String.valueOf(are_srh_services_offered_for_free.getSelectedItem());
                adolescentsPrivacy = String.valueOf(adolescents_privacy.getSelectedItem());
                counselingAndTreatmentRoomsAllowForPrivacy = String.valueOf(counseling_and_treatment_rooms_allow_for_privacy.getSelectedItem());
                codeofConductInPlace = String.valueOf(code_of_conduct_in_place.getSelectedItem());
                transparentConfidentialMechanismForAdolescentsToSubmitComplaints = String.valueOf(transparent_confidential_mechanism_for_adolescents_to_submit_complaints.getSelectedItem());
                haveProvidersBeenTrainedToProvideAdolescentFriendlyServices = String.valueOf(have_providers_been_trained_to_provide_adolescent_friendly_services.getSelectedItem());
                haveAllStaffBeenOrientedToProvidingConfidentialAdolescentFriendlyServices = String.valueOf(have_all_staff_been_oriented_to_providing_confidential_adolescent_friendly_services.getSelectedItem());
                staffDemonstrateRespectWhenInteractingWithAdolescents = String.valueOf(staff_demonstrate_respect_when_interacting_with_adolescents.getSelectedItem());
                providersEnsureTheClientsPrivacyAndConfidentiality = String.valueOf(providers_ensure_the_clients_privacy_and_confidentiality.getSelectedItem());
                providersSetAsideSufficientTimeForClientProviderInteraction = String.valueOf(providers_set_aside_sufficient_time_for_client_provider_interaction.getSelectedItem());
                peerEducatorsOrPeerCounselorsAvailable = String.valueOf(peer_educators_or_peer_counselors_available.getSelectedItem());
                healthProvidersAssessedUsingQualityStandardChecklists = String.valueOf(health_providers_assessed_using_quality_standard_checklists.getSelectedItem());
                adolescentsFemaleAndMalePlayARoleInTheOperationOfTheHealthFacility = String.valueOf(adolescents_female_and_male_play_a_role_in_the_operation_of_the_health_facility.getSelectedItem());
                adolescentsInvolvedInMonitoringTheQualityOfSrhServiceProvision = String.valueOf(adolescents_involved_in_monitoring_the_quality_of_srh_service_provision.getSelectedItem());
                canAdolescentsBeSeenInTheFacilityWithoutTheConsentOfTheirParentsOrSpouses = String.valueOf(can_adolescents_be_seen_in_the_facility_without_the_consent_of_their_parents_or_spouses.getSelectedItem());
                wideRangeOfSrhServicesAvailable = wide_range_of_srh_services_available.getText().toString();
                writtenGuidelinesForProvidingAdolescentServices = String.valueOf(written_guidelines_for_providing_adolescent_services.getSelectedItem());
                condomsAvailableToBothYoungMenAndYoungWomen = String.valueOf(condoms_available_to_both_young_men_and_young_women.getSelectedItem());
                educationalMaterials = String.valueOf(educational_materials.getSelectedItem());
                areReferralMechanismsInPlaceForMedicalEmergencies = String.valueOf(are_referral_mechanisms_in_place_for_medical_emergencies.getSelectedItem());
                toiletNumberOfBlocks = toilet_number_of_blocks.getText().toString();
                toiletNumberOfStances = toilet_number_of_stances.getText().toString();
                toiletMalePatientStances = toilet_male_patient_stances.getText().toString();
                toiletFemalePatientStances = toilet_female_patient_stances.getText().toString();
                toiletHealthFacilityMaleStaff = toilet_health_facility_male_staff.getText().toString();
                toiletHealthFacilityFemaleStaff = toilet_health_facility_female_staff.getText().toString();
                toiletHealthFacilityMixedStaff = toilet_health_facility_mixed_staff.getText().toString();
                toiletFunctional = toilet_functional.getText().toString();
                toiletNotFunctional = toilet_not_functional.getText().toString();
                latrineNumberOfBlocks = latrine_number_of_blocks.getText().toString();
                latrineNumberOfStances = latrine_number_of_stances.getText().toString();
                latrineFemalePatientStances = latrine_female_patient_stances.getText().toString();
                latrineHealthFacilityMaleStaff = latrine_health_facility_male_staff.getText().toString();
                latrineHealthFacilityFemaleStaff = latrine_health_facility_female_staff.getText().toString();
                latrineHealthFacilityMixedStaff = latrine_health_facility_mixed_staff.getText().toString();
                latrineFunctional = latrine_functional.getText().toString();
                latrineNotFunctional = latrine_not_functional.getText().toString();
                femaleChangeRoomsNumberOfBlocks = female_change_rooms_number_of_blocks.getText().toString();
                femaleChangeRoomsNumberOfStances = female_change_rooms_number_of_stances.getText().toString();
                femaleChangeRoomsMalePatientStances = female_change_rooms_male_patient_stances.getText().toString();
                femaleChangeRoomsPatientStances = female_change_rooms_patient_stances.getText().toString();
                femaleChangeRoomsHealthFacilityStaff = female_change_rooms_health_facility_staff.getText().toString();
                femaleChangeRoomsHealthFacilityFemaleStaff = female_change_rooms_health_facility_female_staff.getText().toString();
                femaleChangeRoomsHealthFacilityMixedStaff = female_change_rooms_health_facility_mixed_staff.getText().toString();
                femaleChangeRoomsFunctional = female_change_rooms_functional.getText().toString();
                femaleChangeRoomsNotFunctional = female_change_rooms_not_functional.getText().toString();
                healthFacilityToiletLatrineAccessibleToPatientsWithDisabilities = String.valueOf(health_facility_toilet_latrine_accessible_to_patients_with_disabilities.getSelectedItem());
                pwdFriendlyFacilitiesDoesTheHealthCenter = String.valueOf(pwd_friendly_facilities_does_the_health_center.getSelectedItem());
                pwdFriendlyFacilitiesDoesTheHealthCenterSpecify = pwd_friendly_facilities_does_the_health_center_specify.getText().toString();
                numberBorehole = number_borehole.getText().toString();
                boreholeFunctional = borehole_functional.getText().toString();
                boreholeNonFunctional = borehole_non_functional.getText().toString();
                tapBorehole = tap_borehole.getText().toString();
                tapFunctional = tap_functional.getText().toString();
                tapNonFunctional = tap_non_functional.getText().toString();
                waterTankBorehole = water_tank_borehole.getText().toString();
                waterTankFunctional = water_tank_functional.getText().toString();
                waterTankNonFunctional = water_tank_non_functional.getText().toString();
                waterPointAccessibleToPwds = String.valueOf(water_point_accessible_to_pwds.getSelectedItem());
                facilityHaveAFunctionalWaterWithinNearby = String.valueOf(facility_have_a_functional_water_within_nearby.getSelectedItem());
                facilityHaveAFunctionalWaterWithinNearbyEstimate = facility_have_a_functional_water_within_nearby_estimate.getText().toString();
                handwashingFacilityInstalledAtTheHealthFacility = String.valueOf(handwashing_facility_installed_at_the_health_facility.getSelectedItem());
                healthUnitManagementCommitteeExist = String.valueOf(health_unit_management_committee_exist.getSelectedItem());
                ifyesHowOftenDoTheyMeet = String.valueOf(if_yes_how_often_do_they_meet.getSelectedItem());
                lastVisitOfSuportSupervisor = last_visit_of_suport_supervisor.getText().toString();
                medicalStaffCeiling = medical_staff_ceiling.getText().toString();
                totalNoOfMedicalStaff = total_no_of_medical_staff.getText().toString();
                noOfMedicalStaffPresent = no_of_medical_staff_present.getText().toString();
                nonMedicalStaffCeiling = non_medical_staff_ceiling.getText().toString();
                totalNoOfNonMedicalStaff = total_no_of_non_medical_staff.getText().toString();
                noOfNonMedicalStaffPresent = no_of_non_medical_staff_present.getText().toString();
                reasonsForTheAbsentStaff = reasons_for_the_absent_staff.getText().toString();
                loadDatePicker(last_date_of_medical_staff_performance_appraisal);
                lastDateOfMedicalStaffPerformanceAppraisal = last_date_of_medical_staff_performance_appraisal.getText().toString();
                numberOfStaffAppraised = number_of_staff_appraised.getText().toString();
                didTheHcReceiveAMedicalSuppliesConsignmentFromNationalMedicalStoresInThisQuarter = String.valueOf(did_the_hc_receive_a_medical_supplies_consignment_from_national_medical_stores_in_this_quarter.getSelectedItem());
                ifYesPleaseProvideDateAsPerDeliveryNote = if_yes_please_provide_date_as_per_delivery_note.getText().toString();
                oneStockRequired = one_stock_required.getText().toString();
                twoStockRequired = two_stock_required.getText().toString();
                threeStockRequired = three_stock_required.getText().toString();
                fourStockRequired = four_stock_required.getText().toString();
                fiveStockRequired = five_stock_required.getText().toString();
                sixStockRequired = six_stock_required.getText().toString();
                loadDatePicker(when_did_the_health_center_last_drugs_consignment_get_finished);
                whenDidTheHealthCenterLastDrugsConsignmentGetFinished = when_did_the_health_center_last_drugs_consignment_get_finished.getText().toString();
                numberOfMedicalEquipmentBought = number_of_medical_equipment_bought.getText().toString();
                doesHaveAnAmbulance = String.valueOf(does_have_an_ambulance.getSelectedItem());
                ifNoHowAreReferralsHandled = if_no_how_are_referrals_handled.getText().toString();
                listAnyOtherObservationsOrChallengesHc = list_any_other_observations_or_challenges_hc.getText().toString();

                try {
                    databaseHelper.save_health(PHONE_ID,Reasonestablish_health_education,Ratereason_establish_health_education,Besthealth_education_spec,
                            Besthealth_education_reason,Worsthealth_education_spec,Worsthealth_education_reason,Priorityhealth_education,Priorityhealth_education_spec,
                            Priorityhealth_education_reason,gender,ageCategory,level_of_education,region,district,subcounty,establish_health_education,
                            yes_establish_health_education,rate_establish_health_education,best_health_education,worst_health_education,financialYear, Quarter, Date, Parish, Village, nameOfMonitor,phoneNumber,nameAndGradeOfHealthCenter, numberOfOutpatientOpdAttendanceDuringTheQuarter,
                            numberOfInpatientAttendancesDuringTheQuarter, recurrentApprovedBudgets, recurrentBudgetReleased, recurrentDateReceived, recurrentDateWithdrawn,
                            developmentApprovedBudgets,developmentBudgetReleased, developmentDateReceived, developmentDateWithdrawn, healthFacilityDisplayBudgetInformation,maternityWardYes,
                            maternityWardNo,generalWardYes,generalWardNo,deliveryBedsYes,deliveryBedsNo,familyPlanningServicesYes,familyPlanningServicesNo,hivCounsellingTestingAndTreatmentYes,
                            hivCounsellingTestingAndTreatmentNo,pmtctYes,pmtctNo,immunizationYes,immunizationNo,youthFriendlyCornersYes,youthFriendlyCornersNo,vaccinationForHepBYes,
                            vaccinationForHepBNo,livenoOfDeliveries,stillNoOfDeliveries,childrenImmunizedWithThePentavalentVaccine,facilityOpenDuringHoursThatAreConvenientForAdolescents,
                            areSrhServicesOfferedForFree,adolescentsPrivacy,counselingAndTreatmentRoomsAllowForPrivacy,codeofConductInPlace,
                            transparentConfidentialMechanismForAdolescentsToSubmitComplaints,haveProvidersBeenTrainedToProvideAdolescentFriendlyServices,
                            haveAllStaffBeenOrientedToProvidingConfidentialAdolescentFriendlyServices,staffDemonstrateRespectWhenInteractingWithAdolescents,
                            providersEnsureTheClientsPrivacyAndConfidentiality,providersSetAsideSufficientTimeForClientProviderInteraction,peerEducatorsOrPeerCounselorsAvailable,
                            healthProvidersAssessedUsingQualityStandardChecklists,adolescentsFemaleAndMalePlayARoleInTheOperationOfTheHealthFacility,
                            adolescentsInvolvedInMonitoringTheQualityOfSrhServiceProvision,canAdolescentsBeSeenInTheFacilityWithoutTheConsentOfTheirParentsOrSpouses,
                            wideRangeOfSrhServicesAvailable,writtenGuidelinesForProvidingAdolescentServices,condomsAvailableToBothYoungMenAndYoungWomen,educationalMaterials,
                            areReferralMechanismsInPlaceForMedicalEmergencies,toiletNumberOfBlocks,toiletNumberOfStances,toiletMalePatientStances,toiletFemalePatientStances,
                            toiletHealthFacilityMaleStaff,toiletHealthFacilityFemaleStaff,toiletHealthFacilityMixedStaff,toiletFunctional,toiletNotFunctional,latrineNumberOfBlocks,
                            latrineNumberOfStances,latrineMalePatientStances,latrineFemalePatientStances,latrineHealthFacilityMaleStaff,latrineHealthFacilityFemaleStaff,
                            latrineHealthFacilityMixedStaff,latrineFunctional,latrineNotFunctional,femaleChangeRoomsNumberOfBlocks,femaleChangeRoomsNumberOfStances,
                            femaleChangeRoomsMalePatientStances,femaleChangeRoomsPatientStances,femaleChangeRoomsHealthFacilityStaff,femaleChangeRoomsHealthFacilityFemaleStaff,
                            femaleChangeRoomsHealthFacilityMixedStaff,femaleChangeRoomsFunctional,femaleChangeRoomsNotFunctional,
                            healthFacilityToiletLatrineAccessibleToPatientsWithDisabilities,pwdFriendlyFacilitiesDoesTheHealthCenter,pwdFriendlyFacilitiesDoesTheHealthCenterSpecify,
                            numberBorehole,boreholeFunctional,boreholeNonFunctional,tapBorehole,tapFunctional,tapNonFunctional,waterTankBorehole,waterTankFunctional,waterTankNonFunctional,
                            waterPointAccessibleToPwds,facilityHaveAFunctionalWaterWithinNearby,facilityHaveAFunctionalWaterWithinNearbyEstimate,
                            handwashingFacilityInstalledAtTheHealthFacility,healthUnitManagementCommitteeExist,ifyesHowOftenDoTheyMeet,lastVisitOfSuportSupervisor,medicalStaffCeiling,
                            totalNoOfMedicalStaff,noOfMedicalStaffPresent,nonMedicalStaffCeiling,totalNoOfNonMedicalStaff,noOfNonMedicalStaffPresent,reasonsForTheAbsentStaff,
                            lastDateOfMedicalStaffPerformanceAppraisal,numberOfStaffAppraised,didTheHcReceiveAMedicalSuppliesConsignmentFromNationalMedicalStoresInThisQuarter,
                            ifYesPleaseProvideDateAsPerDeliveryNote,oneStockRequired,twoStockRequired,threeStockRequired,fourStockRequired,fiveStockRequired,sixStockRequired,
                            whenDidTheHealthCenterLastDrugsConsignmentGetFinished,numberOfMedicalEquipmentBought,doesHaveAnAmbulance,ifNoHowAreReferralsHandled,
                            listAnyOtherObservationsOrChallengesHc);

                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(getIntent());
                }catch (Exception e){
                    Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.priority_health_education:
                listItems = getResources().getStringArray(R.array.health_education_service_array);
                multiplexes(Priority_health_education, Priority_health_education,listItems);
                break;

        }
    }

    private  void multiplexes(EditText clickable_field, final EditText selected, final String[] listItems){
        checkedItems = new boolean[listItems.length];
        clickable_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MedicalFormActivity.this);
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

    private  void loadDatePicker(final EditText daterecorded){
        datePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(daterecorded);
            }
        };

        daterecorded.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MedicalFormActivity.this, datePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(EditText daterecorded) {
        String myFormat = "YYYY-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        daterecorded.setText(sdf.format(myCalendar.getTime()));
    }
}
