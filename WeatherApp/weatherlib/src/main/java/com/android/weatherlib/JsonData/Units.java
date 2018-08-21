package com.android.weatherlib.JsonData;

import com.android.weatherlib.Interfaces.ParseJson;

import org.json.JSONObject;

/**
 * Created by Damien on 21/08/2018.
 */

public class Units implements ParseJson {

    private String unit;
    @Override
    public void parse(JSONObject data) {

        unit = data.optString("temperature");
    }

    public String getUnit() {
        return unit;
    }
}
