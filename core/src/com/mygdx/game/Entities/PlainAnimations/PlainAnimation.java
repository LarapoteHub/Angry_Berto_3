package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Entities.Entity;

/**
 * Created by xldan on 10/02/2017.
 */

public abstract class PlainAnimation extends Entity {

    protected TextureRegion currentFrame;

    protected int FRAME_COLS;
    protected int FRAME_ROWS;

    protected AnimationAdapter currentAnimation;

    protected float stateTime = 0f;

    @Override
    public abstract void draw();

    @Override
    public void decreaseLives(float lives) {
        //without lives.
    }

    @Override
    public void move() {

    }

    public abstract boolean isFinished();

    public abstract void initAnimation();

}
