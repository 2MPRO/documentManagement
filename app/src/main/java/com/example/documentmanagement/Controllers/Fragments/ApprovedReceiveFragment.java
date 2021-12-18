package com.example.documentmanagement.Controllers.Fragments;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.idRoom;
import static com.example.documentmanagement.Controllers.Activity.LoginActivity.iduser;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.Controllers.Adapter.Document_Adapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
       loaddata();
       return view;
    }

    private void loaddata() {
        documentArrayList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Log.e("DCMM :",Server.LinkshowApprovedReceive+"?idRoom="+idRoom);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, Server.LinkshowApprovedReceive+"?idRoom="+idRoom, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    String docId, docName, docNum, date, hour,docRoot;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        docId = jsonObject.getString("idVBN");
                        docName = jsonObject.getString("tenVB");
                        docNum = jsonObject.getString("soHieu");
                        date = jsonObject.getString("ngayBanHanh");
                        hour = jsonObject.getString("gioBanHanh");
                        docRoot = jsonObject.getString("tenPhongBan");
                        documentArrayList.add(new Document(docName,docNum,date,hour,docRoot));
                        approvedReceiveAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        });
            requestQueue.add(jsonArrayRequest);
    }

    private void setListView() {
        documentArrayList = new ArrayList<>();
        documentArrayList.add(new Document("s","s","s","s","s"));
        approvedReceiveAdapter = new Document_Adapter(documentArrayList,ApprovedReceiveFragment.this,"no");
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
