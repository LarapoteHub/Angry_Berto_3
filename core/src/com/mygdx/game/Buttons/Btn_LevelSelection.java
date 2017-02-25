package com.mygdx.game.Buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by xldan on 25/02/2017.
 */

public class Btn_LevelSelection extends Button {

    public Btn_LevelSelection(Sprite spr, String name) {
        super(350, 200, 80, 50, spr);
        this.name = name;
    }

    @Override
    public void onTouch() {
        GameEngine.gameState.play();
    }
}
