package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.Multimedia.Sprites;

public class Btn_Options extends Button {

	public Btn_Options() {
		super(30, 20, 110, 120, SprNames.btn_options.name());
		this.name = SprNames.btn_options.name();
	}
	
	@Override
	public void onTouch() {
	}

}
