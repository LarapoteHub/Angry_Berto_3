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
	private boolean GODMODE = false;
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

	private boolean blinking = false;

	//TIEMPO TOTAL PARPADEANDO
	private final int BLINKING_TIME = 75;
	//TIEMPO POR PARPADEO
	private final int BLINK_INTERVAL_LIMIT = 8;

	private int blinkClock = 0;
	private int blinkIntervalClock = 0;

	private boolean victory = false;

	public Player() {
		canShoot = true;
		mode = 0;

		setWidth(48); // la hitbox la dsejamos con menos ancho por las alas
		setHeight(56);
		hspeed = 200;
		vspeed = 200;
		lives = 5.0f; // 5
		initialLives = lives;
		score = 0;

		x = 246;
		y = 20;

		this.FRAME_COLS = 2;
		this.FRAME_ROWS = 1;
		this.FRAME_COLS_PROPULSION = 3;
		this.FRAME_ROWS_PROPULSION = 1;

		//implementado por su cuenta
		this.HITTED_TIME = 10;

		//initAnimation();

		if (!init) {
			initAll();
		}

	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public void decreaseLives(float damage) {
		if (!GODMODE)
			this.lives -= damage;

		if (damage > 0) {
			Sounds.playerHitSound.play();
			hit = true;
			blinking = true;
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

			//si no esta parpadeando, se dibuja normalmente, pero si esta parpadeando solo se dibujara cuando
			// blinkIntervalClock estea por debajo de la mitad de BLINK_INTERVAL_LIMIT.
			if (!blinking || blinkIntervalClock < BLINK_INTERVAL_LIMIT / 2 && blinking) {

				GameEngine.batch.draw(currentFrame,
						x - 4, y, 56, getHeight());
				GameEngine.batch.draw(
						propulsionFrame,
						x - 4 + 14, y - 16, 8, 16);
				GameEngine.batch.draw(
						propulsionFrame,
						x - 4 + 34, y - 16, 8, 16);

			}



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

			//si esta parpadeando
			if (blinking) {

				//si el reloj del intervalo llega al final del intervalo...
				if (blinkIntervalClock >= BLINK_INTERVAL_LIMIT) {
					//se reinicia
					blinkIntervalClock = 0;
				}

				//solo se aumentan los relojes si el juego no está en pausa.
				if (!GameEngine.gameState.isPaused()) {
					blinkClock++;
					blinkIntervalClock++;
				}

				//cuando el reloj de parpadeo llega al limite, se reinicia y deja de parpadear.
				if (blinkClock >= BLINKING_TIME) {
					blinkIntervalClock = 0;
					blinkClock = 0;
					blinking = false;
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

		if (!victory) {

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

			//si el player ha ganado el nivel, lo movemos hacia delante sin limite...
		} else {

			// Mecanica para ir al centro de la pantalla y al y.MAX

			float step = 250 * Gdx.graphics.getDeltaTime();

			if (this.x - step > MyGdxGame.GAMEPLAY_SCR_CENTER)
				this.x -= step;
			else if (this.x + 250 * step < MyGdxGame.GAMEPLAY_SCR_CENTER)
				this.x += step;
			else {
				this.x = MyGdxGame.GAMEPLAY_SCR_CENTER;
			}

			this.y += step;
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

		// TODO Plantearse si esto es mejor hacerlo con el SprName
		//Sprites.getSpriteByName("player")[0].setBounds(0, 0, Sprites.getSpriteByName("player")[0].getTexture().getWidth(), Sprites.getSpriteByName("player")[0].getTexture().getHeight());

		movingFrames = new TextureRegion[FRAME_COLS];

		movingFrames = Sprites.getSpriteByName("player")[0].split(Sprites.getSpriteByName("player")[0].getTexture(), (int) Sprites.getSpriteByName("player")[0].getWidth() / FRAME_COLS, (int) Sprites.getSpriteByName("player")[0].getHeight() / FRAME_ROWS)[0];

		currentAnimation = new Animation(0.4f, movingFrames);
		// Probabliemente inutil, ya que el Width y Height se asignan al cargar...
		//Sprites.getSpriteByName("player_propulsion")[0].setBounds(0, 0, Sprites.getSpriteByName("player_propulsion")[0].getTexture().getWidth(), Sprites.player_propulsion.getTexture().getHeight());

		propulsionFrames = new TextureRegion[FRAME_COLS_PROPULSION];

		propulsionFrames = Sprites.getSpriteByName("player_propulsion")[0].split(Sprites.getSpriteByName("player_propulsion")[0].getTexture(), (int) Sprites.getSpriteByName("player_propulsion")[0].getWidth() / FRAME_COLS_PROPULSION, (int) Sprites.getSpriteByName("player_propulsion")[0].getHeight() / FRAME_ROWS_PROPULSION)[0];

		propulsionAnimation = new Animation(0.4f, propulsionFrames);

	}

	public boolean isHit() {
		//devuelvo blinkng para que sea inmune teniendo en cuenta el tiempo de parpadeo.
		return blinking;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public boolean getVictory() {
		return this.victory;
	}
}
