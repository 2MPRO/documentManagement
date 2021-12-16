package com.example.documentmanagement.Controllers.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.documentmanagement.Controllers.Fragments.AcountFragment;
import com.example.documentmanagement.Controllers.Fragments.MainFragment;
import com.example.documentmanagement.Controllers.Fragments.ReceviFragment;
import com.example.documentmanagement.Controllers.Fragments.SendFragment;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    public ViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
               return new ReceviFragment();
            case 2:
                return new SendFragment();
            case 3:
                return new AcountFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
