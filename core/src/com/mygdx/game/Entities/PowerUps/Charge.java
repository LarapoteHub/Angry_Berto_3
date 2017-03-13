package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 05/09/2016.
 */
// ESTO ES UN OTHER!!
public class Charge extends PowerUp {

	private Sprite spr;

    public Charge(float x, float y) {
        super(x,y, "powerUp_charge_increase");
        
        setWidth(32);
        setHeight(32);
        vSpeed = -200;		// Igual se tiene que ajustar esto...
        
        damage = 0;
        
        charge = 20;
		spr = new Sprite(Sprites.getSpriteByName("powerUp_charge_increase")[0]);
		spr.setSize(width, height);

    }


    public void draw() {
		spr.draw(GameEngine.batch);
    }

	@Override
	public void decreaseLives(float lives) {
		// Ehm... no implementado? xD
	}

	@Override
	public void move() {
		this.y += vSpeed * Gdx.graphics.getDeltaTime();
		spr.setPosition(x, y);
	}
	
	@Override
	public void destroy() {
		kill();
		
		if (gotIt)
			Sounds.chargeSound.play();
		
	}
}
