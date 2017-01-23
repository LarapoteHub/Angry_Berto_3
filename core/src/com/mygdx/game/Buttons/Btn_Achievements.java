package com.mygdx.game.Buttons;

import com.mygdx.game.Multimedia.Sprites;

public class Btn_Achievements extends Button {
	/*
	 * archievementsButtonBox = new Rectangle();
        archievementsButtonBox.width = 110;
        archievementsButtonBox.height = 120;
        archievementsButtonBox.x = 350;
        archievementsButtonBox.y = 20;
	 */
	
	public Btn_Achievements() {
		super(350, 20, 110, 120, Sprites.btn_achievements);
		this.name = "btn_Achievements";
	}
	
	@Override
	public void onTouch() {

	}
}
