package mysmax.com.retrofitapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import mysmax.com.retrofitapplication.aidl.MyAidlInterface;

public class AidlServiceBinder extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyAidlInterface.Stub() {
            @Override
            public int calcFact(int a) {
                return calcFacto(a);
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    public int calcFacto(int n)
    {
        int fact = 1; // default
        for(int i=n; i>=1; --i)
        {
            fact*=i;
        }
        return fact;
    }
}
