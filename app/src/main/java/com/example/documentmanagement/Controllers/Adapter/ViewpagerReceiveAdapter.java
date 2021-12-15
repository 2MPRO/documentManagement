package com.example.documentmanagement.Controllers.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.documentmanagement.Controllers.Fragments.AcountFragment;
import com.example.documentmanagement.Controllers.Fragments.ApprovedReceiveFragment;
import com.example.documentmanagement.Controllers.Fragments.MainFragment;
import com.example.documentmanagement.Controllers.Fragments.ReceviFragment;
import com.example.documentmanagement.Controllers.Fragments.SendFragment;
import com.example.documentmanagement.Controllers.Fragments.WaitReceiveFragment;

public class ViewpagerReceiveAdapter extends FragmentStatePagerAdapter {
    public ViewpagerReceiveAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WaitReceiveFragment();
            case 1:
                return new ApprovedReceiveFragment();
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
                title ="Chờ Duyệt";
                break;
            case 1:
                title ="Đã Duyệt";
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
