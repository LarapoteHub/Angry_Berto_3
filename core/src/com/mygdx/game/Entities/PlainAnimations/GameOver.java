package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;
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

        Sprites.getSpriteByName("gameOver")[0].setSize(width, height);
    }

    @Override
    public void draw() {

        if (y > 500) {
            this.y += vSpeed * Gdx.graphics.getDeltaTime();
        } else if (!Scr_GameOver.textAdded) {
            GameEngine.addText(new Text("TOUCH THE SCREEN", MyGdxGame.WIDTH/2, 180, Color.WHITE).setCenterToPoint(true));
            Scr_GameOver.textAdded = true;
        }


        Sprites.getSpriteByName("gameOver")[0].setPosition(x, y);
        Sprites.getSpriteByName("gameOver")[0].draw(GameEngine.batch);
        //GameEngine.batch.draw(Sprites.gameOver.getTexture(), x, y, width, height);

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void initAnimation() {

    }
}
