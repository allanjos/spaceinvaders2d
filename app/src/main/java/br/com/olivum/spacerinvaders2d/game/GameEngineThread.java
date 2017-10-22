package br.com.olivum.spacerinvaders2d.game;

import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by allann on 10/17/17.
 */

public class GameEngineThread extends Thread {
    private static final String TAG = "GameEngineThread";
    private SurfaceHolder holder = null;
    private GameEngine engine = null;

    private boolean enabled = true;

    public GameEngineThread(GameEngine engine) {
        this.engine = engine;
    }

    public void setRunnable(boolean enabled) {
        Log.d(TAG, "setRunnable(" + enabled + ")");

        this.enabled = enabled;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //Log.d(TAG, "run()");

            try {
                engine.process();

                sleep(GameEngine.REDRAW_TIME_MS);
            }
            catch (InterruptedException e) {
                Log.d(TAG, "Exception: " + e.getMessage());

                break;
            }
        }

        Log.d(TAG, "Exiting");
    }
}