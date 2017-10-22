package br.com.olivum.spacerinvaders2d.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import br.com.olivum.spacerinvaders2d.R;

/**
 * Created by allsilva on 17/10/2017.
 */

public class StarshipEnemy {
    private static final String TAG = "StarshipEnemy";
    private SurfaceHolder surfaceHolder = null;
    private GameScreenSurfaceView surfaceView = null;
    private Bitmap bitmap = null;

    private final int DIRECTION_RIGHT = 1;
    private final int DIRECTION_LEFT = 2;
    private int direction = DIRECTION_RIGHT;

    private int margin = 50;
    private int x0 = margin;
    private int y0 = margin;

    private int x = x0;
    private int y = y0;
    private boolean crashed = false;

    public StarshipEnemy(SurfaceHolder holder, GameScreenSurfaceView surfaceView) {
        this.surfaceHolder = holder;
        this.surfaceView = surfaceView;

        bitmap = BitmapFactory.decodeResource(surfaceView.getResources(), R.mipmap.ic_launcher_round);
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public GameScreenSurfaceView getSurfaceView() {
        return surfaceView;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public void start() {
    }

    public void stop() {
    }

    public void clear(Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);

        canvas.drawRect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight(), paint);
    }

    public void update() {
        if (direction == DIRECTION_RIGHT) {
            if (x + GameEngine.MOTION_STEP < surfaceView.getWidth() - bitmap.getWidth() - margin) {
                x += GameEngine.MOTION_STEP;
            }
            else {
                direction = DIRECTION_LEFT;
            }
        }

        if (direction == DIRECTION_LEFT) {
            if (x - GameEngine.MOTION_STEP > x0) {
                x -= GameEngine.MOTION_STEP;
            }
            else {
                direction = DIRECTION_RIGHT;
            }
        }
    }

    public void draw(Canvas canvas) {
        //Log.d(TAG, "StarshipEnemy.draw()");

        Paint paint = new Paint();

        paint.setColor(Color.BLUE);

        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void crash() {
        Log.d(TAG, "Crashed");

        crashed = true;
    }
}