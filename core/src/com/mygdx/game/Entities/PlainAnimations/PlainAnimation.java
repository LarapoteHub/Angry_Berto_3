package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entities.Entity;

/**
 * Created by xldan on 10/02/2017.
 */

public abstract class PlainAnimation extends Entity {


    @Override
    public abstract void draw();

    @Override
    public void decreaseLives(int lives) {

    }

    @Override
    public void move() {

    }

    public abstract boolean isFinished();

}
