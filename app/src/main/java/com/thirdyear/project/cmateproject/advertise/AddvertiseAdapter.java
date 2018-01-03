package com.thirdyear.project.cmateproject.advertise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thirdyear.project.cmateproject.R;
import com.thirdyear.project.cmateproject.utils.CommonIntents;

import java.util.List;

/**
 * Created by Suryaa Jha on 28-Feb-17.
 */

public class AddvertiseAdapter extends ArrayAdapter<Addvertise> {

    int resourceId = 0 ;

    public AddvertiseAdapter(Context context, int resource, List<Addvertise> objects) {
        super(context, 0 , objects );
        resourceId = resource ;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Addvertise addvertise = getItem(position) ;

        View boxView = convertView ;

        if ( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.advertise_list_item ,parent,false) ;
        }

        TextView addTitleView = ((TextView) convertView.findViewById(R.id.addvertiseTitleId)) ;
        ImageView addImageView = ((ImageView) convertView.findViewById(R.id.addvertiseImageId)) ;
        TextView priceView = ((TextView) convertView.findViewById(R.id.priceTextViewId)) ;
        TextView actualPriceView = ((TextView) convertView.findViewById(R.id.actualPriceTextViewId)) ;

        addvertise.setPhoneNumber("9766844502");
        addvertise.setEmailAddress("dr2pvms@gmail.com");

        convertView.findViewById(R.id.addCallImageButtonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonIntents.startCallingActivity( addvertise.getPhoneNumber() ,getContext());
            }
        });

        convertView.findViewById(R.id.addMessageImageButtonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonIntents.startMessagingActivity( addvertise.getPhoneNumber() ,"Request for " + addvertise.getTitle() ,getContext());
            }
        });

        convertView.findViewById(R.id.addMailImageButtonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] allAddress = {addvertise.getEmailAddress()} ;
                CommonIntents.startMailingActivity( allAddress ,"Request for " + addvertise.getTitle() ,getContext());
            }
        });

        addTitleView.setText(addvertise.getTitle());
        addImageView.setImageResource(R.mipmap.ic_launcher);
        priceView.setText(getContext().getString(R.string.currency)); // Display Currency
        priceView.append(String.valueOf(addvertise.getPrice()));
        actualPriceView.setText(getContext().getString(R.string.currency)); // Display Currency
        actualPriceView.append(String.valueOf(addvertise.getActualPrice())); ;

        return convertView ;
    }
}
