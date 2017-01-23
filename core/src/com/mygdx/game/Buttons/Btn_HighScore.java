package com.mygdx.game.Buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_HighScore extends Button {

	/*
	 * scoresButtonBox = new Rectangle();
        scoresButtonBox.width = 140;
        scoresButtonBox.height = 120;
        scoresButtonBox.x=170;
        scoresButtonBox.y=20;
	 */
	
    public Btn_HighScore() {
        super(170, 20, 140, 120, Sprites.btn_scores);
        this.name = "btn_HighScore";
    }

    @Override
    public void onTouch() {
    }
}
