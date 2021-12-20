package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.idRoom;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import com.example.documentmanagement.Controllers.Adapter.DocTypeAdapter;
import com.example.documentmanagement.Controllers.Adapter.LevelAdapter;
import com.example.documentmanagement.Controllers.Adapter.RoomAdapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Doctype;
import com.example.documentmanagement.model.Document;
import com.example.documentmanagement.model.Level;
import com.example.documentmanagement.model.Room;
import com.example.documentmanagement.util.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDocumentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    EditText edit_Title, edit_content;
    Spinner spinner, spnLevel, spnDocType;
    Button btnSend, btnSaveTmp;
    List<Room> listRoom = new ArrayList<>();
    List<Doctype> listDoctype = new ArrayList<>();
    List<Level> listLevel = new ArrayList<>();
    private RoomAdapter roomAdapter;
    private LevelAdapter levelAdapter;
    private DocTypeAdapter docTypeAdapter;
    private String date;
    private String hour;
    public static String idRecipient; // id nơi đến đã chọn trong combobox
    public static String idLevel;
    public static String idDoctype;
    public boolean aBoolean;
    public Document document;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);
        mapping();
        setToolbar();
        setRoomSpinner();
        setDocTypeSpinner();
        setLevelSpinner();

        //kiểm tra tồn tại dữ liệu truyền qua Intent
        if(isExistIntent()){
            aBoolean = true;
        }
        else
        {
            aBoolean = false;
        };
        Log.d("aBoolean",isExistIntent().toString());

        loaddataRoom();
        loaddataDocType();
        loaddataLevel();
        setBtnSend();

    }

    private Boolean isExistIntent() {
        Intent intent = getIntent();
        document = (Document) intent.getSerializableExtra("document");
        if(document!=null){
            edit_Title.setText(document.getDocName());
            edit_content.setText(document.getNoiDung());
            return  true;

        }
        return false;
    }


    private void mapping() {
        spinner = findViewById(R.id.spnRecipients);
        spnLevel = findViewById(R.id.spnLevel);
        spnDocType = findViewById(R.id.spnDocType);
        toolbar = findViewById(R.id.toolbar);
        btnSend = findViewById(R.id.btnSend);
        btnSaveTmp = findViewById(R.id.btnSavetmp);
        edit_content = findViewById(R.id.edit_content);
        edit_Title = findViewById(R.id.edit_Title);
    }
    private void setToolbar() {
        toolbar.setTitle("Thêm văn bản");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }
    private void setRoomSpinner() {
        roomAdapter = new RoomAdapter(this,R.layout.item_selected,listRoom);
        spinner.setAdapter(roomAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setDocTypeSpinner() {
        docTypeAdapter = new DocTypeAdapter(this,R.layout.item_selected,listDoctype);
        spnDocType.setAdapter(docTypeAdapter);
        spnDocType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setLevelSpinner() {
        levelAdapter = new LevelAdapter(this,R.layout.item_selected,listLevel);
        spnLevel.setAdapter(levelAdapter);
        spnLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void loaddataRoom() {
        listRoom.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Log.e("DCMM :", Server.LinkShowAllRom+"?idRoom="+idRoom);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, Server.LinkShowAllRom+"?idRoom="+idRoom, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i= 0;i<response.length();i++){
                    String idRoom, roomName;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idRoom = jsonObject.getString("idPhongBan");
                        roomName = jsonObject.getString("tenPhongBan");
                        if(aBoolean && roomName.equals(document.getDocRoot2())){
                            listRoom.add(0,new Room(idRoom,roomName));
                            Log.d("document : ",document.getDocRoot2());
                        }
                        else
                        {
                            listRoom.add(new Room(idRoom,roomName));
                        }

                        roomAdapter.notifyDataSetChanged();
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
    private void loaddataDocType() {
        listRoom.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Log.e("DCMM :", Server.LinkShowAllDocType);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, Server.LinkShowAllDocType, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i= 0;i<response.length();i++){
                    String idDocType, typeName;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idDocType = jsonObject.getString("idLVB");
                        typeName = jsonObject.getString("tenLoai");
                        if(aBoolean && typeName.equals(document.getLoaiVanBan())){
                            listDoctype.add(0,new Doctype(idDocType,typeName));
                        }
                        else
                        {
                            listDoctype.add(new Doctype(idDocType,typeName));
                        }

                        docTypeAdapter.notifyDataSetChanged();
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
    private void loaddataLevel() {
        listLevel.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Log.e("DCMM :", Server.LinkShowAllLevel);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, Server.LinkShowAllLevel, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i= 0;i<response.length();i++){
                    String idLevel, levelName;
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        idLevel = jsonObject.getString("idMucDo");
                        levelName = jsonObject.getString("tenMucDo");
                        if(aBoolean && levelName.equals(document.getMucDo())){
                            listLevel.add(0,new Level(idLevel,levelName));
                        }
                        else
                        {
                            listLevel.add(new Level(idLevel,levelName));
                        }
                        levelAdapter.notifyDataSetChanged();

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
    public void setBtnSend() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                getdatetimeCurrent();
                insertData();

            }
        });
    }

    public void  insertData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkinsertDoccument,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getApplicationContext(), "Đã thêm vào văn bản đi", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                            Log.e("queeyyy", response);
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
                Map<String, String> param = new HashMap<>();
                param.put("idPhongBan", idRoom);
                param.put("idNoiDen", idRecipient);
                param.put("idLoaiVanBan", idDoctype);
                param.put("idMucDo", idLevel);
                param.put("noiDung", edit_content.getText().toString().trim());
                param.put("ngayBanHanh", date);
                param.put("gioBanHanh", hour);
                param.put("tenVanBan", edit_Title.getText().toString().trim());
                if(aBoolean){
                    param.put("acTion", document.getDocId());
                }
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void  getdatetimeCurrent(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        date = String.valueOf(dayofmonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
        hour = String.valueOf(hourofday) + ":" + String.valueOf(minute);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}