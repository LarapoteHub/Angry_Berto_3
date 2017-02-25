package com.mygdx.game.Screens;

import com.mygdx.game.Buttons.Btn_SkipIntro;
import com.mygdx.game.Engine.MusicManager;
import com.mygdx.game.Entities.PlainAnimations.Intro_Berto;
import com.mygdx.game.Entities.PlainAnimations.Intro_Enemy;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EntityType;

/**
 * Created by 100VOL on 29/08/2016.
 */

// TODO ESTO ES UN PUÑETERO LIO!!!!!
public class Scr_Introduction implements Screen {

    boolean ascending;
    boolean descending;

    public static boolean playDingSound = true;
    public static boolean playBertoSound = true;
    public static boolean playBertolinaSound = true;

    @Override
    public void initComponents() {

        MusicManager.setMusic(null);

        Intro_Berto introBerto = new Intro_Berto(480, 170);
        Intro_Enemy introEnemy = new Intro_Enemy(25, 800);

        introBerto.setRef(introEnemy);
        introEnemy.setRef(introBerto);

        GameEngine.addEntity(introBerto, EntityType.PLAIN_ANIMATION);
        GameEngine.addEntity(introEnemy, EntityType.PLAIN_ANIMATION);

        GameEngine.addButton(new Btn_SkipIntro());
        
    }
}
