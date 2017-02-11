package com.mygdx.game.Buttons;

import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

public class Btn_Options extends Button {

	/*
	 * optionsButtonBox = new Rectangle();
        optionsButtonBox.width = 110;
        optionsButtonBox.height = 120;
        optionsButtonBox.x = 30;
        optionsButtonBox.y = 20;
	 */
	
	public Btn_Options() {
		super(30, 20, 110, 120, Sprites.btn_options);
		this.name = "btn_Options";
	}
	
	@Override
	public void onTouch() {
		draw(1);
	}

}
