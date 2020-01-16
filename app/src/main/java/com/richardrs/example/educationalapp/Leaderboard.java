package com.richardrs.example.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        ListView Score = (ListView)findViewById(R.id.scores);

        SimpleDatabase db;
        db = new SimpleDatabase(this);

        ArrayList<Integer> myArray;
        ArrayList<String> ranking = new ArrayList<>();
        myArray = db.getAllScores();

        //Sorting it
        Collections.sort(myArray, Collections.reverseOrder());

        //Putting up the score into the leaderboards with the maximum of 10 score
        int enumerator = 1;
        if(myArray.size()<10) {
            for (int points : myArray) {
                ranking.add(String.format(Locale.getDefault(), "%1$2s.  %2$3s ", Integer.toString(enumerator), Integer.toString(points)));
                enumerator++;
            }
        } else {
            for(int x = 0 ; x<10 ; x++){
                ranking.add(String.format(Locale.getDefault(), "%1$2s.  %2$3s ", Integer.toString(enumerator), Integer.toString(myArray.get(x))));
                enumerator++;
            }
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.leaderboard_view,ranking);
        Score.setAdapter(myAdapter);
        Score.setDivider(null);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Leaderboard.this,MainActivity.class));
            }
        });

    }
}
