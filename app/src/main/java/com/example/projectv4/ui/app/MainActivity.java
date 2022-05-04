package com.example.projectv4.ui.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.projectv4.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager = null;
    FragmentPageAdapter fragmentPageAdapter = null;
    TabLayout tabLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();

    }
    public void initFragment(){
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager());


        fragmentPageAdapter.addFragment(new com.example.projectv4.ui.app.Cardiaque(R.layout.rythem_cardiaque_activity),"Heart");
        //fragmentPageAdapter.addFragment(new com.example.models.Bluetooth(R.layout.bluetooth_activity),"Bluetooth");
        //fragmentPageAdapter.addFragment(new com.example.models.Bluetooth(R.layout.map_activity),"Map");
        //fragmentPageAdapter.addFragment(new com.example.models.Bluetooth(R.layout.overview_activity),"OverView");

        viewPager.setAdapter(fragmentPageAdapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.view_pager_tab);
        tabLayout.setupWithViewPager(viewPager);
    }
}