package br.com.olivum.spacerinvaders2d.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by allann on 10/17/17.
 */

public class GameEngine {
    private static final String TAG = "GameEngine";
    private GameEngineThread thread = null;
    private Starship starship = null;
    private StarshipEnemy starshipEnemy = null;

    private SurfaceHolder holder = null;
    private GameScreenSurfaceView surfaceView = null;

    public static final int REDRAW_TIME_MS = 10;
    public static final int MOTION_STEP = 10;
    private boolean started = false;

    public GameEngine(SurfaceHolder holder, GameScreenSurfaceView surfaceView) {
        Log.d(TAG, "GameEngine()");

        this.holder = holder;
        this.surfaceView = surfaceView;

        starship = new Starship(holder, surfaceView);

        starshipEnemy = new StarshipEnemy(holder, surfaceView);
    }

    public void start() {
        Log.d(TAG, "GameEngine.start()");

        if (started) {
            Log.d(TAG, "Already started");

            return;
        }

        started = true;

        starship.start();

        starshipEnemy.start();

        thread = new GameEngineThread(this);

        thread.start();
    }

    public void stop() {
        Log.d(TAG, "GameEngine.stop()");

        starship.stop();

        starshipEnemy.stop();

        if (thread != null) {
            thread.setRunnable(false);

            //try {
            //    Log.d(TAG, "Interrupting thread");

                thread.interrupt();

                //Log.d(TAG, "Trying to join");

                //thread.join();

                Log.d(TAG, "Interrupted");
            //}
            //catch (InterruptedException e) {
            //    Log.d(TAG, "Exception: " + e.getMessage());
            //}
        }

        //thread = null;

        started = false;

        Log.d(TAG, "Stopped");
    }

    public boolean isStarted() {
        return started;
    }

    public void process() {
        starship.update();

        starshipEnemy.update();

        detectCollision();

        draw();
    }

    public void clearScreen(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    public void draw() {
        //Log.d(TAG, "GameEngine.draw()");

        Canvas canvas = holder.lockCanvas();

        clearScreen(canvas);

        starship.draw(canvas);

        starshipEnemy.draw(canvas);

        holder.unlockCanvasAndPost(canvas);
    }

    public void touch(MotionEvent event) {
        Log.d(TAG, "onTouchEvent()");

        float touchX = event.getX();
        float touchY = event.getY();

        Log.d(TAG, "x=" + touchX + ", y=" + touchY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP");

                if (!started) {
                    start();
                }
                else {
                    starship.shoot();
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "ACTION_OUTSIDE");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE");
                break;
            default:
                Log.d(TAG, "UNHANDLED TOUCH ACTION");
                break;
        }
    }

    public boolean detectCollision() {
        if (!started) {
            return false;
        }

        if (starship.getShoot().getX() >= starshipEnemy.getX() && starship.getShoot().getY() >= starshipEnemy.getY() &&
            starship.getShoot().getX() <= (starshipEnemy.getX() + starshipEnemy.getWidth())
                && starship.getShoot().getY() <= (starshipEnemy.getY() + starshipEnemy.getHeight())) {
            Log.d(TAG, "Collision detection between starship shoot and starship enemy");

            stop();

            starshipEnemy.crash();

            return true;
        }

        return false;
    }
}
