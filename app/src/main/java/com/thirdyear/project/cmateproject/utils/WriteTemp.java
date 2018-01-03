package com.thirdyear.project.cmateproject.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thirdyear.project.cmateproject.TestActivity2;
import com.thirdyear.project.cmateproject.TestActivity3;
import com.thirdyear.project.cmateproject.advertise.Addvertise;
import com.thirdyear.project.cmateproject.contract.App;
import com.thirdyear.project.cmateproject.contract.RealtimeDatabaseContract;
import com.thirdyear.project.cmateproject.main_details.DataAndType;
import com.thirdyear.project.cmateproject.notification.CMateNotification;
import com.thirdyear.project.cmateproject.planout.Planout;
import com.thirdyear.project.cmateproject.sharing.CMateSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Suryaa Jha on 15-Mar-17.
 */

/*
    Example:
    UID passed is the other user UID
    WriteObjectToFile writeObjectToFile = new WriteObjectToFile("SxH5dZRVqISi78LePvJ6F9DoUJa2" , getApplicationContext()) ;

    //writeObjectToFile.deleteFile();

     writeObjectToFile.writeNewMessagesToFile(4,3,0,0);
 */

public class WriteTemp {

    public static final int ADDVERTISE_OBJECT = 1 ;
    public static final int NOTIFICATION_OBJECT = 2 ;
    public static final int SHARING_OBJECT = 3 ;
    public static final int PLANOUT_OBJECT = 4 ;

    public static final String dataSuffix =  "_data.dat" ;
    public static final String COUNT = "count" ;

    private int progressAddCount = 0 ;
    private int progressNotificationCount = 0  ;
    private int progressSharingCount = 0 ;
    private int progressPlanoutCount = 0 ;

    private boolean addDone = true  ;
    private boolean notificationDone  = true ;
    private boolean sharingDone = true  ;
    private boolean planoutDone  = true ;

    private Context context = null ;

    private DatabaseReference databaseReferenceToSendingPendingAddveriseOfCurrentUser = null ;
    private DatabaseReference databaseReferenceToSendingPendingNotificationOfCurrentUser = null ;
    private DatabaseReference databaseReferenceToSendingPendingSharingOfCurrentUser = null ;
    private DatabaseReference databaseReferenceToSendingPendingPlanoutOfCurrentUser = null ;

    private ArrayList<DataAndType> dataAndTypeArrayList = new ArrayList<>() ;
    private String uid  ;
    private String fileName ;

    private WriteTemp() {
    }

    public WriteTemp(String uid, Context context) {
        this.uid = uid;
        this.context = context;

        this.fileName = uid + WriteObjectToFile.dataSuffix ;
    }

