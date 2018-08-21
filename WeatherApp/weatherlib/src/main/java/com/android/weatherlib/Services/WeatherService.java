package com.android.weatherlib.Services;

import android.net.Uri;
import android.os.AsyncTask;

import com.android.weatherlib.Interfaces.WeatherServiceCallback;
import com.android.weatherlib.JsonData.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.net.wifi.WifiConfiguration.Status.strings;

/**
 * Created by Damien on 21/08/2018.
 */

public class WeatherService {

    private WeatherServiceCallback callback;
    private String geoloc;
    private Exception e;

    public WeatherService (WeatherServiceCallback callback){
        this.callback = callback;
    }

    public String getGeoloc() {
        return geoloc;
    }

    public void testWeather (String location){
        this.geoloc = location;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {


                String request = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", params[0]);
                String requestURL = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(request));


                try {
                    URL url = new URL( requestURL);
                    URLConnection connection = url.openConnection();
                    InputStream input = connection.getInputStream();
                    BufferedReader br= new BufferedReader(new InputStreamReader(input));
                    StringBuilder res = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null){
                        res.append(line);
                    }
                    return res.toString();
                } catch (MalformedURLException e1) {
                    e = e1;
                    return null;
                } catch (IOException e1) {
                    e = e1;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && e != null){
                    callback.itDoesntWork(e);
                    return;
                }
                try {
                    JSONObject recup = new JSONObject(s);
                    JSONObject resRequest = recup.optJSONObject("query");
                    int count = resRequest.optInt("count");
                    if (count == 0){
                        callback.itDoesntWork(e);
                        return;
                    }

                    Channel channel = new Channel();
                    channel.parse(resRequest.optJSONObject("results").optJSONObject("channel"));
                    callback.itWorks(channel);
                } catch (JSONException e1) {
                    callback.itDoesntWork(e1);
                }
            }
        }.execute(geoloc);
    }
}
