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
        TextView idDung, HoTen, idPass, idRoom, tenPhong, ngaySinh, gioiTinh, diaChi;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.idDung = (TextView) view.findViewById(R.id.txt_id_user);
            holder.HoTen = (TextView) view.findViewById(R.id.txt_ho_ten);
            holder.idPass = (TextView) view.findViewById(R.id.txt_mk);
            holder.idRoom = (TextView) view.findViewById(R.id.txt_id_room);
            holder.tenPhong = (TextView) view.findViewById(R.id.txt_ten_phong);
            holder.ngaySinh = (TextView) view.findViewById(R.id.txt_ngay);
            holder.gioiTinh = (TextView) view.findViewById(R.id.txt_gioi_tinh);
            holder.diaChi = (TextView) view.findViewById(R.id.txt_dia_chi);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        User user = userList.get(position);
        holder.idDung.setText(user.getIdUser());
        holder.HoTen.setText(user.getFullName());
        holder.idPass.setText(user.getPass());
        holder.idRoom.setText(user.getIdRoom());
        holder.tenPhong.setText(user.getRoomName());
        holder.ngaySinh.setText(user.getBirthDay());
        holder.gioiTinh.setText(user.getSex());
        holder.diaChi.setText(user.getDiaChi());

        return view;
    }
}
