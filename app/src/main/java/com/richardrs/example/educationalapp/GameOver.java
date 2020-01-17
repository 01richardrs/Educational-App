package com.richardrs.example.educationalapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {
    private SimpleDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        ConstraintLayout maklo = (ConstraintLayout) findViewById(R.id.croslau);
        final Button back = (Button)findViewById(R.id.backo);
        final Button leaderboard = (Button)findViewById(R.id.Leaderboard);
        final Button playagain = (Button)findViewById(R.id.plei);

        TextView current_score = (TextView)findViewById(R.id.Current);
        TextView HIGHEST_score = (TextView)findViewById(R.id.HIGH);

        maklo.getBackground().setAlpha(150);

        back.startAnimation(bonce);
        leaderboard.startAnimation(bonce);
        playagain.startAnimation(bonce);

        SharedPreferences preferences = getSharedPreferences("Pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        int Score = preferences.getInt("SCORE",0);
        String PlayerName = preferences.getString("Player_name","Player 1");


        System.out.println(Score);

        HIGHEST_score.setText(""+0);
        current_score.setText(""+Score);

        db = new SimpleDatabase(this);
        db.addScore(PlayerName,Score);
        db.close();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                back.startAnimation(bonce);
                GameAct.fa.finish();
                MainActivity.fa.finish();
                finish();
                Intent maklo = new Intent(GameOver.this, MainActivity.class);
                startActivity(maklo);
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                leaderboard.startAnimation(bonce);
                GameAct.fa.finish();
                finish();
                Intent maklo = new Intent(GameOver.this, Leaderboard.class);
                startActivity(maklo);
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                playagain.startAnimation(bonce);
                GameAct.fa.finish();
                MainActivity.fa.finish();
                Intent Plays = new Intent(GameOver.this,GameAct.class);
                startActivity(Plays);
            }
        });
    }

    @Override
    public void onBackPressed() {
        MainActivity.fa.finish();
        finish();
        Intent maklo = new Intent(GameOver.this, MainActivity.class);
        startActivity(maklo);
    }

}
