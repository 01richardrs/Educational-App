package com.richardrs.example.educationalapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        ConstraintLayout maklo = (ConstraintLayout) findViewById(R.id.croslau);
        Button back = (Button)findViewById(R.id.backo);
        Button leaderboard = (Button)findViewById(R.id.Leaderboard);
        Button playagain = (Button)findViewById(R.id.plei);

        maklo.getBackground().setAlpha(150);

        back.startAnimation(bonce);
        leaderboard.startAnimation(bonce);
        playagain.startAnimation(bonce);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                GameAct.fa.finish();
                finish();
                Intent maklo = new Intent(GameOver.this, Leaderboard.class);
                startActivity(maklo);
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
