
package com.mia.outsiderhelper.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BlogResponse {

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

    public static class Document {

        @SerializedName("blogname")
        private String mBlogname;
        @SerializedName("contents")
        private String mContents;
        @SerializedName("datetime")
        private String mDatetime;
        @SerializedName("thumbnail")
        private String mThumbnail;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("url")
        private String mUrl;

        public String getBlogname() {
            return mBlogname;
        }

        public void setBlogname(String blogname) {
            mBlogname = blogname;
        }

        public String getContents() {
            return mContents;
        }

        public void setContents(String contents) {
            mContents = contents;
        }

        public String getDatetime() {
            return mDatetime;
        }

        public void setDatetime(String datetime) {
            mDatetime = datetime;
        }

        public String getThumbnail() {
            return mThumbnail;
        }

        public void setThumbnail(String thumbnail) {
            mThumbnail = thumbnail;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

    }

    @SuppressWarnings("unused")
    public static class Meta {

        @SerializedName("is_end")
        private Boolean mIsEnd;
        @SerializedName("pageable_count")
        private Long mPageableCount;
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

        public Long getTotalCount() {
            return mTotalCount;
        }

        public void setTotalCount(Long totalCount) {
            mTotalCount = totalCount;
        }

    }
}
