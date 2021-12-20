package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.use;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.documentmanagement.R;

public class AccountInformationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uuu = use.get
        setContentView(R.layout.activity_account_information);
    }
}
