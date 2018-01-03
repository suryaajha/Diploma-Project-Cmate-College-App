package com.thirdyear.project.cmateproject.advertise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.utils.CommonIntents;

public class AdDetailsActivity extends AppCompatActivity {

    private Addvertise addvertise = null ;
    public static final String POSITION = "POSITION" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Somehow get the addvertise object
        addvertise = new Addvertise() ;
        addvertise.setEmailAddress("fkdfj");
        addvertise.setPhoneNumber("9766844502");

        ((LinearLayout) findViewById(R.id.addDetailsCallMeButtonId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonIntents.startCallingActivity( addvertise.getPhoneNumber() , AdDetailsActivity.this );
            }
        });

        ((LinearLayout) findViewById(R.id.addDetailsMessageMeButtonId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonIntents.startMessagingActivity( addvertise.getPhoneNumber() ,"Request for " + addvertise.getTitle() , AdDetailsActivity.this );
            }
        });

        ((LinearLayout) findViewById(R.id.addDetailsMailMeButtonId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] allAddress = {addvertise.getEmailAddress()} ;
                CommonIntents.startMailingActivity( allAddress ,"Request for " + addvertise.getTitle() , AdDetailsActivity.this );
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fillUpDetails( Addvertise addvertise ) {



    }
}
