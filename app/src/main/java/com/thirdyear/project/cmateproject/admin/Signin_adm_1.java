package com.thirdyear.project.cmateproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thirdyear.project.cmateproject.R;

public class Signin_adm_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_adm_1);

        Button nextbutton=(Button)findViewById(R.id.nextbutton);

        nextbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), Signin_adm_2.class);

                startActivity(i1);
            }
        });



    }
}
