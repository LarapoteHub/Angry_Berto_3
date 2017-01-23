package com.mygdx.game.Engine;

import com.badlogic.gdx.Gdx;
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
public class Loader {
	// Optimizar esto...
    public void loadSprites() {
        // Crisp code :D
        // Les Botones

        Sprites.btn_pause = new Sprite[2];
        Sprites.btn_pause[0] = new Sprite();
        Sprites.btn_pause[1] = new Sprite();
        Sprites.btn_pause[0].setTexture(new Texture(Gdx.files.internal("sprites/buttons/pauseButton.png")));
        Sprites.btn_pause[1].setTexture(new Texture(Gdx.files.internal("sprites/buttons/playButton.png")));

        // Part 2 - Les Botones - PowerUps
        Sprites.btn_rotaryHoe = new Sprite[2];
        Sprites.btn_rotaryHoe[0] = new Sprite();
        Sprites.btn_rotaryHoe[1] = new Sprite();
        Sprites.btn_rotaryHoe[0].setTexture(new Texture(Gdx.files.internal("sprites/powerups/hoePowerUp.png")));
        Sprites.btn_rotaryHoe[1].setTexture(new Texture(Gdx.files.internal("sprites/powerups/hoePowerUpB.png")));

        Sprites.btn_dog = new Sprite[2];
        Sprites.btn_dog[0] = new Sprite();
        Sprites.btn_dog[1] = new Sprite();
        Sprites.btn_dog[0].setTexture(new Texture(Gdx.files.internal("sprites/powerups/dogPowerUp.png")));
        Sprites.btn_dog[1].setTexture(new Texture(Gdx.files.internal("sprites/powerups/dogPowerUpB.png")));

        // Part 3 - Les Botones del Menu
        Sprites.btn_achievements = new Sprite[2];
        Sprites.btn_achievements[0] = new Sprite();
        Sprites.btn_achievements[1] = new Sprite();
        Sprites.btn_achievements[0].setTexture(new Texture(Gdx.files.internal("sprites/buttons/archievementsButtonMenu.png")));
        Sprites.btn_achievements[1].setTexture(new Texture(Gdx.files.internal("sprites/buttons/archievementsButtonMenuTouched.png")));

        Sprites.btn_options = new Sprite[2];
        Sprites.btn_options[0] = new Sprite();
        Sprites.btn_options[1] = new Sprite();
        Sprites.btn_options[0].setTexture(new Texture(Gdx.files.internal("sprites/buttons/optionsButtonMenu.png")));
        Sprites.btn_options[1].setTexture(new Texture(Gdx.files.internal("sprites/buttons/optionsButtonMenuTouched.png")));

        Sprites.btn_scores = new Sprite[2];
        Sprites.btn_scores[0] = new Sprite();
        Sprites.btn_scores[1] = new Sprite();
        Sprites.btn_scores[0].setTexture(new Texture(Gdx.files.internal("sprites/buttons/scoresButtonMenu.png")));
        Sprites.btn_scores[1].setTexture(new Texture(Gdx.files.internal("sprites/buttons/scoresButtonMenuTouched.png")));
        
        Sprites.btn_init = new Sprite[2];
        Sprites.btn_init[0] = new Sprite();
        Sprites.btn_init[1] = new Sprite();
        Sprites.btn_init[0].setTexture(new Texture(Gdx.files.internal("sprites/buttons/initButtonMenu.png")));
        Sprites.btn_init[1].setTexture(new Texture(Gdx.files.internal("sprites/buttons/initButtonMenuTouched.png")));
        
        Sprites.btn_exit = new Sprite[2];
        Sprites.btn_exit[0] = new Sprite();
        Sprites.btn_exit[1] = new Sprite();
        Sprites.btn_exit[0].setTexture(new Texture(Gdx.files.internal("sprites/buttons/exitButtonMenu.png")));
        Sprites.btn_exit[1].setTexture(new Texture(Gdx.files.internal("sprites/buttons/exitButtonMenuTouched.png")));
        
        Sprites.btn_plusHP = new Sprite[2];
        Sprites.btn_plusHP[0] = new Sprite();
        Sprites.btn_plusHP[1] = new Sprite();
        Sprites.btn_plusHP[0].setTexture(new Texture(Gdx.files.internal("sprites/powerups/livePowerUp.png")));
        Sprites.btn_plusHP[1].setTexture(new Texture(Gdx.files.internal("sprites/powerups/livePowerUpB.png")));
        // Otros
        // Efectos
        //Sprites.star = new Sprite(new Texture(Gdx.files.internal("sprites/others/star.png")));
        Sprites.star = new Sprite(new Texture(Gdx.files.internal("sprites/others/star.png")));

        Sprites.explosion = new Sprite[23];
        for (int i = 0; i < Sprites.explosion.length; i++) {
        	Sprites.explosion[i] = new Sprite();
            Sprites.explosion[i].setTexture(new Texture(Gdx.files.internal("sprites/others/explosion/explode" + i + ".png")));
        }

        // Demás
        Sprites.life = new Sprite(new Texture(Gdx.files.internal("sprites/buttons/live.png")));

        // Logos
        Sprites.logo = new Sprite(new Texture(Gdx.files.internal("sprites/logos/angryBerto.png")));
        Sprites.gameOver = new Sprite(new Texture(Gdx.files.internal("sprites/others/gameOver.png")));

        //Personajes
        Sprites.berto = new Sprite(new Texture(Gdx.files.internal("sprites/characters/berto.png")));
        Sprites.lina = new Sprite(new Texture(Gdx.files.internal("sprites/characters/lina.png")));

        // Naves ---
        Sprites.player = new Sprite[2];
        Sprites.player[0] = new Sprite();
        Sprites.player[1] = new Sprite();
        Sprites.player[0].setTexture(new Texture(Gdx.files.internal("sprites/player/player.png")));
        Sprites.player[1].setTexture(new Texture(Gdx.files.internal("sprites/player/playerB.png")));

        Sprites.player_propulsion = new Sprite[3];
        Sprites.player_propulsion[0] = new Sprite();
        Sprites.player_propulsion[1] = new Sprite();
        Sprites.player_propulsion[2] = new Sprite();
        Sprites.player_propulsion[0].setTexture(new Texture(Gdx.files.internal("sprites/player/playerFire/fire0.png")));
        Sprites.player_propulsion[1].setTexture(new Texture(Gdx.files.internal("sprites/player/playerFire/fire1.png")));
        Sprites.player_propulsion[2].setTexture(new Texture(Gdx.files.internal("sprites/player/playerFire/fire2.png")));

        // Enemigos
        Sprites.enemy_std = new Sprite[4];
        Sprites.enemy_std[0] = new Sprite();
        Sprites.enemy_std[1] = new Sprite();
        Sprites.enemy_std[2] = new Sprite();
        Sprites.enemy_std[3] = new Sprite();
        Sprites.enemy_std[0].setTexture(new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy0.png")));
        Sprites.enemy_std[1].setTexture(new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy1.png")));
        Sprites.enemy_std[2].setTexture(new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy2.png")));
        Sprites.enemy_std[3].setTexture(new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy3.png")));

        Sprites.enemy_dodging = new Sprite[2];
        Sprites.enemy_dodging[0] = new Sprite();
        Sprites.enemy_dodging[1] = new Sprite();
        Sprites.enemy_dodging[0].setTexture(new Texture(Gdx.files.internal("sprites/enemies/evadingEnemy.png")));
        Sprites.enemy_dodging[1].setTexture(new Texture(Gdx.files.internal("sprites/enemies/evadingEnemy-B.png")));

        Sprites.enemy_spikeBall = new Sprite[2];
        Sprites.enemy_spikeBall[0] = new Sprite();
        Sprites.enemy_spikeBall[1] = new Sprite();
        Sprites.enemy_spikeBall[0].setTexture(new Texture(Gdx.files.internal("sprites/enemies/spikeBallEnemy.png")));
        Sprites.enemy_spikeBall[1].setTexture(new Texture(Gdx.files.internal("sprites/enemies/spikeBallEnemy-B.png")));

        Sprites.enemy_heavy = new Sprite[2];
        Sprites.enemy_heavy[0] = new Sprite();
        Sprites.enemy_heavy[1] = new Sprite();
        Sprites.enemy_heavy[0].setTexture(new Texture(Gdx.files.internal("sprites/enemies/heavyEnemy.png")));
        Sprites.enemy_heavy[1].setTexture(new Texture(Gdx.files.internal("sprites/enemies/heavyEnemy-B.png")));

        // Balas - Bullets
        Sprites.bullet_player = new Sprite(new Texture(Gdx.files.internal("sprites/projectiles/playerShoot.png")));
        Sprites.bullet_enemy = new Sprite(new Texture(Gdx.files.internal("sprites/projectiles/standardEnemyShoot.png")));

        Sprites.bullet_heavy_enemy = new Sprite[2];
        Sprites.bullet_heavy_enemy[0] = new Sprite();
        Sprites.bullet_heavy_enemy[1] = new Sprite();
        Sprites.bullet_heavy_enemy[0].setTexture(new Texture(Gdx.files.internal("sprites/projectiles/heavyEnemyShoot.png")));
        Sprites.bullet_heavy_enemy[1].setTexture(new Texture(Gdx.files.internal("sprites/projectiles/heavyEnemyShootB.png")));

        Sprites.boss_1 = new Sprite(new Texture(Gdx.files.internal("sprites/enemies/to Use/cartoonship blue.png")));

        // Steroids warning! Might want to skip Olympics this year...
        Sprites.powerUp_charge_bar = new Sprite(new Texture(Gdx.files.internal("sprites/powerups/chargeBar/chargeBar9.png")));
        Sprites.powerUp_charge_increase = new Sprite(new Texture(Gdx.files.internal("sprites/powerups/charge.png")));
        Sprites.powerUp_rotaryHoe = new Sprite(new Texture(Gdx.files.internal("sprites/powerups/rotaryHoePowerUp0.png")));
        Sprites.powerUp_attackCow = new Sprite(new Texture(Gdx.files.internal("sprites/powerups/attackCowPowerUp.png")));

    }

    // OLD AND DEPRECATED!! #VICENTE_STYLE
    // Good code goes after commented section.
    /*
    public void loadSpritesOLD() {

        Sprites.exitButtonMenuImage = new Texture[2];
        Sprites.exitButtonMenuImage[0] = new Texture(Gdx.files.internal("sprites/buttons/exitButtonMenu.png"));
        Sprites.exitButtonMenuImage[1] = new Texture(Gdx.files.internal("sprites/buttons/exitButtonMenuTouched.png"));

        Sprites.initButtonMenuImage = new Texture[2];
        Sprites.initButtonMenuImage[0] = new Texture(Gdx.files.internal("sprites/buttons/initButtonMenu.png"));
        Sprites.initButtonMenuImage[1] = new Texture(Gdx.files.internal("sprites/buttons/initButtonMenuTouched.png"));

        Sprites.liveImage = new Texture(Gdx.files.internal("sprites/buttons/live.png"));

        Sprites.pausePlayButtonImage = new Texture[2];
        Sprites.pausePlayButtonImage[0] = new Texture(Gdx.files.internal("sprites/buttons/pauseButton.png"));
        Sprites.pausePlayButtonImage[1] = new Texture(Gdx.files.internal("sprites/buttons/playButton.png"));

        //PERSONAJES

        Sprites.bertoImage = new Texture(Gdx.files.internal("sprites/characters/berto.png"));
        Sprites.linaImage = new Texture(Gdx.files.internal("sprites/characters/lina.png"));

        //ENEMIGOS

        Sprites.standardEnemyImage = new Texture[4];
        Sprites.standardEnemyImage[0] = new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy0.png"));
        Sprites.standardEnemyImage[1] = new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy1.png"));
        Sprites.standardEnemyImage[2] = new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy2.png"));
        Sprites.standardEnemyImage[3] = new Texture(Gdx.files.internal("sprites/enemies/standardEnemy/standardEnemy3.png"));

        Sprites.evadingEnemyImage = new Texture[2];
        Sprites.evadingEnemyImage[0] = new Texture(Gdx.files.internal("sprites/enemies/evadingEnemy.png"));
        Sprites.evadingEnemyImage[1] = new Texture(Gdx.files.internal("sprites/enemies/evadingEnemy-B.png"));

        Sprites.spikeBallEnemyImage = new Texture[2];
        Sprites.spikeBallEnemyImage[0] = new Texture(Gdx.files.internal("sprites/enemies/spikeBallEnemy.png"));
        Sprites.spikeBallEnemyImage[1] = new Texture(Gdx.files.internal("sprites/enemies/spikeBallEnemy-B.png"));

        Sprites.heavyEnemyImage = new Texture[2];
        Sprites.heavyEnemyImage[0] = new Texture(Gdx.files.internal("sprites/enemies/heavyEnemy.png"));
        Sprites.heavyEnemyImage[1] = new Texture(Gdx.files.internal("sprites/enemies/heavyEnemy-B.png"));


        Sprites.standardEnemyShootImage = new Texture(Gdx.files.internal("sprites/projectiles/standardEnemyShoot.png"));

        Sprites.heavyEnemyShootImage = new Texture[2];
        Sprites.heavyEnemyShootImage[0] = new Texture(Gdx.files.internal("sprites/projectiles/heavyEnemyShoot.png"));
        Sprites.heavyEnemyShootImage[1] = new Texture(Gdx.files.internal("sprites/projectiles/heavyEnemyShootB.png"));

        //LOGOS

        Sprites.angryBertoLogoImage = new Texture(Gdx.files.internal("sprites/logos/angryBerto.png"));

        //OTROS

        Sprites.gameOverImage = new Texture(Gdx.files.internal("sprites/others/gameOver.png"));

        Sprites.starImage = new Texture(Gdx.files.internal("sprites/others/star.png"));

        Sprites.explodeImage = new Texture[23];

        for (int i = 0; i < Sprites.explodeImage.length; i++) {

            Sprites.explodeImage[i] = new Texture(Gdx.files.internal("sprites/others/explosion/explode" + i + ".png"));

        }


        //JUGADOR:

        Sprites.playerImage = new Texture[2];
        Sprites.playerImage[0] = new Texture(Gdx.files.internal("sprites/player/player.png"));
        Sprites.playerImage[1] = new Texture(Gdx.files.internal("sprites/player/playerB.png"));

        Sprites.playerShootImage = new Texture(Gdx.files.internal("sprites/projectiles/playerShoot.png"));

        Sprites.playerFireImage = new Texture[3];
        Sprites.playerFireImage[0] = new Texture(Gdx.files.internal("sprites/player/playerFire/fire0.png"));
        Sprites.playerFireImage[1] = new Texture(Gdx.files.internal("sprites/player/playerFire/fire1.png"));
        Sprites.playerFireImage[2] = new Texture(Gdx.files.internal("sprites/player/playerFire/fire2.png"));

        //POWERUPS

        Sprites.chargePowerUpImage = new Texture(Gdx.files.internal("sprites/powerups/charge.png"));

        Sprites.livePowerUpImage = new Texture[2];
        Sprites.livePowerUpImage[0] = new Texture(Gdx.files.internal("sprites/powerups/livePowerUp.png"));
        Sprites.livePowerUpImage[1] = new Texture(Gdx.files.internal("sprites/powerups/livePowerUpB.png"));

        Sprites.hoePowerUpBtnImage = new Texture[2];
        Sprites.hoePowerUpBtnImage[0] = new Texture(Gdx.files.internal("sprites/powerups/hoePowerUp.png"));
        Sprites.hoePowerUpBtnImage[1] = new Texture(Gdx.files.internal("sprites/powerups/hoePowerUpB.png"));

        Sprites.rotaryHoePowerUpImage = new Texture[4];
        Sprites.rotaryHoePowerUpImage[0] = new Texture(Gdx.files.internal("sprites/powerups/rotaryHoePowerUp0.png"));
        Sprites.rotaryHoePowerUpImage[1] = new Texture(Gdx.files.internal("sprites/powerups/rotaryHoePowerUp1.png"));
        Sprites.rotaryHoePowerUpImage[2] = new Texture(Gdx.files.internal("sprites/powerups/rotaryHoePowerUp2.png"));
        Sprites.rotaryHoePowerUpImage[3] = new Texture(Gdx.files.internal("sprites/powerups/rotaryHoePowerUp3.png"));

        Sprites.dogPowerUpBtnImage = new Texture[2];
        Sprites.dogPowerUpBtnImage[0] = new Texture(Gdx.files.internal("sprites/powerups/dogPowerUp.png"));
        Sprites.dogPowerUpBtnImage[1] = new Texture(Gdx.files.internal("sprites/powerups/dogPowerUpB.png"));


        Sprites.attackCowPowerUpImage = new Texture(Gdx.files.internal("sprites/powerups/attackCowPowerUp.png"));

        Sprites.chargeBarImage = new Texture[10];

        for (int i = 0; i < Sprites.chargeBarImage.length; i++) {
            Sprites.chargeBarImage[i] = new Texture(Gdx.files.internal("sprites/powerups/chargeBar/chargeBar" + i + ".png"));
        }

        Sprites.chargeBarContainerImage = new Texture(Gdx.files.internal("sprites/powerups/chargeBar/chargeBarContainer.png"));

        Sprites.archievementsButtonMenuImage = new Texture[2];
        Sprites.archievementsButtonMenuImage[0] = new Texture(Gdx.files.internal("sprites/buttons/archievementsButtonMenu.png"));
        Sprites.archievementsButtonMenuImage[1] = new Texture(Gdx.files.internal("sprites/buttons/archievementsButtonMenuTouched.png"));

        Sprites.optionsButtonMenuImage = new Texture[2];
        Sprites.optionsButtonMenuImage[0] = new Texture(Gdx.files.internal("sprites/buttons/optionsButtonMenu.png"));
        Sprites.optionsButtonMenuImage[1] = new Texture(Gdx.files.internal("sprites/buttons/optionsButtonMenuTouched.png"));

        Sprites.scoresButtonMenuImage = new Texture[2];
        Sprites.scoresButtonMenuImage[0] = new Texture(Gdx.files.internal("sprites/buttons/scoresButtonMenu.png"));
        Sprites.scoresButtonMenuImage[1] = new Texture(Gdx.files.internal("sprites/buttons/scoresButtonMenuTouched.png"));

    }
    */

    public void loadSounds() {

        Sounds.explodeSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/explode.wav"));
        Sounds.enemyShootSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/enemyShoot.wav"));
        Sounds.playerHitSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/playerHitted.mp3"));

        Sounds.rotaryHoeSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/rotaryHoe.mp3"));
        Sounds.liveRestoredSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/liveRestored.mp3"));

        Sounds.attackCowSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/attackCow.mp3"));

        Sounds.dingSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/ding.mp3"));

        Sounds.bertolinaSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/bertolinaSound.mp3"));

        Sounds.chargeSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/charge.mp3"));

        Sounds.heavyEnemyShootSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/heavyEnemyShoot.mp3"));

    }


    public void loadMusic() {

        Musics.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music/background.mp3"));
        Musics.backgroundMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music/backgroundMenu.mp3"));

    }

    public void loadBackgrounds() {

        Backgrounds.backgroundMenuImage = new Texture(Gdx.files.internal("backgrounds/backgroundMenu.png"));
        Backgrounds.backgroundGameOver = new Texture(Gdx.files.internal("backgrounds/backgroundGameOver.png"));

        Backgrounds.backgroundIntro = new Texture(Gdx.files.internal("backgrounds/backgroundIntro.png"));

        Backgrounds.backgroundPowerUps = new Texture(Gdx.files.internal("backgrounds/powerUps.png"));

    }
}
