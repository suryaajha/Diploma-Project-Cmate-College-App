package com.thirdyear.project.cmateproject.utils;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Suryaa Jha on 12-Mar-17.
 */

public class DatabaseUtils {

    public static String createReferenceString(DatabaseReference ref ) {

        StringBuilder referenceInString = new StringBuilder () ;

        int count = 0 ;

        ArrayList<String> arrayList = new ArrayList<>() ;
        String name ;

        while ( (name = ref.getKey()) !=null ) {
            arrayList.add(name) ;
            ref = ref.getParent() ;
        }

        Collections.reverse(arrayList) ;

        for (String anArrayList : arrayList) {
            referenceInString.append(anArrayList);
            referenceInString.append("/") ;
        }

        referenceInString.deleteCharAt(referenceInString.length()-1) ;

        return referenceInString.toString() ;

    }

}
