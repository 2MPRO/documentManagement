package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.documentmanagement.R;

public class PermissionActivity extends AppCompatActivity {
    ExpandableListView ExpandableListView_permission;
    ListView lvVanThu_Per, lvQuanTri_Per;
    Button btnLuu;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mapping();
        setToolbar();
        setButtonSave();

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
