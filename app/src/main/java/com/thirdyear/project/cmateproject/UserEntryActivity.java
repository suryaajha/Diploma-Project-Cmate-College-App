package com.thirdyear.project.cmateproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.server.ServerAppCompatActivity;
import com.thirdyear.project.cmateproject.server.ServerConnection;
import com.thirdyear.project.cmateproject.sing_up_in.SignInActivity;
import com.thirdyear.project.cmateproject.sqlite.CMateSQLiteDatabaseContract;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;
import com.thirdyear.project.cmateproject.utils.DatabaseUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// Warning: if some problem comes then go and remove obtainUID() method of UIDSync class

public class UserEntryActivity extends ServerAppCompatActivity {

    private FirebaseDatabase firebaseDatabase ;
    private FirebaseAuth firebaseAuth ;
    private DatabaseReference rootDatabaseReference ;
    private String uidOfFirebaseUser ;
    private UserDetails newUser = null ; 

    // Fields

    private EditText firstName ;
    private EditText lastName ;
    private EditText address ;
    private EditText emailAddress ;
    private EditText password ;
    private EditText retypePassword ; 
    private EditText mobileNo ;
    private EditText enrollmentNo ;
    private ImageView dpImageView ;
    private Spinner  department ;
    private Spinner  semester ;
    private Spinner    division ;
    private Spinner    batch ;
    private Uri      selectedImage = null ;

    // End Fields

    // Request

    static final int REQUEST_PICK_IMAGE  = 2 ;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init

        initEntries() ; // call up all ids to their references
        mImageView = dpImageView ;
        setUpDisplay() ;
        // end init

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

