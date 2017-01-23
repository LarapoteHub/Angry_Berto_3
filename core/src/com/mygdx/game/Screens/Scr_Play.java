package com.mygdx.game.Screens;

import com.mygdx.game.Buttons.Button;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Buttons.Btn_Dog;
import com.mygdx.game.Buttons.Btn_Hoe;
import com.mygdx.game.Buttons.Btn_Pause;
import com.mygdx.game.Buttons.Btn_PlusHP;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Enemies.StandardEnemy;
import com.mygdx.game.GameEngine.EntityType;

public class Scr_Play implements Screen {

	@Override
	public void initComponents() {
		GameEngine.createPlayer();
		GameEngine.addButton(new Btn_Dog());
		GameEngine.addButton(new Btn_Hoe());
		GameEngine.addButton(new Btn_PlusHP());
		GameEngine.addButton(new Btn_Pause());
		//GameEngine.addEntity((Enemy) new StandardEnemy(10, 10, 0), EntityType.ENEMY);
		//printer.draw(batch, "SCORE: " + player.getScore(), 78, 800 - 10); // antes x=10
		GameEngine.addText(new Text("", 78, 800));
		
	}

}
