package com.example.documentmanagement.Controllers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;

public class DocumentDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    Document document;
    Button btn_action;
    TextView edit_Title, edit_content, spnRoot;
    TextView spinner, spnLevel, spnDocType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail);
        mapping();
        setToolbar();
        getIntentData();
        loadData();
    }



    private void mapping() {
        toolbar = findViewById(R.id.toolbar);
        spinner = findViewById(R.id.spnRecipients);
        spnLevel = findViewById(R.id.spnLevel);
        spnDocType = findViewById(R.id.spnDocType);
        btn_action = findViewById(R.id.btn_action);
        spnRoot = findViewById(R.id.spnRoot);
        edit_content = findViewById(R.id.edit_content);
        edit_Title = findViewById(R.id.edit_Title);
    }
    private void setToolbar() {
        toolbar.setTitle("Chi tiết văn bản");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }
    private void getIntentData() {
       Intent intent = getIntent();
       document = (Document) intent.getSerializableExtra("document");
    }
    private void loadData() {
        spinner.setText(document.getDocRoot2());
        spnRoot.setText(document.getDocRoot());
        spnLevel.setText(document.getMucDo());
        spnDocType.setText(document.getLoaiVanBan());
        edit_content.setText(document.getNoiDung());
        edit_Title.setText(document.getDocName());
    }



}