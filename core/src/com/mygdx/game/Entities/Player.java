package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 10/08/2016.
 */
public class Player extends Ship {

	//TODO Cheats!
	private boolean GODMODE = true;
	private int initialLives;
	// --------

	// Dibujar o no
	private boolean draw = false;
	// Puede disparar?
	private float vspeed;
	private float hspeed;

	private long score;
	private int index;
	private int fireIndex;
	private Timer.Task animation;
	Vector3 touchPos;
	
	private int attackSpeed = 1;

	// alamcena la carga para usar powerUps
	public int charge = 100;

	public Player() {
		canShoot = true;

		/*
		 * playerImage = new Texture[2]; fireImage = new Texture[3];
		 * 
		 * playerImage[0] = new
		 * Texture(Gdx.files.internal("sprites/player/player.png"));
		 * playerImage[1] = new
		 * Texture(Gdx.files.internal("sprites/player/playerB.png"));
		 * //playerImage[1] = new
		 * Texture(Gdx.files.internal("sprites/player/playerB.png"));
		 * fireImage[0] = new
		 * Texture(Gdx.files.internal("sprites/player/playerFire/fire0.png"));
		 * fireImage[1] = new
		 * Texture(Gdx.files.internal("sprites/player/playerFire/fire1.png"));
		 * fireImage[2] = new
		 * Texture(Gdx.files.internal("sprites/player/playerFire/fire2.png"));
		 */

		setWidth(48); // la hitbox la dsejamos con menos ancho por las alas
		setHeight(56);
		hspeed = 200;
		vspeed = 200;
		lives = 5; // 5
		initialLives = lives;
		score = 0;

		x = 246;
		y = 20;

		index = 0;
		fireIndex = 0;

		playAnimation();

	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public void decreaseLives(int lives) {
		if (!GODMODE && !(lives > initialLives))
			this.lives -= lives;
		Sounds.playerHitSound.play();
	}

	public void incrementLives(int lives) {
		this.lives += lives;
	}

	public float getHSpeed() {

		return this.hspeed;

	}

	public int getLives() {

		return this.lives;

	}

	public long getScore() {

		return score;

	}

	public void addScore(long score) {

		this.score += score;

	}

	public void increaseCharge(int charge) {
		if (this.charge + charge > 100) {
			this.charge = 100;
		} else {
			this.charge += charge;
		}
	}

	public void decreaseCharge(int charge) {
		if (!MyGdxGame.DEBUG_MODE) {
			this.charge -= charge;
			if (this.charge < 0) {
				this.charge = 0;
			}
		}
	}

	public int getCharge() {
		return charge;

	}

	public void draw() {

		if (draw) {
			if (GameEngine.gameState.isPaused()) {
				GameEngine.batch.draw(Sprites.player[0].getTexture(), x - 4, y,
						56, getHeight());
			} else {
				// TODO entender esto
				// Mecanica para que no salte cuando haya un tap fuera de
				// ciertos limites
				this.x -= hspeed * Gdx.graphics.getDeltaTime() * 10;
				this.y -= vspeed * Gdx.graphics.getDeltaTime() * 10;
				hspeed = Math.round(hspeed / 2);
				vspeed = Math.round(vspeed / 2);

				// Algo de render??
				GameEngine.batch.draw(Sprites.player[index].getTexture(),
						x - 4, y, 56, getHeight());
				GameEngine.batch.draw(
						Sprites.player_propulsion[fireIndex].getTexture(),
						x - 4 + 14, y - 16, 8, 16);
				GameEngine.batch.draw(
						Sprites.player_propulsion[fireIndex].getTexture(),
						x - 4 + 34, y - 16, 8, 16);
			}
		}

	}

	// TODO Just replace it all together
	private void playAnimation() {

		animation = new Timer.Task() {
			@Override
			public void run() {

				if (fireIndex == 1) {
					index = 0;
					y += 1;
				} else if (fireIndex == 0) {
					index = 1;
					y -= 1;
				}

				if (fireIndex < 2) {
					fireIndex++;
				} else {
					fireIndex = 0;
				}

			}

		};

		// cada 0.1 segundos incrementamos el índice de la imagen.
		Timer.schedule(animation, 0.09f, 0.09f);

	} // end playAnimation()

	public float getVSpeed() {
		return vspeed;
	}

	public void setVSpeed(float newVSpeed) {
		this.vspeed = newVSpeed;
	}

	public void setHSpeed(float newHSpeed) {
		this.hspeed = newHSpeed;
	}

	@Override
	public boolean canShoot() {
		return canShoot;
	}

	@Override
	public void move() {
		if (x < 68)
			x = 68; // antes 0 donde el 68
		if (x > 480 - width)
			x = 480 - width;
		if (y > 300)
			y = 300;
		if (y < 20)
			y = 20;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
		if (cooldown != baseCooldown / attackSpeed)
			this.cooldown = baseCooldown / attackSpeed;
	}
	
	public boolean collides(Entity collisionBox) {
		return this.collisionBox.overlaps(collisionBox.collisionBox);

		/*
		 * if (this.x <= collisionBox.x && this.x + this.width >= collisionBox.x
		 * + collisionBox.width && this.y <= collisionBox.y && this.y +
		 * this.height >= collisionBox.y + collisionBox.height) { return true; }
		 * 
		 * return false;
		 */
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub

	}
}
