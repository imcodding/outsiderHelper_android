package com.mia.outsiderhelper.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_header_view = navigationView.getHeaderView(0);
        TextView tvUserId = nav_header_view.findViewById(R.id.txt_nav_user_id);
        TextView tvUserNick = nav_header_view.findViewById(R.id.txt_user_nick);
        TextView tvUserUniversity = nav_header_view.findViewById(R.id.txt_user_university);
        TextView tvUserAge = nav_header_view.findViewById(R.id.txt_user_age);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_home:
                break;
            case R.id.tab_store:
                break;
            case R.id.tab_playing:
                break;
            case R.id.tab_board:
                break;

            case R.id.nav_home:
                break;
            case R.id.nav_eating:

                break;
            case R.id.nav_cafe:

                break;
            case R.id.nav_conv:

                break;
            case R.id.nav_entertain:

                break;
            case R.id.nav_music:

                break;
            case R.id.nav_book:

                break;
            case R.id.nav_video:

                break;
            case R.id.nav_game:

                break;
            case R.id.nav_board:

                break;
//            case R.id.nav_chatting:
//                switchFragment(ChattingFragment.newInstance());
//                setBottomBackground(fragmentBottom[3]);
//                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.img_icon_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
}