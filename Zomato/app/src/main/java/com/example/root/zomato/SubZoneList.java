package com.example.root.zomato;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubZoneList extends AppCompatActivity {

    JSONArray json;
    ListView listView;
    private ProgressBar progressBar;
    public final static String URL = "https://api.zomato.com/v1/";
    public final static String apiKey = "7749b19667964b87a3efc739e254ada2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_zone_list);
        Intent intent = getIntent();
        String city_id = intent.getStringExtra(CityAdapter.MESSAGE);
        new locality().execute("1", "subzones", "subzone", "name", "zone_id", "subzone_id", "city_id", city_id);
    }

    private class locality extends AsyncTask<String, Void, List<SubZone>> {

        @Override
        protected void onPreExecute() {
            progressBar = (ProgressBar) findViewById(R.id.progressBar1);
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<SubZone> doInBackground(String... params) {
            try {
                JSONArray subzones = subzoneList("subzones.json?city_id=" + params[7]);
                List<SubZone> arrayOfSubZones = new ArrayList<SubZone>();
                for (int i = 0; i < subzones.length(); i++){
                    JSONObject subzone = subzones.getJSONObject(i);
                    JSONObject localityDetail = subzone.getJSONObject(params[2]);
                    arrayOfSubZones.add(new SubZone(localityDetail.getString(params[4]), localityDetail.getString(params[3]), localityDetail.getString(params[5]), localityDetail.getString(params[6])));
                }
                return arrayOfSubZones;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<SubZone> s) {
//            super.onPostExecute(s);
            listView = (ListView)findViewById(R.id.locality);
            SubZoneAdapter adapter = new SubZoneAdapter(getApplicationContext(), R.layout.row_layout);
            for(int i =0; i < s.size(); i++){
                adapter.add(s.get(i));
            }
            progressBar.setVisibility(View.INVISIBLE);
            listView.setAdapter(adapter);
        }
    }

    public JSONArray subzoneList(String query) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder url = new StringBuilder(URL);
        url.append(query);
        Request request = new Request.Builder()
                .url(String.valueOf(url))
                .addHeader("X-Zomato-API-Key", apiKey)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        JSONObject localityObject = new JSONObject(result);
        return localityObject.getJSONArray("subzones");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_zone_list, menu);
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
