package br.com.olivum.spacerinvaders2d.game;

import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by allann on 10/17/17.
 */

public class GameEngineThread extends Thread {
    private static final String TAG = "StarshipEnemySurfThr";
    private SurfaceHolder holder = null;
    private GameEngine engine = null;

    private boolean enabled = false;

    public GameEngineThread(GameEngine engine) {
        this.engine = engine;
    }

    public void setRunnable(boolean enabled) {
        this.enabled = enabled;
    }

    public void run() {
        while (enabled) {
            // Log.d(TAG, "StarshipEnemySurfaceThread.run()");

            try {
                engine.process();

                sleep(GameEngine.REDRAW_TIME_MS);
            }
            catch (InterruptedException e) {
                Log.d(TAG, "Exception: " + e.getMessage());
            }
        }
    }
}