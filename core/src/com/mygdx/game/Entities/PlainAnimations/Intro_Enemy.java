package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Screens.Scr_Introduction;

/**
 * Created by 100VOL on 22/02/2017.
 */

public class Intro_Enemy extends PlainAnimation {


    //solo tiene una animacion
    private TextureRegion[] movingFrames;
    private PlainAnimation bertoRef;

    private boolean finished = false;
    private boolean soundPlayed = false;

    public Intro_Enemy(float x, float y) {

        vSpeed = -200;
        hSpeed = 0;

        this.x = x;
        this.y = y;

        width = 82;
        height = 82;

        this.FRAME_COLS = 4;
        this.FRAME_ROWS = 1;



        initAnimation();

    }

    public void draw() {

        if (!finished) {

            if (this.y <= 290) {
                if (!soundPlayed && Scr_Introduction.playDingSound) {
                    Sounds.dingSound.play();
                    soundPlayed = true;
                }
                vSpeed = 0;
            }
            if (bertoRef.getX() <= 100) {
                this.hSpeed = -300;
            }
            if (this.x + this.width <= 0) {
                this.hSpeed = 0;
            }

            if (bertoRef.getX() + bertoRef.getWidth() <= 0) {
                this.hSpeed = 200;
                this.vSpeed = 200;
                finished = true;
            }

        }

        // Moverlo simplemente.
        x += hSpeed * Gdx.graphics.getDeltaTime();
        y += vSpeed * Gdx.graphics.getDeltaTime();

        currentFrame = currentAnimation.getKeyFrame(stateTime, true);
        GameEngine.batch.draw(currentFrame, x, y, width, height);
        stateTime += Gdx.graphics.getDeltaTime() * 6;

    }

    @Override
    public boolean isFinished() {
        return false;
    }


    @Override
    public void initAnimation() {

        Sprite spr = Sprites.getSpriteByName("enemy_std")[0];
        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);

    }

    public void setRef(PlainAnimation bertoRef) {
        this.bertoRef = bertoRef;
    }

}
