package com.thirdyear.project.cmateproject.notification;

import com.thirdyear.project.cmateproject.ModuleSuperclass;

/**
 * Created by Suryaa Jha on 10-Mar-17.
 */

public class CMateNotification extends ModuleSuperclass{

    private String posterUid ;
    private String message ;
    private String androidPath ;
    private String downloadUrl ;


    private String dateOfInterest ;
    private String details ;
    private int    like ;
    private int    dislike ;
    private int    abuse ;
    private int    report ;

    // Now changes

    private String addFrom ;

    public CMateNotification() {
    }

    public CMateNotification(String message, String androidPath, String downloadUrl, int semester, int department, String dateOfInterest, String details) {
        this.message = message ;
        this.androidPath = androidPath;
        this.downloadUrl = downloadUrl;
        this.semester = semester;
        this.department = department;
        this.dateOfInterest = dateOfInterest;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getAndroidPath() {
        return androidPath;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getDateOfInterest() {
        return dateOfInterest;
    }

    public String getDetails() {
        return details;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public int getAbuse() {
        return abuse;
    }

    public int getReport() {
        return report;
    }

    public String getAddFrom() {
        return addFrom;
    }

    public void setAddFrom(String addFrom) {
        this.addFrom = addFrom;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setAndroidPath(String androidPath) {
        this.androidPath = androidPath;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }



    public void setDateOfInterest(String dateOfInterest) {
        this.dateOfInterest = dateOfInterest;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public void setAbuse(int abuse) {
        this.abuse = abuse;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getPosterUid() {
        return posterUid;
    }

    public void setPosterUid(String posterUid) {
        this.posterUid = posterUid;
    }

}

