package com.example.root.zomato;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
public class SubZoneAdapter extends ArrayAdapter<SubZone> {
    public Context context;
    public final static String MESSAGE = "com.example.root.test3.MESSAGE";

    private List<SubZone> list = new ArrayList();
    public SubZoneAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void add(SubZone object) {
        list.add(object);
        super.add(object);
    }

    static class Holder{
        TextView SubZoneName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
            holder = new Holder();
            holder.SubZoneName = (TextView) row.findViewById(R.id.Name);
            holder.SubZoneName.setTextColor(Color.BLACK);
            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }
        final SubZone subZone = (SubZone) getItem(position);
        holder.SubZoneName.setText(subZone.getName());
        final View.OnClickListener makeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantInSubZone.class);
                intent.putExtra(MESSAGE, subZone.getSubzone_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        };

        row.setOnClickListener(makeListener);

        return row;
    }
}
