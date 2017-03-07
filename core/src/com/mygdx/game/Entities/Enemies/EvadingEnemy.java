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
    private Behavior.EvadingEnemy behavior;
    private boolean behaviorStarted = false;

    public EvadingEnemy(float x, float y, Behavior.EvadingEnemy behavior) {
        super(x, y);
        this.vSpeed = -200; //200
        this.width = 48;
        this.height = 48;
        lives = 2;

        this.behavior = behavior;
        
        timerShoot = 50;
        
        score = 120;
        
        type = EnemyType.EVADING_ENEMY;
        
        powerUpProb = 15;

        this.FRAME_COLS = 2;
        this.FRAME_ROWS = 1;

        canReboundX = true;

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
            stateTime += Gdx.graphics.getDeltaTime() * animationSpeed;
        }

    }



    @Override
    public void runBehavior() {

        switch(behavior) {

            case DODGE_PLAYER_RIGHT:

                if (!behaviorStarted) {
                    float distance = this.y - 300;
                    Player p = GameEngine.getPlayer();

                    for (float point = this.y; point > distance; point--) {
                        if (point >= p.getY() && point <= p.getY() + p.getHeight() &&
                                this.x + (this.width / 2) >= p.getX() && this.x + (this.width / 2) <= p.getX() + p.getWidth()) {

                            this.hSpeed = 350;
                            this.vSpeed = -150;
                            behaviorStarted = true;

                        }
                    }
                }

                break;

            case DODGE_PLAYER_LEFT:
                if (!behaviorStarted) {
                    float distance = this.y - 300;
                    Player p = GameEngine.getPlayer();

                    for (float point = this.y; point > distance; point--) {
                        if (point >= p.getY() && point <= p.getY() + p.getHeight() &&
                                this.x + (this.width / 2) >= p.getX() && this.x + (this.width / 2) <= p.getX() + p.getWidth()) {

                            this.hSpeed = -350;
                            this.vSpeed = -150;
                            behaviorStarted = true;

                        }
                    }
                }

                break;
        }

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

        this.animationSpeed = 1;

        Sprite spr = Sprites.getSpriteByName("enemy_dodging")[0];
        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);

        //region OLD
        //Sprites.enemy_dodging.setBounds(0, 0, Sprites.enemy_dodging.getTexture().getWidth(), Sprites.enemy_dodging.getTexture().getHeight());

        /*
        movingFrames = new TextureRegion[FRAME_COLS];
        movingFrames = Sprites.getSpriteByName("enemy_dodging")[0].split(Sprites.getSpriteByName("enemy_dodging")[0].getTexture(), (int) Sprites.getSpriteByName("enemy_dodging")[0].getWidth() / FRAME_COLS, (int) Sprites.getSpriteByName("enemy_dodging")[0].getHeight() / FRAME_ROWS)[0];

        currentAnimation = new Animation(0.4f, movingFrames);
        */
        //endregion

    }


}
