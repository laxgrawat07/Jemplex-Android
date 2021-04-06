package com.jemplex.jemplex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jemplex.jemplex.response.AppMenu;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar myToolbar = findViewById(R.id.toolbarUpload);
        setSupportActionBar(myToolbar);
        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            ArrayList<AppMenu> lstMenu = (ArrayList<AppMenu>) b.get("valuesArray");
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            // set a LinearLayoutManager with default vertical orientation
            GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),2);
            recyclerView.setLayoutManager(linearLayoutManager);

            //  call the constructor of CustomAdapter to send the reference and data to Adapter
            ItemUploadAdapter customAdapter = new ItemUploadAdapter(lstMenu);
            recyclerView.setAdapter(customAdapter);
        }

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
            case R.id.action_settings:
                PageIntent = new Intent(UploadActivity.this, SettingPage.class);
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
        Upload.setVisible(false);
        MenuItem Settings = menu.findItem(R.id.action_settings);
        Settings.setVisible(false);
        MenuItem Login = menu.findItem(R.id.action_Login);
        Login.setVisible(false);
        return true;
    }
}