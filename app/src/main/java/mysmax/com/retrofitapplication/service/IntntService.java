package mysmax.com.retrofitapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class IntntService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntntService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
