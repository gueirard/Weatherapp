package com.android.weatherlib.Interfaces;
import com.android.weatherlib.JsonData.Channel;

/**
 * Created by Damien on 21/08/2018.
 */

public interface WeatherServiceCallback {
    void itWorks(Channel channel);
    void itDoesntWork(Exception e);
}
