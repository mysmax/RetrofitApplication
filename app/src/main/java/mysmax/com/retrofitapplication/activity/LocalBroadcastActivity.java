package mysmax.com.retrofitapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class LocalBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("Custom-Event-Name"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // local receiver
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Catch Custom Intent Data
            if (intent != null)
            {
                String action = intent.getAction().toString();
                if ("Custom-Event-Name".equalsIgnoreCase(action))
                {

                }
                String filterStr = intent.getStringExtra("key");
                if ("showToast".equalsIgnoreCase(filterStr))
                {
                    showToast("" + System.currentTimeMillis());
                }
            }
        }
    };

    private void callSendBroadcastMessage()
    {
        Intent intent = new Intent("Custom-Event-Name");
        intent.putExtra("key", "showToast");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void showToast(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
