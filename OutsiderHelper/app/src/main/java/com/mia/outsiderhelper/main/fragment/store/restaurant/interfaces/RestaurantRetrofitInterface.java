package com.mia.outsiderhelper.main.fragment.store.restaurant.interfaces;

import com.mia.outsiderhelper.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.mia.outsiderhelper.ApplicationClass.KAKAO_REST_KEY;

public interface RestaurantRetrofitInterface {
    @GET("v2/local/search/keyword.json")
    @Headers("Authorization: KakaoAK " + KAKAO_REST_KEY)
    Call<SearchResponse> getSearchByKeyword(
            @Query("query") String query,
            @Query("size") int size
    );
}
