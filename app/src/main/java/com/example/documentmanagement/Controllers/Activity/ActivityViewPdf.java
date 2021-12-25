package com.example.documentmanagement.Controllers.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.documentmanagement.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ActivityViewPdf extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        Mapping();
        getIntentData();

    }

    public void getIntentData(){
        Intent intent = getIntent();
        Uri pa = Uri.parse(intent.getStringExtra("path"));
        Uri path = pa;

        if(path != null){
            pdfView.fromAsset(retrieve(ActivityViewPdf.this.getContentResolver(),path));
         }
    }

    public static String retrieve(ContentResolver resolver, Uri uri)
    {
        if (uri.getScheme().equals("pdf"))
        {
            return uri.getPath();
        }
        final Cursor cursor = resolver.query(uri, new String[]{"_data"}, null, null, null);
        if (cursor.moveToFirst())
        {
            return cursor.getString(0);
        }
        throw new RuntimeException("Can't retrieve path from uri: " + uri.toString());
    }
    private void Mapping() {
        pdfView = findViewById(R.id.PdfView);
    }

}