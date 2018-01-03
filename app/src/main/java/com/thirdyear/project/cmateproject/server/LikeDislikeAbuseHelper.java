package com.thirdyear.project.cmateproject.server;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Suryaa Jha on 04-Mar-17.
 */

public class LikeDislikeAbuseHelper {

    private static final int ADVERTISE = 0 ;
    private static final int PLAN_OUT = 1 ;
    private static final int NOTIFICAITON = 2 ;

    private static final int LIKE = 5 ;
    private static final int DISLIKE = 6 ;
    private static final int ABUSE = 7 ;

    public static void likeIt() {

    }

    public static void dislikeIt() {

    }

    public static void abuseIt() {

    }

    public void build( int context , int type ) {

        System.out.println("Done Suryaa");
        // example http://localhost:3000/abc

        String urlPath = "http://" + ServerUtils.SERVER_IP_ADDRESS + ServerUtils.COLON + "/create_user" ;

        URL url = null;
        try {
            url = new URL(urlPath);
            Map<String,Object> params = new LinkedHashMap<>();

            //params.put("name", "Freddie the Fish");
            params.put("Context" , context ) ;
            params.put("Type" , type ) ;

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
