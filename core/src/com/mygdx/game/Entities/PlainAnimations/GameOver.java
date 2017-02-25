package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Screens.Scr_GameOver;

/**
 * Created by xldan on 25/02/2017.
 */

public class GameOver extends PlainAnimation {

    public GameOver(float x, float y) {

        this.x = x;
        this.y = y;
        this.width = 300;
        this.height = 200;

        this.vSpeed = -200;

    }

    @Override
    public void draw() {

        if (y > 500) {
            this.y += vSpeed * Gdx.graphics.getDeltaTime();
        } else if (!Scr_GameOver.textAdded) {
            GameEngine.addText(new Text("TOUCH THE SCREEN", 85, 200, Color.WHITE));
            Scr_GameOver.textAdded = true;
        }

        GameEngine.batch.draw(Sprites.gameOver.getTexture(), x, y, width, height);

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void initAnimation() {

    }
}
