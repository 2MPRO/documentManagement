package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.documentmanagement.R;

public class DocumentDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail);
        mapping();
        setToolbar();
    }
    private void setToolbar() {
        toolbar.setTitle("Chi tiết văn bản");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }
    private void mapping() {
        toolbar = findViewById(R.id.toolbar);
    }

}