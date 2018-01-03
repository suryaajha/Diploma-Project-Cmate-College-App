package com.thirdyear.project.cmateproject;

/**
 * Created by Suryaa Jha on 10-Mar-17.
 */

public class CountersForUserData {

    private int addvertise ;
    private int notifications ;
    private int sharing ;
    private int planout ;

    public CountersForUserData() {}

    public CountersForUserData(int addvertise, int notifications, int sharing , int planout ) {
        this.addvertise = addvertise;
        this.notifications = notifications;
        this.sharing = sharing;
        this.planout = planout ;
    }

    public int getAddvertise() {
        return addvertise;
    }

    public int getNotifications() {
        return notifications;
    }

    public int getSharing() {
        return sharing;
    }

    public int getPlanout() {
        return planout;
    }
}
