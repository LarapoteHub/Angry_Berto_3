package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Exit extends Button {

    public Btn_Exit() {
        super(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 79, 64, 64, SprNames.btn_exit.name());
        this.name = SprNames.btn_exit.name();
    }

    // TODO Falta un menu extra para preguntar: De veras desea salir?
    @Override
    public void onTouch() {
        if (GameEngine.gameState.isLevelSelection() && Gdx.input.justTouched()) {
            GameEngine.gameState.mainMenu();
        } else if (GameEngine.gameState.isPaused() && Gdx.input.justTouched()) {
            GameEngine.gameState.goToLevelSelection();
        } else if (Gdx.input.justTouched()) {
            System.exit(0);
        }
    }

}
