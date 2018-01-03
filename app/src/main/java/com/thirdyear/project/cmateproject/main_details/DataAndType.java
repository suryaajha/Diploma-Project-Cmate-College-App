package com.thirdyear.project.cmateproject.main_details;

import com.thirdyear.project.cmateproject.ModuleSuperclass;

import java.io.Serializable;

/**
 * Created by Suryaa Jha on 15-Mar-17.
 */

public class DataAndType implements Serializable , Comparable<DataAndType>{

    ModuleSuperclass data = null ;
    int    type  ;

    public DataAndType(ModuleSuperclass data, int type) {
        this.data = data;
        this.type = type;
    }

    public ModuleSuperclass getData() {
        return data;
    }

    public void setData(ModuleSuperclass data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int compareTo(DataAndType dataAndType) {

        if ( this.getData().getPostingTimeInMillisecond() >  dataAndType.getData().getPostingTimeInMillisecond()) {
            return 1 ;
        }else if ( this.getData().getPostingTimeInMillisecond() < dataAndType.getData().getPostingTimeInMillisecond() ) {
            return  -1 ;
        }
        return 0 ;
    }
}
