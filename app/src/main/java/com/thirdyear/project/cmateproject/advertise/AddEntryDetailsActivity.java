package com.thirdyear.project.cmateproject.advertise;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thirdyear.project.cmateproject.CountersForUserData;
import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.CommunicationContract;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.server.ServerConnection;
import com.thirdyear.project.cmateproject.utils.DatabaseUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// Waring make changes very soon cause it is dependent on UID which is copied pasted
public class AddEntryDetailsActivity extends AppCompatActivity {

    private Button postNewAddButton = null ;
    private FirebaseDatabase firebaseDatabase = null ;
    private DatabaseReference databaseReference = null ;
    private DatabaseReference referenceToUser = null ;
    private DatabaseReference referenceToUsersAddSection = null ;

    // GUI Fields

    private EditText titleEditText = null ;
    private EditText priceEditText = null ;
    private EditText actualPriceEditText = null ;
    private EditText detailsEditText = null ;

    private Spinner departmentSpinner = null ;
    private Spinner semesterSpinner = null ;
    private Spinner divisionSpinner = null ;
    private Spinner batchSpinner = null ;

    private ImageView addImageView = null ;

    // End Gui Fields

    private String uid = "7Rd0KSRzFwV5P4qgznUCcWTHSKw1" ;
    private String newKey = null ;
    private int    initialAddvertiseCount = 0 ;
    private int    finalAddvertiseCount = 0 ;

    private Addvertise addvertise = null ;

    // Request

    static final int REQUEST_PICK_IMAGE  = 2 ;
    private Uri selectedImage = null ;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mCurrentPhotoPath;
    private ImageView mImageView ;

    // Post Data
    private String sendingDepartment = null ;
    private String sendingSemester = null ;
    private String sendingDivision = null ;
    private String sendingBatch = null ;
    private String sendingReferenceInString = null ;
    private String sendingWhatToSend  = CommunicationContract.WHAT_TO_SEND_ADDVERTISE; // Addvertise
    private String sendingWhomToSend = null ;
    private String sendingRef = "ref" ;

    private String pageToConnect = "update_sending_pending" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Firebase

        firebaseDatabase = FirebaseDatabase.getInstance() ;
        databaseReference = firebaseDatabase.getReference() ;

        // End Firebase
        // For GUI Enteries
        initEntries();
        mImageView = addImageView ;
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

        // Handle Photo Related Buttons

