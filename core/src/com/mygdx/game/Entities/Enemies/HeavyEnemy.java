package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.GameEngine.EntityType;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.HeavyEnemyShoot;

/**
 * Created by 100VOL on 09/08/2016.
 * Modded by RedMercy since then.
 */
public class HeavyEnemy extends Enemy {

    /* DESCRIPCIÓN:
        - enemigo pesado
        - lento
        - dispara grandes proyectiles (hacen pupa)
     */

    private TextureRegion[] movingFrames;

    public HeavyEnemy(float x, float y, int behavior) {
        super(x, y, behavior);
        vSpeed = -150;
        this.width = 64;
        this.height = 64;
        // Doble de vida.... armadura OP
        lives *= 2;
        // TODO TEST TEST TEST TEST //
        lives = (int) Math.ceil(lives * GameEngine.uni.getEnemyHPBuff());
        setAttackSpeed(GameEngine.uni.getEnemyAttackSpeed(EnemyType.HEAVY_ENEMY));
        // TODO TEST TEST TEST TEST //

        score = 150;


        type = EnemyType.HEAVY_ENEMY;

        powerUpProb = 50;

        this.FRAME_COLS = 2;
        this.FRAME_ROWS = 1;

        initAnimation();

    }

    @Override
    public void draw() {

        if (hit) {
            tmpColor = GameEngine.batch.getColor();
            GameEngine.batch.setColor(Color.RED);
        }

        currentFrame = currentAnimation.getKeyFrame(stateTime, true);
        GameEngine.batch.draw(currentFrame, x, y, width, height);

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
        if (!GameEngine.gameState.isPaused()) {
            stateTime += Gdx.graphics.getDeltaTime();
        }

    }

//    public void action(Player player) {
//
//
//        //PRUEBAS (ZIG ZAG)
//        if (originalX == x && hSpeed == 0) {
//
//        	hSpeed = -50;
//
//        }
//
//        if (x < (originalX - 40)) {
//
//            hSpeed = 50;
//
//        }
//
//        if (x > (originalX+40)) {
//
//            hSpeed = -50;
//        }
//        //************************
//
//        // le restamos a su Y las unidades por segundo de su vspeed aplicando la misma fórmula que a la nave. Lo mismo para su X y su hspeed;
//        y -= vSpeed * Gdx.graphics.getDeltaTime();
//        x += hSpeed * Gdx.graphics.getDeltaTime();
//        //si el enemigo se sale de los márgenes, invertimos su velocidad.
//        if (x <= 68 || x >= 480 - getWidth() ) {
//            this.setHSpeed(-hSpeed);
//        }
//
//        //usamos timerShoot para que entre disparo y disparo ocurran al menos 100 iteraciones
//        if (timerShoot<150) {
//            timerShoot++;
//
//        }
//
//
//
//            if (MathUtils.random(0, 1000) > 990 && !GameEngine.gameState.isPaused() && timerShoot >= 150) {
//
//                timerShoot = 0;
//                Sounds.heavyEnemyShootSound.play();
//                initShoot();
//
//            }
//
//
//        //COMPORTAMIENTO
//        runBehavior();
//
//
//        // en caso de que el enemigo se salga de los márgenes de la pantalla por debajo, la eliminamos.
//        if (y + getHeight() < 0) {
//            remove = true;
//        }
//
//        //si una gota colisiona con la nave...
//        /*if (overlaps(player)) {
//            // reproducimos el sonido correspondiente...
//            Sounds.explodeSound.play();
//            // y la eliminamos de nuestro mundo.
//            kill();
//            player.decreaseLives(2); //si este enemigo choca con el jugador le bajaremos 2 de vida
//
//        }*/
//
//        if (getLives() <= 0) {
//
//            Sounds.explodeSound.play();
//            player.addScore(10);
//
//            random = MathUtils.random(0,20);
//
//            if (random == 5 || random == 8 || random == 2) {
//                //spawnPowerUpLive(enemyContainer.getX(), enemyContainer.getY());
//                charge = true;
//            }
//
//            remove = true;
//
//            //spawnExplosion(enemyContainer.getX() + 6, enemyContainer.getY() + 6);
//            //iterEnemys.remove();
//        }
//
//    } //end ACTION()

    @Override
    public void runBehavior() {

    }

    //creamos un disparo
    public void shoot() {
        // 50% probabilidad de que dispare
        int a = MathUtils.random(0, 100);
        if (a < 70) {
            GameEngine.addEntity(new HeavyEnemyShoot(this), EntityType.BULLET_ENEMY);
        } else {
            // Retraso en caso de que no dispare.
            CDCount -= MathUtils.random(0.15f, 0.45f);
        }
        canShoot = false;
    }


    @Override
    public boolean canShoot() {
        return canShoot;
    }

    @Override
    public void initAnimation() {

        //Sprites.enemy_heavy.setBounds(0, 0, Sprites.enemy_heavy.getTexture().getWidth(), Sprites.enemy_heavy.getTexture().getHeight());

        movingFrames = new TextureRegion[FRAME_COLS];

        movingFrames = Sprites.getSpriteByName("enemy_heavy")[0].split(Sprites.getSpriteByName("enemy_heavy")[0].getTexture(), (int) Sprites.getSpriteByName("enemy_heavy")[0].getWidth() / FRAME_COLS, (int) Sprites.getSpriteByName("enemy_heavy")[0].getHeight() / FRAME_ROWS)[0];

        currentAnimation = new Animation(0.4f, movingFrames);

    }
}
