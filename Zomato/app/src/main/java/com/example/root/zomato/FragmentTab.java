package com.example.root.zomato;

/**
 * Created by root on 1/9/15.
 */
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

public class FragmentTab extends Fragment {
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
//        ((TabListing) getActivity()).city_id;

        String city = ((TabListing) getActivity()).city_id;
        run(city);
//        TextView tv = (TextView) v.findViewById(R.id.text);
//        tv.setText(this.getTag() + " Content");
        return v;
    }

    public void run(String city_id){
        new locality().execute("1", "subzones", "subzone", "name", "zone_id", "subzone_id", "city_id", city_id);
    }

    private class locality extends AsyncTask<String, Void, List<SubZone>> {

        @Override
        protected void onPreExecute() {
//            progressBar = (ProgressBar)findViewById(R.id.progressBar1);
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
//            listView = (ListView) findViewById(R.id.locality);
            SubZoneAdapter adapter = new SubZoneAdapter(getActivity(), R.layout.row_layout);
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
}
