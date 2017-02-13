package com.mygdx.game.Levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;

/**
 * Created by 100VOL on 21/08/2016.
 */
public abstract class Level {

    Timer.Task spawnsTask;

    boolean running;
    public boolean finished;
    int phase;
    int nPhases;
    int bossPhase;
    int maxPhases;
    int phasesCount = 0;

    Player player;


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
            phase = bossPhase;
        } else {

            phase = MathUtils.random(0, nPhases);
            phasesCount++;
        }


    }

}

