package com.thirdyear.project.cmateproject.main_details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thirdyear.project.cmateproject.R;

import java.util.ArrayList;

/**
 * Created by Suryaa Jha on 18-Mar-17.
 */

public class MainDataHomeAdapter extends ArrayAdapter<String> {

    int resource = -1 ;
    public MainDataHomeAdapter (Context context , int resource , ArrayList<String> arrayList )  {
        super(context,0, arrayList );
        this.resource = resource ;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String uid = getItem(position) ;
        if ( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(resource,parent,false) ;
        }

        ((TextView) convertView.findViewById(R.id.notificationPosterNameId)).setText("Emekar Sir") ;
        ((TextView) convertView.findViewById(R.id.notificationPosterMessageId)).setText( uid ) ;
        ((ImageView) convertView.findViewById(R.id.notificationPosterDpId)).setImageResource(R.mipmap.ic_launcher) ;

        return convertView ;

    }
}
