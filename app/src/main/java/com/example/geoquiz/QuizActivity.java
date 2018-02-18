package com.example.geoquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {
    ArrayList<Question> questionsList = new ArrayList<Question>();
    QuestionAdapter questionAdapter;
    ListView lvQuestion;
    Button btnConfirmQuiz;
    String[] answers = new String[5];
    String place;
    DBHelper dbHelper;
    String continent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        dbHelper = new DBHelper(this);

        place = getIntent().getStringExtra("PLACE");
        continent = getIntent().getStringExtra("CONTINENT");
        questionsList = (ArrayList<Question>)getIntent().getSerializableExtra("QUESTIONS");
        for (int i = 0; i < 5; i++){
            answers[i] = "";
        }
        btnConfirmQuiz = (Button) findViewById(R.id.btnConfirmQuiz);
        lvQuestion = (ListView) findViewById(R.id.lvQuestions);

        questionAdapter = new QuestionAdapter(this, questionsList);
        lvQuestion.setAdapter(questionAdapter);
        btnConfirmQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correctAnswers = 0;
                for (int i = 0; i < 5; i++){
                    String s = questionsList.get(i).getAnswer();

                    if(answers[i].toLowerCase().equals(s)){
                        correctAnswers++;
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                builder.setTitle("Vaš rezultat.");
                dbHelper.insertScore(new Score(correctAnswers,place, continent));
                builder.setMessage(Integer.toString(correctAnswers) + "/5")
                        .setCancelable(false)
                        .setPositiveButton("Dalje", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(QuizActivity.this,ContinentsList.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                builder.setCancelable(false);
            }
        });


        lvQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(QuizActivity.this);
                alertDialog.setTitle("ODGOVOR");
                final int alertPosition = position;
                final EditText input = new EditText(QuizActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("ODGOVORI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String answer = input.getText().toString();
                                answers[alertPosition] = answer;
                            }
                        });

                View infoRectangle = (View)parent.getAdapter().getView(position, view, parent).findViewById(R.id.vRectangle);
                if (infoRectangle.getVisibility() == View.GONE)
                    infoRectangle.setVisibility(View.VISIBLE);

                alertDialog.show();
                alertDialog.setCancelable(true);
            }
        });
    }

}
