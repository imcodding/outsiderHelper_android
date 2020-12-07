
package com.mia.outsiderhelper.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("documents")
    private List<Document> mDocuments;
    @SerializedName("meta")
    private Meta mMeta;

    public List<Document> getDocuments() {
        return mDocuments;
    }

    public void setDocuments(List<Document> documents) {
        mDocuments = documents;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public void setMeta(Meta meta) {
        mMeta = meta;
    }

    public static class SameName {

        @SerializedName("keyword")
        private String mKeyword;
        @SerializedName("region")
        private List<Object> mRegion;
        @SerializedName("selected_region")
        private String mSelectedRegion;

        public String getKeyword() {
            return mKeyword;
        }

        public void setKeyword(String keyword) {
            mKeyword = keyword;
        }

        public List<Object> getRegion() {
            return mRegion;
        }

        public void setRegion(List<Object> region) {
            mRegion = region;
        }

        public String getSelectedRegion() {
            return mSelectedRegion;
        }

        public void setSelectedRegion(String selectedRegion) {
            mSelectedRegion = selectedRegion;
        }

    }

    public static class Meta {

        @SerializedName("is_end")
        private Boolean mIsEnd;
        @SerializedName("pageable_count")
        private Long mPageableCount;
        @SerializedName("same_name")
        private SameName mSameName;
        @SerializedName("total_count")
        private Long mTotalCount;

        public Boolean getIsEnd() {
            return mIsEnd;
        }

        public void setIsEnd(Boolean isEnd) {
            mIsEnd = isEnd;
        }

        public Long getPageableCount() {
            return mPageableCount;
        }

        public void setPageableCount(Long pageableCount) {
            mPageableCount = pageableCount;
        }

        public SameName getSameName() {
            return mSameName;
        }

        public void setSameName(SameName sameName) {
            mSameName = sameName;
        }

        public Long getTotalCount() {
            return mTotalCount;
        }

        public void setTotalCount(Long totalCount) {
            mTotalCount = totalCount;
        }

    }

    public static class Document {

        @SerializedName("address_name")
        private String mAddressName;
        @SerializedName("category_group_code")
        private String mCategoryGroupCode;
        @SerializedName("category_group_name")
        private String mCategoryGroupName;
        @SerializedName("category_name")
        private String mCategoryName;
        @SerializedName("distance")
        private String mDistance;
        @SerializedName("id")
        private String mId;
        @SerializedName("phone")
        private String mPhone;
        @SerializedName("place_name")
        private String mPlaceName;
        @SerializedName("place_url")
        private String mPlaceUrl;
        @SerializedName("road_address_name")
        private String mRoadAddressName;
        @SerializedName("x")
        private double mX;
        @SerializedName("y")
        private double mY;

        public String getAddressName() {
            return mAddressName;
        }

        public void setAddressName(String addressName) {
            mAddressName = addressName;
        }

        public String getCategoryGroupCode() {
            return mCategoryGroupCode;
        }

        public void setCategoryGroupCode(String categoryGroupCode) {
            mCategoryGroupCode = categoryGroupCode;
        }

        public String getCategoryGroupName() {
            return mCategoryGroupName;
        }

        public void setCategoryGroupName(String categoryGroupName) {
            mCategoryGroupName = categoryGroupName;
        }

        public String getCategoryName() {
            return mCategoryName;
        }

        public void setCategoryName(String categoryName) {
            mCategoryName = categoryName;
        }

        public String getDistance() {
            return mDistance;
        }

        public void setDistance(String distance) {
            mDistance = distance;
        }

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getPhone() {
            return mPhone;
        }

        public void setPhone(String phone) {
            mPhone = phone;
        }

        public String getPlaceName() {
            return mPlaceName;
        }

        public void setPlaceName(String placeName) {
            mPlaceName = placeName;
        }

        public String getPlaceUrl() {
            return mPlaceUrl;
        }

        public void setPlaceUrl(String placeUrl) {
            mPlaceUrl = placeUrl;
        }

        public String getRoadAddressName() {
            return mRoadAddressName;
        }

        public void setRoadAddressName(String roadAddressName) {
            mRoadAddressName = roadAddressName;
        }

        public double getX() {
            return mX;
        }

        public void setX(double x) {
            mX = x;
        }

        public double getY() {
            return mY;
        }

        public void setY(double y) {
            mY = y;
        }

    }
}
