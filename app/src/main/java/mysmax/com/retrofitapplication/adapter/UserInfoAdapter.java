package mysmax.com.retrofitapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.model.UserInfo;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserView> {

    private ArrayList<UserInfo> dataList;

    public UserInfoAdapter(ArrayList<UserInfo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclv_item,parent,false);
        return new UserView(view);
    }

    @Override
    public void onBindViewHolder(UserView holder, int position) {
         holder.userPosition.setText(dataList.get(position).getPosition());
         holder.userName.setText(dataList.get(position).getName());
         holder.userAddress.setText(dataList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class UserView extends RecyclerView.ViewHolder
    {
        TextView userName;
        TextView userAddress;
        TextView userPosition;

        public UserView(View itemView) {
            super(itemView);
            this.userName = itemView.findViewById(R.id.name);
            this.userPosition = itemView.findViewById(R.id.position);
            this.userAddress = itemView.findViewById(R.id.address);
        }
    }
}
