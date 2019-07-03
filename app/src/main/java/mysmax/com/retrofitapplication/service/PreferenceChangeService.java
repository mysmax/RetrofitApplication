package mysmax.com.retrofitapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mysmax.com.retrofitapplication.util.CommonSharedPreference;

public class PreferenceChangeService extends Service {
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        Log.e("PostDelay","Preference Service Create ");
        Timer timer = new Timer();
        timer.schedule(new TimerHelper(),60*1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //stopSelf();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("PostDelay","Preference Service Destroy ");
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("PostDelay","Preference Service Task Removed ");
        super.onTaskRemoved(rootIntent);
    }

    public class TimerHelper extends TimerTask
    {

        @Override
        public void run() {
            CommonSharedPreference preference = new CommonSharedPreference(context);
            Log.e("PostDelay","Preference Before Change " + preference.getName());
            Random ran = new Random();
            int random = ran.nextInt(1000);
            preference.setName("Inside Service " + random);
            Log.e("PostDelay","Preference After Change " + preference.getName());
            stopSelf();
        }
    }
}
