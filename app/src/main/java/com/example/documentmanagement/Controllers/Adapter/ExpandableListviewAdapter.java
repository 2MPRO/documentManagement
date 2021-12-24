package com.example.documentmanagement.Controllers.Adapter;

import static com.example.documentmanagement.Controllers.Activity.PermissionActivity.saveChange;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Per_room;
import com.example.documentmanagement.model.Permission;
import com.example.documentmanagement.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpandableListviewAdapter extends BaseExpandableListAdapter {

    ArrayList<Room> romArrayList;
    private Map<Room, List<Permission>> listPermissions;
    ArrayList<Per_room> listRoom_Permission;

    public ExpandableListviewAdapter(ArrayList<Room> romArrayList, Map<Room,List<Permission>> listPermissions, ArrayList<Per_room> listRoom_Permission) {
        this.romArrayList = romArrayList;
        this.listPermissions = listPermissions;
        this.listRoom_Permission = listRoom_Permission;
        saveChange.clear();
    }

    @Override
    public int getGroupCount() {
        if(romArrayList!=null){
            return romArrayList.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(romArrayList != null && listPermissions != null){
            return listPermissions.get(romArrayList.get(groupPosition)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return romArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listPermissions.get(romArrayList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        Room room = romArrayList.get(groupPosition);
        return Integer.valueOf(room.getIdRoom().trim());
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Permission permission = listPermissions.get(romArrayList.get(groupPosition)).get(childPosition);
        return Integer.valueOf( permission.getIdPermission());
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rule_group,parent, false);


        }
        TextView txtRuleRoom = convertView.findViewById(R.id.txtRuleRoom);
        Room room = romArrayList.get(groupPosition);
        txtRuleRoom.setText(room.getRoomName().toUpperCase());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rule,parent, false);
        }
        CheckBox checkBoxRule = convertView.findViewById(R.id.checkbox_rule);
        Room room = romArrayList.get(groupPosition);
        Permission permission = listPermissions.get(romArrayList.get(groupPosition)).get(childPosition);


        for(int i =0;i<listRoom_Permission.size();i++){
           Per_room room_per = listRoom_Permission.get(i);
            if(permission.getIdPermission().equals(room_per.getIdPer()) && room_per.getIdRoom().equals(room.getIdRoom()))
            {
                checkBoxRule.setChecked(true);
                if(!saveChange.contains(room_per)){
                    saveChange.add(room_per);
                    Log.d("KhoiTao","Chenvaomang id Phòng : "+room_per.getIdRoom()+room_per.getIdPer());
                }
            }
        }
        Log.d("KhoiTao","chìu dài mảng "+saveChange.size());
        for(int j=0;j<saveChange.size();j++){
            Per_room prom = saveChange.get(j);
            Log.d("Xuát mang","idPerrom =   "+prom.getIdPer_room()+"  : idPhong "+prom.getIdRoom()+ "  id_Quyen"+prom.getIdPer());
        }
        checkBoxRule.setText(permission.getPermissionName());
        checkBoxRule.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Permission permission = listPermissions.get(romArrayList.get(groupPosition)).get(childPosition);

                if(checkBoxRule.isChecked()==true){
                    Log.d("thienQuyenTrue", "chèn vào"+"IDPerRoom "+permission.getIdPer_room() + "Quyền : "+permission.getPermissionName()+room.getIdRoom()+room.getRoomName());
                    saveChange.add(new Per_room(permission.getIdPermission()+room.getIdRoom(), permission.getIdPermission(),room.getIdRoom()));
                }
                else if(checkBoxRule.isChecked()==false){
                         Log.d("falseTrc xóa","chiều dài"+saveChange.size()+"IDPer_room ="+permission.getIdPer_room());
                         if((saveChange = isRemove(saveChange,room.getIdRoom(),permission.getIdPermission())).size()>0){
                                     Log.d("falseSau xóa","chiều dài"+saveChange.size());
                         }
                     }
                     for(int j=0;j<saveChange.size();j++){
                            Per_room prom = saveChange.get(j);
                            Log.d("Inmang","idPerrom =   "+prom.getIdPer_room()+j+"  : idPhong "+prom.getIdRoom()+ "  id_Quyen"+prom.getIdPer());
                     }
            }


        });
        return convertView;
    }
    private ArrayList<Per_room> isRemove(ArrayList<Per_room> peroms,String idRoom, String idPer){
        for(int i=0;i<peroms.size();i++){
            Per_room permission = peroms.get(i);
                if((permission.getIdRoom().equals(idRoom.trim()))&& (permission.getIdPer().equals(idPer))){
                    peroms.remove(i);
                }
        }
        return peroms;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
