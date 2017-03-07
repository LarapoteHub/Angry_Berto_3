package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 17/08/2016.
 */
public class Explosion extends PlainAnimation {

    public Explosion(float x, float y, float width, float height, boolean sound) {

        remove = true;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        //this.FRAME_COLS = 25;
        this.FRAME_COLS = 19;
        this.FRAME_ROWS = 1;

        if (sound) {
            Sounds.explodeSound.play();
        }

        //iniciamos la animación.
        initAnimation();
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    @Override
    public void draw() {

        //EL SEGUNDO PARAMETRO ES EL LOOP
        currentFrame = currentAnimation.getKeyFrame(stateTime, false);
        //System.out.println("Exp at :" + x + "  " + y + " - " + width + "  " + height);
        GameEngine.batch.draw(currentFrame, x, y, width, height);

        // Por que hay un x19 y despues un x0.4??
        if (!GameEngine.gameState.isPaused()) {
            stateTime += Gdx.graphics.getDeltaTime() * 19;
        }

    }

    @Override
    public boolean isFinished() {
        return currentAnimation.isAnimationFinished(stateTime);
    }

    public void initAnimation() {

        // TODO REDUCIDO A 1 LINEA (2 por comodidad)

        Sprite spr = Sprites.getSpriteByName("explosion")[0];

        currentAnimation = new AnimationAdapter(0.4f, AnimationAdapter.splitSheet(spr, FRAME_COLS, FRAME_ROWS), Animation.PlayMode.NORMAL);

    }
}
