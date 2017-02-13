package com.mygdx.game.Levels;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 21/08/2016.
 */
public class Level0 extends Level {

    int movementIndex;
    int random;

    public Level0() {
        movementIndex = 0;
        this.nPhases = 7;
        this.bossPhase = 8;
        this.maxPhases = MathUtils.random(10, 15);
    }

    public void runLevel() {

        if (!running) {
            running = true;
            runPhase();
        } else if (phase != bossPhase && GameEngine.getEnemies().isEmpty()) {
            changePhase();
        } else if (phase == bossPhase && GameEngine.getBosses().isEmpty()) {
            changePhase();
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
                GameEngine.spawnEnemy(78, 800, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 800, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(78, 880, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 880, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(78, 960, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 960, EnemyType.STANDARD_ENEMY, 0);

                break;


            case 1: //FASE 1 ***********************************************************************

                GameEngine.spawnEnemy(78, 800, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 800, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(78, 880, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 880, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(78, 960, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 960, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(78, 1040, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 1040, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(78, 1120, EnemyType.STANDARD_ENEMY, 0);
                GameEngine.spawnEnemy(422, 1120, EnemyType.STANDARD_ENEMY, 0);

                break;

            case 2: //FASE 2 ***********************************************************************

                /*
                spawneamos varias filas de bolas de pinchos (SpikeBallEnemy) decidiendo de forma aleatoria
                un hueco entre las mismas.
                 */
                random = MathUtils.random(0, 1);

                if (random == 0) {

                    GameEngine.spawnEnemy(71.1f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(71.1f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(71.1f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                }
                if (random == 1) {

                    GameEngine.spawnEnemy(173.3f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(173.3f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(173.3f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                }

                if (random != 2) {

                    GameEngine.spawnEnemy(275.5f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(275.5f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(275.5f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                }

                if (random != 3) {
                    GameEngine.spawnEnemy(377.7f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(377.7f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 880, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(377.7f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 960, EnemyType.SPIKE_BALL, -1); //spikeBall
                }


                break;

            case 3:
                /*
                spawneamos 3 filas de EvadingEnemy en el centro de la pantalla
                 */
                GameEngine.spawnEnemy(197, 800, EnemyType.EVADING_ENEMY, -1);
                GameEngine.spawnEnemy(250, 800, EnemyType.EVADING_ENEMY, -1);
                GameEngine.spawnEnemy(303, 800, EnemyType.EVADING_ENEMY, -1);
                GameEngine.spawnEnemy(197, 880, EnemyType.EVADING_ENEMY, -1);
                GameEngine.spawnEnemy(250, 880, EnemyType.EVADING_ENEMY, -1);
                GameEngine.spawnEnemy(303, 880, EnemyType.EVADING_ENEMY, -1);

                break;

            case 4:

                /*
                spawneamos EvadingEnemy y SpikeBallEnemy
                 */

                //GameEngine.addEntity(new SpikeBallEnemy(97, 800, -1), EntityType.ENEMY);

                GameEngine.spawnEnemy(97, 800, EnemyType.SPIKE_BALL, -1);
                GameEngine.spawnEnemy(403, 800, EnemyType.SPIKE_BALL, -1);

                GameEngine.spawnEnemy(197, 880, EnemyType.EVADING_ENEMY, -1);
                GameEngine.spawnEnemy(303, 880, EnemyType.EVADING_ENEMY, -1);

                GameEngine.spawnEnemy(97, 960, EnemyType.SPIKE_BALL, -1);
                GameEngine.spawnEnemy(403, 960, EnemyType.SPIKE_BALL, -1);

                break;

            case 5:

                /*
                spawneamos 2 filas de dos enemigos pesados
                 */

                GameEngine.spawnEnemy(108, 800, EnemyType.HEAVY_ENEMY, -1);
                GameEngine.spawnEnemy(376, 800, EnemyType.HEAVY_ENEMY, -1);
                GameEngine.spawnEnemy(108, 880, EnemyType.HEAVY_ENEMY, -1);
                GameEngine.spawnEnemy(376, 880, EnemyType.HEAVY_ENEMY, -1);

                break;

            case 6:

                GameEngine.spawnEnemy(132, 800, EnemyType.STANDARD_ENEMY, 1);
                GameEngine.spawnEnemy(368, 800, EnemyType.STANDARD_ENEMY, 1);

                GameEngine.spawnEnemy(132, 880, EnemyType.STANDARD_ENEMY, 1);
                GameEngine.spawnEnemy(368, 880, EnemyType.STANDARD_ENEMY, 1);

                break;

            case 7:

                GameEngine.spawnEnemy(71.1f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                GameEngine.spawnEnemy(122.2f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                GameEngine.spawnEnemy(173.3f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                GameEngine.spawnEnemy(224.4f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                GameEngine.spawnEnemy(275.5f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                GameEngine.spawnEnemy(326.6f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                GameEngine.spawnEnemy(377.7f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                GameEngine.spawnEnemy(428.8f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                break;

            case 8:
                GameEngine.spawnBoss(78, 700, GameEngine.BossType.TYPE_1); //boss1

                break;

        } //end switch

    }
}
