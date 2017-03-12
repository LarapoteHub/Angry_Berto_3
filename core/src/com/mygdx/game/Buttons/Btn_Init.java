package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Init extends Button {

	public Btn_Init() {
		super(96, 166, 287, 81, SprNames.btn_init.name());
		this.name = SprNames.btn_init.name();
	}

	@Override
	public void onTouch() {
		GameEngine.gameState.showIntro();
		// Shortcut para el PLAY
		//GameEngine.gameState.play();
	}
}
