package com.thirdyear.project.cmateproject.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.utils.DatabaseUtils;

public class Signin_teach_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_teach_1);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(RealtimeDatabaseContract.USERS).child(RealtimeDatabaseContract.STUDENTS).child(RealtimeDatabaseContract.COMPUTERDEPARTMENT)
                .child(RealtimeDatabaseContract.SEMESTER6)
                .child(RealtimeDatabaseContract.DIVA1)
                .child(RealtimeDatabaseContract.A_BATCH) ;

        String ref = DatabaseUtils.createReferenceString(databaseReference) ;

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child(ref);

        databaseReference1.push().setValue("GoodMoringIndia") ;

        Toast.makeText(this, "" + ref , Toast.LENGTH_SHORT).show();

        Log.i("Reference" , ref ) ;
        Button nextbutton=(Button)findViewById(R.id.nextbutton);

        nextbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(), Signin_teach_2.class);

                startActivity(i1);
            }
        });



    }
}
