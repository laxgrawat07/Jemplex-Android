package com.jemplex.jemplex;

import android.content.Context;
import android.util.Log;

import com.jemplex.jemplex.util.SharedPreferences;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class APIClient {

//    private static String BASE_URL = "https://fatwebapi.azurewebsites.net/fat/";


    public static Retrofit getClient() {
        String BASE_URL;
        BASE_URL = (SharedPreferences.instance().fetchValueString("BASE_URL"));

        Log.e("###", "BASE_URL2: " + BASE_URL);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //noinspection deprecation
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
