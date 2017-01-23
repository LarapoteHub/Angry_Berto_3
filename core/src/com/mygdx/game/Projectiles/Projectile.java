package com.mygdx.game.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;

/**
 * Created by 100VOL on 06/09/2016.
 */
public abstract class Projectile extends Entity {
	
    protected int damage;
    protected int charge = 0;
    protected boolean gotIt = false;

    public Projectile() {
    	collisionBox = new Rectangle(x, y, width, height);
    }
    
    public abstract void draw();

    public int getDamage() {
    	return damage;
    }
    
    public void destroy() {
    	this.remove = true;
    }
    
    public void move() {
    	this.x += hSpeed * Gdx.graphics.getDeltaTime();
    	this.y += vSpeed * Gdx.graphics.getDeltaTime();
    }

	public int getCharge() {
		gotIt = true;
		return charge;
	}
}
