package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.AddDocumentActivity.idRecipient;
import static com.example.documentmanagement.Controllers.Activity.LoginActivity.use;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.User;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountInformationActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etHoTen, etGioiTinh, etNgay, etDiaChi, etMatKhau;
    Button btnCapNhat;
    String fullName;
    String pass;
    String roomName;
    String birthDay;
    String sex;
    String diaChi;
    String urlUpdate = "http://192.168.1.118/phpcodeDocumentMNG/updateAccount.php" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        mapping();
        setToolbar();
        GetAccount();
        ButtonUpdateAccount();

    }

    private void mapping() {
        etHoTen = findViewById(R.id.edt_tk_ht);
        etNgay = findViewById(R.id.edt_tk_ngsinh);
        etGioiTinh = findViewById(R.id.edt_tk_gt);
        etDiaChi = findViewById(R.id.edt_tk_dchi);
        etMatKhau = findViewById(R.id.edt_mk);
        btnCapNhat = findViewById(R.id.btnUpdate);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setToolbar() {
        toolbar.setTitle("Thông tin cá nhân");
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }

    private void GetAccount() {
        etHoTen.setText(use.getFullName());
        etNgay.setText(use.getBirthDay());
        etGioiTinh.setText(use.getSex());
        etDiaChi.setText(use.getDiaChi());
        etMatKhau.setText(use.getPass());
    }

    private void ButtonUpdateAccount() {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName = etHoTen.getText().toString().trim();
                birthDay = etNgay.getText().toString().trim();
                sex = etGioiTinh.getText().toString().trim();
                diaChi = etDiaChi.getText().toString().trim();
                pass= etMatKhau.getText().toString().trim();

                if(fullName.isEmpty() || birthDay.isEmpty() || sex.isEmpty() || diaChi.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Update();
                }

            }
        });
    }

//    private void update(String url) {
//        idRoom = idRecipient;
//        RequestQueue requestQueue  = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.trim().equals("ok")){
//                            Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
//
//                            // gọi lại hàm load thông
//                            readJson(Server.LinkGetAccount +"?idPhongBan="+ idRoom );
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
//
//            }
//        } ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<>();
//                param.put("idPhongBan",String.valueOf(idRoom));
//                param.put("hoten",etHoTen.getText().toString().trim());
//                param.put("ngaysinh",etNgay.getText().toString().trim());
//                param.put("gioitinh",etGioiTinh.getText().toString().trim());
//                param.put("diachi",etDiaChi.getText().toString().trim());
//                param.put("taiKhoan",etTaiKhoan.getText().toString().trim());
//                param.put("matkhau",etMatKhau.getText().toString().trim());
//
//                return param;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }

//    private void readJson(String url){
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(0);
//                            idRoom = jsonObject.getString("idPhongBan");
//                            etHoTen.setText(jsonObject.getString("hoten"));
//                            etNgay.setText(jsonObject.getString("ngaysinh"));
//                            etGioiTinh.setText(jsonObject.getString("gioitinh"));
//                            etDiaChi.setText(jsonObject.getString("diachi"));
//                            etTaiKhoan.setText(jsonObject.getString("taiKhoan"));
//                            etMatKhau.setText(jsonObject.getString("matkhau"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//        );
//        requestQueue.add(jsonArrayRequest);
//    }

    private void Update() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkUpdateAccount,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("Success")) {
                            Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
                            Log.e("DCMM :", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("hoten",etHoTen.getText().toString().trim());
                param.put("ngaysinh",etNgay.getText().toString().trim());
                param.put("gioitinh",etGioiTinh.getText().toString().trim());
                param.put("diachi",etDiaChi.getText().toString().trim());
                param.put("matkhau",etMatKhau.getText().toString().trim());
                param.put("taiKhoan", use.getIdUser());

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }




}
