package com.jemplex.jemplex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.jemplex.jemplex.response.AppMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarMenu);
        setSupportActionBar(myToolbar);
        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            ArrayList<AppMenu> lstMenu = b.getParcelableArrayList("valuesArray");
            FATMenuFragment datFragment = new FATMenuFragment();
            datFragment.setArguments(b);

            /*FragmentClass fragInfo = new FragmentClass();
            fragInfo.setArguments(bundle);
            transaction.replace(R.id.fragment_single, fragInfo);
            transaction.commit();*/
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, datFragment, null)
                    .commit();
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
            case R.id.action_Upload:
                PageIntent = new Intent(MenuPageActivity.this, UploadActivity.class);
                startActivity(PageIntent);
                return true;
            case R.id.action_settings:
                PageIntent = new Intent(MenuPageActivity.this, SettingPage.class);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}