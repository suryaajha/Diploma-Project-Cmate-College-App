package com.thirdyear.project.cmateproject.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Suryaa Jha on 13-Mar-17.
 */

public class CMateProjectSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CMateProjectDb.db";


    public CMateProjectSQLiteOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CMateSQLiteDatabaseContract.SignInUserTable.CREATE_TABLE ) ;
        db.execSQL( CMateSQLiteDatabaseContract.UserMessagesTable.CREATE_TABLE ) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteTable( String tableName , SQLiteDatabase db) {

        db.execSQL( CMateSQLiteDatabaseContract.SignInUserTable.DELETE_TABLE ) ;

    }
}
