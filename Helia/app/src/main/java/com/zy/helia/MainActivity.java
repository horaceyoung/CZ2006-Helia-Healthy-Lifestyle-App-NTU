package com.zy.helia;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mainViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        MainPageAdapter mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainPageAdapter);

    }
}
