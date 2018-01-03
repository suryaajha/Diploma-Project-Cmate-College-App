package com.thirdyear.project.cmateproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thirdyear.project.cmateproject.R;

public class Admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        Button createnoti =(Button)findViewById(R.id.createnoti);

        createnoti.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), AdminCreateNotification.class);

                startActivity(i1);
            }
        });


        Button reportnoti =(Button)findViewById(R.id.reportnoti);

        reportnoti.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), AdminReportNotification.class);

                startActivity(i2);
            }
        });

        Button createteach =(Button)findViewById(R.id.createteach);

        createteach.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), AdminCreateNotification.class);

                startActivity(i3);
            }
        });





    }
}
