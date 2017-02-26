package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 17/08/2016.
 */
public class Explosion extends PlainAnimation {


    private TextureRegion[] explodingFrames;
    private Animation explodingAnimation;

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
        //System.out.println("Exp at :" + x + "  " + y + " - " + width + "  " + height);
        GameEngine.batch.draw(currentFrame, x, y, width, height);

        if (!GameEngine.gameState.isPaused()) {
            stateTime += Gdx.graphics.getDeltaTime() * 19;
        }

    }

    @Override
    public boolean isFinished() {
        return explodingAnimation.isAnimationFinished(stateTime);
    }

    public void initAnimation() {

        //Sprites.getSpriteByName("explosion").explosion.setBounds(0, 0, Sprites.explosion.getTexture().getWidth(), Sprites.explosion.getTexture().getHeight());

        //Sprites.getSpriteByName("explosion").setPosition(x, y);


        tmp = Sprites.getSpriteByName("explosion")[0].split(Sprites.getSpriteByName("explosion")[0].getTexture(), (int) Sprites.getSpriteByName("explosion")[0].getWidth() / FRAME_COLS, (int) Sprites.getSpriteByName("explosion")[0].getHeight() / FRAME_ROWS);
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
