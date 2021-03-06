package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.idRoom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDocumentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    EditText edit_Title, edit_content;
    Spinner spinner, spnLevel, spnDocType;
   Button  btnSaveTmp = null;
   Button btnSend = null;
    List<Room> listRoom = new ArrayList<>();
    List<Doctype> listDoctype = new ArrayList<>();
    List<Level> listLevel = new ArrayList<>();
    private RoomAdapter roomAdapter;
    private LevelAdapter levelAdapter;
    private DocTypeAdapter docTypeAdapter;
    private String date;
    private String hour;
    public static String idRecipient; // id n??i ?????n ???? ch???n trong combobox
    public static String idLevel;
    public static String idDoctype;
    public boolean aBoolean;
    public Document document;
    public TextView txtFilePdf;
    private Button btnUpPdf;
    private  int REQ_PDF = 21;
    Uri path;
    private String encodePDF ; //L??u pdf chu???n b??? up;

    private byte[] pdfInBytes;
    String  fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);
        mapping();
        setToolbar();
        setRoomSpinner();
        setDocTypeSpinner();
        setLevelSpinner();
        setbtnUpPdf();
        //ki???m tra t???n t???i d??? li???u truy???n qua Intent
        if(isExistIntent()){
            aBoolean = true;

        }
        else
        {
            aBoolean = false;
        };
        loaddataRoom();
        loaddataDocType();
        loaddataLevel();
        setBtnSend();
        settxtOpenPdf();
        settmpSavebuton();
    }

//    private void setURL() {
//        try {
//            pathUrl = new URL(linkDoc);
//            String  fileName = pathUrl.getPath();
//            fileName = fileName.substring(fileName.lastIndexOf('/')+1);
//            txtFilePdf.setText(fileName);
//            downLoadDoc(fileName);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    private void downLoadDoc(String fileName) {
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pathUrl.toString()));
//        request.setTitle(fileName);
//        request.setMimeType("application/pdf");
//        request.setAllowedOverMetered(true);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);
//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        downloadManager.enqueue(request);
//    }

    private void setbtnUpPdf() {
        btnUpPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent = Intent.createChooser(intent,"Choose a file");
                startActivityForResult(intent, REQ_PDF);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_PDF && resultCode == RESULT_OK && data != null){
            path = data.getData();
            try {
                InputStream inputStream = AddDocumentActivity.this.getContentResolver().openInputStream(path);
                byte[] PdfInBytes = new byte[inputStream.available()];
                inputStream.read(PdfInBytes);
                encodePDF =   Base64.encodeToString(PdfInBytes,Base64.DEFAULT);
                fileName = path.toString();
                fileName = fileName.substring(fileName.lastIndexOf('/')+1);
                txtFilePdf.setText(fileName);

                Toast.makeText(getApplicationContext(), "???? th??m file pdf", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void settxtOpenPdf() {
        txtFilePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean == true){
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+fileName);
                    Uri uri= FileProvider.getUriForFile(AddDocumentActivity.this, "com.example.documentmanagement"+".provider",file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri,"application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path,"application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                }

            }
        });
    }


    private Boolean isExistIntent() {
        Intent intent = getIntent();
        document = (Document) intent.getSerializableExtra("document");
        if(document!=null){
            edit_Title.setText(document.getDocName());
            edit_content.setText(document.getNoiDung());
            btnSaveTmp.setVisibility(View.GONE);
            setPdf(document.getDinhKem());
            encodePDF = document.getDinhKem(); // l??u cho c?? chuy???n
            return  true;
        }
        return false;
    }

    private void setPdf(String dinhKem) {
        pdfInBytes = Base64.decode(dinhKem, Base64.DEFAULT);
        if(isStoragePermissionGranted()){
            savePDF();
        }
    }

    private void savePDF() {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/ManagerDocument");
        if( !myDir.exists()){
            myDir.mkdir();
        }
        try {
            File fileLocation = new File(myDir, "myPDF.pdf");
            FileOutputStream fos = new FileOutputStream(fileLocation);
            fos.write(pdfInBytes);
            fos.flush();
            fos.close();
            Toast.makeText(this, "???? l??u file pdf!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){e.printStackTrace();}

    }

    public boolean isStoragePermissionGranted() {
        String TAG = "Storage Permission";
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Quy???n ???? ???????c c???p");
                return true;
            } else {
                Log.v(TAG, "Quy???n ???? b??? thu h???i");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return true;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Quy???n ???? ???????c c???p");
            return true;
        }
    }

    private void mapping() {
        btnUpPdf = findViewById(R.id.btnUpPdf);
        txtFilePdf = findViewById(R.id.txtFilePdf);
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
        toolbar.setTitle("Th??m v??n b???n");
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
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.LinkShowAllRom+"?idRoom="+idRoom, null, new Response.Listener<JSONArray>() {
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

    private void settmpSavebuton() {

            btnSaveTmp.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    if(encodePDF==null){
                        Toast.makeText(getApplicationContext(), "B??? file ????nh k??m z??", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!edit_content.getText().toString().isEmpty() ||  !edit_Title.getText().toString().isEmpty() ) {
                            getdatetimeCurrent();
                            insertData("saveTmp");
                            Intent intent = new Intent(AddDocumentActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Vui l??ng ??i???n ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


    }
    public void setBtnSend() {

        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(encodePDF==null){
                    Toast.makeText(getApplicationContext(), "B??? file ????nh k??m z??", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!edit_content.getText().toString().isEmpty() ||  !edit_Title.getText().toString().isEmpty() ) {

                            getdatetimeCurrent();
                            insertData("save");
                        }
                    else
                        Toast.makeText(getApplicationContext(), "Vui l??ng ??i???n ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();


                }


            }
        });
    }

    public void  insertData(String saction) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkinsertDoccument,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("erro")) {
                            Toast.makeText(getApplicationContext(), "C?? quan ???? c?? v??n b???n n??y; Vui l??ng ch???n ph??ng ban kh??c", Toast.LENGTH_SHORT).show();
                        } else {
                            ////// Ch??n ti???p pdf
                            RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Server.LinkInsertTableDinhKem, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("qurry",response);
                                    if(saction.equals("saveTmp")){
                                        Toast.makeText(getApplicationContext(), "???? th??m v??o danh s??ch ch??? g???i", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "???? g???i v??n b???n th??nh c??ng", Toast.LENGTH_SHORT).show();
                                        Log.e("queeyyy", response);
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> hashMap = new HashMap<String,String>();
                                    hashMap.put("PDF",encodePDF);
                                    hashMap.put("docNum",response);
                                    return hashMap;
                                }


                            };
                            requestQueue2.add(stringRequest2);
                                    //////Ch??n ti???p pdf
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
                    param.put("docNum",document.getDocNum());
                }
                if(saction.equals("saveTmp")){
                    param.put("saveTmp","saveTmp");
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}