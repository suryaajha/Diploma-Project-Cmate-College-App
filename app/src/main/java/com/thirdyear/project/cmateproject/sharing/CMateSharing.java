package com.thirdyear.project.cmateproject.sharing;

import com.thirdyear.project.cmateproject.ModuleSuperclass;

/**
 * Created by Suryaa Jha on 16-Mar-17.
 */

public class CMateSharing extends ModuleSuperclass {

    private String title ;
    private String downloadUrl  ;

    public CMateSharing() {
    }

    public CMateSharing(String title, String downloadUrl) {
        this.title = title;
        this.downloadUrl = downloadUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
