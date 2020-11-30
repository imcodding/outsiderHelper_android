package com.mia.outsiderhelper.main.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mia.outsiderhelper.BaseFragment;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.home.interfaces.HomeFragmentView;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    private HomeService mHomeService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeService = new HomeService(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();
        mHomeService.getWeather("api.openweathermap.org");
    }

    @Override
    public void getWeatherSuccess() {
        hideProgressDialog();
        showCustomToast("성공");
    }

    @Override
    public void getWeatherFailure() {
        hideProgressDialog();
        showCustomToast("실패");
    }
}
