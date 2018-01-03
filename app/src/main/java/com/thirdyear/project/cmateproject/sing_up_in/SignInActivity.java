package com.thirdyear.project.cmateproject.sing_up_in;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thirdyear.project.cmateproject.LauncherActivity;
import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.advertise.AddEntryDetailsActivity;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.sqlite.CMateProjectSQLiteOpenHelper;
import com.thirdyear.project.cmateproject.sqlite.CMateSQLiteDatabaseContract;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;


public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth ;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener ;
    private Button signInButton ;
    private Button signUpButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up firebase Auth and AuthStateListener

            firebaseAuth = FirebaseAuth.getInstance() ;

            firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser() ;

                    if ( firebaseUser != null ) {
                        Toast.makeText(SignInActivity.this, "User is Signed in", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "User is signed out", Toast.LENGTH_SHORT).show();
                    }
                }
            } ;

        // End

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SignInActivity.this, "" + App.UID, Toast.LENGTH_SHORT).show();

                startActivity(new Intent(SignInActivity.this,LauncherActivity.class));

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        signInButton = (Button)findViewById(R.id.sign_in_button_id) ;

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = ((EditText)findViewById(R.id.username_entry_field_id)).getText().toString() ;
                String password = ((EditText)findViewById(R.id.password_entry_field_id)).getText().toString() ;

                final Task<AuthResult> signInTask = firebaseAuth.signInWithEmailAndPassword( userName , password ) ;

                signInTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( task.isSuccessful() ) {

                            String uidOfFirebaseUser = signInTask.getResult().getUser().getUid() ;

                            addUserToSignInTableSQLiteDatabase(uidOfFirebaseUser);
                            App.UID = uidOfFirebaseUser ;

                            Toast.makeText(SignInActivity.this, "Successfully Signed in", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignInActivity.this, "Unsuccessfull Signed in", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) ;

            }
        });

        signUpButton = (Button)findViewById(R.id.sign_up_button_id) ;

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent ( SignInActivity.this , AddEntryDetailsActivity.class ) ;
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthStateListener);
    }

    private void addUserToSignInTableSQLiteDatabase(final String uidOfFirebaseUser ) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        final DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        DatabaseReference uidSyncReference = databaseReference.child(RealtimeDatabaseContract.UID_SYNC).child(uidOfFirebaseUser) ;

        uidSyncReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UIDSync uidSync = dataSnapshot.getValue(UIDSync.class) ;
                if ( uidSync.getUserDepartment() != null ) {
                    Toast.makeText(SignInActivity.this, "" + uidSync.getUserBatch(), Toast.LENGTH_SHORT).show();
                    doDatabaseWork(uidSync, uidOfFirebaseUser);
                }else {
                    Toast.makeText(SignInActivity.this, "UID is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;
    }

    private void doDatabaseWork(UIDSync uidSync , String uidOfFirebaseUser ) {

        CMateProjectSQLiteOpenHelper cMateSQLiteOpenHelper = new CMateProjectSQLiteOpenHelper(this) ;

        SQLiteDatabase db = cMateSQLiteOpenHelper.getWritableDatabase() ;

        cMateSQLiteOpenHelper.deleteTable(CMateSQLiteDatabaseContract.SignInUserTable.TABLE_NAME,db) ;  //delete database before preceding cause we are storing all info of all users in cloud so dont worry

        ContentValues contentValues = new ContentValues() ;

        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.USER_ID,uidOfFirebaseUser) ;
        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.PATH_TO_UID,uidSync.getPathToUid()) ;
        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.USER_CODE,uidSync.getUserCode()) ;
        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.DEPARTMENT,uidSync.getUserDepartment()) ;
        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.SEMESTER,uidSync.getUserSemester()) ;
        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.DIVISION,uidSync.getUserDivision()) ;
        contentValues.put(CMateSQLiteDatabaseContract.SignInUserTable.BATCH,uidSync.getUserBatch()) ;

        long id = db.insert(CMateSQLiteDatabaseContract.SignInUserTable.TABLE_NAME,null,contentValues) ;
        Toast.makeText(this, "Inserted With Id" + id , Toast.LENGTH_SHORT).show() ;

    }

}
