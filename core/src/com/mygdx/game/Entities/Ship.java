package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Red Mercy on 10/14/2016.
 */
public abstract class Ship extends Entity {
	protected boolean canShoot = false;
	protected int baseCooldown = 10;
	protected int cooldown = 10;
	protected int CDCount = 0;
	protected int lives = 2;

	//boolean para probar mecanica de hits (cuando un enemigo sea golpeado que se vuelva rojo)
	protected boolean hitted = false;
	//int para controlar el tiempo que se va a ver e nrojo el enemigo tras recibir daño.
	protected int hittedClock = 0;
	//variable para almacenar y restaurar el color actual de SpriteBatch.
	protected Color tmpColor;
	//variable para almacenar el numero de ciclos del render en los cuales el enemigo se verá de color rojo.
	protected int HITTED_TIME = 4;

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
