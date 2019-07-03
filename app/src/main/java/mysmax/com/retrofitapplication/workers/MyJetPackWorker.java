package mysmax.com.retrofitapplication.workers;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyJetPackWorker extends Worker {
    // Android JetPack (androidx)
    /*
    Worker: The main class where we will put the work that needs to be done.
    WorkRequest: It defines an individual task, like it will define which worker class should execute the task.
    WorkManager: The class used to enqueue the work requests.
    WorkInfo: The class contains information about the works. For each WorkRequest we can get a LiveData using WorkManager.
             The LiveData holds the WorkInfo and by observing it we can determine the Work Informations.
     */

    public MyJetPackWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Do something here

        return Result.SUCCESS;
    }

}
