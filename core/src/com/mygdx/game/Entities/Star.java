package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Effects;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 18/08/2016.
 */
public class Star extends Entity {

    private ParticleEffect p = new ParticleEffect(Effects.star);

    public Star() {
        x = MathUtils.random(68, 480 - 2); // antes 0
        y = 800;
        vSpeed = (-MathUtils.random(1000, 1500));
        // Esto es opcional
        hSpeed = (-MathUtils.random(10, 30));

        p.start();
    }

    @Override
    public void draw() {
        p.draw(GameEngine.batch);
    }

    @Override
    public void kill() {
        remove = true;
    }

    @Override
    public void decreaseLives(float lives) {
        // NO USADO
    }

    @Override
    public void move() {
        this.y += vSpeed * Gdx.graphics.getDeltaTime();
        this.x += hSpeed * Gdx.graphics.getDeltaTime();
//        x = 300;
//        y = 300;
        p.setPosition(x, y);
        //p.getEmitters().first().setPosition(x, y);
        p.update(Gdx.graphics.getDeltaTime());


        // TODO Poner esto en funcion de un rectangulo especial PLEASE!
        if (p.isComplete()) {
            p.reset();
        }
        if (y < -300 || x + width < Backgrounds.backgroundPowerUps.getWidth()) {

            p.dispose();
            kill();

        }
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public void setX(float x) {

        this.x = x;
    }

    public void setY(float y) {

        this.y = y;
    }

    public void setVSpeed(float vspeed) {

        this.vSpeed = vspeed;
    }

    public float getVspeed() {

        return vSpeed;
    }


}

