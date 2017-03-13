package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Buttons.Button;
import com.mygdx.game.Engine.GameStateManager;
import com.mygdx.game.Engine.LevelManager;
import com.mygdx.game.Engine.Loader;
import com.mygdx.game.Engine.Logic;
import com.mygdx.game.Engine.Renderer;
import com.mygdx.game.Entities.Enemies.BarbedWireEnemy;
import com.mygdx.game.Entities.Enemies.Bossses.Boss;
import com.mygdx.game.Entities.Enemies.Bossses.Boss1;
import com.mygdx.game.Entities.Enemies.ShieldEnemy;
import com.mygdx.game.Entities.Enemies.CoreOrbitEnemy;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Enemies.EvadingEnemy;
import com.mygdx.game.Entities.Enemies.HeavyEnemy;
import com.mygdx.game.Entities.Enemies.SpikeBallEnemy;
import com.mygdx.game.Entities.Enemies.StandardEnemy;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.PlainAnimations.Explosion;
import com.mygdx.game.Entities.PlainAnimations.PlainAnimation;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Entities.PowerUps.Charge;
import com.mygdx.game.Entities.Star;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.Projectile;
import com.mygdx.game.Universe.Universe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 100VOL on 16/08/2016.
 */
public class GameEngine {

	/*
		 * Esta clase contendrá todos los elementos comunes e importantes para el
         * motor como el jugador, enemigos, mecanicas de dibujado, etc
         */
	// <Mio!!>
	public static OrthographicCamera cam;
	public static SpriteBatch batch;
	public static ShapeRenderer sRenderer;
	public static GameStateManager gameState;
	public static BitmapFont printer;
	public static LevelManager levelManager;


	// Partes del motor
	public Logic logic;
	public Renderer renderer;
	// Clase usada solo para la carga.
	protected static Loader loader;

	protected static Vector3 touchPos = new Vector3();

	// Todo lo que se necesita para renderizar / calcular...
	protected static ArrayList<Enemy> enemies;
	protected static ArrayList<Boss> bosses;
	protected static ArrayList<Projectile> bullets_Player;
	protected static ArrayList<Projectile> bullets_Enemy;
	protected static ArrayList<PlainAnimation> plainAnimations;
	protected static Player player;
	protected static ArrayList<Entity> otherEntities;
	protected static ArrayList<Sprite> images;
	protected static ArrayList<Text> texts;
	// Contador de estrellas, para que no se llene la pantalla de ellas.
	// TODO Poner la generacion de estrellas SEPARADA del GameEngine, en una clase
	protected static int starCount = 0;
	protected static int starLimit = 50;

	protected static HashMap<String, Button> buttons;
	
	public static Universe uni;
	private boolean multimediaInitialized = false;
	private boolean componentsInitialized = false;
	private static  long lastScore = 0;
	// </Mio!!>

	private Color powerUpChargeBgColor;

	public GameEngine() {
		// Apparently not used?
	}

	public void create() {
		gameState = new GameStateManager();
		levelManager = new LevelManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

		enemies = new ArrayList<Enemy>();
		bosses = new ArrayList<Boss>();
		bullets_Player = new ArrayList<Projectile>();
		bullets_Enemy = new ArrayList<Projectile>();
		plainAnimations = new ArrayList<PlainAnimation>();

		buttons = new HashMap<String, Button>();

		otherEntities = new ArrayList<Entity>();

		images = new ArrayList<Sprite>();
		texts = new ArrayList<Text>();


		// Pasarle el motor, que se va usar en todas partes del Entity.
		Entity.engine = this;

		batch = new SpriteBatch();
		sRenderer = new ShapeRenderer();

		printer = new BitmapFont(Gdx.files.internal("fonts/anime_ace2.fnt"));

		uni = new Universe();

		loader = new Loader();
		loadMultimedia();

		logic = new Logic();
		renderer = new Renderer();

	}

	private void loadMultimedia() {
		if (!multimediaInitialized) {
			loader.loadSprites();
			loader.loadSounds();
			loader.loadMusic();
			loader.loadBackgrounds();
		}
	}

