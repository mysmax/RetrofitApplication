package mysmax.com.retrofitapplication.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mysmax.com.retrofitapplication.R;


public class FragmentB extends Fragment {


    public interface BListener
    {
        void onBbuttonClick(String msg);
    }
    BListener listener;
    TextView bTxt;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (BListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_b,container,false);
        bTxt = view.findViewById(R.id.tvFb);
        view.findViewById(R.id.btn_fb1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBbuttonClick("Btn Fb1 Clicked");
            }
        });
        view.findViewById(R.id.btn_fb2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBbuttonClick("Btn Fb2 Clicked");
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void upDateBText(String msg)
    {
        bTxt.setText("" + msg);
    }
}
