package com.richardrs.example.educationalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isplay;
    private int ScreenX,ScreenY;
    private float screenratX,screenratY;
    private Paint paint;
    private Background background1,background2;

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

    }public void draw() {

        if(getHolder().getSurface().isValid()){

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paint);

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
