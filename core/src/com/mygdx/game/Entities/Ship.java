package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Red Mercy on 10/14/2016.
 */
public abstract class Ship extends Entity {
	protected boolean canShoot = false;
	protected int baseCooldown = 10;
	protected int cooldown = 10;
	protected int CDCount = 0;
	protected int lives = 2;
	
    public abstract boolean canShoot();
    
    public void setCanShoot(boolean newCanShoot) {
    	this.canShoot = newCanShoot;
    }
    
    public void incrementCooldown() {
    	CDCount++;
    	if (CDCount >= cooldown) {
    		CDCount = 0;
    		canShoot = true;
    	}
    }
    
    public abstract void shoot();
}
