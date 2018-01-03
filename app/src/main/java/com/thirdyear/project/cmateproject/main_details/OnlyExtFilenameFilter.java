package com.thirdyear.project.cmateproject.main_details;

import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Suryaa Jha on 18-Mar-17.
 */

public class OnlyExtFilenameFilter implements FilenameFilter {

    private String intrestedExtension ;

    public OnlyExtFilenameFilter(String intrestedExtension ) {

        this.intrestedExtension = intrestedExtension ;
    }

    @Override
    public boolean accept(File file, String name) {
        return  name.endsWith(WriteObjectToFile.dataSuffix) ;
    }
}
