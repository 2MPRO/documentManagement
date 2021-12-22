package com.example.documentmanagement.Controllers.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.documentmanagement.Controllers.Activity.AddDocumentActivity;
import com.example.documentmanagement.Controllers.Activity.DocumentDetailActivity;
import com.example.documentmanagement.Controllers.Fragments.ApprovedReceiveFragment;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;

import java.util.ArrayList;

public class Document_Approved_Receive_Adapter extends BaseAdapter {

    ArrayList<Document> documentArrayList,documentArrayListTMP;
    ApprovedReceiveFragment activity;
    String btn_action;
    public Document_Approved_Receive_Adapter(ArrayList<Document> documentArrayList, ApprovedReceiveFragment activity, String btn_action) {
        this.documentArrayList = documentArrayList;
        this.activity = activity;
        this.btn_action = btn_action;
        this.documentArrayListTMP = documentArrayList;
    }
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchKey = constraint.toString();
                if(searchKey.isEmpty()){
                    documentArrayList = documentArrayListTMP;
                }
                else{
                    ArrayList<Document> documents = new ArrayList<>();
                    for(Document document : documentArrayListTMP){
                        if(document.getDocName().toLowerCase().contains(searchKey.toLowerCase())){
                            documents.add(document);
                        }
                    }
                    documentArrayList = documents;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = documentArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                documentArrayList = (ArrayList<Document>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getCount() {
        return documentArrayList.size();
    }
    private class ViewHolder{
        Button btnActionDoc,btnDelete;
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
            convertView = inflater.inflate(R.layout.item_document, null);
            viewHolder.linearLayout = convertView.findViewById(R.id.item_document);
            viewHolder.txtDocNum = convertView.findViewById(R.id.numDoc);
            viewHolder.txtDocName = convertView.findViewById(R.id.txtNameDoc);
            viewHolder.txtDocRoot = convertView.findViewById(R.id.rootDoc);
            viewHolder.txtHour = convertView.findViewById(R.id.txtHour);
            viewHolder.txtDate = convertView.findViewById(R.id.txtDate);
            viewHolder.btnActionDoc = convertView.findViewById(R.id.btnActionDoc);
            viewHolder.txtRecipients = convertView.findViewById(R.id.txtRecipients);
            viewHolder.btnDelete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Document document = documentArrayList.get(position);
        viewHolder.txtDocName.setText( document.getDocName());
        viewHolder.txtDocNum.setText( document.getDocNum());
        viewHolder.txtDocRoot.setText(document.getDocRoot());
        viewHolder.txtHour.setText(document.getHour());
        viewHolder.txtDate.setText(document.getDate());
        viewHolder.btnDelete.setVisibility(View.GONE);
        if(btn_action!="no"){
            viewHolder.btnActionDoc.setText(btn_action);
            if(btn_action!="Duyệt"){
                viewHolder.txtRecipients.setText("CQN: ");
                viewHolder.txtDocRoot.setText(document.getDocRoot2());
            }
        }
        else{
            viewHolder.btnActionDoc.setVisibility(View.INVISIBLE);
        }
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_action!="Gửi"){
                    Intent intent = new Intent(activity.getActivity(), DocumentDetailActivity.class);
                    intent.putExtra("document",document);
                    intent.putExtra("btn_action",btn_action);
                    activity.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(activity.getActivity(), AddDocumentActivity.class);
                    intent.putExtra("document",document);
                    activity.startActivity(intent);
                }
            }
        });

        viewHolder.btnActionDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

}
