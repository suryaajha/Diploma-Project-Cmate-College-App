package com.thirdyear.project.cmateproject.server;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Suryaa Jha on 09-Mar-17.
 */

//Todo: Once you create your first activity then send token to server at that time plzz

public class DeviceRegistrationToken {

    public static String getDeviceToken() {
        return FirebaseInstanceId.getInstance().getToken() ;
    }

    public static void sendTokenToServer(String token , Context context) {

        ServerConnection serverConnection = new ServerConnection() ;

        Map<String,Object> params = new LinkedHashMap<>();

        UIDSync uidSync = UIDSync.obtainUIDSync(context) ; // This method queries its SQLite database to obtain information about the currently signed in user
        // Since this activity opens after sign in so we can user database values
        params.put("pathToUid",uidSync.getPathToUid()) ;
        params.put("token",token) ;

        serverConnection.sendPostData(params);
        serverConnection.connectToPage("set_device_token") ;
        serverConnection.go() ;

    }

}
