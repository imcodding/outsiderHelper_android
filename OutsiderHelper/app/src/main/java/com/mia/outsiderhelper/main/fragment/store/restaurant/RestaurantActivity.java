package com.mia.outsiderhelper.main.fragment.store.restaurant;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.store.restaurant.interfaces.RestaurantActivityView;
import com.mia.outsiderhelper.models.SearchResponse;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends BaseActivity implements RestaurantActivityView, View.OnClickListener {

    private RestaurantService mRestaurantService;
    private MapView mMapView;
    private EditText mResEditKeyword;
    private double mLatitude;
    private double mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mRestaurantService = new RestaurantService(this);

        initView();

        requestPermission();
    }

    private void initView() {
        mProgressBar = findViewById(R.id.progress_bar);
        mResEditKeyword = findViewById(R.id.restaurant_edit_keyword);
        mMapView = new MapView(this);

        mMapView.setZoomLevel(4, false);
        ViewGroup container = findViewById(R.id.restaurant_map_view);
        container.addView(mMapView);

        Button resSearchBtn = findViewById(R.id.restaurant_btn_search);
        resSearchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String keyword = String.valueOf(mResEditKeyword.getText());
        if (keyword.length() == 0) {
            showCustomToast(getString(R.string.store_input_search_keyword));
            return;
        }

        showProgressDialog();
        for(int i = 1; i <= 5; i++)
            mRestaurantService.getSearchByKeyword(keyword, mLongitude, mLatitude, i);
    }

    @Override
    public void getSearchByKeywordSuccess(List<SearchResponse.Document> documents) {
        hideProgressDialog();

        for (SearchResponse.Document item : documents) {
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
        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        marker.setCustomImageResourceId(R.drawable.marker_store);

        mMapView.addPOIItem(marker);
    }

    public void requestPermission() {
        TedPermission.with(getApplicationContext())
                .setRationaleMessage("위치 권한이 필요합니다.\n(거부할 경우 진행불가)")
                .setDeniedMessage("위치 권한을 거부하셨습니다.")
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
            mMapView.setCustomCurrentLocationMarkerTrackingImage(R.drawable.marker, new MapPOIItem.ImageOffset(30,0) );
            mMapView.setCurrentLocationEventListener(new MapView.CurrentLocationEventListener() {
                @Override
                public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
                    mLatitude = mapPoint.getMapPointGeoCoord().latitude;
                    mLongitude = mapPoint.getMapPointGeoCoord().longitude;

                    Log.d("Restaurant", "location update");

                    showProgressDialog();
                    mRestaurantService.getSearchByKeyword("밥", mLongitude, mLatitude,1);
                    mRestaurantService.getSearchByKeyword("밥", mLongitude, mLatitude,2);
                    mRestaurantService.getSearchByKeyword("밥", mLongitude, mLatitude,3);
                    mRestaurantService.getSearchByKeyword("고기", mLongitude, mLatitude,1);
                    mRestaurantService.getSearchByKeyword("고기", mLongitude, mLatitude,2);
                    mRestaurantService.getSearchByKeyword("고기", mLongitude, mLatitude,3);
                    mRestaurantService.getSearchByKeyword("치킨", mLongitude, mLatitude,1);
                    mRestaurantService.getSearchByKeyword("치킨", mLongitude, mLatitude,2);
                    mRestaurantService.getSearchByKeyword("치킨", mLongitude, mLatitude,3);
                }

                @Override
                public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

                }

                @Override
                public void onCurrentLocationUpdateFailed(MapView mapView) {

                }

                @Override
                public void onCurrentLocationUpdateCancelled(MapView mapView) {

                }
            });
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            showCustomToast(getString(R.string.store_location_request_deny));
            finish();
        }
    };
}