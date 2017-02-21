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
		
		// Implementado en otro lado. Usar cooldown
		// timerShoot = 50;
		//cooldown = 100;
		
		damage = 2;
		
		// Valor que sumar para la puntuacion
		score = 100;

		// ascending = true;
		// descending = false;

		type = EnemyType.STANDARD_ENEMY;
		powerUpProb = 10;

		setAttackSpeed(GameEngine.uni.getEnemyAttackSpeed(EnemyType.STANDARD_ENEMY));

		this.lives = (int) Math.ceil(GameEngine.uni.getEnemyHPBuff() * lives);

		this.FRAME_COLS = 4;
		this.FRAME_ROWS = 1;

		initAnimation();

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

	//region CHANGE THIS
	//
	// // TODO Change this
	// protected void playAnimation() {
	//
	// animation = new Timer.Task(){
	// @Override
	// public void run() {
	//
	// if (ascending) {
	//
	// if (index < 3) {
	// index++;
	//
	// } else {
	// ascending = false;
	// descending = true;
	// }
	//
	// }
	//
	// if (descending) {
	//
	// if (index > 0) {
	//
	// index--;
	//
	// } else {
	//
	// ascending = true;
	// descending = false;
	// }
	//
	// }
	//
	// }
	//
	// };
	//
	// //cada 0.1 segundos incrementamos el índice de la imagen.
	// Timer.schedule(animation,0.05f, 0.05f); //antes 0.2f
	//
	// } //end playAnimation()
	//
	// public void action(Player player) {
	//
	// // le restamos a su Y las unidades por segundo de su vspeed aplicando la
	// misma fórmula que a la nave. Lo mismo para su X y su hspeed;
	// y -= vspeed * Gdx.graphics.getDeltaTime();
	// x += hspeed * Gdx.graphics.getDeltaTime();
	// //si el enemigo se sale de los márgenes, invertimos su velocidad.
	// if (x <= 68 || x >= 480 - getWidth() ) {
	// this.setHSpeed(-hspeed);
	// }
	//
	// //usamos timerShoot para que entre disparo y disparo ocurran al menos 50
	// iteraciones
	// if (timerShoot<50) {
	// timerShoot++;
	// }
	//
	// // 5% de probabilidad de disparar?
	// if (MathUtils.random(0, 1000) > 995 && !GameEngine.gameState.isPaused()
	// && timerShoot == 50) {
	// Sounds.enemyShootSound.play();
	// engine.addEntity(new StandardEnemyShoot(this), EntityType.BULLET_ENEMY);
	// timerShoot = 0;
	// }
	//
	// //COMPORTAMIENTO
	// runBehavior();
	//
	// // en caso de que el enemigo se salga de los márgenes de la pantalla por
	// debajo, la eliminamos.
	// if (y + getHeight() < 0) {
	// remove = true;
	// }
	//
	// //si una gota colisiona con la nave...
	// /*if (overlaps(player)) {
	// // reproducimos el sonido correspondiente...
	// Sounds.explodeSound.play();
	// // y la eliminamos de nuestro mundo.
	// kill();
	// player.decreaseLives(1); //si este enemigo choca con el jugador le
	// bajaremos 1 vida, la spikeBall le bajara 2
	//
	// }*/
	//
	// if (getLives() <= 0) {
	//
	// Sounds.explodeSound.play();
	// player.addScore(10);
	//
	// random = MathUtils.random(0,20);
	//
	// if (random == 5 || random == 8 || random == 2) {
	// //spawnPowerUpLive(enemyContainer.getX(), enemyContainer.getY());
	// charge = true;
	// }
	//
	// remove = true;
	//
	// //spawnExplosion(enemyContainer.getX() + 6, enemyContainer.getY() + 6);
	// //iterEnemys.remove();
	// }
	//
	// } //end ACTION()
	//
	// @Override
	// public void runBehavior() {
	//
	// switch(behavior) {
	//
	// /*
	// en funcion de si esta a la izquierda o a la derecha,
	// le damos movimiento hacia un lado o hacia otro.
	// */
	// case 0:
	//
	// if (this.getY() < 550 && this.getHSpeed() == 0) {
	// if (this.getX() > 240) {
	// this.setHSpeed(-100);
	// } else {
	// this.setHSpeed(100);
	// }
	//
	// } //end if
	// break;
	//
	// //igual que el anterior pero invirtiendo el lado
	// case 1:
	//
	//
	// if (this.getY() < 550 && this.getHSpeed() == 0) {
	// if (this.getX() > 240) {
	// this.setHSpeed(100);
	// } else {
	// this.setHSpeed(-100);
	// }
	//
	// } //end if
	// break;
	//
	// //si el enemigo alcanza la y550, se moverá hacia la derecha
	// case 2:
	//
	//
	// if (this.getY() < 550 && this.getHSpeed() == 0) {
	//
	// this.setHSpeed(100);
	//
	// } //end if
	// break;
	//
	// //si el enemigo alcanza la y550, se moverá hacia la derecha
	// case 3:
	//
	//
	// if (this.getY() < 550 && this.getHSpeed() == 0) {
	//
	// this.setHSpeed(-100);
	//
	// } //end if
	// break;
	//
	// }
	//
	// }

	//endregion

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
			
			/*if (this.getX() > 240) {
				this.setHSpeed(-100);
			} else if(this.getX() < 230) {
				this.setHSpeed(100);
			}*/
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
		// 30% probabilidad de que dispare... nope
		int a = MathUtils.random(0, 100);
		if (a > 70) {
			GameEngine.addEntity(new StandardEnemyShoot(this), EntityType.BULLET_ENEMY);
			canShoot = false;
		} else {
			CDCount -= 3;
		}
		
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
