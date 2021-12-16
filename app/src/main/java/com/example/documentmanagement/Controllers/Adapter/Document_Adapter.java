package com.example.documentmanagement.Controllers.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;

import java.util.ArrayList;

public class Document_Adapter extends BaseAdapter {
    ArrayList<Document> documentArrayList;
    Fragment activity;

    public Document_Adapter(ArrayList<Document> documentArrayList, Fragment activity) {
        this.documentArrayList = documentArrayList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return documentArrayList.size();
    }
    private class ViewHolder{
        TextView txtDocName, txtDocNum, txtDocRoot, txtDate, txtHour;
        LinearLayout linearLayout;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater =activity.getLayoutInflater();
            convertView =inflater.inflate(R.layout.item_document, null);
            viewHolder.linearLayout = convertView.findViewById(R.id.item_document);
            viewHolder.txtDocNum = convertView.findViewById(R.id.numDoc);
            viewHolder.txtDocName = convertView.findViewById(R.id.txtNameDoc);
            viewHolder.txtDocRoot = convertView.findViewById(R.id.rootDoc);
            viewHolder.txtHour = convertView.findViewById(R.id.txtHour);
            viewHolder.txtDate = convertView.findViewById(R.id.txtDate);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Document document =documentArrayList.get(position);
        viewHolder.txtDocName.setText( document.getDocName());
        viewHolder.txtDocRoot.setText(document.getDocRoot());
        viewHolder.txtHour.setText(document.getHour());
        viewHolder.txtDate.setText(document.getDate());
        return convertView;
    }
}