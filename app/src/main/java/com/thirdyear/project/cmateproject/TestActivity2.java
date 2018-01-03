package com.thirdyear.project.cmateproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.thirdyear.project.cmateproject.main_details.DataAndType;
import com.thirdyear.project.cmateproject.server.DeviceRegistrationToken;
import com.thirdyear.project.cmateproject.server.LikeDislikeAbuseHelper;
import com.thirdyear.project.cmateproject.server.OnResponseListener;
import com.thirdyear.project.cmateproject.server.ServerAppCompatActivity;
import com.thirdyear.project.cmateproject.server.ServerConnection;
import com.thirdyear.project.cmateproject.server.ServerUtils;
import com.thirdyear.project.cmateproject.sharing.CMateSharing;
import com.thirdyear.project.cmateproject.sqlite.CMateProjectSQLiteOpenHelper;
import com.thirdyear.project.cmateproject.sqlite.CMateSQLiteDatabaseContract;
import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;
import com.thirdyear.project.cmateproject.utils.WriteTemp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestActivity2 extends ServerAppCompatActivity implements OnResponseListener{

    private String response = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_test2) ;

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new Gson() ;
                CMateSharing cMateSharing = new CMateSharing() ;
                DataAndType dataAndType = new DataAndType(cMateSharing , WriteObjectToFile.SHARING_OBJECT ) ;
                cMateSharing.setBatch(0) ;
                cMateSharing.setPosterUid("fkldjfkjdjfkjdsjf") ;

                try {
                    FileWriter fileWriter = new FileWriter(new File(TestActivity2.this.getFilesDir(),"testjsonfile.json") ) ;

                    gson.toJson(dataAndType,DataAndType.class,fileWriter) ;

                    FileReader fileReader = new FileReader ( new File(TestActivity2.this.getFilesDir(),"testjsonfile.json") ) ;

                    BufferedReader br = new BufferedReader(fileReader) ;
                    String data ;
                    while ( (data = br.readLine()) != null ) {
                        System.out.println("Came");
                        System.out.println(data);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });




        /*DeviceRegistrationToken.sendTokenToServer(DeviceRegistrationToken.getDeviceToken(),"PSo21s0mIGfBOXvzznumxm7ym6h1") ;

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerConnection serverConnection = new ServerConnection(TestActivity2.this) ;

                Map<String,Object> params = new LinkedHashMap<>();
                params.put("uid", "Freddie the Fish");

                serverConnection.sendPostData(params);

                serverConnection.connectToPage("create_user") ;

                serverConnection.go() ;

            }
        });*/

    }

    public void onResponseRecieved(String response) {
        this.response = response ;
        Toast.makeText(TestActivity2.this, "" + response  , Toast.LENGTH_SHORT).show();
    }


}
