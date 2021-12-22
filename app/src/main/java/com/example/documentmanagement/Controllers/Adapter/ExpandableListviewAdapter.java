package com.example.documentmanagement.Controllers.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Permission;
import com.example.documentmanagement.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpandableListviewAdapter extends BaseExpandableListAdapter {

    ArrayList<Room> romArrayList;
    private Map<Room, List<Permission>> listPermissions;

    public ExpandableListviewAdapter(ArrayList<Room> romArrayList, Map<Room, List<Permission>> listPermissions) {
        this.romArrayList = romArrayList;
        this.listPermissions = listPermissions;
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

            TextView txtRuleRoom = convertView.findViewById(R.id.txtRuleRoom);
            Room room = romArrayList.get(groupPosition);
            txtRuleRoom.setText(room.getRoomName());
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rule,parent, false);
            CheckBox checkBoxRule = convertView.findViewById(R.id.checkbox_rule);
            Permission permission = listPermissions.get(romArrayList.get(groupPosition)).get(childPosition);
            checkBoxRule.setText(permission.getPermissionName());

        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
