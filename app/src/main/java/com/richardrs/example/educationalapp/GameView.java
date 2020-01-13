package com.richardrs.example.educationalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private static MediaPlayer mediaPlayer;
    private Thread thread;
    private boolean isplay;
    private boolean gameover = false; // for the time
    private int ScreenX,ScreenY;
    public static float screenratX,screenratY;
    private Paint paint;
    private Background background1,background2;

    Rect maklo;
    private Bubbl[] bubble;
    private Random random;

    public GameView(Context context, int ScreenX,int ScreenY) {
        super(context);

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.whitelady);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        this.ScreenX = ScreenX;
        this.ScreenY = ScreenY;
        screenratX = 1920f/ScreenX;
        screenratY = 1080f/ScreenY;

        background1 = new Background(ScreenX,ScreenY,getResources());
        background2 = new Background(ScreenX,ScreenY,getResources());

        background2.x =ScreenX;

        paint = new Paint();

        bubble = new Bubbl[5];//array depend on how much pic need for animation

        for (int i = 0;i < 5;i++) {

            Bubbl bubbl = new Bubbl(getResources());
            bubble[i] = bubbl;

        }
        random = new Random();
    }

    @Override
    public void run() {

        while(isplay){
            update();
            draw();
            sleep();
        }

    }

    public void update() {

        background1.x -= 10*screenratX;
        background2.x -= 10*screenratX;

        if(background1.x + background1.background.getWidth() < 0){
            background1.x =ScreenX;
        }
        if(background2.x + background2.background.getWidth() < 0){
            background2.x =ScreenX;
        }

    for (Bubbl bubbl : bubble){
        bubbl.x -= bubbl.speed;

        if(bubbl.x + bubbl.width <0 ){
            int bound = (int) (13 *screenratX);
            bubbl.speed = random.nextInt(bound);

            if(bubbl.speed < 5*screenratX){
                bubbl.speed = (int) (5*screenratX);
            }

            bubbl.x = ScreenX;
            bubbl.y = random.nextInt(ScreenY - bubbl.height);
        }
    }
    }
    private void draw() {

        if(getHolder().getSurface().isValid()){

            final Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paint);

            Rect fku = new Rect(0,0,100,getHeight());
            paint.setColor(Color.RED);
            canvas.drawRect(fku,paint);

            for (Bubbl bubbl : bubble){
//                System.out.println("x"+bubbl.x +" y "+bubbl.y); //for check coordinate
                canvas.drawBitmap(bubbl.getBubbl1(), bubbl.x, bubbl.y, paint);
//                paint.setColor(Color.GREEN); // for check buble hit range
//                canvas.drawRect(bubbl.getcolshape(), paint);
            }
//            if(maklo != null){ //debugmode to see user hit
//              paint.setColor(Color.BLUE);
//                canvas.drawRect(maklo,paint);
//            }

            if(gameover){
                isplay = false;
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }



            getHolder().unlockCanvasAndPost(canvas);
        }

    }
    public void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void resume() {

        isplay = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause() {

        try {
            isplay = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:


                maklo = new Rect((int)x+100,(int)y+100,(int)x,(int)y);

                for (Bubbl bubbl : bubble){
                    if(Rect.intersects(maklo,bubbl.getcolshape())){
                        System.out.println("BUB GET HIT +"+bubbl);
                        // add command if its get hit
                    }else{
                        System.out.println("NOT HIT :"+bubbl);
                    }
                }
                break;
        }
        return true;
    }

}
