package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.documentmanagement.R;

public class AddAccountActivity extends AppCompatActivity {

    EditText etHoTen, etMail, etMatKhau, etNhapLai;
    ImageView hide_1, hide_2;
    Button btnThemTK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        mapping();

    }

    private void mapping() {
        etHoTen = findViewById(R.id.etName);
        etMail = findViewById(R.id.etEmail);
        etMatKhau = findViewById(R.id.etPassword);
        etNhapLai = findViewById(R.id.etReneterPassword);
        hide_1 = findViewById(R.id.hide_password_1);
        hide_2 = findViewById(R.id.hide_password_re);

    }

}
