package com.example.documentmanagement.Controllers.Adapter;

import static com.example.documentmanagement.Controllers.Activity.AddDocumentActivity.idLevel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Level;

import java.util.List;

public class LevelAdapter extends ArrayAdapter<Level> {

    public LevelAdapter(@NonNull Context context, int resource, @NonNull List<Level> objects) {
        super(context, resource, objects);
    }

    @NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView txt_select_recipient =convertView.findViewById(R.id.txt_select_recipient);
        Level level = this.getItem(position);
        if(level!=null){
            idLevel = level.getIdLevel();
            txt_select_recipient.setText(level.getLevelName());
        }
        return convertView;
        }

@Override
public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipients,parent,false);
        TextView txtRecipients =convertView.findViewById(R.id.txtRecipients);
            Level level = this.getItem(position);
        if(level!=null){
            idLevel = level.getIdLevel();
            txtRecipients.setText(level.getLevelName());
        }

        return convertView;
        }
}
