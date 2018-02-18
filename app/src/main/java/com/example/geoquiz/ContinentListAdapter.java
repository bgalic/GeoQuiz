package com.example.geoquiz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContinentListAdapter extends BaseAdapter{

    Context context;
    ArrayList<Continent> continentList = new ArrayList<>();

    public ContinentListAdapter(Context context) {
        super();
        this.context = context;
        continentList.add(new Continent("Azija"));
        continentList.add(new Continent("Europa"));
        continentList.add(new Continent("Afrika"));
        continentList.add(new Continent("Autralija i Oceanija"));
        continentList.add(new Continent("Sjeverna Amerika"));
        continentList.add(new Continent("Južna Amerika"));
        continentList.add(new Continent("Antarktika"));

    }

    @Override
    public int getCount() {
        return this.continentList.size();
    }

    @Override
    public Object getItem(int position) {
        return continentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_continent_item, null);
        }

        TextView tvItemContinentName = (TextView) convertView.findViewById(R.id.tvItemContinentName);

        tvItemContinentName.setText(continentList.get(position).getName());

        return convertView;
    }
}
