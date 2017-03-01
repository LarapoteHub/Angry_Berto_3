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

    private static String[] texts;

    public Scr_Loading() {
        texts = new String[] {
                "VIRANDO NA CRIBA...",
                "EMPESANDO A MOER...",
                "REMOVENDO NO ESTERCO...",
                "ROZANDO NOS TOXOS...",
                "FACENDO NOS ARREDORES...",
                "SULFATANDO A RIBEIRA...",
                "ESPANTANDO NAS PEGAS...",
                "TIRÁNDOLLE ÓS MERLOS...",
                "AFALÁNDOLLES ÁS VACAS...",
                "CURANDO OS CHOURIZOS...",
                "SEMENTANDO AS PATACAS...",
                "FACENDO NOS REGOS...",
                "QUEIMANDO NO BULLO...",
                "VOTANDO ÓS POPULARES...",
                "PICANDO NA LEÑA...",
                "ABRAZANDO AS LEITUGAS...",
                "POÑENDO O PASTOR...",
                "MUNXINDO AS GHALIÑAS...",
                "TIRANDO DO BECERRO...",
                "BOTANDO O TOURO...",
                "CONFENSANDO CO CURA...",
                "ABRÍNDOLLE Ó PIPO...",
                "COCENDO A CACHUCHA...",
                "CEBANDO NOS PORCOS...",
                "BUSH ME PATACAIRA...",
                "AFIANDO NO FOUCIÑO..."};
    }

    @Override
    public void initComponents() {

        GameEngine.addText(new Text(texts[MathUtils.random(0,texts.length-1)], MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2, Color.GREEN).setCenterToPoint(true));
        GameEngine.addText(new Text("0%", MyGdxGame.WIDTH/2, 350, Color.WHITE).setCenterToPoint(true).setScale(2, 2));
    }

    public static String getRandomText() {
        return texts[MathUtils.random(0,texts.length-1)];
    }
}
