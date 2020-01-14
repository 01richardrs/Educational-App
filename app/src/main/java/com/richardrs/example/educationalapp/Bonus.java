package com.richardrs.example.educationalapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.EventListener;

import static com.richardrs.example.educationalapp.GameView.screenratX;
import static com.richardrs.example.educationalapp.GameView.screenratY;


public class Bonus implements EventListener {

    public int speed = 20;

    public boolean gettap = true;
    int x=0,y , width,height,lucknum;
    Bitmap bonus1,bonus2,bonus3;


    Bonus(Resources res){

        bonus1 = BitmapFactory.decodeResource(res, R.drawable.bomb);
        bonus2 = BitmapFactory.decodeResource(res, R.drawable.mush1);
        bonus3 = BitmapFactory.decodeResource(res, R.drawable.mush2);

        width = bonus1.getWidth();
        height = bonus1.getHeight();

        width /= 4;
        height /= 4;

        width += (int) (width * screenratX);
        height += (int) (height* screenratY);

        bonus1 = Bitmap.createScaledBitmap(bonus1,width,height,false);
        bonus2 = Bitmap.createScaledBitmap(bonus2,width,height,false);
        bonus3 = Bitmap.createScaledBitmap(bonus3,width,height,false);

        y= -height;

    }

    public Bitmap getBonus1() {
        return bonus1;
    }

    public Bitmap getBonus2() {
        return bonus2;
    }

    public Bitmap getBonus3() {
        return bonus3;
    }

    Rect getcolshape(){
        return new Rect(x,y,x+width+50,y+height+70);
    }

    public int getLucknum() {
        return lucknum;
    }

    public void setLucknum(int lucknum) {
        this.lucknum = lucknum;
    }
}

