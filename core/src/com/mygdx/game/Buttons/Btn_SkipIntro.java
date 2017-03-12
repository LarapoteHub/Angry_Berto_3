package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 22/02/2017.
 */

public class Btn_SkipIntro extends Button {

    public Btn_SkipIntro() {
        super(350, 20, 100, 80, SprNames.btn_skipIntro.name());
        this.name = SprNames.btn_skipIntro.name();
    }

    @Override
    public void onTouch() {
        GameEngine.gameState.goToLevelSelection();
    }
}
