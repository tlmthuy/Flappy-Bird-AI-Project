package com.example.flappythuy;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class SoundBank {
    private MediaPlayer music;
    private Context context;

    public SoundBank(Context context) {
        this.context = context;
        initMusic();
    }

    private void initMusic() {
        try {
            music = MediaPlayer.create(context, R.raw.dst);
            if (music != null) {
                music.setLooping(true);
                music.setVolume(1.0f, 1.0f);
                Log.d("SOUND", "MediaPlayer initialized successfully");
            } else {
                Log.e("SOUND", "Failed to create MediaPlayer - check if dst.mp3 exists in res/raw");
            }
        } catch (Exception e) {
            Log.e("SOUND", "Error initializing MediaPlayer: " + e.getMessage());
        }
    }

    public void playMusic() {
        if (music == null) {
            initMusic();
        }
        
        try {
            if (music != null && !music.isPlaying()) {
                music.start();
                Log.d("SOUND", "Music started playing");
            }
        } catch (Exception e) {
            Log.e("SOUND", "Error playing music: " + e.getMessage());
        }
    }

    public void stopMusic() {
        try {
            if (music != null && music.isPlaying()) {
                music.pause();
                Log.d("SOUND", "Music paused");
            }
        } catch (Exception e) {
            Log.e("SOUND", "Error stopping music: " + e.getMessage());
        }
    }
    
    public void playWing() {}
    public void playPoint() {}
    public void playDie() {}
}
