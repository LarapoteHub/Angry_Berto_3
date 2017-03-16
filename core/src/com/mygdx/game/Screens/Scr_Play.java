package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Buttons.Btn_Mute;
import com.mygdx.game.Buttons.Btn_Switch_Bullet;
import com.mygdx.game.Buttons.Btn_Dog;
import com.mygdx.game.Buttons.Btn_Hoe;
import com.mygdx.game.Buttons.Btn_Pause;
import com.mygdx.game.Buttons.Btn_PlusHP;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;

public class Scr_Play implements Screen {

	@Override
	public void initComponents() {
		GameEngine.createPlayer();
		GameEngine.addButton(new Btn_Dog());
		GameEngine.addButton(new Btn_Hoe());
		GameEngine.addButton(new Btn_PlusHP());
		GameEngine.addButton(new Btn_Pause());
		GameEngine.addButton(new Btn_Switch_Bullet());

		//TEXTO DE LA SCORE:
		GameEngine.addText(new Text("", 78, 800, Color.GREEN));

		// TODO RedMercy - RuLoSp Was here
		GameEngine.addButton(new Btn_Mute());
	}

}
