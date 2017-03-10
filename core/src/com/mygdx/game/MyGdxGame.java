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

    // Por alguna razon esto no hace falta inicializarlo O.o??
    private Loader loader;

    private boolean multimediaInitialized = false;

    GameEngine engine;

    public static MusicManager musicManager;

    // ------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------

    @Override
    public void create() {

        GameData.initGameData();
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

        engine.tick(); // TODO Use some DeltaTime mechanics??

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

    // DEBUG MODE
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

        engine.create();

    }

}
