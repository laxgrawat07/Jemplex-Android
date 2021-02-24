package com.jemplex.jemplex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jemplex.jemplex.response.AppMenu;
import com.jemplex.jemplex.response.Authenticate;
import com.jemplex.jemplex.util.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtComanyName;
    private EditText txtUser;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView txtSettings;
    FATAPIInterface api;
    public static String ApplicationId = "FATMOBILEAPP";
    Authenticate userDetails;
    public static String strRole;
    public static String strToken;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        progressDialog = new ProgressDialog(MainActivity.this);
        txtUser = findViewById(R.id.editTextTextPersonName);
        txtPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtComanyName = findViewById(R.id.txtComanyCode);
//        String Host = txtComanyName.getText().toString();
        init();

    }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences.instance(this);
        api = APIClient.getClient().create(FATAPIInterface.class);
    }

    private void init() {
        btnLogin.setOnClickListener(this);
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
                PageIntent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(PageIntent);
                return true;
            case R.id.action_settings:
                PageIntent = new Intent(MainActivity.this, SettingPage.class);
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
        LogOut.setVisible(false);
        MenuItem Upload = menu.findItem(R.id.action_Upload);
        Upload.setVisible(true);
        MenuItem Settings = menu.findItem(R.id.action_settings);
        Settings.setVisible(true);
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            progressDialog.setMessage("please wait...");
            progressDialog.show();
            String strUser = txtUser.getText().toString();
            String strPassword = txtPassword.getText().toString();
            if (strUser.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please Enter User Id and Password!", Toast.LENGTH_SHORT).show();
            } else {
                Authenticate user = new Authenticate(txtComanyName.getText().toString(), ApplicationId, txtUser.getText().toString(), txtPassword.getText().toString());

                Call<Authenticate> Auth = api.authUser(user);
                Auth.enqueue(new Callback<Authenticate>() {
                    @Override
                    public void onResponse(Call<Authenticate> call, Response<Authenticate> response) {
                        userDetails = response.body();
                        strToken = "Bearer" + " " + userDetails.token;
                        strRole = userDetails.roleName;
                        if (!userDetails.token.isEmpty()) {
                            Call<List<AppMenu>> call2 = api.GetMenu(strToken);
                            call2.enqueue(new Callback<List<AppMenu>>() {
                                @Override
                                public void onResponse(Call<List<AppMenu>> call, Response<List<AppMenu>> response) {
                                    progressDialog.dismiss();
                                    List<AppMenu> lstMenu = response.body();
//                                        List<AppMenu> Menu = lstMenu.stream()
//                                                .filter((p) -> p.applicationID.equals(ApplicationId))
//                                                .collect(Collectors.toList());

                                    Intent MenuIntent = new Intent(MainActivity.this, MenuPageActivity.class);
                                    Bundle bundle = new Bundle();
                                    ArrayList<AppMenu> arMenu = new ArrayList<>(lstMenu.size());
                                    arMenu.addAll(lstMenu);
                                    bundle.putParcelableArrayList("valuesArray", arMenu);
                                    MenuIntent.putExtras(bundle);
                                    startActivity(MenuIntent);
                                }

                                @Override
                                public void onFailure(Call<List<AppMenu>> call, Throwable t) {
                                    call.cancel();
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<Authenticate> call, Throwable t) {
                        progressDialog.dismiss();
                        call.cancel();
                        Toast.makeText(MainActivity.this, "Error please try after some time!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

    }
}