	protected void initComponents() {
		if (!componentsInitialized) {
			initMultimedia();

			MyGdxGame.musicManager.setMusic(Musics.backgroundMenuMusic);
		}
	}

	protected void initMultimedia() {

		if (!multimediaInitialized) {

			loader.initSprites();
			loader.initSounds();
			loader.initMusic();
			loader.initBackgrounds();

			multimediaInitialized = true;
		}
	}

	public static Player getPlayer() {
		return player;
	}

	public static void addText(Text t) {
		texts.add(t);
	}

	// Usado para algunas cosas que SOLO son imagenes, como Berto y Lina
	public static void addImage(Sprite spr) {
		images.add(spr);
	}

	public static void addEntity(Entity entity, EntityType type) {
		switch (type) {
		case BULLET_PLAYER:
			bullets_Player.add((Projectile) entity);
			break;
		case BULLET_ENEMY:
			bullets_Enemy.add((Projectile) entity);
			break;
		case ENEMY:
			enemies.add((Enemy) entity);
			break;
			case PLAIN_ANIMATION:
			plainAnimations.add((PlainAnimation) entity);
		case OTHER:
			otherEntities.add(entity);
		}
	}

	// Especial para añadir botones:
	public static void addButton(Button btn) {
		buttons.put(btn.getName(), btn);
	}

	public void dispose() {
		batch.dispose();
		printer.dispose();
	}

	public static long getLastScore() {
		return lastScore;
	}

	public enum EntityType {
		BULLET_PLAYER, BULLET_ENEMY, ENEMY, PLAIN_ANIMATION, OTHER
	}
	
	public static void changeUniverse(int uniID) {
		uni.setMode(uniID);
		player.setAttackSpeed(uni.getPlayerAttackSpeed());
	}


	public void drawPlayerLives() {

		firstY = 742; // antes 726

		for (int i = 0; i < player.getLives(); i++) {

			// ANTES 20* I
			// batch.draw(Sprites.liveImage, 10 + (36 * i), 726, 32, 32);

			batch.draw(Sprites.getSpriteByName("life")[0], 10, firstY, 48, 48);

			firstY -= 48;

		}

	}


	protected void clearAll() {
		if (player != null)
			lastScore = player.getScore();
		player = null;
		enemies.clear();
		bosses.clear();
		bullets_Player.clear();
		bullets_Enemy.clear();
		otherEntities.clear();
		plainAnimations.clear();
		images.clear();
		buttons.clear();
		texts.clear();
		starCount = 0;
		// Reset del universo.
		uni.setMode(1);
	}

	public static void createPlayer() {
		player = new Player();
	}

	// Cosas del SHOW_FPS
	int count = 1;
	double time = 0;
	double subTotal = 0;

	// **********************

	public void tick() {
		logic.tick();
		renderer.draw();

		// Contador de FPS
		if (MyGdxGame.SHOW_FPS) {
			// Improved FPS counter. Draw FPS count every 30 seconds.
			// For Improved accuracy
			subTotal += 1 / Gdx.graphics.getDeltaTime();
			if (count++ % 30 == 0) {
				time = subTotal / count;
				subTotal = 0;
				count = 1;
			}

			batch.begin();

			Color oldColor = printer.getColor();
			printer.setColor(Color.WHITE);

			printer.getData().setScale(0.75f, 0.75f);
													//x78
			printer.draw(batch, String.format("%.2f fps",time), 78, 770);

			printer.setColor(oldColor);

			printer.getData().setScale(1, 1);

			batch.end();

			count++;
		}

	}

	public static ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public static ArrayList<Boss> getBosses() {
		return bosses;
	}


