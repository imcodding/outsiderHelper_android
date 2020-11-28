package com.mia.outsiderhelper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    // SharedPreferences 키 값
    public static String TAG = "OUTSIDER_APP";
    public static SharedPreferences sSharedPreferences = null;

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Firebase 데이터베이스
    public static DatabaseReference mPostReference;

    public static int userNo;

    public final static int SUCCESS_CODE = 200;

    @Override
    public void onCreate() {
        super.onCreate();

        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
    }

    public static DatabaseReference getDatabaseReference() {
        mPostReference = FirebaseDatabase.getInstance().getReference();

        return mPostReference;
    }
}