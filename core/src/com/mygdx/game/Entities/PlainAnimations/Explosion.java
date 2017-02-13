package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 17/08/2016.
 */
public class Explosion extends PlainAnimation {


    private Animation explodingAnimation;

    private float stateTime = 0f;

    public Explosion(float x, float y, float width, float height) {

        remove = true;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.FRAME_COLS = 25;
        this.FRAME_ROWS = 1;

        //iniciamos la animación.
        initAnimation();

        //action();
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
        System.out.println("Exp at :" + x + "  " + y + " - " + width + "  " + height);
        GameEngine.batch.draw(currentFrame, x, y, width, height);
        stateTime += Gdx.graphics.getDeltaTime() * 25;

    }

    /*
    public void action() {






    }

    @Override
    public void kill() {
        remove = true;
    }
    */

    @Override
    public void decreaseLives(int lives) {
        // Doesn't have lives
    }

    @Override
    public boolean isFinished() {
        return explodingAnimation.isAnimationFinished(stateTime);
    }

    public void initAnimation() {

        Sprites.explosion.setBounds(0, 0, Sprites.explosion.getTexture().getWidth(), Sprites.explosion.getTexture().getHeight());

        Sprites.explosion.setPosition(x, y);


        tmp = Sprites.explosion.split(Sprites.explosion.getTexture(), (int) Sprites.explosion.getWidth() / FRAME_COLS, (int) Sprites.explosion.getHeight() / FRAME_ROWS);
        explodingFrames = new TextureRegion[FRAME_COLS];

        explodingFrames = tmp[0];

        /*for (int i = 0 ; i < FRAME_COLS ; i++) {
            explodingFrames[i] = tmp[0][i];
        }*/

        explodingAnimation = new Animation(0.4f, explodingFrames);
        explodingAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        currentAnimation = explodingAnimation;


    }
}
