package com.android.weatherlib.JsonData;

import com.android.weatherlib.Interfaces.ParseJson;

import org.json.JSONObject;

/**
 * Created by Damien on 21/08/2018.
 */

public class Condition implements ParseJson {

    private int code;
    private int temp;
    private String text;
    private String date;

    @Override
    public void parse(JSONObject data) {

        code = data.optInt("code");
        temp = data.optInt("temp");
        text = data.optString("text");
        date = data.optString("date");
    }

    public int getCode() {
        return code;
    }

    public int getTemp() {
        return temp;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
