package br.com.olivum.spacerinvaders2d.ui;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by allsilva on 17/10/2017.
 */

public class UI {
    public static void runOnUiThread(Runnable runnable){
        final Handler UIHandler = new Handler(Looper.getMainLooper());

        UIHandler.post(runnable);
    }
}