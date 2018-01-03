package com.thirdyear.project.cmateproject.main_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.advertise.Addvertise;
import com.thirdyear.project.cmateproject.notification.CMateNotification;
import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;

import java.util.ArrayList;

public class MainDataDetailsActivity extends AppCompatActivity {

    private String uidToRepresentOnScreen ;
    public static String UID_TO_REPRESENT = "UID_TO_REPRESENT" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar) ;

        Intent intentReceived  = getIntent() ;
        if ( intentReceived != null ) {

            this.uidToRepresentOnScreen = intentReceived.getStringExtra(MainDataDetailsActivity.UID_TO_REPRESENT) ;

            System.out.println( this.uidToRepresentOnScreen );

            WriteObjectToFile writeObjectToFile = new WriteObjectToFile(getApplicationContext()) ;

            ArrayList<DataAndType> arrayList = writeObjectToFile.readFromFile(uidToRepresentOnScreen) ;

            System.out.println(arrayList.size()) ;

            DataAndTypeAdapter dataAndTypeAdapter = new DataAndTypeAdapter(this,R.layout.main_details_single_data,arrayList) ;

            ListView listView = ((ListView) findViewById(R.id.mainDataDetailsListViewId)) ;

            listView.setAdapter(dataAndTypeAdapter) ;
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void moveToBottom() {



    }

}
