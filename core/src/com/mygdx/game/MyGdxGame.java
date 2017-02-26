package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Engine.Loader;
import com.mygdx.game.Engine.MusicManager;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Levels.Level0;

// TODO LIST!
// Lista donde se pone el resto de cosas que no son directamente relacionadas con codigo
// Corregir el playButton.png

public class MyGdxGame extends ApplicationAdapter {
    // Dimensiones de la pantalla de renderizado DENTRO del juego...
    // TODO Esto debería ajustarse a la pantalla actual que tenemos en realidad,
    // TODO con bordes negros
    // TODO o lo que haga falta.
    public static final int WIDTH = 480, HEIGHT = 800, ORIGIN_X = 68;
    //EL WIDTH DE LA BARRA DE LA IZQUIERDA ES 68.

    // modo DEBUG
    public static final boolean DEBUG_MODE = false;

    public static final boolean SHOW_FPS = true;
    public static final boolean MOSTRAR_COMENTARIOS_CHORRA = false;

    private Loader loader;

    private boolean multimediaInitialized = false;

    private long frameStart;

    static int clockSprites;

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

    public static MusicManager musicManager;

    private BitmapFont fuenteDelDany;
    private SpriteBatch batchDelDany;
    private OrthographicCamera camaraDelDany = new OrthographicCamera();


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
        initComponents();

    } // fin del método create() <-----

    // ------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    // -------------------------------->>>>> RENDER
    // <<<<<----------------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------

    @Override
    public void render() {

        //un comentario

        frameStart = System.nanoTime();

        engine.tick(); // TODO Use some DeltaTime mechanics??

        if (showFPS)
            System.out.println(1000.0 / ((1.0 * System.nanoTime() - frameStart) / 1000000));


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
        // Limpiamos lo demas.
        engine.dispose();
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
            loadMultimedia();
            loader.finishLoading();
            initMultimedia();

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

            // instanciamos la clase Pause, la cual se encargara de dibujar la
            // pantalla en el estado de pausa.

            // instanciamos la clase MainMenu, la cual se encargará de la
            // aplicación cuando estemos en el menú principal.
            // mainMenuInstance = new com.mygdx.game.Screens.MainMenu();

            // estas dos variables servirán para que cuando el juego termine y
            // se cargue el menú, la pantalla no detecte la pulsación hasta que
            // se levante el dedo.
            // ************************************************************************
            // ****************** --- MECÁNICA JUGADOR ---
            // ****************************
            // ************************************************************************
            // ************************************************************************
            // ******************** --- BACKGROUND MUSIC ---
            // **************************
            // ************************************************************************

            // cargamos el sonido de la música de fondo
            // backgroundMusic =
            // Gdx.audio.newMusic(Gdx.files.internal("audio/music/background.mp3"));

            // establecemos la musica de fondo como bucle e iniciamos su
            // reproducción.

            levelList = new Array<Level>();

            levelList.add(new Level0());
            //levelList.add(new LevelTest());

            // variable para acceder a los niveles en el Array
            levelIndex = 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
