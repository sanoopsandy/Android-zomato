package com.example.root.test2;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tabs extends AppCompatActivity implements View.OnClickListener {

    TabHost th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        Button newTab = (Button)findViewById(R.id.bAddTab);
        Button bStart = (Button)findViewById(R.id.bStart);
        Button bStop = (Button)findViewById(R.id.bStop);
        newTab.setOnClickListener(this);
        th = (TabHost)findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("Tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        specs = th.newTabSpec("Tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);
        specs = th.newTabSpec("Tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Tab 3");
        th.addTab(specs);
    }

    public void onClick(View arg0){
        switch (arg0.getId()){
            case R.id.bAddTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You've Created a new tab");
                        return (text);
                    }
                });
                ourSpec.setIndicator("NEW");
                th.addTab(ourSpec);
                break;
            case R.id.bStart:

                break;
            case R.id.bStop:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
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
