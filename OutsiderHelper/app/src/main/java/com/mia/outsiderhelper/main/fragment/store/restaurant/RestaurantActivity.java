package com.mia.outsiderhelper.main.fragment.store.restaurant;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.store.restaurant.interfaces.RestaurantActivityView;
import com.mia.outsiderhelper.models.SearchResponse;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class RestaurantActivity extends BaseActivity implements RestaurantActivityView {

    private RestaurantService mRestaurantService;
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mRestaurantService = new RestaurantService(this);
        mProgressBar = findViewById(R.id.progress_bar);

        mMapView = new MapView(this);
        ViewGroup container = findViewById(R.id.restaurant_map_view);
        container.addView(mMapView);

        EditText resEditKeyword = findViewById(R.id.restaurant_edit_keyword);
        Button resSearchBtn = findViewById(R.id.restaurant_btn_search);
        resSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = String.valueOf(resEditKeyword.getText());
                if(keyword.length() == 0) {
                    showCustomToast(getString(R.string.store_input_search_keyword));
                    return;
                }
                showProgressDialog();
                mRestaurantService.getSearchByKeyword(keyword);
            }
        });
    }

    @Override
    public void getSearchByKeywordSuccess(List<SearchResponse.Document> documents) {
        hideProgressDialog();

        for(SearchResponse.Document item : documents) {
            createMarker(item);
        }
    }

    @Override
    public void getSearchByKeywordFailure(String message) {
        hideProgressDialog();
    }

    private void createMarker(SearchResponse.Document item) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(item.getPlaceName());
        marker.setTag(Integer.parseInt(item.getId()));
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(item.getY(), item.getX()));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mMapView.addPOIItem(marker);
    }
}