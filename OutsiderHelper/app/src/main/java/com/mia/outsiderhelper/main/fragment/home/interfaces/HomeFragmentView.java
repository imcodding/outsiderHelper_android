package com.mia.outsiderhelper.main.fragment.home.interfaces;

import java.util.ArrayList;

public interface HomeFragmentView {
    void getWeatherSuccess();
    void getWeatherFailure();

    void getFoodImageSuccess(ArrayList<String> images);
    void getFoodImageFailure(String message);
}
