package com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces;

import com.mia.outsiderhelper.models.BlogResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.mia.outsiderhelper.ApplicationClass.KAKAO_REST_KEY;

public interface BlogListRetrofitInterface {
    @GET("v2/search/blog")
    @Headers("Authorization: KakaoAK " + KAKAO_REST_KEY)
    Call<BlogResponse> getBlogList(
            @Query("query") String query,
            @Query("sort") String sort,
            @Query("page") int page,
            @Query("size") int size
    );
}
