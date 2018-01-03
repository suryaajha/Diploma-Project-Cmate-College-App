package com.thirdyear.project.cmateproject.sqlite;

import android.provider.BaseColumns;

/**
 * Created by Suryaa Jha on 13-Mar-17.
 */

public class CMateSQLiteDatabaseContract {

    private CMateSQLiteDatabaseContract(){} // No One can make object of this class

    public static class SignInUserTable implements BaseColumns {

        public static final String TABLE_NAME = "sign_in_user_table_final";

        public static final String USER_ID = "user_id";
        public static final String PATH_TO_UID = "path_to_uid";
        public static final String USER_CODE = "user_code"; // 0 or 1 or 2 for student , teacher , admin
        public static final String DEPARTMENT = "department";
        public static final String SEMESTER = "semester"; // if not valid then enter null
        public static final String DIVISION = "division";
        public static final String BATCH = "batch";

        //CREATE TABLE table name  ( name datatype , name datatpe ) ;
        public static final String CREATE_TABLE = "CREATE TABLE " + SignInUserTable.TABLE_NAME +
                "(" +
                SignInUserTable._ID + " INTEGER PRIMARY KEY," +
                SignInUserTable.USER_ID + " TEXT," +
                SignInUserTable.PATH_TO_UID + " TEXT," +
                SignInUserTable.USER_CODE + " INTEGER," +
                SignInUserTable.DEPARTMENT + " TEXT," +
                SignInUserTable.SEMESTER + " TEXT," +
                SignInUserTable.DIVISION + " TEXT," +
                SignInUserTable.BATCH + " TEXT" +
                ")";
        public static final String DELETE_TABLE = "DELETE FROM " + SignInUserTable.TABLE_NAME ;
    }

    public static class UserMessagesTable implements BaseColumns {

        public static final String TABLE_NAME = "user_messages_table";

        public static final String USER_ID = "user_id";

        //CREATE TABLE table name  ( name datatype , name datatpe ) ;
        public static final String CREATE_TABLE = "CREATE TABLE " + UserMessagesTable.TABLE_NAME +
                "(" +
                UserMessagesTable._ID + " INTEGER PRIMARY KEY," +
                UserMessagesTable.USER_ID + " TEXT" +
                ")";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + UserMessagesTable.TABLE_NAME ;

    }
}
