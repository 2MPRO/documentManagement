package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.idRoom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.Controllers.Adapter.RoomAdapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;
import com.example.documentmanagement.model.Room;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddAccountActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etHoTen, etMail, etMatKhau, etNhapLai;
    ImageView hide_1, hide_2;
    Button btnThemTK;
    Spinner spnPhongBan;
    List<Room> listRoom = new ArrayList<>();
    private RoomAdapter roomAdapter;
    public boolean aBoolean;
    public Document document;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        mapping();
//        setToolbar();
        setRoomSpinner();
        loaddataRoom();
    }

    private void mapping() {
        etHoTen = findViewById(R.id.etName);
        etMail = findViewById(R.id.etEmail);
        etMatKhau = findViewById(R.id.etPassword);
        etNhapLai = findViewById(R.id.etReneterPassword);
        hide_1 = findViewById(R.id.hide_password_1);
        hide_2 = findViewById(R.id.hide_password_re);
        spnPhongBan = findViewById(R.id.spnPosition);
    }


//    private void setToolbar() {
//        toolbar.setTitle("Thêm Tài khoản");
//        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
//        setSupportActionBar(toolbar);
//    }

    private void setRoomSpinner() {
        roomAdapter = new RoomAdapter(this,R.layout.item_selected,listRoom);
        spnPhongBan.setAdapter(roomAdapter);
        spnPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


}
