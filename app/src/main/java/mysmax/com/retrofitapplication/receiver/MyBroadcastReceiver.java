package mysmax.com.retrofitapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MyBroadcastReceiver extends BroadcastReceiver {

    /*
    // Policy changed by google to Receive SMS directly, Some Permission inclusion
    // Declare in Manifest
        <receiver android:name=".receiver.OtpSmsReceiver">
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>
   */

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        //Log.d("Customer Receiver Response", (bundle != null) ? bundle.toString() : null);
        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String message = currentMessage.getDisplayMessageBody();
                    if (message.toLowerCase().contains("YourAppKey") && message.toLowerCase().contains("otp")) {
                        //OneTimePasswordFragment.getInstance().setOtp(message.replaceAll("\\D+",""));
                    }
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        // or
        if (intent != null)
            {
                // set action name during manifest declare
                // Request action name during send

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
         */

    }
}
