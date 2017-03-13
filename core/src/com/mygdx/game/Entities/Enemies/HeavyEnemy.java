package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.PlainAnimations.AnimationAdapter;
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

    private Behavior.HeavyEnemy behavior;

    public HeavyEnemy(float x, float y, Behavior.HeavyEnemy behavior) {
        super(x, y);
        vSpeed = -150;
        this.width = 64;
        this.height = 64;
        // Doble de vida.... armadura OP
        lives *= 2;
        // TODO TEST TEST TEST TEST //
        lives = (int) Math.ceil(lives * GameEngine.uni.getEnemyHPBuff());
        setAttackSpeed(GameEngine.uni.getEnemyAttackSpeed(EnemyType.HEAVY_ENEMY));
        // TODO TEST TEST TEST TEST //

        this.behavior = behavior;

        scoreValue = 150;


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
            stateTime += Gdx.graphics.getDeltaTime() * animationSpeed;
        }

    }

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

        this.animationSpeed = 1;

        Sprite spr = Sprites.getSpriteByName("enemy_heavy")[0];
        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);


    }
}
