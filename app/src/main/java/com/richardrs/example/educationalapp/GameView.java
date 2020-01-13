package com.richardrs.example.educationalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
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
    private SoundPool soundPool;
    private int sound,sound1,sound2;
    private Background background1,background2;
    public int SCORE = 0;

    private String [] TrueQuestPlus = {
            "5+2 = 7",
            "9+4 = 13",
            "7+2 = 9",
            "5+7 = 12",
            "2+5 = 7",
    };
    private String [] TrueQuestMin = {
            "3-2 = 1",
            "6-4 = 2",
            "8-2 = 6",
            "7-5 = 2",
            "2-5 = -3",
    };
    private String [] TrueQuestTimes = {
            "6*6 = 36",
            "7*4 = 28",
            "7*0 = 0",
            "5*7 = 35",
            "2*5 = 10",
    };
    private String [] TrueQuestDiv = {
            "9/3 = 2",
            "28/4 = 7",
            "8/2 = 4",
            "64/8 = 8",
            "10/5 = 2",
    };

    private String [] FalseQuestPlus = {
            "4+2 = 7",
            "11+4 = 14",
            "6+2 = 9",
            "15+7 = 25",
            "1+5 = 9",
    };

    private String [] FalseQuestMin = {
            "2-2 = 1",
            "4-4 = -1",
            "8-2 = -6",
            "7-5 = -2",
            "12-5 = 6",
    };

    private String [] FalseQuestTimes = {
            "6*6 = 26",
            "5*4 = 24",
            "7*0 = 7",
            "6*7 = 45",
            "3*5 = 13",
    };

    private String [] FalseQuestDiv = {
            "6/3 = 3",
            "4/4 = 0",
            "18/2 = 8",
            "32/4 = 9",
            "25/5 = 6",
    };

    Rect maklo;
    private Bubbl[] bubble;
    private Random random;

    //Games Rules
    // LET THE FALSE BUB PASS GET SCORE IF HIT FALSE BUB - SCORE
    // HIT EVERY RIGHT BUB IF RIGHT ONE PASS U DED



    public GameView(Context context, int ScreenX,int ScreenY) {
        super(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(context, R.raw.shooty, 1);
        sound1 = soundPool.load(context, R.raw.gud, 2);
        sound2 = soundPool.load(context, R.raw.wrong, 2);

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

//        background1.x -= 10*screenratX;
//        background2.x -= 10*screenratX;
//
//        if(background1.x + background1.background.getWidth() < 0){
//            background1.x =ScreenX;
//        }
//        if(background2.x + background2.background.getWidth() < 0){
//            background2.x =ScreenX;
//        }

    for (Bubbl bubbl : bubble){
        bubbl.x -= bubbl.speed;

        if(bubbl.x + bubbl.width <0 ){

            if(!bubbl.gettap){
                if(bubbl.isBubstat() == false){
                    SCORE++;
                }else{
                    gameover = true;
                    return;
                }
            }

            int truenot = random.nextInt((1-0)+1)+0;

            if(truenot == 0){
                bubbl.setBubstat(true);
            }else{
                bubbl.setBubstat(false);
            }

            bubbl.setText(QuestionRandomizer(bubbl.isBubstat()));

            int bound = (int) (13 *screenratX);
            bubbl.speed = random.nextInt(bound);

            if(bubbl.speed < 5*screenratX){
                bubbl.speed = (int) (5*screenratX);
            }

            bubbl.x = ScreenX;
            bubbl.y = random.nextInt(ScreenY - bubbl.height);

            bubbl.gettap = false;        }
    }
    }
    private void draw() {

        if(getHolder().getSurface().isValid()){

            final Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.background,background2.x,background2.y,paint);

            canvas.drawText("SCORE : "+SCORE,ScreenX/2,100,paint);


            Rect dedzone = new Rect(0,0,100,getHeight());
            paint.setColor(Color.RED);
            canvas.drawRect(dedzone,paint);

            for (Bubbl bubbl : bubble){

//                System.out.println("x"+bubbl.x +" y "+bubbl.y); //for check coordinate
                canvas.drawBitmap(bubbl.getBubbl1(), bubbl.x, bubbl.y, paint);
                paint.setColor(Color.BLACK); // for check buble hit range
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(50);
                canvas.drawText(""+bubbl.text,bubbl.x+100,bubbl.y+150,paint);
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
                soundPool.play(sound,1,1,0,0,1);

                for (Bubbl bubbl : bubble){
                    System.out.println("DOWN HEREE"+bubbl.isBubstat());
                    if(Rect.intersects(maklo,bubbl.getcolshape())){
                        bubbl.x = -500;
                        bubbl.gettap = true;
                        if(bubbl.isBubstat()){
                            SCORE++;
                            soundPool.play(sound1,1,1,0,0,1);
                        }else{
                            SCORE--;
                            soundPool.play(sound2,1,1,0,0,1);
                        }
                        // add command if its get hit
                    }
                }
                break;
        }
        return true;
    }

    public String QuestionRandomizer (Boolean cond){
        String mama = "";
        int morerand = random.nextInt((4-1)+1)+1;
        int morerand2 = random.nextInt((5-1)+1)+1;
        morerand2--;

        if(cond){
            if(morerand == 1){
                mama = TrueQuestPlus[morerand2];
            }else if(morerand == 2){
                mama = TrueQuestMin[morerand2];
            }else if(morerand == 3){
                mama = TrueQuestTimes[morerand2];
            }else if(morerand == 4){
                mama = TrueQuestDiv[morerand2];
            }else{
                mama = "BONUS CORRECT";
            }
        }else{
            if(morerand == 1){
                mama = FalseQuestPlus[morerand2];
            }else if(morerand == 2){
                mama = FalseQuestMin[morerand2];
            }else if(morerand == 3){
                mama = FalseQuestTimes[morerand2];
            }else if(morerand == 4){
                mama = FalseQuestDiv[morerand2];
            }else{
                mama = "BONUS FALSE";
            }
        }
        return mama;
    }

}