    // Call this method by first constructing a new object and then passing remaining counts
    public  void writeNewMessagesToFile(final int addCount , final int notificationCount , final int sharingCount , final int planoutCount ) {

        progressAddCount = 0 ;
        progressNotificationCount = 0  ;
        progressSharingCount = 0 ;
        progressPlanoutCount = 0 ;

        if ( addCount> 0 ) {
            addDone = false ;
        }
        if ( notificationCount > 0 ) {
            notificationDone = false ;
        }
        if ( sharingCount > 0 ) {
            sharingDone = false ;
        }
        if ( planoutCount > 0 ) {
            planoutDone = false ;
        }


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();


        final DatabaseReference databaseReferenceToSendingPendingOfCurrentUser = databaseReference.child(App.PATH_TO_UID).child(RealtimeDatabaseContract.SENDING_PENDING);

        dataAndTypeArrayList = new ArrayList<>() ;

        databaseReferenceToSendingPendingAddveriseOfCurrentUser = databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_ADVERTISE) ;
        databaseReferenceToSendingPendingNotificationOfCurrentUser = databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_NOTIFICATIONS) ;
        databaseReferenceToSendingPendingSharingOfCurrentUser = databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_SHARING) ;
        databaseReferenceToSendingPendingPlanoutOfCurrentUser = databaseReferenceToSendingPendingOfCurrentUser.child(RealtimeDatabaseContract.SENDING_PENDING_PLANOUT) ;


        if ( addCount > 0 ) {

            databaseReferenceToSendingPendingAddveriseOfCurrentUser.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (!dataSnapshot.getKey().equals(WriteObjectToFile.COUNT)) {


                        String reference = dataSnapshot.getValue(String.class);
                        DatabaseReference databaseReferenceToAddvertise = databaseReference.child(reference);
                        databaseReferenceToAddvertise.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {


                                Addvertise addvertise = dataSnapshot.getValue(Addvertise.class);
                                DataAndType dataAndType = new DataAndType(addvertise, WriteObjectToFile.ADDVERTISE_OBJECT);
                                dataAndTypeArrayList.add(dataAndType);

                                ++progressAddCount;
                                if (progressAddCount == addCount) {
                                    databaseReferenceToSendingPendingAddveriseOfCurrentUser.removeEventListener(this);
                                    addDone = true;
                                    if (allFetchingDone()) {
                                        doDatabaseWork(0);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
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

        if ( notificationCount > 0 ) {

            databaseReferenceToSendingPendingNotificationOfCurrentUser.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (!dataSnapshot.getKey().equals(WriteObjectToFile.COUNT)) {

                        String reference = dataSnapshot.getValue(String.class);
                        DatabaseReference databaseReferenceToNotifications = databaseReference.child(reference);
                        databaseReferenceToNotifications.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {


                                CMateNotification cMateNotification = dataSnapshot.getValue(CMateNotification.class);
                                DataAndType dataAndType = new DataAndType(cMateNotification, WriteObjectToFile.NOTIFICATION_OBJECT);
                                dataAndTypeArrayList.add(dataAndType);

                                progressNotificationCount++ ;
                                if (progressNotificationCount == notificationCount) {
                                    databaseReferenceToSendingPendingNotificationOfCurrentUser.removeEventListener(this);
                                    notificationDone = true;
                                    if (allFetchingDone()) {
                                        //then do db work
                                        doDatabaseWork(1);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
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


        if ( sharingCount > 0 ) {

            databaseReferenceToSendingPendingSharingOfCurrentUser.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (!dataSnapshot.getKey().equals(WriteObjectToFile.COUNT)) {

                        String reference = dataSnapshot.getValue(String.class);
                        DatabaseReference databaseReferenceToSharing = databaseReference.child(reference);
                        databaseReferenceToSharing.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                CMateSharing cMateSharing = dataSnapshot.getValue(CMateSharing.class);
                                DataAndType dataAndType = new DataAndType(cMateSharing, WriteObjectToFile.SHARING_OBJECT);
                                dataAndTypeArrayList.add(dataAndType);

                                progressSharingCount++;
                                if (progressSharingCount == sharingCount) {
                                    databaseReferenceToSendingPendingSharingOfCurrentUser.removeEventListener(this);
                                    addDone = true;
                                    if (allFetchingDone()) {
                                        //then do db work
                                        doDatabaseWork(2);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
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

        if ( planoutCount > 0 ) {

            databaseReferenceToSendingPendingPlanoutOfCurrentUser.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    if (!dataSnapshot.getKey().equals(WriteObjectToFile.COUNT)) {

                        String reference = dataSnapshot.getValue(String.class);
                        DatabaseReference databaseReferenceToPlanout = databaseReference.child(reference);
                        databaseReferenceToPlanout.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                Planout planout = dataSnapshot.getValue(Planout.class);
                                DataAndType dataAndType = new DataAndType(planout, WriteObjectToFile.PLANOUT_OBJECT);
                                dataAndTypeArrayList.add(dataAndType);

                                progressPlanoutCount++;
                                if (progressPlanoutCount == planoutCount) {
                                    databaseReferenceToSendingPendingPlanoutOfCurrentUser.removeEventListener(this);
                                    addDone = true;
                                    if (allFetchingDone()) {
                                        //then do db work
                                        doDatabaseWork(3);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
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
    }

    private boolean allFetchingDone() {

        if ( addDone && notificationDone && sharingDone && planoutDone ) // if all true then
            return true ;
        else
            return false ;
    }

    private void doDatabaseWork (int l) {


        Collections.sort(dataAndTypeArrayList) ;

        saveToFile1( dataAndTypeArrayList );

        ArrayList<DataAndType> returnedArrayList = new ArrayList<>() ;

        returnedArrayList = readFromFile() ;

        int k = 0 ;
        for ( DataAndType dataAndType : returnedArrayList ) {
            System.out.println("TEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMPTEMP TEMP TEMP  " + k++ + dataAndType.getData().getPostingTimeInMillisecond() );
        }

        for (DataAndType dataAndType : returnedArrayList) {
            switch (dataAndType.getType()) {
                case WriteObjectToFile.ADDVERTISE_OBJECT:
                    Addvertise addvertise = ((Addvertise) dataAndType.getData());
                    Toast.makeText( context , "" + addvertise.getPosterUid() , Toast.LENGTH_SHORT).show();
                    break;
                case WriteObjectToFile.NOTIFICATION_OBJECT:
                    CMateNotification cMateNotification = ((CMateNotification) dataAndType.getData());
                    Toast.makeText( context , "" + cMateNotification.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case WriteObjectToFile.PLANOUT_OBJECT:
                    break;
                case WriteObjectToFile.SHARING_OBJECT:
                    break;
            }

        }
    }

    private void saveToFile(ArrayList<DataAndType> dataAndTypeArrayList ) {

        File dataFile = new File( context.getFilesDir(), fileName  );
        FileOutputStream fileOutputStreamToDataFile = null;
        ObjectOutputStream objectOutputStreamToDataFile = null;

        FileInputStream fileInputStreamFromDataFile = null ;
        ObjectInputStream objectInputStreamFromDataFile = null ;

        ArrayList<DataAndType> arrayListTemp = new ArrayList<>() ;

        if ( dataFile.exists() ) {
            try {
                fileInputStreamFromDataFile = new FileInputStream(dataFile);
                objectInputStreamFromDataFile = new ObjectInputStream(fileInputStreamFromDataFile);

                DataAndType d = null ;

                while ( fileInputStreamFromDataFile.available() > 0) {
                    d = (DataAndType) objectInputStreamFromDataFile.readObject() ;
                    arrayListTemp.add ( d ) ;
                }
                objectInputStreamFromDataFile.close() ;
                fileInputStreamFromDataFile.close() ;


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // End TEst


        try {
            fileOutputStreamToDataFile = new FileOutputStream(dataFile);
            objectOutputStreamToDataFile = new ObjectOutputStream(fileOutputStreamToDataFile) ;

            // Copy existing data from file
            for ( DataAndType dataAndType : arrayListTemp ) {
                objectOutputStreamToDataFile.writeObject( dataAndType );
            }

            // Update new data at end of file
            for (DataAndType dataAndType : dataAndTypeArrayList) {
                objectOutputStreamToDataFile.writeObject(dataAndType);
            }
            objectOutputStreamToDataFile.close();
            fileOutputStreamToDataFile.close();
        } catch (FileNotFoundException e) {
            Toast.makeText( context , "File Not Found Exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText( context , "IO Exception", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private void saveToFile1(ArrayList<DataAndType> dataAndTypeArrayList ) {

        for ( DataAndType dataAndType : dataAndTypeArrayList ) {

            File dataFileOfMUser = new File( context.getFilesDir() , dataAndType.getData().getPosterUid() + WriteObjectToFile.dataSuffix  ) ;
            FileOutputStream fileOutputStreamMUserOfDataFile = null ;
            ObjectOutputStream objectOutputStreamMUserOfDataFile = null ;

            FileInputStream fileInputStreamMUserDataFile = null ;
            ObjectInputStream objectInputStreamMUserDataFile = null ;

            ArrayList<DataAndType> arrayListTemp = new ArrayList<>() ;

            if ( dataFileOfMUser.exists() ) {
                try {
                    fileInputStreamMUserDataFile = new FileInputStream(dataFileOfMUser);
                    objectInputStreamMUserDataFile = new ObjectInputStream(fileInputStreamMUserDataFile);

                    DataAndType d = null ;

                    while ( fileInputStreamMUserDataFile.available() > 0) {
                        d = (DataAndType) objectInputStreamMUserDataFile.readObject() ;
                        arrayListTemp.add ( d ) ;
                    }
                    objectInputStreamMUserDataFile.close() ;
                    fileInputStreamMUserDataFile.close() ;


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                fileOutputStreamMUserOfDataFile = new FileOutputStream(dataFileOfMUser);
                objectOutputStreamMUserOfDataFile = new ObjectOutputStream(fileOutputStreamMUserOfDataFile) ;

                // Copy existing data from file
                for ( DataAndType dataAndType1 : arrayListTemp ) {
                    objectOutputStreamMUserOfDataFile.writeObject( dataAndType1 );
                }

                // Update new data at end of file
                objectOutputStreamMUserOfDataFile.writeObject(dataAndType) ;

                objectOutputStreamMUserOfDataFile.close();
                fileOutputStreamMUserOfDataFile.close();
            } catch (FileNotFoundException e) {
                Toast.makeText( context , "File Not Found Exception", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText( context , "IO Exception", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public ArrayList<DataAndType> readFromFile() {

        File dataFile = new File( context.getFilesDir() , fileName ) ;
        FileInputStream fileInputStreamFromDataFile = null ;
        ObjectInputStream objectInputStreamFromDataFile = null ;

        ArrayList<DataAndType> returningArrayList = new ArrayList<>() ;

        if ( dataFile.exists() ) {
            try {
                fileInputStreamFromDataFile = new FileInputStream(dataFile);
                objectInputStreamFromDataFile = new ObjectInputStream(fileInputStreamFromDataFile);

                DataAndType dataAndType = null;
                try {

                    while ((dataAndType = ((DataAndType) objectInputStreamFromDataFile.readObject())) != null) {
                        returningArrayList.add(dataAndType);
                    }
                } catch (ClassNotFoundException e) {
                    Toast.makeText(context, "ClassNotFoundException", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                objectInputStreamFromDataFile.close();
                fileInputStreamFromDataFile.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            return null ; // Return null if file is not present

        return returningArrayList ;
    }

    public ArrayList<DataAndType> readFromFileTemo( String uid ) {

        File dataFile = new File( context.getFilesDir() ,uid + WriteObjectToFile.dataSuffix ) ;
        FileInputStream fileInputStreamFromDataFile = null ;
        ObjectInputStream objectInputStreamFromDataFile = null ;

        ArrayList<DataAndType> returningArrayList = new ArrayList<>() ;

        if ( dataFile.exists() ) {
            try {
                fileInputStreamFromDataFile = new FileInputStream(dataFile);
                objectInputStreamFromDataFile = new ObjectInputStream(fileInputStreamFromDataFile);

                DataAndType dataAndType = null;
                try {

                    while ((dataAndType = ((DataAndType) objectInputStreamFromDataFile.readObject())) != null) {
                        returningArrayList.add(dataAndType);
                    }
                } catch (ClassNotFoundException e) {
                    Toast.makeText(context, "ClassNotFoundException", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                objectInputStreamFromDataFile.close();
                fileInputStreamFromDataFile.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            return null ; // Return null if file is not present

        return returningArrayList ;
    }

    public void deleteFile( String uid ) {
        context.deleteFile( fileName ) ;
    }

}
