package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
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
    Sprite spr;

    float angle = 0;

    private int index;
    Timer.Task animation;

    public RotaryHoe(float x, float y) {
        this.spr = new Sprite(Sprites.powerUp_rotaryHoe);
        spr.rotate(MathUtils.random(0, 350));

    	this.x = x - 6;
    	this.y = y - 6;
        spr.setPosition(x - 6, y - 6);

        this.setWidth(64);
        this.setHeight(64);
        this.damage = 3;
        this.angle = 0;

        Sounds.rotaryHoeSound.play();

        vSpeed = 1000;

    }

    @Override
    public void destroy() {
        // Override hecho para que no se destruya al chocar
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
    	spr.rotate(MathUtils.random(15, 45));

        spr.draw(GameEngine.batch);
        //GameEngine.batch.draw(Sprites.rotaryHoePowerUpImage[0])

    }

    @Override
    public void decreaseLives(float lives) {

    }

    @Override
    public void move() {
        this.x += hSpeed * Gdx.graphics.getDeltaTime();
        this.y += vSpeed * Gdx.graphics.getDeltaTime();
        spr.setPosition(x - 6, y - 6);
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
