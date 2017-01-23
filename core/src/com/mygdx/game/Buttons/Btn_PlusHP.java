package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

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
