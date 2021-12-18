package com.example.documentmanagement.Controllers.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.documentmanagement.Controllers.Fragments.ApprovedReceiveFragment;
import com.example.documentmanagement.Controllers.Fragments.ApprovedSendFragment;
import com.example.documentmanagement.Controllers.Fragments.WaitReceiveFragment;
import com.example.documentmanagement.Controllers.Fragments.WaitSendFragment;

public class ViewpagerSendAdapter extends FragmentStatePagerAdapter {
    public ViewpagerSendAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WaitSendFragment();
            case 1:
                return new ApprovedSendFragment();
            default:
                return new WaitReceiveFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
            case 0:
                title ="Chờ Gửi";
                break;
            case 1:
                title ="Đã Gửi";
                break;
            default:
                title ="Chờ Duyệt";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
