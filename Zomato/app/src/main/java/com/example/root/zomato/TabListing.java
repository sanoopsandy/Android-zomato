package com.example.root.zomato;

import android.app.ActivityGroup;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class TabListing extends FragmentActivity {
    private FragmentTabHost mTabHost;
    static String city_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab_listing);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        Intent intent = getIntent();
        city_id = intent.getStringExtra(CityAdapter.MESSAGE);
        FragmentTab fragment = new FragmentTab();
        Bundle bundle = new Bundle();
        bundle.putString("City_id", city_id);
        fragment.setArguments(bundle);
        mTabHost.addTab(
                mTabHost.newTabSpec("cuisine").setIndicator("Cuisine", null),
                CuisineFragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("subzone").setIndicator("Sub Zone", null),
                FragmentTab.class, null);

    }
}

