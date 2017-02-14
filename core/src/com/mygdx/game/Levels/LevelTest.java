package com.mygdx.game.Levels;

import com.mygdx.game.GameEngine;

/**
 * Created by Red Mercy on 12/28/2016.
 */

public class LevelTest extends Level {
    public LevelTest() {

    }

    @Override
    public void runLevel() {
        if (!running) {
            GameEngine.spawnBoss(78, 500, GameEngine.BossType.TYPE_1);
            running = true;
        }

    }

    @Override
    public void runPhase() {

    }
}
