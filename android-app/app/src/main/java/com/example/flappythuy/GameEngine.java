package com.example.flappythuy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import java.util.ArrayList;

public class GameEngine {
    BackgroundImage backgroundImage;
    Bird bird;
    ArrayList<Pipe> pipes;
    Paint pipePaint, scorePaint, gameOverPaint;
    int currentFrame = 0;
    static int gameState = 0; // 0: Chờ, 1: Đang chơi, 2: Game Over
    int score = 0;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        pipes = new ArrayList<>();
        
        pipes.add(new Pipe(AppConstants.SCREEN_WIDTH + 500));
        pipes.add(new Pipe(AppConstants.SCREEN_WIDTH + 1500));

        pipePaint = new Paint();
        pipePaint.setColor(Color.GREEN);
        pipePaint.setStyle(Paint.Style.FILL);

        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(100);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        gameOverPaint = new Paint();
        gameOverPaint.setColor(Color.RED);
        gameOverPaint.setTextSize(150);
        gameOverPaint.setTypeface(Typeface.DEFAULT_BOLD);
        gameOverPaint.setTextAlign(Paint.Align.CENTER);
        gameOverPaint.setAntiAlias(true);
        
        gameState = 0;
        score = 0;
    }

    public void updateAndDrawBackgroundImage(Canvas canvas) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);

        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    public void updateAndDrawPipes(Canvas canvas) {
        if (gameState == 1) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe p = pipes.get(i);
                p.setPipeX(p.getPipeX() - Pipe.pipeVelocity);

                canvas.drawRect(p.getPipeX(), 0, p.getPipeX() + Pipe.pipeWidth, p.getTopPipeY(), pipePaint);
                canvas.drawRect(p.getPipeX(), p.getBottomPipeY(), p.getPipeX() + Pipe.pipeWidth, AppConstants.SCREEN_HEIGHT, pipePaint);

                if (!p.isScored() && bird.getX() > (p.getPipeX() + Pipe.pipeWidth)) {
                    score++;
                    p.setScored(true);
                    AppConstants.getSoundBank().playPoint(); // Chơi âm thanh ghi điểm
                }

                if (p.getPipeX() < -Pipe.pipeWidth) {
                    p.setPipeX(AppConstants.SCREEN_WIDTH + 500);
                    p.resetPipe();
                }

                if (checkCollision(p)) {
                    gameState = 2;
                    AppConstants.getSoundBank().stopMusic(); // Dừng nhạc nền khi thua
                    AppConstants.getSoundBank().playDie(); // Chơi âm thanh va chạm
                }
            }
        }
    }

    private boolean checkCollision(Pipe p) {
        Rect birdRect = new Rect(
            bird.getX() + 20, bird.getY() + 20, 
            bird.getX() + AppConstants.getBitmapBank().getBirdWidth() - 20, 
            bird.getY() + AppConstants.getBitmapBank().getBirdHeight() - 20
        );
        Rect topPipeRect = new Rect(p.getPipeX(), 0, p.getPipeX() + Pipe.pipeWidth, p.getTopPipeY());
        Rect bottomPipeRect = new Rect(p.getPipeX(), p.getBottomPipeY(), p.getPipeX() + Pipe.pipeWidth, AppConstants.SCREEN_HEIGHT);

        return Rect.intersects(birdRect, topPipeRect) || Rect.intersects(birdRect, bottomPipeRect);
    }

    public void updateAndDrawBird(Canvas canvas) {
        if (gameState == 0) {
            double period = 500;
            double amplitude = 30;
            int hoverY = (int) (AppConstants.SCREEN_HEIGHT / 2 - AppConstants.getBitmapBank().getBirdHeight() / 2 
                         + Math.sin(System.currentTimeMillis() / period) * amplitude);
            bird.setY(hoverY);
            bird.setVelocity(0);
        } else if (gameState == 1) {
            bird.setVelocity(bird.getVelocity() + Bird.gravity);
            bird.setY(bird.getY() + bird.getVelocity());

            if (bird.getY() < 0) bird.setY(0);
            int groundLevel = AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight();
            if (bird.getY() > groundLevel) {
                bird.setY(groundLevel);
                bird.setVelocity(0);
                gameState = 2;
                AppConstants.getSoundBank().stopMusic(); // Dừng nhạc nền
                AppConstants.getSoundBank().playDie();
            }
        }

        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(bird.getCurrentFrame()), bird.getX(), bird.getY(), null);
        canvas.drawText(String.valueOf(score), AppConstants.SCREEN_WIDTH / 2 - 30, 150, scorePaint);

        if (gameState == 2) {
            canvas.drawText("GAME OVER", AppConstants.SCREEN_WIDTH / 2, AppConstants.SCREEN_HEIGHT / 2 - 100, gameOverPaint);
            
            Paint finalScorePaint = new Paint(scorePaint);
            finalScorePaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Final Score: " + score, AppConstants.SCREEN_WIDTH / 2, AppConstants.SCREEN_HEIGHT / 2 + 50, finalScorePaint);
            
            Paint tapToRetryPaint = new Paint(finalScorePaint);
            tapToRetryPaint.setTextSize(50);
            canvas.drawText("Tap to Try Again", AppConstants.SCREEN_WIDTH / 2, AppConstants.SCREEN_HEIGHT / 2 + 150, tapToRetryPaint);
        }

        if (gameState != 2) {
            currentFrame++;
            if (currentFrame > Bird.maxFrame) {
                currentFrame = 0;
            }
            bird.setCurrentFrame(currentFrame);
        }
    }
}
