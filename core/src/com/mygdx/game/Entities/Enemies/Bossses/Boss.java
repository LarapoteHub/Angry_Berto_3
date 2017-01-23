package com.mygdx.game.Entities.Enemies.Bossses;

import com.mygdx.game.Entities.Enemies.Enemy;

/**
 * Created by Red Mercy on 12/5/2016.
 */
// TODO Implement this
public abstract class Boss extends Enemy {
    public Boss(float x, float y, int behavior) {
        super(x, y, behavior);
    }

    public abstract void updateBehaviour();


}
