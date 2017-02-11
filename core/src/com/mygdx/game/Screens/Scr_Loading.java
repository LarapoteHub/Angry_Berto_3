package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;

/**
 * Created by SlowFolk on 2/11/2017.
 */

public class Scr_Loading implements Screen {
    @Override
    public void initComponents() {
        GameEngine.addText(new Text("LOADING", 160, 360, Color.GREEN));
        GameEngine.addText(new Text("0%", 160, 300, Color.WHITE));
    }
}
