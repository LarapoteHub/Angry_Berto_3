package com.mygdx.game.Levels;

import com.mygdx.game.Entities.Enemies.Enemy;
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

            /*
            GameEngine.spawnEnemy(88, 880, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT); //spikeBall
            GameEngine.spawnEnemy(186, 800, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT); //spikeBall
            GameEngine.spawnEnemy(314, 800, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT); //spikeBall
            GameEngine.spawnEnemy(412, 880, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT); //spikeBall
            */

            //GameEngine.spawnBoss(78, 700, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT); //boss1
            GameEngine.spawnEnemy(MyGdxGame.WIDTH/2, 800, GameEngine.EnemyType.CORE_ORBIT_ENEMY, Enemy.Behavior.CoreOrbitEnemy.DEFAULT);
            running = true;
        } else if (GameEngine.getEnemies().isEmpty() && GameEngine.getBosses().isEmpty()) {
            running = false;
        }

    }

    @Override
    public void runPhase() {

    }
}
