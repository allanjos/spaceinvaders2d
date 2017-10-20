package br.com.olivum.spacerinvaders2d.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by allann on 10/20/17.
 */

public class StarshipShoot {
    private static final String TAG = "StarshipShoot";
    //private StarshipSurfaceThread surfaceThread = null;
    private SurfaceHolder surfaceHolder = null;
    private GameScreenSurfaceView surfaceView = null;
    private Bitmap bitmap = null;
    private Starship starship = null;

    private final int DIRECTION_BOTTOM = 1;
    private final int DIRECTION_TOP = 2;
    private int direction = DIRECTION_TOP;

    public static final int MOTION_STEP = 50;

    private int x = 0;
    private int y = 0;

    private int width = 10;
    private int height = 50;

    private int color = Color.RED;

    private boolean started = false;

    public StarshipShoot(Starship starship) {
        this.starship = starship;
    }

    public void init() {
        x = starship.getX() + (starship.getWidth() - width) / 2;

        y = starship.getY() - height;

        Log.d(TAG, "x=" + x + ", y=" + y);
    }

    public void start() {
        if (started) {
            return;
        }

        init();

        started = true;
    }

    public void stop() {
        started = false;
    }

    public void term() {

    }

    public void update() {
        if (!started) {
            return;
        }

        if (direction == DIRECTION_BOTTOM) {
            if (y + MOTION_STEP < surfaceView.getHeight()) {
                y += MOTION_STEP;
            }
            else {
                stop();
            }
        }
        else {
            if (y - MOTION_STEP >= 0) {
                y -= MOTION_STEP;
            }
            else {
                stop();
            }
        }
    }

    public void draw(Canvas canvas) {
        if (!started) {
            return;
        }

        Paint paint = new Paint();

        paint.setColor(color);

        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
