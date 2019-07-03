package mysmax.com.retrofitapplication.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.aidl.MyAidlInterface;
import mysmax.com.retrofitapplication.service.LocalServiceBinder;
import mysmax.com.retrofitapplication.service.MessengerServiceBinder;
import mysmax.com.retrofitapplication.service.RepeatingIntentService;

public class ServiceActivity extends AppCompatActivity {

    LocalServiceBinder localService;
    MessengerServiceBinder messengerBinder;
    MyAidlInterface aidlBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*
                ((TextView) findViewById(R.id.tv)).setText("Random Number " +localService.getRandomNumber());


                    ((TextView) findViewById(R.id.tv)).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                            ((TextView) findViewById(R.id.tv)).setText("" + aidlBinder.calcFact(localService.getRandomNumber()));
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            }
                    },5000);
                    */
                 // Start Repeating Intent Service
                    startRepeatingService();
            }
        });
        /**/

        // O onwards service changed to Jobservice, JobIntentService
        // JOB Service
        // TO Start Use JobScheduler
        /*
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getApplicationContext(), PollJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName).setMinimumLatency(1).build();
        jobScheduler.schedule(jobInfo);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // JOB Service
            Intent intentService = new Intent(getApplicationContext(),IntentJobService.class);
            IntentJobService.enqueueWork(getApplicationContext(),intentService);
        }
        */

    }

    @Override
    protected void onStart() {
        super.onStart();

        // UnBound Service
        // Start Service
        //Intent intent = new Intent(getBaseContext(), Gps.class);
        //startService(intent);
        // Stop Service
        //Intent intent = new Intent(getBaseContext(),Gps.class);
        //stopService(intent);
        // or
        // stopSelf();

        // Bound Service
        Intent intent = new Intent(getBaseContext(),LocalServiceBinder.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
       /*
        Intent intent = new Intent(this, MessengerServiceBinder.class);
        bindService(intent, msgnrConnet, Context.BIND_AUTO_CREATE);

        Intent aidlIntent = new Intent(getBaseContext(), AidlServiceBinder.class);
        bindService(aidlIntent,aidlConnect,Context.BIND_AUTO_CREATE);
        */

    }

    @Override
    protected void onStop() {
        // UnBound Service
        //Intent intent = new Intent(getBaseContext(), Gps.class);
        //stopService(intent);
        // Bound Service

        // Unbind
        unbindService(connection);
        /*
        unbindService(msgnrConnet);
        unbindService(aidlConnect);
        */
        super.onStop();
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalServiceBinder.LocalBinder binder = (LocalServiceBinder.LocalBinder) service;  // cast
            localService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    boolean isBound;
    Messenger messenger;

    ServiceConnection msgnrConnet = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBound = true;
            // Create the Messenger object
            messenger = new Messenger(service); // Initialize
            // Create a Message
            // Note the usage of MSG_SAY_HELLO as the what value
            Message msg = Message.obtain(null, MessengerServiceBinder.MSG_HELLO, 0, 0);
            // Create a bundle with the data
            Bundle bundle = new Bundle();
            bundle.putString("hello", "world");
            // Set the bundle data to the Message
            msg.setData(bundle);
            // Send the Message to the Service (in another process)
            try
            {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            messengerBinder = null;
        }
    };

    ServiceConnection aidlConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlBinder = MyAidlInterface.Stub.asInterface(service);
            try {
                int cal = aidlBinder.calcFact(1);
                Toast.makeText(getApplicationContext(),"Calc : " + cal,Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
           aidlBinder = null; // release
        }
    };

    // Alarm Service
    PendingIntent pendingIntent = null;
    AlarmManager almMngr = null;

    private void startRepeatingService()
    {   Log.e("RepeatingIntent","Service Call");
        if (pendingIntent != null) return;

        Intent intent = new Intent(getApplicationContext(), RepeatingIntentService.class);
        pendingIntent = PendingIntent.getService(getApplicationContext(),0,intent,0);

        almMngr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        almMngr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),10000,pendingIntent);
    }

    private void stopRepeatingService()
    {
        if (pendingIntent != null) {
            almMngr.cancel(pendingIntent);
            pendingIntent = null;
            almMngr = null;
        }
    }

}
