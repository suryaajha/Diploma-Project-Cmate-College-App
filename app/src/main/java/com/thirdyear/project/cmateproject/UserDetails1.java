package com.thirdyear.project.cmateproject;

/**
 * Created by Suryaa Jha on 20-Feb-17.
 */

public class UserDetails1 {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String password;
    private long enrollmentNumber;
    private long mobileNumber;


    public UserDetails1(String firstName, String lastName, String emailAddress, String password, String address, long enrollmentNumber, long mobileNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.address = address;
        this.enrollmentNumber = enrollmentNumber;
        this.mobileNumber = mobileNumber;

    }

    public UserDetails1(String firstName, String lastName, String emailAddress, String password, String address, long mobileNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.address = address;
        this.mobileNumber = mobileNumber;

    }


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public long getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

}
