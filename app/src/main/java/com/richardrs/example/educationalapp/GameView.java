package com.richardrs.example.educationalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isplay;
    private boolean gameover = false; // for the time
    private int ScreenX,ScreenY;
    public static float screenratX,screenratY;
    private Paint paint;
    private Background background1,background2;

    private Bubbl[] bubble;
    private Random random;

    public GameView(Context context, int ScreenX,int ScreenY) {
        super(context);

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

//        Bubbl bubbl = new Bubbl(getResources());
//        bubble[0]=bubbl;
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
            int bound = (int) (30 *screenratX);
            bubbl.speed = random.nextInt(bound);

            if(bubbl.speed < 10*screenratX){
                bubbl.speed = (int) (10*screenratX);
            }

            bubbl.x = ScreenX;
            bubbl.y = random.nextInt(ScreenY - bubbl.height);
        }

        //for check if it reach end
// need add some kind of obj if the bubble hit smth which the big red rect
//        if(Rect.intersects(bubbl.getcolshape()))
////condition check or minus etc
//
    }
    }
    private void draw() {

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paint);

            Bubbl bubbl2 = new Bubbl(getResources());
//            canvas.drawBitmap(bubbl.getBubbl1(),bubbl.x,bubbl.y,paint);

            for (Bubbl bubbl : bubble){
                System.out.println("x"+bubbl.x +" y "+bubbl.y);
                canvas.drawBitmap(bubbl.getBubbl1(), bubbl.x, bubbl.y, paint);
            }


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


}