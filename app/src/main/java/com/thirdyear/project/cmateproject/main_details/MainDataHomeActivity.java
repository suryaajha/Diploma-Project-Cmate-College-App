package com.thirdyear.project.cmateproject.main_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainDataHomeActivity extends AppCompatActivity {

    private ListView allUsersListView = null ;
    private ArrayList<String> allUsersArrayList = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar) ;

        allUsersListView = ((ListView) findViewById(R.id.allUsersListView)) ;

        allUsersArrayList = getAllUsers() ;

        MainDataHomeAdapter mainDataHomeAdapter = new MainDataHomeAdapter(getApplicationContext(),R.layout.notification_list_item, allUsersArrayList ) ;

        allUsersListView.setAdapter(mainDataHomeAdapter) ;

        allUsersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position , long l) {

                String uidToSend = allUsersArrayList.get(position) ;

                Intent intentToSend = new Intent(MainDataHomeActivity.this,MainDataDetailsActivity.class) ;
                intentToSend.putExtra(MainDataDetailsActivity.UID_TO_REPRESENT, uidToSend ) ;

                startActivity( intentToSend );
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private ArrayList<String> getAllUsers () {

        File rootDirectory = this.getFilesDir() ;
        String[] allFileNames = rootDirectory.list(new OnlyExtFilenameFilter(".dat") ) ;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(allFileNames)) ;
        ArrayList<String> returningArrayList = new ArrayList<>() ;
        for ( String uidFileName : arrayList ) {
            returningArrayList.add ( uidFileName.substring(0, uidFileName.indexOf(WriteObjectToFile.dataSuffix))) ;
        }
        return returningArrayList ;
    }

}
