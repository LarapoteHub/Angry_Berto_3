package com.mygdx.game.Entities.Enemies.Bossses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.PlainAnimations.AnimationAdapter;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 12/28/2016.
 */

public class Boss1 extends Boss {

    private int behaviorPhase;
    private int referenceY;
    private int actionTimer;

    private TextureRegion[] movingFrames;

    private Behavior.Bosses.Boss1 behavior;

    public Boss1(float x, float y, Behavior.Bosses.Boss1 behavior) {
        super(x, y, 0);
        vSpeed = -100; //-300 no se por que
        hSpeed = 0;

        this.behavior = behavior;

        width = 120;
        height = 96;
        // TODO Que es un boss, por dios
        lives = 200.0f;

        // Implementado en otro lado. Usar cooldown
        // timerShoot = 50;
        //cooldown = 100;

        damage = 2;

        // Valor que sumar para la puntuacion
        score = 100;

        // ascending = true;
        // descending = false;

        type = GameEngine.EnemyType.BOSS;

        powerUpProb = 10;

        //incializamos el comportamiento en la primera fase
        behaviorPhase = 0;
        referenceY = 600;
        actionTimer = 0;

        this.FRAME_COLS = 4;
        this.FRAME_ROWS = 1;

        canReboundX = true;
        canReboundY = true;

        MyGdxGame.musicManager.setMusic(Musics.boss1Music);

        initAnimation();
    }

    @Override
    public void updateBehaviour() {

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

        if(!GameEngine.gameState.isPaused()) {
            stateTime += Gdx.graphics.getDeltaTime() * 6;
        }



    }

    @Override
    public void action(Player player) {

    }

    @Override
    public void runBehavior() {
        //ANTES ESTABA EN EL MOVE, PERO DEJO DE SER NECESARIO IMPLEMENTARLO (POR AHORA)
        actionTimer++;
        //System.out.println("BOSS HSPEED: "+hSpeed+" VSPEED: "+vSpeed+" X: "+x+" Y: "+y);
        if (MyGdxGame.MOSTRAR_COMENTARIOS_CHORRA) {
            System.out.println("We need to build a BOSS");
            System.out.println("And some Universes");
            System.out.println("And it has to be build QUICKLY!!");
        }
        switch (behavior) {
            /*
            en funcion de si esta a la izquierda o a la derecha,
            le damos movimiento hacia un lado o hacia otro.
            */
            case DEFAULT:

                if (MathUtils.random(0, 1000) > 990) {

                    GameEngine.spawnEnemy(MathUtils.random(68, 432), 800, GameEngine.EnemyType.SPIKE_BALL, Behavior.SpikeBallEnemy.DEFAULT);
                }
                System.out.println(behaviorPhase + "  " + vSpeed + "  " + hSpeed);
                switch (behaviorPhase) {
                    case 0:
                        vSpeed = -100;
                        if (y <= referenceY) {
                            y = referenceY;
                            hSpeed = 400;
                            vSpeed = 0;
                            behaviorPhase++;

                        }
                        break;

                    case 1:
                        if (actionTimer >= Math.random() * 500 + 250) {

                            hSpeed = 0;
                            vSpeed = -400;
                            referenceY -= 144; //height * 1.5
                            if (referenceY <= 0) {
                                referenceY = 600;
                            }
                            actionTimer = 0;
                            behaviorPhase++;

                        }
                        break;

                    case 2:
                        if (y <= 0) {
                            y = 0;
                            vSpeed = 600;
                            behaviorPhase++;
                        }
                        break;

                    case 3:
                        if (y >= referenceY) {
                            y = referenceY;
                            hSpeed = 400;
                            vSpeed = 0;
                            behaviorPhase = 0;

                        }
                        break;
                } //end switch

                break;
        } //end switch

    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void initAnimation() {

        Sprite spr = Sprites.getSpriteByName("boss_1")[0];
        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);

        //region OLD

        /*
        movingFrames = new TextureRegion[FRAME_COLS];

        movingFrames = Sprites.getSpriteByName("boss_1")[0].split(Sprites.getSpriteByName("boss_1")[0].getTexture(), (int) Sprites.getSpriteByName("boss_1")[0].getWidth() / FRAME_COLS, (int) Sprites.getSpriteByName("boss_1")[0].getHeight() / FRAME_ROWS)[0];

        currentAnimation = new Animation(0.4f, movingFrames);
        //currentAnimation.setPlayMode(Animation.PlayMode.LOOP);
         */

        //endregion

    }
}
