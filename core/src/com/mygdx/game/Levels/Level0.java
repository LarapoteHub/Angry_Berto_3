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
        this.nPhases = 8;
        this.bossPhase = 9;
        this.maxPhases = MathUtils.random(10, 15);
    }

    //run level
    public void runLevel() {
    	if (MyGdxGame.DEBUG_MODE)
            // "phase" tiene otro significado comparado con "fase" en English
            System.out.println("FASE: " + phase);

        switch (phase) {


            case 0: //FASE 0 ***********************************************************************
                /*
                spawneamos 2 filas de enemigos estandar.
                 */
                if (!running) {
                    running = true;


                    GameEngine.spawnEnemy(78, 800, EnemyType.STANDARD_ENEMY, 0);
                    GameEngine.spawnEnemy(422, 800, EnemyType.STANDARD_ENEMY, 0);
                    GameEngine.spawnEnemy(78, 880, EnemyType.STANDARD_ENEMY, 0);
                    GameEngine.spawnEnemy(422, 880, EnemyType.STANDARD_ENEMY, 0);
                    GameEngine.spawnEnemy(78, 960, EnemyType.STANDARD_ENEMY, 0);
                    GameEngine.spawnEnemy(422, 960, EnemyType.STANDARD_ENEMY, 0);

                    /*
                    GameEngine.spawnEnemy(78, 800, EnemyType.EVADING_ENEMY, 0);
                    GameEngine.spawnEnemy(422, 800, EnemyType.EVADING_ENEMY, 0);
                    GameEngine.spawnEnemy(78, 880, EnemyType.EVADING_ENEMY, 0);
                    GameEngine.spawnEnemy(422, 880, EnemyType.EVADING_ENEMY, 0);
                    GameEngine.spawnEnemy(78, 960, EnemyType.EVADING_ENEMY, 0);
                    GameEngine.spawnEnemy(422, 960, EnemyType.EVADING_ENEMY, 0);
                    */

                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();

                }
                break;


            case 1: //FASE 1 ***********************************************************************

                if (!running) {
                    running = true;

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


                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();
                    //fase++;
                    //running = false;

                } //end else

                break;

            case 2: //FASE 2 ***********************************************************************

                /*
                spawneamos varias filas de bolas de pinchos (SpikeBallEnemy) decidiendo de forma aleatoria
                un hueco entre las mismas.
                 */

                if (!running) {

                    running = true;

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

                    if (random!= 2) {

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


                } else if (GameEngine.getEnemies().isEmpty()) {

                    //fase++;
                    //running = false;
                    changePhase();
                }

                break;

            case 3:
                /*
                spawneamos 3 filas de EvadingEnemy en el centro de la pantalla
                 */
                if (!running) {
                    running = true;

                    GameEngine.spawnEnemy(197, 800, EnemyType.EVADING_ENEMY, -1);
                    GameEngine.spawnEnemy(250, 800, EnemyType.EVADING_ENEMY, -1);
                    GameEngine.spawnEnemy(303, 800, EnemyType.EVADING_ENEMY, -1);
                    GameEngine.spawnEnemy(197, 880, EnemyType.EVADING_ENEMY, -1);
                    GameEngine.spawnEnemy(250, 880, EnemyType.EVADING_ENEMY, -1);
                    GameEngine.spawnEnemy(303, 880, EnemyType.EVADING_ENEMY, -1);


                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();

                } //end else

                break;

            case 4:

                /*
                spawneamos EvadingEnemy y SpikeBallEnemy
                 */
                if (!running) {
                    running = true;
                    	
                    //GameEngine.addEntity(new SpikeBallEnemy(97, 800, -1), EntityType.ENEMY);
                    
                        GameEngine.spawnEnemy(97, 800, EnemyType.SPIKE_BALL, -1);
                        GameEngine.spawnEnemy(403,800, EnemyType.SPIKE_BALL, -1);

                        GameEngine.spawnEnemy(197, 880, EnemyType.EVADING_ENEMY, -1);
                        GameEngine.spawnEnemy(303, 880, EnemyType.EVADING_ENEMY, -1);

                        GameEngine.spawnEnemy(97, 960, EnemyType.SPIKE_BALL, -1);
                        GameEngine.spawnEnemy(403,960, EnemyType.SPIKE_BALL, -1);


                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();

                } //end else

                break;

            case 5:

                /*
                spawneamos 2 filas de dos enemigos pesados
                 */
                if (!running) {
                    running = true;

                    GameEngine.spawnEnemy(132, 800, EnemyType.HEAVY_ENEMY, -1);
                    GameEngine.spawnEnemy(416, 800, EnemyType.HEAVY_ENEMY, -1);
                    GameEngine.spawnEnemy(132, 880, EnemyType.HEAVY_ENEMY, -1);
                    GameEngine.spawnEnemy(416, 880, EnemyType.HEAVY_ENEMY, -1);


                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();

                } //end else

                break;

            case 6:

                if (!running) {
                    running = true;

                    GameEngine.spawnEnemy(132, 800, EnemyType.STANDARD_ENEMY, 1);
                    GameEngine.spawnEnemy(416, 800, EnemyType.STANDARD_ENEMY, 1);

                    GameEngine.spawnEnemy(132, 880, EnemyType.STANDARD_ENEMY, 1);
                    GameEngine.spawnEnemy(416, 880, EnemyType.STANDARD_ENEMY, 1);


                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();

                } //end else

                break;

            case 7:

                if (!running) {
                    running = true;
                    //TODO cagoenraul
                    //System.exit(7);
                    GameEngine.spawnEnemy(71.1f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(122.2f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                    GameEngine.spawnEnemy(173.3f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(224.4f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                    GameEngine.spawnEnemy(275.5f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(326.6f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                    GameEngine.spawnEnemy(377.7f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall
                    GameEngine.spawnEnemy(428.8f, 800, EnemyType.SPIKE_BALL, -1); //spikeBall

                } else if (GameEngine.getEnemies().isEmpty()) {

                    changePhase();

                } //end else

                break;
                
            case 8:
            	if (!running) {
            		running = true;
            		
            		GameEngine.changeUniverse(2);
                    changePhase();

                    System.out.println("UNIVERSO CAMBIADO");

            	}

            case 9:

                if (!running) {
                    running = true;
                    //GameEngine.spawnBoss(78, 500, GameEngine.BossType.TYPE_1); //boss1
                    GameEngine.spawnBoss(78, 700, GameEngine.BossType.TYPE_1); //boss1

                } else if (GameEngine.getBosses().isEmpty()) {

                    changePhase();

                } //end else

                break;

        } //end switch

    }
}
