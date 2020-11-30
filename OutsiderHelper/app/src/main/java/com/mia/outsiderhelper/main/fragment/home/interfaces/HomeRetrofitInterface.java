package com.mia.outsiderhelper.main.fragment.home.interfaces;

import com.mia.outsiderhelper.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeRetrofitInterface {
    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(@Query("q") String city, @Query("appid") String appid);
}
