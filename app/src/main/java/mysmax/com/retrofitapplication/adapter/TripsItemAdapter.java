package mysmax.com.retrofitapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.model.TripsItem;

public class TripsItemAdapter extends BaseExpandableListAdapter {
    private ArrayList<TripsItem> dataList;

    public TripsItemAdapter(ArrayList<TripsItem> listModel) {
        this.dataList = listModel;
    }

    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataList.get(groupPosition).getEmployee().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).getEmployee().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        HeaderHolder headerHolder = null;
        if (convertView == null) {
            headerHolder = new HeaderHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent,false);

            headerHolder.tripID = convertView.findViewById(R.id.heading);

            convertView.setTag(headerHolder);

        } else {
            headerHolder = (HeaderHolder) convertView.getTag();
        }

        headerHolder.tripID.setText(dataList.get(groupPosition).getRouteId());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SectionHolder sectionHolder = null;
        if (convertView == null) {
            sectionHolder = new SectionHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_child, parent,false);

            sectionHolder.txtEmpname = convertView.findViewById(R.id.tv_empname);
            sectionHolder.txtEmpId = convertView.findViewById(R.id.tv_empid);
            sectionHolder.txtAddress = convertView.findViewById(R.id.tv_address);
            sectionHolder.txtPickUptime = convertView.findViewById(R.id.tv_pickuptime);

            convertView.setTag(sectionHolder);

        } else {
            sectionHolder = (SectionHolder) convertView.getTag();
        }

        sectionHolder.txtEmpname.setText(dataList.get(groupPosition).getEmployee().get(childPosition).getEmpName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class HeaderHolder {
        TextView tripID;

    }

    static class SectionHolder {

        public TextView txtEmpname;
        public TextView txtAddress;
        public TextView txtEmpId;
        public TextView txtPickUptime;

    }


}
