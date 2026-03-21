package com.example.flappythuy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    Bitmap background;
    Bitmap bird[];


    public BitmapBank(Resources resources){
        background = BitmapFactory.decodeResource(resources, R.drawable.background);
        background = scaleImage(background);
        bird = new Bitmap [4];
        bird[0] = BitmapFactory.decodeResource(resources, R.drawable.dvh1);
        bird[1] = BitmapFactory.decodeResource(resources, R.drawable.dvh2);
        bird[2] = BitmapFactory.decodeResource(resources, R.drawable.dvh3);
        bird[3] = BitmapFactory.decodeResource(resources, R.drawable.dvh4);
    }

    public Bitmap getBird(int frame){
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }

    public int getBirdHeight(){
        return bird[0].getHeight();
    }


    public Bitmap getBackground(){
        return background;
    }

    public int getBackgroundWidth(){
        return background.getWidth();
    }

    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap) {
        float widthHeightRatio = (float) bitmap.getWidth() / (float) bitmap.getHeight();

        // Sửa dòng này: Nhân trước rồi mới ép kiểu int cho cả kết quả
        int backgroundScalerWidth = (int) (widthHeightRatio * AppConstants.SCREEN_HEIGHT);

        return Bitmap.createScaledBitmap(bitmap, backgroundScalerWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
