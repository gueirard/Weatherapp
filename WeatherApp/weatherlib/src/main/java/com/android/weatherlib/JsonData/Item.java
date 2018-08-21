package com.android.weatherlib.JsonData;

import com.android.weatherlib.Interfaces.ParseJson;

import org.json.JSONObject;

/**
 * Created by Damien on 21/08/2018.
 */

public class Item implements ParseJson {

    private Condition condition;


    @Override
    public void parse(JSONObject data) {
        condition = new Condition();
        condition.parse(data.optJSONObject("condition"));
    }

    public Condition getCondition() {
        return condition;
    }
}
