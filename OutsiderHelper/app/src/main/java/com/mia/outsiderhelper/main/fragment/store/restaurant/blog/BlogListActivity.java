package com.mia.outsiderhelper.main.fragment.store.restaurant.blog;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mia.outsiderhelper.BaseActivity;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces.BlogListActivityView;
import com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces.OnRefreshListener;
import com.mia.outsiderhelper.models.BlogResponse;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

public class BlogListActivity extends BaseActivity implements BlogListActivityView, OnRefreshListener {

    private BlogListService mBlogListService;
    private BlogListAdapter mBlogListAdapter;
    private SwipyRefreshLayout mBlogSwipeContainer;
    private RecyclerView mRvBlogList;

    private String mSearchName = "";
    private int mPageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list);

        initView();

        mBlogListService = new BlogListService(this);

        mSearchName = getIntent().getStringExtra("restaurantName");
        if(mSearchName != null) {
            showProgressDialog();
            mBlogListService.getBlogList(mSearchName, mPageNo);
        } else {
            showCustomToast(getString(R.string.store_blog_no_search));
            finish();
        }
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvBlogList = findViewById(R.id.blog_rv_list);
        mRvBlogList.setLayoutManager(manager);
        mBlogListAdapter = new BlogListAdapter();

        mBlogSwipeContainer = findViewById(R.id.blog_swipe_container);
        mBlogSwipeContainer.setOnRefreshListener(this::onRefresh);
    }

    @Override
    public void getBlogListSuccess(List<BlogResponse.Document> documents) {
        hideProgressDialog();
        if(mPageNo > 1) {
            mBlogListAdapter.addBlogList(documents);
            mBlogListAdapter.notifyDataSetChanged();
            return;
        }
        mBlogListAdapter.setBlogList(documents);
        mRvBlogList.setAdapter(mBlogListAdapter);
    }

    @Override
    public void getBlogListFailure(String message) {
        hideProgressDialog();
        showCustomToast(getString(R.string.store_blog_no_search));
        finish();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        showProgressDialog();
        if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            mPageNo = mPageNo + 1;
            mBlogListService.getBlogList(mSearchName, mPageNo);
            mBlogSwipeContainer.setRefreshing(false);
        } else if (direction == SwipyRefreshLayoutDirection.TOP) {
            mPageNo = 1;
            mBlogListService.getBlogList(mSearchName, mPageNo);
            mBlogSwipeContainer.setRefreshing(false);
        }
    }

    public void onBack() {

    }
}