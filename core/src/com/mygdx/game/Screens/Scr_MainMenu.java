package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Buttons.*;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 15/08/2016.
 */
public class Scr_MainMenu {

    //private SpriteBatch batch;
    private Rectangle initButtonBox;
    private Rectangle exitButtonBox;
    private Rectangle optionsButtonBox;
    private Rectangle scoresButtonBox;
    private Rectangle archievementsButtonBox;

    private int logoX;
    private int logoY;

    public static boolean recently_touched;
    private Vector3 touchPos;

    //Music background;
    public boolean playMusic;

    //variable que impedira la interaccion durante la animacion del logo
    public boolean touchable;

    public void initComponents() {


        //this.batch = new SpriteBatch();

        logoX = 75;
        logoY = 800;
        
        GameEngine.addButton(new Btn_Play());
        GameEngine.addButton(new Btn_Exit());
        GameEngine.addButton(new Btn_Options());
        GameEngine.addButton(new Btn_HighScore());
        GameEngine.addButton(new Btn_Achievements());
        
        /* Ref:
         * logoX = 75;
         * logoY = 525;
         */
        // Hace falta en 2 lineas para poder poner la posicion...
        Sprite spr = new Sprite(Sprites.logo);
        spr.setPosition(75, 525);
        GameEngine.addImage(spr);
        
        
        // Agregamos al gañuflas
        Sprite sprBerto = new Sprite(Sprites.berto);
        sprBerto.setPosition(38.99f, 217);
        sprBerto.setSize(234, 311);
        
        GameEngine.addImage(sprBerto);
        
        // Agregamos a su compinche
        Sprite sprLina = new Sprite(Sprites.lina);
        sprLina.setPosition(205.72f, 217);
        sprLina.setSize(234, 311);
        
        GameEngine.addImage(sprLina);
        
        

        //background = Gdx.audio.newMusic(Gdx.files.internal("audio/music/backgroundMenu.mp3"));
        Musics.backgroundMenuMusic.setLooping(true);

        playMusic = true;


    }

}
