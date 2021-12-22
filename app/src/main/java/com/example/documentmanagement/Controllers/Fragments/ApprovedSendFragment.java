package com.example.documentmanagement.Controllers.Fragments;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.idRoom;

import android.os.Bundle;
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
import com.example.documentmanagement.Controllers.Adapter.Document_Approved_send_Adapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApprovedSendFragment  extends Fragment {
    private View view;
    private ArrayList<Document> documentArrayList;
    private Document_Approved_send_Adapter document_adapter;
    private SearchView search_send;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_body, container, false);
        initView();
        return view;
    }
    public void initView(){
        mapping();
        setListView();
        loaddata();
        setSearchView();
    }
    private void setSearchView() {
        search_send.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                document_adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                document_adapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    public void loaddata() {
        documentArrayList.clear();
        documentArrayList.removeAll(documentArrayList);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, Server.LinkshowApprovedSend+"?idRoom="+idRoom, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(documentArrayList.isEmpty()){
                for(int i= 0;i<response.length();i++){
                    String docId, docName, docNum, date, hour,docRoot;
                    String docRoot2, dinhKem,loaiVanBan, mucDo, noiDung;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        docId = jsonObject.getString("idVBD");
                        docName = jsonObject.getString("tenVB");
                        docNum = jsonObject.getString("soHieu");
                        date = jsonObject.getString("ngayBanHanh");
                        hour = jsonObject.getString("gioBanHanh");
                        docRoot = jsonObject.getString("tenPhongBan");
                        docRoot2 = jsonObject.getString("docRoot2");
                        dinhKem  = jsonObject.getString("dinhKem");
                        loaiVanBan = jsonObject.getString("loaiVanBan");
                        mucDo = jsonObject.getString("mucDo");
                        noiDung = jsonObject.getString("noiDung");

                        documentArrayList.add(new Document(docId,docName,docNum,date,hour,docRoot,docRoot2,dinhKem,loaiVanBan,mucDo,noiDung));
                        document_adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
        document_adapter.notifyDataSetChanged();
    }
    public void setListView() {
        documentArrayList = new ArrayList<>();
        document_adapter = new Document_Approved_send_Adapter(documentArrayList,ApprovedSendFragment.this,"Thu hồi");
        listView.setAdapter(document_adapter);
    }
    private void mapping() {
        search_send =  view.findViewById(R.id.search_send);
        listView = view.findViewById(R.id.listView_send);
    }

    @Override
    public void onResume() {
        loaddata();
        document_adapter.notifyDataSetChanged();
        super.onResume();
    }



}
