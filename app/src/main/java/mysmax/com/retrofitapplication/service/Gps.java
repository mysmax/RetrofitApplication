package mysmax.com.retrofitapplication.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class Gps  extends Service implements LocationListener{

    Context context;
    LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Log.e("GPS Service","Create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("GPS Service","onStartCommand");
        if (intent != null)
        {
            Log.e("GPS Service","onStartCommand " + intent.getAction().toString());
            switch (intent.getAction().toString())
            {
                case "create" : startLocationManager(); break;
                case "stop" : stopService(); break;
            }
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("GPS Service","onDestroy");
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("GPS Service","onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @SuppressLint("MissingPermission")
    private void startLocationManager()
    {
        Log.e("GPS Service","Create LocationManager");
        /*
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if (location != null) Log.e("GPS","Lat: " + location.getLatitude() + " Lng: " + location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        */
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0, this);
    }

    private void stopService()
    {
        Log.e("GPS Service","Stop Service");
        stopSelf();
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) Log.e("GPS","Lat: " + location.getLatitude() + " Lng: " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("GPS","onStatusChanged " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e("GPS","Provider Enabled " + provider);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e("GPS","Provider Disabled " + provider);

    }
}
