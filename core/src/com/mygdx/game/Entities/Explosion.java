package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

import java.util.Iterator;

/**
 * Created by 100VOL on 17/08/2016.
 */
public class Explosion extends Entity {

    private float x;
    private float y;
    private int index;
    Timer.Task animation;

    public boolean remove;

    public Explosion(float x, float y) {

        this.x = x;
        this.y = y;
        index = 0;

        //iniciamos la animación.
        play();

        action();
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public int getIndex() {

        return index;

    }

    public void increaseIndex() {

        index++;
    }

    @Override
    public void draw() {

        GameEngine.batch.draw(Sprites.explosion[index].getTexture(), x, y, 56, 56);

    }

    public void play() {

        // TODO Find an alternative
        animation = new Timer.Task() {
            @Override
            public void run() {

                if (index <= 21) {
                    increaseIndex();
                }

            }

        };

        //cada 0.06 segundos incrementamos el índice de la imagen.
        Timer.schedule(animation, 0.0f, 0.06f);


    }

    public void action() {

        if (index >= 21) {
            remove = true;
        }


    }

    @Override
    public void kill() {

    }

    @Override
    public void decreaseLives(int lives) {
        // Doesn't have lives
    }

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
