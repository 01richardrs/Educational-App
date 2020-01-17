package com.richardrs.example.educationalapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.EventListener;

import static com.richardrs.example.educationalapp.GameView.screenratX;
import static com.richardrs.example.educationalapp.GameView.screenratY;


public class Bubbl implements EventListener {

    public int speed = 20;
    public boolean gettap = true;
    int x=0,y , width,height;
    Bitmap bubbl1,bubbl2,bubbl3,bubbl4;
    String text;
    boolean bubstat = true;

//    int bubblecount = 1;

    Bubbl(Resources res){

        bubbl1 = BitmapFactory.decodeResource(res, R.drawable.buburu);
        bubbl2 = BitmapFactory.decodeResource(res, R.drawable.buburu2);
        bubbl3 = BitmapFactory.decodeResource(res, R.drawable.buburu3);
        bubbl4 = BitmapFactory.decodeResource(res, R.drawable.buburu4);

        width = bubbl1.getWidth();
        height = bubbl1.getHeight();

        width /= 6;
        height /= 6;

        width += (int) (width * screenratX);
        height += (int) (height* screenratY);

        bubbl1 = Bitmap.createScaledBitmap(bubbl1,width,height,false);
        bubbl2 = Bitmap.createScaledBitmap(bubbl2,width,height,false);
        bubbl3 = Bitmap.createScaledBitmap(bubbl3,width,height,false);
        bubbl4 = Bitmap.createScaledBitmap(bubbl4,width,height,false);

        y= -height;

    }
    Bitmap getBubbl1(){
//        if (bubblecount == 1) {
//            bubblecount++;
//            return bubbl1;
//        }
//
//        if (bubblecount == 2) {
//            bubblecount++;
//            return bubbl1;
//        }
//
//        if (bubblecount == 3) {
//            bubblecount++;
//            return bubbl1;
//        }
//
//        bubblecount = 1;
        //animation purpose

        return bubbl1;
    }

    public Bitmap getBubbl2() {
        return bubbl2;
    }

    public Bitmap getBubbl3() {
        return bubbl3;
    }

    public Bitmap getBubbl4() {
        return bubbl4;
    }

    Rect getcolshape(){
        return new Rect(x,y,x+width+50,y+height+70);
    }

    public boolean isBubstat() {
        return bubstat;
    }

    public void setBubstat(boolean bubstat) {
        this.bubstat = bubstat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

