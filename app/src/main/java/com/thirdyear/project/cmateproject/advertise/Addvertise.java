package com.thirdyear.project.cmateproject.advertise;

import com.thirdyear.project.cmateproject.ModuleSuperclass;

import java.io.Serializable;

/**
 * Created by Suryaa Jha on 28-Feb-17.
 */

public class Addvertise extends ModuleSuperclass {

    private String title ;
    private String androidPath ;
    private String downloadUrl ;


    private int    price ;
    private int    actualPrice ;
    private String details ;
    private int    like ;
    private int    dislike ;
    private int    abuse ;
    private int    report ;
    private String    phoneNumber ;
    private String    emailAddress ;

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Addvertise() {}

    public Addvertise(String title, String androidPath, String downloadUrl, int semester, int department, int price, int actualPrice, String details) {
        this.title = title;
        this.androidPath = androidPath;
        this.downloadUrl = downloadUrl;
        this.semester = semester;
        this.department = department;
        this.price = price;
        this.actualPrice = actualPrice;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAndroidPath() {
        return androidPath;
    }

    public void setAndroidPath(String androidPath) {
        this.androidPath = androidPath;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getAbuse() {
        return abuse;
    }

    public void setAbuse(int abuse) {
        this.abuse = abuse;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
