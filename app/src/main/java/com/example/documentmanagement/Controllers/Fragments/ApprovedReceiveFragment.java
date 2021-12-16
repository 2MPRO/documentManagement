package com.example.documentmanagement.Controllers.Fragments;

import android.annotation.SuppressLint;
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

public class ApprovedReceiveFragment extends Fragment {
    private View view;
    private SearchView search_receive;
    private ListView listView;
    private ArrayList<Document> documentArrayList;
    private Document_Adapter approvedReceiveAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_receive_body, container, false);
       mapping();
       setListView();
       return view;
    }

    private void setListView() {
        documentArrayList = new ArrayList<>();
        documentArrayList.add(new Document("s","s","s","s","s"));
        approvedReceiveAdapter = new Document_Adapter(documentArrayList,ApprovedReceiveFragment.this);
        listView.setAdapter(approvedReceiveAdapter);

    }

    @SuppressLint("ResourceAsColor")
    public void mapping(){
        search_receive =  view.findViewById(R.id.search_receive);
        listView = view.findViewById(R.id.listView_receive);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("Đang on   :","fragment receive_approved");
    }
}
