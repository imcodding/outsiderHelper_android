package com.mia.outsiderhelper.main.fragment.store.restaurant.blog;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mia.outsiderhelper.R;
import com.mia.outsiderhelper.models.BlogResponse;

import java.util.List;

import static com.mia.outsiderhelper.ApplicationClass.DATE_FORMAT;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogHolder> {
    List<BlogResponse.Document> blogList;

    @NonNull
    @Override
    public BlogListAdapter.BlogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, null);
        BlogHolder holder = new BlogHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BlogListAdapter.BlogHolder holder, int position) {
        BlogResponse.Document blogItem = blogList.get(position);

        Glide.with(holder.itemView.getContext()).load(blogItem.getThumbnail()).into(holder.blogThumbnail);
        holder.blogName.setText(blogItem.getBlogname());
        holder.blogTitle.setText(htmlToString(blogItem.getTitle()));
        holder.blogContents.setText(htmlToString(blogItem.getContents()));
        holder.blogDateTime.setText(dateToString(blogItem.getDatetime()));
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class BlogHolder extends RecyclerView.ViewHolder {
        ImageView blogThumbnail;
        TextView blogName;
        TextView blogTitle;
        TextView blogContents;
        TextView blogDateTime;

        public BlogHolder(@NonNull View itemView) {
            super(itemView);

            blogThumbnail = itemView.findViewById(R.id.item_blog_iv_thumbnail);
            blogName = itemView.findViewById(R.id.item_blog_tv_blogName);
            blogTitle = itemView.findViewById(R.id.item_blog_tv_title);
            blogContents = itemView.findViewById(R.id.item_blog_tv_contents);
            blogDateTime = itemView.findViewById(R.id.item_blog_tv_dateTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BlogResponse.Document blogItem = blogList.get(getAdapterPosition());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(blogItem.getUrl());
                    intent.setData(uri);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public void setBlogList(List<BlogResponse.Document> blogList) {
        this.blogList = blogList;
    }

    public void addBlogList(List<BlogResponse.Document> blogList) { this.blogList.addAll(blogList); }

    private String htmlToString(String text) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString();
        }
        return Html.fromHtml(text).toString();
    }

    private String dateToString(String date) {
        int index = date.indexOf("T");
        String dateStr = date.substring(0, index);

        return dateStr;
    }
}
