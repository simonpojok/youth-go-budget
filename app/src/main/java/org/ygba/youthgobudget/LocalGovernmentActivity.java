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
import android.widget.ImageView;

public class LocalGovernmentActivity extends AppCompatActivity {

    private ImageView ic_dc_pic,ic_cao_pic,ic_cp_pic,ic_rdc_pic,ic_hod_pic,ic_ardc_pic,ic_sub_chiefs,ic_sec_pic;

    private String title,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_government);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ic_dc_pic = findViewById(R.id.ic_dc_pic);
        ic_cao_pic = findViewById(R.id.ic_cao_pic);
        ic_cp_pic = findViewById(R.id.ic_cp_pic);
        ic_rdc_pic = findViewById(R.id.ic_rdc_pic);
        ic_hod_pic = findViewById(R.id.ic_hod_pic);
        ic_ardc_pic = findViewById(R.id.ic_ardc_pic);
        ic_sub_chiefs = findViewById(R.id.ic_sub_chiefs);
        ic_sec_pic = findViewById(R.id.ic_sec_pic);

        ic_sec_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Secretaries for services";
                message = "A secretary shall have responsibility for such functions of the district council as the district chairperson may, from time to time, assign to him or her.\n\nThe chairperson shall assign one of the secretaries to be responsible for health and children welfare";

                displayDuties(title,message);


            }
        });

        ic_sub_chiefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Duties and Responsibilities of the sub-county chief";
                message = "";
            }
        });

        ic_ardc_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Assistant/Deputy Resident District Commissioner";
                message = "There shall be such number of deputy or assistant district resident commissioners in a district as the President may deem necessary.\n\nThe deputy or assistant resident district commissioner shall assist the resident district commissioner in carrying out the functions of the office in a specified area of jurisdiction generally as may be determined by the President.";

                displayDuties(title,message);


            }
        });

        ic_hod_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Heads of Department";
                message = "To draw the attention of the district chairperson, the chief administrative officer and the assistant chief administrative officer at the county level or the chief at the parish level to any matter that rouses their concern or interest;\n\nAt the county level to advise the area members of Parliament on all matters pertaining to the county;\n\nAt the county and parish level to resolve problems or disputes referred to it by relevant sub-county or village councils •to resolve problems identified at that level;\n\nTo monitor the delivery of services within its area of jurisdiction;\n\nTo assist in the maintenance of law, order and security;\n\nTo carry out any functions that may be assigned to it by the district council or higher local government councils;\n\nTo carry out any other function conferred by law or incidental to the above.";

                displayDuties(title,message);


            }
        });





        ic_rdc_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Resident District Commissioner";
                message = "The resident district commissioner shall represent the President and the Government in the district;\n\nThe resident district commissioner shall coordinate the administration of Government services in the district;\n\nThe resident district commissioner shall advise the district chairperson on matters of a national nature that may affect the district or its plans or programmes, and particularly the relations between the district and the Government;\n\nThe resident district commissioner shall monitor and inspect the activities of local governments and where necessary advise the chairperson;\n\nThe resident district commissioner carry out such other functions as may be assigned by the President or prescribed by Parliament.\n\nThe resident district commissioner may sensitise the populace on governmental policies and programmes, and in so doing shall liaise with the district chairperson;\n\nThe resident district commissioner may advise the chairperson to instruct the chief internal auditor to carry out a special audit and submit a report to the council;\n\nThe resident district commissioner may draw the attention of the Auditor General to the need for special investigation audits of the local government council;\n\nThe resident district commissioner may draw the attention of the Inspector General of Government to a need to investigate any cases of mismanagement or abuse of office;\n\nThe resident district commissioner may draw the attention of any relevant line Ministry to the divergence from or noncompliance with Government policy by any council within his or her area of jurisdiction;\n\nThe resident district commissioner may in consultation with the speaker or chairperson of a council as the case may be, address the council from time to time on any matter of national importance.";

                displayDuties(title,message);


            }
        });


        ic_cp_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Chairperson LC-5";
                message = "A district chairperson shall preside at meetings of the executive committee of the district;\n\nMonitor the general administration of the district;\n\nMonitor the implementation of council decisions;\n\nSubject to section 79 of this Act and article 197 of the Constitution, monitor and coordinate the activities of the municipal and town councils and of other lower local governments and administrative units in the district;\n\nOn behalf of the council, oversee the performance of persons employed by the Government to provide services in the district and\n\nTo monitor the provision of Government services or the implementation of projects in the district;\n\nSubject to section 64(2)(g), coordinate and monitor government functions as between the district and the Government;\n\nPerform other functions that may be necessary for the better functioning of the district council, or which may be incidental to the functions of chairperson or imposed on the chairperson by any law.\n\nThe chairperson shall abide by, uphold and safeguard the Constitution, the district laws and other laws of Uganda and shall endeavour to promote the welfare of the citizens in the district.\n\nSubject to the Constitution and this Act, the functions conferred on the chairperson may be exercised by the chairperson directly or through elected or appointed officials subordinate to the chairperson.\n\nThe chairperson shall, in the performance of his or her functions, be answerable to the district council.\n\nThe chairperson shall make a report to the council on the state of affairs of the district, at least once a year.";

                displayDuties(title,message);

            }
        });

        ic_cao_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "Chief Administrative officer";
                message = "The chief administrative officer shall be the head of the public service in the district and the head of the administration of the district council and shall be the accounting officer of the district\n\nThe chief administrative officer shall be responsible for the implementation of all lawful decisions taken by the district council\n\nThe chief administrative officer shall give guidance to the local government councils and their departments in the application of the relevant laws and policies\n\nThe chief administrative officer shall supervise, monitor and coordinate the activities of the district and lower council’s employees and departments and ensure accountability and transparency in the management and delivery of the council’s services;\n\nThe chief administrative officer shall develop capacity for development and management of the planning function in the district\n\nThe chief administrative officer shall supervise and coordinate the activities of all delegated services and the officers working in those services;\n\nThe chief administrative officer shall have custody of all documents and records of the local government council;\n\nThe chief administrative officer shall act as a liaison officer between the district council and the Government;\n\nThe chief administrative officer shall advise the chairperson on the administration of the council;\n\nThe chief administrative officer shall assist in the maintenance of law, order and security in the district;\n\nThe chief administrative officer shall carry out any other duty that may be assigned by the district council from time to time.\n\nIn addition to the duties under subsection (2), the chief administrative officer shall perform all statutory duties and functions which he or she is required to do under any other law.\n\nThe office of the chief administrative officer shall not remain vacant for more than three months after the office falls vacant.";

                displayDuties(title,message);
            }
        });

        ic_dc_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "District Councils";
                message = "Exercise all political and executive powers and functions \n\nProvide services as it deems fit with the exception of the functions, powers and services listed under Part 1 of the Second Schedule to this Act \n\nProtect the Constitution and other laws of Uganda and promote democratic governance;\n\nEnsure the implementation and compliance with Government policy \n\nWithout prejudice to the generality of subsection (1), a district council shall perform and carry on the functions and services specified under Part 2 of the Second Schedule to this Act \n\nUrban councils shall have autonomy over their planning and financial management when carrying out the functions and services specified under Part 3 of the Second Schedule to this Act \n\nSubject to subsection (5), in rural areas, a local government council may devolve to a lower council some of the functions and services specified under Part 4 of the Second Schedule to this Act and, in urban areas, functions and services specified under Part 5B of the Second Schedule.";

                displayDuties(title,message);
            }
        });
    }


    private void displayDuties(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(LocalGovernmentActivity.this);
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
