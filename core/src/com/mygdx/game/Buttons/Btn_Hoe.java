package com.mygdx.game.Buttons;

import com.mygdx.game.Entities.PowerUps.RotaryHoe;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EntityType;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 20/08/2016.
 * Modded by RedMercy since then.
 */
public class Btn_Hoe extends PowerUp_Button {

    private int damage = 10;

    public Btn_Hoe() {
        super(10, 150, 48, 48, Sprites.btn_rotaryHoe);
        isPowerUp = true;
        name = "btn_Hoe";
        cost = 50;
    }

    @Override
    public void onTouch() {
    	if ((engine.getPlayer().getCharge() >= cost) || MyGdxGame.DEBUG_MODE) {
    		GameEngine.addEntity(new RotaryHoe(engine.getPlayer().getX(), engine.getPlayer().getY()), EntityType.BULLET_PLAYER);
    		engine.getPlayer().decreaseCharge(cost);
    	}
    }
    
    public int getCost() {
    	return cost;
    }
    
    public int getDamage() {
    	return damage;
    }

    // TODO Consider if we really need the "Act" function or not...
    //@Override
    public void act(float x, float y, int currentCharge) {
        //engine.entities.add(new RotaryHoe(x, y, engine));
    }
}
