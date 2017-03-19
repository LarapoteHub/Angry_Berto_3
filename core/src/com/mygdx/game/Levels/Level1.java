package com.mygdx.game.Levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 21/08/2016.
 */
public class Level1 extends Level {

    private int random;

    public Level1() {
        this.nPhases = 13;
        this.maxPhases = MathUtils.random(12, 18);
        this.name = "Level 1";
    }

    @Override
    public void runLevel() {

        if (!running) {
            running = true;

            runPhase();
        } else if (phase != nPhases && GameEngine.getEnemies().isEmpty()) {
            changePhase();
        } else if (phase == nPhases && GameEngine.getBosses().isEmpty() && !victoryTriggered) {
            MyGdxGame.musicManager.setMusic(Musics.backgroundMusic);
            Timer.schedule(win, 0.5f, 0.5f);
            //
            GameEngine.getPlayer().setVictory(true);
            victoryTriggered = true;
        }




    }

    @Override
    public void runPhase() {
        if (MyGdxGame.DEBUG_MODE)
            // "phase" tiene otro significado comparado con "fase" en English.
            //"fouciño" tiene un sistema significado muy particular en gallego.
            System.out.println("FASE: " + phase);

        switch (phase) {


            case 0: //FASE 0 ***********************************************************************
                /*
                spawneamos 2 filas de enemigos estandar.
                 */



                GameEngine.spawnEnemy(78, 800, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 800, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(78, 880, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 880, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(78, 960, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 960, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);

                break;


            case 1: //FASE 1 ***********************************************************************

                GameEngine.spawnEnemy(78, 800, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 800, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(78, 880, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 880, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(78, 960, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 960, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(78, 1040, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 1040, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(78, 1120, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(422, 1120, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);

                break;

            case 2: //FASE 2 ***********************************************************************

                /*
                spawneamos varias filas de bolas de pinchos (SpikeBallEnemy) decidiendo de forma aleatoria
                un hueco entre las mismas.
                 */
                random = MathUtils.random(0, 1);

                if (random == 0) {

                    GameEngine.spawnEnemy(71.1f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(71.1f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(71.1f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                }
                if (random == 1) {

                    GameEngine.spawnEnemy(173.3f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(173.3f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(173.3f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                }

                if (random != 2) {

                    GameEngine.spawnEnemy(275.5f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(275.5f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(275.5f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                }

                if (random != 3) {
                    GameEngine.spawnEnemy(377.7f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(377.7f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 880, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(377.7f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                }


                break;

            case 3:
                /*
                spawneamos 3 filas de EvadingEnemy en el centro de la pantalla
                 */
                GameEngine.spawnEnemy(197, 800, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT);
                GameEngine.spawnEnemy(250, 800, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT);
                GameEngine.spawnEnemy(303, 800, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT);
                GameEngine.spawnEnemy(197, 880, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT);
                GameEngine.spawnEnemy(250, 880, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT);
                GameEngine.spawnEnemy(303, 880, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT);

                break;

            case 4:

                /*
                spawneamos EvadingEnemy y SpikeBallEnemy
                 */

                //GameEngine.addEntity(new SpikeBallEnemy(97, 800, -1), EntityType.ENEMY);

                GameEngine.spawnEnemy(97, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT);
                GameEngine.spawnEnemy(403, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT);

                GameEngine.spawnEnemy(197, 880, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT);
                GameEngine.spawnEnemy(303, 880, EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT);

                GameEngine.spawnEnemy(97, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT);
                GameEngine.spawnEnemy(403, 960, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT);

                break;

            case 5:

                /*
                spawneamos 2 filas de dos enemigos pesados
                 */

                GameEngine.spawnEnemy(108, 800, EnemyType.HEAVY_ENEMY, Enemy.Behavior.HeavyEnemy.DEFAULT);
                GameEngine.spawnEnemy(376, 800, EnemyType.HEAVY_ENEMY, Enemy.Behavior.HeavyEnemy.DEFAULT);
                GameEngine.spawnEnemy(108, 880, EnemyType.HEAVY_ENEMY, Enemy.Behavior.HeavyEnemy.DEFAULT);
                GameEngine.spawnEnemy(376, 880, EnemyType.HEAVY_ENEMY, Enemy.Behavior.HeavyEnemy.DEFAULT);

                break;

            case 6:

                GameEngine.spawnEnemy(250, 1500, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(250, 1400, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(250, 1300, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(250, 1200, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(250, 1100, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(250, 1000, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);
                GameEngine.spawnEnemy(250, 900, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_RIGHT);
                GameEngine.spawnEnemy(250, 800, EnemyType.STANDARD_ENEMY, Enemy.Behavior.StandardEnemy.ON_Y550_TURN_LEFT);

                break;

            case 7:

                GameEngine.spawnEnemy(380, 800, GameEngine.EnemyType.CORE_ORBIT_ENEMY, Enemy.Behavior.CoreOrbitEnemy.DEFAULT);
                GameEngine.spawnEnemy(128, 800, GameEngine.EnemyType.CORE_ORBIT_ENEMY, Enemy.Behavior.CoreOrbitEnemy.DEFAULT);

                break;

            case 8:

                GameEngine.spawnEnemy(71.1f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                GameEngine.spawnEnemy(122.2f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall

                GameEngine.spawnEnemy(173.3f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                GameEngine.spawnEnemy(224.4f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall

                GameEngine.spawnEnemy(275.5f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                GameEngine.spawnEnemy(326.6f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall

                GameEngine.spawnEnemy(377.7f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall
                GameEngine.spawnEnemy(428.8f, 800, EnemyType.SPIKE_BALL, Enemy.Behavior.SpikeBallEnemy.DEFAULT); //spikeBall

                break;

            case 9:

                GameEngine.spawnEnemy(88, 880, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT); //spikeBall
                GameEngine.spawnEnemy(186, 800, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_LEFT); //spikeBall
                GameEngine.spawnEnemy(314, 800, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT); //spikeBall
                GameEngine.spawnEnemy(412, 880, GameEngine.EnemyType.EVADING_ENEMY, Enemy.Behavior.EvadingEnemy.DODGE_PLAYER_RIGHT); //spikeBall

                break;

            case 10:

                /*
                spawneamos 2 filas de dos enemigos pesados
                 */

                GameEngine.spawnEnemy(108, 800, EnemyType.SHIELD_ENEMY, Enemy.Behavior.ShieldEnemy.DEFAULT);
                GameEngine.spawnEnemy(376, 800, EnemyType.SHIELD_ENEMY, Enemy.Behavior.ShieldEnemy.DEFAULT);

                break;

            case 11:

                /*
                spawneamos 2 filas de dos enemigos pesados
                 */

                GameEngine.spawnEnemy(108, 800, EnemyType.BARBEDWIRE, Enemy.Behavior.BarbedWireEnemy.DEFAULT);
                GameEngine.spawnEnemy(376, 1030, EnemyType.BARBEDWIRE, Enemy.Behavior.BarbedWireEnemy.DEFAULT);

                break;


            case 12:

                /*
                Alambradas
                 */

                GameEngine.spawnEnemy(108, 800, EnemyType.BARBEDWIRE, Enemy.Behavior.BarbedWireEnemy.DEFAULT);
                GameEngine.spawnEnemy(376, 800, EnemyType.BARBEDWIRE, Enemy.Behavior.BarbedWireEnemy.DEFAULT);

                break;

            //BOSS PHASE
            case 13:

                GameEngine.spawnBoss(78, 700, GameEngine.BossType.TYPE_1, Enemy.Behavior.Bosses.Boss1.DEFAULT); //boss1

                break;

        } //end switch

    }
}
