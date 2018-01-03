package com.thirdyear.project.cmateproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainAuthDemoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth  ;
    private FirebaseAuth.AuthStateListener mAuthListener ;

    public static final String TAG = "TAG" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auth_demo);

        mAuth = FirebaseAuth.getInstance() ;
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainAuthDemoActivity.this, "User is Signed in Now", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Toast.makeText(MainAuthDemoActivity.this, "User is signed out Now", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        } ;

        Button button = ((Button) findViewById(R.id.singupbutton));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.username)).getText().toString() ;
                String password = ((EditText) findViewById(R.id.password)).getText().toString() ;

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if ( task.isSuccessful() ) {
                                    Toast.makeText(MainAuthDemoActivity.this, "Done Creating Account", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainAuthDemoActivity.this, "Failed Creating Account", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) ; 

            }
        });

        button = ((Button) findViewById(R.id.singinbutton));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.username)).getText().toString() ;
                String password = ((EditText) findViewById(R.id.password)).getText().toString() ;
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser() ;
                                    Toast.makeText(MainAuthDemoActivity.this, "Signed As " + user.getUid() , Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainAuthDemoActivity.this, "Please Make sure your username and password are correct", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) ;
            }
        });

        ((Button) findViewById(R.id.singoutbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser() ;
                if ( user != null ) {
                    mAuth.signOut() ;
                    Toast.makeText(MainAuthDemoActivity.this, "You are Signed Out Now", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainAuthDemoActivity.this, "Plzzz Addhi sign in kar mag sign out karnycha v4 kar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.removeAuthStateListener(mAuthListener) ;
    }
}
