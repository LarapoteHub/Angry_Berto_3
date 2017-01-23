package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.Engine.GameState;
import com.mygdx.game.GameEngine.EntityType;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.HeavyEnemyShoot;
import com.mygdx.game.MyGdxGame;

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
    //variable para crear zigzag
    private float originalX;
    Timer.Task shootTask;

    public HeavyEnemy(float x, float y, int behavior) {
        super(x, y, behavior);
        vSpeed = -150;
        setWidth(64);
        setHeight(64);
    	// TODO TEST TEST TEST TEST //
        lives = lives * 2 * GameEngine.uni.getEnemyHPBuff();
        timerShoot = 150 / GameEngine.uni.getEnemyAttackSpeed();
    	// TODO TEST TEST TEST TEST //
        
        score = 150;
        
        cooldown = 150;
        
        type = EnemyType.HEAVY_ENEMY;

        powerUpProb = 50;
        
    }

    @Override
    public void draw () {
    	
        GameEngine.batch.draw(Sprites.enemy_heavy[index].getTexture(), x, y, 64, 64);

    }


    protected void playAnimation() {

        animation = new Timer.Task(){
            @Override
            public void run() {

                if (index==0) {
                    index = 1;
                } else {
                    index = 0;
                }

            }

        };

        //cada 0.1 segundos incrementamos el índice de la imagen.
        Timer.schedule(animation,0.2f, 0.2f);

    } //end playAnimation()

    public void action(Player player) {


        //PRUEBAS (ZIG ZAG)
        if (originalX == x && hSpeed == 0) {

        	hSpeed = -50;

        }

        if (x < (originalX - 40)) {

            hSpeed = 50;

        }

        if (x > (originalX+40)) {

            hSpeed = -50;
        }
        //************************

        // le restamos a su Y las unidades por segundo de su vspeed aplicando la misma fórmula que a la nave. Lo mismo para su X y su hspeed;
        y -= vSpeed * Gdx.graphics.getDeltaTime();
        x += hSpeed * Gdx.graphics.getDeltaTime();
        //si el enemigo se sale de los márgenes, invertimos su velocidad.
        if (x <= 68 || x >= 480 - getWidth() ) {
            this.setHSpeed(-hSpeed);
        }

        //usamos timerShoot para que entre disparo y disparo ocurran al menos 100 iteraciones
        if (timerShoot<150) {
            timerShoot++;

        }



            if (MathUtils.random(0, 1000) > 990 && !GameEngine.gameState.isPaused() && timerShoot >= 150) {

                timerShoot = 0;
                Sounds.heavyEnemyShootSound.play();
                initShoot();

            }


        //COMPORTAMIENTO
        runBehavior();


        // en caso de que el enemigo se salga de los márgenes de la pantalla por debajo, la eliminamos.
        if (y + getHeight() < 0) {
            remove = true;
        }

        //si una gota colisiona con la nave...
        /*if (overlaps(player)) {
            // reproducimos el sonido correspondiente...
            Sounds.explodeSound.play();
            // y la eliminamos de nuestro mundo.
            kill();
            player.decreaseLives(2); //si este enemigo choca con el jugador le bajaremos 2 de vida

        }*/

        if (getLives() <= 0) {

            Sounds.explodeSound.play();
            player.addScore(10);

            random = MathUtils.random(0,20);

            if (random == 5 || random == 8 || random == 2) {
                //spawnPowerUpLive(enemyContainer.getX(), enemyContainer.getY());
                charge = true;
            }

            remove = true;

            //spawnExplosion(enemyContainer.getX() + 6, enemyContainer.getY() + 6);
            //iterEnemys.remove();
        }

    } //end ACTION()

    @Override
    public void runBehavior() {



    }

    //este método lanzará el método shoot() en 1 segundo
    public void initShoot() {
        /*shootTask  = new Timer.Task() {  @Override
        public void run() {

            shoot();

        }

        };

        //cada 0.06 segundos incrementamos el índice de la imagen.
        Timer.schedule(shootTask,1, 1 ,0);*/
    }

    //creamos un disparo
    public void shoot() {
        GameEngine.addEntity(new HeavyEnemyShoot(this), EntityType.BULLET_ENEMY);
        canShoot = false;

    }


	@Override
	public boolean canShoot() {
		return canShoot;
	}
}
