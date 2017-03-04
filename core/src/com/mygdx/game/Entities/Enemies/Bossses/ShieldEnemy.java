package com.mygdx.game.Entities.Enemies.Bossses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.PlainAnimations.AnimationAdapter;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.StandardEnemyShoot;

/**
 * Created by xldan on 04/03/2017.
 */

public class ShieldEnemy extends Enemy {



    /* DESCRIPCIÓN:
        enemigo que si detecta al jugador enfrente, se lanza hacia un lado aleatorio
        a una velocidad de 300, su velocidad vertical es de 250.
     */

    private TextureRegion[] movingFrames;
    private Enemy.Behavior.ShieldEnemy behavior;
    private boolean behaviorStarted = false;
    private boolean shielded = false;
    private Timer.Task shieldTimer;

    private Sprite shieldSpr;

    public ShieldEnemy(float x, float y, Enemy.Behavior.ShieldEnemy behavior) {
        super(x, y);
        this.vSpeed = -200; //200
        this.width = 72;
        this.height = 72;

        //chéganlle ben
        lives = 10;

        this.behavior = behavior;

        timerShoot = 50;

        score = 120;

        type = GameEngine.EnemyType.SHIELD_ENEMY;

        powerUpProb = 15;

        this.FRAME_COLS = 4;
        this.FRAME_ROWS = 1;

        canReboundX = true;

        shieldSpr = new Sprite(Sprites.getSpriteByName("enemy_shield__shield")[0]);
        shieldSpr.setSize(width, height);

        initShieldTimer();

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

        if (shielded) {
            GameEngine.batch.draw(shieldSpr, x, y, width, height);
        }

        if(!GameEngine.gameState.isPaused()) {
            stateTime += Gdx.graphics.getDeltaTime();
        }

    }

    //sobreescribimos esto para evitar que con el escudo reciba daño.
    @Override
    public void decreaseLives(float damage) {
        if (!shielded) {
            super.decreaseLives(damage);
            hit = true;
        }
    }

    @Override
    public void runBehavior() {

    }


    @Override
    public boolean canShoot() {
        return canShoot;
    }


    @Override
    public void shoot() {
        GameEngine.addEntity(new StandardEnemyShoot(this), GameEngine.EntityType.BULLET_ENEMY);
        canShoot = false;
    }

    @Override
    public void initAnimation() {

        Sprite spr = Sprites.getSpriteByName("enemy_shield")[0];
        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);

    }

    private void initShieldTimer() {

        shieldTimer = new Timer.Task() {

            @Override
            public void run() {
                if (shielded) {
                    shielded = false;
                } else {
                    shielded = true;
                }
            }
        };

        Timer.schedule(shieldTimer, 1.5f, 1.5f);

    }




}
