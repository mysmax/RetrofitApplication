package mysmax.com.retrofitapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.adapter.TripsItemAdapter;
import mysmax.com.retrofitapplication.model.TripsItem;

public class Trips extends Fragment {

    private ExpandableListView epView;
    //private TripsAdapter adapter;
    //private ArrayList<TripInfo> tripInfoList;
    private ArrayList<TripsItem> tripItem;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Check Size
        //tripInfoList = new ArrayList<>(); // Trip Data Holder
        tripItem = new ArrayList<>(); // Trip Data Holder
        //ArrayList tripInfoSheet = TripContainer.tripInfoList.getTripInofList(); // Trip Data Holder
        //Log.e("TripInfoSheet", "Size: " + tripInfoSheet.size());
        for (int i = 0; i < 5; i++) {
            //TripInfo tripInfo = (TripInfo) tripInfoSheet.get(i);
            //TripInfo info = new TripInfo();
            TripsItem item = new TripsItem();
            item.setRouteId("Route" + i);
            ArrayList<TripsItem.EmployeeDetails> empList = new ArrayList<>();
            for (int j= 0; j<5; j++)
            {
                TripsItem.EmployeeDetails details = new TripsItem.EmployeeDetails();
                details.setEmpAddress("Address " + j);
                details.setEmpId("ID-"+j);
                details.setEmpName("Name " + j);
                details.setPickupTime("12.3"+j);

                empList.add(details);
            }
            item.setEmployee(empList);

            tripItem.add(item);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips,container,false);

        epView = view.findViewById(R.id.eplv);

        epView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //CommonAlertDialogUtils.showToast(getActivity(),"Expand Clicked " + groupPosition);
                showToast("Group Clicked " + groupPosition);
                return false;
            }
        });
        epView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //CommonAlertDialogUtils.showToast(getActivity(),"Child Clicked " + childPosition);
                showToast("Child Clicked " + childPosition);
                return false;
            }
        });
        epView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //CommonAlertDialogUtils.showToast(getActivity(),"Expand " + groupPosition);
                showToast("Expand " + groupPosition);
            }
        });
        epView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //CommonAlertDialogUtils.showToast(getActivity(),"Expand " + groupPosition);
                showToast("Collapse " + groupPosition);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //adapter = new TripsAdapter(getActivity().getBaseContext(), tripInfoList);
        //epView.setAdapter(adapter);
        epView.setAdapter(new TripsItemAdapter(tripItem));
    }

    private void showToast(String msg)
    {
        Toast.makeText(getActivity().getBaseContext(),""+msg,Toast.LENGTH_SHORT).show();
    }


}
