package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.AddDocumentActivity.idRecipient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.documentmanagement.Controllers.Adapter.RoomAdapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Document;
import com.example.documentmanagement.model.Room;
import com.example.documentmanagement.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAccountActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etHoTen, etGioi_Tinh, etDiaChi, etNgaySinh, etTaiKhoan, etMatKhau, etNhapLai;
    ImageView hide_1, hide_2;
    Button btnThemTK;
    Spinner spnPhongBan;
    List<Room> listRoom = new ArrayList<>();
    private RoomAdapter roomAdapter;
    public boolean aBoolean;
    public Document document;
    ///////////////////
    String idUser;
    String fullName;
    String pass;
    String Repass;
    String idRoom;
    String roomName;
    String birthDay;
    String sex;
    String diaChi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        mapping();
//        setToolbar();
        setRoomSpinner();
        loaddataRoom();
        setBtnInsert();
        SelectDate();
        HideButtonEye1();
        HideButtonEye2();
    }


    private void mapping() {
        etHoTen = findViewById(R.id.etName);
        etGioi_Tinh = findViewById(R.id.etSex);
        etDiaChi = findViewById(R.id.etAddress);
        etNgaySinh = findViewById(R.id.etBirthDay);
        etTaiKhoan = findViewById(R.id.etAccount);

        etMatKhau = findViewById(R.id.etPassword);
        etNhapLai = findViewById(R.id.etReneterPassword);
        hide_1 = findViewById(R.id.hide_password_1);
        hide_2 = findViewById(R.id.hide_password_re);
        spnPhongBan = findViewById(R.id.spnPosition);
        btnThemTK = findViewById(R.id.btnThemTaiKhoan);
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

    // Lấy ngày, tháng, năm
    private void setDate() {
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                etNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));

            }
        },date, month, year );
        datePickerDialog.show();
    }

    private void SelectDate() {
        etNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });
    }

    private void HideButtonEye1() {
        hide_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMatKhau.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    etMatKhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hide_1.setImageResource(R.drawable.eye_hiddent);
                }else {
                    etMatKhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hide_1.setImageResource(R.drawable.eye_visibility);
                }
            }
        });
    }

    private void HideButtonEye2() {
        hide_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNhapLai.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    etNhapLai.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hide_2.setImageResource(R.drawable.eye_hiddent);
                }else {
                    etNhapLai.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hide_2.setImageResource(R.drawable.eye_visibility);
                }
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


    public void insertAccount() {
        fullName = etHoTen.getText().toString().trim();
        birthDay = etNgaySinh.getText().toString().trim();
        sex = etGioi_Tinh.getText().toString().trim();
        diaChi = etDiaChi.getText().toString().trim();

        roomName = etTaiKhoan.getText().toString().trim();

        pass= etMatKhau.getText().toString().trim();
        Repass = etNhapLai.getText().toString().trim();

        idRoom = idRecipient;

        if(!pass.equals(Repass)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else if (!fullName.equals("") && !birthDay.equals("") && !sex.equals("") && !diaChi.equals("")
                && !roomName.equals("") && !pass.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkInsertAccount, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("QB", "onResponse: " + response);
                    if (response.contains("success")) {
                        Toast.makeText(getApplicationContext(), "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AccountManagerActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else  {
                        Log.e("QB", "onResponse: " + response);
                        etMatKhau.setText("Lỗi");

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<>();
                        param.put("idPhongBan", idRoom);
                        param.put("hoten", fullName);
                        param.put("ngaysinh", birthDay);
                        param.put("gioitinh", sex);
                        param.put("diachi", diaChi);
                        param.put("taiKhoan", roomName);
                        param.put("matkhau", pass);
                    return param;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void setBtnInsert() {
        btnThemTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName = etHoTen.getText().toString().trim();
                birthDay = etNgaySinh.getText().toString().trim();
                sex = etGioi_Tinh.getText().toString().trim();
                diaChi = etDiaChi.getText().toString().trim();

                roomName = etTaiKhoan.getText().toString().trim();

                pass= etMatKhau.getText().toString().trim();
                Repass = etNhapLai.getText().toString().trim();

                if(fullName.isEmpty() || birthDay.isEmpty() || sex.isEmpty() || diaChi.isEmpty() ||
                  roomName.isEmpty() || pass.isEmpty() || Repass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else {
                    insertAccount();
                }
            }
        });
    }

}
