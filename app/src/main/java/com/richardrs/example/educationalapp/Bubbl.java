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


public class Bubbl implements EventListener {

    public int speed = 20;
    int bubblecount = 1;
    int x=0,y , width,height;
    Bitmap bubbl1;


    Bubbl(Resources res){

        bubbl1 = BitmapFactory.decodeResource(res, R.drawable.buburu);

        width = bubbl1.getWidth();
        height = bubbl1.getHeight();

        width /= 6;
        height /= 6;

        width += (int) (width * screenratX);
        height += (int) (height* screenratY);

        bubbl1 = Bitmap.createScaledBitmap(bubbl1,width,height,false);

        y= -height;

    }
    Bitmap getBubbl1(){
        if (bubblecount == 1) {
            bubblecount++;
            return bubbl1;
        }

        if (bubblecount == 2) {
            bubblecount++;
            return bubbl1;
        }

        if (bubblecount == 3) {
            bubblecount++;
            return bubbl1;
        }

        bubblecount = 1;

        return bubbl1;
    }


    Rect getcolshape(){
        return new Rect(x,y,x+width,y+height);
    }
//    Bitmap getBubbl(){
//// for animation
////        if(bubblecount == 1){
////            bubblecount++;
//        return bubbl1;
////        }

//    }

}

