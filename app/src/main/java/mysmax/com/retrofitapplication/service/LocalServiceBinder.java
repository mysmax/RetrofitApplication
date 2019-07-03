package mysmax.com.retrofitapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.activity.MainActivity;

public class LocalServiceBinder extends Service {

    private NotificationManager mNM;
    public static final int NOTIFICATION_ID = 29812;

    // Binder Class
    public class LocalBinder extends Binder
    {
        public LocalServiceBinder getService()
        {
            return  LocalServiceBinder.this;
        }
    }

    LocalBinder localBinder = new LocalBinder();


    @Override
    public void onCreate() {
        super.onCreate();
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        if (mNM != null) mNM.cancel(NOTIFICATION_ID);
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    public int getRandomNumber()
    {
        Random random = new Random();
        return random.nextInt(1000);
    }

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        //CharSequence text = getText(R.string.local_service_started);
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker("Ticker")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Label")  // the label of the entry
                .setContentText("Content Text")  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();
        // Send the notification.
        mNM.notify(NOTIFICATION_ID, notification);
    }
}
