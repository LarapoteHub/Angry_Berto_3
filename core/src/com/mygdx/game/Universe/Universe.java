package com.mygdx.game.Universe;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;

public class Universe {
	protected float enemyHPBuff = 1;
	protected float playerHPBuff = 1;
	
	protected float playerAttackSpeed = 1;
	protected float enemyAttackSpeed = 5;
	
	protected boolean dogDisabled = false;
	protected boolean hoeDisabled = false;
	protected boolean hpDisabled = false;
	
	protected int playerAttackMode = 1;
	

	private int uniMode = 1;

	// Base values Not used yet, for some reason...
	private float playerAttackSpeedBase = 10;
	private float enemyAttackSpeedBase = 10;

	public void setMode(int uniMode) {
		this.uniMode = uniMode;
		switch (uniMode){
		case 1:
			// Normaliño
			enemyHPBuff = 1;
			enemyAttackSpeed = 10;

			playerHPBuff = 1;
			playerAttackSpeed = 10;

			dogDisabled = false;
			hoeDisabled = false;
			hpDisabled = false;
			break;
		case 2:
			// TODO Crear balas oscuras para el fondo del universo blanco.
			dogDisabled = true;
			enemyHPBuff = 2;
			playerAttackSpeed = 20;
			break;
		}
	}
	
	public float getEnemyAttackSpeed(GameEngine.EnemyType type) {
		switch (type) {
			case STANDARD_ENEMY:
				return enemyAttackSpeed;
			case EVADING_ENEMY:
				return enemyAttackSpeed;
			case HEAVY_ENEMY:
				return enemyAttackSpeed / 10;
		}

		// Just in case TODO Edit this later to include all possibilities
		return 1;
	}
	
	/// #Getters
	public float getEnemyHPBuff() {
		return enemyHPBuff;
	}

	
	public float getPlayerAttackSpeed() {
		return playerAttackSpeed;
	}

	public Texture getBackground() {
		switch (uniMode) {
			case 1:
				return Backgrounds.universe_1;
			case 2:
				return Backgrounds.universe_2;
		}

		return null;
	}


	// To be implemented in the future.
	public int getPlayerAttackMode() {
		return playerAttackMode;
	}

	public float getPlayerHPBuff() {
		return playerHPBuff;
	}
	
	public boolean isDisableDog() {
		return dogDisabled;
	}
	public boolean isDisableHoe() {
		return hoeDisabled;
	}
	public boolean isDisableHP() {
		return hpDisabled;
	}
}
