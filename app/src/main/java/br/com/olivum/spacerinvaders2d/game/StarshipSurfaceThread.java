package br.com.olivum.spacerinvaders2d.game;

import android.view.SurfaceHolder;

/**
 * Created by allsilva on 17/10/2017.
 */

public class StarshipSurfaceThread extends Thread {
    private static final String TAG = "StarshipSurfaceThread";
    private SurfaceHolder holder = null;
    private GameScreenSurfaceView surfaceView = null;
    private Starship starship = null;

    private boolean enabled = false;

    public StarshipSurfaceThread(Starship starship, SurfaceHolder holder, GameScreenSurfaceView surfaceView) {
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
            // Log.d(TAG, "StarshipSurfaceThread.run()");

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