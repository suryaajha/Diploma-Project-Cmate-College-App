package com.thirdyear.project.cmateproject.notification;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.thirdyear.project.cmateproject.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CMateNotification cMateNotification = new CMateNotification("Tomorrow I am Checking your assingments",null,null,1,1,"Some Date",null) ;
        CMateNotification cMateNotification1 = new CMateNotification("Tomorrow I am Checking your assingments Ok",null,null,1,1,"Some Date",null) ;
        CMateNotification cMateNotification2 = new CMateNotification("Tomorrow I am Checking your assingments 2",null,null,1,1,"Some Date",null) ;

        List<CMateNotification> list = new ArrayList<>() ;
        list.add(cMateNotification) ;
        list.add(cMateNotification1) ;
        list.add(cMateNotification2) ;

        NotificationAdapter notificationAdapter = new NotificationAdapter(this,0,list) ;

        ((ListView) findViewById(R.id.allNotificationListView)).setAdapter(notificationAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( NotificationActivity.this , NotificationEntryActivity.class ) ;
                startActivity(intent);
            }
        });
    }

}
