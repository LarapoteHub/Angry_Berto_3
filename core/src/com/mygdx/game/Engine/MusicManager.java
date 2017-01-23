package com.mygdx.game.Engine;

import com.badlogic.gdx.audio.Music;

/**
 * Created by Red Mercy on 12/10/2016.
 */

public class MusicManager {

    private static Music currentMusic = null;

    public static void setMusic(Music m) {
        dispose();

        currentMusic = m;

        currentMusic.setLooping(true);
        currentMusic.play();
    }

    public static void dispose() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }
    }
}
