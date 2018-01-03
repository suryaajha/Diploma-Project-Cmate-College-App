package com.thirdyear.project.cmateproject;

import android.app.*;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.thirdyear.project.cmateproject.advertise.Addvertise;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.main_details.DataAndType;
import com.thirdyear.project.cmateproject.notification.CMateNotification;
import com.thirdyear.project.cmateproject.sqlite.CMateSQLiteDatabaseContract;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;
import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static int NC =  0 ;
    public static int MC = 0  ;

    public static final String ADD_COUNT = "addCount" ;
    public static final String NOTIFICATION_COUNT = "notificationCount" ;
    public static final String SHARING_COUNT = "sharingCount" ;
    public static final String PLANOUT_COUNT = "planoutCount" ;

    private ArrayList<DataAndType> dataAndTypeArrayList = new ArrayList<>() ;

    Map<String,String> mapOfDataMessage = null ;
    JSONObject jsonObject = null ;

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {

        StringBuilder keys = new StringBuilder() ;

        int addCount = 0 ;
        int notificationCount = 0  ;
        int sharingCount = 0 ;
        int planoutCount = 0 ;

        RemoteMessage.Notification notification = remoteMessage.getNotification() ;

        // Check whether the recieved remoteMessage is notification or message
        if ( notification != null ) { // Notification
            sendNotification(notification.getBody()) ;
            return ;
        }else { // Message

            try {
                jsonObject = new JSONObject(remoteMessage.getData().toString()) ;
                addCount = jsonObject.getInt(ADD_COUNT) ;
                notificationCount = jsonObject.getInt(NOTIFICATION_COUNT) ;
                sharingCount = jsonObject.getInt(SHARING_COUNT) ;
                planoutCount = jsonObject.getInt(PLANOUT_COUNT) ;
                sendNotification(addCount,notificationCount,sharingCount,planoutCount) ;

            } catch (JSONException e) {
                keys.append("Error in JSOON") ;
                e.printStackTrace();
            }

        }

        //doDatabaseWork(CMateSQLiteDatabaseContract.MessageSaver.TABLE_NAME,remoteMessage.getData().toString());
        Intent intent = new Intent(this , TestActivity3.class ) ;
        intent.putExtra("Data",jsonObject.toString() + "Keys " + keys.toString() ) ;
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
        //startActivity(intent);

    }

    private void sendNotification( String messageBOdy ) {

        //doDatabaseWork(CMateSQLiteDatabaseContract.NotificationSaver.TABLE_NAME,messageBody) ;


        Intent intent = new Intent(this, TestActivity3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_send)
                .setContentTitle("New Notifications Waiting For You")
                .setContentText(messageBOdy )
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[] {1000,1000,1000,1000,1000}) // For Vibration
                .setTicker("New Notifications Sir")
                .setLights(Color.RED, 3000, 3000); // For Lights


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }


    private void sendNotification( int addCount , int notificationCount , int sharingCount , int planoutCount ) {

        //doDatabaseWork(CMateSQLiteDatabaseContract.NotificationSaver.TABLE_NAME,messageBody) ;


        Intent intent = new Intent(this, TestActivity3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_send)
                .setContentTitle("New Notifications Waiting For You")
                .setContentText("" + addCount + " " + notificationCount + " " + sharingCount + " " + planoutCount )
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[] {1000,1000,1000,1000,1000}) // For Vibration
                .setTicker("New Notifications Sir")
                .setLights(Color.RED, 3000, 3000); // For Lights


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

        // Now save all the notifications data to the local file


        // End Now save all the notifications data to the local file
    }

    private void displayArrayListTemp() {
        for ( DataAndType dataAndType : dataAndTypeArrayList ) {
            Toast.makeText(this, "" + dataAndType.getType() , Toast.LENGTH_SHORT).show();
        }
    }

    private void saveMessagesToLocalFile() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        final DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        UIDSync uidSync = UIDSync.obtainUIDSync(getApplicationContext()) ;
        DatabaseReference databaseReferenceToSendingPendingOfCurrentUser = databaseReference.child(uidSync.getPathToUid()).child(RealtimeDatabaseContract.SENDING_PENDING) ;

        dataAndTypeArrayList = new ArrayList<>() ;

        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_ADVERTISE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String reference = dataSnapshot.getValue(String.class) ;
                DatabaseReference databaseReferenceToAddvertise = databaseReference.child(reference) ;
                databaseReferenceToAddvertise.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Addvertise addvertise = dataSnapshot.getValue(Addvertise.class) ;
                        DataAndType dataAndType = new DataAndType(addvertise, WriteObjectToFile.ADDVERTISE_OBJECT) ;
                        dataAndTypeArrayList.add(dataAndType) ;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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

        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_NOTIFICATIONS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String reference = dataSnapshot.getValue(String.class) ;
                DatabaseReference databaseReferenceToNotifications = databaseReference.child(reference) ;
                databaseReferenceToNotifications.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        CMateNotification cMateNotification = dataSnapshot.getValue(CMateNotification.class) ;
                        DataAndType dataAndType = new DataAndType(cMateNotification, WriteObjectToFile.NOTIFICATION_OBJECT) ;
                        dataAndTypeArrayList.add(dataAndType) ;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
        });



    }

}
