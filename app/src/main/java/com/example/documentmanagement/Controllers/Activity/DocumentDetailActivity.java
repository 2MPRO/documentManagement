package com.example.documentmanagement.Controllers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;
import com.example.documentmanagement.util.Server;

public class DocumentDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    Document document;
    Button btn_action;
    Button btnUpPdf;
    String btn_action_Text;
    TextView edit_Title, edit_content, spnRoot;
    TextView spinner, spnLevel, spnDocType, txtFilePdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_detail);
        mapping();
        setToolbar();
        getIntentData();
        loadData();
        setbtnOpenPdf();
        setbtnUpPdf();
    }

    private void setbtnUpPdf() {

    }

    private void setbtnOpenPdf() {
        txtFilePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentDetailActivity.this, ActivityViewPdf.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        txtFilePdf = findViewById(R.id.txtFilePdf);

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
       btn_action_Text = intent.getStringExtra("btn_action");
       btn_action.setText(btn_action_Text);
       if(btn_action_Text.equals("no")){
           btn_action.setVisibility(View.GONE);
       }
        if(btn_action_Text.equals("Thu hồi")){
            btn_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recall();
                }
            });
        }
        if(btn_action_Text.equals("Duyệt")){
            btn_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateConfirm();
                }


            });
        }
    }
    private void updateConfirm() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.LinkToConfirm + "?docNum=" + document.getDocNum(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("ok")){
                            Toast.makeText(getApplicationContext(),"Duyệt thành công !",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DocumentDetailActivity.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"querry erro", Toast.LENGTH_SHORT).show();
                            Log.e("Lẹnh sai : ",response.trim());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        } );
        requestQueue.add(stringRequest);
    }
    private void recall() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.LinkToRecall + "?docNum=" + document.getDocNum(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("ok")){
                            Toast.makeText(getApplicationContext(),"Thu hồi thành công !",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DocumentDetailActivity.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                        }

                        else{
                            Toast.makeText(getApplicationContext(),"querry erro", Toast.LENGTH_SHORT).show();
                            Log.e("Lẹnh sai : ",response.trim());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        } );

        requestQueue.add(stringRequest);
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