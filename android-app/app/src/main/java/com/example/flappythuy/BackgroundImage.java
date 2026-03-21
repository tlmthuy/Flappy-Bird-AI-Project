package com.example.flappythuy;

public class BackgroundImage {
    private int backgroundImageX, backgroundImageY, getBackgroundVelocity;

    public BackgroundImage() {
        backgroundImageX = 0;
        backgroundImageY = 0;
        getBackgroundVelocity = 10; // Tăng thêm nữa để game nhanh hơn
    }

    public int getX() {
        return backgroundImageX;
    }

    public int getY() {
        return backgroundImageY;
    }

    public int getVelocity() {
        return getBackgroundVelocity;
    }

    public void setX (int x) {
        backgroundImageX = x;
    }

    public void setY (int y) {
        backgroundImageY = y;
    }

    public void setVelocity (int velocity){
        getBackgroundVelocity = velocity;
    }
}
