package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.PlainAnimations.AnimationAdapter;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 09/08/2016.
 */
public class SpikeBallEnemy extends Enemy {

    /* DESCRIPCIÓN:

     es una mina
     */

    private TextureRegion[] movingFrames;

    private Behavior.SpikeBallEnemy behavior;

    public SpikeBallEnemy(float x, float y, Behavior.SpikeBallEnemy behavior) {

        super(x, y);
        vSpeed = -550;
        setWidth(48);
        setHeight(48);
        lives = 3;
        
        scoreValue = 15;
        
        type = EnemyType.SPIKE_BALL;

        this.FRAME_COLS = 2;
        this.FRAME_ROWS = 1;

        initAnimation();

    }


    public void draw () {

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
            stateTime += Gdx.graphics.getDeltaTime() * animationSpeed;
        }

    }

    public void action(Player player) {

        // le restamos a su Y las unidades por segundo de su vspeed aplicando la misma fórmula que a la nave. Lo mismo para su X y su hspeed;
        y -= this.vSpeed * Gdx.graphics.getDeltaTime();
        x += this.hSpeed * Gdx.graphics.getDeltaTime();
        //si el enemigo se sale de los márgenes, invertimos su velocidad.
        if (x <= 68 || x >= 480 - 32 ) {
            this.setHSpeed(-hSpeed);
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

            random = MathUtils.random(0,10);

            if (random == 5 || random == 8 || random == 2) {
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
		return false;
	}


	@Override
	public void shoot() {
		// Se deja vacio, ya que son minas. NO DISPARAN... por ahora
		
	}

    @Override
    public void initAnimation() {

        this.animationSpeed = 1;

        Sprite spr = Sprites.getSpriteByName("enemy_spikeBall")[0];
        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);

    }

}
