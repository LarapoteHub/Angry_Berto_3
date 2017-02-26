package com.mygdx.game.Buttons;

import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 22/02/2017.
 */

public class Btn_SkipIntro extends Button {

    public Btn_SkipIntro() {
        super(350, 20, 100, 80, "btn_skip");
        this.name = "btn_skip";
    }

    @Override
    public void onTouch() {
        GameEngine.gameState.goToLevelSelection();
    }
}
