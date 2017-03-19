package com.mygdx.game.Screens;

import com.mygdx.game.Buttons.Btn_Exit;
import com.mygdx.game.Buttons.Btn_LevelSelection;
import com.mygdx.game.Buttons.Btn_Mute;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Levels.Level1;
import com.mygdx.game.Levels.LevelSurvival;
import com.mygdx.game.MyGdxGame;

/**
 * Created by xldan on 25/02/2017.
 */

public class Scr_LevelSelection implements Screen {

    @Override
    public void initComponents() {

        GameEngine.addButton(new Btn_LevelSelection(350, 200, "btn_level0", new Level1()));
        //super(350, 80, 80, 50, spr);


        // PRUEBA
        GameEngine.addButton(new Btn_LevelSelection(100, 150, "btn_level_survival", new LevelSurvival()));

        // TODO Hecho para que los de la distribucion NO se lien, tiene pasado xD
        //GameEngine.addButton(new Btn_LevelSelection(100, 100, "btn_level_test", new LevelTest()));


        //GameEngine.addButton(new Btn_LevelSelection(40, 500, "btn_level_die", new LevelDie()));

        GameEngine.addButton(new Btn_Exit(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 5));

        // TODO RedMercy - RuLoSp Was here
        GameEngine.addButton(new Btn_Mute());

    }
}
