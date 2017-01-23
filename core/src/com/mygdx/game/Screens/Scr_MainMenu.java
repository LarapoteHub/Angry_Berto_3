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

    // Ya no se implementa...
    /*public void menu(boolean recently_touched, SpriteBatch batch, OrthographicCamera camera) {

        try {

            if (playMusic) {

                Musics.backgroundMenuMusic.play();
                playMusic = false;

            }

            batch.begin();

            if (!Gdx.input.isTouched()) {
                MyGdxGame.recently_touched = false;
            }



            //dibujamos el fondo del menú.
            //gameEngineInstace.drawBackground(batch, true);
            //dibujamos el boton de salir
            batch.draw(Sprites.btn_exit[0], exitButtonBox.x, exitButtonBox.y, exitButtonBox.width, exitButtonBox.height);
            //dibujamos el boton de comenzar
            batch.draw(Sprites.btn_init[0], initButtonBox.x, initButtonBox.y);
            //que quede esto bien es mas increible que WINDOWS 2000
            batch.draw(Sprites.berto, 38.99f, 217, 234, 311); //antes y = 237
            batch.draw(Sprites.lina, 205.72f , 217, 234, 311); //antes y = 237
            batch.draw(Sprites.logo, logoX, logoY); //y = 525

            if (logoY > 525) {

                logoY -= Gdx.graphics.getDeltaTime() * 100;

            } else {
                touchable = true;
            }

            batch.draw(Sprites.btn_options[0], optionsButtonBox.x, optionsButtonBox.y, optionsButtonBox.width, optionsButtonBox.height);
            batch.draw(Sprites.btn_scores[0], scoresButtonBox.x, scoresButtonBox.y, scoresButtonBox.width, scoresButtonBox.height);
            batch.draw(Sprites.btn_achievements[0], archievementsButtonBox.x, archievementsButtonBox.y, archievementsButtonBox.width, archievementsButtonBox.height);




            if (Gdx.input.isTouched() && !recently_touched && touchable) {


                //touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

                //System.out.println("EQUIS: "+Gdx.input.getX());
                camera.unproject(touchPos);

                //SI LA POSICIÓN QUE TOCAMOS COINCIDE CON EL BOTÓN DE INICIO, CAMBIAREMOS SU IMAGEN
                if (touchPos.x >= initButtonBox.x && touchPos.x <= (initButtonBox.x + initButtonBox.width)
                        && touchPos.y >= initButtonBox.y && touchPos.y <= initButtonBox.y + initButtonBox.height) {

                    batch.draw(Sprites.btn_init[1], initButtonBox.x, initButtonBox.y);

                }

                if (touchPos.x >= optionsButtonBox.x && touchPos.x <= (optionsButtonBox.x + optionsButtonBox.width)
                        && touchPos.y >= optionsButtonBox.y && touchPos.y <= optionsButtonBox.y + optionsButtonBox.height) {

                    batch.draw(Sprites.btn_options[1], optionsButtonBox.x, optionsButtonBox.y, optionsButtonBox.width, optionsButtonBox.height);
                }

                if (touchPos.x >= scoresButtonBox.x && touchPos.x <= (scoresButtonBox.x + scoresButtonBox.width)
                        && touchPos.y >= scoresButtonBox.y && touchPos.y <= scoresButtonBox.y + scoresButtonBox.height) {

                    batch.draw(Sprites.btn_scores[1], scoresButtonBox.x, scoresButtonBox.y, scoresButtonBox.width, scoresButtonBox.height);

                }

                if (touchPos.x >= archievementsButtonBox.x && touchPos.x <= (archievementsButtonBox.x + archievementsButtonBox.width)
                        && touchPos.y >= archievementsButtonBox.y && touchPos.y <= archievementsButtonBox.y + archievementsButtonBox.height) {

                    batch.draw(Sprites.btn_achievements[1], archievementsButtonBox.x, archievementsButtonBox.y, archievementsButtonBox.width, archievementsButtonBox.height);

                }

                if (touchPos.x >= exitButtonBox.x && touchPos.x <= (exitButtonBox.x + 64)
                        && touchPos.y >= exitButtonBox.y && touchPos.y <= exitButtonBox.y + 64) {

                    batch.draw(Sprites.btn_exit[1], exitButtonBox.x, exitButtonBox.y, 64, 64);
                }





            }

                if(!Gdx.input.isTouched()) {



                if (touchPos.x >= initButtonBox.x && touchPos.x <= (initButtonBox.x + initButtonBox.width)
                        && touchPos.y >= initButtonBox.y && touchPos.y <= initButtonBox.y + initButtonBox.height) {

                    //Ahí vai o marcián move-lo marco
                	GameEngine.gameState.showIntro();

                    //cambiamos la música del menú por la de juego.
                    Musics.backgroundMenuMusic.stop();
                    //Musics.backgroundMusic.play();

                }

                if (touchPos.x >= exitButtonBox.x && touchPos.x <= (exitButtonBox.x + 64)
                        && touchPos.y >= exitButtonBox.y && touchPos.y <= exitButtonBox.y + 64) {

                    System.exit(0);
                    //dispose();

                }

            } //end !isTouched();

            batch.end();

            //return recently_touched;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }*/

}
