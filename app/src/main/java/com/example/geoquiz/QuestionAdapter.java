package com.example.geoquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class QuestionAdapter extends BaseAdapter {
    Context context;
    ArrayList<Question> questionList;

    public QuestionAdapter(Context context, ArrayList<Question> questionList) {
        super();
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return this.questionList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_question_item, null);
        }

        Question current = questionList.get(position);

        TextView tvItemQuestionPlace = (TextView) convertView.findViewById(R.id.tvItemQuestionPlace);
        TextView tvItemQuestionQuestion = (TextView) convertView.findViewById(R.id.tvItemQuestionQuestion);
        View vRectangle = (View) convertView.findViewById(R.id.vRectangle);

        tvItemQuestionPlace.setText(current.getPlace());
        tvItemQuestionQuestion.setText(current.getQuestion());

        return convertView;
    }
}
