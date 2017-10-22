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

public class Starship {
    private static final String TAG = "Starship";
    private SurfaceHolder surfaceHolder = null;
    private GameScreenSurfaceView surfaceView = null;
    private Bitmap bitmap = null;
    private StarshipShoot shoot = null;

    private final int DIRECTION_RIGHT = 1;
    private final int DIRECTION_LEFT = 2;
    private int direction = DIRECTION_LEFT;

    private int margin = 50;
    private int x0 = margin;
    private int y0 = margin;

    private int x = x0;
    private int y = y0;

    public Starship(SurfaceHolder holder, GameScreenSurfaceView surfaceView) {
        this.surfaceHolder = holder;
        this.surfaceView = surfaceView;

        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(surfaceView.getResources(), R.mipmap.ic_launcher);

        x = (surfaceView.getWidth() - bitmap.getWidth()) / 2;

        y = surfaceView.getHeight() - bitmap.getHeight() - margin;

        Log.d(TAG, "surfaceView.getHeight()=" + surfaceView.getHeight());
        Log.d(TAG, "bitmap.getHeight()=" + bitmap.getHeight());
        Log.d(TAG, "y=" + y);

        shoot = new StarshipShoot(this);

        shoot.init();
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
        init();
    }

    public void stop() {
        shoot.stop();
    }

    public void clear(Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);

        canvas.drawRect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight(), paint);
    }

    public void update() {
        shoot.update();

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
        shoot.draw(canvas);

        Paint paint = new Paint();

        paint.setColor(Color.GREEN);

        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void shoot() {
        Log.d(TAG, "shoot()");

        shoot.start();
    }

    public StarshipShoot getShoot() {
        return shoot;
    }
}