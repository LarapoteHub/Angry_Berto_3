package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;

public class Btn_Achievements extends Button {

	/**
	 * Poner un boton de tipo Achievements en el 350, 20 con tamaño 110, 120 y su sprite correspondiente.
	 */
	public Btn_Achievements() {
		super(350, 20, 110, 120, SprNames.btn_achievements.name());
		this.name = SprNames.btn_achievements.name();
	}
	
	@Override
	public void onTouch() {

	}
}
