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
    enum SprPath {

        // Les Botones.
        btn_pause("sprites/buttons/pauseButton.png", "sprites/buttons/playButton.png"),
        btn_skip("sprites/buttons/skipButton.png"),

        btn_achievements("sprites/buttons/archievementsButtonMenu.png", "sprites/buttons/archievementsButtonMenuTouched.png"),
        btn_options("sprites/buttons/optionsButtonMenu.png", "sprites/buttons/optionsButtonMenuTouched.png"),
        btn_scores("sprites/buttons/scoresButtonMenu.png", "sprites/buttons/scoresButtonMenuTouched.png"),

        btn_init("sprites/buttons/initButtonMenu.png", "sprites/buttons/initButtonMenuTouched.png"),
        btn_exit("sprites/buttons/exitButtonMenu.png", "sprites/buttons/exitButtonMenuTouched.png"),

        btn_dog("sprites/powerups/dogPowerUp.png", "sprites/powerups/dogPowerUpB.png"),
        btn_rotaryHoe("sprites/powerups/hoePowerUp.png", "sprites/powerups/hoePowerUpB.png"),
        btn_plusHP("sprites/powerups/livePowerUp.png", "sprites/powerups/livePowerUpB.png"),
        btn_switch_bullet("sprites/powerups/type_1_btn.png", "sprites/powerups/type_2_btn.png"),

        // Charge Bar / Power Up
        powerUp_charge_bar("sprites/powerups/chargeBar/chargeBar9.png"),
        powerUp_charge_bar_container("sprites/powerups/chargeBar/chargeBarContainer.png"),
        powerUp_charge_increase("sprites/powerups/charge.png"),
        powerUp_rotaryHoe("sprites/powerups/rotaryHoePowerUp0.png"),
        powerUp_attackCow("sprites/powerups/attackCowPowerUp.png"),

        // Other
        life("sprites/buttons/live.png"),
        explosion("sprites/others/explosion.png"),

        gameOver("sprites/others/gameOver.png"),
        logo("sprites/logos/angryBerto.png"),

        berto("sprites/characters/berto.png"),
        lina("sprites/characters/lina.png"),

        scoreStar("sprites/others/scoreStar.png"),

        // Important stuff!
        // TODO Implementar los otros colores del jugador algun dia
        player("sprites/player/player.png"),
        player_propulsion("sprites/player/playerPropulsion.png"),


        // Enemies
        enemy_std("sprites/enemies/standardEnemy.png"),
        enemy_dodging("sprites/enemies/evadingEnemy.png"),
        enemy_spikeBall("sprites/enemies/spikeBallEnemy.png"),
        enemy_heavy("sprites/enemies/heavyEnemy.png"),
        enemy_satellite_orbit("sprites/enemies/satelliteOrbitEnemy.png"),
        enemy_core_orbit("sprites/enemies/coreOrbitEnemy.png"),
        //FALLA EN ANDROID PORQUE SÍ:
        enemy_shield("sprites/enemies/shieldEnemy.png"),
        //bad name but...
        enemy_shield__shield("sprites/enemies/shieldEnemy_Shield.png"),


        // BOSSES
        boss_1("sprites/enemies/firstBoss.png"),

        // Balas - Bullets
        bullet_player("sprites/projectiles/playerShoot.png", "sprites/projectiles/playerShoot_brown.png"),
        bullet_enemy("sprites/projectiles/standardEnemyShoot.png"),
        bullet_heavy_enemy("sprites/projectiles/heavyEnemyShoot.png", "sprites/projectiles/heavyEnemyShootB.png"),

        //Botones seleccion de nivel
        btn_level0("sprites/buttons/levels/level0.png"),
        btn_level_test("sprites/buttons/levels/level0.png"),
        btn_level_die("sprites/buttons/levels/level0.png"),

        // IL GRAN FINALE!
        ;
        private String path[];

        SprPath(String... path) {
            this.path = path;
        }
    }

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
            for (SprPath sp : SprPath.values()) {
                for (String path : sp.path) {
                    this.load(path, Texture.class, param);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
        this.load(SprPath.bulletSwitch_1.path, Texture.class, param);
        this.load(SprPath.bulletSwitch_2.path, Texture.class, param);
        this.load(SprPath.bullet_type_0.path, Texture.class, param);
        this.load(SprPath.bullet_type_1.path, Texture.class, param);*/
        // </TODO>

        //BOTONES - BUTTONS
        /*
        this.load(SprPath.pauseButton.path, Texture.class, param);
        this.load(SprPath.playButton.path, Texture.class, param);

        //Botones de seleccion de nivel
        this.load(SprPath.btn_level0.path, Texture.class, param);
        */
//
//        this.load("sprites/buttons/archievementsButtonMenu.png", Texture.class, param);
//        this.load("sprites/buttons/archievementsButtonMenuTouched.png", Texture.class, param);
//        this.load("sprites/buttons/optionsButtonMenu.png", Texture.class, param);
//        this.load("sprites/buttons/optionsButtonMenuTouched.png", Texture.class, param);
//        this.load("sprites/buttons/scoresButtonMenu.png", Texture.class, param);
//        this.load("sprites/buttons/scoresButtonMenuTouched.png", Texture.class, param);
//        this.load("sprites/buttons/initButtonMenu.png", Texture.class, param);
//        this.load("sprites/buttons/initButtonMenuTouched.png", Texture.class, param);
//        this.load("sprites/buttons/exitButtonMenu.png", Texture.class, param);
//        this.load("sprites/buttons/exitButtonMenuTouched.png", Texture.class, param);
//        this.load("sprites/buttons/live.png", Texture.class, param);
//        //this.load(SprPath.skipButton.path, Texture.class, param);
//
//        //POWERUPS
//        this.load("sprites/powerups/livePowerUp.png", Texture.class, param);
//        this.load("sprites/powerups/livePowerUpB.png", Texture.class, param);
//        // Steroids warning! Might want to skip Olympics this year...
//        this.load("sprites/powerups/chargeBar/chargeBar9.png", Texture.class, param);
//        //this.load(SprPath.powerUp_charge_bar_container.path, Texture.class, param);
//        this.load("sprites/powerups/charge.png", Texture.class, param);
//        this.load("sprites/powerups/rotaryHoePowerUp0.png", Texture.class, param);
//        this.load("sprites/powerups/attackCowPowerUp.png", Texture.class, param);
//
//        this.load("sprites/powerups/hoePowerUp.png", Texture.class, param);
//        this.load("sprites/powerups/hoePowerUpB.png", Texture.class, param);
//        this.load("sprites/powerups/dogPowerUp.png", Texture.class, param);
//        this.load("sprites/powerups/dogPowerUpB.png", Texture.class, param);
//
//        //OTROS - OTHERS
//        this.load("sprites/others/star.png", Texture.class, param);
//        this.load("sprites/others/explosion.png", Texture.class, param);
//        this.load("sprites/others/gameOver.png", Texture.class, param);
//
//        //OTROS - OTHERS
//        // TODO DANGER!!
//        this.load("effects/test.p", ParticleEffect.class);
//        this.load("sprites/others/star.png", Texture.class, param);
//        this.load("sprites/others/explosion.png", Texture.class, param);
//        this.load("sprites/others/gameOver.png", Texture.class, param);
//
//        //LOGOS
//        this.load("sprites/logos/angryBerto.png", Texture.class, param);
//
//        //PERSONAJES
//        this.load("sprites/characters/berto.png", Texture.class, param);
//        this.load("sprites/characters/lina.png", Texture.class, param);
//
//        //JUGADOR
//        this.load("sprites/player/player.png", Texture.class, param);
//        //this.load(SprPath.player_propulsion.path, Texture.class, param);
//
//        //ENEMIGOS
//
//        this.load("sprites/enemies/standardEnemy.png", Texture.class, param);
//        this.load("sprites/enemies/evadingEnemy.png", Texture.class, param);
//        this.load("sprites/enemies/spikeBallEnemy.png", Texture.class, param);
//        this.load("sprites/enemies/heavyEnemy.png", Texture.class, param);
//        //this.load(SprPath.enemy_satellite_orbit.path, Texture.class, param);
//        //this.load(SprPath.enemy_core_orbit.path, Texture.class, param);
//
//        //BOSSES
//
//        this.load("sprites/enemies/firstBoss.png", Texture.class, param);
//
//        //BALAS - BULLETS
//
//        this.load("sprites/projectiles/playerShoot.png", Texture.class, param);
//        this.load("sprites/projectiles/standardEnemyShoot.png", Texture.class, param);
//        this.load("sprites/projectiles/heavyEnemyShoot.png", Texture.class, param);
//        this.load("sprites/projectiles/heavyEnemyShootB.png", Texture.class, param);
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

        // Recorrer TODOS los valores de SprPath
        for (SprPath sPath : SprPath.values()) {
            // Crear un array de sprites con el nombre
            Sprite[] spr = Sprites.getSpriteByName(sPath.name());

            // En caso de que se quieran cargar mas sprites que el tamaño del array que existe
            // en el HashMap, amañar eso.
            if (spr.length != sPath.path.length) {
                spr = new Sprite[sPath.path.length];
            }

            // Ir path por path
            Sprite tSpr;
            for (int i = 0; i < sPath.path.length; i++) {
                // Crear sprite con textura
                tSpr = new Sprite((Texture) this.get(sPath.path[i]));

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


        //Botones seleccion de nivel
        //Sprites.btn_level0 = new Sprite((Texture) this.get(SprPath.btn_level0.path));

        // <TODO TEST BULLET TYPES>
        /*Sprites.btn_switch_bullet = new Sprite[2];
        Sprites.btn_switch_bullet[0] = new Sprite((Texture) this.get(SprPath.bulletSwitch_1.path));
        Sprites.btn_switch_bullet[1] = new Sprite((Texture) this.get(SprPath.bulletSwitch_2.path));*/
        // </TODO>


        // TODO Organizar mejor esto
        // TODO Acortar las 2 lineas en 1, como arriba.
        // Part 2 - Les Botones - PowerUps
//



        // TODO DANGER!!
        Effects.star = new ParticleEffect();
        Effects.star.load(Gdx.files.internal("effects/test.p"), Gdx.files.internal("effects"));

//
//        Sprites.player = new Sprite((Texture) this.get("sprites/player/player.png"));
//        // TODO Implementar los otros colores del jugador algun dia
//
//        Sprites.player_propulsion = new Sprite((Texture) this.get(SprPath.player_propulsion.path));
//
//        // Enemigos
//
//        //BOSSES
//        Sprites.boss_1 = new Sprite((Texture) this.get("sprites/enemies/firstBoss.png"));
//
//        // Balas - Bullets
//        Sprites.bullet_player = new Sprite[2];
//        Sprites.bullet_player[0] = new Sprite((Texture) this.get(SprPath.bullet_type_0.path));
//        Sprites.bullet_player[1] = new Sprite((Texture) this.get(SprPath.bullet_type_1.path));


//        Sprites.bullet_heavy_enemy = new Sprite[2];
//        Sprites.bullet_heavy_enemy[0] = new Sprite();
//        Sprites.bullet_heavy_enemy[1] = new Sprite();
//        Sprites.bullet_heavy_enemy[0].setTexture((Texture) this.get("sprites/projectiles/heavyEnemyShoot.png"));
//        Sprites.bullet_heavy_enemy[1].setTexture((Texture) this.get("sprites/projectiles/heavyEnemyShootB.png"));

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
