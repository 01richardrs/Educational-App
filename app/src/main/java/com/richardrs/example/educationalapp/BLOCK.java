package com.richardrs.example.educationalapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.EventListener;

import static com.richardrs.example.educationalapp.GameView.screenratX;
import static com.richardrs.example.educationalapp.GameView.screenratY;


public class BLOCK implements EventListener {

    public boolean gettap = true;
    int x=0,y;
    Bitmap blockarea;

//    int bubblecount = 1;

    BLOCK(Resources res){
        blockarea = BitmapFactory.decodeResource(res,R.drawable.bublee);
        blockarea = Bitmap.createScaledBitmap(blockarea,350,1080,false);


    }
    Bitmap getBlockarea(){
        return blockarea;
    }

    Rect getcolshape(){
        return new Rect(x,y,x+450,2000);
    }

    public void setX(int x) {
        this.x = x;
    }
}

