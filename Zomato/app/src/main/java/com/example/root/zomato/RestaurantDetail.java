package com.example.root.zomato;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class RestaurantDetail extends AppCompatActivity {

    JSONArray json;
    ListView listView;
    EditText editText;
    TextView textView;
    ImageView imageView;
    private ProgressBar progressBar;
    public final static String URL = "https://api.zomato.com/v1/";
    public final static String apiKey = "7749b19667964b87a3efc739e254ada2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        Intent intent = getIntent();
        String rest_id = intent.getStringExtra(RestaurantAdapter.MESSAGE);
        String cuisineRest_id = intent.getStringExtra(RestaurantCuisineAdapter.MESSAGE);
        new restaurantDetail().execute("1", "name", "address", "city", "phone", "timings", "avgCostForTwo", "cuisines", "reviewText", rest_id, cuisineRest_id);
    }

    private class restaurantDetail extends AsyncTask<String, Void, List<Restaurant>> {

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
        protected List<Restaurant> doInBackground(String... params) {
            try {
                if (params[5] != null) {
                    JSONObject restaurants = restDetail("restaurant.json/" + params[5]);
                    String[] restaurantSpecs = new String[7];
                    restaurantSpecs[0] = restaurants.getString(params[1]);
                    JSONObject address = restaurants.getJSONObject("location");
                    restaurantSpecs[1] = address.getString(params[2]);
                    restaurantSpecs[2] = address.getString(params[3]);
                    restaurantSpecs[3] = restaurants.getString(params[4]);
                    restaurantSpecs[4] = restaurants.getString(params[5]);
                    restaurantSpecs[5] = restaurants.getString(params[6]);
                    restaurantSpecs[6] = restaurants.getString(params[7]);
                    JSONObject review = restaurants.getJSONObject("userReviews");
                    JSONObject editor = review.getJSONObject("0");
                    JSONObject editoReview = editor.getJSONObject("review");
                    restaurantSpecs[7] = editoReview.getString(params[8]);
                } else {
                    JSONObject restaurants = restDetail("restaurant.json/" + params[6]);
                    String[] restaurantSpecs = new String[7];
                    restaurantSpecs[0] = restaurants.getString(params[1]);
                    JSONObject address = restaurants.getJSONObject("location");
                    restaurantSpecs[1] = address.getString(params[2]);
                    restaurantSpecs[2] = address.getString(params[3]);
                    restaurantSpecs[3] = restaurants.getString(params[4]);
                    restaurantSpecs[4] = restaurants.getString(params[5]);
                    restaurantSpecs[5] = restaurants.getString(params[6]);
                    restaurantSpecs[6] = restaurants.getString(params[7]);
                    JSONObject review = restaurants.getJSONObject("userReviews");
                    JSONObject editor = review.getJSONObject("0");
                    JSONObject editoReview = editor.getJSONObject("review");
                    restaurantSpecs[7] = editoReview.getString(params[8]);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(List<Restaurant> s) {


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_detail, menu);
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
