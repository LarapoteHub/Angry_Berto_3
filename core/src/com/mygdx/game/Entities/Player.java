package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Projectiles.PlayerShoot;

/**
 * Created by 100VOL on 10/08/2016.
 */
public class Player extends Ship {

	//TODO Cheats!
	private boolean GODMODE = true;
	private float initialLives;
	// --------

	// Dibujar o no
	private boolean draw = false;
	private float vspeed;
	private float hspeed;

	// Lo relacionado con los modos del jugador (Tipos de bala, nave, etc)
	private int numModes = 2;
	private int mode;

	private long score;

	// alamcena la carga para usar powerUps
	public int charge = 100;

	private TextureRegion currentFrame, propulsionFrame;
	private  TextureRegion[] movingFrames, propulsionFrames;

	private Animation currentAnimation, propulsionAnimation;

	private int FRAME_COLS, FRAME_ROWS;
	private int FRAME_COLS_PROPULSION, FRAME_ROWS_PROPULSION;

	private float stateTime = 0f;
	private float propulsionStateTime = 0f;

	boolean init = false;
	private int countBigOne = 0;
	private int bigOneInterval = 10;

	public Player() {
		canShoot = true;
		mode = 0;

		setWidth(48); // la hitbox la dsejamos con menos ancho por las alas
		setHeight(56);
		hspeed = 200;
		vspeed = 200;
		lives = 5; // 5
		initialLives = lives;
		score = 0;

		x = 246;
		y = 20;

		this.FRAME_COLS = 2;
		this.FRAME_ROWS = 1;
		this.FRAME_COLS_PROPULSION = 3;
		this.FRAME_ROWS_PROPULSION = 1;

		//initAnimation();

		if (!init) {
			initAll();
		}

	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public void decreaseLives(int lives) {
		if (!GODMODE && !(lives > initialLives))
			this.lives -= lives;

		if (lives > 0) {
			Sounds.playerHitSound.play();
			hit = true;
		}
	}

	public void incrementLives(int lives) {
		this.lives += lives;
	}

	public float getHSpeed() {

		return this.hspeed;

	}

	public float getLives() {

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
		if (!GODMODE) {
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

		//descomentar para ver
		//System.out.println("Color que trae el SpriteBatch: "+GameEngine.batch.getColor().toString());

		if (draw) {
			if (hit) {
				tmpColor = GameEngine.batch.getColor();
				GameEngine.batch.setColor(Color.RED);
			}


			currentFrame = currentAnimation.getKeyFrame(stateTime, true);
			propulsionFrame = propulsionAnimation.getKeyFrame(propulsionStateTime, true);

			GameEngine.batch.draw(currentFrame,
					x - 4, y, 56, getHeight());
			GameEngine.batch.draw(
					propulsionFrame,
					x - 4 + 14, y - 16, 8, 16);
			GameEngine.batch.draw(
					propulsionFrame,
					x - 4 + 34, y - 16, 8, 16);

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

			if (!GameEngine.gameState.isPaused()) {

				stateTime += Gdx.graphics.getDeltaTime() * 3;
				propulsionStateTime += Gdx.graphics.getDeltaTime() * 6;

			}

		}

	}

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
		/*x += hSpeed * Gdx.graphics.getDeltaTime();
		y += vSpeed * Gdx.graphics.getDeltaTime();
		*/

		// TODO entender esto
		// Mecanica para que no salte cuando haya un tap fuera de
		// ciertos limites
		this.x -= hspeed * Gdx.graphics.getDeltaTime() * 10;
		this.y -= vspeed * Gdx.graphics.getDeltaTime() * 10;
		hspeed = Math.round(hspeed / 2);
		vspeed = Math.round(vspeed / 2);

		if (x <= Backgrounds.backgroundPowerUps.getWidth())
			x = Backgrounds.backgroundPowerUps.getWidth(); // antes 0 donde el 68
		if (x > 480 - width)
			x = 480 - width;
		if (y > 300)
			y = 300;
		if (y < 20)
			y = 20;
		if (x <= Backgrounds.backgroundPowerUps.getWidth() && hSpeed > 0) {
			hSpeed = 0;
		}
		if (x + width >= MyGdxGame.WIDTH && hSpeed < 0) {
			hSpeed = 0;
		}
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

	/**
	 * Puesto aqui la parte del disparo para poder añadir mecanicas extra, como la del ejemplo.
	 */
	@Override
	public void shoot() {
		countBigOne++;
		PlayerShoot bullet = new PlayerShoot(this);
		if (countBigOne >= bigOneInterval) {
			countBigOne = 0;
			bullet.setWidth(100);
			bullet.setDamage(2.5f);
		}
		bullet.centerTo("x", x, width);
		GameEngine.addEntity(bullet, GameEngine.EntityType.BULLET_PLAYER);

		setCanShoot(false);
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getNumModes() {
		return numModes;
	}

	public int getMode() {
		return mode;
	}

	private void initAll() {
		initAnimation();
		setAttackSpeed(GameEngine.uni.getPlayerAttackSpeed());
		init = true;
	}

	private void initAnimation() {


		Sprites.player.setBounds(0, 0, Sprites.player.getTexture().getWidth(), Sprites.player.getTexture().getHeight());

		movingFrames = new TextureRegion[FRAME_COLS];

		movingFrames = Sprites.player.split(Sprites.player.getTexture(), (int) Sprites.player.getWidth() / FRAME_COLS, (int) Sprites.player.getHeight() / FRAME_ROWS)[0];

		currentAnimation = new Animation(0.4f, movingFrames);

		Sprites.player_propulsion.setBounds(0, 0, Sprites.player_propulsion.getTexture().getWidth(), Sprites.player_propulsion.getTexture().getHeight());

		propulsionFrames = new TextureRegion[FRAME_COLS_PROPULSION];

		propulsionFrames = Sprites.player_propulsion.split(Sprites.player_propulsion.getTexture(), (int) Sprites.player_propulsion.getWidth() / FRAME_COLS_PROPULSION, (int) Sprites.player_propulsion.getHeight() / FRAME_ROWS_PROPULSION)[0];

		propulsionAnimation = new Animation(0.4f, propulsionFrames);

	}

	public boolean isHitted() {
		return hit;
	}
}
