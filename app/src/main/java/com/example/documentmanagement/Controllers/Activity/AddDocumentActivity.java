package com.example.documentmanagement.Controllers.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.documentmanagement.R;

public class AddDocumentActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);
        mapping();
        setToolbar();
    }
    private void setToolbar() {
        toolbar.setTitle("Thêm văn bản");
        setSupportActionBar(toolbar);

    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbar);
    }

}