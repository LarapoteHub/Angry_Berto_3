package com.mygdx.game.Levels;

import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by xldan on 04/03/2017.
 */

public class LevelDie extends Level {

    public LevelDie() {
        this.name = "Level Die";
    }

    @Override
    public void runLevel() {

        if (!running) {
            for (int i = 0; i < 30; i++) {
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 800, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 700, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 600, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 500, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 400, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 300, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 200, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
                GameEngine.spawnBoss(MyGdxGame.WIDTH / 2 - 100, 100, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT);
            }
            running = true;
        } else if (GameEngine.getEnemies().isEmpty() && GameEngine.getBosses().isEmpty()) {
            running = false;
        }

    }

    @Override
    public void runPhase() {

    }
}
