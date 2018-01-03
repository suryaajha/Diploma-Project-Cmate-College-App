package com.thirdyear.project.cmateproject.sharing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.thirdyear.project.cmateproject.CountersForUserData;
import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.CommunicationContract;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.server.ServerConnection;
import com.thirdyear.project.cmateproject.utils.DatabaseUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SharingEntryActivity extends AppCompatActivity {

    private Button postNewFileButton = null ;
    private Button browseFileButton = null  ;
    private FirebaseDatabase firebaseDatabase = null ;
    private DatabaseReference databaseReference = null ;
    private DatabaseReference referenceToUser = null ;
    private DatabaseReference referenceToUsersSharingSection = null ;

    // GUI Fields

    private EditText titleEditText = null ;
    private TextView fileNameTextView = null ;

    private Spinner departmentSpinner = null ;
    private Spinner semesterSpinner = null ;
    private Spinner divisionSpinner = null ;
    private Spinner batchSpinner = null ;

    // End Gui Fields

    private String uid = "7Rd0KSRzFwV5P4qgznUCcWTHSKw1" ;
    private String newKey = null ;
    private int initialSharingCount = 0 ;
    private int finalSharingCount = 0 ;

    private CMateSharing cMateSharing = null ;

    // Post Data
    private String sendingDepartment = null ;
    private String sendingSemester = null ;
    private String sendingDivision = null ;
    private String sendingBatch = null ;
    private String sendingReferenceInString = null ;
    private String sendingWhatToSend  = CommunicationContract.WHAT_TO_SEND_SHARING; // Sharing
    private String sendingWhomToSend = null ;
    private String sendingRef = "ref" ;

    private String pageToConnect = "update_sending_pending" ;

    //Request
    private static int RC_FILE_CHOOSE_REQUEST = 1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Firebase

        firebaseDatabase = FirebaseDatabase.getInstance() ;
        databaseReference = firebaseDatabase.getReference() ;

        // End Firebase
        // For GUI Enteries
        initEntries();
        // End For GUI Enteries

        // Handle Those Spinner Visible and invisible states

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if ( position == 1 ) { // i.e ALL
                    findViewById(R.id.semester_card_view).setVisibility(View.GONE);
                    findViewById(R.id.division_card_view).setVisibility(View.GONE);
                    findViewById(R.id.batch_card_view).setVisibility(View.GONE) ;
                }else {
                    findViewById(R.id.semester_card_view).setVisibility(View.VISIBLE);
                    findViewById(R.id.division_card_view).setVisibility(View.VISIBLE);
                    findViewById(R.id.batch_card_view).setVisibility(View.VISIBLE) ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if ( position == 1 ) { // i.e ALL
                    findViewById(R.id.division_card_view).setVisibility(View.GONE);
                    findViewById(R.id.batch_card_view).setVisibility(View.GONE) ;
                }else {
                    findViewById(R.id.division_card_view).setVisibility(View.VISIBLE);
                    findViewById(R.id.batch_card_view).setVisibility(View.VISIBLE) ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if ( position == 1 ) { // i.e ALL
                    findViewById(R.id.batch_card_view).setVisibility(View.GONE) ;
                }else {
                    findViewById(R.id.batch_card_view).setVisibility(View.VISIBLE) ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // End Handle Those Spinner Visible and invisible states

        // Handle Browse Button

        handleBrowseButton()  ;

        // Done Handle Browse Button

        //Edit1
        postNewFileButton = ((Button) findViewById(R.id.postNewAddButtonId)) ;
        postNewFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View renderView = view ;

                boolean everythingOk = validateData() ;

                if ( everythingOk ) {

                    cMateSharing = constructCMateSharing() ;

                    referenceToUser = constructPathToUser( uid ) ;
                    referenceToUser
                            .child(RealtimeDatabaseContract.COUNTERS)
                            .addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    CountersForUserData counter = dataSnapshot.getValue(CountersForUserData.class) ;
                                    Toast.makeText(SharingEntryActivity.this, "" + counter.getSharing() , Toast.LENGTH_SHORT).show();

                                    initialSharingCount = counter.getSharing()  ;
                                    finalSharingCount = initialSharingCount +1 ;

                                    newKey = RealtimeDatabaseContract.COUNTER_NEW_SHARING + finalSharingCount ;

                                    // Create a New Add With New Key
                                    referenceToUsersSharingSection = constructPath( uid ) ;
                                    if ( referenceToUsersSharingSection != null ) {
                                        cMateSharing.setDbPathToSelfReference(DatabaseUtils.createReferenceString(referenceToUsersSharingSection.child(newKey)));
                                        Task sharingUploadTask = referenceToUsersSharingSection.child(newKey).setValue(cMateSharing) ;

                                        sharingUploadTask.addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {
                                                // Now Update the Counter i.e Increment it

                                                Map<String,Object> map = new HashMap<String, Object>() ;
                                                map.put(RealtimeDatabaseContract.COUNTERS_SHARING,finalSharingCount) ;

                                                referenceToUser
                                                        .child(RealtimeDatabaseContract.COUNTERS)
                                                        .child(RealtimeDatabaseContract.COUNTER_NEW_NAME )
                                                        .updateChildren(map) ;

                                                // End
                                                // Now to sending pending work so that other users can recieve notifications

                                                createPostData(cMateSharing.getDbPathToSelfReference()) ;

                                                // End Now to sending pending work so that other users can recieve notifications

                                                Snackbar.make(renderView, "Uploaded File to Server", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                            }
                                        }) ;

                                        sharingUploadTask.addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(renderView, "There is some problem in Uploading File", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                            }
                                        }) ;
                                    }else {
                                        Snackbar.make(renderView, "You are not a valid user for this action", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }


                                    // Remove the Child Event Listener since all work is done
                                    referenceToUser
                                            .child(RealtimeDatabaseContract.COUNTERS)
                                            .removeEventListener(this) ;
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

                }else {
                    Snackbar.make(view, "Make Sure you have entered all valid data", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

        });

        //Edit1

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void handleBrowseButton() {

        browseFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentForImage = new Intent(Intent.ACTION_GET_CONTENT) ;
                intentForImage.setType("file/*") ;
                intentForImage.putExtra(Intent.EXTRA_LOCAL_ONLY , true ) ;

                startActivityForResult(Intent.createChooser(intentForImage,"Choose the image using"),RC_FILE_CHOOSE_REQUEST );
            }
        });

    }

    private void initEntries() {
        titleEditText = ((EditText) findViewById(R.id.addTitleEditText));
        fileNameTextView = ((TextView) findViewById(R.id.filename_show_id)) ;
        browseFileButton = ((Button) findViewById(R.id.browse_file_button_id)) ;

        semesterSpinner = ((Spinner) findViewById(R.id.audienceSpinnerId));
        departmentSpinner = ((Spinner) findViewById(R.id.departmentSpinnerId));
        divisionSpinner = ((Spinner) findViewById(R.id.divisionSpinnerId));
        batchSpinner = ((Spinner) findViewById(R.id.batchSpinnerId));

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        titleEditText = null ;
        fileNameTextView = null ;
        browseFileButton = null ;

        semesterSpinner = null ;
        departmentSpinner = null ;
        batchSpinner = null ;
        divisionSpinner = null ;

    }

    private boolean validateData() {

        if ((titleEditText.getText().toString().length() > 0) && (fileNameTextView.getText().toString().length() > 0) && (semesterSpinner.getSelectedItemPosition() != 0) && (departmentSpinner.getSelectedItemPosition() != 0) && (divisionSpinner.getSelectedItemPosition() != 0) && (batchSpinner.getSelectedItemPosition() != 0) ) {
            if ( ((departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.GONE ) && (departmentSpinner.getVisibility() == View.GONE) && (batchSpinner.getVisibility() == View.GONE) ) || (departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.VISIBLE ) && (departmentSpinner.getVisibility() == View.GONE) && (batchSpinner.getVisibility() == View.GONE) || (departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.VISIBLE ) && (departmentSpinner.getVisibility() == View.VISIBLE) && (batchSpinner.getVisibility() == View.GONE) || (departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.VISIBLE ) && (departmentSpinner.getVisibility() == View.VISIBLE) && (batchSpinner.getVisibility() == View.VISIBLE) )
                return true ;
        } else {
            return false;
        }
        return false ;
    }

    private CMateSharing constructCMateSharing() {

        CMateSharing cMateSharing = new CMateSharing("Title" , "Dnwload Yrill") ;

        cMateSharing.setPosterUid(uid) ;

        cMateSharing.setPostingTimeInMillisecond(System.currentTimeMillis()) ;

        return cMateSharing ;
    }

    private DatabaseReference constructPath( String uid ) {
        if ( App.appFor == App.STUDENT_CODE ) {

            //Todo: Take all credintails from the SqlLiteHelper class OK this is dummy

            DatabaseReference referenceToUsersSharingSection = databaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(RealtimeDatabaseContract.STUDENTS)
                    .child(RealtimeDatabaseContract.COMPUTERDEPARTMENT)
                    .child(RealtimeDatabaseContract.SEMESTER6)
                    .child(RealtimeDatabaseContract.DIVA1)
                    .child(RealtimeDatabaseContract.C_BATCH)
                    .child(uid)
                    .child(RealtimeDatabaseContract.SHARINGDATA) ;

            return referenceToUsersSharingSection ;

        }else if ( App.appFor == App.TEACHER_CODE ) {

            DatabaseReference referenceToUsersSharingSection = databaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(RealtimeDatabaseContract.TEACHERS)
                    .child(RealtimeDatabaseContract.COMPUTERDEPARTMENT)
                    .child(uid)
                    .child(RealtimeDatabaseContract.SHARINGDATA) ;

            return referenceToUsersSharingSection ;

        }

        return null ; // if null then invalid
    }

    private DatabaseReference constructPathToUser( String uid ) {
        if ( App.appFor == App.STUDENT_CODE ) {

            //Todo: Take all credintails from the SqlLiteHelper class OK this is dummy

            DatabaseReference referenceToUser = databaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(RealtimeDatabaseContract.STUDENTS)
                    .child(RealtimeDatabaseContract.COMPUTERDEPARTMENT)
                    .child(RealtimeDatabaseContract.SEMESTER6)
                    .child(RealtimeDatabaseContract.DIVA1)
                    .child(RealtimeDatabaseContract.C_BATCH)
                    .child(uid);

            return referenceToUser ;

        }else if ( App.appFor == App.TEACHER_CODE ) {

            DatabaseReference referenceToUser = databaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(RealtimeDatabaseContract.TEACHERS)
                    .child(RealtimeDatabaseContract.COMPUTERDEPARTMENT)
                    .child(uid);

            return referenceToUser ;

        }

        return null ; // if null then invalid
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if  ( requestCode == RC_FILE_CHOOSE_REQUEST  && resultCode == RESULT_OK ) {

            Uri selectedFile = data.getData() ;

            fileNameTextView.setText( selectedFile.getLastPathSegment() );

        }
    }

    private void createPostData(String sendingRef) {

        sendingDepartment = null ;
        sendingSemester = null ;
        sendingDivision = null ;
        sendingBatch = null ;
        sendingReferenceInString = null ;
        sendingWhomToSend = null ;
        this.sendingRef = sendingRef ;

        if ( departmentSpinner.getSelectedItemPosition() == 1 ) {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_COLLEGE ;
            sendNow() ;
            return ;
        }else {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_DEPARTMENT ;
            switch (departmentSpinner.getSelectedItemPosition()) {
                case 2:
                    sendingDepartment = RealtimeDatabaseContract.COMPUTERDEPARTMENT ;
                    break ;
                case 3:
                    sendingDepartment = RealtimeDatabaseContract.ITDEPARTMENT ;
                    break ;
            }
        }
        // Now Semester
        if ( semesterSpinner.getSelectedItemPosition() == 1 ) {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_DEPARTMENT ;
            sendNow();
            return ;
        }else {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_SEMESTER ;
            switch (semesterSpinner.getSelectedItemPosition()) {
                case 2:
                    sendingSemester = RealtimeDatabaseContract.SEMESTER1 ;
                    break ;
                case 3:
                    sendingSemester = RealtimeDatabaseContract.SEMESTER2 ;
                    break ;
                case 4:
                    sendingSemester = RealtimeDatabaseContract.SEMESTER3 ;
                    break ;
                case 5:
                    sendingSemester = RealtimeDatabaseContract.SEMESTER4 ;
                    break ;
                case 6:
                    sendingSemester = RealtimeDatabaseContract.SEMESTER5 ;
                    break ;
                case 7:
                    sendingSemester = RealtimeDatabaseContract.SEMESTER6 ;
                    break ;
            }
        }

        if ( divisionSpinner.getSelectedItemPosition() == 1 ) {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_SEMESTER ;
            sendNow() ;
            return ;
        }else {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_DIVISION ;
            switch (divisionSpinner.getSelectedItemPosition()) {
                case 2:
                    sendingDivision = RealtimeDatabaseContract.DIVA1 ;
                    break ;
                case 3:
                    sendingDivision = RealtimeDatabaseContract.DIVA2 ;
                    break ;
                case 4:
                    sendingDivision = RealtimeDatabaseContract.DIVA3 ;
                    break ;
                case 5:
                    sendingDivision = RealtimeDatabaseContract.DIVB1 ;
                    break ;
                case 6:
                    sendingDivision = RealtimeDatabaseContract.DIVB2 ;
                    break ;
                case 7:
                    sendingDivision = RealtimeDatabaseContract.DIVB3 ;
                    break ;
                case 8:
                    sendingDivision = RealtimeDatabaseContract.DIVG ;
                    break ;
                case 9:
                    sendingDivision = RealtimeDatabaseContract.DIVH ;
                    break ;
                case 10:
                    sendingDivision = RealtimeDatabaseContract.DIVI ;
                    break ;
            }
        }

        // Now Batch
        if ( batchSpinner.getSelectedItemPosition() == 1 ) {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_DIVISION ;
            sendNow();
            return ;
        }else {
            sendingWhomToSend = CommunicationContract.WHOM_TO_SEND_BATCH ;
            switch (batchSpinner.getSelectedItemPosition()) {
                case 2:
                    sendingBatch = RealtimeDatabaseContract.A_BATCH ;
                    break ;
                case 3:
                    sendingBatch = RealtimeDatabaseContract.B_BATCH ;
                    break ;
                case 4:
                    sendingBatch = RealtimeDatabaseContract.C_BATCH ;
                    break ;
            }
        }

        ServerConnection serverConnection = new ServerConnection();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("whomToSend", sendingWhomToSend );
        params.put("whatToSend", sendingWhatToSend);
        params.put("department", sendingDepartment);
        params.put("semester", sendingSemester);
        params.put("division", sendingDivision);
        params.put("batch", sendingBatch ) ;
        params.put("ref", this.sendingRef) ;

        serverConnection.sendPostData(params);

        serverConnection.connectToPage(pageToConnect);

        serverConnection.go();

    }

    private void sendNow(  ) {

        if ( sendingWhomToSend.equals(CommunicationContract.WHOM_TO_SEND_COLLEGE ) ) {
            ServerConnection serverConnection = new ServerConnection() ;

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("whomToSend", sendingWhomToSend ) ;
            params.put("whatToSend", sendingWhatToSend ) ;
            params.put("ref", sendingRef ) ;

            serverConnection.sendPostData(params);

            serverConnection.connectToPage(pageToConnect) ;

            serverConnection.go() ;
        }else if ( sendingWhomToSend.equals(CommunicationContract.WHOM_TO_SEND_DEPARTMENT ) ) {
            ServerConnection serverConnection = new ServerConnection() ;

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("whomToSend", sendingWhomToSend  ) ;
            params.put("whatToSend", sendingWhatToSend ) ;
            params.put("department", sendingDepartment ) ;
            params.put("ref", sendingRef ) ;

            serverConnection.sendPostData(params);

            serverConnection.connectToPage(pageToConnect) ;

            serverConnection.go() ;
        }else if ( sendingWhomToSend.equals(CommunicationContract.WHOM_TO_SEND_SEMESTER ) ) {
            ServerConnection serverConnection = new ServerConnection() ;

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("whomToSend", sendingWhomToSend ) ;
            params.put("whatToSend", sendingWhatToSend ) ;
            params.put("department", sendingDepartment ) ;
            params.put("semester" , sendingSemester ) ;
            params.put("ref", sendingRef ) ;

            serverConnection.sendPostData(params);

            serverConnection.connectToPage(pageToConnect) ;

            serverConnection.go() ;
        }else if ( sendingWhomToSend.equals(CommunicationContract.WHOM_TO_SEND_DIVISION ) ) {
            ServerConnection serverConnection = new ServerConnection();

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("whomToSend", sendingWhomToSend );
            params.put("whatToSend", sendingWhatToSend );
            params.put("department", sendingDepartment);
            params.put("semester", sendingSemester);
            params.put("division", sendingDivision);
            params.put("ref", sendingRef);

            serverConnection.sendPostData(params);

            serverConnection.connectToPage(pageToConnect);

            serverConnection.go();
        }else if ( sendingWhomToSend.equals(CommunicationContract.WHOM_TO_SEND_BATCH ) ) {
            ServerConnection serverConnection = new ServerConnection();

            Map<String, Object> params = new LinkedHashMap<>();
            params.put("whomToSend", sendingWhomToSend );
            params.put("whatToSend", sendingWhatToSend );
            params.put("department", sendingDepartment);
            params.put("semester", sendingSemester);
            params.put("division", sendingDivision);
            params.put("batch", sendingBatch ) ;
            params.put("ref", sendingRef) ;

            serverConnection.sendPostData(params);

            serverConnection.connectToPage(pageToConnect);

            serverConnection.go();
        }

    }
}
