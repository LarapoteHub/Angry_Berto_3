package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Scores extends Button {
	
    public Btn_Scores() {
        super(170, 20, 140, 120, SprNames.btn_scores.name());
        this.name = SprNames.btn_scores.name();
    }

    @Override
    public void onTouch() {
        GameEngine.gameState.goToScoresScreen();
    }
}
