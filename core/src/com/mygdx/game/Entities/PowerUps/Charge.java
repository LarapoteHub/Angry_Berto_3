package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Entities.Entity;

/**
 * Created by 100VOL on 05/09/2016.
 */
// ESTO ES UN OTHER!!
public class Charge extends PowerUp {
    

    public Charge(float x, float y) {
        super(x,y, Sprites.powerUp_charge_increase);
        
        setWidth(32);
        setHeight(32);
        vSpeed = -200;		// Igual se tiene que ajustar esto...
        
        damage = 0;
        
        charge = 20;

        //powerUpImage = new Texture(Gdx.files.internal("sprites/powerups/livePowerUp.png"));
    }


    public void draw() {
        GameEngine.batch.draw(Sprites.powerUp_charge_increase.getTexture(), x, y, getWidth(), getHeight());
    }

	@Override
	public void decreaseLives(int lives) {
		// Ehm... no implementado? xD
	}

	@Override
	public void move() {
		this.y += vSpeed * Gdx.graphics.getDeltaTime();
		
	}
	
	@Override
	public void destroy() {
		//this.remove = true;
		kill();
		
		if (gotIt)
			Sounds.chargeSound.play();
		
	}
}
