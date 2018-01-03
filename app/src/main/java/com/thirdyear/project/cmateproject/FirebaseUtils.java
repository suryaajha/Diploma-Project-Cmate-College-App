package com.thirdyear.project.cmateproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Suryaa Jha on 02-Mar-17.
 */

public class FirebaseUtils {

    public static final FirebaseDatabase FIREBASE_DATABASE = FirebaseDatabase.getInstance() ;
    public static final DatabaseReference ROOT_DATABASE_REFERENCE = FIREBASE_DATABASE.getReference() ;

}
