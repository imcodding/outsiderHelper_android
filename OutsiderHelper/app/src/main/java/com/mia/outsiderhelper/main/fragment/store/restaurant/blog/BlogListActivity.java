package com.mia.outsiderhelper.main.fragment.store.restaurant.blog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.main.fragment.store.restaurant.blog.interfaces.BlogListActivityView;
import com.mia.outsiderhelper.models.BlogResponse;

import java.util.List;

public class BlogListActivity extends AppCompatActivity implements BlogListActivityView {

    private BlogListService mBlogListService;
    private BlogListAdapter mBlogListAdapter;
    private RecyclerView mRvBlogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list);

        initView();
        mBlogListService = new BlogListService(this);
        mBlogListService.getBlogList("처갓집양념치킨 구로가산점", 1);
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvBlogList = findViewById(R.id.blog_rv_list);
        mRvBlogList.setLayoutManager(manager);

        mBlogListAdapter = new BlogListAdapter();
    }

    @Override
    public void getBlogListSuccess(List<BlogResponse.Document> documents) {
        mBlogListAdapter.setBlogList(documents);
        mRvBlogList.setAdapter(mBlogListAdapter);
    }

    @Override
    public void getBlogListFailure(String message) {

    }
}