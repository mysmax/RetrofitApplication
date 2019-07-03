package mysmax.com.retrofitapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.model.ExpandInfoList;

public class ExpandAdapter extends BaseExpandableListAdapter {
    ArrayList<ExpandInfoList> data;

    public ExpandAdapter(ArrayList<ExpandInfoList> data) {
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getChildrenList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition).getGroup();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChildrenList().get(childPosition);
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

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item,parent,false);
        HeaderView  hv = new HeaderView();
        hv.title = convertView.findViewById(R.id.heading);
        hv.title.setText(data.get(groupPosition).getGroup().getTitle());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
        ChildViews cv = new ChildViews();
        cv.childName = convertView.findViewById(R.id.child);
        cv.childName.setText(data.get(groupPosition).getChildrenList().get(childPosition).getChildName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class HeaderView
    {
        TextView title;
    }

    static class ChildViews
    {
        TextView childName;
    }
}
