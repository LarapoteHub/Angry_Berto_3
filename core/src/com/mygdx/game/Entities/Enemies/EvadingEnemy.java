package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.GameEngine.EntityType;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.StandardEnemyShoot;

/**
 * Created by 100VOL on 09/08/2016.
 */
public class EvadingEnemy extends Enemy {


    /* DESCRIPCIÓN:
        enemigo que si detecta al jugador enfrente, se lanza hacia un lado aleatorio
        a una velocidad de 300, su velocidad vertical es de 250.
     */

    private TextureRegion[] movingFrames;

    public EvadingEnemy(float x, float y, int behavior) {
        super(x, y, behavior);
        vSpeed = -200; //200
        setWidth(48);
        setHeight(48);
        lives = 2;
        
        timerShoot = 50;
        
        score = 120;
        
        type = EnemyType.EVADING_ENEMY;
        
        powerUpProb = 15;

        this.FRAME_COLS = 2;
        this.FRAME_ROWS = 1;

        initAnimation();

    }

    
    public void draw () {
        // HIT es un verbo irregular...
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
        if(!GameEngine.gameState.isPaused()) {
            stateTime += Gdx.graphics.getDeltaTime();
        }

    }

    public void action(Player player) {

        if (x+24 >= player.getX() && x+24 <= player.getX()+56 && player.getY() >= y - 400 && hSpeed == 0) { //antes 48 el 100

            if (MathUtils.random(0, 1) == 0) {
            	hSpeed = 300;
            } else {
            	hSpeed = -300;
            }

        } //end if

        // le restamos a su Y las unidades por segundo de su vspeed aplicando la misma fórmula que a la nave. Lo mismo para su X y su hspeed;
        y -= this.vSpeed * Gdx.graphics.getDeltaTime();
        x += this.hSpeed * Gdx.graphics.getDeltaTime();
        //si el enemigo se sale de los márgenes, invertimos su velocidad.
        if (x <= 68 || x >= 480 - 32 ) //antes 0 donde el 68
            this.setHSpeed(-hSpeed);

        //usamos timerShoot para que entre disparo y disparo ocurran al menos 50 iteraciones
        if (timerShoot<50) {
            timerShoot++;

        }

        //probabilidad de que dispare. //990
        if (MathUtils.random(0, 1000) > 995 && !GameEngine.gameState.isPaused() && timerShoot == 50) {
            Sounds.enemyShootSound.play();
            engine.addEntity(new StandardEnemyShoot(this), EntityType.BULLET_ENEMY);
            timerShoot = 0;
        }

        //COMPORTAMIENTO
        runBehavior();

        // en caso de que el enemigo se salga de los márgenes de la pantalla por debajo, la eliminamos.
        if (y + 48 < 0) {
            remove = true;
        }

        if (getLives() <= 0) {

            Sounds.explodeSound.play();
            player.addScore(10);

            random = MathUtils.random(0,20);

            if (random == 5 || random == 8 || random == 2 || random == 4) {
                //spawnPowerUpLive(enemyContainer.getX(), enemyContainer.getY());
                charge = true;
            }


            remove = true;
        }

    } //end ACTION()

    @Override
    public void runBehavior() {

    }


	@Override
	public boolean canShoot() {
		return canShoot;
	}


	@Override
	public void shoot() {
		GameEngine.addEntity(new StandardEnemyShoot(this), EntityType.BULLET_ENEMY);
        canShoot = false;
	}

    @Override
    public void initAnimation() {

        Sprites.enemy_dodging.setBounds(0, 0, Sprites.enemy_dodging.getTexture().getWidth(), Sprites.enemy_dodging.getTexture().getHeight());

        movingFrames = new TextureRegion[FRAME_COLS];
        movingFrames = Sprites.enemy_dodging.split(Sprites.enemy_dodging.getTexture(), (int) Sprites.enemy_dodging.getWidth() / FRAME_COLS, (int) Sprites.enemy_dodging.getHeight() / FRAME_ROWS)[0];

        currentAnimation = new Animation(0.4f, movingFrames);

    }


}
