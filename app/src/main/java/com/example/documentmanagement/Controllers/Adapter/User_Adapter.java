package com.example.documentmanagement.Controllers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class User_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<User> userList;

    public User_Adapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView HoTen, tenPhong;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.HoTen = (TextView) view.findViewById(R.id.txt_hienthi_ten);

            holder.tenPhong = (TextView) view.findViewById(R.id.txt_hienthi_phong);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        User user = userList.get(position);
        holder.HoTen.setText(user.getFullName());
        holder.tenPhong.setText(user.getRoomName());

        return view;
    }
}
