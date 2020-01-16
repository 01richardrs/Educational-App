package com.richardrs.example.educationalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static MediaPlayer mediaPlayer;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa = this;
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Button Exit = (Button)findViewById(R.id.Exit_but);
        final Button Play = (Button)findViewById(R.id.Play);
        final Button LBoard = (Button)findViewById(R.id.Leaderboard);
        final ImageButton Share = (ImageButton)findViewById(R.id.share);
        final ImageButton Settg = (ImageButton)findViewById(R.id.setting);
        final ImageButton Voise = (ImageButton)findViewById(R.id.voise);
        final ImageButton Qmark = (ImageButton)findViewById(R.id.qmark);

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        Voise.startAnimation(bonce);
        Qmark.startAnimation(bonce);
        Exit.startAnimation(bonce);
        Play.startAnimation(bonce);
        LBoard.startAnimation(bonce);
        Share.startAnimation(bonce);
        Settg.startAnimation(bonce);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.whitelady);
        SharedPreferences preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

         final boolean BG_SOUND = preferences.getBoolean("BG_VOICE", true);
         final boolean[] current = {BG_SOUND};

        if (current[0]){
            Voise.setImageResource(R.drawable.voicew);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }else {
            Voise.setImageResource(R.drawable.voiceoffw);
            mediaPlayer.stop();
        }

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Play.startAnimation(bonce);
                Intent Plays = new Intent(MainActivity.this,GameAct.class);
                startActivity(Plays);
            }
        });

        LBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                LBoard.startAnimation(bonce);
                Intent Lboards = new Intent(MainActivity.this,Leaderboard.class);
                startActivity(Lboards);

            }
        });

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Share.startAnimation(bonce);
                Intent shares = new Intent(android.content.Intent.ACTION_SEND);
                shares.setType("text/plain");
                String shareBody = "Download GAME NAME for freee noww CLICK HERE : ";
                shares.putExtra(android.content.Intent.EXTRA_SUBJECT, "GAME NAME");
                shares.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shares, "Share via"));
            }
        });

        Settg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Settg.startAnimation(bonce);
                Intent settgs = new Intent(MainActivity.this,Settings.class);
                startActivity(settgs);
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Exit.startAnimation(bonce);
                onBackPressed();
            }
        });

        Voise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Voise.startAnimation(bonce);
                if(current[0]){
                    Voise.setImageResource(R.drawable.voiceoffw);
                    current[0] = false;
                    mediaPlayer.stop();
                }else{
                    Voise.setImageResource(R.drawable.voicew);
                    current[0] = true;
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.whitelady);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                editor.putBoolean("BG_VOICE",current[0]);
                editor.apply();
            }
        });

        Qmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Qmark.startAnimation(bonce);
                Intent howto = new Intent(MainActivity.this,HowToPlay.class);
                startActivity(howto);
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(true)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        System.exit(0);
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#326CFB"));
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#326CFB"));
            }
        });
        alert.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }


}

