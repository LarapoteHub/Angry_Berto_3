package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Screens.Scr_Introduction;

/**
 * Created by 100VOL on 22/02/2017.
 */

public class Intro_Berto extends PlainAnimation {

    private PlainAnimation enemyRef;
    private boolean finished = false;

    private TextureRegion currentFrame, propulsionFrame;
    private  TextureRegion[] movingFrames, propulsionFrames;

    private Animation currentAnimation, propulsionAnimation;

    private int FRAME_COLS, FRAME_ROWS;
    private int FRAME_COLS_PROPULSION, FRAME_ROWS_PROPULSION;

    private float stateTime = 0f;
    private float propulsionStateTime = 0f;

    private boolean animationInitialized = false;
    private boolean soundPlayed = false;
    private boolean bertolinaSoundPlayed = false;

    public Intro_Berto(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 150;

        this.hSpeed = 0;
        this.vSpeed = 0;

        this.FRAME_COLS = 2;
        this.FRAME_ROWS = 1;
        this.FRAME_COLS_PROPULSION = 3;
        this.FRAME_ROWS_PROPULSION = 1;

    }

    @Override
    public void draw() {

        if (!finished) {
            if (this.x <= 100) {
                hSpeed = 0;
                if (!soundPlayed && Scr_Introduction.playBertoSound) {
                    Sounds.playerHitSound.play();
                    soundPlayed = true;
                }
            } else if (enemyRef.getY() <= 290) {
                hSpeed = -300;
            }

            if (enemyRef.getX() + enemyRef.getWidth() <= 0) {
                hSpeed = -200;
            }


            if (enemyRef.getX() > 200 && enemyRef.getY() > 400 && enemyRef.getHSpeed() > 0&& enemyRef.getVSpeed() > 0) {
                if (!bertolinaSoundPlayed && Scr_Introduction.playBertolinaSound) {
                    //por si las moscas
                    Sounds.bertolinaSound.setLooping(1, true);
                    Sounds.bertolinaSound.play();
                }
                this.hSpeed = 200;
                this.vSpeed = 200;
                finished = true;
            }

        }

        // Moverlo simplemente.
        x += hSpeed * Gdx.graphics.getDeltaTime();
        y += vSpeed * Gdx.graphics.getDeltaTime();

        if (!finished) {
            Sprites.getSpriteByName("berto")[0].setBounds(x, y, width, height);
            Sprites.getSpriteByName("berto")[0].draw(GameEngine.batch);
        } else {

            if (!animationInitialized) {
                initAnimation();
                animationInitialized = true;
            }

            currentFrame = currentAnimation.getKeyFrame(stateTime, true);
            propulsionFrame = propulsionAnimation.getKeyFrame(propulsionStateTime, true);

            GameEngine.batch.draw(currentFrame, x, y, 104, 104);

            GameEngine.batch.draw(
                    propulsionFrame,
                    x + 25, y - 32, 16, 32);
            GameEngine.batch.draw(
                    propulsionFrame,
                    x + 63, y - 32, 16, 32);


            stateTime += Gdx.graphics.getDeltaTime() * 3;
            propulsionStateTime += Gdx.graphics.getDeltaTime() * 6;

            if (this.y >= 800) {
                GameEngine.gameState.goToLevelSelection();
                Sounds.bertolinaSound.stop(1);
            }
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public void setRef(PlainAnimation enemyRef) {
        this.enemyRef = enemyRef;
    }

    @Override
    public void initAnimation() {

        // TODO Implementar AnimationAdapter!
        movingFrames = new TextureRegion[FRAME_COLS];

        movingFrames = Sprites.getSpriteByName("player")[0].split(Sprites.getSpriteByName("player")[0].getTexture(), (int) Sprites.getSpriteByName("player")[0].getWidth() / FRAME_COLS, (int) Sprites.getSpriteByName("player")[0].getHeight() / FRAME_ROWS)[0];

        currentAnimation = new Animation(0.4f, movingFrames);

        //Sprites.player_propulsion.setBounds(0, 0, Sprites.player_propulsion.getTexture().getWidth(), Sprites.player_propulsion.getTexture().getHeight());

        propulsionFrames = new TextureRegion[FRAME_COLS_PROPULSION];

        propulsionFrames = Sprites.getSpriteByName("player_propulsion")[0].split(Sprites.getSpriteByName("player_propulsion")[0].getTexture(), (int) Sprites.getSpriteByName("player_propulsion")[0].getWidth() / FRAME_COLS_PROPULSION, (int) Sprites.getSpriteByName("player_propulsion")[0].getHeight() / FRAME_ROWS_PROPULSION)[0];

        propulsionAnimation = new Animation(0.4f, propulsionFrames);

    }
}
