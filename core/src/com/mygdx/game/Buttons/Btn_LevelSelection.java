package com.mygdx.game.Buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entities.PlainAnimations.ScoreStar;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameData;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Levels.Level;

/**
 * Created by xldan on 25/02/2017.
 */

public class Btn_LevelSelection extends Button {

    private Level targetLevel;

    public Btn_LevelSelection(float x, float y, String name, Level targetLevel) {
        super(x, y, 80, 50, name);
        this.name = name;
        this.targetLevel = targetLevel;

        GameEngine.addText(new Text(targetLevel.getName(), x + (width/2), (y-10), Color.RED).setCenterToPoint(true).setScale(0.8f, 0.8f));
        if (GameData.existLevel(targetLevel.getName())) {
            GameEngine.addText(new Text(GameData.getHighscore(targetLevel.getName())+"", x + (width / 2), (y - 40), Color.GREEN).setCenterToPoint(true).setScale(0.8f, 0.8f));
        }

    }

    @Override
    public void onTouch() {
        GameEngine.levelManager.setLevel(targetLevel);
        GameEngine.gameState.play();
    }
}
