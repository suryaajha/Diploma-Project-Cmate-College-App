package com.thirdyear.project.cmateproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thirdyear.project.cmateproject.admin.Signin_adm_1;
import com.thirdyear.project.cmateproject.students.Signin_stud_1;
import com.thirdyear.project.cmateproject.teacher.Signin_teach_1;

public class UserSelection extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);



        Button studbutton=(Button)findViewById(R.id.studentbutton);

        studbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), Signin_stud_1.class);

                startActivity(i1);
            }
        });

        Button teachbutton=(Button)findViewById(R.id.teacherbutton);

        teachbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), Signin_teach_1.class);

                startActivity(i2);
            }
        });

        Button admnbutton=(Button)findViewById(R.id.adminbutton);

        admnbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), Signin_adm_1.class);

                startActivity(i3);
            }
        });

    }
}
