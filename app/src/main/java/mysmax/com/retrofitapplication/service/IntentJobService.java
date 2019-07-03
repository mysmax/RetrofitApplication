package mysmax.com.retrofitapplication.service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class IntentJobService extends JobIntentService {

    private static final String TAG = "PollIntentService";
    private static final int JOB_ID = 12345;
    private Timer timer;
    private TimeTask task;
    private Context context;

    static public void enqueueWork(Context context, Intent work) {
        //if(ConstVariableIC.modeofdev.equals("Y")) Log.e(TAG, "JobService onBackground Run onEnqueueWork");
        enqueueWork(context, PollJobService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //if(ConstVariableIC.modeofdev.equals("Y")) Log.e(TAG, "JobService onBackground Run onCreate");
        context = this;
        getCurrentData(); // Lat & Lng update
        getBatteryLevel(); // Battery Status update
        task = new TimeTask();
        timer = new Timer("PollJobServiceTimer");
        timer.schedule(task,0); // start
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        //if(ConstVariableIC.modeofdev.equals("Y")) Log.e(TAG, "JobService onBackground Run onHandleWork");
        //LandingActivity.call_pollingasync(context); // Polling Call With Data
        //int tripIntervalTime = Integer.valueOf(LandingActivity.icustompollingtime) * 1000;
        timer.cancel();
        timer.purge();
        timer = null;
        task = null;
        task = new TimeTask();
        timer = new Timer("PollJobServiceTimer");
        timer.schedule(task,10); // repeat
        //if(ConstVariableIC.modeofdev.equals("Y")) Log.e(TAG, "JObService onBackground Run interval: " + tripIntervalTime);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //if(ConstVariableIC.modeofdev.equals("Y")) Log.e(TAG, "JobService onBackground Run onDestroy");
        super.onDestroy();
    }

    private class TimeTask extends TimerTask {

        @Override
        public void run() {
            // Repeat
            //if(ConstVariableIC.modeofdev.equals("Y")) Log.e(TAG, "JobService onBackground Timer Task Created");
            Intent intentService = new Intent(context,PollJobService.class);
            IntentJobService.enqueueWork(context,intentService);
        }
    }

    private void getCurrentData() {
        //if (ConstVariableIC.modeofdev.equals("Y")) Log.e("GPS-TRACKER", "LoginAct getCurrentData() enter");
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                //if (ConstVariableIC.modeofdev.equals("Y")) Log.e("GPS-TRACKER", "LoginAct getCurrentData() lat : " + Config.latitude + " lng : " + Config.longitude);
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
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // for ActivityCompat#requestPermissions for more details.
            //return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, listener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
                0, listener);
        Location mloc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location netloc = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        // First Instance
        if (mloc != null)
        {
            //if (ConstVariableIC.modeofdev.equals("Y")) Log.e("JobServiceGPS"," LastKnwnLocation Lat : " + mloc.getLatitude() + " Lng : " + mloc.getLongitude());
        }else  if (netloc != null)
        {
           //if (ConstVariableIC.modeofdev.equals("Y")) Log.e("JobServiceGPS","LastKnwnLocation newtwork Lat : " + netloc.getLatitude() + " Lng : " + netloc.getLongitude());
        }

    }

    public void getBatteryLevel() {

        Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int status  = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int isCharging = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        Log.e(TAG, "  Battery Level  :  " + level + "  Scale : " + scale + "  isCharging : " + isCharging + "   status : " + status) ;
       if (level != -1)
       {
           //Config.battery_level = level ;
           //Config.battery_scale = scale ;
           //Config.battery_isCharging = isCharging ;
           //Config.battery_status = status ;
       }
    }

    // Fore Ground Work
    private void createForeGroundNotification()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
        startForeground(9999, getNotification()); // Stay Focused
        }
    }

    private Notification getNotification() {
        Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
        NotificationChannel channel = null;
        channel = new NotificationChannel("channel_009", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = null;
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        builder = new Notification.Builder(getApplicationContext(), "channel_009").setAutoCancel(false);
        }
        return builder.build();
    }

    private void cancelForeGroundNotification()
    {
        //9988
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(9999);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            stopForeground(Service.STOP_FOREGROUND_REMOVE);
        }
    }


}
