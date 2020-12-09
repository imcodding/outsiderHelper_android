package com.mia.outsiderhelper.main.fragment.store.restaurant;

import com.mia.outsiderhelper.main.fragment.store.restaurant.interfaces.RestaurantActivityView;
import com.mia.outsiderhelper.main.fragment.store.restaurant.interfaces.RestaurantRetrofitInterface;
import com.mia.outsiderhelper.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mia.outsiderhelper.ApplicationClass.getRetrofit;

public class RestaurantService {
    private final RestaurantActivityView restaurantActivityView;
    private final String url = "https://dapi.kakao.com";

    public RestaurantService(RestaurantActivityView restaurantActivityView) {
        this.restaurantActivityView = restaurantActivityView;
    }

    void getSearchByKeyword(String keyword, double x, double y, int page) {
        final RestaurantRetrofitInterface restaurantRetrofitInterface = getRetrofit(url).create(RestaurantRetrofitInterface.class);
        restaurantRetrofitInterface.getSearchByKeyword(keyword, x, y, page, 15).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                if(searchResponse == null) {
                    restaurantActivityView.getSearchByKeywordFailure(null);
                    return;
                }
                restaurantActivityView.getSearchByKeywordSuccess(searchResponse.getDocuments());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                restaurantActivityView.getSearchByKeywordFailure(t.getMessage());
            }
        });
    }

}
