package com.example.documentmanagement.Controllers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.documentmanagement.R;

public class PermissionActivity extends AppCompatActivity {
    ListView lvVanThu_Per, lvQuanTri_Per;
    Button btnLuu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mapping();
        ButtonSave();

    }

    private void ButtonSave() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update phân quyền
            }
        });
    }

    private void mapping() {
        lvVanThu_Per = findViewById(R.id.lv_VanThu_Per);
        lvQuanTri_Per = findViewById(R.id.lv_QuanTri_Per);
        btnLuu = findViewById(R.id.btn_Luu);
    }

}
