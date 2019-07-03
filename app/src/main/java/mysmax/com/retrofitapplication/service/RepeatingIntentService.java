package mysmax.com.retrofitapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class RepeatingIntentService extends IntentService {

    public RepeatingIntentService(){ super("RepeatingIntentService");}

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("RepeatingIntent","onHandleIntent Thread Sleep");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("RepeatingIntent","onHandleIntent Thread Awake");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        // In intent service use default
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("RepeatingIntent","onDestroy");
        super.onDestroy();
    }
}
