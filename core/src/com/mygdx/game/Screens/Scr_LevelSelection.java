package com.mygdx.game.Screens;

import com.mygdx.game.Buttons.Btn_Exit;
import com.mygdx.game.Buttons.Btn_LevelSelection;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Levels.Level0;
import com.mygdx.game.Levels.LevelDie;
import com.mygdx.game.Levels.LevelTest;
import com.mygdx.game.MyGdxGame;

/**
 * Created by xldan on 25/02/2017.
 */

public class Scr_LevelSelection implements Screen {

    @Override
    public void initComponents() {

        GameEngine.addButton(new Btn_LevelSelection(350, 200, "btn_level0", new Level0()));
        //super(350, 80, 80, 50, spr);

        GameEngine.addButton(new Btn_LevelSelection(100, 100, "btn_level_test", new LevelTest()));

        GameEngine.addButton(new Btn_LevelSelection(40, 500, "btn_level_die", new LevelDie()));

        GameEngine.addButton(new Btn_Exit(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 5));

    }
}
