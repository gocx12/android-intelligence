package com.example.androidintelligence;

import android.os.Bundle;

import com.example.androidintelligence.infrastructure.SystemFacade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

import android.content.Intent;
import android.provider.Settings;
import android.widget.Button;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidintelligence.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        HashMap<String, String> appNameToPackageName = SystemFacade.getAllInstalledApp(MainActivity.this);
        // 将HashMap内容转换为String
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : appNameToPackageName.entrySet()) {
            // 过滤掉appName以com.开头的元素
            if(entry.getKey().startsWith("com.")) {
                continue;
            }
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // 显示在TextView上
        TextView textView = findViewById(R.id.textView);
        textView.setText(sb.toString());

        Button btnEnableService = findViewById(R.id.btn_enable_service);
        btnEnableService.setOnClickListener(v -> {
            // 跳转到无障碍设置页面
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        });

        Button btnCallPhone = findViewById(R.id.btn_call_phone);
        btnCallPhone.setOnClickListener(v -> {
            // 打开微信应用
            new Handler().postDelayed(() -> {
                String pacakgeName = appNameToPackageName.get("微信");

                SystemFacade.launchApp(MainActivity.this, pacakgeName);
            }, 1000);
        });


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}