package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.documentmanagement.R;

public class AccountInformationActivity extends AppCompatActivity {
    EditText etHoTen, etSDT, etMail, etNgay, etDiaChi;
    Button btnCapNhat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);


    }

    private void mapping() {
        etHoTen = findViewById(R.id.edt_tk_ht);
        etSDT = findViewById(R.id.edt_tk_sdt);
        etMail = findViewById(R.id.edt_tk_em);
    }

}
