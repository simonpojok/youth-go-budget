package org.ygba.youthgobudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class BudgetLifeCycleActivity extends AppCompatActivity {

    private TextView txt_approve,txt_prep,txt_mon,txt_exec;

    private String title,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_life_cycle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_approve = findViewById(R.id.txt_approve);
        txt_prep = findViewById(R.id.txt_prep);
        txt_mon = findViewById(R.id.txt_mon);
        txt_exec = findViewById(R.id.txt_exec);

        txt_exec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Budget execution\n(MDAs)";
                message = "July-June\n\nSubmission of work plans for Ministry of local government and agencies\nQuarterly budget releases\nBudget monitoring";
                displayDuties(title,message);
            }
        });

        txt_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Monitoring and Evaluation and Audit (MDAs)";
                message = "July-December\n\nAudit reports published\nCommunity accountability platforms e.g barazas";
                displayDuties(title,message);
            }
        });

        txt_prep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Budget preparation and execution\n(The execution and MOFPED)";
                message = "Local government budget consultations holding\nNational budget conferences";
                displayDuties(title,message);
            }
        });

        txt_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = "Budget Approval\n(Parliament)";

                message = "June\nPresentation of the Budget Speech in Parliament\n\nJanuary\nParliamentary Committee consultations" +
                        " on NBFP\n\nMarch-April\nPresentation of Ministerial Policy Statements to Parliament-Sec " +
                        "13(13)\n\nApril\nAnnual Budget and Tax Bills presented to Parliament-Sec 13 (13";

                displayDuties(title,message);

            }
        });
    }

    private void displayDuties(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(BudgetLifeCycleActivity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog dialog  = builder.create();
        dialog.show();
    }

}
