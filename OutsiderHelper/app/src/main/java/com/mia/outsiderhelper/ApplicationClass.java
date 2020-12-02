package com.mia.outsiderhelper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    // SharedPreferences 키 값
    public static String TAG = "OUTSIDER_APP";
    public static SharedPreferences sSharedPreferences = null;

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.KOREA);
//    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Firebase 데이터베이스
    public static DatabaseReference mPostReference;

    // Retrofit 인스턴스
    public static Retrofit retrofit;

    public static String USER_ID;

    public final static int SUCCESS_CODE = 200;
    public final static int FAILURE_CODE = 400;

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

    public static Retrofit getRetrofit(String BASE_URL) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}