package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ContinentsList extends AppCompatActivity {

    ContinentListAdapter continentsAdapter;
    ListView lvContinents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continents_list);

        lvContinents = (ListView) findViewById(R.id.lvContinents);
        continentsAdapter = new ContinentListAdapter(this);
        lvContinents.setAdapter(continentsAdapter);

        lvContinents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Continent selectedContinent = (Continent) continentsAdapter.getItem(i);
                String continentEnglish;
                switch (selectedContinent.getName()){
                    case "Azija":
                        continentEnglish = "Asia";
                        break;
                    case "Afrika":
                        continentEnglish = "Africa";
                        break;
                    case "Europa":
                        continentEnglish = "Europe";
                        break;
                    case "Autralija i Oceanija":
                        continentEnglish = "Oceania";
                        break;
                    case "Sjeverna Amerika":
                        continentEnglish = "North America";
                        break;
                    case "Južna Amerika":
                        continentEnglish = "South America";
                        break;
                    case "Antarktika":
                        continentEnglish = "Antarctica";
                        break;
                    default:
                        continentEnglish = "Unknown";
                        break;
                }
                Intent intent = new Intent(ContinentsList.this, ScoresActivity.class);
                intent.putExtra("CONTINENT", continentEnglish);
                startActivity(intent);
                return false;
            }
        });
    }

}
