package com.example.documentmanagement.Controllers.Adapter;

import static com.example.documentmanagement.Controllers.Activity.AddDocumentActivity.idDoctype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Doctype;

import java.util.List;

public class DocTypeAdapter extends ArrayAdapter<Doctype> {

    public DocTypeAdapter(@NonNull Context context, int resource, @NonNull List<Doctype> objects) {
        super(context, resource, objects);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView txt_select_recipient =convertView.findViewById(R.id.txt_select_recipient);
        Doctype doctype = this.getItem(position);
        if(doctype!=null){
            idDoctype = doctype.getIdType();
            txt_select_recipient.setText(doctype.getTypeName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipients,parent,false);
        TextView txtRecipients =convertView.findViewById(R.id.txtRecipients);
        Doctype doctype = this.getItem(position);
        if(doctype!=null){
            idDoctype = doctype.getIdType();
            txtRecipients.setText(doctype.getTypeName());
        }

        return convertView;
    }
}
