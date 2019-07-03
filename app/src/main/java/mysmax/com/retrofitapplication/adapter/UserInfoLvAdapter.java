package mysmax.com.retrofitapplication.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.model.UserInfo;

public class UserInfoLvAdapter extends BaseAdapter {

    ArrayList<UserInfo> infoList;

    public UserInfoLvAdapter(ArrayList<UserInfo> infoList) {
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclv_item,parent,false);
        UserView holder = new UserView();
        holder.layer = convertView.findViewById(R.id.item);
        holder.userName = convertView.findViewById(R.id.name);
        holder.userAddress = convertView.findViewById(R.id.address);
        holder.userPosition = convertView.findViewById(R.id.position);

        holder.userName.setText(infoList.get(position).getName());
        holder.userPosition.setText(infoList.get(position).getPosition());
        holder.userAddress.setText(infoList.get(position).getAddress());

        if (position % 2 == 0)
        {
            holder.layer.setBackgroundColor(Color.parseColor("#50DCDCDC"));
        }

        return convertView;
    }

    static class UserView
    {
        // Widgets
        RelativeLayout layer;
        TextView userName;
        TextView userPosition;
        TextView userAddress;
    }
}
