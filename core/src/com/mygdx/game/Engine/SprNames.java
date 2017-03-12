package com.mygdx.game.Engine;

/**
 * Created by Red Mercy on 3/12/2017.
 */


// NOTA: SI SE AÑADE AQUI UN SPRITE, HACER QUE COINCIDA LA NOMENCLATURA CON LA CLASE!!!!
public enum SprNames {

    // Les Botones.
    btn_pause("sprites/buttons/pauseButton.png", "sprites/buttons/playButton.png"),
    btn_skipIntro("sprites/buttons/skipButton.png"),

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
    gameWin("sprites/others/gameWin.png"),
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

    SprNames(String... path) {
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }
}
