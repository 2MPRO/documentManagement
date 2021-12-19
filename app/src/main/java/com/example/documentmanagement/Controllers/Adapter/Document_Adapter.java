package com.example.documentmanagement.Controllers.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.documentmanagement.Controllers.Activity.DocumentDetailActivity;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;

import java.util.ArrayList;

public class Document_Adapter extends BaseAdapter {

    ArrayList<Document> documentArrayList;
    Fragment activity;
    String btn_action;
    public Document_Adapter(ArrayList<Document> documentArrayList, Fragment activity,String btn_action) {
        this.documentArrayList = documentArrayList;
        this.activity = activity;
        this.btn_action = btn_action;
    }

    @Override
    public int getCount() {
        return documentArrayList.size();
    }
    private class ViewHolder{
        Button btnActionDoc;
        TextView txtDocName, txtDocNum, txtDocRoot, txtDate, txtHour,txtRecipients;
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
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView =inflater.inflate(R.layout.item_document, null);
            viewHolder.linearLayout = convertView.findViewById(R.id.item_document);
            viewHolder.txtDocNum = convertView.findViewById(R.id.numDoc);
            viewHolder.txtDocName = convertView.findViewById(R.id.txtNameDoc);
            viewHolder.txtDocRoot = convertView.findViewById(R.id.rootDoc);
            viewHolder.txtHour = convertView.findViewById(R.id.txtHour);
            viewHolder.txtDate = convertView.findViewById(R.id.txtDate);
            viewHolder.btnActionDoc = convertView.findViewById(R.id.btnActionDoc);
            viewHolder.txtRecipients = convertView.findViewById(R.id.txtRecipients);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getActivity(), DocumentDetailActivity.class);
                activity.startActivity(intent);
            }
        });
        Document document =documentArrayList.get(position);
        viewHolder.txtDocName.setText( document.getDocName());
        viewHolder.txtDocNum.setText( document.getDocNum());
        viewHolder.txtDocRoot.setText(document.getDocRoot());
        viewHolder.txtHour.setText(document.getHour());
        viewHolder.txtDate.setText(document.getDate());
        if(btn_action!="no"){
            viewHolder.btnActionDoc.setText(btn_action);
            if(btn_action!="Duyệt"){
                viewHolder.txtRecipients.setText("CQN: ");
            }

        }
        else{
            viewHolder.btnActionDoc.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }


}