        ((ImageButton) findViewById(R.id.removePhotoInput)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = null  ;
                dpImageView.setImageResource(R.mipmap.ic_launcher);
            }
        });

        // Set up Firebase

            firebaseDatabase = FirebaseDatabase.getInstance() ;
            rootDatabaseReference = firebaseDatabase.getReference() ;
            firebaseAuth = FirebaseAuth.getInstance() ;

        // End

        // test

        findViewById(R.id.bottomDummyArea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserEntryActivity.this, SignInActivity.class));
            }
        });

        // end test

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean everythingOk = validateData();

                // check whether all fields are present otherwise generate some error message

                if ( everythingOk ) {

                    if ( isPasswordCorrect() ) {

                        newUser = constructUser() ;
                        Task<AuthResult> task  = firebaseAuth.createUserWithEmailAndPassword( newUser.getEmailAddress(), newUser.getPassword() ) ;

                        task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(UserEntryActivity.this, "Firebase User in Created", Toast.LENGTH_SHORT).show();
                                FirebaseUser firebaseUser = authResult.getUser() ;

                                // Now adding Dp to user acccount
                                if ( selectedImage != null ) {

                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(newUser.getFirstName())
                                            .setPhotoUri(selectedImage)
                                            .build();

                                    firebaseUser.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(UserEntryActivity.this, "Done adding Dp", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                                // End adding Dp to user acccount

                                uidOfFirebaseUser = firebaseUser.getUid() ;

                                DatabaseReference path = constructPath( uidOfFirebaseUser ) ;
                                final DatabaseReference pathToBatch = path.getParent().getParent() ; //Go to allUids path
                                final DatabaseReference pathToUid = path.getParent() ; // Go to UID
                                path.push().setValue( newUser )
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(UserEntryActivity.this, "Firebase Database has been modified", Toast.LENGTH_SHORT).show();

                                                // if the entry was successfully done than Now set the uid entry in allUids in the path
                                                pathToBatch.child(RealtimeDatabaseContract.ALL_UIDS).push().setValue(uidOfFirebaseUser) ;

                                                // update the UID sync so that after sign in user can fastly access their all information
                                                updateUidSyncInfo(pathToUid) ;

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UserEntryActivity.this, "Firebase Database has not been modified", Toast.LENGTH_SHORT).show();
                                            }
                                        }) ;

                                // Now creating counters JSON object in Firebase Realtime Database

                                CountersForUserData countersForUserData = new CountersForUserData(0,0,0,0);
                                path.getParent().child(RealtimeDatabaseContract.COUNTERS).child(RealtimeDatabaseContract.COUNTER_NEW_NAME).setValue( countersForUserData ) ;

                                // End Now creating counters JSON object in Firebase Realtime Database
                            }
                        }) ;
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserEntryActivity.this, "Firebase User is not created", Toast.LENGTH_SHORT).show();
                            }
                        }) ;
                        
                    }else {
                        Snackbar.make(view, "Make Sure you retyped your password correctly", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    Toast.makeText(UserEntryActivity.this, "Called", Toast.LENGTH_SHORT).show();

                }else {
                    Snackbar.make(view, "Make Sure you have entered all valid data", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void updateUidSyncInfo(DatabaseReference pathToUid) {


        int userCode = -1 ;
        String userDepartment = null  ;
        String userSemester = null  ;
        String userDivision = null  ;
        String userBatch = null  ;

        if ( App.appFor == App.STUDENT_CODE ) {
            userCode = App.STUDENT_CODE ;
        }else if ( App.appFor == App.TEACHER_CODE ) {
            userCode = App.TEACHER_CODE ;
        }else if ( App.appFor == App.TEACHER_CODE ) {
            userCode = App.ADMIN_CODE ;
        }

        switch (department.getSelectedItemPosition()) {
            case 1:
                userDepartment = RealtimeDatabaseContract.COMPUTERDEPARTMENT ;
                break;
            case 2:
                userDepartment = RealtimeDatabaseContract.ITDEPARTMENT ;
                break;
            default:
                userDepartment = null ;
                break;
        }
        switch (semester.getSelectedItemPosition()) {
            case 1:
                userSemester = RealtimeDatabaseContract.SEMESTER1 ;
                break;
            case 2:
                userSemester = RealtimeDatabaseContract.SEMESTER2 ;
                break;
            case 3:
                userSemester = RealtimeDatabaseContract.SEMESTER3 ;
                break;
            case 4:
                userSemester = RealtimeDatabaseContract.SEMESTER4 ;
                break;
            case 5:
                userSemester = RealtimeDatabaseContract.SEMESTER5 ;
                break;
            case 6:
                userSemester = RealtimeDatabaseContract.SEMESTER6 ;
                break;
            default:
                userSemester = null ;
                break;
        }
        switch (division.getSelectedItemPosition()) {
            case 1:
                userDivision = RealtimeDatabaseContract.DIVA1 ;
                break;
            case 2:
                userDivision = RealtimeDatabaseContract.DIVA2 ;
                break;
            case 3:
                userDivision = RealtimeDatabaseContract.DIVA3 ;
                break;
            case 4:
                userDivision = RealtimeDatabaseContract.DIVB1 ;
                break;
            case 5:
                userDivision = RealtimeDatabaseContract.DIVB2 ;
                break;
            case 6:
                userDivision = RealtimeDatabaseContract.DIVB3 ;
                break;
            case 7:
                userDivision = RealtimeDatabaseContract.DIVG ;
                break;
            case 8:
                userDivision = RealtimeDatabaseContract.DIVH ;
                break;
            case 9:
                userDivision = RealtimeDatabaseContract.DIVI ;
                break;
            default:
                userDivision = null ;
                break;
        }

        switch (batch.getSelectedItemPosition()) {
            case 1:
                userBatch = RealtimeDatabaseContract.A_BATCH ;
                break;
            case 2:
                userBatch = RealtimeDatabaseContract.B_BATCH ;
                break;
            case 3:
                userBatch = RealtimeDatabaseContract.C_BATCH ;
                break;
            default:
                userBatch = null ;
                break;
        }


        UIDSync uidSync = new UIDSync(DatabaseUtils.createReferenceString(pathToUid),userCode,userDepartment,userSemester,userDivision,userBatch) ;

        DatabaseReference uidSyncReference = FirebaseDatabase.getInstance().getReference().child(RealtimeDatabaseContract.UID_SYNC) ;

        uidSyncReference.child(uidOfFirebaseUser).setValue(uidSync) ; // User Value Event Listener for retrieving Please

    }

    private boolean validateData() {
        if ( App.appFor == App.STUDENT_CODE ) {
            if ( ( firstName.getText().toString().length() > 0 ) && ( lastName.getText().toString().length() > 0 ) && ( address.getText().toString().length() > 0 ) && ( emailAddress.getText().toString().length() > 0 ) && ( password.getText().toString().length() > 0 ) && ( mobileNo.getText().toString().length() > 0 ) && ( enrollmentNo.getText().toString().length() > 0 ) && ( department.getSelectedItemPosition() != 0) && ( semester.getSelectedItemPosition() != 0) && ( division.getSelectedItemPosition() != 0) && ( batch.getSelectedItemPosition() != 0) ){
                return true ;
            }else {
                return false ;
            }
        }else if ( App.appFor == App.TEACHER_CODE || App.appFor == App.ADMIN_CODE ) {
            if ( ( firstName.getText().toString().length() > 0 ) && ( lastName.getText().toString().length() > 0 ) && ( address.getText().toString().length() > 0 ) && ( emailAddress.getText().toString().length() > 0 ) && ( password.getText().toString().length() > 0 ) && ( mobileNo.getText().toString().length() > 0 ) && ( department.getSelectedItemPosition() != 0)  ){
                return true ;
            }else {
                return false ;
            }
        }
        return false ; // will be never called
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        firstName = null ;
        lastName = null ;
        address = null ;
        emailAddress = null ;
        mobileNo = null ;
        enrollmentNo = null ;
        department = null ;
        division = null ;
        batch = null ;
        password = null ;
        retypePassword = null ;
        semester = null ;
    }

    private void initEntries() {
        firstName = ((EditText) findViewById(R.id.firstNameEditText)) ;
        lastName = ((EditText) findViewById(R.id.lastNameEditText)) ;
        address = ((EditText) findViewById(R.id.addressEditText)) ;
        emailAddress = ((EditText) findViewById(R.id.emailEditText)) ;
        password  = ((EditText) findViewById(R.id.passwordEditText)) ;
        retypePassword = ((EditText) findViewById(R.id.retypePasswordEditText)) ;
        mobileNo = ((EditText) findViewById(R.id.mobileNoEditText)) ;
        enrollmentNo = ((EditText) findViewById(R.id.enrollmentNoEditText)) ;
        department = ((Spinner) findViewById(R.id.department_view_id)) ;
        semester = ((Spinner) findViewById(R.id.semester_view_id));
        division = ((Spinner) findViewById(R.id.division_view_id)) ;
        batch = ((Spinner) findViewById(R.id.batch_view_id)) ;
        dpImageView = ((ImageView) findViewById(R.id.dpPhotoImageView)) ;
    }

    private DatabaseReference constructPath( String uid ) {


        DatabaseReference databaseReference = null ;
        String userPath = null ;
        String departmentPath = null ;
        String batchPath = null ;
        String divisionPath = null ;
        String semesterPath = null ;
        String uidPath = uid ;

        if ( App.appFor == App.STUDENT_CODE ) {
            userPath = RealtimeDatabaseContract.STUDENTS ;
        }else if ( App.appFor == App.ADMIN_CODE ) {
            userPath = RealtimeDatabaseContract.ADMINS ;
        }else if ( App.appFor == App.TEACHER_CODE ) {
            userPath = RealtimeDatabaseContract.TEACHERS ;
        }

        int position ;

        position = department.getSelectedItemPosition() ;
        switch (position) {
            case 1:
                departmentPath = RealtimeDatabaseContract.COMPUTERDEPARTMENT ;
                break;
            case 2:
                departmentPath = RealtimeDatabaseContract.ITDEPARTMENT ;
                break;
        }
        // Only Students are going to be categoriezed by div and batches teachers are not so
        if ( App.appFor == App.STUDENT_CODE ) {

            position = semester.getSelectedItemPosition() ;
            switch (position) {
                case 1:
                    semesterPath = RealtimeDatabaseContract.SEMESTER1 ;
                    break;
                case 2:
                    semesterPath = RealtimeDatabaseContract.SEMESTER2 ;
                    break;
                case 3:
                    semesterPath = RealtimeDatabaseContract.SEMESTER3 ;
                    break;
                case 4:
                    semesterPath = RealtimeDatabaseContract.SEMESTER4 ;
                    break;
                case 5:
                    semesterPath = RealtimeDatabaseContract.SEMESTER5 ;
                    break;
                case 6:
                    semesterPath = RealtimeDatabaseContract.SEMESTER6 ;
                    break;

            }

            position = division.getSelectedItemPosition() ;
            switch (position) {
                case 1:
                    divisionPath = RealtimeDatabaseContract.DIVA1 ;
                    break;
                case 2:
                    divisionPath = RealtimeDatabaseContract.DIVA2 ;
                    break;
                case 3:
                    divisionPath = RealtimeDatabaseContract.DIVA3 ;
                    break;
                case 4:
                    divisionPath = RealtimeDatabaseContract.DIVB1 ;
                    break;
                case 5:
                    divisionPath = RealtimeDatabaseContract.DIVB2 ;
                    break;
                case 6:
                    divisionPath = RealtimeDatabaseContract.DIVB3 ;
                    break;
                case 7:
                    divisionPath = RealtimeDatabaseContract.DIVG ;
                    break;
                case 8:
                    divisionPath = RealtimeDatabaseContract.DIVH ;
                    break;
                case 9:
                    divisionPath = RealtimeDatabaseContract.DIVI ;
                    break;
            }

            position = batch.getSelectedItemPosition() ;
            switch (position) {
                case 1:
                    batchPath = RealtimeDatabaseContract.A_BATCH ;
                    break;
                case 2:
                    batchPath = RealtimeDatabaseContract.B_BATCH ;
                    break;
                case 3:
                    batchPath = RealtimeDatabaseContract.C_BATCH ;
                    break;
            }


            databaseReference = rootDatabaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(userPath)
                    .child(departmentPath)
                    .child(semesterPath)
                    .child(divisionPath)
                    .child(batchPath)
                    .child(uidPath)
                    .child(RealtimeDatabaseContract.PERSONALDATA) ;

            return databaseReference ;

        }else if( App.appFor == App.TEACHER_CODE || App.appFor == App.ADMIN_CODE ) {

            databaseReference = rootDatabaseReference
                    .child(RealtimeDatabaseContract.USERS)
                    .child(userPath)
                    .child(departmentPath)
                    .child(uidPath)
                    .child(RealtimeDatabaseContract.PERSONALDATA) ;

            return  databaseReference ;

        }
        return null ; // not going to be happend
    }

    private UserDetails constructUser() {

        if ( App.appFor == App.STUDENT_CODE ) {
            UserDetails userDetailsForStudent = new UserDetails (
                    firstName.getText().toString() ,
                    lastName.getText().toString() ,
                    emailAddress.getText().toString() ,
                    password.getText().toString() ,
                    address.getText().toString() ,
                    Long.parseLong(enrollmentNo.getText().toString()) ,
                    Long.parseLong(mobileNo.getText().toString())
            ) ;
            return userDetailsForStudent ;
        }else if ( App.appFor == App.TEACHER_CODE ) {
            UserDetails userDetailsForTeacher = new UserDetails (
                    firstName.getText().toString() ,
                    lastName.getText().toString() ,
                    emailAddress.getText().toString() ,
                    password.getText().toString() ,
                    address.getText().toString() ,
                    Long.parseLong(mobileNo.getText().toString())
            ) ;
            return userDetailsForTeacher ;
        }else if ( App.appFor == App.ADMIN_CODE ) {
            UserDetails userDetailsForAdmin = new UserDetails (
                    firstName.getText().toString() ,
                    lastName.getText().toString() ,
                    emailAddress.getText().toString() ,
                    password.getText().toString() ,
                    address.getText().toString() ,
                    Long.parseLong(mobileNo.getText().toString())
            ) ;
            return userDetailsForAdmin ;
        }
       return null ; // this will be never called
    }
    
    private boolean isPasswordCorrect() { 
        if  ( password.getText().toString().equals(retypePassword.getText().toString()) && password.getText().toString().length() >= 8 ) {
            return true ; 
        }else{
            return false ; 
        }
    }

    private void setUpDisplay() {

        if ( App.appFor == App.TEACHER_CODE || App.appFor == App.ADMIN_CODE ) {

            findViewById(R.id.enrollment_no_card_view).setVisibility(View.GONE ) ;
            findViewById(R.id.semester_card_view).setVisibility(View.GONE );
            findViewById(R.id.division_card_view).setVisibility(View.GONE );
            findViewById(R.id.batch_card_view).setVisibility(View.GONE );

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK ) {
            selectedImage = data.getData() ;
            Glide.with(dpImageView.getContext()) // display the image in the image view
                    .load(selectedImage)
                    .into(dpImageView) ;
        }else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            selectedImage = Uri.parse(mCurrentPhotoPath) ;
            Glide.with(dpImageView.getContext()) // display the image in the image view
                    .load(selectedImage)
                    .into(dpImageView) ;
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


}
