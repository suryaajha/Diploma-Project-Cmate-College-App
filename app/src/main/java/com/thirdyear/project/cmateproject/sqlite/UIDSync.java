package com.thirdyear.project.cmateproject.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Suryaa Jha on 14-Mar-17.
 */

public class UIDSync {

    private String pathToUid ;
    private int    userCode ;
    private String userDepartment ;
    private String userSemester ;
    private String userDivision ;
    private String userBatch ;

    public UIDSync() {
    }

    public UIDSync( String pathToUid, int userCode, String userDepartment, String userSemester, String userDivision, String userBatch) {
        this.pathToUid = pathToUid;
        this.userCode = userCode;
        this.userDepartment = userDepartment;
        this.userSemester = userSemester;
        this.userDivision = userDivision;
        this.userBatch = userBatch;
    }


    public String getPathToUid() {
        return pathToUid;
    }

    public void setPathToUid(String pathToUid) {
        this.pathToUid = pathToUid;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserSemester() {
        return userSemester;
    }

    public void setUserSemester(String userSemester) {
        this.userSemester = userSemester;
    }

    public String getUserDivision() {
        return userDivision;
    }

    public void setUserDivision(String userDivision) {
        this.userDivision = userDivision;
    }

    public String getUserBatch() {
        return userBatch;
    }

    public void setUserBatch(String userBatch) {
        this.userBatch = userBatch;
    }

    public static UIDSync obtainUIDSync(Context context) {

        CMateProjectSQLiteOpenHelper cMateSQLiteOpenHelper = new CMateProjectSQLiteOpenHelper(context) ;
        SQLiteDatabase db = cMateSQLiteOpenHelper.getReadableDatabase() ;

        Cursor cursorOfUIDSync = db.query(CMateSQLiteDatabaseContract.SignInUserTable.TABLE_NAME,null,null,null,null,null,null) ;

        int indexOfUid = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.USER_ID) ;
        int indexOfPathToUid = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.PATH_TO_UID) ;
        int indexOfUserCode = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.USER_CODE) ;
        int indexOfDepartment = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.DEPARTMENT) ;
        int indexOfSemester = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.SEMESTER) ;
        int indexOfDivision = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.DIVISION) ;
        int indexOfBatch = cursorOfUIDSync.getColumnIndex(CMateSQLiteDatabaseContract.SignInUserTable.BATCH) ;

        cursorOfUIDSync.moveToFirst() ;

        UIDSync uidSync = new UIDSync(
                cursorOfUIDSync.getString(indexOfPathToUid),
                cursorOfUIDSync.getInt(indexOfUserCode),
                cursorOfUIDSync.getString(indexOfDepartment),
                cursorOfUIDSync.getString(indexOfSemester),
                cursorOfUIDSync.getString(indexOfDivision),
                cursorOfUIDSync.getString(indexOfBatch)
        ) ;

        return uidSync ;

    }

}
