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
 * Created by root on 31/8/15.
 */
public class CuisineAdapter extends ArrayAdapter<Cuisine> {

    public Context context;
    public final static String MESSAGE = "com.example.root.test3.MESSAGE";
    public final static String ID = "com.example.root.test3.ID";

    private List<Cuisine> list = new ArrayList();

    public CuisineAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Cuisine object) {
        list.add(object);
        super.add(object);
    }

    static class Holder{
        TextView CuisineName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
            holder = new Holder();
            holder.CuisineName = (TextView) row.findViewById(R.id.Name);
            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }
        final Cuisine cuisine = (Cuisine) getItem(position);
        holder.CuisineName.setText(cuisine.getName());
        final View.OnClickListener makeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantDetail.class);
                intent.putExtra(MESSAGE, cuisine.getId());
                intent.putExtra(ID, cuisine.getCity_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        };

        row.setOnClickListener(makeListener);

        return row;
    }
}
