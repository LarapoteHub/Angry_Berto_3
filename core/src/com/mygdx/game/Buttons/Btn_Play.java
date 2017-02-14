package com.mygdx.game.Buttons;

import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Play extends Button {

//	initButtonBox = new Rectangle();
//    initButtonBox.width = 287;
//    initButtonBox.height = 81;
//    initButtonBox.x = 96; //96
//    initButtonBox.y = 166; //136
	
	public Btn_Play() {
		super(96, 166, 287, 81, Sprites.btn_init);
		this.name = "btn_Play";
	}

	@Override
	public void onTouch() {
		// TODO Implementar esto para probar el intro....
		//GameEngine.gameState.showIntro();
		// ESTO NO SE EJECUTA
		// WTF, MAN??
		System.out.println("HALLO");
		GameEngine.gameState.play();

	}

	public void onRelease() {
		GameEngine.gameState.play();
	}
}
