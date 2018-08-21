package com.android.weatherlib;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.weatherlib.Interfaces.WeatherServiceCallback;
import com.android.weatherlib.JsonData.Channel;
import com.android.weatherlib.JsonData.Item;
import com.android.weatherlib.Services.WeatherService;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weather;
    private TextView temp;
    private TextView condi;
    private TextView date;
    private TextView geoloc; // TODO : dynamique
    private WeatherService service;
    private ProgressDialog wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);

        weather = (ImageView)findViewById(R.id.imgWeather2);
        temp = (TextView) findViewById(R.id.textViewTemps);
        condi = (TextView) findViewById(R.id.textViewCondi);
        geoloc = (TextView) findViewById(R.id.textViewLoc);
        date = (TextView) findViewById(R.id.textViewDate);

        wait = new ProgressDialog(this);
        wait.setMessage("Waiting");
        wait.show();
        service = new WeatherService(this);
        service.testWeather("Paris, France");
    }

    @Override
    public void itWorks(Channel channel) {
        wait.hide();
        Item item = channel.getItem();
        int idIcon = getResources().getIdentifier("@drawable/icon_"+channel.getItem().getCondition().getCode(),null, getPackageName());
        Drawable weatherIcon = getResources().getDrawable(idIcon);
        //weather = (ImageView)findViewById(R.id.imageViewWeather);
        weather.setImageDrawable(weatherIcon);
        geoloc.setText(service.getGeoloc());
        temp.setText(item.getCondition().getTemp()+"degres" + channel.getUnits().getUnit());
        condi.setText(item.getCondition().getText());
        date.setText(item.getCondition().getDate());

        Log.i("date - - - - - - -", String.valueOf(date));
        Log.i("geoloc - - - - - -", String.valueOf(geoloc));
        Log.i("temp - - - - - - -", String.valueOf(temp));
        Log.i("conditions - - - - - -",String.valueOf(condi));
    }

    @Override
    public void itDoesntWork(Exception e) {
        wait.hide();
        Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
    }
}
