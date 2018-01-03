package com.thirdyear.project.cmateproject.server;

import android.os.AsyncTask;
import android.widget.Toast;

import com.thirdyear.project.cmateproject.TestActivity2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Suryaa Jha on 09-Mar-17.
 */

/*
    Example:


public class TestActivity2 extends AppCompatActivity implements OnResponseListener{ //The activiy must implemtn this listener for getting responsed

    private String response = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState) ;

        setContentView(R.layout.activity_test2) ;

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerConnection serverConnection = new ServerConnection() ;

                Map<String,Object> params = new LinkedHashMap<>();
                params.put("name", "Freddie the Fish");
                params.put("email", "fishie@seamail.example.com");
                params.put("reply_to_thread", 10394);

                serverConnection.sendPostData(params);

                serverConnection.connectToPage("test") ;

                serverConnection.go() ;

            }
        });

    }

    public void onResponseRecieved(String response) {
        this.response = response ;
        Toast.makeText(TestActivity2.this, "" + response  , Toast.LENGTH_SHORT).show();
    }

}

 */

public class ServerConnection {

    private  String serverAddress = "http://" + ServerUtils.SERVER_IP_ADDRESS + ":" + ServerUtils.PORT_No + "/" ;
    private  String pageName = null ;
    private  String finalPath =  null ;
    private  Map<String,Object> sendingMap = null ;
    private  PassParameterToServerTask passParameterToServerTask = null ;
    private  String responseFromServer = null ;
    private ServerAppCompatActivity activity = null ;

    public ServerConnection() {} // If request not want response
    public ServerConnection(ServerAppCompatActivity activity) {
        this.activity = activity ;
    }

    public void connectToPage(String pageName) {
        passParameterToServerTask = null ;
        this.pageName = pageName ;
    }

    public void sendPostData(Map<String,Object> sendingMap) {
        this.sendingMap = sendingMap ;
    }

    public void go(){
        passParameterToServerTask = new PassParameterToServerTask() ;
        passParameterToServerTask.execute() ;
    }

    public String getResponseFromServer() {
        return responseFromServer ;
    }

    private class PassParameterToServerTask extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {

            return delegate() ;

        }

        private String delegate() {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer();

            try {
                finalPath = serverAddress + pageName ;
                URL url = new URL(finalPath) ;



                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String,Object> param : sendingMap.entrySet()) {
                    if (postData.length() != 0) postData.append('&');
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") ;

                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(postDataBytes);

                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            responseFromServer = stringBuffer.toString() ;

            return responseFromServer ;
        }

        @Override
        protected void onPostExecute(String response) {
            System.out.println(finalPath);
            if( activity != null ) {
                activity.onResponseRecieved(response);
            }
        }
    }

}

/*
    Steps:
    1) If your activity wants to talk to server then that activity must extend ServerAppCompatActivity
    2) That activity must implement OnResponseListener
    3) Example TestActivity2.java file in server package
    4) If you want response then create ServerConnection object by ServerConnection serverConnection = new ServerConnection(TestActivity2.this) ;
    5) Else ServerConnection serverConnection = new ServerConnection() ;
    6) Example
                Map<String,Object> params = new LinkedHashMap<>();
                params.put("name", "Freddie the Fish");
                params.put("email", "fishie@seamail.example.com");
                params.put("reply_to_thread", 10394);

                serverConnection.sendPostData(params);

                serverConnection.connectToPage("test") ;

                serverConnection.go() ;
     7) Example of onResponseRecieved
        public void onResponseRecieved(String response) {
            this.response = response ;
            Toast.makeText(TestActivity2.this, "" + response  , Toast.LENGTH_SHORT).show();
        }

    8) Done
 */
