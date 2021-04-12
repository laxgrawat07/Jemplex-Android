package com.jemplex.jemplex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jemplex.jemplex.util.SharedPreferences;

public class SettingPage extends AppCompatActivity implements View.OnClickListener {

    private EditText baseUrlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        Toolbar myToolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(myToolbar);
        baseUrlText = findViewById(R.id.baseEditText);
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //    Constants.instance().storeValueString("companyKey", "Brainwash Inc.");
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent PageIntent;
        // Handle item selection
        if (item.getItemId() == R.id.action_Upload) {
            PageIntent = new Intent(SettingPage.this, UploadActivity.class);
            startActivity(PageIntent);
            return true;
        }
        if (item.getItemId() == R.id.action_Login) {
            PageIntent = new Intent(SettingPage.this, MainActivity.class);
            startActivity(PageIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem Login = menu.findItem(R.id.action_Login);
        Login.setVisible(true);
        MenuItem Download = menu.findItem(R.id.action_Download);
        Download.setVisible(true);
        MenuItem LogOut = menu.findItem(R.id.action_LogOut);
        LogOut.setVisible(false);
        MenuItem Upload = menu.findItem(R.id.action_Upload);
        Upload.setVisible(true);
        MenuItem Settings = menu.findItem(R.id.action_settings);
        Settings.setVisible(false);
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            if (!baseUrlText.getText().toString().matches("")) {
                SharedPreferences.instance().storeValueString("BASE_URL", baseUrlText.getText().toString());
                Toast.makeText(getApplicationContext(), "Path saved successfully.", Toast.LENGTH_SHORT).show();
                baseUrlText.setText("");
                Log.e("###", "BASE_URL1: " + (SharedPreferences.instance().fetchValueString("BASE_URL")));
            }
            else
                Toast.makeText(getApplicationContext(), "Please enter some value.", Toast.LENGTH_LONG).show();

        }
    }
}