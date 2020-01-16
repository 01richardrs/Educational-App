package com.richardrs.example.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class HowToPlay extends AppCompatActivity {
    private int pages = 0;
    private int Scontainer;
    private SoundPool soundPool;
    private int sound,sound1,sound2,soundbon1,soundbon2,soundbon3,soundblockarea,soundblockmove,soundend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        GifImageView animations = (GifImageView)findViewById(R.id.gifto);
        ImageButton SFx = (ImageButton)findViewById(R.id.sfx);
        final TextView Text = (TextView)findViewById(R.id.texto);
        ImageButton Next = (ImageButton)findViewById(R.id.nexto);

        final String[] Guides = {
                "Tap On The Right Question inside the Bubble and you get 1 Point",
                "Dont Let The Right Answer Bubble Go To Red Zone or you will Lose",
                "Dont Tap on Wrong Question inside the Bubble Or Minus 2 Point",
                "Let The Wrong Question Pass Red Zone And you get 1 Point",
                "There is 3 Item In This Game : Mushroom + 5 Score",
                "There is 3 Item In This Game : Devil Mushroom - 5 Score",
                "There is 3 Item In This Game : Bomb That will End The game",
                "Poison Zone Will Appear at Start of a game and changed position every 60 Second",
                "Avoid Tap Bubble inside Poison Zone It Will -3 For false and -1 For True",
                "All items cannot be pressed inside The Poison Zone, it will count as miss"
        };

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(this, R.raw.shooty, 1);
        sound1 = soundPool.load(this, R.raw.gud, 2);
        soundend = soundPool.load(this, R.raw.over, 2);
        sound2 = soundPool.load(this, R.raw.wrong, 2);
        soundbon1 = soundPool.load(this,R.raw.bum,2);
        soundbon2 = soundPool.load(this,R.raw.up,2);
        soundbon3 = soundPool.load(this,R.raw.down,2);
        soundblockarea = soundPool.load(this,R.raw.puff,3);
        soundblockmove = soundPool.load(this,R.raw.poison,3);

        Text.setText(Guides[0]);
        Scontainer = sound1;

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pages++;
                if(pages == 1){
                    Scontainer = soundend;
                }else if(pages == 2){
                    Scontainer = sound2;
                }else if(pages == 3){
                    Scontainer = sound1;
                }else if(pages == 4){
                    Scontainer = soundbon2;
                }else if(pages == 5){
                    Scontainer = soundbon3;
                }else if(pages == 6){
                    Scontainer = soundbon1;
                }else if(pages == 7){
                    Scontainer = soundblockmove;
                }else if(pages == 8){
                    Scontainer = soundblockarea;
                }else if(pages == 9){
                    Scontainer= sound;
                }else{
                    finish();
                }
                if(pages < 10){
                    Text.setText(Guides[pages]);
                }
            }
        });

        SFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(Scontainer,100,100,0,0,1);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pages = 0;
    }
}
