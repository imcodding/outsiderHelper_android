package com.mia.outsiderhelper.main.fragment.store.restaurant.blog;

import com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces.BlogListActivityView;
import com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces.BlogListRetrofitInterface;
import com.mia.outsiderhelper.models.BlogResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mia.outsiderhelper.ApplicationClass.getRetrofit;

public class BlogListService {
    private final BlogListActivityView blogListActivityView;
    private final String url = "https://dapi.kakao.com";

    public BlogListService(BlogListActivityView blogListActivityView) {
        this.blogListActivityView = blogListActivityView;
    }

    void getBlogList(String query, int page) {
        final BlogListRetrofitInterface blogListRetrofitInterface = getRetrofit(url).create(BlogListRetrofitInterface.class);
        blogListRetrofitInterface.getBlogList(query, "lastest", page, 10).enqueue(new Callback<BlogResponse>() {
            @Override
            public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
                BlogResponse blogResponse = response.body();
                if(blogResponse == null) {
                    blogListActivityView.getBlogListFailure(null);
                    return;
                }
                blogListActivityView.getBlogListSuccess(blogResponse.getDocuments());
            }

            @Override
            public void onFailure(Call<BlogResponse> call, Throwable t) {
                blogListActivityView.getBlogListFailure(t.getMessage());
            }
        });
    }
}
