package com.example.root.zomato;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 1/9/15.
 */
public class CuisineFragmentTab extends Fragment {
    JSONArray json;
    ListView listView;
    private ProgressBar progressBar;
    public Context context;
    public final static String URL = "https://api.zomato.com/v1/";
    public final static String apiKey = "7749b19667964b87a3efc739e254ada2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentlayout, container, false);
        listView = (ListView) v.findViewById(R.id.locality);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
//        String cityid=getArguments().getString("City_id");
        String city = ((TabListing) getActivity()).city_id;
        runcuisine(city);

        return v;
    }

    public void runcuisine(String city_id) {
        new cuisine().execute("1", "cuisines", "cuisine", "cuisine_id", "cuisine_name", city_id);
    }

    private class cuisine extends AsyncTask<String, Void, List<Cuisine>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Cuisine> doInBackground(String... params) {
            try {
                JSONArray cuisines = cuisineList("cuisines.json?city_id=" + params[5]);
                List<Cuisine> arrayOfCuisine = new ArrayList<Cuisine>();
                for (int i = 0; i < cuisines.length(); i++) {
                    JSONObject cuisine = cuisines.getJSONObject(i);
                    JSONObject cuisineDetail = cuisine.getJSONObject(params[2]);
                    arrayOfCuisine.add(new Cuisine(cuisineDetail.getString(params[4]), cuisineDetail.getString(params[3]), params[5]));
                }
                return arrayOfCuisine;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Cuisine> s) {
//            super.onPostExecute(s);
            CuisineAdapter adapter = new CuisineAdapter(getActivity(), R.layout.row_layout);
            for (int i = 0; i < s.size(); i++) {
                adapter.add(s.get(i));
            }
            progressBar.setVisibility(View.INVISIBLE);
            listView.setAdapter(adapter);
        }
    }

    public JSONArray cuisineList(String query) throws IOException, JSONException {
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
        return localityObject.getJSONArray("cuisines");
    }
}