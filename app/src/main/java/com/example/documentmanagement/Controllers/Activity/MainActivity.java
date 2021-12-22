package com.example.documentmanagement.Controllers.Activity;

import static com.example.documentmanagement.Controllers.Activity.LoginActivity.use;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.documentmanagement.Controllers.Adapter.ViewpagerAdapter;
import com.example.documentmanagement.R;
import com.example.documentmanagement.model.Permission;
import com.example.documentmanagement.model.User;
import com.example.documentmanagement.util.Server;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout; //layout chứa navigationview
    private NavigationView navigationview;
    private BottomNavigationView bottom_nav;
    private ViewPager viewPager; //chứa fragment
    Toolbar toolbar;
    private FloatingActionButton btnAddDoc;
    public static ArrayList<User> userArrayList;
    public static ArrayList<Permission> permissionArrayList;
    TextView userName, txtRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        initView();
        setbtnAdd();
    }

    private void setbtnAdd() {
        btnAddDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddDocumentActivity.class);
                startActivity(intent);

            }
        });
    }

    // ánh xạ
    public void Mapping() {
        bottom_nav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.frag_viewpager);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationview = findViewById(R.id.nav_view);
        btnAddDoc = findViewById(R.id.btnaddDocument);
        userName = findViewById(R.id.userName);
        txtRoom = findViewById(R.id.txtRoom);

    }
    public void initView( ){
        setUpViewPager();
        setBottom_nav();
        setToolbar();
        setDrawerToggle();
        setNavigationView();
    }

    private void setNavigationView() {
        navigationview.setNavigationItemSelectedListener(this);

    }

    private void setDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void setBottom_nav() {
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.action_home:
                        toolbar.setTitle(" ");
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_recevi:
                        toolbar.setTitle("Văn Bản Đến");
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_send:
                        toolbar.setTitle("Văn Bản Đi");
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_Acount:
                        toolbar.setTitle("Tài Khoản");
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "có lỗi", Toast.LENGTH_SHORT).show();
                }
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }

        });
    }
    private void setUpViewPager(){
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottom_nav.getMenu().findItem(R.id.action_home).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_home).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_Acount).setChecked(false);
                        break;
                    case 1:
                        bottom_nav.getMenu().findItem(R.id.action_recevi).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_recevi).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_Acount).setChecked(false);
                        break;
                    case 2:
                        bottom_nav.getMenu().findItem(R.id.action_send).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_send).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_Acount).setChecked(false);
                        break;
                    case 3:
                        bottom_nav.getMenu().findItem(R.id.action_Acount).setChecked(true);
                        navigationview.getMenu().findItem(R.id.action_Acount).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //  pause slider when  out app
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //sự kien click vao navigation view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  R.id.action_home:
                toolbar.setTitle("");
                viewPager.setCurrentItem(0);
                break;
            case R.id.action_recevi:
                toolbar.setTitle("Văn Bản Đến");
                viewPager.setCurrentItem(1);
                break;
            case R.id.action_send:
                toolbar.setTitle("Văn Bản Đi");
                viewPager.setCurrentItem(2);
                break;
            case R.id.action_Acount:
                toolbar.setTitle("Tài Khoản");
                viewPager.setCurrentItem(3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // nếu đang mở drawer thì đóng khi bấm nút trở lại
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
             super.onBackPressed();
    }


}