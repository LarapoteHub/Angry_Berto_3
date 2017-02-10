package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 17/08/2016.
 */
public class Explosion extends Entity {

    private float x;
    private float y;

    public boolean remove;

    private TextureRegion[] explodingFrames;
    private TextureRegion currentFrame;

    private Animation explodingAnimation, currentAnimation;

    TextureRegion[][] tmp;

    private final int FRAME_COLS = 16;
    private final int FRAME_ROWS = 1;

    private float stateTime = 0f;

    public Explosion(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

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

        //GameEngine.batch.draw(Sprites.explosion[index].getTexture(), x, y, 56, 56);
        //Sprites.explosion.draw(GameEngine.batch);             //EL SEGUNDO PARAMETRO ES EL LOOP
        currentFrame = currentAnimation.getKeyFrame(stateTime, false);
        System.out.println("Exp at :" + x + "  " + y + " - " + width + "  " + height);
        GameEngine.batch.draw(currentFrame, x, y, width, height);
        stateTime += Gdx.graphics.getDeltaTime() * 6;

    }

    public void action() {

        if (explodingAnimation.isAnimationFinished(stateTime)) {
            remove = true;
        }


    }

    @Override
    public void kill() {

    }

    @Override
    public void decreaseLives(int lives) {
        // Doesn't have lives
    }

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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
