package com.thirdyear.project.cmateproject.sing_up_in;

import android.content.Intent;
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
import com.thirdyear.project.cmateproject.R;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth ;

    private Button signUpButton  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance() ;

        signUpButton = (Button)findViewById(R.id.sign_in_button_id) ;

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo: sign up validaton
                // 1st check whether this user exist in database with the email provided email address which is uid
                // Then if user data is there than create the Firebase User
                // otherwise dont allow the user to create the account
                // Inform the user to provide valid email address

                String userName = ((EditText)findViewById(R.id.username_entry_field_id)).getText().toString() ;
                String password = ((EditText)findViewById(R.id.password_entry_field_id)).getText().toString() ;

                Task<AuthResult> signUpTask = firebaseAuth.createUserWithEmailAndPassword( userName , password ) ;

                signUpTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( task.isSuccessful() ) {
                            Toast.makeText(SignUpActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) ; 

            }
        });
    }

}
