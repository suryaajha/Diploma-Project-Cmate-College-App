package com.thirdyear.project.cmateproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.server.DeviceRegistrationToken;
import com.thirdyear.project.cmateproject.server.ServerConnection;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;

import java.util.LinkedHashMap;
import java.util.Map;

public class LauncherActivity extends AppCompatActivity {

    private String firstTimeAppOpenPage = "first_time_app_open" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        /*
            First Send Device Token To Server OK
         */
        // Sending Device Token to server
        //DeviceRegistrationToken.sendTokenToServer(DeviceRegistrationToken.getDeviceToken(), getApplicationContext() ) ; // send token to server


        DatabaseReference databaseReferenceForGettingAutomaticNotifications = FirebaseDatabase.getInstance().getReference().child(App.PATH_TO_UID).child(RealtimeDatabaseContract.SENDING_PENDING) ;

        databaseReferenceForGettingAutomaticNotifications.child(RealtimeDatabaseContract.SENDING_PENDING_ADVERTISE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if ( !dataSnapshot.getKey().equals("count") ) {
                    connectToFirstTimeAppOpenPage() ;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;

        //Todo: goto first server init page
        // then init App.appFor to teaher or student or admin

        // Send Your Token to server

        //Todo: this is dummy create actual uid
        Toast.makeText(this, "" + DeviceRegistrationToken.getDeviceToken(), Toast.LENGTH_LONG).show();

        // End Send Your Token to server

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LauncherActivity.this,TestActivity2.class));
            }
        });

        // Recieve all new messages

        ((Button) findViewById(R.id.testButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectToFirstTimeAppOpenPage();
            }
        });

        // End Recieve all new messages

    }

    private void connectToFirstTimeAppOpenPage() {

        ServerConnection serverConnection = new ServerConnection() ;

        UIDSync uidSync = UIDSync.obtainUIDSync(LauncherActivity.this) ; //Fetching data from SQLiteDatabase#SignInTable

        Map<String,Object> params = new LinkedHashMap<>();
        params.put("pathToUid", uidSync.getPathToUid() ) ;
        serverConnection.sendPostData(params);
        serverConnection.connectToPage(firstTimeAppOpenPage) ;
        serverConnection.go() ;
    }
}
