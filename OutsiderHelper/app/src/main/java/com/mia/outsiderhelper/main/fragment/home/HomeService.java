package com.mia.outsiderhelper.main.fragment.home;

import com.mia.outsiderhelper.main.fragment.home.interfaces.HomeFragmentView;
import com.mia.outsiderhelper.main.fragment.home.interfaces.HomeRetrofitInterface;
import com.mia.outsiderhelper.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mia.outsiderhelper.ApplicationClass.getRetrofit;

public class HomeService {
    private final HomeFragmentView homeFragmentView;

    public HomeService(HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
    }

    void getWeather(String url) {
        final HomeRetrofitInterface homeRetrofitInterface = getRetrofit(url).create(HomeRetrofitInterface.class);
        homeRetrofitInterface.getWeather("Seoul", "326ccf8d226e6c1c0bea6d7d302a4d2c").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                final WeatherResponse weatherResponse = response.body();
                if(weatherResponse == null) {
                    homeFragmentView.getWeatherFailure();
                }
                homeFragmentView.getWeatherSuccess();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                homeFragmentView.getWeatherFailure();
            }
        });

    }
}
