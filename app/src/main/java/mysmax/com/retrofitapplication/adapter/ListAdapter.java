package mysmax.com.retrofitapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mysmax.com.retrofitapplication.R;

public class ListAdapter extends BaseAdapter {
    //private Context context;
    private List<String> dataList;

    public ListAdapter(Context context, List<String> dataList) {
        //this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final MyViewHolder viewHolder;

        if (convertView ==  null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclv_item,parent,false);
            viewHolder = new MyViewHolder();
            viewHolder.label = view.findViewById(R.id.tv);

            view.setTag(viewHolder); // Set the object

        } else
        {
            viewHolder = (MyViewHolder) view.getTag(); // Get the object
        }

        viewHolder.label.setText(dataList.get(position));

        return view;
    }

    private static class MyViewHolder
    {
        TextView label;
    }
}
