package com.mia.outsiderhelper.main.fragment.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.mia.outsiderhelper.R;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

    private final Context mContext;
    private final ArrayList<String> mImages;

    public ImageAdapter(Context context, ArrayList<String> images) {
        this.mContext = context;
        this.mImages = images;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if(mContext != null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.food_image_page, container, false);
            ImageView imageView = view.findViewById(R.id.page_food_image);

            try {
                String url = mImages.get(position);
                Glide.with(mContext).load(url).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
