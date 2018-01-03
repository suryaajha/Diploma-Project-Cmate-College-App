package com.thirdyear.project.cmateproject.notification;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thirdyear.project.cmateproject.R;


import java.util.List;

/**
 * Created by Suryaa Jha on 28-Feb-17.
 */

public class NotificationAdapter extends ArrayAdapter<CMateNotification> {

    int resourceId = 0 ;

    public NotificationAdapter(Context context, int resource, List<CMateNotification> objects) {
        super(context, 0 , objects );
        resourceId = resource ;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final CMateNotification cMateNotification = getItem(position) ;
        cMateNotification.setAddFrom("Emekar Sir") ;

        View boxView = convertView ;

        if ( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.notification_list_item ,parent,false) ;
        }

        ImageView posterDpImageView = ((ImageView) convertView.findViewById(R.id.notificationPosterDpId)) ;
        TextView posterNameTextView = ((TextView) convertView.findViewById(R.id.notificationPosterNameId)) ;
        TextView posterMessageTextView = ((TextView) convertView.findViewById(R.id.notificationPosterMessageId)) ;

        if ( false ) {
            Glide.with(posterDpImageView.getContext()) // display the image in the image view
                    .load(cMateNotification.getDownloadUrl())
                    .into(posterDpImageView);
        }
        posterNameTextView.setText(cMateNotification.getAddFrom());
        posterMessageTextView.setText(cMateNotification.getMessage()) ;

        return convertView ;
    }
}
