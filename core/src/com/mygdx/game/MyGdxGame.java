package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Engine.Loader;
import com.mygdx.game.Engine.MusicManager;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Levels.Level0;
import com.mygdx.game.Multimedia.Musics;

public class MyGdxGame extends ApplicationAdapter {
	// Dimensiones de la pantalla de renderizado DENTRO del juego...
	// TODO Esto debería ajustarse a la pantalla actual que tenemos en realidad,
	// con bordes negros
	// TODO o lo que haga falta.
	public static final int WIDTH = 480, HEIGHT = 800;

	//TODO modo DEBUG
	public static final boolean DEBUG_MODE = true;

	public static final boolean SHOW_FPS = true;

	private Loader loader;

	private boolean multimediaInitialized = false;

	private long frameStart;

	private int clockShoot;
	static int clockSprites;

	// variables que serviran para dejar un tiempo entre la pausa y el resumir,
	// para que no se hagan las dos cosas instantaneamente
	public static long lastPaused;
	public static long lastResumed;

	// variable para que al perder tocando la pantalla, no se cierre.
	public static boolean recently_touched;
	// variable para que la anterior solo se ponga una vez a true.

	com.mygdx.game.Screens.Scr_MainMenu mainMenuInstance;

	com.mygdx.game.Screens.Scr_Introduction introInstace;

	com.mygdx.game.Screens.Scr_Pause pauseInstance;

	GameEngine engine;

	com.mygdx.game.Screens.Scr_GameOver gameOverInstance;

	Array<Level> levelList;

	int levelIndex;

	private BitmapFont fuenteDelDany;
	private SpriteBatch batchDelDany;
	private OrthographicCamera camaraDelDany = new OrthographicCamera();

	private boolean componentsInitialized = false;


	// OTHER!!
	private boolean showFPS = false; // Theoretical FPS, actual FPS has VSync
										// (60 Hz)

	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------

	@Override
	public void create() {

		//TODO 100vol estuvo aqui
		loader = new Loader();
		loadMultimedia(); //no asincrono

		fuenteDelDany = new BitmapFont(Gdx.files.internal("fonts/anime_ace2.fnt"));
		batchDelDany = new SpriteBatch();
		camaraDelDany.setToOrtho(false, WIDTH, HEIGHT);

	} // fin del método create() <-----

	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// -------------------------------->>>>> RENDER
	// <<<<<----------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------

	@Override
	public void render() {

		if (!loader.update()) {
			camaraDelDany.update();
			batchDelDany.setProjectionMatrix(camaraDelDany.combined);
			batchDelDany.begin();
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			fuenteDelDany.draw(batchDelDany, "LOADING: "+(int)(loader.getProgress()*100) + "%", 240, 400, Align.center, Align.center, true);
			batchDelDany.end();
		} else {

			if (!componentsInitialized) {
				initMultimedia();
				initComponents();

				batchDelDany.dispose();
				fuenteDelDany.dispose();
			}

			frameStart = System.nanoTime();

			engine.tick(); // TODO Use some DeltaTime mechanics??

			if (showFPS)
				System.out.println(1000.0 / ((1.0 * System.nanoTime() - frameStart) / 1000000));

		}
	} // fin del método render() <-----

	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------

	// este método sobrescrito de la Interface dispose sirve para ayudar al
	// sistema a "limpiar" todos los objetos creados
	// en el momento que nos salgamos de la aplicación.
	@Override
	public void dispose() {

		// limpiamos el sonido de fondo.
		MusicManager.dispose();
		// limpiamos el batch al completo.
		// batch.dispose();

		//loader.dispose();
	}

	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// TODO Find a better system
	public void checkFirstTouched() {
		/*
		 * //si es la primera vez que ha sido tocado recientemente... if
		 * (firstRecently_touched) { //...ha sido recientemente tocado...
		 * recently_touched = true; //...y ya no es la primera vez
		 * firstRecently_touched = false; }
		 */
	}

	private void forceLoadMultimedia() {

		if (!multimediaInitialized) {

			loader.loadSprites();
			loader.loadSounds();
			loader.loadMusic();
			loader.loadBackgrounds();

			loader.finishLoading();

			loader.initSprites();
			loader.initSounds();
			loader.initMusic();
			loader.initBackgrounds();

			multimediaInitialized = true;
		}

	}

	private void loadMultimedia() {

		if (!multimediaInitialized) {

			loader.loadSprites();
			loader.loadSounds();
			loader.loadMusic();
			loader.loadBackgrounds();
		}
	}

	private void initMultimedia() {

		if (!multimediaInitialized) {

			loader.initSprites();
			loader.initSounds();
			loader.initMusic();
			loader.initBackgrounds();

			multimediaInitialized = true;
		}
	}

	//TODO ESTO SE HACE EN EL ON CREATE, ESTA PUESTO AQUI PARA PROBAR LA CARGA ASÍNCRONA.
	private void initComponents() {
		engine = new GameEngine();

		//loadMultimedia();

		engine.create();

		try {

			// Evitar que cuando se reinicia el juego se reinicialize t0do.
			// Con esto solo se cargarán los datos 1 vez.
			// if (!multimediaInitialized)

			// engine.start();

			//TODO PELIGRO

			introInstace = new com.mygdx.game.Screens.Scr_Introduction();

			gameOverInstance = new com.mygdx.game.Screens.Scr_GameOver();

			// instanciamos la clase Pause, la cual se encargara de dibujar la
			// pantalla en el estado de pausa.

			// instanciamos la clase MainMenu, la cual se encargará de la
			// aplicación cuando estemos en el menú principal.
			// mainMenuInstance = new com.mygdx.game.Screens.MainMenu();

			// estas dos variables servirán para que cuando el juego termine y
			// se cargue el menú, la pantalla no detecte la pulsación hasta que
			// se levante el dedo.
			recently_touched = false;

			// ************************************************************************
			// ****************** --- MECÁNICA JUGADOR ---
			// ****************************
			// ************************************************************************
			// No se pa que sirve.... por ahora
			clockShoot = 0;
			clockSprites = -5;

			// ************************************************************************
			// ******************** --- BACKGROUND MUSIC ---
			// **************************
			// ************************************************************************

			// cargamos el sonido de la música de fondo
			// backgroundMusic =
			// Gdx.audio.newMusic(Gdx.files.internal("audio/music/background.mp3"));

			// establecemos la musica de fondo como bucle e iniciamos su
			// reproducción.
			Musics.backgroundMusic.setLooping(true);

			levelList = new Array<Level>();

			levelList.add(new Level0());
			//levelList.add(new LevelTest());

			// variable para acceder a los niveles en el Array
			levelIndex = 0;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		componentsInitialized = true;
	}

}
