package mysmax.com.retrofitapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.model.UserInfo;

public class RclvAdapter extends RecyclerView.Adapter<RclvAdapter.RcViewHolder> {

    //Context context;
    List<UserInfo> dataList;

    public RclvAdapter(Context context, List<UserInfo> dataList) {
       //this.context = context;
       this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public RclvAdapter.RcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclv_item,parent,false);
        return new RcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RcViewHolder holder, int position) {
        if (position % 2 == 0)
        {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#DCDCDC"));
        }
        holder.positon.setText(dataList.get(position).getPosition());
        holder.name.setText(dataList.get(position).getName());
        holder.address.setText(dataList.get(position).getAddress());
    }

    public static class RcViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout itemLayout;
        public TextView name;
        public TextView address;
        public TextView positon;

        public RcViewHolder(View itemView) {
            super(itemView);
            this.itemLayout = itemView.findViewById(R.id.item);
            this.name = itemView.findViewById(R.id.name);
            this.address = itemView.findViewById(R.id.address);
            this.positon = itemView.findViewById(R.id.position);
        }
    }

}
