package com.example.flappythuy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    public GameView(Context context) {
        super(context);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (GameEngine.gameState == 0) {
                GameEngine.gameState = 1;
                // Bắt đầu phát nhạc nền khi người chơi nhấn bắt đầu
                AppConstants.getSoundBank().playMusic();
            }
            
            if (GameEngine.gameState == 1) {
                AppConstants.getGameEngine().bird.setVelocity(Bird.jumpVelocity);
                AppConstants.getSoundBank().playWing(); // Âm thanh vỗ cánh
            }

            if (GameEngine.gameState == 2) {
                Context context = getContext();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (gameThread.getState() == Thread.State.TERMINATED) {
            gameThread = new GameThread(holder);
        }
        gameThread.setIsRunning(true);
        if (gameThread.getState() == Thread.State.NEW) {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        gameThread.setIsRunning(false);
        // Dừng nhạc khi thoát view
        AppConstants.getSoundBank().stopMusic();
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    private void initView() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }
}
