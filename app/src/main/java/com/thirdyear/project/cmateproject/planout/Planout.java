package com.thirdyear.project.cmateproject.planout;

import com.thirdyear.project.cmateproject.ModuleSuperclass;

/**
 * Created by Suryaa Jha on 16-Mar-17.
 */

public class Planout extends ModuleSuperclass {

    private String messageText ;
    private int    like ;
    private int    dislike ;

    public Planout() {
    }

    public Planout(String messageText, int like, int dislike) {
        this.messageText = messageText;
        this.like = like;
        this.dislike = dislike;
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
