package mysmax.com.retrofitapplication.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.fragment.FragmentA;
import mysmax.com.retrofitapplication.fragment.FragmentB;


public class FragCommunicateActivity extends AppCompatActivity implements FragmentA.AListener, FragmentB.BListener {

    FragmentA fragA;
    FragmentB fragB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_fragcomm);

       fragA = new FragmentA();
       fragB = new FragmentB();

        FragmentManager fma = getFragmentManager();
        FragmentTransaction fmta = fma.beginTransaction();
        fmta.add(R.id.fcom1,fragA,null).commit();

        FragmentManager fmb = getFragmentManager();
        FragmentTransaction fmtb = fmb.beginTransaction();
        fmtb.add(R.id.fcom2,fragB,null).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
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
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public void onAbuttonClick(String msg) {
        if (fragB != null) fragB.upDateBText(msg);
    }

    @Override
    public void onBbuttonClick(String msg) {
        if (fragA != null) fragA.upDateAText(msg);
    }
}
