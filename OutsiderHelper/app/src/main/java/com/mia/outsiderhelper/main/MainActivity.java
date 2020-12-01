package com.mia.outsiderhelper.main;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.board.BoardFragment;
import com.mia.outsiderhelper.main.fragment.home.HomeFragment;
import com.mia.outsiderhelper.main.fragment.playing.PlayingFragment;
import com.mia.outsiderhelper.main.fragment.store.StoreFragment;
import com.mia.outsiderhelper.models.LoginResponse;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    // view
    private DrawerLayout mDrawerLayout;
    private TextView mTvUserId;
    private TextView mTvUserNickname;
    private TextView mTvUserUniversity;
    private TextView mTvUserAge;

    // fragment
    private final FragmentManager fm = getSupportFragmentManager();
    private final Fragment mHomeFragment = new HomeFragment();
    private final Fragment mStoreFragment = new StoreFragment();
    private final Fragment mPlayingFragment = new PlayingFragment();
    private final Fragment mBoardFragment = new BoardFragment();
    private Fragment active = mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navHeader = navigationView.getHeaderView(0);

        mTvUserId = navHeader.findViewById(R.id.txt_nav_user_id);
        mTvUserNickname = navHeader.findViewById(R.id.txt_user_nick);
        mTvUserUniversity = navHeader.findViewById(R.id.txt_user_university);
        mTvUserAge = navHeader.findViewById(R.id.txt_user_age);

        setUserInfo();

        fm.beginTransaction().add(R.id.main_container, mBoardFragment, "4").hide(mBoardFragment).commit();
        fm.beginTransaction().add(R.id.main_container, mPlayingFragment, "3").hide(mPlayingFragment).commit();
        fm.beginTransaction().add(R.id.main_container, mStoreFragment, "2").hide(mStoreFragment).commit();
        fm.beginTransaction().add(R.id.main_container, mHomeFragment, "1").commit();

    }

    private void setUserInfo() {
        Bundle bundle = getIntent().getExtras();
        LoginResponse user = (LoginResponse) bundle.getSerializable("user");
        if(user != null) {
            mTvUserId.setText(user.getUserId());
            mTvUserNickname.setText(user.getNickname());
            mTvUserUniversity.setText(user.getUniversity());
            mTvUserAge.setText(user.getAge());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_home:
                fm.beginTransaction().hide(active).show(mHomeFragment).commit();
                active = mHomeFragment;
                break;
            case R.id.tab_store:
                fm.beginTransaction().hide(active).show(mStoreFragment).commit();
                active = mStoreFragment;
                break;
            case R.id.tab_playing:
                fm.beginTransaction().hide(active).show(mPlayingFragment).commit();
                active = mPlayingFragment;
                break;
            case R.id.tab_board:
                fm.beginTransaction().hide(active).show(mBoardFragment).commit();
                active = mBoardFragment;
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