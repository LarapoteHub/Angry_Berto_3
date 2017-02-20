package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Buttons.Button;
import com.mygdx.game.Engine.GameStateManager;
import com.mygdx.game.Engine.Loader;
import com.mygdx.game.Engine.Logic;
import com.mygdx.game.Engine.Renderer;
import com.mygdx.game.Entities.Enemies.Bossses.Boss;
import com.mygdx.game.Entities.Enemies.Bossses.Boss1;
import com.mygdx.game.Entities.Enemies.CoreOrbitEnemy;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Enemies.EvadingEnemy;
import com.mygdx.game.Entities.Enemies.HeavyEnemy;
import com.mygdx.game.Entities.Enemies.SatelliteOrbitEnemy;
import com.mygdx.game.Entities.Enemies.SpikeBallEnemy;
import com.mygdx.game.Entities.Enemies.StandardEnemy;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.PlainAnimations.Explosion;
import com.mygdx.game.Entities.PlainAnimations.PlainAnimation;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Entities.PowerUps.Charge;
import com.mygdx.game.Entities.Star;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.PlayerShoot;
import com.mygdx.game.Projectiles.Projectile;
import com.mygdx.game.Screens.Scr_Loading;
import com.mygdx.game.Screens.Scr_MainMenu;
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
	public static GameStateManager gameState;
	public static BitmapFont printer;

	private Thread lThread = null;

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

	protected static HashMap<String, Button> buttons;
	
	public static Universe uni;
	private boolean multimediaInitialized = false;
	private boolean componentsInitialized = false;
	// </Mio!!>

	public GameEngine() {
		// Apparently not used?
	}

	public void create() {
		// MIO
		gameState = new GameStateManager();
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

		player = new Player();
		player.setDraw(false);

		// Pasarle el motor, que se va usar en todas partes del Entity.
		Entity.engine = this;

		batch = new SpriteBatch();

		printer = new BitmapFont(Gdx.files.internal("fonts/anime_ace2.fnt"));



		// TODO TEST TEST TEST TEST //
		uni = new Universe();
		// TODO TEST TEST TEST TEST //
		// Not needed
		//gameState.loadGame();
		loader = new Loader();
		loadMultimedia();

		logic = new Logic();
		renderer = new Renderer();


		// Necesario para algunos botones...
		Button.engine = this;

		// NO MAS MIO -------------------

		// enemysList = new Array<Enemy>();
		// enemyShootList = new Array<Projectile>();
		// playerShootList = new Array<PlayerShoot>();
		explosionsList = new Array<Explosion>();
		starsList = new Array<Star>();

		// ponemos a cero el controlador de tiempo de spawn de las estrellas.
		lastStarSpawnTime = 0;

		entities = new Array<Entity>();
		


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

	public enum EntityType {
		BULLET_PLAYER, BULLET_ENEMY, ENEMY, PLAIN_ANIMATION, OTHER
	}
	
	public static void changeUniverse(int uniID) {
		uni.setMode(uniID);
		player.setAttackSpeed(uni.getPlayerAttackSpeed());
	}

	/***************************************************************************************************************************************************
	 * MÉTODOS DE DIBUJO - Otros??
	 **************************************************************************************************************************************************/

	/*
	 * public void drawPausePlayButton(SpriteBatch batch, boolean pause) {
	 * 
	 * if (pause) { batch.draw(Sprites.btn_pause[1], pausePlayButtonBox.x,
	 * pausePlayButtonBox.y - 2, 64, 64); } else {
	 * batch.draw(Sprites.btn_pause[0], pausePlayButtonBox.x,
	 * pausePlayButtonBox.y - 2, 64, 64); }
	 * 
	 * }
	 */

	public void drawPlayerLives() {

		firstY = 742; // antes 726

		for (int i = 0; i < player.getLives(); i++) {

			// ANTES 20* I
			// batch.draw(Sprites.liveImage, 10 + (36 * i), 726, 32, 32);

			batch.draw(Sprites.life, 10, firstY, 48, 48);

			firstY -= 48;

		}

	}

	/*
	public void drawPlayerScore() {


	}*/

	protected void clearAll() {
		player = new Player();
		player.setDraw(false);
		enemies.clear();
		bullets_Player.clear();
		bullets_Enemy.clear();
		otherEntities.clear();
		images.clear();
		buttons.clear();
		texts.clear();
		starCount = 0;
	}

	public static void createPlayer() {
		player = new Player();
	}

	// Cosas del SHOW_FPS
	long start = 0;
	int count = 0;
	double time = 0;
	double miniTotal = 0;

	// **********************

	public void tick() {
		// Contador de FPS
		// if (MyGdxGame.SHOW_FPS) {
		// start = System.nanoTime();
		// count++;
		// }

		// FAAAAIIILLL
		/*try {
			lThread.join();
			lThread = new Thread(new Logic());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}*/
		logic.tick();
		renderer.draw();
		//lThread.setDaemon(true);
		//lThread.start();

		// Contador de FPS
		if (MyGdxGame.SHOW_FPS) {
			miniTotal += (1000d / ((1.0d * System.nanoTime() - start) / 1000000));

			if (count % 60 == 0 && count > 1) {
				time = miniTotal / count;
				count = 0;
				miniTotal = 0;
			}
			/*
			 * Gdx.gl.glClearColor(0, 0, 0, 1);
			 * Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			 * 
			 * // Esto SI que hace falta cam.update(); // cam.update();
			 * 
			 * // Lo que Dani dice que es importante:
			 * batch.setProjectionMatrix(cam.combined);
			 */

			batch.begin();
			printer.draw(batch, time + "", MyGdxGame.WIDTH - 200, 50);
			batch.end();
			start = System.nanoTime();
			count++;
		}

		//region LIO MENTAL

		//
		// try {
		//
		//
		//
		// try {
		//
		//
		//
		// if (MyGdxGame.stateManager.isInEndGame()) {
		//
		// MyGdxGame.stateManager.mainMenu();
		//
		// }
		//
		// //como inicio del bucle render, limpiamos la pantalla con un color
		// azul negro y actualizamos la camara
		// //engine.updateCamera(batch, camera);
		//
		// if (gameState.isIntro()) {
		// // TODO JOER QUE LIO MENTAL....
		// try {
		// introInstace.run(batch);
		// } catch (Exception ex) {
		// System.out.println("ERROR en la intro: ");
		// ex.printStackTrace();
		// }
		//
		// } //fin si no estamos en el menu...
		//
		//
		// //------------------------------------------------------------------------------------------
		//
		//
		// //si el jugador se queda sin vidas, el juego se reinicia.
		// if (engine.player.getLives() <= 0) {
		//
		// gameOverInstance.gameOver(batch, camera, engine.player.getScore());
		// //create();
		// } else {
		//
		// engine.drawPlayerScore(batch, printer);
		//
		//

		//
		//
		// /*
		// //esta línea sirve para comprobar cuanto tiempo a pasado desde el
		// spawn del último enemigo, y, de ser necesario, llamará a otro nuevo
		// enemigo.
		// if (TimeUtils.nanoTime() - engine.lastEnemySpawnTime > 350000000)
		// //antes 500000000
		// engine.spawnEnemy();
		// */
		//
		// try {
		// if (TimeUtils.nanoTime() - engine.lastStarSpawnTime > 5000000)
		// //antes 50000000
		// engine.spawnStar();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		//
		// //este código hace que cada 10 iteraciones del render, el jugador
		// dispare.
		// if (clockShoot >= 10) {
		//
		// engine.spawnPlayerShoot();
		// clockShoot = 0;
		// }
		// //reloj que controla la frecuencia de disparo del jugador
		// clockShoot++;
		// //reloj que controla la frecuencia de animación de los sprites
		// clockSprites++;
		// if (clockSprites > 10) {
		// clockSprites = 0;
		// }
		//
		//
		// //A continuación le daremos el movimiento a los enemigos, para ello
		// recorreremos el array de rectángulos de los mismos restándoles
		// // 200 unidades por segundo (tal como hicimos con la nave)
		// //Creamos un Iterator (de toda la vida) para recorrer el Array de
		// enemigos.
		// //engine.enemysAction();
		//
		// //lo mismo que antes pero para los disparos enemigos.
		// //engine.enemyShootsAction();
		//
		// //lo mismo que antes pero para los disparos del jugador.
		// playerShootsAction();
		//
		// }
		//
		// //------------------------------------------------------------------------------------------------------------------------------------
		// //*** FIN DEL BATCH ***
		// //------------------------------------------------------------------------------------------------------------------------------------
		//
		// //Gdx.input.isTouched() nos devolverá true si ha sido tocada la
		// pantalla o si se ha hecho un click desde el ratón.
		//
		//
		// } //fin "si el juego no esta pausado..."
		//
		//
		//
		//
		// } catch (Exception ex) {
		// System.out.println("ERROR!!");
		// System.out.println("Excepción: " + ex.toString());
		// System.out.println("Causa: " + ex.getCause());
		// ex.printStackTrace();
		// System.out.println("-------");
		// System.exit(0);
		// }
		//
		//
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		//
		//

		//endregion
	}

	public static ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public static ArrayList<Boss> getBosses() {
		return bosses;
	}


	public void drawPlayerCharge() {

		/*
		 * printer.setColor(Color.RED); printer.draw(batch,
		 * player.getCharge()+"", 10, 380); //339 //800 - 80 printer.draw(batch,
		 * "%", 22, 350); printer.setColor(Color.WHITE);
		 */

		// TODO Rehacer esto...
		// TODO ESTO ESTA MAAAL (A medio hacer)
		if (MyGdxGame.DEBUG_MODE)
			System.out.println("Need to finish the charge_bar!");

		batch.draw(Sprites.powerUp_charge_bar.getTexture(), 2, 350);

		//region OLD CODE
		/*
		 * if (player.getCharge() >= 10) { batch.draw(Sprites.chargeBarImage[1],
		 * 4, 370); } if (player.getCharge() >= 30) {
		 * batch.draw(Sprites.chargeBarImage[2], 4, 370); } if
		 * (player.getCharge() >= 40) { batch.draw(Sprites.chargeBarImage[3], 4,
		 * 370); } if (player.getCharge() >= 50) {
		 * batch.draw(Sprites.chargeBarImage[4], 4, 370); } if
		 * (player.getCharge() >= 60) { batch.draw(Sprites.chargeBarImage[5], 4,
		 * 370); } if (player.getCharge() >= 70) {
		 * batch.draw(Sprites.chargeBarImage[6], 4, 370); } if
		 * (player.getCharge() >= 80) { batch.draw(Sprites.chargeBarImage[7], 4,
		 * 370); } if (player.getCharge() >= 90) {
		 * batch.draw(Sprites.chargeBarImage[8], 4, 370); } if
		 * (player.getCharge() >= 100) { batch.draw(Sprites.chargeBarImage[9],
		 * 4, 370); }
		 */
		//endregion

	}

	//region THATCH

	/*
	 * public void drawObjects(SpriteBatch batch, boolean pause,
	 * OrthographicCamera camera) {
	 * 
	 * iterStars = starsList.iterator(); while (iterStars.hasNext()) {
	 * 
	 * starContainer = iterStars.next();
	 * 
	 * batch.draw(Sprites.star, starContainer.getX(), starContainer.getY(), 2,
	 * 32);
	 * 
	 * if (!pause) { starContainer.setY(starContainer.getY() -
	 * (starContainer.getVspeed() * Gdx.graphics.getDeltaTime())); }
	 * 
	 * if (starContainer.getY() < -8) { iterStars.remove(); }
	 * 
	 * } //end while stars
	 * 
	 * for (Explosion exCont : explosionsList) {
	 * 
	 * exCont.draw(batch); exCont.action();
	 * 
	 * if (exCont.remove) { explosionsList.removeValue(exCont, true); }
	 * 
	 * 
	 * 
	 * } //end for explosions
	 * 
	 * //dibujamos los powerUps. iterPowerUps = entities.iterator(); while
	 * (iterPowerUps.hasNext()) {
	 * 
	 * if (powerUpContainer.remove) { iterPowerUps.remove(); }
	 * 
	 * } //end while
	 * 
	 * 
	 * //dibujamos al jugador player.draw(batch);
	 * 
	 * //recorremos la lista de enemigos iterEnemys = enemysList.iterator();
	 * while (iterEnemys.hasNext()) {
	 * 
	 * 
	 * enemyContainer = iterEnemys.next();
	 * 
	 * enemyContainer.draw(batch);
	 * 
	 * //si el juego esta pausado no ejecutamos la accion if (!pause) {
	 * enemyContainer.action(player); }
	 *//*
		 * if (MathUtils.random(0, 200) > 199 && !pause) {
		 * spawnEnemyShoot(enemyContainer); }
		 *//*
			 * 
			 * //power up vida if (enemyContainer.charge) {
			 * spawnPowerUpCharge(enemyContainer.getX(), enemyContainer.getY());
			 * }
			 * 
			 * //borrar if (enemyContainer.remove) {
			 * spawnExplosion(enemyContainer.getX() - 8, enemyContainer.getY() -
			 * 8); iterEnemys.remove();
			 * 
			 * }
			 * 
			 * }
			 * 
			 * iterShoots = enemyShootList.iterator(); while
			 * (iterShoots.hasNext()) { enemyShootContainer = iterShoots.next();
			 * 
			 * enemyShootContainer.draw(batch);
			 * 
			 * if (!pause) { enemyShootContainer.action(player); }
			 * 
			 * if (enemyShootContainer.remove) { iterShoots.remove(); }
			 * 
			 * }
			 *//*
				 * for (Rectangle enShoot : enemyShootList) {
				 * batch.draw(Sprites.enemyShootImage, enShoot.x, enShoot.y, 16,
				 * 16); } //end for enemyShoots
				 *//*
					 * 
					 * //DISPAROS DEL JUGADOR //lo mismo que antes pero para los
					 * disparos del jugador. iterPlayerShoots =
					 * playerShootList.iterator(); while
					 * (iterPlayerShoots.hasNext()) {
					 * 
					 * //almacenamos temporalmente en raindrop el rectángulo que
					 * nos ocupa. playerShootContainer =
					 * iterPlayerShoots.next();
					 * 
					 * playerShootContainer.draw(batch); if (!pause) {
					 * playerShootContainer.action(enemysList); }
					 * 
					 * if (playerShootContainer.remove) {
					 * iterPlayerShoots.remove(); }
					 * 
					 * 
					 * } //fin iteradorDisparosJugador()
					 * 
					 * 
					 * //ACCIÓN DEL JUGADOR. if (!pause) { player.action(camera,
					 * pausePlayButtonBox); }
					 * 
					 * batch.draw(Backgrounds.backgroundPowerUps, 0, 0, 68,
					 * 800);
					 * 
					 * hoeButton.draw(batch); liveButton.draw(batch);
					 * dogButton.draw(batch);
					 * 
					 * if (!pause) {
					 * 
					 * hoeButton.action(player, camera);
					 * liveButton.action(player, camera);
					 * dogButton.action(player, camera);
					 * 
					 * }
					 * 
					 * //TODO revisar el dibujo de la carga
					 * drawPlayerCharge(batch);
					 * 
					 * } //end drawObjects()
					 * ------------------------------------
					 * ----------------------
					 * ------------------------------------
					 * --------------------------------
					 */

	//endregion

	/***************************************************************************************************************************************************
	 * MÉTODOS DE SPAWN
	 **************************************************************************************************************************************************/

	public enum EnemyType {
		STANDARD_ENEMY, EVADING_ENEMY, SPIKE_BALL, HEAVY_ENEMY, SATELLITE_ORBIT_ENEMY, CORE_ORBIT_ENEMY, BOSS;
	}

	public enum BossType {
		TYPE_1
	}

	// TODO Totally make this more abstract. Just proof of concept atm.
	public static void spawnBoss(float x, float y, BossType type) {
		switch (type) {
			case TYPE_1:
				Boss b = new Boss1(x, y);
				bosses.add(b);
				break;
		}
	}

	public static void spawnEnemy(float x, float y, EnemyType type, int behavior) {
		// ------------------------------------------------------------------------
		// este método creará los gotas con un movimiento aleatorio e
		// independiente.
		// ------------------------------------------------------------------------

		// limite de enemigos en pantalla 100
		// if (enemies.size() <= 100) {

		switch (type) {

		// standardEnemy
		// --------------------------------------------------------------------------
			case STANDARD_ENEMY:
				enemy = new StandardEnemy(x, y, behavior);
				break;

		// evadingEnemy
		// --------------------------------------------------------------------------
			case EVADING_ENEMY:
				enemy = new EvadingEnemy(x, y, behavior);
				break;
		// spikeBallEnemy
		// --------------------------------------------------------------------------
			case SPIKE_BALL:
				enemy = new SpikeBallEnemy(x, y, behavior);
				break;

			case HEAVY_ENEMY:
				enemy = new HeavyEnemy(x, y, behavior);
				break;

			case CORE_ORBIT_ENEMY:
				enemy = new CoreOrbitEnemy(x, y);
				enemies.add(new SatelliteOrbitEnemy(enemy));
				break;

		} // end switch();

		enemies.add(enemy);

		// }

		// return null;
	} // end spawnEnemy()
		// ---------------------------------------------------------------------------

	//region MORE THATCH

	/*
	 * public void spawnEnemyShoot(Enemy enem, int type) {
	 * 
	 * switch (type) {
	 * 
	 * case 0: bullets_Enemy.add(new StandardEnemyShoot(enem)); break; case 1:
	 * bullets_Enemy.add(new HeavyEnemyShoot(enem)); break;
	 * 
	 * }
	 * 
	 * } // end spawnEnemyShoot()
	 */
	// ----------------------------------------------------------------------

	// método que spawnea un disparo del jugador.
	/*
	 * public void spawnPlayerShoot() {
	 * 
	 * bullets_Enemy.add(new PlayerShoot(player));
	 * 
	 * } // end spawnPlayerShoot()
	 */
	// ---------------------------------------------------------------------

	//endregion

	public void spawnExplosion(float x, float y) {

		//explosionsList.add(new Explosion(x, y));

	}

	public void spawnStar() {
		// ------------------------------------------------------------------------
		// este método creará los gotas con un movimiento aleatorio e
		// independiente.
		// ------------------------------------------------------------------------

		// evitamos que haya demasiados enemigos en pantalla por rendimiento
		if (starsList.size <= 50) {
			// instanciamos un nuevo rectángulo.
			// Le pasamos las vidas que va a tener el enemigo, para ahorrar una
			// llamada al metodo.
			starSpawnContainer = new Star(10, 10);
			// lo colocamos en una posición X aleatoria dentro de la pantalla
			// (entre 0 y 480-48(-48 para no salirse del borde)).
			starSpawnContainer.setX(MathUtils.random(68, 480 - 2)); // antes 0
																	// en el 68
			// y en la parte más alta de la pantalla.

			// le damos una velocidad vertical aleatorias a la estrella.
			starSpawnContainer.setVSpeed(MathUtils.random(1000, 1500));

			// añadimos el rectángulo al array de rectángulos de las gotas de
			// lluvia.
			starsList.add(starSpawnContainer);
			// guardamos el tiempo actual en nano segundos para posteriormente
			// decidir si pasado un tiempo X,
			// aparecerá otro enemigo o no.
			lastStarSpawnTime = TimeUtils.nanoTime();
		}
	} // end spawnStar()
		// ---------------------------------------------------------------------------

	public static void spawnPowerUpCharge(float x, float y) {
		addEntity(new Charge(x, y), EntityType.BULLET_ENEMY);

		// entities.add(new com.mygdx.game.Entities.PowerUps.Charge(x, y));

	}

	/*
	 * 
	 * public void spawnPowerUpLive(float x, float y) {
	 * 
	 * if (entities.size <= 10) {
	 * 
	 * entities.add(new Btn_PlusHP(x, y));
	 * 
	 * }
	 * 
	 * 
	 * } //end spawnPowerUpLive()
	 * 
	 * public void spawnPowerUpHoe(float x, float y) {
	 * 
	 * if (entities.size <= 10) {
	 * 
	 * entities.add(new Btn_Hoe(x, y, this));
	 * 
	 * }
	 * 
	 * 
	 * } //end spawnPowerUpLive()
	 * 
	 * public void spawnPowerUpDog(float x, float y) {
	 * 
	 * if (entities.size <= 10) {
	 * 
	 * entities.add(new Btn_Dog(x, y, this));
	 * 
	 * }
	 * 
	 * 
	 * } //end spawnPowerUpLive()
	 */

	/**************************************************************************************************************************************************
	 * MÉTODOS DE ACCIÓN
	 **************************************************************************************************************************************************/

	public void playerShootsAction() {

	} // end playerShootAction()
		// --------------------------------------------------------------------

	// static long lastEnemySpawnTime;
	// TODO REHACER ESTO - ELIMINAR LO INUTIL!
	static Enemy enemy;

	Array<Explosion> explosionsList;

	Array<Star> starsList;
	Iterator<Star> iterStars;
	Star starContainer;
	Star starSpawnContainer;

	public Array<Entity> entities;
	// Iterator<com.mygdx.game.Entities.PowerUps.PowerUp> iterPowerUps;
	// com.mygdx.game.Entities.PowerUps.PowerUp powerUpContainer;

	static long lastStarSpawnTime;

	boolean removePlayerShoot;

	// Y de la primera vida del jugador a dibujar.
	int firstY;

} // end GameEngine class