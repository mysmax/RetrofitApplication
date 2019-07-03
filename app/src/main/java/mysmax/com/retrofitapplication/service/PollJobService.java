package mysmax.com.retrofitapplication.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


public class PollJobService extends JobService {

    JobParameters params;
    DoItTask doIt;

    private static final String TAG = "PollJobService";
    int tripIntervalTime;

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d(TAG, "Service onBackground Run onCreate");
        tripIntervalTime = 60*1000;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        this.params = params;
        //Log.d("TestService", "Work to be called from here");
        doIt = new DoItTask();
        doIt.execute();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        //Log.d("TestService", "System calling to stop the job here");
        //if (doIt != null) doIt.cancel(true);
        return false;
    }

    private class DoItTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("DoItTask", "Working here...");
            try {
                Thread.sleep(tripIntervalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("DoItTask", "Working Done here...");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Log.d("DoItTask", "Clean up the task here and call jobFinished...");
            //jobFinished(params, true);
            doIt = null;
            // Start Again
            JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
            ComponentName componentName = new ComponentName(getApplicationContext(), PollJobService.class);
            JobInfo jobInfo = new JobInfo.Builder(1, componentName).setMinimumLatency(0).build();
            jobScheduler.schedule(jobInfo);
            super.onPostExecute(aVoid);
        }
    }

}
