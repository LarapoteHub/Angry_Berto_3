package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 18/08/2016.
 */
public class Star extends Entity{

    private Texture starImage = Sprites.star.getTexture();


    public Star(float x, float y) {
    	this.x = x;
    	this.y = y;
    	this.vSpeed = -100;
    	// Esto es por comodidad mas que nada
    	this.width = starImage.getWidth();
    	this.height = starImage.getHeight();
    }

    @Override
    public void draw() {
        GameEngine.batch.draw(starImage, x, y);
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

        // TODO Poner esto en funcion de un rectangulo especial PLEASE!
        if (y < 0 || x + width < Backgrounds.backgroundPowerUps.getWidth())
            kill();
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
