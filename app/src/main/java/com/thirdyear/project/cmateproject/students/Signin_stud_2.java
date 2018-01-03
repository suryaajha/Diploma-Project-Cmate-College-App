package com.thirdyear.project.cmateproject.students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thirdyear.project.cmateproject.R;

public class Signin_stud_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_stud_2);
        Button signinbutton=(Button)findViewById(R.id.signinbutton);

        signinbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), Student_home.class);

                startActivity(i1);
            }
        });



    }
}
