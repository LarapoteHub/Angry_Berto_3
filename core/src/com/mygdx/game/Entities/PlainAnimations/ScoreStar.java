package com.mygdx.game.Entities.PlainAnimations;

import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by xldan on 04/03/2017.
 */

public class ScoreStar extends PlainAnimation {

    public ScoreStar(float x, float y) {

        this.x = x;
        this.y = y;

        this.width = 96;
        this.height = 96;

        Sprites.getSpriteByName("scoreStar")[0].setSize(width, height);

    }

    @Override
    public void draw() {

        Sprites.getSpriteByName("scoreStar")[0].setPosition(x, y);
        Sprites.getSpriteByName("scoreStar")[0].draw(GameEngine.batch);

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void initAnimation() {

    }
}
