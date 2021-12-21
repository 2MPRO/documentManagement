package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.idRoom;
import static com.example.documentmanagement.Controllers.Activity.LoginActivity.iduser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.Controllers.Adapter.Document_Adapter;
import com.example.documentmanagement.Controllers.Adapter.User_Adapter;
import com.example.documentmanagement.Controllers.Fragments.ApprovedSendFragment;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.User;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountManagerActivity extends AppCompatActivity {

    ArrayList<User> listUser;
    User_Adapter adapter;

    ListView lv_DS_TaiKhoan;
    Button btnThem;

   String urlGet = "http://192.168.1.118/phpcodeDocumentMNG/getListAccount.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);
        mapping();

//        listUser = new ArrayList<>();
//        adapter = new User_Adapter(this, R.layout.item_user, listUser);
        lv_DS_TaiKhoan.setAdapter(adapter);

        LoadData();

        ButtonThemMoi();

    }


    private void LoadData() {
        //listUser.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Log.e("DCMM :", Server.LinkToLogin+"?idNguoiDung="+iduser);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.LinkToLogin,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null) {
                            String idUser;
                            String fullName;
                             String pass;
                            String idRoom;
                            String roomName;
                            String birthDay;
                           String sex;
                           String diaChi;
                            for(int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                    idUser = object.getString("idNguoiDung");
                                    fullName = object.getString("hoten");
                                    pass = object.getString("matkhau");
                                    idRoom = object.getString("idPhongBan");
                                    roomName = object.getString("tenPhongBan");
                                    birthDay = object.getString("ngaysinh");
                                    sex = object.getString("gioitinh");
                                    diaChi = object.getString("diachi");

//                                listUser.add(new User(
//                                        object.getString("idNguoiDung"),
//                                        object.getString("hoten"),
//                                object.getString("matkhau"),
//                                object.getString("idPhongBan"),
//                                 object.getString("tenPhongBan"),
//                                object.getString("ngaysinh"),
//                                 object.getString("gioitinh"),
//                                 object.getString("diachi")
//                                ));

                                listUser.add(new User(idUser, fullName, pass, idRoom, roomName, birthDay, sex, diaChi));
                                adapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }

    private void ButtonThemMoi() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountManagerActivity.this, AddAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        lv_DS_TaiKhoan = findViewById(R.id.lv_DS_TK);
        btnThem = findViewById(R.id.btn_ThemMoi);
    }



}
