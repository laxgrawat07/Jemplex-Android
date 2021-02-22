package com.jemplex.jemplex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class AssetTagging extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private Button btnNext;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_tagging);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_asset);
        setSupportActionBar(myToolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Scan"));
        tabLayout.addTab(tabLayout.newTab().setText("Status Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Classification"));
        tabLayout.addTab(tabLayout.newTab().setText("Location"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final AssetTaggingAdapter adapter = new AssetTaggingAdapter(this, getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int item = viewPager.getCurrentItem();
                if(item <= 3) {
                    viewPager.setCurrentItem(item+1, true);
                }
            }
        });
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int item = viewPager.getCurrentItem();
                if(item > 0) {
                    viewPager.setCurrentItem(item-1, true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent PageIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_Upload:
                PageIntent = new Intent(AssetTagging.this, UploadActivity.class);
                startActivity(PageIntent);
                return true;
            case R.id.action_settings:
                PageIntent = new Intent(AssetTagging.this, SettingPage.class);
                startActivity(PageIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem Download = menu.findItem(R.id.action_Download);
        Download.setVisible(true);
        MenuItem LogOut = menu.findItem(R.id.action_LogOut);
        LogOut.setVisible(true);
        MenuItem Upload = menu.findItem(R.id.action_Upload);
        Upload.setVisible(true);
        MenuItem Settings = menu.findItem(R.id.action_settings);
        Settings.setVisible(false);
        return true;
    }
}