package com.jemplex.jemplex.util;

import android.app.Activity;
import android.content.Context;

public class SharedPreferences {
    public static SharedPreferences _instance;

    Context context;
    android.content.SharedPreferences sharedPref;
    android.content.SharedPreferences.Editor sharedPrefEditor;

    public static SharedPreferences instance(Context context) {
        if (_instance == null) {
            _instance = new SharedPreferences();
            _instance.configSessionUtils(context);
        }
        return _instance;
    }

    public static SharedPreferences instance() {
        return _instance;
    }

    public void configSessionUtils(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences("AppPreferences", Activity.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }

    public void storeValueString(String key, String value) {
        sharedPrefEditor.putString(key, value);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    public String fetchValueString(String key) {
        return sharedPref.getString(key, null);
    }

    public void storeValueBoolean(String key, Boolean value) {
        sharedPrefEditor.putBoolean(key, value);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    public Boolean fetchValueBoolean(String key) {
        return sharedPref.getBoolean(key, false);
    }

    public void clearValue() {
        sharedPrefEditor.clear();
    }
}
