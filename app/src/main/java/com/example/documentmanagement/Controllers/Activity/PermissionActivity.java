package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Permission;
import com.example.documentmanagement.model.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionActivity extends AppCompatActivity {
    ExpandableListView ExpandableListView_permission;
    private ArrayList<Room> roomArrayList;
    private Map<Room, List<Permission>> listPermissions;
    Button btnLuu;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mapping();
        setRoomArrayList();
        setToolbar();
        setButtonSave();

    }
    private void setRoomArrayList(){
        roomArrayList = new ArrayList<>();

    }
    private Map<Room, List<Permission>> getListPermissions(){
        Map<Room, List<Permission>> listPermission = new HashMap<>();
        Room room = new Room();
        return  listPermissions;
    }
    private void  setListView(){

    }

    private void setToolbar() {
        toolbar.setTitle("Phân quyền");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }
    private void setButtonSave() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update phân quyền
            }
        });
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbar);
        ExpandableListView_permission = findViewById(R.id.ExpandableListView_permission);

        btnLuu = findViewById(R.id.btn_Luu);
    }

}
