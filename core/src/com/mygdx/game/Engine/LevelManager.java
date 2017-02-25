package com.mygdx.game.Engine;

import com.mygdx.game.Levels.Level;
import com.mygdx.game.Levels.Level0;

/**
 * Created by xldan on 25/02/2017.
 */

public class LevelManager {

    private Level currentLevel;

    public LevelManager(){
        //incializamos con un nivel, para evitar NullPointers de entrada.
        currentLevel = new Level0();
    }

    public void setLevel(Level newLevel) {
        this.currentLevel = newLevel;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

}
