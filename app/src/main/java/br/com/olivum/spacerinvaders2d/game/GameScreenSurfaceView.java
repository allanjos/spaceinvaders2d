package br.com.olivum.spacerinvaders2d.game;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import br.com.olivum.spacerinvaders2d.activity.MainActivity;

/**
 * Created by allann on 10/13/17.
 */

public class GameScreenSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "BasicGLSurfaceView";

    private MainActivity activity = null;

    SurfaceHolder surfaceHolder;

    private ArrayList<GameEngineInterface> listeners = new ArrayList<>();

    public GameScreenSurfaceView(Context context) {
        super(context);

        init();
    }

    public GameScreenSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public GameScreenSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        getHolder().addCallback(this);

        surfaceHolder = getHolder();

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public void registerListener(GameEngineInterface listener) {
        listeners.add(listener);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        for (GameEngineInterface listener: listeners) {
            listener.onSurfaceCreated();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        for (GameEngineInterface listener: listeners) {
            listener.onSurfaceChanged();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        for (GameEngineInterface listener: listeners) {
            listener.onSurfaceDestroyed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent()");

        float touchX = event.getX();
        float touchY = event.getY();

        //Log.d(TAG, "x=" + touchX + ", y=" + touchY);

        for (GameEngineInterface listener: listeners) {
            listener.onTouch(event);
        }

        return true;
    }
}