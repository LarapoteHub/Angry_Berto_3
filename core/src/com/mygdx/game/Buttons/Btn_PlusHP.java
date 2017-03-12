package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 20/08/2016.
 * Modded by RedMercy since then.
 */
public class Btn_PlusHP extends PowerUp_Button {
	
    public Btn_PlusHP() {
    	// Calculos de Dani: x = 10; y = 213; width = 48; height = 48
    	super(10, 213, 48, 48, SprNames.btn_plusHP.name());
    	isPowerUp = true;
    	name = SprNames.btn_plusHP.name();
    	cost = 75;
    }

    @Override
    public void onTouch() {

    	if ((GameEngine.getPlayer().getLives() < 5 && GameEngine.getPlayer().getCharge() >= cost) || MyGdxGame.DEBUG_MODE) {
            GameEngine.getPlayer().incrementLives(1);
            Sounds.liveRestoredSound.play();
            GameEngine.getPlayer().decreaseCharge(cost);
    	}
    }
}
