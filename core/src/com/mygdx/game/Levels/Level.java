package com.mygdx.game.Levels;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameEngine;

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

    public Level() {
        running = false;
        finished = false;
        phase = 0;

        //this.player = gameEngineInstance.getPlayer();
    }

    public boolean isRunned() {
        return running;
    }

    public abstract void runLevel();

    public abstract void runPhase();

    protected void changePhase() {

        running = false;

        if (MathUtils.random(100) > 98) {
            GameEngine.changeUniverse(2);
            System.out.println("UNIVERSO CAMBIADO");
        }

        if (phasesCount >= maxPhases) {
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

