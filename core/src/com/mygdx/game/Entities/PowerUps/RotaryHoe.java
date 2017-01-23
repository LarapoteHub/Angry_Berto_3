package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.Projectile;

import java.util.Iterator;

/**
 * Created by 100VOL on 20/08/2016.
 */
public class RotaryHoe extends Projectile {
    Iterator<Enemy> collisionEnemys;
    GameEngine gameEngineInstance;
    Enemy enContainer;

    float angle = 0;

    private int index;
    Timer.Task animation;

    public RotaryHoe(float x, float y) {
        
    	this.x = x - 6;
    	this.y = y - 6;
    	
        this.setWidth(64);
        this.setHeight(64);

        this.angle = 0;

        Sounds.rotaryHoeSound.play();

        vSpeed = 1000;

    }


    public void action(Player player, OrthographicCamera camera) {
/*
        y += 500 * Gdx.graphics.getDeltaTime();

        collisionEnemys = gameEngineInstance.enemysList.iterator();

        while(collisionEnemys.hasNext()) {
            enContainer = collisionEnemys.next();

            if (enContainer.overlaps(this)) {

                enContainer.decreaseLives(enContainer.getLives());

            }
        }

        if (this.y >= 864) {

            remove = true;

        }
*/
    } //end action()

    private void increaseIndex() {
        index++;
    }

    public void draw() {
        // TODO Reñir a Dani por no usar la clase "Sprite"
    	Sprites.powerUp_rotaryHoe.rotate(30);
        GameEngine.batch.draw(Sprites.powerUp_rotaryHoe.getTexture(), x, y, getWidth(), getHeight());
        //GameEngine.batch.draw(Sprites.rotaryHoePowerUpImage[0])

    }

    @Override
    public void decreaseLives(int lives) {

    }

    @Override
    public void move() {
    	this.y += vSpeed * Gdx.graphics.getDeltaTime();
    	this.x += hSpeed * Gdx.graphics.getDeltaTime();
    }

    /*public void play() {

        animation = new Timer.Task(){
            @Override
            public void run() {

                if (index <= 2) {
                    increaseIndex();
                } else {
                    index = 0;
                }

            }

        };

        //cada 0.06 segundos incrementamos el índice de la imagen.
        Timer.schedule(animation,0.05f, 0.05f);
    }*/



}
