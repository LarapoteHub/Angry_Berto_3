package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameEngine.EntityType;
import com.mygdx.game.Entities.Enemies.StandardEnemy;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 29/08/2016.
 */

// TODO ESTO ES UN PUÑETERO LIO!!!!!
public class Scr_Introduction implements Screen {

    private int index;
    private int fireIndex;
    private int indexEnemy;
    boolean ascending;
    boolean descending;


    Timer.Task animation = new Timer.Task(){
        @Override
        public void run() {

            if (fireIndex==1) {
                index = 0;

            } else if (fireIndex==0){
                index = 1;
            }

            if (fireIndex < 2) {
                fireIndex++;
            } else {
                fireIndex=0;
            }


            if (ascending) {

                if (indexEnemy < 3) {
                    indexEnemy++;

                } else {
                    ascending = false;
                    descending = true;
                }

            }

            if (descending) {

                if (indexEnemy > 0) {

                    indexEnemy--;

                } else {

                    ascending = true;
                    descending = false;
                }

            }


        }

    };




    private int enemyX;
    private int enemyY;
    private int enemyX2;
    private int enemyY2;
    private int bertoX;
    private int bertoY;
    private int bertolinaX;
    private int bertolinaY;
    boolean playDingSound;
    boolean playBertoSound;
    boolean playBertolinaSound;

    @Override
    public void initComponents() {

        //Sounds.dingSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/ding.mp3"));
        enemyX = 25;
        enemyY = 800;
        bertoX = 480;
        bertoY = 170;
        enemyX2 = -96; //-48
        enemyY2 = 290;
        bertolinaX = -104; //-56
        bertolinaY = 290;
        playDingSound = true;
        playBertoSound = true;
        playBertolinaSound = true;

        indexEnemy = 0;
        ascending = true;
        descending = false;

        index = 0;
        fireIndex = 0;

        StandardEnemy enem1 = new StandardEnemy(enemyX, enemyY, 0);
        GameEngine.addEntity(enem1, EntityType.OTHER);
        
        Timer.schedule(animation, 0.09f, 0.09f);

        
        
    }

    public void draw(SpriteBatch batch, BitmapFont printer) {

        //COMENTAR
        //MyGdxGame.GAME_MENU = false;

        batch.begin();

        batch.draw(Backgrounds.backgroundIntro, 0, 0);

        //batch.draw(Sprites.enemy_std[indexEnemy], enemyX, enemyY, 96, 96); //48x48

        //batch.draw(Sprites.enemy_std[indexEnemy], enemyX2, enemyY2, 96, 96); //48x48

        batch.draw(Sprites.berto, bertoX, bertoY, 100, 150);

        batch.draw(Sprites.player[0], bertolinaX, bertolinaY, 104, 104); //56x56

        batch.draw(Sprites.player_propulsion[fireIndex], bertolinaX + 25, bertolinaY - 32, 16, 32); //8x16
        batch.draw(Sprites.player_propulsion[fireIndex], bertolinaX + 63, bertolinaY - 32, 16, 32); //8x16


        if (enemyY>290) {
            enemyY -= Gdx.graphics.getDeltaTime() * 200;
        } else {

            if (playDingSound) {

                Sounds.dingSound.play();
                playDingSound = false;
            }

            if (bertoX>100) {
                bertoX -= Gdx.graphics.getDeltaTime() * 300;
            }  else {
                if (playBertoSound) {

                        Sounds.playerHitSound.play();
                        playBertoSound = false;

                }

                enemyX -= Gdx.graphics.getDeltaTime() * 150;

                if (enemyX<-96) { //<-76
                    bertoX -= Gdx.graphics.getDeltaTime() * 300;
                }

                if (bertoX < -100) {

                    enemyX2+= Gdx.graphics.getDeltaTime() * 250;
                    enemyY2+= Gdx.graphics.getDeltaTime() * 250;

                    if (enemyY2 >= 450) {

                        if (playBertolinaSound) {
                            Sounds.bertolinaSound.play();
                            playBertolinaSound = false;
                        }
                        bertolinaX+= Gdx.graphics.getDeltaTime() * 250;
                        bertolinaY+= Gdx.graphics.getDeltaTime() * 250;

                    }

                    if (bertolinaY >= 856) {
                        Musics.backgroundMusic.play();
                        Sounds.bertolinaSound.stop();
                        animation = null;
                        //comienza el juego de verdad :D
                        GameEngine.gameState.play();
                    }

                }

                //MyGdxGame.GAME_MENU = false;
            }

        }



        batch.end();


    }

	//@Override
	//public void initComponents() {
		// TODO Auto-generated method stub
		
	//}
}
