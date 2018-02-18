package com.example.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {
    ArrayList<Score> scoreList = new ArrayList<Score>();
    ScoreAdapter scoreAdapter;
    ListView lvScore;
    DBHelper dbHelper;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        dbHelper = new DBHelper(this);
        String continent = getIntent().getStringExtra("CONTINENT");
        lvScore = (ListView) findViewById(R.id.lvScores);
        scoreList = dbHelper.getScoresByContinent(continent);
        scoreAdapter = new ScoreAdapter(this, scoreList);
        lvScore.setAdapter(scoreAdapter);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScoresActivity.this,MapsActivity.class));
            }
        });
    }
}
