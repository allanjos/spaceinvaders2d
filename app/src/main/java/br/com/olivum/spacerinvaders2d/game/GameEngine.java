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

    public GameEngine(SurfaceHolder holder, GameScreenSurfaceView surfaceView) {
        Log.d(TAG, "GameEngine()");

        this.holder = holder;
        this.surfaceView = surfaceView;

        starship = new Starship(holder, surfaceView);

        starshipEnemy = new StarshipEnemy(holder, surfaceView);

        thread = new GameEngineThread(this);
    }

    public void start() {
        Log.d(TAG, "GameEngine.start()");

        starship.start();

        starshipEnemy.start();

        thread.setRunnable(true);

        thread.start();
    }

    public void stop() {
        Log.d(TAG, "GameEngine.stop()");

        starship.stop();

        starshipEnemy.stop();

        if (thread != null) {
            thread.setRunnable(false);

            try {
                thread.join();
            }
            catch (InterruptedException e) {
                Log.d(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    public void process() {
        starship.update();

        starshipEnemy.update();

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

                starship.shoot();

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
}
