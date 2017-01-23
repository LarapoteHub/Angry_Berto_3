package com.mygdx.game.Levels;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Player;

/**
 * Created by 100VOL on 21/08/2016.
 */
public abstract class Level {

    Timer.Task spawnsTask;

    boolean running;
    public boolean finished;
    int fase;

    Player player;



    public Level() {
        running = false;
        finished = false;
        fase = 0;

        //this.player = gameEngineInstance.getPlayer();
    }

    public boolean isRunned() {
        return running;
    }


    public abstract void runLevel() ;

    protected void changeFase(int fase) {

        running = false;
        this.fase = MathUtils.random(0,fase);


    }

}

