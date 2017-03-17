package com.mygdx.game.Buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.Entities.Btn_Achievements_Anim;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

public class Btn_Achievements extends Button {

	/**
	 * Poner un boton de tipo Achievements en el 350, 20 con tamaño 110, 120 y su sprite correspondiente.
	 */
	// TODO Implement commented part in the future
	//private boolean disabled = false;

	public Btn_Achievements() {
		super(350, 20, 110, 120, SprNames.btn_achievements.name());
		this.name = SprNames.btn_achievements.name();
	}

	@Override
	public void onTouch() {
		// TODO Implement commented part in the future
		//if (!disabled) {
			GameEngine.addEntity(new Btn_Achievements_Anim(x, y, width, height), GameEngine.EntityType.OTHER);
			this.setRemove(true);

			//this.spr = Sprites.getSpriteByName(SprNames.btn_disabled.name());
		/*} else {

		}*/
	}
}
