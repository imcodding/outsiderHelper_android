package com.mia.outsiderhelper.main.fragment.store.restaurant.interfaces;

import com.mia.outsiderhelper.models.SearchResponse;

import java.util.List;

public interface RestaurantActivityView {
    void getSearchByKeywordSuccess(List<SearchResponse.Document> documents);
    void getSearchByKeywordFailure(String message);
}
