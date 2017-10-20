package br.com.olivum.spacerinvaders2d.game;

import android.view.MotionEvent;

/**
 * Created by allann on 10/17/17.
 */

public interface GameEngineInterface {
    void onSurfaceCreated();

    void onSurfaceChanged();

    void onSurfaceDestroyed();

    void onTouch(MotionEvent event);
}
