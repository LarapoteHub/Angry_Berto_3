package com.mygdx.game.Levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 21/08/2016.
 */
public abstract class Level {

    boolean running;
    public boolean finished;
    int phase;
    int nPhases;
    //int bossPhase;
    int maxPhases;
    int phasesCount = 0;
    protected String name = null;

    protected int enemiesSpawned = 0;
    protected int enemiesDestroyed = 0;

    protected boolean survivalMode = false;

    protected Timer.Task win = new Timer.Task() {
        @Override
        public void run() {

            if (GameEngine.getPlayer().getY() > MyGdxGame.HEIGHT) {
                GameEngine.getPlayer().setVictory(false);
                GameEngine.gameState.finishGame(true);
                win.cancel();
            }
        }
    };

    protected boolean victoryTriggered = false;

    public Level() {
        running = false;
        finished = false;
        phase = 0;

        //this.player = gameEngineInstance.getPlayer();
    }

    public abstract void runLevel();

    public abstract void runPhase();

    protected void changePhase() {

        running = false;

        if (MathUtils.random(100) > 98) {
            GameEngine.changeUniverse(2);
            System.out.println("UNIVERSO CAMBIADO");
        }
        // Modo survival añadido a mano. No hay spawn despues del boss sino...
        if (phasesCount >= maxPhases && !survivalMode) {
            phasesCount = 0;
            //cambiamos al boss (si nPhases es 11, la del boss sería las 11(la doceava)).
            phase = nPhases;
        } else {
            //si hay 11 fases, generariamos un random entre 0 y 10.
            phase = MathUtils.random(0, nPhases-1);
            phasesCount++;
        }


    }

    public String getName() {
        return name;
    }

    public int getEnemiesSpawned() {
        return this.enemiesSpawned;
    }

    public int getEnemiesDestroyed() {
        return this.enemiesDestroyed;
    }

    public void increaseSpawned() {
        this.enemiesSpawned++;
    }

    public void increaseEnemiesDestroyed() {
        this.enemiesDestroyed++;
    }
}

