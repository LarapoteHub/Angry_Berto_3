package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Buttons.Btn_LevelSelection;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by xldan on 25/02/2017.
 */

public class Scr_LevelSelection implements Screen {

    @Override
    public void initComponents() {

        GameEngine.addButton(new Btn_LevelSelection(Sprites.btn_level0, "btn_level0"));
        //super(350, 80, 80, 50, spr);
        GameEngine.addText(new Text("1", 350 + 40, 190, Color.RED).setCenterToPoint(true));

    }
}
