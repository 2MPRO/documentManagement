package com.example.documentmanagement.Controllers.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.documentmanagement.R;

public class ApprovedSendFragment  extends Fragment {
    private View view;
    private SearchView search_send;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_body, container, false);
        mapping();
        return view;
    }

    private void mapping() {
        search_send =  view.findViewById(R.id.search_send);
        listView = view.findViewById(R.id.listView_send);
    }
}
