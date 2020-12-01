package com.mia.outsiderhelper.main.fragment.home;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.home.adapter.ImageAdapter;
import com.mia.outsiderhelper.main.fragment.home.interfaces.HomeFragmentView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    private final String TAG = "HomeFragment";

    private HomeService mHomeService;
    private ViewPager mHomeFoodViewPager;
    private ImageAdapter mImageAdapter;
    private Timer mTimer;
    private int currentPos = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeFoodViewPager = view.findViewById(R.id.home_food_viewPager);
        mHomeService = new HomeService(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();
        mHomeService.getWeather("http://api.openweathermap.org");
    }

    @Override
    public void getWeatherSuccess() {
//        hideProgressDialog();
        mHomeService.getFoodImage("winter");
    }

    @Override
    public void getWeatherFailure() {
        hideProgressDialog();
    }

    @Override
    public void getFoodImageSuccess(ArrayList<String> images) {
        hideProgressDialog();

        mImageAdapter = new ImageAdapter(getContext(), images);
        mHomeFoodViewPager.setAdapter(mImageAdapter);

        mHomeFoodViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(currentPos >= images.size()) { currentPos = 0; }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mHomeFoodViewPager.setCurrentItem(currentPos++, true);
                    }
                });
            }
        },1000,2000);

    }

    @Override
    public void getFoodImageFailure(String message) {
        hideProgressDialog();
        Log.d(TAG, message);
        showCustomToast("이미지 실패");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
        }
    }
}
