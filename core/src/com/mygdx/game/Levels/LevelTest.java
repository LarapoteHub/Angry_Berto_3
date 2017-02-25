package com.mygdx.game.Levels;

import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 12/28/2016.
 */

public class LevelTest extends Level {
    public LevelTest() {
        this.name = "Level Test";
    }

    @Override
    public void runLevel() {
        if (!running) {
            //GameEngine.spawnBoss(78, 500, GameEngine.BossType.TYPE_1);
            GameEngine.spawnEnemy(MyGdxGame.WIDTH/2 - 24, 800, GameEngine.EnemyType.CORE_ORBIT_ENEMY, -1);
            running = true;
        }

    }

    @Override
    public void runPhase() {

    }
}
