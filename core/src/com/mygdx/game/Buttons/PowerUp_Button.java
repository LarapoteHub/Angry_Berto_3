package com.mygdx.game.Buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class PowerUp_Button extends Button {

	protected int cost;
	
	public PowerUp_Button(float x, float y, float width, float height,
			String name) {
		super(x, y, width, height, name);
	}

	public int getCost() {
		return cost;
	}

}
