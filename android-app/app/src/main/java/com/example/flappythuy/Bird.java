package com.example.flappythuy;

public class Bird {
    private int birdX, birdY, currentFrame, velocity;
    public static int maxFrame;
    public static int gravity = 12; // Tăng trọng lực lên 12 để rơi cực nhanh
    public static int jumpVelocity = -70; // Tăng sức nhảy mạnh hơn nữa để bù lại trọng lực

    public Bird () {
        birdX = AppConstants.SCREEN_WIDTH / 2 - AppConstants.getBitmapBank().getBirdWidth() / 2;
        birdY = AppConstants.SCREEN_HEIGHT / 2 - AppConstants.getBitmapBank().getBirdHeight() / 2;
        currentFrame = 1;
        maxFrame = 3;
        velocity = 0;
    }

    public int getVelocity() { 
        return velocity; 
    }
    
    public void setVelocity(int velocity) { 
        this.velocity = velocity; 
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    public int getX() {
        return birdX;
    }
    public int getY() {
        return birdY;
    }
    public void setX (int x) {
        birdX = x;
    }
    public void setY (int y) {
        birdY = y;
    }

}
