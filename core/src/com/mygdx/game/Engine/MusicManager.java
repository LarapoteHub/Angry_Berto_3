package com.mygdx.game.Engine;

import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 12/10/2016.
 */

public class MusicManager {

    private static Music currentMusic = null;


    public static void setMusic(Music m) {
        if (currentMusic != null) {
            currentMusic.stop();
        }
        //dispose(); ??
        currentMusic = m;
        if (!GameEngine.isMuted()) {
            if (currentMusic != null) {
                currentMusic.setLooping(true);
                currentMusic.play();
            }
        }
    }

    public static void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            //currentMusic = null;
        }
    }

    public static void playMusic() {
        if (currentMusic != null) {
            currentMusic.play();
        }
    }

    public static void dispose() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }
    }
}
