package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.use;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.documentmanagement.R;

public class AccountInformationActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etHoTen, etGioiTinh, etMail, etNgay, etDiaChi;
    Button btnCapNhat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_information);
        mapping();
        setToolbar();
        GetAccount();
    }

    private void mapping() {
        etHoTen = findViewById(R.id.edt_tk_ht);
        etGioiTinh = findViewById(R.id.edt_tk_gt);
        etMail = findViewById(R.id.edt_tk_em);
        etNgay = findViewById(R.id.edt_tk_ngsinh);
        etDiaChi = findViewById(R.id.edt_tk_dchi);
        btnCapNhat = findViewById(R.id.btnUpdate);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setToolbar() {
        toolbar.setTitle("Thông tin cá nhân");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }

    private void GetAccount() {
        etHoTen.setText(use.getFullName());
        etGioiTinh.setText(use.getSex());
        etMail.setText(use.getBirthDay());
        etNgay.setText(use.getPass());
        etDiaChi.setText(use.getDiaChi());
    }

}
