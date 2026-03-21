package com.example.flappythuy;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static SoundBank soundBank;
    
    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;

    public static void initialization(Context context) {
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        soundBank = new SoundBank(context);
        gameEngine = new GameEngine();
    }

    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }
    
    public static SoundBank getSoundBank() {
        return soundBank;
    }

    public static void setScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        AppConstants.SCREEN_WIDTH = metrics.widthPixels;
        AppConstants.SCREEN_HEIGHT = metrics.heightPixels;
    }
}
