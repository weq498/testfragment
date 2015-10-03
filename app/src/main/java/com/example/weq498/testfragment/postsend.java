package com.example.weq498.testfragment;

import android.os.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by weq498 on 2015/8/15.
 */
public class postsend {
    private URL url;
    private String resultData;
    public postsend(String HttpURL){
        try {
            url = new URL(HttpURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public String get_data(String inputstrings){
        if (url != null) {
            try {
                HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
                urlconn.setRequestMethod("POST");
                urlconn.setDoInput(true);
                urlconn.setDoOutput(true);
                /*
                DataOutputStream dataout = new DataOutputStream(urlconn.getOutputStream());
                dataout.writeBytes("data1=" + inputstrings);
                dataout.flush();
                dataout.close();
                */
                InputStreamReader in = new InputStreamReader(urlconn.getInputStream());
                BufferedReader buffer = new BufferedReader(in);
                String inputLine = null;
                while ((inputLine = buffer.readLine()) != null) {
                    resultData += inputLine + "\n";
                }
                if (resultData.equals("")) {
                    resultData = "資料錯誤";
                }
                in.close();
                urlconn.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultData;
    }
}
