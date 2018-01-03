package com.thirdyear.project.cmateproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thirdyear.project.cmateproject.advertise.Addvertise;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.main_details.DataAndType;
import com.thirdyear.project.cmateproject.notification.CMateNotification;
import com.thirdyear.project.cmateproject.planout.Planout;
import com.thirdyear.project.cmateproject.sharing.CMateSharing;
import com.thirdyear.project.cmateproject.sqlite.UIDSync;
import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

//// TODO: 17-Mar-17 Not satisfied - create a node fuction to find the total counter and use total counter to call the fileSave method 

public class TestActivity3 extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;

    private ArrayList<DataAndType> dataAndTypeArrayList = new ArrayList<>();

    private long targetAddCount = -1;
    private long targetSharingCount = -1 ;
    private long targetPlanoutCount = -1 ;
    private long targetNotificationCount = -1 ;

    private long total =  0 ;

    private long progressingAddCount  = 0 ;
    private long progressingSharingCount = 0 ;
    private long progressingPlanoutCount = 0 ;
    private long progressingNotificationCount =0  ;

    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        //saveMessagesToLocalFile();

        Toast.makeText(this, ""  + getTotalCount() , Toast.LENGTH_SHORT).show();

        mImageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        if (intent != null) {
            ((EditText) findViewById(R.id.editTextAct3)).setText(intent.getStringExtra("Data"));
        }

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayArrayListTemp();
            }
        });

        return;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                mImageView.setImageBitmap(mImageBitmap);
                Toast.makeText(this, "" + Uri.parse(mCurrentPhotoPath), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void displayArrayListTemp() {

        Collections.sort(dataAndTypeArrayList);

        // Todo: Extract UID of poster
        saveToFile(dataAndTypeArrayList,"SxH5dZRVqISi78LePvJ6F9DoUJa2");

        ArrayList<DataAndType> returnedArrayList = new ArrayList<>() ;

        returnedArrayList = readFromFile("SxH5dZRVqISi78LePvJ6F9DoUJa2") ;

        for (DataAndType dataAndType : returnedArrayList) {
            switch (dataAndType.getType()) {
                case WriteObjectToFile.ADDVERTISE_OBJECT:
                    Addvertise addvertise = ((Addvertise) dataAndType.getData());
                    Toast.makeText(this, "" + addvertise.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case WriteObjectToFile.NOTIFICATION_OBJECT:
                    CMateNotification cMateNotification = ((CMateNotification) dataAndType.getData());
                    Toast.makeText(this, "" + cMateNotification.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case WriteObjectToFile.PLANOUT_OBJECT:
                    break;
                case WriteObjectToFile.SHARING_OBJECT:
                    break;
            }


        }
    }

    private long getTotalCount() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        final DatabaseReference databaseReferenceToSendingPendingOfCurrentUser = databaseReference.child(App.PATH_TO_UID).child(RealtimeDatabaseContract.SENDING_PENDING);


        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_ADVERTISE).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total += dataSnapshot.getValue(Long.class)  ;
                databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_NOTIFICATIONS).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        total += dataSnapshot.getValue(Long.class) ;
                        /*databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_SHARING).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                total += dataSnapshot.getValue(Long.class) ;
                                databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_PLANOUT).child("count").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        total += dataSnapshot.getValue(Long.class) ;

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                return;
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        return;*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                return;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return  total  ;

    }


    private void saveMessagesToLocalFile() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();


        final DatabaseReference databaseReferenceToSendingPendingOfCurrentUser = databaseReference.child(App.PATH_TO_UID).child(RealtimeDatabaseContract.SENDING_PENDING);

        dataAndTypeArrayList = new ArrayList<>() ;


        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_ADVERTISE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String reference = null ;
                if ( dataSnapshot.getKey().equals("count") ) {
                    Toast.makeText(TestActivity3.this, "Count Fired", Toast.LENGTH_SHORT).show();
                    targetAddCount = dataSnapshot.getValue(Long.class)  ;
                    return;
                }else {
                    reference = dataSnapshot.getValue(String.class) ;

                }
                if ( targetAddCount == progressingAddCount ) {
                    // remove listener and return
                    Toast.makeText(TestActivity3.this, "Removed", Toast.LENGTH_SHORT).show();
                    databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_ADVERTISE).removeEventListener(this) ;
                    return;

                }
                final String key = dataSnapshot.getKey() ;
                DatabaseReference databaseReferenceToAddvertise = databaseReference.child(reference);
                databaseReferenceToAddvertise.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Addvertise addvertise = dataSnapshot.getValue(Addvertise.class);
                        Toast.makeText(TestActivity3.this, "Key " + key + " Value " +addvertise.getTitle() , Toast.LENGTH_SHORT).show();
                        DataAndType dataAndType = new DataAndType(addvertise, WriteObjectToFile.ADDVERTISE_OBJECT);
                        dataAndTypeArrayList.add(dataAndType) ;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                progressingAddCount++ ;

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_NOTIFICATIONS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String reference = null ;
                if ( dataSnapshot.getKey().equals("count") ) {
                    Toast.makeText(TestActivity3.this, "Count Fired", Toast.LENGTH_SHORT).show();
                    targetNotificationCount = dataSnapshot.getValue(Long.class)  ;
                    return;
                }else {
                    reference = dataSnapshot.getValue(String.class) ;

                }
                if ( targetNotificationCount == progressingNotificationCount ) {
                    // remove listener and return
                    Toast.makeText(TestActivity3.this, "Removed", Toast.LENGTH_SHORT).show();
                    databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_NOTIFICATIONS).removeEventListener(this) ;
                    return;

                }
                final String key = dataSnapshot.getKey() ;
                DatabaseReference databaseReferenceToNotifications = databaseReference.child(reference);
                databaseReferenceToNotifications.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        CMateNotification cMateNotification = dataSnapshot.getValue(CMateNotification.class);
                        Toast.makeText(TestActivity3.this, "Key " + key + " Value " + cMateNotification.getMessage() , Toast.LENGTH_SHORT).show();
                        DataAndType dataAndType = new DataAndType(cMateNotification, WriteObjectToFile.NOTIFICATION_OBJECT);
                        dataAndTypeArrayList.add(dataAndType) ;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                progressingNotificationCount++ ;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_PLANOUT).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String reference = null ;
                if ( dataSnapshot.getKey().equals("count") ) {
                    Toast.makeText(TestActivity3.this, "Count Fired", Toast.LENGTH_SHORT).show();
                    targetPlanoutCount = dataSnapshot.getValue(Long.class)  ;
                    return;
                }else {
                    reference = dataSnapshot.getValue(String.class) ;

                }
                if ( targetPlanoutCount == progressingPlanoutCount ) {
                    // remove listener and return
                    Toast.makeText(TestActivity3.this, "Removed", Toast.LENGTH_SHORT).show();
                    databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_PLANOUT).removeEventListener(this) ;
                    return;

                }
                final String key = dataSnapshot.getKey() ;
                DatabaseReference databaseReferenceToPlanout = databaseReference.child(reference);
                databaseReferenceToPlanout.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Planout planout = dataSnapshot.getValue(Planout.class);
                        Toast.makeText(TestActivity3.this, "Key " + key + " Value "  + planout.getBatch() , Toast.LENGTH_SHORT).show();
                        DataAndType dataAndType = new DataAndType(planout, WriteObjectToFile.ADDVERTISE_OBJECT);
                        dataAndTypeArrayList.add(dataAndType) ;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                progressingPlanoutCount++ ;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_SHARING).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String reference = null ;
                if ( dataSnapshot.getKey().equals("count") ) {
                    Toast.makeText(TestActivity3.this, "Count Fired", Toast.LENGTH_SHORT).show();
                    targetSharingCount = dataSnapshot.getValue(Long.class)  ;
                    return;
                }else {
                    reference = dataSnapshot.getValue(String.class) ;

                }
                if ( targetSharingCount == progressingSharingCount ) {
                    // remove listener and return
                    Toast.makeText(TestActivity3.this, "Removed Sharing", Toast.LENGTH_SHORT).show();
                    databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_SHARING).removeEventListener(this) ;
                    return;

                }
                final String key = dataSnapshot.getKey() ;
                DatabaseReference databaseReferenceToSharing = databaseReference.child(reference);
                databaseReferenceToSharing.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        CMateSharing cMateSharing = dataSnapshot.getValue(CMateSharing.class);
                        Toast.makeText(TestActivity3.this, "Key " + key + " Value "  + cMateSharing.getBatch() , Toast.LENGTH_SHORT).show();
                        DataAndType dataAndType = new DataAndType(cMateSharing, WriteObjectToFile.SHARING_OBJECT);
                        dataAndTypeArrayList.add(dataAndType) ;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                progressingSharingCount++ ;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void saveToFile(ArrayList<DataAndType> dataAndTypeArrayList , String uid ) {


        File dataFile = new File(getFilesDir(), uid + WriteObjectToFile.dataSuffix  );
        Toast.makeText(this, "" + dataFile.getAbsolutePath() , Toast.LENGTH_SHORT).show();
        FileOutputStream fileOutputStreamToDataFile = null;
        ObjectOutputStream objectOutputStreamToDataFile = null;

        try {
            fileOutputStreamToDataFile = new FileOutputStream(dataFile);
            objectOutputStreamToDataFile = new ObjectOutputStream(fileOutputStreamToDataFile) ;
            for (DataAndType dataAndType : dataAndTypeArrayList) {

                objectOutputStreamToDataFile.writeObject(dataAndType);

            }

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File Not Found Exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        Toast.makeText(this, "Done Saving File", Toast.LENGTH_SHORT).show();

    }

    private ArrayList<DataAndType> readFromFile( String uid ) {

        File dataFile = new File( getFilesDir() , uid + WriteObjectToFile.dataSuffix ) ;
        FileInputStream fileInputStreamFromDataFile = null ;
        ObjectInputStream objectInputStreamFromDataFile = null ;

        ArrayList<DataAndType> returningArrayList = new ArrayList<>() ;

        try {
            fileInputStreamFromDataFile = new FileInputStream(dataFile);
            objectInputStreamFromDataFile = new ObjectInputStream(fileInputStreamFromDataFile);

            DataAndType dataAndType = null ;
            try {
                /*for ( int k = 0 ; k < 2 ; k++ ) {
                    dataAndType = ((DataAndType) objectInputStreamFromDataFile.readObject()) ;
                    Toast.makeText(this, "Read ", Toast.LENGTH_SHORT).show();
                    returningArrayList.add(dataAndType) ;
                }*/
                while ( (dataAndType = ((DataAndType) objectInputStreamFromDataFile.readObject())) != null ) {
                    returningArrayList.add(dataAndType) ;
                }
            } catch (ClassNotFoundException e) {
                Toast.makeText(this, "File Nt Fon", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returningArrayList ;
    }
}
