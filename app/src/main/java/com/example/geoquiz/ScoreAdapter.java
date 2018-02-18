package com.example.geoquiz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class ScoreAdapter extends BaseAdapter {

    Context context;
    ArrayList<Score> scoreList;

    public ScoreAdapter(Context context, ArrayList<Score> scoreList) {
        super();
        this.context = context;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_score_item, null);
        }



        TextView tvItemScorePlace = (TextView) convertView.findViewById(R.id.tvItemScorePlace);
        TextView tvItemScoreResult = (TextView) convertView.findViewById(R.id.tvItemScoreResult);

        tvItemScorePlace.setText(scoreList.get(position).getPlace());
        tvItemScoreResult.setText(Integer.toString(scoreList.get(position).getNumberOfCorrect()) + "/5");

        return convertView;
    }
}
