package com.example.root.test2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfCities extends AppCompatActivity {

    TextView textView;
    ListView listView;
    JSONArray json;
    public final static String URL = "https://api.zomato.com/v1/";
    public final static String apiKey = "7749b19667964b87a3efc739e254ada2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_cities);
        new read().execute("1", "cities", "city", "name", "id");
    }

    private class read extends AsyncTask<String, Void, List<City>> {
        @Override
        protected List<City> doInBackground(String... params) {
            try {
                JSONArray cities = cityList("cities.json/1");
                List<City> arrayOfCity = new ArrayList<City>();
//                String[] ArrayOfCity = new String[cities.length()];
                for(int i = 0; i < cities.length(); i++){
                    JSONObject city = cities.getJSONObject(i);
//                    ArrayOfCity[i] = city.getString(params[3]);
//                    arrayOfCity[i] = new City(city.getString(params[3]), city.getString(params[4]));
                    arrayOfCity.add(new City(city.getString(params[3]), city.getString(params[4])));

                }
                return arrayOfCity;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<City> s) {
//            super.onPostExecute(s);
            listView = (ListView)findViewById(R.id.city_list);

            CityAdapter adapter = new CityAdapter(getApplicationContext(), R.layout.row_layout);
            for(int i =0; i < s.size(); i++){
                adapter.add(s.get(i));
            }
//            textView.setText(s);
        }
    }

    public JSONArray cityList(String query) throws IOException, JSONException{
        OkHttpClient client = new OkHttpClient();
        StringBuilder url = new StringBuilder(URL);
        url.append(query);
        Request request = new Request.Builder()
                .url(String.valueOf(url))
                .addHeader("X-Zomato-API-Key", apiKey)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        JSONObject citiesObject = new JSONObject(result);
        return citiesObject.getJSONArray("cities");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_cities, menu);
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
