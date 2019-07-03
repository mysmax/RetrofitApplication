package mysmax.com.retrofitapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AnouncementCatch extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       if (intent != null)
       {
           Bundle bundle = intent.getExtras();
       }
    }
}
