package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entities.PlainAnimations.GameOver;
import com.mygdx.game.Entities.PlainAnimations.ScoreStar;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
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

	private int enemiesDestroyed;
	private int enemiesSpawned;
	private int enemiesDestroyedPercent;

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

			GameEngine.addText(new Text("-- SCORE --", MyGdxGame.WIDTH/2, 420, Color.RED).setCenterToPoint(true).setScale(0.8f, 0.8f));
			GameEngine.addText(new Text(GameEngine.getLastScore()+"", MyGdxGame.WIDTH/2, 385, Color.GREEN).setCenterToPoint(true).setScale(1.5f, 1.5f));

			drawScoreStars();


			/*
			GameEngine.addText(new Text("-- ENEMIES DESTROYED --", MyGdxGame.WIDTH/2, 320, Color.RED).setCenterToPoint(true).setScale(0.8f, 0.8f));
			GameEngine.addText(new Text(GameEngine.levelManager.getCurrentLevel().getEnemiesDestroyed() + " / " + GameEngine.levelManager.getCurrentLevel().getEnemiesSpawned(), MyGdxGame.WIDTH/2, 285, Color.GREEN).setCenterToPoint(true).setScale(1.5f, 1.5f));
			*/


		} catch (Exception ex) {
			System.out.println("Excepción en Scr_GameOver.");
			ex.printStackTrace();
		}

	}

	private void drawScoreStars() {
		enemiesDestroyed = GameEngine.levelManager.getCurrentLevel().getEnemiesDestroyed();
		enemiesSpawned = GameEngine.levelManager.getCurrentLevel().getEnemiesSpawned();

		enemiesDestroyedPercent = (enemiesDestroyed * 100) / enemiesSpawned;

		int stars = 0;

		if (enemiesDestroyedPercent >= 40 && enemiesDestroyedPercent < 60) {

			stars = 1;

		} else if (enemiesDestroyedPercent >= 60 && enemiesDestroyedPercent < 85) {

			stars = 2;

		} else if (enemiesDestroyedPercent >= 85) {

			stars = 3;

		}

		float x = MyGdxGame.WIDTH/2 - 144;
		float y = 250; //320

		for (int i = 0 ; i < stars ; i++) {
			GameEngine.addEntity(new ScoreStar(x, y), GameEngine.EntityType.PLAIN_ANIMATION);
			x+=96;
		}
	}
}
