package com.example.documentmanagement.Controllers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Permission;
import com.example.documentmanagement.model.User;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText editpass;
    private EditText edituser;
    private Button buttonLogin;
    private ImageView imgEye;
    private String user;
    private String pass;
    public static String iduser;
    public static String idRoom;
    public static User use;
    public static ArrayList<Permission> permissionArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        setbuttonLogin();
        setButtonEye();
    }

    private void setbuttonLogin() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void setButtonEye() {
        imgEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editpass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    editpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imgEye.setImageResource(R.drawable.eye_hiddent);
                }else {
                    editpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imgEye.setImageResource(R.drawable.eye_visibility);
                }
            }
        });
    }

    private void mapping() {
        buttonLogin = findViewById(R.id.btnDangNhap);
        edituser = findViewById(R.id.txtAcc);
        editpass = findViewById(R.id.imgPass);
        imgEye = findViewById(R.id.hide_password_1);
    }

    public void login(){
        user = edituser.getText().toString().trim();
        pass = editpass.getText().toString().trim();
        if(!user.equals("")&&!pass.equals("")){

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkToLogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        if (!response.contains("failure")) {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject;

                            for(int i=0;i<jsonArray.length();i++){
                            jsonObject = jsonArray.getJSONObject(i);
                          //  iduser = String.valueOf( jsonObject.getInt("idNguoiDung"));
                                idRoom = String.valueOf( jsonObject.getInt("idPhongBan"));
                                permissionArrayList.add(new Permission(String.valueOf( jsonObject.getInt("idQuyen")),"noSelect"));
                                use = new User(jsonObject.getString("taiKhoan").trim(),
                                        jsonObject.getString("hoten").trim(),
                                        jsonObject.getString("matkhau").trim(),
                                        String.valueOf(jsonObject.getInt("idPhongBan")).trim(),
                                        jsonObject.getString("tenPhongBan").trim(),
                                        jsonObject.getString("ngaysinh").trim(),
                                        jsonObject.getString("gioitinh").trim(),
                                        jsonObject.getString("diachi").trim(),
                                        "Image");
                            }
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Log.d("email", " " + user);
                            intent.putExtra("user", use);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Nhập sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("user",user);
                    data.put("pass",pass);
                    return data;
                }
            };
            requestQueue.add(stringRequest);
        }

        }
    }


