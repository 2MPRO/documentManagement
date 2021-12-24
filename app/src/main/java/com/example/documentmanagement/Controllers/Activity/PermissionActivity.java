package com.example.documentmanagement.Controllers.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.Controllers.Adapter.ExpandableListviewAdapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Per_room;
import com.example.documentmanagement.model.Permission;
import com.example.documentmanagement.model.Room;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionActivity extends AppCompatActivity {
    ExpandableListView ExpandableListView_permission ;
    ExpandableListviewAdapter expandableListviewAdapter;
    private Map<Room, List<Permission>> listPermissions ;
    private ArrayList<Room> roomArrayList;
    private ArrayList<Room>  roomGroup ;
    private ArrayList<Permission> lisItemPermission;
    private ArrayList<Per_room> listRoom_Permission;
    public static ArrayList<Per_room> saveChange  = new ArrayList<>();

    Button btnLuu;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mapping();
        setToolbar();
        setButtonSave();
        setArrayList();
        setlistPermissions();
        setClickPermisson();
    }

    private void setClickPermisson() {
        ExpandableListView_permission.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), "1 you ok?", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void setArrayList() {
        Log.d("xuat h ","1");
        roomArrayList = new ArrayList<>();
        setRoomArrayList();
        lisItemPermission = new ArrayList<>();
        setlisItemPermission();

        listRoom_Permission = new ArrayList<>();


        setlistRoom_Permission();
    }

    private void setlistPermissions() {
        Log.d("xuat hie ","2");
        listPermissions = getListPermissions();
        roomGroup =  new ArrayList<>(listPermissions.keySet());
        expandableListviewAdapter = new ExpandableListviewAdapter(roomGroup,listPermissions,listRoom_Permission);
        ExpandableListView_permission.setAdapter(expandableListviewAdapter);
        for (int i = 0; i < expandableListviewAdapter.getGroupCount(); i++)
            ExpandableListView_permission.expandGroup(i);

    }

    @NonNull
    private Map<Room,List<Permission>> getListPermissions(){

        Map<Room, List<Permission>> listPermission = new HashMap<>();

        for(int i=0; i<roomArrayList.size();i++){
            Room room = roomArrayList.get(i);
            listPermission.put(room,lisItemPermission);
        }
        return  listPermission;
    }
    private void setRoomArrayList(){
        Log.d("xuat hien ","1.5");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.LinkgetRoomGroup, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i= 0;i<response.length();i++){
                    String idRoom, roomName;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idRoom = jsonObject.getString("idPhongBan").trim();
                        roomName = jsonObject.getString("tenPhongBan").trim();
                        roomArrayList.add(new Room(idRoom,roomName));
                        setlistPermissions();
                        expandableListviewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("Phia A",String.valueOf( roomArrayList.size()));
        requestQueue.add(jsonArrayRequest);
    }
    private void setlisItemPermission(){

        lisItemPermission.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.LinkgetPermission, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i= 0;i<response.length();i++){
                    String idPermission, permissionName, idRoom;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idPermission = jsonObject.getString("idQuyen").trim();
                        permissionName = jsonObject.getString("tenQuyen").trim();
                        lisItemPermission.add(new Permission(idPermission,permissionName));
                        expandableListviewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void setlistRoom_Permission(){

        listRoom_Permission.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.LinkgetRoom_Permission, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i= 0;i<response.length();i++){
                    String idPer_room, idPermission, permissionName, idRoom;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idPer_room = jsonObject.getString("idPhanQuyen").trim();
                        idPermission = jsonObject.getString("idQuyen").trim();
                        permissionName = jsonObject.getString("tenQuyen").trim();
                        idRoom = jsonObject.getString("idRoom").trim();
                        listRoom_Permission.add(new Per_room(idPer_room,idPermission,idRoom));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Looix cho :"+ error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void setToolbar() {
        toolbar.setTitle("Phân quyền");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }
    private void setButtonSave() {

        btnLuu.setOnClickListener(new View.OnClickListener() {

            Boolean isRemove = false;
            @Override
            public void onClick(View view) {


                if(0==0){
                    Log.d("chieudai",String.valueOf( saveChange.size()));
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkInsertTablePermisson,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.trim().equals("success")) {
                                            Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Log.d("aaaaaaa",response.toString());
                                            Toast.makeText(getApplicationContext(), "lose", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                JSONArray jsonArray = new JSONArray();
                                for(int index=0;index<saveChange.size();index++){
                                    Per_room permission = saveChange.get(index);
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("idRoom", permission.getIdRoom().trim());
                                        jsonObject.put("IdPermission", permission.getIdPer().trim());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArray.put(jsonObject);
                                }
                                HashMap<String, String> param = new HashMap<>();
                                param.put("json",jsonArray.toString());
                                return param;
                            }
                        };
                        requestQueue.add(stringRequest);
                        Log.d("----", "--------------------------------");
                    }
                }


        });
        saveChange.clear();
    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbar);
        ExpandableListView_permission = findViewById(R.id.ExpandableListView_permission);
        btnLuu = findViewById(R.id.btn_Luu);
    }

}
