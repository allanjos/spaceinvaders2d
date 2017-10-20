package br.com.olivum.spacerinvaders2d.game;

import android.view.SurfaceHolder;

/**
 * Created by allsilva on 16/10/2017.
 */

public class StarshipEnemySurfaceThread extends Thread {
    private static final String TAG = "StarshipEnemySurfThr";
    private SurfaceHolder holder = null;
    private GameScreenSurfaceView surfaceView = null;
    private StarshipEnemy starship = null;

    private boolean enabled = false;

    public StarshipEnemySurfaceThread(StarshipEnemy starship, SurfaceHolder holder, GameScreenSurfaceView surfaceView) {
        this.holder = holder;
        this.surfaceView = surfaceView;
        this.starship = starship;
    }

    public void setRunnable(boolean enabled) {
        this.enabled = enabled;
    }

    public void run() {
        /*
        while (enabled) {
            // Log.d(TAG, "StarshipEnemySurfaceThread.run()");

            try {
                //starship.draw();

                sleep(GameEngine.REDRAW_TIME_MS);
            }
            catch (InterruptedException e) {
                Log.d(TAG, "Exception: " + e.getMessage());
            }
        }
        */
    }
}