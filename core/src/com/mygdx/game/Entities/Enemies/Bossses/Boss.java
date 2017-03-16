package com.mygdx.game.Entities.Enemies.Bossses;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 12/5/2016.
 */
// TODO Implement this
public abstract class Boss extends Enemy {
    public Boss(float x, float y) {
        super(x, y);
    }

    @Override
    public abstract void initAnimation();


}
