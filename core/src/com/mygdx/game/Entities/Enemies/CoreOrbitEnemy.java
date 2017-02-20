package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by xldan on 20/02/2017.
 */

public class CoreOrbitEnemy extends Enemy {

    public CoreOrbitEnemy(float x, float y) {
        super(x, y, -1);

        this.vSpeed = -200;
        //CUIDAO COLGAOS!!
        //this.hSpeed = 100;
        this.width = 48;
        this.height = 48;
        this.lives = 10;

        //this.setLivesOutsideScreen(true);

        // Implementado en otro lado. Usar cooldown
        // timerShoot = 50;
        //cooldown = 100;

        damage = 2;

        // Valor que sumar para la puntuacion
        score = 200;

        // ascending = true;
        // descending = false;

        this.type = GameEngine.EnemyType.CORE_ORBIT_ENEMY;
        this.powerUpProb = 10;

        //TODO, muy peligroso
        //this.cooldown = GameEngine.uni.getEnemyAttackSpeed();
        //this.lives = GameEngine.uni.getEnemyHPBuff() * lives;

        //this.FRAME_COLS = 4;
        //this.FRAME_ROWS = 1;

        //initAnimation();
    }

    @Override
    public void draw() {

        if (hitted) {
            tmpColor = GameEngine.batch.getColor();
            GameEngine.batch.setColor(Color.RED);
        }

        GameEngine.batch.draw(Sprites.enemy_core_orbit.getTexture(), x, y, width, height);

        if (hitted) {
            GameEngine.batch.setColor(tmpColor);

            if (hittedClock >= HITTED_TIME) {
                hittedClock = 0;
                hitted = false;
            }

            if (!GameEngine.gameState.isPaused()) {
                hittedClock++;
            }

        }

    }

    @Override
    public void move() {
        //super.move();

        // Moverlo simplemente.
        x += hSpeed * Gdx.graphics.getDeltaTime();
        y += vSpeed * Gdx.graphics.getDeltaTime();

        if ((this.y + this.height > MyGdxGame.HEIGHT && this.vSpeed > 0 || this.y < 0 && this.vSpeed < 0) && !canLiveOutsideScreen()) {
            vSpeed = -vSpeed;
        }
        if (x + width > MyGdxGame.WIDTH && hSpeed > 0) {
            x = MyGdxGame.WIDTH - width;
            hSpeed = -hSpeed;
        }
        if ( x < Backgrounds.backgroundPowerUps.getWidth() && hSpeed < 0){
            x = Backgrounds.backgroundPowerUps.getWidth();
            hSpeed = -hSpeed;
        }

        // Desactivar la habilidad de vivir fuera de la pantalla una vez que entre el area de renderizado
        // Este se usa para cuando generamos los enemigos.
        // Los enemigos se generan fuera de la pantalla con el booleano en true, y despues se desactiva para
        // ser destruidos al salir de la pantalla por debajo.
        if (this.y < MyGdxGame.HEIGHT && this.y + this.height > 0) {
            this.setLivesOutsideScreen(false);
        }
    }

    @Override
    public void initAnimation() {

    }

    @Override
    public void action(Player player) {

    }

    @Override
    public void runBehavior() {

    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void shoot() {

    }

}
