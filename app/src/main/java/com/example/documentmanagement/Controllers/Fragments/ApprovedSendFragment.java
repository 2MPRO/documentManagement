package com.example.documentmanagement.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.documentmanagement.Controllers.Adapter.Document_Adapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;

import java.util.ArrayList;

public class ApprovedSendFragment  extends Fragment {
    private View view;
    private ArrayList<Document> documentArrayList;
    private Document_Adapter document_adapter;
    private SearchView search_send;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_body, container, false);
        mapping();
        setListView();
        return view;
    }
    private void setListView() {
        documentArrayList = new ArrayList<>();
        documentArrayList.add(new Document("s","s","s","s","s"));
        document_adapter = new Document_Adapter(documentArrayList,ApprovedSendFragment.this);
        listView.setAdapter(document_adapter);

    }
    private void mapping() {
        search_send =  view.findViewById(R.id.search_send);
        listView = view.findViewById(R.id.listView_send);
    }
}
