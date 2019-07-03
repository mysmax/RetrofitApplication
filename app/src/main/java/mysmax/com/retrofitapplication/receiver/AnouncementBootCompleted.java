package mysmax.com.retrofitapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mysmax.com.retrofitapplication.activity.MainActivity;

public class AnouncementBootCompleted extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            // On Boot Complete
            Intent intn = new Intent(context,MainActivity.class);
            intn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intn);
             /*
             Intent inten = new Intent();
             inten.setComponent(new ComponentName(context.getPackageName(), MainActivity.class.getName()));
             inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
             context.getApplicationContext().startActivity(inten);
             */

    }
}
