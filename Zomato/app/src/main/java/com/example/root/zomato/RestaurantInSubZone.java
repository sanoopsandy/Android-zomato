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

public class RestaurantInSubZone extends AppCompatActivity {

    JSONArray json;
    ListView listView;
    private ProgressBar progressBar;
    public final static String URL = "https://api.zomato.com/v1/";
    public final static String apiKey = "7749b19667964b87a3efc739e254ada2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_in_sub_zone);
        Intent intent = getIntent();
        String subzone_id = intent.getStringExtra(SubZoneAdapter.MESSAGE);
        new restaurant().execute("1", "results", "result", "id", "name", subzone_id);
    }

    private class restaurant extends AsyncTask<String, Void, List<RestaurantList>> {

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
        protected List<RestaurantList> doInBackground(String... params) {
            try {
                JSONArray restaurants = restaurantList("search.json?subzone_id=" + params[5]);
                List<RestaurantList> arrayOfRestaurant = new ArrayList<RestaurantList>();
                for (int i = 0; i < restaurants.length(); i++){
                    JSONObject restaurant = restaurants.getJSONObject(i);
                    JSONObject restaurantDetail = restaurant.getJSONObject(params[2]);
                    arrayOfRestaurant.add(new RestaurantList(restaurantDetail.getString(params[4]), restaurantDetail.getString(params[3])));
                }
                return arrayOfRestaurant;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RestaurantList> s) {
//            super.onPostExecute(s);
            listView = (ListView)findViewById(R.id.restaurantList);
            RestaurantAdapter adapter = new RestaurantAdapter(getApplicationContext(), R.layout.row_layout);
            for(int i =0; i < s.size(); i++){
                adapter.add(s.get(i));
            }
            progressBar.setVisibility(View.INVISIBLE);
            listView.setAdapter(adapter);
//            setListAdapter(new ArrayAdapter<String>(Locality.this, android.R.layout.simple_list_item_1, s));
        }
    }

    public JSONArray restaurantList(String query) throws IOException, JSONException {
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
        return localityObject.getJSONArray("results");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_in_sub_zone, menu);
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
