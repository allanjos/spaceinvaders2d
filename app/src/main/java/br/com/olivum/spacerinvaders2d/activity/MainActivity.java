package br.com.olivum.spacerinvaders2d.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import br.com.olivum.spacerinvaders2d.game.GameEngine;
import br.com.olivum.spacerinvaders2d.game.GameEngineInterface;
import br.com.olivum.spacerinvaders2d.game.GameScreenSurfaceView;
import br.com.olivum.spacerinvaders2d.R;

public class MainActivity extends AppCompatActivity implements GameEngineInterface {
    private TextView textView = null;
    private GameEngine engine = null;
    private GameScreenSurfaceView surfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view_status);

        surfaceView = (GameScreenSurfaceView) findViewById(R.id.surface_view_game_screen);

        surfaceView.setActivity(this);

        surfaceView.registerListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setStatusMessage(String str) {
        textView.setText(str);
    }

    @Override
    public void onSurfaceCreated() {
        // Game screen surface

        engine = new GameEngine(surfaceView.getHolder(), surfaceView);

        engine.start();
    }

    @Override
    public void onSurfaceChanged() {

    }

    @Override
    public void onSurfaceDestroyed() {
        engine.stop();

        engine = null;
    }

    @Override
    public void onTouch(MotionEvent event) {
        engine.touch(event);
    }
}