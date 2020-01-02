package com.richardrs.example.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button Exit = (Button)findViewById(R.id.Exit_but);
        final Button Play = (Button)findViewById(R.id.Play);
        final Button LBoard = (Button)findViewById(R.id.Leaderboard);
        final ImageButton Share = (ImageButton)findViewById(R.id.share);
        final ImageButton Settg = (ImageButton)findViewById(R.id.setting);

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        Exit.startAnimation(bonce);
        Play.startAnimation(bonce);
        LBoard.startAnimation(bonce);
        Share.startAnimation(bonce);
        Settg.startAnimation(bonce);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Exit.startAnimation(bonce);
                System.out.println("LOL");
            }
        });
    }
}
