package com.mygdx.game.Buttons;

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
    	super(10, 213, 48, 48, Sprites.btn_plusHP);
    	isPowerUp = true;
    	name = "btn_PlusHP";
    	cost = 75;
    }

    /*
                if (player.getLives() < 5 && player.getCharge() >= 75) {
                    player.incrementLives(1);
                    Sounds.liveRestoredSound.play();
                    player.decreaseCharge(75);


            }
        }*/


    
    @Override
    public void onTouch() {

    	if ((engine.getPlayer().getLives() < 5 && engine.getPlayer().getCharge() >= cost) || MyGdxGame.DEBUG_MODE) {
    		engine.getPlayer().incrementLives(1);
            Sounds.liveRestoredSound.play();
            engine.getPlayer().decreaseCharge(cost);
    	}
    }


}
