package com.mia.outsiderhelper.util;

import android.content.SharedPreferences;

import static com.mia.outsiderhelper.ApplicationClass.sSharedPreferences;

public class SharedPreferenceUtil {
    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return sSharedPreferences.getString(key, null);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        return sSharedPreferences.getBoolean(key, false);
    }
}
