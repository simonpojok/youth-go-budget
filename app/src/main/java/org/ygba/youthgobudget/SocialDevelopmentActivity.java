package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SocialDevelopmentActivity extends AppCompatActivity {

    private EditText financial_year, date, parish, village, name_of_monitor, phone_number, budget_receipt_date,
            community_mobilization_expected_or_approved, community_mobilization_amount_received,community_mobilization_date_received,
            community_mobilization_date_withdrawn, fal_ylp_uwep_expected_or_approved,fal_ylp_uwep_amount_received,
            fal_ylp_uwep_date_received,fal_ylp_uwep_date_withdrawn,if_yes_how_many_women_have_been_supported,
            name_of_the_women_group_village_or_sub_county_one, number_of_males_one, number_of_females_one, amount_received_one,
            name_of_the_women_group_village_or_sub_county_two,number_of_males_two, number_of_females_two,amount_received_two,
            name_of_the_women_group_village_or_sub_county_three, number_of_males_three, number_of_females_three, amount_received_three,
            name_of_the_women_group_village_or_sub_county_four,number_of_males_four, number_of_females_four,amount_received_four,
            name_of_the_women_group_village_or_sub_county_five, number_of_males_five, number_of_females_five, amount_received_five,
            name_of_the_women_group_village_or_sub_county_six, number_of_males_six, number_of_females_six, amount_received_six,
            name_of_the_women_group_village_or_sub_county_seven, number_of_males_seven, number_of_females_seven, amount_received_seven,
            if_yes_how_many_youths_have_been_supported, name_of_the_youth_group_one, village_or_sub_county_one,
            number_of_group_members_males_one, number_of_female_group_members_one, livelihood_amount_received_one, name_of_the_youth_group_two,
            village_or_sub_county_two, number_of_group_members_males_two, number_of_female_group_members_two, livelihood_amount_received_two,
            name_of_the_youth_group_three, village_or_sub_county_three, number_of_group_members_males_three, number_of_female_group_members_three,
            livelihood_amount_received_three, name_of_the_youth_group_four, village_or_sub_county_four, number_of_group_members_males_four,
            number_of_female_group_members_four, name_of_the_youth_group_five, village_or_sub_county_five, number_of_group_members_males_five,
            number_of_female_group_members_five, livelihood_amount_received_five, name_of_the_youth_group_six, village_or_sub_county_six,
            number_of_group_members_males_six, livelihood_amount_received_six, name_of_the_youth_group_seven, village_or_sub_county_seven,
            number_of_group_members_males_seven, number_of_female_group_members_seven, livelihood_amount_received_seven,
            male_adult_literacy_training_sessions_have_been_conducted_in_this_quarter, female_adult_literacy_training_sessions_have_been_conducted_in_this_quarter,
            mention_any_other_challenges_or_observations_affecting_social_development_sector, district_budget_financial_year, administration_approved_budget,
            administration_percentage_share, finance_approved_budget, finance_percentage_share, statutory_bodies_approved_budget, statutory_bodies_percentage_share,
            production_and_marketing_approved_budget, production_and_marketing_percentage_share, health_approved_budget, health_percentage_share, education_approved_budget,
            education_percentage_share, roads_and_engineering_approved_budget, roads_and_engineering_percentage_share, water_approved_budget, water_percentage_share,
            natural_resources_approved_budget, natural_resources_percentage_share, community_based_services_approved_budget, community_based_services_percentage_share,
            planning_approved_budget, planning_percentage_share, internal_audit_approved_budget, internal_audit_percentage_share,
            trade_industry_and_local_development_approved_budget, trade_industry_and_local_development_percentage_share, total_approved_budget, total_percentage_share,
            wage_approved_budget, wage_percentage_share, non_wage_recurrent_approved_budget,non_wage_recurrent_percentage_share,domestic_development_approved_budget,
            domestic_development_percentage_share,ext_financing_approved_budget, ext_financing_percentage_share, district_or_sector_one, sub_county_one,
            financial_year_one, service_centre_one, community_needs_one, district_or_sector_two, sub_county_two, financial_year_two, service_centre_two,
            community_needs_two, district_or_sector_three, sub_county_three, financial_year_three, service_centre_three, community_needs_three,
            district_or_sector_four, sub_county_four, financial_year_four, service_centre_four, community_needs_four, district_or_sector_five, sub_county_five,
            financial_year_five, service_centre_five, community_needs_five, district_or_sector_six, sub_county_six, financial_year_six, service_centre_six,
            community_needs_six, district_or_sector_seven, sub_county_seven, financial_year_seven, service_centre_seven, community_needs_seven;

    private Spinner quarter, region, district, subcounty,are_the_women_empowerment_programs_functional, is_the_youth_livelihood_program_running;

    private String  financialYear, Quarter, Date, District, Subcounty, Parish, Village, nameOfMonitor, phoneNumber,
            budgetReceiptDate, communityMobilizationExpectedOrApproved, communityMobilizationAmountReceived,
            communityMobilizationDateReceived,communityMobilizationDateWithdrawn, falYlpUwepExpectedOrApproved,
            falYlpUwepAmountReceived, falYlpUwepDateReceived, areTheWomenEmpowermentProgramsFunctional,falYlpUwepdateWithdrawn,
            ifYesHowManyWomenHaveBeenSupported, nameOfTheWomenGroupVillageOrSubCountyOne, numberOfMalesOne, numberOfFemalesOne,
            amountReceivedOne, nameOfTheWomenGroupVillageOrSubCountyTwo, numberOfMalesTwo,numberOfFemalesTwo, amountReceivedTwo,
            nameOfTheWomenGroupVillageOrSubCountyThree, numberOfMalesThree, numberOfFemalesThree, amountReceivedThree,
            nameOfTheWomenGroupVillageOrSubCountyFour,numberOfMalesFour, numberOfFemalesFour, amountReceivedFour,
            nameOfTheWomenGroupVillageOrSubCountyFive, numberOfMalesFive, numberOfFemalesFive, amountReceivedFive,
            nameOfTheWomenGroupVillageOrSubCountySix, numberOfMalesSix, numberOfFemalesSix, amountReceivedSix, nameOfTheWomenGroupVillageOrSubCountySeven,
            numberOfMalesSeven, numberOfFemalesSeven, amountReceivedSeven, isTheYouthLivelihoodProgramRunning, ifYesHowManyYouthsHaveBeenSupported,
            nameOfTheYouthGroupOne, villageOrSubCountyOne, numberOfGroupMembersMalesOne, numberOfFemaleGroupMembersOne, livelihoodAmountReceivedOne,
            nameOfTheYouthGroupTwo, villageOrSubCountyTwo, numberOfGroupMembersMalesTwo, numberOfFemaleGroupMembersTwo, livelihoodAmountReceivedTwo,
            nameOfTheYouthGroupThree, villageOrSubCountyThree, numberOfGroupMembersMalesThree, numberOfFemaleGroupMembersThree, livelihoodAmountReceivedThree,
            nameOfTheYouthGroupFour, villageOrSubCountyFour, numberOfGroupMembersMalesFour, numberOfFemaleGroupMembersFour, nameOfTheYouthGroupFive,
            villageOrSubCountyFive, numberOfGroupMembersMalesFive, numberOfFemaleGroupMembersFive, livelihoodAmountReceivedFive, nameOfTheYouthGroupSix,
            villageOrSubCountySix, numberOfGroupMembersMalesSix, livelihoodAmountReceivedSix, nameOfTheYouthGroupSeven, villageOrSubCountySeven,
            numberOfGroupMembersMalesSeven, numberOfFemaleGroupMembersSeven, livelihoodAmountReceivedSeven,
            maleAdultLiteracyTrainingSessionsHaveBeenConductedInThisQuarter, femaleAdultLiteracyTrainingSessionsHaveBeenConductedInThisQuarter,
            mentionAnyOtherChallengesOrObservationsAffectingSocialDevelopmentSector, districtBudgetFinancialYear, administrationApprovedBudget,
            administrationPercentageShare, financeApprovedBudget, financePercentageShare, statutoryBodiesApprovedBudget, statutoryBodiesPercentageShare,
            productionAndMarketingApprovedBudget, productionAndMarketingPercentageShare, healthApprovedBudget, healthPercentageShare, educationApprovedBudget,
            educationPercentageShare, roadsAndEngineeringApprovedBudget, roadsAndEngineeringPercentageShare, waterApprovedBudget, waterPercentageShare,
            naturalResourcesApprovedBudget, naturalResourcesPercentageShare, communityBasedServicesApprovedBudget, communityBasedServicesPercentageShare,
            planningApprovedBudget, planningPercentageShare, internalAuditApprovedBudget, internalAuditPercentageShare, tradeIndustry_ndLocalDevelopmentApprovedBudget,
            tradeIndustryAndLocalDevelopmentPercentageShare, totalApprovedBudget, totalPercentageShare, wageApprovedBudget, wagePercentageShare,
            nonWageRecurrentApprovedBudget, nonWageRecurrentPercentageShare, domesticDevelopmentApprovedBudget, domesticDevelopmentPercentageShare,
            extFinancingApprovedBudget, extFinancingPercentageShare, districtOrSectorOne, subCountyOne, financialYearOne, serviceCentreOne, communityNeedsOne,
            districtOrSectorTwo, subCountyTwo, financialYearTwo,serviceCentreTwo, communityNeedsTwo, districtOrSectorThree, subCountyThree, financialYearThree,
            serviceCentreThree, communityNeedsThree, districtOrSectorFour, subCountyFour, financialYearFour, serviceCentreFour, communityNeedsFour,
            districtOrSectorFive, subCountyFive, financialYearFive, serviceCentreFive, communityNeedsFive, districtOrSectorSix, subCountySix,
            financialYearSix, serviceCentreSix, communityNeedsSix, districtOrSectorSeven, subCountySeven, financialYearSeven, serviceCentreSeven,
            communityNeedsSeven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_development);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
