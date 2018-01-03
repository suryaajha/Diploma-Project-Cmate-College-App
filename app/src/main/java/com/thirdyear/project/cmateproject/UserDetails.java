package com.thirdyear.project.cmateproject;

/**
 * Created by Suryaa Jha on 20-Feb-17.
 */

// There is some problem in fetching Data From Firebase

public class UserDetails {
    private String userName ;
    private String password ;
    private String firstName ;
    private String lastName ;
    private String emailAddress ;
    private String address ;
    private String uid ;

    private long enrollmentNumber ;
    private long mobileNumber ;

    public UserDetails() {
    }

    /*public UserDetails ( String userName , String password , String firstName , String lastName , String emailAddress , String address , long enrollmentNumber , long mobileNumber , int age ) {

        this.userName = userName ;
        this.password = password ;
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.emailAddress = emailAddress ;
        this.address = address ;
        this.enrollmentNumber = enrollmentNumber ;
        this.mobileNumber = mobileNumber ;

        this.age = age ;
    }*/

    public UserDetails (  String firstName , String lastName , String emailAddress , String password ,  String address , long enrollmentNumber , long mobileNumber  ) {

        this.firstName = firstName ;
        this.lastName = lastName ;
        this.emailAddress = emailAddress ;
        this.password = password ;
        this.address = address ;
        this.enrollmentNumber = enrollmentNumber ;
        this.mobileNumber = mobileNumber ;

    }
    public UserDetails (  String firstName , String lastName , String emailAddress , String password ,  String address  , long mobileNumber  ) {

        this.firstName = firstName ;
        this.lastName = lastName ;
        this.emailAddress = emailAddress ;
        this.password = password ;
        this.address = address ;
        this.mobileNumber = mobileNumber ;

    }


    public String getUserName() {
        return userName;
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

    public String getUid() {
        return uid;
    }

}
