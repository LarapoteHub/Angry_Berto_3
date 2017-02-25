package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Entities.PlainAnimations.GameOver;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 19/08/2016.
 */
public class Scr_GameOver implements Screen {

	// Texture background;
	// Texture gameOverImage;
	int gameOverX;
	int gameOverY;
	boolean touched;
	boolean selected;

	//variable para controlar cuando aparece el texto "Tocuh the Screen".
	public static boolean textAdded = false;

	public Scr_GameOver() {

		textAdded = false;

		gameOverX = 90;
		gameOverY = 500;
		touched = true;
		selected = false;

	}

	public void draw() {

	}

	@Override
	public void initComponents() {

		try {

			GameEngine.addEntity(new GameOver(90, 850), GameEngine.EntityType.PLAIN_ANIMATION);

			GameEngine.addText(new Text("-- SCORE --", MyGdxGame.WIDTH/2, 400, Color.RED).setCenterToPoint(true).setScale(0.8f, 0.8f));
			GameEngine.addText(new Text("- " + GameEngine.getLastScore()+" -", MyGdxGame.WIDTH/2, 365, Color.GREEN).setCenterToPoint(true).setScale(1.5f, 1.5f));

		} catch (Exception ex) {
			System.out.println("Excepción en Scr_GameOver.");
			ex.printStackTrace();
		}

	}
}
