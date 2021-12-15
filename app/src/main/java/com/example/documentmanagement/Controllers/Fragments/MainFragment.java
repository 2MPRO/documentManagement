package com.example.documentmanagement.Controllers.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.documentmanagement.Controllers.Adapter.PhotoViewpager2Adapter;
import com.example.documentmanagement.Controllers.animation.ZoomOutPageTransformer;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.PhoTo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainFragment extends Fragment {

    private ViewPager2 viewPager2; //imgslide
    private List<PhoTo> listPhoto;
    private CircleIndicator3 circleIndicator3;
    private Handler handler = new Handler();
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view   = inflater.inflate(R.layout.fragment_main,container,false);
        Mapping();
        initView();
        return view;
    }
    public void Mapping() {
        viewPager2 = view.findViewById(R.id.viewPage2);
        circleIndicator3 = view.findViewById(R.id.circleIndicator3);

    }
    public void initView(){
        // slide img
        PhotoAdapter();

    }
    private void PhotoAdapter() {
        listPhoto = getListPhoto();
        PhotoViewpager2Adapter adapter  = new PhotoViewpager2Adapter(listPhoto);
        viewPager2.setAdapter(adapter);

        //connect view page with circelindicator
        circleIndicator3.setViewPager(viewPager2);


        //auto run
       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

        // animation
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
    }

    private List<PhoTo> getListPhoto(){
        List<PhoTo> list = new ArrayList<>();
        list.add(new PhoTo(R.drawable.img_slider_muonkiep));
        list.add(new PhoTo(R.drawable.img_slider_nhagiak));
        list.add(new PhoTo(R.drawable.img_slider_tuyet));
        list.add(new PhoTo(R.drawable.img_slider_muonkiep));
        return list;
    }
    // set slide
  private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager2.getCurrentItem() == listPhoto.size() -1 ){
                viewPager2.setCurrentItem(0);
            }else
            {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }

        }
    };
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }

}
