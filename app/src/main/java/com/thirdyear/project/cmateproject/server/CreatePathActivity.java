package com.thirdyear.project.cmateproject.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thirdyear.project.cmateproject.R ;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;

public class CreatePathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_path) ;

        ((Button) findViewById(R.id.createPathGoButtonId)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateFirebaseDatabaseAllPaths.createTopLevelUserPath(RealtimeDatabaseContract.STUDENTS);
                CreateFirebaseDatabaseAllPaths.createTopLevelUserPath(RealtimeDatabaseContract.TEACHERS);
                CreateFirebaseDatabaseAllPaths.createTopLevelUserPath(RealtimeDatabaseContract.ADMINS);

                CreateFirebaseDatabaseAllPaths.createDepartmentPathForStudents(RealtimeDatabaseContract.COMPUTERDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createDepartmentPathForStudents(RealtimeDatabaseContract.ITDEPARTMENT);

                CreateFirebaseDatabaseAllPaths.createDepartmentPathForTeacherAndAdmins(RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.TEACHERS);
                CreateFirebaseDatabaseAllPaths.createDepartmentPathForTeacherAndAdmins(RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.TEACHERS);

                CreateFirebaseDatabaseAllPaths.createDepartmentPathForTeacherAndAdmins(RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.ADMINS) ;
                CreateFirebaseDatabaseAllPaths.createDepartmentPathForTeacherAndAdmins(RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.ADMINS) ;

                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER1,RealtimeDatabaseContract.COMPUTERDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER2,RealtimeDatabaseContract.COMPUTERDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER3,RealtimeDatabaseContract.COMPUTERDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER4,RealtimeDatabaseContract.COMPUTERDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER5,RealtimeDatabaseContract.COMPUTERDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER6,RealtimeDatabaseContract.COMPUTERDEPARTMENT);

                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER1,RealtimeDatabaseContract.ITDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER2,RealtimeDatabaseContract.ITDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER3,RealtimeDatabaseContract.ITDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER4,RealtimeDatabaseContract.ITDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER5,RealtimeDatabaseContract.ITDEPARTMENT);
                CreateFirebaseDatabaseAllPaths.createSemPathForStudents(RealtimeDatabaseContract.SEMESTER6,RealtimeDatabaseContract.ITDEPARTMENT);

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER6) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER6) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER6) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5) ;

                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER6) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER6) ;
                CreateFirebaseDatabaseAllPaths.createDivsionPathForStudents(RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER6) ;


                // Creating Batches

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.COMPUTERDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER1);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVG,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVH,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVI,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER2);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER3);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVB3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER4);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVA1,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVA2,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);

                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.A_BATCH,RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.B_BATCH,RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);
                CreateFirebaseDatabaseAllPaths.createBatchPathForStudents(RealtimeDatabaseContract.C_BATCH,RealtimeDatabaseContract.DIVA3,RealtimeDatabaseContract.ITDEPARTMENT,RealtimeDatabaseContract.SEMESTER5);

            }
        });


    }
}
