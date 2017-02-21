package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Red Mercy on 10/14/2016.
 */
public abstract class Ship extends Entity {
	protected boolean canShoot = false;
	// Esta cosa va a funcionar en funcion de la velocidad de ataque por segundo.
	// Este es el enfriamiento entre disparos.
	protected float cooldown = 1;
	protected float CDCount = 0;
	protected float lives = 2;
	protected float attackSpeed = 1;

	//boolean para probar mecanica de hits (cuando un enemigo sea golpeado que se vuelva rojo)
	protected boolean hit = false;
	//int para controlar el tiempo que se va a ver e nrojo el enemigo tras recibir daño.
	protected int hitClock = 0;
	//variable para almacenar y restaurar el color actual de SpriteBatch.
	protected Color tmpColor;
	//variable para almacenar el numero de ciclos del render en los cuales el enemigo se verá de color rojo.
	protected int HITTED_TIME = 4;

	public abstract boolean canShoot();
    
    public void setCanShoot(boolean newCanShoot) {
    	this.canShoot = newCanShoot;
    }
    
    public void incrementCooldown() {
    	CDCount += Gdx.graphics.getDeltaTime();
    	if (CDCount >= cooldown) {
    		CDCount = 0;
    		canShoot = true;
    	}
    }
    
    public abstract void shoot();

	public void setAttackSpeed(float attackSpeed) {
		// Asignar el attackSpeed
		this.attackSpeed = attackSpeed;
		// Calcular cuanto esperar entre disparos.
		cooldown = 1.0f / attackSpeed;
	}

	public void decreaseLives(float damage) {
		this.lives -= lives;
	}
}
