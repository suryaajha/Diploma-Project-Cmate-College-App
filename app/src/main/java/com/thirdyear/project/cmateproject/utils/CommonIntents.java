package com.thirdyear.project.cmateproject.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Suryaa Jha on 11-Mar-17.
 */

public class CommonIntents {

    public static void startMessagingActivity(String phoneNumber , String subject  , Context context ){
        Intent intent = new Intent(Intent.ACTION_SENDTO) ;
        Uri sendToUri = Uri.parse("smsto:" + phoneNumber ) ;
        intent.setType("text/plain") ;
        intent.setData(sendToUri) ;
        intent.putExtra("subject",subject) ;

        if ( intent.resolveActivity(context.getPackageManager()) != null ) {
            context.startActivity(intent);
        }
    }

    public static void startMailingActivity(String[] addresses , String subject , Context context ) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void startCallingActivity(String phoneNumber , Context context ) {

        Intent intent = new Intent(Intent.ACTION_DIAL) ;
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if ( intent.resolveActivity(context.getPackageManager()) != null ) {
            context.startActivity(intent) ;
        }

    }

}
