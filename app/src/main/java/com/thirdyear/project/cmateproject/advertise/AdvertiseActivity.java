package com.thirdyear.project.cmateproject.advertise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thirdyear.project.cmateproject.R;

import java.util.ArrayList;
import java.util.List;


// To Do Delete those files from the root package if you do not wont them any more with their entries in Manifest file
public class AdvertiseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView allAdsListView ;
    public static List<Addvertise> addvertiseArrayList = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Addvertise addvertise = new Addvertise("TitleTitleTitleTitleTitleTitleTitleTitleTitleTitle",null,null,1,1,200,500,getString(R.string.large_text)) ;
        Addvertise addvertise1 = new Addvertise("VijayVijayVijayVijayVijayVijayVijayVijayVijayVijay",null,null,1,1,200,500,getString(R.string.large_text)) ;
        Addvertise addvertise2 = new Addvertise("SuryaaSuryaaSuryaaSuryaaSuryaaSuryaaSuryaaSuryaaSuryaa",null,null,1,1,200,500,getString(R.string.large_text)) ;

        addvertiseArrayList = new ArrayList<Addvertise>() ;
        addvertiseArrayList.add(addvertise) ;
        addvertiseArrayList.add(addvertise1) ;
        addvertiseArrayList.add(addvertise2) ;

        AddvertiseAdapter addveriseAdapter = new AddvertiseAdapter(this,0,addvertiseArrayList ) ;

        allAdsListView = ((ListView) findViewById(R.id.allAdsListView)) ;

        allAdsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent( AdvertiseActivity.this , AdDetailsActivity.class ) ;
                intent.putExtra( AdDetailsActivity.POSITION , i ) ;
                startActivity(intent);
            }
        });

        allAdsListView.setAdapter(addveriseAdapter) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( AdvertiseActivity.this , AddEntryDetailsActivity.class ) ;
                startActivity(intent) ;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(this,AddEntryDetailsActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
