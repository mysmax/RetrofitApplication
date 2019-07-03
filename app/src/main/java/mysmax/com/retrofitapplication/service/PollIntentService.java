package mysmax.com.retrofitapplication.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 31/05/18.
 */

public class PollIntentService extends IntentService {

    private static final String TAG = "PollService";
    private Timer timer;
    private TimeTask task;
    private Context context;


    public PollIntentService() {super("PollServiceManager");}

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Log.d(TAG, "Service onBackground Run onHandleIntent");
        //int tripIntervalTime = Integer.valueOf(LandingActivity.icustompollingtime) * 1000;
        //Log.d(TAG, "Service onBackground Run interval: " + tripIntervalTime);
        //timer.schedule(task,0,tripIntervalTime);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        task = new TimeTask();
        timer = new Timer("PollTimer");
        timer.schedule(task,0); // start
        Log.i(TAG, "Service onBackground Run onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
         Log.i(TAG, "Service onBackground Run onStartCommand");
        //LandingActivity.call_pollingasync(context); // Polling Call With Data
        //int tripIntervalTime = Integer.valueOf(LandingActivity.icustompollingtime) * 1000;
        //DashboardActivity.bgPolling(context);
        int tripIntervalTime = 15 * 1000;
        timer.cancel();
        timer.purge();
        timer = null;
        task = null;
        task = new TimeTask();
        timer = new Timer("PollTimer");
        timer.schedule(task,tripIntervalTime); // repeat
        Log.i(TAG, "Service onBackground Run interval: " + tripIntervalTime);

        return Service.START_STICKY;
    }

    private class TimeTask extends TimerTask {

        @Override
        public void run() {
            Log.i(TAG, "Service onBackground Timer Task Created");
            Intent intentService = new Intent(context,PollIntentService.class);
            startService(intentService); // call
        }
    }

}
