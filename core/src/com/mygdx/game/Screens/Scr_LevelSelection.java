package com.mygdx.game.Screens;

import com.mygdx.game.Buttons.Btn_LevelSelection;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Levels.Level0;
import com.mygdx.game.Levels.LevelTest;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by xldan on 25/02/2017.
 */

public class Scr_LevelSelection implements Screen {

    @Override
    public void initComponents() {

        GameEngine.addButton(new Btn_LevelSelection(350, 200, Sprites.btn_level0, "btn_level0", new Level0()));
        //super(350, 80, 80, 50, spr);

        GameEngine.addButton(new Btn_LevelSelection(100, 100, Sprites.btn_level0, "btn_level_test", new LevelTest()));

    }
}
