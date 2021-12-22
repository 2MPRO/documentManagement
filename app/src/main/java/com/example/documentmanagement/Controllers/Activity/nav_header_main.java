package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.documentmanagement.R;

public class nav_header_main extends AppCompatActivity {
    TextView userName, txtRoom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        userName = findViewById(R.id.userName);
        txtRoom = findViewById(R.id.txtRoom);
        userName.setText("use.getFullName()");
        txtRoom.setText("use.getRoomName()");
    }
}
