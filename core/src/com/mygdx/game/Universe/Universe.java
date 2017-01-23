package com.mygdx.game.Universe;

public class Universe {
	protected int enemyHPBuff = 1;
	protected int playerHPBuff = 1;
	
	protected int playerAttackSpeed = 1;
	protected int enemyAttackSpeed = 1;
	
	protected boolean dogDisabled = false;
	protected boolean hoeDisabled = false;
	protected boolean hpDisabled = false;
	
	protected int playerAttackMode = 1;
	
	
	// Base values
	private int playerAttackSpeedBase = 10;
	private int enemyAttackSpeedBase = 10;
	
	public void setMode(int uniMode) {
		switch (uniMode){
		case 1:
			// Normaliño
			enemyHPBuff = playerHPBuff = playerAttackSpeed = playerAttackMode = enemyAttackSpeed = 1;
			dogDisabled = hoeDisabled = hpDisabled = false;
			break;
		case 2:
			dogDisabled = true;
			enemyHPBuff = 2;
			playerAttackSpeed = 5;
			break;
		}
	}
	
	public int getEnemyAttackSpeed() {
		return enemyAttackSpeedBase / enemyAttackSpeed;
	}
	
	/// #Getters
	public int getEnemyHPBuff() {
		return enemyHPBuff;
	}
	
	public int getPlayerAttackMode() {
		return playerAttackMode;
	}
	
	public int getPlayerAttackSpeed() {
		return playerAttackSpeedBase / playerAttackSpeed;
	}
	
	public int getPlayerHPBuff() {
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
