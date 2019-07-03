package mysmax.com.retrofitapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentA extends Fragment {

    public static final int MY_PERMISSIONS_REQUEST_MAKECALL = 123;

   interface AListener
   {
       void onAbuttonClick(String msg);
   }
   AListener listener;
   TextView bTxt;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (AListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_a,container,false);
         bTxt = view.findViewById(R.id.tvFa);
        view.findViewById(R.id.btn_fa1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onAbuttonClick("Btn Fa1 Clicked");
                checkCallPermission("8884781222");
            }
        });
        view.findViewById(R.id.btn_fa2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAbuttonClick("Btn Fa2 Clicked");
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void upDateAText(String msg)
    {
        bTxt.setText("" + msg);
    }

    private void checkCallPermission(String phoneNumber)
    {
        int permission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);
        //int readPerm = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);
        if (permission != PackageManager.PERMISSION_GRANTED ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setMessage("Permission to make call which is required for this app .").setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_MAKECALL); // , Manifest.permission.READ_PHONE_STATE
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else
            {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_MAKECALL);

            }
        } else {
            makeCall(phoneNumber);
        }

    }


    private void makeCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }
}