        ((ImageButton) findViewById(R.id.galleryInput)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE );

            }
        });

        ((ImageButton) findViewById(R.id.cameraInput)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

        //Todo: Remove button should be given a app image
        ((ImageButton) findViewById(R.id.removePhotoInput)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageView.setImageResource(R.mipmap.ic_launcher) ;
            }
        });

        // End Handle Photo Related Buttons

        postNewAddButton = ((Button) findViewById(R.id.postNewAddButtonId)) ;
        postNewAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View renderView = view ;

                boolean everythingOk = validateData() ;

                if ( everythingOk ) {

                    addvertise = constructAdd() ;

                    referenceToUser = constructPathToUser( uid ) ;
                    referenceToUser
                            .child(RealtimeDatabaseContract.COUNTERS)
                            .addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    CountersForUserData counter = dataSnapshot.getValue(CountersForUserData.class) ;
                                    Toast.makeText(AddEntryDetailsActivity.this, "" + counter.getAddvertise() , Toast.LENGTH_SHORT).show();

                                    initialAddvertiseCount = counter.getAddvertise() ;
                                    finalAddvertiseCount = initialAddvertiseCount+1 ;

                                    newKey = RealtimeDatabaseContract.COUNTER_NEW_ADDVERISE + finalAddvertiseCount ;

                                    // Create a New Add With New Key
                                    referenceToUsersAddSection = constructPath( uid ) ;
                                    if ( referenceToUsersAddSection != null ) {
                                        addvertise.setDbPathToSelfReference(DatabaseUtils.createReferenceString(referenceToUsersAddSection.child(newKey)));
                                        Task addvertiseUploadTask = referenceToUsersAddSection.child(newKey).setValue(addvertise) ;

                                        addvertiseUploadTask.addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {
                                                // Now Update the Counter i.e Increment it

                                                Map<String,Object> map = new HashMap<String, Object>() ;
                                                map.put(RealtimeDatabaseContract.COUNTERS_ADDVERTISE,finalAddvertiseCount) ;

                                                referenceToUser
                                                        .child(RealtimeDatabaseContract.COUNTERS)
                                                        .child(RealtimeDatabaseContract.COUNTER_NEW_NAME)
                                                        .updateChildren(map) ;

                                                // End
                                                // Now to sending pending work so that other users can recieve notifications

                                                createPostData(addvertise.getDbPathToSelfReference()) ;

                                                // End Now to sending pending work so that other users can recieve notifications

                                                Snackbar.make(renderView, "Uploaded Addvertisement to Server", Snackbar.LENGTH_LONG)
                                                        .setAction("Action", null).show();
                                            }
                                        }) ;

                                        addvertiseUploadTask.addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(renderView, "There is some problem in Uploading Addvertisemtnt", Snackbar.LENGTH_LONG)
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void initEntries() {
        titleEditText = ((EditText) findViewById(R.id.addTitleEditText));
        priceEditText = ((EditText) findViewById(R.id.priceEditTextId));
        actualPriceEditText = ((EditText) findViewById(R.id.actualPriceEditTextId));
        detailsEditText = ((EditText) findViewById(R.id.detailsEditTextId));

        semesterSpinner = ((Spinner) findViewById(R.id.audienceSpinnerId));
        departmentSpinner = ((Spinner) findViewById(R.id.departmentSpinnerId));
        divisionSpinner = ((Spinner) findViewById(R.id.divisionSpinnerId));
        batchSpinner = ((Spinner) findViewById(R.id.batchSpinnerId));

        addImageView = ((ImageView) findViewById(R.id.addImageViewId)) ;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        titleEditText = null ;
        priceEditText = null ;
        actualPriceEditText = null ;
        detailsEditText = null ;

        semesterSpinner = null ;
        departmentSpinner = null ;
        batchSpinner = null ;
        divisionSpinner = null ;

        addImageView = null ;
    }

    private boolean validateData() {

        if ((titleEditText.getText().toString().length() > 0) && (priceEditText.getText().toString().length() > 0) && (actualPriceEditText.getText().toString().length() > 0) && (detailsEditText.getText().toString().length() > 0) && (semesterSpinner.getSelectedItemPosition() != 0) && (departmentSpinner.getSelectedItemPosition() != 0) && (divisionSpinner.getSelectedItemPosition() != 0) && (batchSpinner.getSelectedItemPosition() != 0) ) {
            if ( ((departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.GONE ) && (departmentSpinner.getVisibility() == View.GONE) && (batchSpinner.getVisibility() == View.GONE) ) || (departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.VISIBLE ) && (departmentSpinner.getVisibility() == View.GONE) && (batchSpinner.getVisibility() == View.GONE) || (departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.VISIBLE ) && (departmentSpinner.getVisibility() == View.VISIBLE) && (batchSpinner.getVisibility() == View.GONE) || (departmentSpinner.getVisibility() == View.VISIBLE) && (semesterSpinner.getVisibility() == View.VISIBLE ) && (departmentSpinner.getVisibility() == View.VISIBLE) && (batchSpinner.getVisibility() == View.VISIBLE) )
                return true ;
        } else {
            return false;
        }
        return false ;
    }

    private Addvertise constructAdd() {

        Addvertise addvertise = new Addvertise(titleEditText.getText().toString(),null,null, semesterSpinner.getSelectedItemPosition(),departmentSpinner.getSelectedItemPosition(),Integer.parseInt(priceEditText.getText().toString()),Integer.parseInt(actualPriceEditText.getText().toString()),detailsEditText.getText().toString()) ;

        addvertise.setPosterUid(uid) ;

        addvertise.setPostingTimeInMillisecond(System.currentTimeMillis()) ;

        return addvertise ;
    }

    private DatabaseReference constructPath( String uid ) {
        if ( App.appFor == App.STUDENT_CODE ) {

            //Todo: Take all credintails from the SqlLiteHelper class OK this is dummy

            DatabaseReference referenceToUsersAddSection = databaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(RealtimeDatabaseContract.STUDENTS)
                    .child(RealtimeDatabaseContract.COMPUTERDEPARTMENT)
                    .child(RealtimeDatabaseContract.SEMESTER6)
                    .child(RealtimeDatabaseContract.DIVA1)
                    .child(RealtimeDatabaseContract.C_BATCH)
                    .child(uid)
                    .child(RealtimeDatabaseContract.ADVERTISEDATA) ;

            return referenceToUsersAddSection ;

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

        }

        return null ; // if null then invalid
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK ) {
            selectedImage = data.getData() ;
            Glide.with(addImageView.getContext()) // display the image in the image view
                    .load(selectedImage)
                    .into(addImageView) ;
        }else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            selectedImage = Uri.parse(mCurrentPhotoPath) ;
            Glide.with(addImageView.getContext()) // display the image in the image view
                    .load(selectedImage)
                    .into(addImageView) ;
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );


        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
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
