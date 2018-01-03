package com.thirdyear.project.cmateproject;

import java.io.Serializable;

/**
 * Created by Suryaa Jha on 16-Mar-17.
 */

public class ModuleSuperclass implements Serializable{
    protected long  postingTimeInMillisecond ;
    protected String posterUid ;
    private String    dbPathToSelfReference ;

    protected int semester;
    protected int department ;
    protected int division ;
    protected int batch ;


    public long getPostingTimeInMillisecond() {
        return postingTimeInMillisecond;
    }

    public void setPostingTimeInMillisecond(long postingTimeInMillisecond) {
        this.postingTimeInMillisecond = postingTimeInMillisecond;
    }

    public String getPosterUid() {
        return posterUid;
    }

    public void setPosterUid(String posterUid) {
        this.posterUid = posterUid;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getDbPathToSelfReference() {
        return dbPathToSelfReference;
    }

    public void setDbPathToSelfReference(String dbPathToSelfReference) {
        this.dbPathToSelfReference = dbPathToSelfReference;
    }
}
