package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.PlainAnimations.AnimationAdapter;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.GameEngine.EntityType;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.StandardEnemyShoot;

/**
 * Created by 100VOL on 09/08/2016. Modded by RedMercy since then.
 */
public class StandardEnemy extends Enemy {
	
	/*
	 * DESCRIPCIÓN: enemigo estánder dispara y tiene una velocidad vertical de
	 * 200, su movimiento es controlado por schedules de las clases derivadas de
	 * Level.
	 */

	private Behavior.StandardEnemy behavior;

	public StandardEnemy(float x, float y, Behavior.StandardEnemy behavior) {
		super(x, y);
		vSpeed = -300;
		width = 48;
		height = 48;
		lives = 2;

		this.behavior = behavior;

		damage = 2;
		
		// Valor que sumar para la puntuacion
		scoreValue = 100;


		type = EnemyType.STANDARD_ENEMY;
		powerUpProb = 10;

		// Calculate the attack speed.
		initAttackSpeed();

		this.lives = (int) Math.ceil(GameEngine.uni.getEnemyHPBuff() * lives);

		this.FRAME_COLS = 4;
		this.FRAME_ROWS = 1;

		initAnimation();

	}

	private void initAttackSpeed() {
		float uniAtkSpeed = GameEngine.uni.getEnemyAttackSpeed(EnemyType.STANDARD_ENEMY);
		// Porcentaje de la variacion de la velocidad de ataque, para que no tengan todos exactamente
		// la misma velocidad de ataque.
		float variation = 0.1f;

		setAttackSpeed(MathUtils.random(uniAtkSpeed * (1 - variation), uniAtkSpeed * (1 + variation)));
	}

	public void draw() {

		if (hit) {
			tmpColor = GameEngine.batch.getColor();
			GameEngine.batch.setColor(Color.RED);
		}

		currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		GameEngine.batch.draw(currentFrame, x, y, width, height);

		if (hit) {
			GameEngine.batch.setColor(tmpColor);

			if (hitClock >= HITTED_TIME) {
				hitClock = 0;
				hit = false;
			}

			if (!GameEngine.gameState.isPaused()) {
				hitClock++;
			}

		}

		if(!GameEngine.gameState.isPaused()) {
			stateTime += Gdx.graphics.getDeltaTime() * animationSpeed;
		}

	}

	@Override
	public boolean canShoot() {
		return canShoot;
	}

	@Override
	public void runBehavior() {
		switch (behavior) {

			// si el enemigo alcanza la y550, se moverá hacia la derecha
			case ON_Y550_TURN_RIGHT:

				if (this.getY() < 550 && this.getHSpeed() == 0) {

					this.setHSpeed(100);

				} // end if
				break;

			// si el enemigo alcanza la y550, se moverá hacia la izquierda
			case ON_Y550_TURN_LEFT:

				if (this.getY() < 550 && this.getHSpeed() == 0) {

					this.setHSpeed(-100);

				} // end if
				break;

		}
	}

	@Override
	public void shoot() {
		// 50% probabilidad de que dispare
		int a = MathUtils.random(0, 100);
		if (a < 10) {
			GameEngine.addEntity(new StandardEnemyShoot(this), EntityType.BULLET_ENEMY);
		} else {
			// Retraso en caso de que no dispare.
			CDCount -= MathUtils.random(0.15f, 0.45f);
		}
		canShoot = false;
	}

	@Override
	public void initAnimation() {

		this.animationSpeed = 6;

		Sprite spr = Sprites.getSpriteByName("enemy_std")[0];
		currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);


	}

}
