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
    // TODO Esto no funca bien...
    private float targetX, targetY, accelX = 0, step;

    float angle = 0;

    public RotaryHoe(float x, float y) {
        this.x = x;
        this.y = y;
        initSprite();
        initCurve();

        this.setWidth(64);
        this.setHeight(64);
        this.damage = 3;
        this.angle = 0;

        Sounds.rotaryHoeSound.play();

        vSpeed = 1000;

    }

    private void initSprite() {
        this.spr = new Sprite(Sprites.powerUp_rotaryHoe);
        spr.rotate(MathUtils.random(0, 350));

        x -= 6;
        y -= 6;
        spr.setPosition(x, y);
    }

    private void initCurve() {
        targetX = this.x;
        targetY = -100;
        // TODO CORREGIR ESTO!!
        int direccion = (int) Math.round(Math.random());

        accelX = MathUtils.random(10, 50);

        if (direccion == 1)
            accelX = -accelX;
        step = accelX * 0.005f;
        System.out.println(accelX);
    }

    @Override
    public void destroy() {
        // Override hecho para que no se destruya al chocar
    }


    public void action(Player player, OrthographicCamera camera) {
    } //end action()

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
        this.x += (hSpeed + accelX)* accelX * Gdx.graphics.getDeltaTime();
        this.y += vSpeed * Gdx.graphics.getDeltaTime();
        spr.setPosition(x - 6, y - 6);


        if (step > 0) {
            accelX -= step;
        } else {
            accelX += step;
        }


    }
}
