package mysmax.com.retrofitapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import mysmax.com.retrofitapplication.activity.MainActivity;
import mysmax.com.retrofitapplication.adapter.ExpandAdapter;
import mysmax.com.retrofitapplication.eventbus.BottomLayoutEvent;
import mysmax.com.retrofitapplication.eventbus.Frag1ClickEvent;
import mysmax.com.retrofitapplication.model.ExpandInfoList;

public class FragTwo extends Fragment {
    TextView tv2;
    ArrayList<ExpandInfoList> dataList;
    ExpandableListView eplv;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataList = MainActivity.createExpandInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fone = inflater.inflate(R.layout.fragment_two,container,false);

        fone.findViewById(R.id.btnft1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) fone.findViewById(R.id.tv2)).setText("F2 Btn 1");
            }
        });
        fone.findViewById(R.id.btnft2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) fone.findViewById(R.id.tv2)).setText("F2 Btn 2");
            }
        });
        ((TextView) fone.findViewById(R.id.tv2)).setText("");
        tv2 = fone.findViewById(R.id.tv2);

        // Expandable List View
        eplv = fone.findViewById(R.id.eplv);

        eplv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                showToast("Group Clicked " + groupPosition);
                return false;
            }
        });
        eplv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                showToast("Child Clicked  group " + groupPosition + " child " + childPosition);
                return false;
            }
        });

        eplv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                showToast("Expand Clicked " + groupPosition);
            }
        });

        eplv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                showToast("Collapse Clicked " + groupPosition);
            }
        });

        fone.findViewById(R.id.info_layer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus bus = EventBus.getDefault();
                bus.post(new BottomLayoutEvent(true));
            }
        });
        return fone;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        eplv.setAdapter(new ExpandAdapter(dataList));
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void setMessage(Frag1ClickEvent event)
    {
        tv2.setText("" + event.msg1);
    }

    private void showToast(String msg)
    {
        Toast.makeText(getActivity().getBaseContext(),""+msg,Toast.LENGTH_SHORT).show();
    }


}
