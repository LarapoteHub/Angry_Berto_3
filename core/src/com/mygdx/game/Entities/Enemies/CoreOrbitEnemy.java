package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

/**
 * Created by xldan on 20/02/2017.
 */

public class CoreOrbitEnemy extends Enemy {

    private ArrayList<SatelliteOrbitEnemy> satellites;

    public CoreOrbitEnemy(float x, float y, Behavior.CoreOrbitEnemy behavior) {
        super(x, y);

        this.vSpeed = -200;
        this.width = 48;
        this.height = 48;
        this.lives = 10;

        damage = 2;

        // Valor que sumar para la puntuacion
        scoreValue = 200;

        this.type = GameEngine.EnemyType.CORE_ORBIT_ENEMY;
        this.powerUpProb = 10;

        //initAnimation();

        canReboundX = true;
        canReboundY = true;

        satellites = new ArrayList<SatelliteOrbitEnemy>();
        SatelliteOrbitEnemy satellite = new SatelliteOrbitEnemy(this, Behavior.SatelliteOrbitEnemy.DEFAULT);
        GameEngine.getEnemies().add(satellite);
        satellites.add(satellite);
    }

    @Override
    public void draw() {

        // TODO Sustituir esto por copias de Sprites guardados localmente.
        if (hit) {
            tmpColor = GameEngine.batch.getColor();
            GameEngine.batch.setColor(Color.RED);
        }

        GameEngine.batch.draw(Sprites.getSpriteByName(SprNames.enemy_core_orbit.name())[0].getTexture(), x, y, width, height);

        if (hit) {
            GameEngine.batch.setColor(tmpColor);

            if (hitClock >= HITTED_TIME) {
                hitClock = 0;
                hit = false;
            }

            if (!GameEngine.gameState.isPaused()) {
                hitClock++;
            }

        }

    }

    @Override
    public void move() {
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
