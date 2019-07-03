package mysmax.com.retrofitapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MessengerServiceBinder extends Service {
   public static final int MSG_HELLO = 1;

    static class IncomingHandler extends Handler
    {
        private Context applicationContext;
        IncomingHandler(Context context)
        {
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case MSG_HELLO :
                    Toast.makeText(applicationContext,"SAY HELLO!",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    Messenger  messenger = new Messenger(new IncomingHandler(this));

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }
}
