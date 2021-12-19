package com.example.documentmanagement.Controllers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.documentmanagement.R;

public class AccountManagerActivity extends AppCompatActivity {

    ListView lvQuanTri, lvVanThu;
    Button btnThem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);
        mapping();
        ButtonThemMoi();

    }

    private void ButtonThemMoi() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountManagerActivity.this, AddAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        lvQuanTri = findViewById(R.id.lv_QuanTri_Acc);
        lvVanThu = findViewById(R.id.lv_VanThu_Acc);
        btnThem = findViewById(R.id.btn_ThemMoi);
    }

}
