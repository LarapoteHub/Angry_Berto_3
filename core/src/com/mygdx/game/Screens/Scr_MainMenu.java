package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Buttons.Btn_Achievements;
import com.mygdx.game.Buttons.Btn_Exit;
import com.mygdx.game.Buttons.Btn_Mute;
import com.mygdx.game.Buttons.Btn_Scores;
import com.mygdx.game.Buttons.Btn_Options;
import com.mygdx.game.Buttons.Btn_Init;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 15/08/2016.
 */
public class Scr_MainMenu implements Screen {

    //Music background;
    public boolean playMusic;

    public void initComponents() {

        GameEngine.addButton(new Btn_Init());
        GameEngine.addButton(new Btn_Exit(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 5));
        GameEngine.addButton(new Btn_Options());
        GameEngine.addButton(new Btn_Scores());
        GameEngine.addButton(new Btn_Achievements());
        // TODO RedMercy - RuLoSp Was here
        GameEngine.addButton(new Btn_Mute());
        
        /* Ref:
         * logoX = 75;
         * logoY = 525;
         */
        // Hace falta en 2 lineas para poder poner la posicion...
        Sprite spr = new Sprite(Sprites.getSpriteByName("logo")[0]);
        spr.setPosition(75, 525);
        GameEngine.addImage(spr);
        
        
        // Agregamos al gañuflas
        Sprite sprBerto = Sprites.getSpriteByName("berto")[0];
        sprBerto.setPosition(38.99f, 217);
        sprBerto.setSize(234, 311);
        
        GameEngine.addImage(sprBerto);
        
        // Agregamos a su compinche
        Sprite sprLina = new Sprite(Sprites.getSpriteByName("lina")[0]);
        sprLina.setPosition(205.72f, 217);
        sprLina.setSize(234, 311);
        
        GameEngine.addImage(sprLina);

        Musics.backgroundMenuMusic.setLooping(true);

        playMusic = !GameEngine.isMuted();


    }

}
