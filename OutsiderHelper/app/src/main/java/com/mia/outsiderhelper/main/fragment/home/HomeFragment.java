package com.mia.outsiderhelper.main.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.home.adapter.ImageAdapter;
import com.mia.outsiderhelper.main.fragment.home.interfaces.HomeFragmentView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    private final static String TAG = "HomeFragment";

    private HomeService mHomeService;
    private ViewPager mHomeFoodViewPager;
    private Timer mTimer;

    private int currentPos = 0;
    private final int PAGING_DELAY = 1000;
    private final int PAGING_PERIOD = 2000;

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

        ImageAdapter imageAdapter = new ImageAdapter(getContext(), images);
        mHomeFoodViewPager.setAdapter(imageAdapter);

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
        }, PAGING_DELAY, PAGING_PERIOD);

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
