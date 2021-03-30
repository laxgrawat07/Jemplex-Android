package com.jemplex.jemplex.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jemplex.jemplex.MainActivity;
import com.jemplex.jemplex.MenuPageActivity;
import com.jemplex.jemplex.R;
import com.jemplex.jemplex.util.SharedPreferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
//        String BASE_URL = "https://fatwebapi.azurewebsites.net:8004/fat/";
        String BASE_URL = "http://acuberfid.fortiddns.com:8004/";
        SharedPreferences.instance(this);
        SharedPreferences.instance().storeValueString("BASE_URL", BASE_URL);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            //Write whatever to want to do after delay specified (1 sec)
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }, 1000);

    }
}