package com.example.flappythuy;

import java.util.Random;

public class Pipe {
    private int pipeX, topPipeY;
    public static int pipeVelocity = 18; 
    public static int pipeGap = 600; 
    public static int pipeWidth = 200;
    private Random random;
    private boolean scored; // Cờ đánh dấu đã tính điểm cho ống này chưa

    public Pipe(int x) {
        this.pipeX = x;
        random = new Random();
        scored = false;
        resetPipe();
    }

    public void resetPipe() {
        int minTopPipeHeight = 200;
        int maxTopPipeHeight = AppConstants.SCREEN_HEIGHT - pipeGap - 200;
        topPipeY = minTopPipeHeight + random.nextInt(Math.max(1, maxTopPipeHeight - minTopPipeHeight));
        scored = false; // Reset cờ khi ống quay lại bên phải
    }

    public int getPipeX() {
        return pipeX;
    }

    public void setPipeX(int pipeX) {
        this.pipeX = pipeX;
    }

    public int getTopPipeY() {
        return topPipeY;
    }

    public int getBottomPipeY() {
        return topPipeY + pipeGap;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
