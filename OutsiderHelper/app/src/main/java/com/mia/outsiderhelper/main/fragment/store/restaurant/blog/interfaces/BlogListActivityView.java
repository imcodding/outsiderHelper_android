package com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces;

import com.mia.outsiderhelper.models.BlogResponse;

import java.util.List;

public interface BlogListActivityView {
    void getBlogListSuccess(List<BlogResponse.Document> documents);
    void getBlogListFailure(String message);
}
