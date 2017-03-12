package com.mygdx.game.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Effects;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by Red Mercy on 9/26/2016.
 * Clase para hacer el cargado de los recursos mas organizado.
 */
public class Loader extends AssetManager {

    TextureLoader.TextureParameter param;

    public Loader() {
        param = new TextureLoader.TextureParameter();
        //param.minFilter = Texture.TextureFilter.Linear;
        param.magFilter = Texture.TextureFilter.Linear;
        // Esto ultimo es solo util para 3D, pero bueno...
        param.genMipMaps = true;
    }

    // Fase de Load compleja. Deberia haber un String que tenga todas las rutas.
    // Enum algo chungo de entender.

    /**
     * Nota: PELIGROSO ya que se puede acceder a los paths de los archivos. NO sirve para la CIA.
     */

    enum MusicPath {

        background_music("audio/music/background.mp3"),
        background_menu_music("audio/music/backgroundMenu.mp3"),
        boss1_music("audio/music/boss1Music.mp3"),
        background_level_selection_music("audio/music/backgroundLevelSelectionMusic.mp3");
        private final String path;

        MusicPath(String path) {
            this.path = path;
        }
    }

    enum BackgroundPath {
        background_level_selection("backgrounds/backgroundLevelSelection.png");
        private final String path;

        BackgroundPath(String path) {
            this.path = path;
        }
    }

    public void loadSprites() {

        try {

            // <TODO UBER BUCLE DE CARGA TEST!>
            for (SprNames sp : SprNames.values()) {
                for (String path : sp.getPath()) {
                    this.load(path, Texture.class, param);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadSounds() {

        this.load("audio/sounds/explode.wav", Sound.class);
        this.load("audio/sounds/enemyShoot.wav", Sound.class);
        this.load("audio/sounds/playerHitted.mp3", Sound.class);
        this.load("audio/sounds/rotaryHoe.mp3", Sound.class);
        this.load("audio/sounds/liveRestored.mp3", Sound.class);
        this.load("audio/sounds/attackCow.mp3", Sound.class);
        this.load("audio/sounds/ding.mp3", Sound.class);
        this.load("audio/sounds/bertolinaSound.mp3", Sound.class);
        this.load("audio/sounds/charge.mp3", Sound.class);
        this.load("audio/sounds/heavyEnemyShoot.mp3", Sound.class);

    }

    public void loadMusic() {
        this.load(MusicPath.background_music.path, Music.class);
        this.load(MusicPath.background_menu_music.path, Music.class);
        this.load(MusicPath.boss1_music.path, Music.class);
        this.load(MusicPath.background_level_selection_music.path, Music.class);
    }

    public void loadBackgrounds() {
        this.load("backgrounds/backgroundMenu.png", Texture.class, param);
        this.load("backgrounds/backgroundGameOver.png", Texture.class, param);
        this.load("backgrounds/backgroundIntro.png", Texture.class, param);
        this.load("backgrounds/powerUps.png", Texture.class, param);
        this.load("backgrounds/universe1.jpg", Texture.class, param);
        this.load("backgrounds/universe2.jpg", Texture.class, param);
        this.load(BackgroundPath.background_level_selection.path, Texture.class, param);
    }

    public void initSprites() {

        // Recorrer TODOS los valores de SprNames
        for (SprNames sPath : SprNames.values()) {
            // Crear un array de sprites con el nombre
            Sprite[] spr = Sprites.getSpriteByName(sPath.name());

            // En caso de que se quieran cargar mas sprites que el tamaño del array que existe
            // en el HashMap, amañar eso.
            if (spr.length != sPath.getPath().length) {
                spr = new Sprite[sPath.getPath().length];
            }

            // Ir path por path
            Sprite tSpr;
            for (int i = 0; i < sPath.getPath().length; i++) {
                // Crear sprite con textura
                tSpr = new Sprite((Texture) this.get(sPath.getPath()[i]));

                // Ajustar el tamaño del Sprite al de la textura.
                tSpr.setBounds(0, 0, tSpr.getTexture().getWidth(), tSpr.getTexture().getHeight());
                //tSpr.setSize(tSpr.getTexture().getWidth(), tSpr.getTexture().getHeight());

                // Poner el Sprite ya listo en el array
                spr[i] = tSpr;
            }
            // TODO Cambiar "todo" por "tod0" o algo si para expresarse libremente.
            // Poner tod0 de vuelta en el HashMap
            Sprites.putSpriteWithName(sPath.name(), spr);
        }

        // TODO DANGER!!
        Effects.star = new ParticleEffect();
        Effects.star.load(Gdx.files.internal("effects/test.p"), Gdx.files.internal("effects"));

    }

    public void initSounds() {
        Sounds.explodeSound = (Sound) this.get("audio/sounds/explode.wav");
        Sounds.enemyShootSound = (Sound) this.get("audio/sounds/enemyShoot.wav");
        Sounds.playerHitSound = (Sound) this.get("audio/sounds/playerHitted.mp3");
        Sounds.rotaryHoeSound = (Sound) this.get("audio/sounds/rotaryHoe.mp3");
        Sounds.liveRestoredSound = (Sound) this.get("audio/sounds/liveRestored.mp3");
        Sounds.attackCowSound = (Sound) this.get("audio/sounds/attackCow.mp3");
        Sounds.dingSound = (Sound) this.get("audio/sounds/ding.mp3");
        Sounds.bertolinaSound = (Sound) this.get("audio/sounds/bertolinaSound.mp3");
        Sounds.chargeSound = (Sound) this.get("audio/sounds/charge.mp3");
        Sounds.heavyEnemyShootSound = (Sound) this.get("audio/sounds/heavyEnemyShoot.mp3");
    }

    public void initMusic() {

        Musics.backgroundMusic = (Music) this.get(MusicPath.background_music.path);
        Musics.backgroundMenuMusic = (Music) this.get(MusicPath.background_menu_music.path);
        Musics.boss1Music = (Music) this.get(MusicPath.boss1_music.path);
        Musics.backgroundLevelSelectionMusic = (Music) this.get(MusicPath.background_level_selection_music.path);

    }

    public void initBackgrounds() {
        Backgrounds.backgroundMenuImage = (Texture) this.get("backgrounds/backgroundMenu.png");
        Backgrounds.backgroundGameOver = (Texture) this.get("backgrounds/backgroundGameOver.png");
        Backgrounds.backgroundIntro = (Texture) this.get("backgrounds/backgroundIntro.png");
        Backgrounds.backgroundPowerUps = (Texture) this.get("backgrounds/powerUps.png");
        Backgrounds.universe_1 = (Texture) this.get("backgrounds/universe1.jpg");
        Backgrounds.universe_2 = (Texture) this.get("backgrounds/universe2.jpg");
        Backgrounds.backgroundLevelSelection = (Texture) this.get(BackgroundPath.background_level_selection.path);
    }

}
