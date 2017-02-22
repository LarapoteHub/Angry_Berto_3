package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
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
	/*
	 * boolean ascending; boolean descending;
	 */

	//solo tiene una animacion
	private TextureRegion[] movingFrames;

	public StandardEnemy(float x, float y, int behavior) {
		super(x, y, behavior);
		vSpeed = -300;
		width = 48;
		height = 48;
		lives = 2;
		
		damage = 2;
		
		// Valor que sumar para la puntuacion
		score = 100;

		// ascending = true;
		// descending = false;

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
			stateTime += Gdx.graphics.getDeltaTime() * 6;
		}

	}

	@Override
	public boolean canShoot() {
		return canShoot;
	}

	@Override
	public void runBehavior() {
		switch (behavior) {

		/*
		 * en funcion de si esta a la izquierda o a la derecha, le damos
		 * movimiento hacia un lado o hacia otro.
		 */
		case 0:

			if (this.getY() < 550 && this.getHSpeed() == 0) {
				if (this.getX() > 240) {
					this.setHSpeed(-100);
				} else {
					this.setHSpeed(100);
				}

			} // end if
			break;

		// igual que el anterior pero invirtiendo el lado
		case 1:

			if (this.getY() < 550 && this.getHSpeed() == 0) {
				if (this.getX() > 240) {
					this.setHSpeed(100);
				} else {
					this.setHSpeed(-100);
				}

			} // end if
			break;

		// si el enemigo alcanza la y550, se moverá hacia la derecha
		case 2:

			if (this.getY() < 550 && this.getHSpeed() == 0) {

				this.setHSpeed(100);

			} // end if
			break;

		// si el enemigo alcanza la y550, se moverá hacia la derecha
		case 3:

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

		Sprites.enemy_std.setBounds(0, 0, Sprites.enemy_std.getTexture().getWidth(), Sprites.enemy_std.getTexture().getHeight());

		movingFrames = new TextureRegion[FRAME_COLS];

		movingFrames = Sprites.enemy_std.split(Sprites.enemy_std.getTexture(), (int) Sprites.enemy_std.getWidth() / FRAME_COLS, (int) Sprites.enemy_std.getHeight() / FRAME_ROWS)[0];

		currentAnimation = new Animation(0.4f, movingFrames);
		//currentAnimation.setPlayMode(Animation.PlayMode.LOOP);

	}

}
