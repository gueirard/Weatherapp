package com.android.weatherlib.JsonData;

import com.android.weatherlib.Interfaces.ParseJson;

import org.json.JSONObject;

/**
 * Created by Damien on 21/08/2018.
 */

public class Channel implements ParseJson{

    private Units units;
    private Item item;

    @Override
    public void parse(JSONObject data) {

        units = new Units();
        units.parse(data.optJSONObject("units"));

        item = new Item();
        item.parse(data.optJSONObject("item"));
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }
}
