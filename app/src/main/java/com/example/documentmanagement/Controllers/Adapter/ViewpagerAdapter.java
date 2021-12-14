package com.example.documentmanagement.Controllers.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.documentmanagement.Controllers.Activity.AcountFragment;
import com.example.documentmanagement.Controllers.Activity.MainFragment;
import com.example.documentmanagement.Controllers.Activity.ReceviFragment;
import com.example.documentmanagement.Controllers.Activity.SendFragment;

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
