package com.jemplex.jemplex;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jemplex.jemplex.response.AppMenu;
import com.jemplex.jemplex.ui.DownloadActivity;
import com.jemplex.jemplex.util.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPageActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        Toolbar myToolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(myToolbar);
        if (savedInstanceState == null) {
            init();
        }
    }

    private void init() {
        progressDialog = new ProgressDialog(MenuPageActivity.this);
        strToken = SharedPreferences.instance(this).fetchValueString("ACCESS_TOKEN");
        api = APIClient.getClient().create(FATAPIInterface.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        callApi();
    }

    FATAPIInterface api;
    public static String strToken;

    private void callApi() {
        progressDialog.setMessage("please wait...Fetching data");
        progressDialog.show();
        if (!strToken.isEmpty()) {
            Log.e("###", "strToken" + strToken);
            Call<List<AppMenu>> call = api.GetMenu(strToken);
            call.enqueue(new Callback<List<AppMenu>>() {
                @Override
                public void onResponse(@NotNull Call<List<AppMenu>> call, @NotNull Response<List<AppMenu>> response) {
                    progressDialog.dismiss();
                    List<AppMenu> lstMenu = response.body();
                    assert lstMenu != null;
                    ArrayList<AppMenu> arMenu = new ArrayList<>(lstMenu.size());
                    addFragment((ArrayList<AppMenu>) lstMenu);
                }

                @Override
                public void onFailure(@NotNull Call<List<AppMenu>> call, @NotNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(MenuPageActivity.this, "Error please try after some time!", Toast.LENGTH_LONG).show();
                    call.cancel();
                }
            });
        } else {
            Toast.makeText(MenuPageActivity.this, "Error please try after some time!", Toast.LENGTH_LONG).show();
            goBackToLogin();
        }
    }

    private void addFragment(ArrayList<AppMenu> arMenu) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("valuesArray", arMenu);
        FATMenuFragment datFragment = new FATMenuFragment();
        datFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, datFragment, null)
                .commit();
    }

    public void goBackToLogin() {
        Intent i = new Intent(MenuPageActivity.this, MainActivity.class);
        startActivity(i);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
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


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_Upload:
                intent = new Intent(MenuPageActivity.this, UploadActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_Download:
                intent = new Intent(MenuPageActivity.this, DownloadActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(MenuPageActivity.this, SettingPage.class);
                startActivity(intent);
                return true;
            case R.id.action_LogOut:
                showLogoutDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPageActivity.this); //Home is name of the activity
        builder.setMessage("Are you sure want to logout?");
        builder.setPositiveButton("OK", (dialog, id) -> {
            goBackToLogin();
        });
        builder.setNegativeButton("CANCEL", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

}