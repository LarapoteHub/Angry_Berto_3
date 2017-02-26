package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Exit extends Button {
    // Antes
    /*
    exitButtonBox = new Rectangle();

    exitButtonBox.width = 64;
    exitButtonBox.height = 64;

    exitButtonBox.x = 480-64-5;
    exitButtonBox.y = 800-138-5;

    */

    public Btn_Exit() {
        super(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 79, 64, 64, "btn_exit");
        this.name = "btn_exit";
    }

    @Override
    public void onTouch() {
        if (GameEngine.gameState.isPaused() && Gdx.input.justTouched())
            GameEngine.gameState.mainMenu();
        else if (Gdx.input.justTouched())
            System.exit(0);
    }

    public void onRelease() {

    }
}
