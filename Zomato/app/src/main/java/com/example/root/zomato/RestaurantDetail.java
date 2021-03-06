package com.example.root.zomato;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RestaurantDetail extends AppCompatActivity {

    TextView name, address, city, phone, timings, avgCostForTwo, cuisines, reviewText;
    ImageView imageView;
    private ProgressBar progressBar, imageprogressbar;
    public final static String URL = "https://api.zomato.com/v1/";
    public final static String apiKey = "7749b19667964b87a3efc739e254ada2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        Intent intent = getIntent();
        String rest_id = intent.getStringExtra(RestaurantAdapter.MESSAGE);
        new restaurantDetail().execute("1", "name", "address", "city", "phone", "timings", "avgCostForTwo", "cuisines", "reviewText", rest_id);
        new downloadImageTask(imageView).execute(rest_id);
    }

    private class downloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        protected void onPreExecute() {
            imageprogressbar = (ProgressBar) findViewById(R.id.imageProgress);
            imageprogressbar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public downloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... params) {
            try {
                JSONObject restaurants = restDetail("restaurant.json/" + params[0]);
                JSONObject image = restaurants.getJSONObject("photos");
                JSONObject firstpic = image.getJSONObject("0");
                JSONObject photo = firstpic.getJSONObject("photo");
                String urldisplay = photo.getString("url");
                Bitmap mIcon11 = null;
                InputStream is = (InputStream) new URL(urldisplay).getContent();
                mIcon11 = BitmapFactory.decodeStream(is);
                return mIcon11;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                bmImage = (ImageView)findViewById(R.id.imageView);
                bmImage.setImageBitmap(result);
            }else {
                imageView.setImageResource(R.drawable.placeholder);
            }
            imageprogressbar.setVisibility(View.INVISIBLE);
        }
    }
    private class restaurantDetail extends AsyncTask<String, Void, String[]> {

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
        protected String[] doInBackground(String... params) {
            String urldisplay;
            try {
                JSONObject restaurants = restDetail("restaurant.json/" + params[9]);
                String[] restaurantSpecs = new String[10];
                restaurantSpecs[0] = restaurants.getString(params[1]);
                JSONObject address = restaurants.getJSONObject("location");
                restaurantSpecs[1] = address.getString(params[2]);
                restaurantSpecs[2] = address.getString(params[3]);
                restaurantSpecs[3] = restaurants.getString(params[4]);
                restaurantSpecs[4] = restaurants.getString(params[5]);
                restaurantSpecs[5] = restaurants.getString(params[6]);
                restaurantSpecs[6] = restaurants.getString(params[7]);
                JSONObject review = restaurants.getJSONObject("userReviews");
                if (review.length() != 2) {
                    JSONObject editor = review.getJSONObject("0");
                    JSONObject editoReview = editor.getJSONObject("review");
                    restaurantSpecs[7] = editoReview.getString(params[8]);
                }else{
                    restaurantSpecs[7] = "No Review found";
                }

                return restaurantSpecs;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String[] s) {
            name = (TextView)findViewById(R.id.Rname);
            address = (TextView)findViewById(R.id.address);
            city = (TextView)findViewById(R.id.restCity);
            phone = (TextView)findViewById(R.id.phone);
            timings = (TextView)findViewById(R.id.timing);
            avgCostForTwo = (TextView)findViewById(R.id.avgCost);
            cuisines = (TextView)findViewById(R.id.cuisineRest);
            reviewText = (TextView)findViewById(R.id.review);
            name.setText("Name : " + s[0]);
            address.setText("Address : " + s[1]);
            city.setText("City : " + s[2]);
            phone.setText("Phone : " + s[3]);
            timings.setText("timings : " + s[4]);
            avgCostForTwo.setText("Average Cost For Two : " + s[5]);
            cuisines.setText("Cuisines : " + s[6]);
            reviewText.setText("Review : " + s[7]);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public JSONObject restDetail(String query) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        StringBuilder url = new StringBuilder(URL);
        url.append(query);
        Request request = new Request.Builder()
                .url(String.valueOf(url))
                .addHeader("X-Zomato-API-Key", apiKey)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        JSONObject restaurantObject = new JSONObject(result);
        return restaurantObject;

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
