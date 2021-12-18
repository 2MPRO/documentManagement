package com.example.documentmanagement.Controllers.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.documentmanagement.Controllers.Activity.LoginActivity;
import com.example.documentmanagement.R;

public class AcountFragment extends Fragment {
    Button btnPermission, btnManager, btnAccount;
    Button btnSignOut;
    private View view ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view   = inflater.inflate(R.layout.fragment_acount,container,false);
        mapping();
        SignOut();


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("Đang on   :","fragment acount");
    }

    private void SignOut() {            // Đăng xuất
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void mapping() {            // Ánh xạ
        btnPermission = view.findViewById(R.id.btn_PhanQuyen);
        btnManager = view.findViewById(R.id.btn_QuanLy);
        btnAccount = view.findViewById(R.id.btn_ThongTinTK);
        btnSignOut = view.findViewById(R.id.btn_DangXuat);
    }

}
