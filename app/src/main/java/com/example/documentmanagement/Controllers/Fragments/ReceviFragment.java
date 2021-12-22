package com.example.documentmanagement.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.documentmanagement.Controllers.Adapter.ViewpagerReceiveAdapter;
import com.example.documentmanagement.R;
import com.google.android.material.tabs.TabLayout;

public class ReceviFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewpagerReceiveAdapter viewpagerReceiveAdapter;
    private View view   ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_recevi,container,false);
        mapping();
        setTabLayout();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("Đang on   :","fragment recevi");
    }

    private void setTabLayout() {
        // getsupport different video
        viewpagerReceiveAdapter = new ViewpagerReceiveAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpagerReceiveAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }




    private void mapping() {
        tabLayout = view.findViewById(R.id.tablayout_receive);
        viewPager = view.findViewById(R.id.frag_receive_pager);
    }

}
