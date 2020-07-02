package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class BudgetConsutanceActivity extends AppCompatActivity {

    private TextView agric,agric_form,agric_records,education,education_form,
            education_records,health,health_form,health_records,water_and_sanitation_form,
            water_and_sanitation,water_and_sanitation_records,social_development,social_development_form,
            social_development_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_consutance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        agric = findViewById(R.id.agric);
        agric_form = findViewById(R.id.agric_form);
        agric_records = findViewById(R.id.agric_records);
        education = findViewById(R.id.education);
        education_form = findViewById(R.id.education_form);
        education_records = findViewById(R.id.education_records);
        health = findViewById(R.id.health);
        health_form = findViewById(R.id.health_form);
        health_records = findViewById(R.id.health_records);
        water_and_sanitation_form = findViewById(R.id.water_and_sanitation_form);
        water_and_sanitation = findViewById(R.id.water_and_sanitation);
        water_and_sanitation_records = findViewById(R.id.water_and_sanitation_records);
        social_development = findViewById(R.id.social_development);
        social_development_form = findViewById(R.id.social_development_form);
        social_development_records = findViewById(R.id.social_development_records);


        agric_form.setVisibility(View.GONE);
        agric_records.setVisibility(View.GONE);

        education_form.setVisibility(View.GONE);
        education_records.setVisibility(View.GONE);

        health_form.setVisibility(View.GONE);
        health_records.setVisibility(View.GONE);

        water_and_sanitation_form.setVisibility(View.GONE);
        water_and_sanitation_records.setVisibility(View.GONE);

        social_development_form.setVisibility(View.GONE);
        social_development_records.setVisibility(View.GONE);

        agric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agric_form.setVisibility(View.VISIBLE);
                agric_records.setVisibility(View.VISIBLE);

                education_form.setVisibility(View.GONE);
                education_records.setVisibility(View.GONE);

                health_form.setVisibility(View.GONE);
                health_records.setVisibility(View.GONE);

                water_and_sanitation_form.setVisibility(View.GONE);
                water_and_sanitation_records.setVisibility(View.GONE);

                social_development_form.setVisibility(View.GONE);
                social_development_records.setVisibility(View.GONE);


            }
        });

        agric_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BudgetConsutanceActivity.this,BudgetFormActivity.class));
            }
        });

        agric_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BudgetConsutanceActivity.this,AgricListActivity.class));
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                education_form.setVisibility(View.VISIBLE);
                education_records.setVisibility(View.VISIBLE);

                agric_form.setVisibility(View.GONE);
                agric_records.setVisibility(View.GONE);

                health_form.setVisibility(View.GONE);
                health_records.setVisibility(View.GONE);

                water_and_sanitation_form.setVisibility(View.GONE);
                water_and_sanitation_records.setVisibility(View.GONE);

                social_development_form.setVisibility(View.GONE);
                social_development_records.setVisibility(View.GONE);
            }
        });

        education_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BudgetConsutanceActivity.this,EducationActivity.class));
            }
        });

        education_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BudgetConsutanceActivity.this,EducationRecordActivity.class));
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                health_form.setVisibility(View.VISIBLE);
                health_records.setVisibility(View.VISIBLE);

                agric_form.setVisibility(View.GONE);
                agric_records.setVisibility(View.GONE);

                education_form.setVisibility(View.GONE);
                education_records.setVisibility(View.GONE);

                water_and_sanitation_form.setVisibility(View.GONE);
                water_and_sanitation_records.setVisibility(View.GONE);

                social_development_form.setVisibility(View.GONE);
                social_development_records.setVisibility(View.GONE);

            }
        });

        health_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BudgetConsutanceActivity.this,MedicalFormActivity.class));
            }
        });

        health_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BudgetConsutanceActivity.this,MedicalRecordActivity.class));
            }
        });

        water_and_sanitation_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BudgetConsutanceActivity.this,WaterSectorActivity.class));
            }
        });

        water_and_sanitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                health_form.setVisibility(View.GONE);
                health_records.setVisibility(View.GONE);

                agric_form.setVisibility(View.GONE);
                agric_records.setVisibility(View.GONE);

                education_form.setVisibility(View.GONE);
                education_records.setVisibility(View.GONE);

                water_and_sanitation_form.setVisibility(View.VISIBLE);
                water_and_sanitation_records.setVisibility(View.VISIBLE);

                social_development_form.setVisibility(View.GONE);
                social_development_records.setVisibility(View.GONE);

            }
        });

        social_development.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                health_form.setVisibility(View.GONE);
                health_records.setVisibility(View.GONE);

                agric_form.setVisibility(View.GONE);
                agric_records.setVisibility(View.GONE);

                education_form.setVisibility(View.GONE);
                education_records.setVisibility(View.GONE);

                water_and_sanitation_form.setVisibility(View.GONE);
                water_and_sanitation_records.setVisibility(View.GONE);

                social_development_form.setVisibility(View.VISIBLE);
                social_development_records.setVisibility(View.VISIBLE);
            }
        });

        social_development_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BudgetConsutanceActivity.this,SocialDevelopmentActivity.class));
            }
        });

    }

}