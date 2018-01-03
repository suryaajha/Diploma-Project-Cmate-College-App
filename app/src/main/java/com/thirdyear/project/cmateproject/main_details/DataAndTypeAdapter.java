package com.thirdyear.project.cmateproject.main_details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.advertise.Addvertise;
import com.thirdyear.project.cmateproject.notification.CMateNotification;
import com.thirdyear.project.cmateproject.planout.Planout;
import com.thirdyear.project.cmateproject.utils.WriteObjectToFile;

import java.util.ArrayList;

/**
 * Created by Suryaa Jha on 15-Mar-17.
 */

public class DataAndTypeAdapter extends ArrayAdapter<DataAndType> {

    private int resource = -1  ;

    public DataAndTypeAdapter(Context context, int resource , ArrayList<DataAndType> arrayList) {

       super(context,0,arrayList);
        this.resource = resource ;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataAndType dataAndType = getItem(position) ;

        if ( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(resource,parent,false) ;
        }

        convertView.findViewById(R.id.addvertiseChatBoxMessage).setVisibility(View.GONE);
        convertView.findViewById(R.id.notificationChatBoxMessage).setVisibility(View.GONE);
        convertView.findViewById(R.id.sharingChatBoxMessage).setVisibility(View.GONE);
        convertView.findViewById(R.id.planoutChatBoxMessage).setVisibility(View.GONE);

        switch (dataAndType.getType()) {
            case WriteObjectToFile.ADDVERTISE_OBJECT  :
                LinearLayout addvertiseLinearLayout = (LinearLayout) convertView.findViewById(R.id.addvertiseChatBoxMessage) ;
                addvertiseLinearLayout.setVisibility(View.VISIBLE) ;
                TextView textView = (TextView)addvertiseLinearLayout.findViewById(R.id.t) ;
                Addvertise addvertise = ((Addvertise) dataAndType.getData())  ;
                textView.setText(addvertise.getTitle()) ;
                break;
            case WriteObjectToFile.NOTIFICATION_OBJECT  :
                LinearLayout notificationLinearLayout = (LinearLayout) convertView.findViewById(R.id.notificationChatBoxMessage) ;
                notificationLinearLayout.setVisibility(View.VISIBLE);
                TextView textView1 = (TextView)notificationLinearLayout.findViewById(R.id.n) ;
                CMateNotification cMateNotification = (CMateNotification) dataAndType.getData() ;
                textView1.setText(cMateNotification.getMessage());
                break;
            case WriteObjectToFile.SHARING_OBJECT  :
                LinearLayout sharingLinearLayout = (LinearLayout) convertView.findViewById(R.id.sharingChatBoxMessage) ;
                sharingLinearLayout.setVisibility(View.VISIBLE);
                //Same as Notificaton and Add
                break;
            case WriteObjectToFile.PLANOUT_OBJECT  :
                LinearLayout planoutLinearLayout = (LinearLayout) convertView.findViewById(R.id.planoutChatBoxMessage) ;
                planoutLinearLayout.setVisibility(View.VISIBLE);
                break;
        }

        return convertView ;
    }
}
