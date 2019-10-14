package com.example.postparampractice;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class GetAsyncData extends AsyncTask<String, Void, LinkedList<String>> {
    IData iData;

    public GetAsyncData(IData iData){
        this.iData = iData;
    }

    @Override
    protected LinkedList<String> doInBackground(String... strings) {
//        LinkedList<String> tweets = new LinkedList<>();
//        for(int i=0;i<10;i++){
//            tweets.add("tweets "+i);
//        }
        StringBuilder stringBuilder;
        LinkedList<String> list = new LinkedList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            String data = null;
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                data = IOUtils.toString(conn.getInputStream(),"UTF-8");
            }

            for(String x: data.split(";")){
                list.add(x);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(LinkedList<String> list) {
       // secondActivity.handleData(list);
        iData.handleLinkedData(list);
    }

    public static interface IData{
        public void handleLinkedData(LinkedList<String> data);
    }

}