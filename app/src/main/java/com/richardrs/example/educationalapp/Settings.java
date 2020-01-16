package com.richardrs.example.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    boolean bgstatus,sfxstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ImageButton Back = (ImageButton) findViewById(R.id.backt);
        final ImageButton Save = (ImageButton) findViewById(R.id.sav);
        ImageButton Bg_voice = (ImageButton) findViewById(R.id.bg_voice);
        ImageButton Sfx_voice = (ImageButton) findViewById(R.id.sfx_voice);
        SeekBar Bg_sound = (SeekBar)findViewById(R.id.bg_sound);
        SeekBar Sfx_sound = (SeekBar)findViewById(R.id.sfx_sound);
        EditText Player_name = (EditText) findViewById(R.id.Pname);
        Button Pname_apply = (Button)findViewById(R.id.PnameApply);
        //radio button not yet

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Back.startAnimation(bonce);
                finish();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Save.startAnimation(bonce);
                Toast.makeText(Settings.this, "Changes are Applied.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Bg_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bgstatus){
                    bgstatus=false;
                    Toast.makeText(Settings.this, "Background voice is Muted.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    bgstatus=true;
                    Toast.makeText(Settings.this, "Background voice is On.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Sfx_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bgstatus){
                    sfxstatus=false;
                    Toast.makeText(Settings.this, "Background voice is Muted.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    sfxstatus=true;
                    Toast.makeText(Settings.this, "Background voice is On.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Bg_sound.setProgress(100);
        Bg_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Sfx_sound.setProgress(100);
        Sfx_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Pname_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Applied.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
