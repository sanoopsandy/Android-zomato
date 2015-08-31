package com.example.root.test2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by root on 26/8/15.
 */
public class Menu extends ListActivity {
    String classes[] = { "MainActivity", "TextPlay", "Tabs", "HttpExample", "OkHttp1", "ListOfCities"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String temp = classes[position];
        try {
            Class ourClass = Class.forName("com.example.root.test2." + temp);
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        }catch (ClassNotFoundException e){
            e.printStackTrace();;
        }
    }
}
