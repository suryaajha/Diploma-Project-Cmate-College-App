package com.thirdyear.project.cmateproject.server;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;

/**
 * Created by Suryaa Jha on 12-Mar-17.
 */

public class CreateFirebaseDatabaseAllPaths {


    public static void createSemPathForStudents(String semester,String department){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        databaseReference
                .child(RealtimeDatabaseContract.USERS)
                .child(RealtimeDatabaseContract.STUDENTS)
                .child(department)
                .child(semester)
                .child(RealtimeDatabaseContract.ALL_DIVISONS)
                    .push()
                    .setValue("Suryaa") ;
    }

    public static void createDepartmentPathForStudents(String department ){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        databaseReference
                .child(RealtimeDatabaseContract.USERS)
                .child(RealtimeDatabaseContract.STUDENTS)
                .child(department)
                .child(RealtimeDatabaseContract.ALL_SEMESTERS)
                .push()
                .setValue("Suryaa") ;
    }

    public static void createDepartmentPathForTeacherAndAdmins(String department , String adminOrTeacher){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        databaseReference
                .child(RealtimeDatabaseContract.USERS)
                .child(adminOrTeacher)
                .child(department)
                .child(RealtimeDatabaseContract.ALL_UIDS)
                .push()
                .setValue("Suryaa") ;
    }

    public static void createTopLevelUserPath(String user) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        databaseReference
                .child(RealtimeDatabaseContract.USERS)
                .child(user)
                .child(RealtimeDatabaseContract.ALL_DEPARTMENTS)
                .push()
                .setValue("Suryaa") ;
    }

    public static void createDivsionPathForStudents(String division,String department,String semester){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        databaseReference
                .child(RealtimeDatabaseContract.USERS)
                .child(RealtimeDatabaseContract.STUDENTS)
                .child(department)
                .child(semester)
                .child(division)
                .child(RealtimeDatabaseContract.ALL_BATCHES)
                .push()
                .setValue("Suryaa") ;
    }

    public static void createBatchPathForStudents(String batch , String division,String department,String semester){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance() ;
        DatabaseReference databaseReference = firebaseDatabase.getReference() ;

        databaseReference
                .child(RealtimeDatabaseContract.USERS)
                .child(RealtimeDatabaseContract.STUDENTS)
                .child(department)
                .child(semester)
                .child(division)
                .child(batch)
                .child(RealtimeDatabaseContract.ALL_UIDS)
                .push()
                .setValue("Suryaa")  ;
    }

}
