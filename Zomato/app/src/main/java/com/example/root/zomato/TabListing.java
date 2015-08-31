package com.example.root.zomato;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class TabListing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_listing);
        Resources resources = getResources();
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        Intent intentSubZone = new Intent().setClass(this, SubZoneList.class);
        TabHost.TabSpec specs = tabHost.newTabSpec("Tag1")
                .setContent(intentSubZone)
                .setIndicator("SubZone");

        Intent intentCuisine = new Intent().setClass(this, CuisineList.class);
        TabHost.TabSpec cuisinespecs = tabHost.newTabSpec("Tag1")
                .setContent(intentCuisine)
                .setIndicator("Cuisine");

        tabHost.addTab(specs);
        tabHost.addTab(cuisinespecs);
        tabHost.setCurrentTab(0);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_listing, menu);
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
}
