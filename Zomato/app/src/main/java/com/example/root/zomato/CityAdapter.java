package com.example.root.zomato;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 28/8/15.
 */
public class CityAdapter extends ArrayAdapter<City> {

    public Context context;
    public final static String MESSAGE = "com.example.root.test3.MESSAGE";

    private List<City> list = new ArrayList();
    public CityAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void add(City object) {
        list.add(object);
        super.add(object);
    }

    static class Holder{
        TextView NAME;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
            holder = new Holder();
            holder.NAME = (TextView) row.findViewById(R.id.Name);
            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }
        final City city = (City) getItem(position);
        holder.NAME.setText(city.getName());
        final View.OnClickListener makeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TabListing.class);
                intent.putExtra(MESSAGE, city.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        };

        row.setOnClickListener(makeListener);

        return row;
    }


}