	public void drawPlayerCharge() {

		// TODO Rehacer esto...
		// TODO ESTO ESTA MAAAL (A medio hacer)
		if (MyGdxGame.DEBUG_MODE)
			System.out.println("Need to finish the charge_bar!");

																		//380 - 20 (diferencia )
		batch.draw(Sprites.getSpriteByName("powerUp_charge_bar_container")[0].getTexture(), 3, 360);
		batch.draw(Sprites.getSpriteByName("powerUp_charge_bar")[0].getTexture(), 5, 380);
		batch.end();

		if (powerUpChargeBgColor == null) {
			processPowerUpChargeBgColor();
		}

		sRenderer.begin(ShapeRenderer.ShapeType.Filled);

		sRenderer.setColor(powerUpChargeBgColor);
		//Lógica chunga para esto....
		sRenderer.rect(5, 380 + 100 - (100 - player.getCharge()), Sprites.getSpriteByName("powerUp_charge_bar")[0].getTexture().getWidth(), 100 - player.getCharge());

		sRenderer.end();

		batch.begin();

	}

	private void processPowerUpChargeBgColor() {

		if (!Backgrounds.backgroundMenuImage.getTextureData().isPrepared()) {
			Backgrounds.backgroundMenuImage.getTextureData().prepare();
		}

		Pixmap pMap = Backgrounds.backgroundMenuImage.getTextureData().consumePixmap();

		powerUpChargeBgColor = new Color(pMap.getPixel(6, 350));

	}

	/***************************************************************************************************************************************************
	 * MÉTODOS DE SPAWN
	 **************************************************************************************************************************************************/

	public enum EnemyType {
		STANDARD_ENEMY, EVADING_ENEMY, SPIKE_BALL, HEAVY_ENEMY, SATELLITE_ORBIT_ENEMY, CORE_ORBIT_ENEMY, SHIELD_ENEMY, BOSS, BARBEDWIRE;
	}

	public enum BossType {
		TYPE_1
	}

	// TODO Totally make this more abstract. Just proof of concept atm.
	public static void spawnBoss(float x, float y, BossType type, Object behavior) {
		switch (type) {
			case TYPE_1:
				Boss b = new Boss1(x, y, (Enemy.Behavior.Bosses.Boss1) behavior);
				bosses.add(b);
				break;
		}
	}

	public static void spawnEnemy(float x, float y, EnemyType type, Object behavior) {
		Enemy enemy = null;

		switch (type) {

			// standardEnemy
			// --------------------------------------------------------------------------
			case STANDARD_ENEMY:
				enemy = new StandardEnemy(x, y, (Enemy.Behavior.StandardEnemy)behavior);
				break;

			// evadingEnemy
			// --------------------------------------------------------------------------
			case EVADING_ENEMY:
				enemy = new EvadingEnemy(x, y, (Enemy.Behavior.EvadingEnemy)behavior);
				break;
			// spikeBallEnemy
			// --------------------------------------------------------------------------
			case SPIKE_BALL:
				enemy = new SpikeBallEnemy(x, y, (Enemy.Behavior.SpikeBallEnemy)behavior);
				break;

			case HEAVY_ENEMY:
				enemy = new HeavyEnemy(x, y, (Enemy.Behavior.HeavyEnemy)behavior);
				break;

			case CORE_ORBIT_ENEMY:
				enemy = new CoreOrbitEnemy(x, y, (Enemy.Behavior.CoreOrbitEnemy)behavior);
				break;

			case SHIELD_ENEMY:
				enemy = new ShieldEnemy(x,y, (Enemy.Behavior.ShieldEnemy)behavior);
				break;
			case  BARBEDWIRE:
				enemy = new BarbedWireEnemy(x, y, (Enemy.Behavior.BarbedWireEnemy)behavior);
				break;

		} // end switch();

		enemies.add(enemy);
		// TODO Probar si esto va bien.
		//muy bonito
		//if (!enemy.getClass().equals(SpikeBallEnemy.class))
		//	levelManager.getCurrentLevel().increaseSpawned();
		//pero...
		if (type != EnemyType.SPIKE_BALL)
			levelManager.getCurrentLevel().increaseSpawned();


	} // end spawnEnemy()
		// ---------------------------------------------------------------------------


	public static void spawnPowerUpCharge(float x, float y) {
		addEntity(new Charge(x, y), EntityType.BULLET_ENEMY);
	}

	/**************************************************************************************************************************************************
	 * MÉTODOS DE ACCIÓN
	 **************************************************************************************************************************************************/


	// Y de la primera vida del jugador a dibujar.
	int firstY;

} // end GameEngine class