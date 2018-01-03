package com.thirdyear.project.cmateproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.thirdyear.project.cmateproject.server.DeviceRegistrationToken;
import com.thirdyear.project.cmateproject.server.ServerConnection;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;

import java.util.LinkedHashMap;
import java.util.Map;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

//public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
//
//    private static final String TAG = "SuryaaFirebase";
//    public  static final String TOKEN = "REG_TOKEN" ;
//
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//        if ( refreshedToken == null ) {
//            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
//        }
//
//        Toast.makeText(this, "" + refreshedToken , Toast.LENGTH_SHORT).show();
//
//        Toast.makeText( this , "" + refreshedToken , Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//        // Testing
//
//            Intent intent = new Intent(this,TestActivity.class) ;
//            intent.putExtra(TOKEN,refreshedToken) ;
//            startActivity(intent) ;
//
//        // End Testing
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
//    }
//
//    private void sendRegistrationToServer( String refreshedToken ) {
//
//    }
//
//}

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.

        DeviceRegistrationToken.sendTokenToServer(token,this);

    }
}
