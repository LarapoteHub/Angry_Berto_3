package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by SlowFolk on 2/11/2017.
 */

public class Scr_Loading implements Screen {

    private String[] texts;

    public Scr_Loading() {
        this.texts = new String[] {
                "VIRANDO NA CRIBA...",
                "EMPESANDO A MOER...",
                "REMOVENDO NO ESTERCO...",
                "ROZANDO NOS TOXOS...",
                "FACENDO NOS ARREDORES...",
                "SULFATANDO A RIBEIRA...",
                "ESPANTANDO NAS PEGAS...",
                "TIRÁNDOLLE ÓS MERLOS..."};
    }

    @Override
    public void initComponents() {

        GameEngine.addText(new Text(texts[MathUtils.random(0,texts.length)], MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2, Color.GREEN).setCenterToPoint(true));
        GameEngine.addText(new Text("0%", MyGdxGame.WIDTH/2, 300, Color.WHITE).setCenterToPoint(true));
    }
}
