package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Buttons.Btn_Exit;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameData;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by xldan on 16/03/2017.
 */

public class Scr_Scores implements Screen {

    private final String[] levelsName = new String[]{"Level 1"};

    @Override
    public void initComponents() {
        GameEngine.addButton(new Btn_Exit(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 5));

        GameEngine.addText(new Text("-- Level 1 --", MyGdxGame.WIDTH/2, 700, Color.RED).setCenterToPoint(true));

        long[] scores = GameData.getScores(levelsName[0]);

        float y = 640;

        for (int i = 0 ; i < scores.length ; i++) {
            if (scores[i] == 0) {
                GameEngine.addText(new Text("-", MyGdxGame.WIDTH / 2, y, Color.GREEN).setCenterToPoint(true).setScale(0.8f, 0.8f));
            } else {
                GameEngine.addText(new Text(scores[i]+"", MyGdxGame.WIDTH / 2, y, Color.GREEN).setCenterToPoint(true).setScale(0.8f, 0.8f));
            }

            y-=60;
        }

    }
}
