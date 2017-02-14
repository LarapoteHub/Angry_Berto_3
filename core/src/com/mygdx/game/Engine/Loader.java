package com.mygdx.game.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Multimedia.Backgrounds;
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
        param.minFilter = Texture.TextureFilter.Linear;
        param.genMipMaps = true;
    }

    public void loadSprites() {

        //BOTONES - BUTTONS
        this.load("sprites/buttons/pauseButton.png", Texture.class, param);
        this.load("sprites/buttons/playButton.png", Texture.class, param);
        this.load("sprites/buttons/archievementsButtonMenu.png", Texture.class, param);
        this.load("sprites/buttons/archievementsButtonMenuTouched.png", Texture.class, param);
        this.load("sprites/buttons/optionsButtonMenu.png", Texture.class, param);
        this.load("sprites/buttons/optionsButtonMenuTouched.png", Texture.class, param);
        this.load("sprites/buttons/scoresButtonMenu.png", Texture.class, param);
        this.load("sprites/buttons/scoresButtonMenuTouched.png", Texture.class, param);
        this.load("sprites/buttons/initButtonMenu.png", Texture.class, param);
        this.load("sprites/buttons/initButtonMenuTouched.png", Texture.class, param);
        this.load("sprites/buttons/exitButtonMenu.png", Texture.class, param);
        this.load("sprites/buttons/exitButtonMenuTouched.png", Texture.class, param);
        this.load("sprites/buttons/live.png", Texture.class, param);

        //POWERUPS
        this.load("sprites/powerups/livePowerUp.png", Texture.class, param);
        this.load("sprites/powerups/livePowerUpB.png", Texture.class, param);
        // Steroids warning! Might want to skip Olympics this year...
        this.load("sprites/powerups/chargeBar/chargeBar9.png", Texture.class, param);
        this.load("sprites/powerups/charge.png", Texture.class, param);
        this.load("sprites/powerups/rotaryHoePowerUp0.png", Texture.class, param);
        this.load("sprites/powerups/attackCowPowerUp.png", Texture.class, param);

        this.load("sprites/powerups/hoePowerUp.png", Texture.class, param);
        this.load("sprites/powerups/hoePowerUpB.png", Texture.class, param);
        this.load("sprites/powerups/dogPowerUp.png", Texture.class, param);
        this.load("sprites/powerups/dogPowerUpB.png", Texture.class, param);

        //OTROS - OTHERS
        this.load("sprites/others/star.png", Texture.class, param);
        this.load("sprites/others/explosion.png", Texture.class, param);
        this.load("sprites/others/gameOver.png", Texture.class, param);

        //LOGOS
        this.load("sprites/logos/angryBerto.png", Texture.class, param);

        //PERSONAJES
        this.load("sprites/characters/berto.png", Texture.class, param);
        this.load("sprites/characters/lina.png", Texture.class, param);

        //JUGADOR
        this.load("sprites/player/player.png", Texture.class, param);
        this.load("sprites/player/playerB.png", Texture.class, param);
        this.load("sprites/player/playerFire/fire0.png", Texture.class, param);
        this.load("sprites/player/playerFire/fire1.png", Texture.class, param);
        this.load("sprites/player/playerFire/fire2.png", Texture.class, param);

        //ENEMIGOS

        this.load("sprites/enemies/standardEnemy.png", Texture.class, param);
        this.load("sprites/enemies/evadingEnemy.png", Texture.class, param);
        this.load("sprites/enemies/spikeBallEnemy.png", Texture.class, param);
        this.load("sprites/enemies/heavyEnemy.png", Texture.class, param);

        //BOSSES

        this.load("sprites/enemies/firstBoss.png", Texture.class, param);

        //BALAS - BULLETS

        this.load("sprites/projectiles/playerShoot.png", Texture.class, param);
        this.load("sprites/projectiles/standardEnemyShoot.png", Texture.class, param);
        this.load("sprites/projectiles/heavyEnemyShoot.png", Texture.class, param);
        this.load("sprites/projectiles/heavyEnemyShootB.png", Texture.class, param);
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
        this.load("audio/music/background.mp3", Music.class);
        this.load("audio/music/backgroundMenu.mp3", Music.class);
        this.load("audio/music/boss1Music.mp3", Music.class);
    }

    public void loadBackgrounds() {
        this.load("backgrounds/backgroundMenu.png", Texture.class, param);
        this.load("backgrounds/backgroundGameOver.png", Texture.class, param);
        this.load("backgrounds/backgroundIntro.png", Texture.class, param);
        this.load("backgrounds/powerUps.png", Texture.class, param);
    }

    public void initSprites() {

        // Part 2 - Les Botones - PowerUps
        Sprites.btn_rotaryHoe = new Sprite[2];
        Sprites.btn_rotaryHoe[0] = new Sprite();
        Sprites.btn_rotaryHoe[1] = new Sprite();
        Sprites.btn_rotaryHoe[0].setTexture((Texture) this.get("sprites/powerups/hoePowerUp.png"));
        Sprites.btn_rotaryHoe[1].setTexture((Texture) this.get("sprites/powerups/hoePowerUpB.png"));

        Sprites.btn_dog = new Sprite[2];
        Sprites.btn_dog[0] = new Sprite();
        Sprites.btn_dog[1] = new Sprite();
        Sprites.btn_dog[0].setTexture((Texture) this.get("sprites/powerups/dogPowerUp.png"));
        Sprites.btn_dog[1].setTexture((Texture) this.get("sprites/powerups/dogPowerUpB.png"));

        // Steroids warning! Might want to skip Olympics this year...
        Sprites.powerUp_charge_bar = new Sprite((Texture) this.get("sprites/powerups/chargeBar/chargeBar9.png"));
        Sprites.powerUp_charge_increase = new Sprite((Texture) this.get("sprites/powerups/charge.png"));
        Sprites.powerUp_rotaryHoe = new Sprite((Texture) this.get("sprites/powerups/rotaryHoePowerUp0.png"));
        Sprites.powerUp_attackCow = new Sprite((Texture) this.get("sprites/powerups/attackCowPowerUp.png"));

        // BOTONES - BUTTONS
        Sprites.btn_pause = new Sprite[2];
        Sprites.btn_pause[0] = new Sprite();
        Sprites.btn_pause[1] = new Sprite();
        Sprites.btn_pause[0].setTexture((Texture) this.get("sprites/buttons/pauseButton.png"));
        Sprites.btn_pause[1].setTexture((Texture) this.get("sprites/buttons/playButton.png"));

        Sprites.btn_achievements = new Sprite[2];
        Sprites.btn_achievements[0] = new Sprite();
        Sprites.btn_achievements[1] = new Sprite();
        Sprites.btn_achievements[0].setTexture((Texture) this.get("sprites/buttons/archievementsButtonMenu.png"));
        Sprites.btn_achievements[1].setTexture((Texture) this.get("sprites/buttons/archievementsButtonMenuTouched.png"));

        Sprites.btn_options = new Sprite[2];
        Sprites.btn_options[0] = new Sprite();
        Sprites.btn_options[1] = new Sprite();
        Sprites.btn_options[0].setTexture((Texture) this.get("sprites/buttons/optionsButtonMenu.png"));
        Sprites.btn_options[1].setTexture((Texture) this.get("sprites/buttons/optionsButtonMenuTouched.png"));

        Sprites.btn_scores = new Sprite[2];
        Sprites.btn_scores[0] = new Sprite();
        Sprites.btn_scores[1] = new Sprite();
        Sprites.btn_scores[0].setTexture((Texture) this.get("sprites/buttons/scoresButtonMenu.png"));
        Sprites.btn_scores[1].setTexture((Texture) this.get("sprites/buttons/scoresButtonMenuTouched.png"));

        Sprites.btn_init = new Sprite[2];
        Sprites.btn_init[0] = new Sprite();
        Sprites.btn_init[1] = new Sprite();
        Sprites.btn_init[0].setTexture((Texture) this.get("sprites/buttons/initButtonMenu.png"));
        Sprites.btn_init[1].setTexture((Texture) this.get("sprites/buttons/initButtonMenuTouched.png"));

        Sprites.btn_exit = new Sprite[2];
        Sprites.btn_exit[0] = new Sprite();
        Sprites.btn_exit[1] = new Sprite();
        Sprites.btn_exit[0].setTexture((Texture) this.get("sprites/buttons/exitButtonMenu.png"));
        Sprites.btn_exit[1].setTexture((Texture) this.get("sprites/buttons/exitButtonMenuTouched.png"));

        Sprites.btn_plusHP = new Sprite[2];
        Sprites.btn_plusHP[0] = new Sprite();
        Sprites.btn_plusHP[1] = new Sprite();
        Sprites.btn_plusHP[0].setTexture((Texture) this.get("sprites/powerups/livePowerUp.png"));
        Sprites.btn_plusHP[1].setTexture((Texture) this.get("sprites/powerups/livePowerUpB.png"));
        Sprites.life = new Sprite((Texture) this.get("sprites/buttons/live.png"));


        //otros - others
        Sprites.explosion = new Sprite((Texture) this.get("sprites/others/explosion.png"));
        Sprites.gameOver = new Sprite((Texture) this.get("sprites/others/gameOver.png"));
        Sprites.star = new Sprite((Texture) this.get("sprites/others/star.png"));

        // Logos
        Sprites.logo = new Sprite((Texture) this.get("sprites/logos/angryBerto.png"));

        //Personajes
        Sprites.berto = new Sprite((Texture) this.get("sprites/characters/berto.png"));
        Sprites.lina = new Sprite((Texture) this.get("sprites/characters/lina.png"));

        // jugador
        Sprites.player = new Sprite[2];
        Sprites.player[0] = new Sprite();
        Sprites.player[1] = new Sprite();
        Sprites.player[0].setTexture((Texture) this.get("sprites/player/player.png"));
        Sprites.player[1].setTexture((Texture) this.get("sprites/player/playerB.png"));
        Sprites.player_propulsion = new Sprite[3];
        Sprites.player_propulsion[0] = new Sprite();
        Sprites.player_propulsion[1] = new Sprite();
        Sprites.player_propulsion[2] = new Sprite();
        Sprites.player_propulsion[0].setTexture((Texture) this.get("sprites/player/playerFire/fire0.png"));
        Sprites.player_propulsion[1].setTexture((Texture) this.get("sprites/player/playerFire/fire1.png"));
        Sprites.player_propulsion[2].setTexture((Texture) this.get("sprites/player/playerFire/fire2.png"));

        // Enemigos
        Sprites.enemy_std = new Sprite((Texture) this.get("sprites/enemies/standardEnemy.png"));
        Sprites.enemy_dodging = new Sprite((Texture) this.get("sprites/enemies/evadingEnemy.png"));
        Sprites.enemy_spikeBall = new Sprite((Texture) this.get("sprites/enemies/spikeBallEnemy.png"));
        Sprites.enemy_heavy = new Sprite((Texture) this.get("sprites/enemies/heavyEnemy.png"));

        //BOSSES
        Sprites.boss_1 = new Sprite((Texture) this.get("sprites/enemies/firstBoss.png"));

        // Balas - Bullets
        Sprites.bullet_player = new Sprite((Texture) this.get("sprites/projectiles/playerShoot.png"));
        Sprites.bullet_enemy = new Sprite((Texture) this.get("sprites/projectiles/standardEnemyShoot.png"));
        Sprites.bullet_heavy_enemy = new Sprite[2];
        Sprites.bullet_heavy_enemy[0] = new Sprite();
        Sprites.bullet_heavy_enemy[1] = new Sprite();
        Sprites.bullet_heavy_enemy[0].setTexture((Texture) this.get("sprites/projectiles/heavyEnemyShoot.png"));
        Sprites.bullet_heavy_enemy[1].setTexture((Texture) this.get("sprites/projectiles/heavyEnemyShootB.png"));

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

        Musics.backgroundMusic = (Music) this.get("audio/music/background.mp3");
        Musics.backgroundMenuMusic = (Music) this.get("audio/music/backgroundMenu.mp3");
        Musics.boss1Music = (Music) this.get("audio/music/boss1Music.mp3");

    }

    public void initBackgrounds() {
        Backgrounds.backgroundMenuImage = (Texture) this.get("backgrounds/backgroundMenu.png");
        Backgrounds.backgroundGameOver = (Texture) this.get("backgrounds/backgroundGameOver.png");
        Backgrounds.backgroundIntro = (Texture) this.get("backgrounds/backgroundIntro.png");
        Backgrounds.backgroundPowerUps = (Texture) this.get("backgrounds/powerUps.png");
    }

}
