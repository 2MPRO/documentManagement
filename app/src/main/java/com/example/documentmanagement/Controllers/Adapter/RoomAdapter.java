package com.example.documentmanagement.Controllers.Adapter;

import static com.example.documentmanagement.Controllers.Activity.AddDocumentActivity.idRecipient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Room;

import java.util.List;

public class RoomAdapter extends ArrayAdapter<Room> {

    public RoomAdapter(@NonNull Context context, int resource, @NonNull List<Room> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView txt_select_recipient =convertView.findViewById(R.id.txt_select_recipient);
        Room room = this.getItem(position);
        if(room!=null){
            txt_select_recipient.setText(room.getRoomName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipients,parent,false);
        TextView txtRecipients =convertView.findViewById(R.id.txtRecipients);
        Room room = this.getItem(position);
        if(room!=null){
            idRecipient = room.getIdRoom();
            txtRecipients.setText(room.getRoomName());
        }

        return convertView;
    }
}
